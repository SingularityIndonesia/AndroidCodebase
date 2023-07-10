In order to use the biometric, you need a FragmentActivity context or a Fragment context.

# Check Feature Availability
Inside the activity or fragment you can check biometric availability in this way:
```kotlin
if (biometricIsAvailable.first) {
    ...
}
```

The `biometricIsAvailable` variable will provide you Pair of BiometricIsAvailable code and BiometricError code such:
```kotlin
BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE
BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE
BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED
BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED
BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED
BiometricManager.BIOMETRIC_STATUS_UNKNOWN
```

# Enroll Biometric Feature
You can auto enroll if you need enrollment, by this command:
```kotlin
autoEnrollBiometric(BIOMETRIC_ENROLL_REQUEST)
```

The enrollment result will be catch in activityResult, since this action will request activity result for setting activity of your android device.

# Prompt the Biometric
```kotlin
if (biometricIsAvailable.first) {
    promptBiometricInput(
        negativeButtonText = "Use Password Instead",
        onError = {_, msg -> toast(message = "Error: $msg", isLong = false)},
        onFailed = {toast("Failed")}
    ) {
        toast("Success : ${it.authenticationType} ${it.cryptoObject}", true)
    }
}
```
