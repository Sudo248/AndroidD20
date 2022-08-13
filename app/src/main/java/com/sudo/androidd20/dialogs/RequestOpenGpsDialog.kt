package com.sudo.androidd20.dialogs

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RequestOpenGpsDialog(context: Context) : MaterialAlertDialogBuilder(context) {
    override fun create(): AlertDialog {
        this.setTitle("Open gps")
            .setPositiveButton(
                "SETTING",
                DialogInterface.OnClickListener(openGpsSettingButtonClicked)
            )
            .setNegativeButton(
                "CANCEL",
                DialogInterface.OnClickListener(cancelButtonClicked)
            )

        return super.create()
    }

    private val cancelButtonClicked =
        { dialog: DialogInterface, _: Int -> dialog.dismiss() }

    private val openGpsSettingButtonClicked = { _: DialogInterface, _: Int ->
        context.startActivity(
            Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        )
    }
}