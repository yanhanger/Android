package com.xsyu.inputreportproject.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;

import com.xsyu.inputreportproject.R;

public class CustomDialog extends Dialog{
    private String content;
    public CustomDialog(Context context,String content){
        super(context, R.style.CustomDialog);
        this.content = content;
        InitView();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if(CustomDialog.this.isShowing())
                    CustomDialog.this.dismiss();
                break;
        }
        return true;
    }
    private void InitView(){
        setContentView(R.layout.activity_loading);
        ((TextView)findViewById(R.id.loading_content)).setText(content);
        setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha=0.8f;
        getWindow().setAttributes(attributes);
        setCancelable(false);
    }
}
