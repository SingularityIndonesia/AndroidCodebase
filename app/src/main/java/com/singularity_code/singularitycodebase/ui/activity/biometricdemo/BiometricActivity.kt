package com.singularity_code.singularitycodebase.ui.activity.biometricdemo

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.pluto.utilities.extensions.toast
import com.singularity_code.codebase.util.autoEnrollBiometric
import com.singularity_code.codebase.util.biometricIsAvailable
import com.singularity_code.codebase.util.promptBiometricInput

class BiometricActivity : FragmentActivity() {

    companion object {
        const val BIOMETRIC_ENROLL_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        autoEnrollBiometric(BIOMETRIC_ENROLL_REQUEST_CODE)

        if (biometricIsAvailable.first) {
            promptBiometricInput(
                negativeButtonText = "Use Password Instead",
                onError = {_, msg -> toast(message = "Error: $msg", isLong = false)},
                onFailed = {toast("Failed")}
            ) {
                toast("Success : ${it.authenticationType} ${it.cryptoObject}", true)
            }
        }
    }

}