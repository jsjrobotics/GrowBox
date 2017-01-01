package com.jsjrobotics.growbox.views;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsjrobotics.growbox.R;

public class FullKeyboardView extends DialogFragment{


    private View mView;
    private QwertyKeyboard mKeyboard;
    private NumberRow mNumberRow;

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.qwerty_with_number_row, parent, false);
        View keyboard = mView.findViewById(R.id.qwerty_keyboard);
        View numberRow = mView.findViewById(R.id.number_row);
        mKeyboard = new QwertyKeyboard(keyboard);
        mNumberRow = new NumberRow(numberRow);
        return mView;
    }

    @Override
    public void onDismiss(final DialogInterface dialog){
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(final DialogInterface dialog){
        super.onCancel(dialog);

    }
}
