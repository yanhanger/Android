package com.xsyu.inputreportproject.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xsyu.inputreportproject.Adapter.IndexAdapter;
import com.xsyu.inputreportproject.Dialog.CustomDialog;
import com.xsyu.inputreportproject.R;
import com.xsyu.inputreportproject.beans.Icon;

import java.util.ArrayList;

public class AcidateActivity extends AppCompatActivity {
    //找到页面上方的返回按钮
    private ImageView imgView = null;
    //酸化模块的title
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
        ShowSHTables();

    }
    //初始化页面实现
    private void InitView(){
        //显示返回按钮，按钮默认隐藏
        imgView = findViewById(R.id.iv_back_icon);
        imgView.setVisibility(View.VISIBLE);
        //找到首页title的文本块，修改内容
        txtView = findViewById(R.id.ir_title_name);
        txtView.setText("酸化类措施报表");
        //给按钮设置点击事件，返回首页
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AcidateActivity.this.finish();
            }
        });
    }
    //显示所有需要录入报表
    public void ShowSHTables(){
        //找到页面的GridView
        gridPhoto = findViewById(R.id.gv_sh_report);
        ArrayList<Icon> reportData = new ArrayList<Icon>();
        reportData.add(new Icon(R.drawable.jjt,"基本信息"));
        reportData.add(new Icon(R.drawable.setuppara,"工艺参数"));
        reportData.add(new Icon(R.drawable.pct,"施工工序"));
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
                final CustomDialog customDialog = new CustomDialog(AcidateActivity.this,"上传信息中...");
                customDialog.show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        // 等待10000毫秒后销毁此页面，并提示登陆成功
                        customDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "上传成功",
                                Toast.LENGTH_SHORT).show();
                    }
                }, 10000);
                Toast.makeText(AcidateActivity.this, "你点击了~" + position + "~项", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
