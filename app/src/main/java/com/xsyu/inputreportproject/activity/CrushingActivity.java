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

public class CrushingActivity extends AppCompatActivity {
    //找到页面上方的返回按钮
    private ImageView imgView = null;
    //压裂模块的title
    private TextView txtView = null;
    //找到图标GridView
    private GridView gridPhoto = null;
    private BaseAdapter adapter = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crushing_module);
        //初始化页面
        InitView();
        //页面中收集报表
        ShowYLTables();

    }
    //初始化页面实现
    private void InitView(){
        //显示返回按钮，按钮默认隐藏
        imgView = findViewById(R.id.iv_back_icon);
        imgView.setVisibility(View.VISIBLE);
        //找到首页title的文本块，修改内容
        txtView = findViewById(R.id.ir_title_name);
        txtView.setText("压裂类措施报表");
        //给按钮设置点击事件，返回首页
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrushingActivity.this.finish();
            }
        });
    }
    //显示所有需要录入报表
    public void ShowYLTables(){
        //找到页面的GridView
        gridPhoto = findViewById(R.id.gv_yl_report);
        ArrayList<Icon> reportData = new ArrayList<Icon>();
        reportData.add(new Icon(R.drawable.jjt,"基本信息"));
        reportData.add(new Icon(R.drawable.setuppara,"工艺参数"));
        reportData.add(new Icon(R.drawable.pct,"工料核实"));
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
                Intent intent = null;
                switch (position){
                    case 0:
                        //打开基本信息界面
                        intent = new Intent(CrushingActivity.this,YLBaseInfoActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
