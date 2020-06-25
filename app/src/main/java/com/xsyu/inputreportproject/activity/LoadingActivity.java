package com.xsyu.inputreportproject.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xsyu.inputreportproject.R;

/**
 * 上传loading
 */
public class LoadingActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                // 等待10000毫秒后销毁此页面，并提示登陆成功
                LoadingActivity.this.finish();
            }
        }, 10000);
    }
}
