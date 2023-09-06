package com.singularity_code.singularitycodebase.ui.activity.biometricdemo

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.singularity_code.codebase.util.io.autoEnrollBiometric
import com.singularity_code.codebase.util.io.biometricIsAvailable
import com.singularity_code.codebase.util.io.promptBiometricInput

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
                onError = { _, msg ->
                    Toast.makeText(
                        this,
                        "Error : $msg", Toast.LENGTH_LONG
                    ).show()
                },
                onFailed = {
                    Toast.makeText(
                        this,
                        "Failed", Toast.LENGTH_LONG
                    ).show()
                }
            ) {
                Toast.makeText(
                    this,
                    "Success : ${it.authenticationType} ${it.cryptoObject}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}