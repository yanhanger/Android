package com.xsyu.inputreportproject.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xsyu.inputreportproject.activity.LoadingActivity;

public class EditTextUtils {

    public static void addEditListener(final EditText e1, final ImageView m1) {

        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable e) {
                //监听输入框的变化
                String input = e + "";
                if(input.length() > 0){
                    m1.setVisibility(View.VISIBLE);
                }
                else{
                    m1.setVisibility(View.GONE);
                }
            }
        });

        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setText("");
            }
        });
        e1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b && e1.getText().length()>0){
                    Log.e("abc","失去焦点了");
                    Log.e("abc",e1.getText().length()+"");
                    Log.e("abc",b+"");
                    m1.setVisibility(View.VISIBLE);

                }
                else{
                    m1.setVisibility(View.GONE);
                }
            }
        });

    }
}
