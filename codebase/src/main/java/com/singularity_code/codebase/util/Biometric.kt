package com.singularity_code.codebase.util

import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import timber.log.Timber
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.Key
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey


private fun biometricIsAvailable(
    activity: FragmentActivity
): Pair<Boolean, ErrorCode?> {
    val biometricManager = BiometricManager.from(activity)
    return when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
        BiometricManager.BIOMETRIC_SUCCESS -> true to null
        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> false to BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE
        BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> false to BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE
        BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> false to BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED
        BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> false to BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED
        BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> false to BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED
        BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> false to BiometricManager.BIOMETRIC_STATUS_UNKNOWN
        else -> false to BiometricManager.BIOMETRIC_STATUS_UNKNOWN
    }
}

val FragmentActivity.biometricIsAvailable: Pair<Boolean, ErrorCode?>
    get() = biometricIsAvailable(this)

val Fragment.biometricIsAvailable: Pair<Boolean, ErrorCode?>
    get() = biometricIsAvailable(requireActivity())


private fun _autoEnrollBiometric(
    fragmentActivity: FragmentActivity,
    EnrollRequestCode: Int
) {
    if (fragmentActivity.biometricIsAvailable.second == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED) {
        // Prompts the user to create credentials that your app accepts.
        val enrollIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                putExtra(
                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                )
            }
        } else {
            Timber.tag("Biometric").e("Biometric features are currently unavailable.")
            null
        }
        if (enrollIntent != null)
            fragmentActivity.startActivityForResult(enrollIntent, EnrollRequestCode)
    }
}

/**
 * @param EnrollRequestCode Activity result request code. Catch this code in onActivityResult block if you need it.
 */
fun FragmentActivity.autoEnrollBiometric(
    EnrollRequestCode: Int
) {
    _autoEnrollBiometric(this, EnrollRequestCode)
}

/**
 * @param EnrollRequestCode Activity result request code. Catch this code in onActivityResult block if you need it.
 */
fun Fragment.autoEnrollBiometric(
    EnrollRequestCode: Int
) {
    _autoEnrollBiometric(requireActivity(), EnrollRequestCode)
}

@Throws(
    NoSuchProviderException::class,
    NoSuchAlgorithmException::class,
    InvalidAlgorithmParameterException::class
)
private fun createKey(): Key {
    val algorithm = KeyProperties.KEY_ALGORITHM_AES
    val provider = "AndroidKeyStore"
    val keyGenerator = KeyGenerator.getInstance(algorithm, provider)
    val keyGenParameterSpec = KeyGenParameterSpec.Builder(
        "MY_KEY",
        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    )
        .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
        .setUserAuthenticationRequired(true)
        .build()
    keyGenerator.init(keyGenParameterSpec)
    return keyGenerator.generateKey()
}

@Throws(
    NoSuchPaddingException::class,
    NoSuchAlgorithmException::class,
    InvalidKeyException::class
)
private fun getEncryptCipher(key: Key): BiometricPrompt.CryptoObject {
    val algorithm = KeyProperties.KEY_ALGORITHM_AES
    val blockMode = KeyProperties.BLOCK_MODE_CBC
    val padding = KeyProperties.ENCRYPTION_PADDING_PKCS7
    val cipher = Cipher.getInstance("$algorithm/$blockMode/$padding")
    cipher.init(Cipher.ENCRYPT_MODE, key)
    return BiometricPrompt.CryptoObject(cipher)
}


private fun _promptBiometricInput(
    activity: FragmentActivity,
    title: String,
    subTitle: String,
    negativeButtonText: String?,
    onError: (
        errorCode: Int,
        errString: CharSequence
    ) -> Unit,
    onFailed: () -> Unit,
    onSuccess: (BiometricPrompt.AuthenticationResult) -> Unit,
) {
    val executor = ContextCompat.getMainExecutor(activity)
    val biometricPrompt = BiometricPrompt(activity, executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(
                errorCode: Int,
                errString: CharSequence
            ) {
                super.onAuthenticationError(errorCode, errString)
                onError.invoke(errorCode, errString)
            }

            override fun onAuthenticationSucceeded(
                result: BiometricPrompt.AuthenticationResult
            ) {
                super.onAuthenticationSucceeded(result)
                onSuccess.invoke(result)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                onFailed.invoke()
            }
        })

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle(title)
        .setSubtitle(subTitle)
        .apply {
            if (!negativeButtonText.isNullOrBlank()) {
                setNegativeButtonText(negativeButtonText)
            }
        }
        .build()

    val cipherObject = getEncryptCipher(createKey())

    biometricPrompt.authenticate(promptInfo, cipherObject)
}

fun FragmentActivity.promptBiometricInput(
    title: String = "Biometric login for my app",
    subTitle: String = "Log in using your biometric credential",
    negativeButtonText: String? = null,
    onError: (
        errorCode: Int,
        errString: CharSequence
    ) -> Unit = { _, _ -> },
    onFailed: () -> Unit = {},
    onSuccess: (BiometricPrompt.AuthenticationResult) -> Unit = {},
) {
    _promptBiometricInput(
        this, title, subTitle, negativeButtonText, onError, onFailed, onSuccess
    )
}

fun Fragment.promptBiometricInput(
    title: String = "Biometric login for my app",
    subTitle: String = "Log in using your biometric credential",
    negativeButtonText: String? = null,
    onError: (
        errorCode: Int,
        errString: CharSequence
    ) -> Unit = { _, _ -> },
    onFailed: () -> Unit = {},
    onSuccess: (BiometricPrompt.AuthenticationResult) -> Unit = {},
) {
    _promptBiometricInput(
        requireActivity(), title, subTitle, negativeButtonText, onError, onFailed, onSuccess
    )
}