package com.xsyu.inputreportproject.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xsyu.inputreportproject.Dialog.CustomDialog;
import com.xsyu.inputreportproject.R;
import com.xsyu.inputreportproject.beans.CrushingBaseInfoBeans;
import com.xsyu.inputreportproject.utils.DBUtils;
import com.xsyu.inputreportproject.utils.EditTextUtils;
import com.xsyu.inputreportproject.utils.IdUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class YLBaseInfoActivity extends AppCompatActivity {
    //找到页面上方的返回按钮
    private ImageView imgView = null;
    //压裂基本信息的title
    private TextView txtView = null;
    //删除按钮
    private ImageView ivDelWellNameIcon = null;
    private ImageView ivDelMeaTeamIcon = null;//措施队伍
    private ImageView ivDelCrushTeamIcon = null;//压裂队伍
    private ImageView ivDelZDJIcon = null;//暂堵剂技术服务
    private ImageView ivDelYLYIcon = null;//压裂液技术服务
    private ImageView ivDelCrushMethodIcon = null;//压裂方式
    private ImageView ivDelZCJTypeIcon = null;//支撑剂类型
    private ImageView ivDelHorizonIcon = null;//层位
    private ImageView ivDelYLYSetupIcon = null;//压裂液体系
    //压裂基本信息数据项汇总
    private EditText wellName = null;
    private EditText measureTeam = null;
    private EditText crushingTeam = null;
    private EditText zdjTechServTeam = null;
    private EditText ylyTechServTeam = null;
    private EditText crushingMethod = null;
    private EditText zcjType = null;
    private EditText horizon = null;
    private EditText ylySetup = null;
    //找到提交按钮
    private Button submitButton = null;
    private Button resetButton = null;
    public static int i = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yl_baseinfo);
        InitView();

        //提交事件响应
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // 等待10000毫秒后销毁此页面，并提示登陆成功
                final CustomDialog customDialog = new CustomDialog(YLBaseInfoActivity.this,"提交中...");
                customDialog.show();
                try{
                    i = InsertYLBaseInfo(GetBaseInfo());
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
                System.out.println("iiiiii"+i);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                       customDialog.dismiss();
                        if(i > 0){
                            customDialog.dismiss();
                            ShowSuccessToast("提交成功！",2000);
                        }
                        else {
                            customDialog.dismiss();
                            ShowErrorToast("提交失败！", 2000);
                        }
                    }
                }, 3000);

            }
        });
        //重置事件
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wellName.setText("");
                measureTeam.setText("");
                crushingTeam.setText("");
                zdjTechServTeam.setText("");
                ylyTechServTeam.setText("");
                crushingMethod.setText("");
                zcjType.setText("");
                horizon.setText("");
                ylySetup.setText("");
            }
        });
    }
    private void InitView(){
        //显示返回按钮，按钮默认隐藏
        imgView = findViewById(R.id.iv_back_icon);
        imgView.setVisibility(View.VISIBLE);
        //找到首页title的文本块，修改内容
        txtView = findViewById(R.id.ir_title_name);
        txtView.setText("压裂基本信息表");
        //找到压裂基本信息的文本框
        wellName = findViewById(R.id.et_wellname);
        measureTeam = findViewById(R.id.et_yl_measureteam);
        crushingTeam = findViewById(R.id.et_yl_crushingteam);
        zdjTechServTeam = findViewById(R.id.et_yl_zd_techservice);
        ylyTechServTeam = findViewById(R.id.et_yl_yly_techservice);
        crushingMethod = findViewById(R.id.et_yl_type);
        zcjType = findViewById(R.id.et_yl_zcj_type);
        horizon = findViewById(R.id.et_horizon);
        ylySetup = findViewById(R.id.et_yl_yly_setup);
        //找到删除按钮
        ivDelWellNameIcon = findViewById(R.id.iv_del_wellname);
        ivDelMeaTeamIcon = findViewById(R.id.iv_del_measureteam);
        ivDelCrushTeamIcon = findViewById(R.id.iv_del_crushingteam);
        ivDelZDJIcon = findViewById(R.id.iv_del_zd_techservice);
        ivDelYLYIcon = findViewById(R.id.iv_del_yly_techservice);
        ivDelCrushMethodIcon = findViewById(R.id.iv_del_yl_type);
        ivDelZCJTypeIcon = findViewById(R.id.iv_del_zcj_type);
        ivDelHorizonIcon = findViewById(R.id.iv_del_horizon);
        ivDelYLYSetupIcon = findViewById(R.id.iv_del_yly_setup);
        //输入框删除按钮的监听事件
        EditTextUtils.addEditListener(wellName,ivDelWellNameIcon);
        EditTextUtils.addEditListener(measureTeam,ivDelMeaTeamIcon);
        EditTextUtils.addEditListener(crushingTeam,ivDelCrushTeamIcon);
        EditTextUtils.addEditListener(zdjTechServTeam,ivDelZDJIcon);
        EditTextUtils.addEditListener(ylyTechServTeam,ivDelYLYIcon);
        EditTextUtils.addEditListener(crushingMethod,ivDelCrushMethodIcon);
        EditTextUtils.addEditListener(zcjType,ivDelZCJTypeIcon);
        EditTextUtils.addEditListener(horizon,ivDelHorizonIcon);
        EditTextUtils.addEditListener(ylySetup,ivDelYLYSetupIcon);
        //给按钮设置点击事件，返回首页
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YLBaseInfoActivity.this.finish();
            }
        });
        //找到提交和重置按钮
        submitButton = findViewById(R.id.bt_yl_baseinfo_submit);
        resetButton = findViewById(R.id.bt_yl_baseinfo_reset);
    }
    private CrushingBaseInfoBeans GetBaseInfo(){
        CrushingBaseInfoBeans cbib = new CrushingBaseInfoBeans();
        cbib.setWellId(IdUtils.createIdByDate());
        cbib.setWellName(wellName.getText().toString());
        cbib.setMeasureTeam(measureTeam.getText().toString());
        cbib.setCrushingTeam(crushingTeam.getText().toString());
        cbib.setZdTechServTeam(zdjTechServTeam.getText().toString());
        cbib.setYlTechServTeam(ylyTechServTeam.getText().toString());
        cbib.setCrushingMethod(crushingMethod.getText().toString());
        cbib.setZcjType(zcjType.getText().toString());
        cbib.setHorizon(horizon.getText().toString());
        cbib.setYlySetup(ylySetup.getText().toString());
        return cbib;
    }

    /**
     * 定制成功Toast
     */
    private void ShowSuccessToast(String str,int showTime){
        Toast toast = Toast.makeText(this, str, showTime);
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER , 0, 0);  //设置显示位置
        LinearLayout layout = (LinearLayout) toast.getView();
        layout.setBackgroundColor(Color.GRAY);
        layout.setGravity(Gravity.CENTER);
        layout.setOrientation(LinearLayout.VERTICAL);
        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.success);
        layout.addView(image, 0);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);     //设置字体颜色
        v.setTextSize(20);
        toast.show();
    }
    /**
     * 定制失败Toast
     */
    private void ShowErrorToast(String str,int showTime){
        Toast toast = Toast.makeText(this, str, showTime);
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER , 0, 0);  //设置显示位置
        LinearLayout layout = (LinearLayout) toast.getView();
        layout.setBackgroundColor(Color.GRAY);
        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.error);
        layout.setGravity(Gravity.CENTER);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(image, 0);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);     //设置字体颜色
        v.setTextSize(20);
        toast.show();
    }
    public int InsertYLBaseInfo(final CrushingBaseInfoBeans cbib) throws InterruptedException{
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String sql = "insert into PC_YL_MEASURE_BASEINFO values(?,?,?,?,?,?,?,?,?,?)";
                DBUtils db = new DBUtils();
                PreparedStatement ps = null;
                try {
                    ps = db.GetConDatabase(sql);
                    System.out.println("基本信息"+ps);
                    ps.setObject(1, cbib.getWellId());
                    ps.setObject(2, cbib.getWellName());
                    ps.setObject(3, cbib.getMeasureTeam());
                    ps.setObject(4, cbib.getCrushingTeam());
                    ps.setObject(5, cbib.getZdTechServTeam());
                    ps.setObject(6, cbib.getYlTechServTeam());
                    ps.setObject(7, cbib.getCrushingMethod());
                    ps.setObject(8, cbib.getZcjType());
                    ps.setObject(9, cbib.getHorizon());
                    ps.setObject(10, cbib.getYlySetup());
                    System.out.println("你好"+sql);
                    i = ps.executeUpdate();
                    System.out.println("wwww"+i);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }finally {
                    db.close();
                }
            }
        }).start();
        System.out.println("这块的i"+i);
        return i;
    }
}
