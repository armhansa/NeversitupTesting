package com.armhansa.neversituptesting.presentation.custom

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.armhansa.neversituptesting.R
import com.armhansa.neversituptesting.databinding.CustomFullScreenLoadingBinding

class FullScreenLoadingDialog(
    context: Context
): Dialog(context, R.style.FullScreenLoadingStyle) {

    init {
        setContentView(CustomFullScreenLoadingBinding.inflate(LayoutInflater.from(context)).root)
        setCancelable(false)
    }

}