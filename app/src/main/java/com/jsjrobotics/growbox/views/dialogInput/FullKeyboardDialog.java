package com.jsjrobotics.growbox.views.dialogInput;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.jsjrobotics.growbox.R;

public abstract class FullKeyboardDialog {

    public static AlertDialog show(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder.setView(R.layout.qwerty_with_number_row).show();
        EditText display = (EditText) dialog.findViewById(R.id.display);
        View numberRow = dialog.findViewById(R.id.number_row);
        View qwertyInput = dialog.findViewById(R.id.qwerty_keyboard);
        View delete = dialog.findViewById(R.id.delete);
        View clearEntry = dialog.findViewById(R.id.clear_entry);
        setupNumberRowClickListeners(display, numberRow);
        setupQwertyClickListeners(display, qwertyInput);
        setupDeleteButton(display, delete);
        setupClearEntry(display, clearEntry);
        return dialog;
    }

    private static void setupClearEntry(EditText display, View view) {

    }

    private static void setupDeleteButton(EditText display, View view) {

    }

    private static void setupQwertyClickListeners(EditText display, View view) {
        QwertyKeyboard input = new QwertyKeyboard(view);
        input.setOnPressedListener(display::append);
    }

    private static void setupNumberRowClickListeners(EditText display, View view) {
        NumberRow input = new NumberRow(view);
        input.setOnPressedListener(display::append);
    }
}
