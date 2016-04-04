package com.aecc.aecc.Alertas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import com.aecc.aecc.R;

public class AlertPinDialog extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.alert_pin_title)
                .setMessage(R.string.alert_pin_message)
                .setNegativeButton(R.string.accept_button_text, null);
        AlertDialog dialog = builder.create();

        return dialog;
    }
}
