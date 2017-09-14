package com.example.pc.todo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by ola on 14/09/2017.
 */

public class Custom_Dialog extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);
    }

    //view 안에 있는 contents를 설정한다
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.custom_dialog,container,false);
        layout.findViewById(R.id.edit_groupname);
        layout.findViewById(R.id.endroll_button);
        layout.findViewById(R.id.dismissButton);
        return layout;
    }

    public void onClick(View v) {
        dismiss();
    }




}
