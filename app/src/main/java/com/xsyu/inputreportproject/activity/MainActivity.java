package com.xsyu.inputreportproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mysql.jdbc.MiniAdmin;
import com.xsyu.inputreportproject.Adapter.IndexAdapter;
import com.xsyu.inputreportproject.R;
import com.xsyu.inputreportproject.beans.Icon;
import com.xsyu.inputreportproject.utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //压裂模块点击实现
    private RelativeLayout moduleCrusing = null;
    //酸化模块
    private RelativeLayout moduleAcidate = null;
    //管柱管理模块
    private RelativeLayout moduleGZManage = null;
    //水井措施类模块
    private RelativeLayout moduleWaterWellMeasure = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到压裂模块
        moduleCrusing = findViewById(R.id.module_crushing);
        //酸化模块
        moduleAcidate = findViewById(R.id.module_acidate);
        //管柱管理模块
        moduleGZManage = findViewById(R.id.module_gz_manage);
        //找到水井措施模块
        moduleWaterWellMeasure = findViewById(R.id.module_waterwell_measure);
        System.out.println("走到这里了");
        //设置压裂模块的监听事件
        moduleCrusing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进入压裂模块
                Intent intent = new Intent(MainActivity.this,CrushingActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"我进来的压裂模块",Toast.LENGTH_LONG).show();
            }
        });
        //设置酸化模块的监听事件
        moduleAcidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进入酸化模块
                Intent intent = new Intent(MainActivity.this,AcidateActivity.class);
                startActivity(intent);
            }
        });
        //设置管柱管理的监听事件
        moduleGZManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GZManageActivity.class);
                startActivity(intent);
            }
        });
        //设置水井措施管理的监听事件
        moduleWaterWellMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WaterWellMeasureActivity.class);
                startActivity(intent);
            }
        });

    }




}
