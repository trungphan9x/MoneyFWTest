package com.trung.applicationMoneyFW.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.KeyEvent
import com.trung.applicationMoneyFW.R

object AppDialog {
    var alertDialog: AlertDialog? = null

    private fun initDialog(context: Context?) {
        if (context != null) {
            if (alertDialog != null) {
                if (alertDialog!!.isShowing) {
                    return
                }
                alertDialog = null
            }
        }
    }

    fun showDialog(context: Context, message: String, positive: (() -> Unit)? = null) {
        initDialog(context)

        val alertDialogBuilder = AlertDialog.Builder(context, R.style.MyCustomAlertDialog)

        alertDialogBuilder.setMessage(message)

        alertDialogBuilder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
            dialog.dismiss()
            positive?.invoke()
        })
        alertDialogBuilder.setOnCancelListener { dialog ->
            dialog.dismiss()
            positive?.invoke()
        }
        alertDialogBuilder.setOnKeyListener { dialog, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dialog.dismiss()
                positive?.invoke()
            }
            true
        }
        alertDialog = alertDialogBuilder.create()

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            alertDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(context.getColor(R.color.white))
//            alertDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.background = context.resources.getDrawable(R.color.tealish)
//        }
        alertDialog?.show()
    }
}