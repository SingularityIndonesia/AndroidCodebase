package com.singularity_code.codebase.util

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi


val Context.getSimCardInformation: String
    @RequiresApi(Build.VERSION_CODES.Q)
    get() {
        val telephonyManager =
            getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        // For Android Q and above
        return telephonyManager.run {
            "$carrierIdFromSimMccMnc ${this.simCountryIso} ${this.simOperatorName} ${this.simSpecificCarrierIdName} ${this.simOperator}"
        }
    }