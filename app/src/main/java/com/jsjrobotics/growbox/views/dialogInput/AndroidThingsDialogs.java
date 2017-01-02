package com.jsjrobotics.growbox.views.dialogInput;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import com.jsjrobotics.growbox.R;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class AndroidThingsDialogs {

    public static AlertDialog showFullKeyboard(Context context, Consumer<String> onSubmit, Consumer<Void> onNoEntry){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setOnCancelListener(dialog -> onNoEntry.accept(null));
        AlertDialog dialog = builder.setView(R.layout.qwerty_with_number_row).show();
        EditText display = (EditText) dialog.findViewById(R.id.display);
        View numberRow = dialog.findViewById(R.id.number_row);
        View qwertyInput = dialog.findViewById(R.id.qwerty_keyboard);
        View delete = dialog.findViewById(R.id.delete);
        View clearEntry = dialog.findViewById(R.id.clear_entry);
        View submit = dialog.findViewById(R.id.submit);
        View cancel = dialog.findViewById(R.id.cancel);
        setupNumberRowClickListeners(display, numberRow);
        setupQwertyClickListeners(display, qwertyInput);
        setupDeleteButton(display, delete);
        setupClearEntry(display, clearEntry);
        setupCancelButton(dialog, cancel);
        setupSubmitButton(dialog, display, submit, onSubmit);
        return dialog;
    }

    public static AlertDialog showTimePicker(Context context, Consumer<SelectedTime> onSubmit, Consumer<Void> onNoEntry){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setOnCancelListener(dialog -> onNoEntry.accept(null));
        AlertDialog dialog = builder.setView(R.layout.time_picker).show();
        View submit = dialog.findViewById(R.id.submit);
        View cancel = dialog.findViewById(R.id.cancel);
        TimePicker timePicker = (TimePicker) dialog.findViewById(R.id.timePicker);
        setupCancelButton(dialog, cancel);
        setupSubmitButton(dialog, timePicker, submit, onSubmit);
        return dialog;
    }

    public static AlertDialog showNumberPad(Context context, Consumer<String> onSubmit, Consumer<Void> onNoEntry){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setOnCancelListener(dialog -> onNoEntry.accept(null));
        View view = LayoutInflater.from(context).inflate(R.layout.number_pad, null);
        AlertDialog dialog = builder.setView(view).show();
        View submit = dialog.findViewById(R.id.submit);
        View cancel = dialog.findViewById(R.id.cancel);
        EditText display = (EditText) dialog.findViewById(R.id.display);
        View delete = dialog.findViewById(R.id.delete);
        View clearEntry = dialog.findViewById(R.id.clear_entry);
        setupNumberRowClickListeners(display, view);
        setupCancelButton(dialog, cancel);
        setupSubmitButton(dialog, display, submit, onSubmit);
        setupDeleteButton(display, delete);
        setupClearEntry(display, clearEntry);
        return dialog;
    }


    private static void setupSubmitButton(final AlertDialog dialog,
                                          final TimePicker display,
                                          final View view,
                                          final Consumer<SelectedTime> listener) {
        view.setOnClickListener(ignored -> {
            dialog.dismiss();
            SelectedTime result = new SelectedTime(display.getHour(), display.getMinute());
            listener.accept(result);
        });
    }

    private static void setupSubmitButton(final AlertDialog dialog,
                                          final EditText display,
                                          final View view,
                                          final Consumer<String> listener) {
        view.setOnClickListener(ignored -> {
            String result = display.getText().toString();
            dialog.dismiss();
            listener.accept(result);
        });
    }

    private static void setupCancelButton(final AlertDialog dialog, final View cancel) {
        cancel.setOnClickListener(ignored -> dialog.dismiss());
    }

    private static void setupClearEntry(final EditText display, final View view) {
        view.setOnClickListener(ignored -> {
            String text = display.getText().toString();
            if (text.isEmpty()) { return;}
            display.getText().delete(0, text.length());
        });
    }

    private static void setupDeleteButton(final EditText display, final View view) {
        view.setOnClickListener(ignored -> {
            String text = display.getText().toString();
            if (text.isEmpty()) { return;}
            display.getText().delete(text.length()-1, text.length());
        });
    }

    private static void setupQwertyClickListeners(final EditText display, final View view) {
        QwertyKeyboard input = new QwertyKeyboard(view);
        input.setOnPressedListener(display::append);
    }

    private static void setupNumberRowClickListeners(final EditText display, final View view) {
        NumberRow input = new NumberRow(view);
        input.setOnPressedListener(display::append);
    }
}
