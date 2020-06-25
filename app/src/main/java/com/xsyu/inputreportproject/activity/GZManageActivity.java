package com.xsyu.inputreportproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xsyu.inputreportproject.Adapter.IndexAdapter;
import com.xsyu.inputreportproject.R;
import com.xsyu.inputreportproject.beans.Icon;

import java.util.ArrayList;

public class GZManageActivity extends AppCompatActivity {
    //找到页面上方的返回按钮
    private ImageView imgView = null;
    //管柱模块的title
    private TextView txtView = null;
    //找到图标GridView
    private GridView gridPhoto = null;
    private BaseAdapter adapter = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acidate_module);
        //初始化页面
        InitView();
        //页面中收集报表
        ShowGZTables();

    }
    //初始化页面实现
    private void InitView(){
        //显示返回按钮，按钮默认隐藏
        imgView = findViewById(R.id.iv_back_icon);
        imgView.setVisibility(View.VISIBLE);
        //找到首页title的文本块，修改内容
        txtView = findViewById(R.id.ir_title_name);
        txtView.setText("管柱管理类报表");
        //给按钮设置点击事件，返回首页
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GZManageActivity.this.finish();
            }
        });
    }
    //显示所有需要录入报表
    public void ShowGZTables(){
        //找到页面的GridView
        gridPhoto = findViewById(R.id.gv_sh_report);
        ArrayList<Icon> reportData = new ArrayList<Icon>();
        reportData.add(new Icon(R.mipmap.downholeequipment,"器材发放"));
        adapter = new IndexAdapter<Icon>(reportData,R.layout.activity_report_item) {
            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                holder.setImageResource(R.id.ir_image_icon, obj.getIcon_Id());
                holder.setText(R.id.ir_txt_icon, obj.getIcon_Name());
                System.out.println("我进来了"+holder.getItemView());
            }
        };
        gridPhoto.setAdapter(adapter);
        gridPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GZManageActivity.this, "你点击了~" + position + "~项", Toast.LENGTH_SHORT).show();
                Intent intent = null;
                switch (position){
                    case 0:
                        //打开基本信息界面
                        intent = new Intent(GZManageActivity.this,GZDownholeEquipmentActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
