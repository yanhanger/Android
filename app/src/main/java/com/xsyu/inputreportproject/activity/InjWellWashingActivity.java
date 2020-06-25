package com.xsyu.inputreportproject.activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;
import androidx.core.app.ActivityCompat;

import com.xsyu.inputreportproject.Dialog.CustomDialog;
import com.xsyu.inputreportproject.R;
import com.xsyu.inputreportproject.beans.GZDownholeEquipmentBeans;
import com.xsyu.inputreportproject.beans.InjWellWashingBeans;
import com.xsyu.inputreportproject.utils.DBUtils;
import com.xsyu.inputreportproject.utils.EditTextUtils;
import com.xsyu.inputreportproject.utils.IdUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * 注水井洗井
 */
public class InjWellWashingActivity extends AppCompatActivity {
    private File currentImageFile = null;
    private File currentImageFile1 = null;
    private Uri uri = null;
    //找到页面上方的返回按钮
    private ImageView imgView = null;
    //水井的title
    private TextView txtView = null;
    //先弄好时间的显示
    private EditText etComingTime = null;
    private EditText etWashingTime = null;
    private EditText etLeavingTime = null;
    private EditText etWashingWellName = null;//洗井井号
    private EditText etWashingType = null;//洗井方式
    private EditText etWashingTeam = null;//洗井队伍
    private EditText etInjMedium = null;//注入介质
    private EditText etMatchInj = null;//配注量
    private EditText etOilPress = null;//油压
    private EditText etCasingPress = null;//套压
    private EditText etSupervior = null;//现场监督人
    private EditText etWashingPush = null;//洗井排量
    private EditText etPress = null;//压力
    //输入框的删除按钮
    private ImageView ivWashingWellName = null;
    private ImageView ivWashingType = null;
    private ImageView ivInjMedium = null;
    private ImageView ivMatchInj = null;
    private ImageView ivWashingTeam = null;
    private ImageView ivOilPress = null;
    private ImageView ivCasingPress = null;
    private ImageView ivSupervior = null;
    private ImageView ivWashingPush = null;
    private ImageView ivPress = null;
    private ImageView ivWashingPhoto = null;    //洗井现场照片
    private ImageView ivWashingDelete = null;
    private ImageView ivWaterPhoto = null;       //水质照片
    private ImageView ivWaterDelPhoto = null;
    public int year;
    public int month;
    public int day;
    public static int i;
    private Button submitButton = null;
    //读写权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
            ,Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_injwellwashing);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }

        InitView();
        //提交事件响应
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                // 等待10000毫秒后销毁此页面，并提示登陆成功
                final CustomDialog customDialog = new CustomDialog(InjWellWashingActivity.this,"提交中...");
                customDialog.show();
                try{
                    i = InsertInjWellWashing(GetInjWellWashing());
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                //System.out.println("iiiiii"+i);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
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
    }
    private void InitView(){
        //显示返回按钮，按钮默认隐藏
        imgView = findViewById(R.id.iv_back_icon);
        imgView.setVisibility(View.VISIBLE);
        //找到首页title的文本块，修改内容
        txtView = findViewById(R.id.ir_title_name);
        txtView.setText("注水井洗井明细表");
        //给按钮设置点击事件，返回首页
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InjWellWashingActivity.this.finish();
            }
        });
        //找到时间输入控件
        etComingTime = findViewById(R.id.et_zsj_xj_comingtime);
        etWashingTime = findViewById(R.id.et_zsj_xj_washingtime);
        etLeavingTime = findViewById(R.id.et_zsj_xj_leavetime);
        etWashingWellName = findViewById(R.id.et_zsj_xj_wellname);
        etWashingType = findViewById(R.id.et_zsj_xj_method);
        etInjMedium = findViewById(R.id.et_zsj_xj_injmedium);
        etWashingTeam = findViewById(R.id.et_zsj_xj_washingteam);
        etMatchInj = findViewById(R.id.et_zsj_xj_matchinj);
        etOilPress = findViewById(R.id.et_zsj_xj_oilpress);
        etCasingPress = findViewById(R.id.et_zsj_xj_casingpress);
        etSupervior = findViewById(R.id.et_zsj_xj_supervior);
        etWashingPush = findViewById(R.id.et_zsj_xj_washingpush);
        etPress = findViewById(R.id.et_zsj_xj_press);
        ivWashingWellName = findViewById(R.id.iv_zsj_xj_del_wellname);
        ivWashingType = findViewById(R.id.iv_zsj_xj_del_method);
        ivInjMedium = findViewById(R.id.iv_zsj_xj_del_injmedium);
        ivWashingTeam = findViewById(R.id.iv_zsj_xj_del_washingteam);
        ivMatchInj = findViewById(R.id.iv_zsj_xj_del_yly_matchinj);
        ivOilPress = findViewById(R.id.iv_zsj_xj_del_olipress);
        ivCasingPress = findViewById(R.id.iv_zsj_xj_del_casingpress);
        ivSupervior = findViewById(R.id.iv_zsj_xj_del_supervior);
        ivWashingPush = findViewById(R.id.iv_zsj_xj_del_washingpush);
        ivPress = findViewById(R.id.iv_zsj_xj_del_press);
        ivWashingPhoto = findViewById(R.id.iv_washing_camerapicdefault);
        ivWashingDelete = findViewById(R.id.iv_washing_delete);
        ivWaterPhoto =findViewById(R.id.iv_water_camerapicdefault);
        ivWaterDelPhoto = findViewById(R.id.iv_water_delete);
        //点击拍照从卡中取
        ivWashingPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path=Environment.getExternalStorageDirectory().getPath()+"/pictures/";
                File dir = new File(path);
                if(dir.exists()){
                    dir.mkdirs();
                }
                currentImageFile = new File(dir,System.currentTimeMillis() + ".jpg");
                if(!currentImageFile.exists()){
                    try {
                        currentImageFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentImageFile));
                startActivityForResult(it, Activity.DEFAULT_KEYS_DIALER);
            }
        });
        //点击拍照从卡中取
        ivWaterPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path=Environment.getExternalStorageDirectory().getPath()+"/pictures/";
                File dir = new File(path);
                if(dir.exists()){
                    dir.mkdirs();
                }
                currentImageFile1 = new File(dir,System.currentTimeMillis() + ".jpg");
                if(!currentImageFile1.exists()){
                    try {
                        currentImageFile1.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentImageFile1));

                startActivityForResult(it, Activity.DEFAULT_KEYS_DIALER);
            }
        });

        //做输入的监听
        EditTextUtils.addEditListener(etWashingWellName,ivWashingWellName);
        EditTextUtils.addEditListener(etWashingType,ivWashingType);
        EditTextUtils.addEditListener(etInjMedium,ivInjMedium);
        EditTextUtils.addEditListener(etWashingTeam,ivWashingTeam);
        EditTextUtils.addEditListener(etMatchInj,ivMatchInj);
        EditTextUtils.addEditListener(etOilPress,ivOilPress);
        EditTextUtils.addEditListener(etCasingPress,ivCasingPress);
        EditTextUtils.addEditListener(etSupervior,ivSupervior);
        EditTextUtils.addEditListener(etWashingPush,ivWashingPush);
        EditTextUtils.addEditListener(etPress,ivPress);
        //点击输入框显示时间控件
        etComingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcalendar = Calendar.getInstance();     //  获取当前时间    —   年、月、日
                year = mcalendar.get(Calendar.YEAR);         //  得到当前年
                month = mcalendar.get(Calendar.MONTH);       //  得到当前月
                day = mcalendar.get(Calendar.DAY_OF_MONTH);  //  得到当前日
                new DatePickerDialog(InjWellWashingActivity.this, new DatePickerDialog.OnDateSetListener() {      //  日期选择对话框
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //  这个方法是得到选择后的 年，月，日，分别对应着三个参数 — year、month、dayOfMonth
                        etComingTime.setText(year+"-"+month+"-"+dayOfMonth);
                    }
                },year,month,day).show();   //  弹出日历对话框时，默认显示 年，月，日
            }
        });
        etWashingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcalendar = Calendar.getInstance();     //  获取当前时间    —   年、月、日
                year = mcalendar.get(Calendar.YEAR);         //  得到当前年
                month = mcalendar.get(Calendar.MONTH);       //  得到当前月
                day = mcalendar.get(Calendar.DAY_OF_MONTH);  //  得到当前日
                new DatePickerDialog(InjWellWashingActivity.this, new DatePickerDialog.OnDateSetListener() {      //  日期选择对话框
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //  这个方法是得到选择后的 年，月，日，分别对应着三个参数 — year、month、dayOfMonth
                        etWashingTime.setText(year+"-"+month+"-"+dayOfMonth);
                    }
                },year,month,day).show();   //  弹出日历对话框时，默认显示 年，月，日
            }
        });
        etLeavingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcalendar = Calendar.getInstance();     //  获取当前时间    —   年、月、日
                year = mcalendar.get(Calendar.YEAR);         //  得到当前年
                month = mcalendar.get(Calendar.MONTH);       //  得到当前月
                day = mcalendar.get(Calendar.DAY_OF_MONTH);  //  得到当前日
                new DatePickerDialog(InjWellWashingActivity.this, new DatePickerDialog.OnDateSetListener() {      //  日期选择对话框
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //  这个方法是得到选择后的 年，月，日，分别对应着三个参数 — year、month、dayOfMonth
                        etLeavingTime.setText(year+"-"+month+"-"+dayOfMonth);
                    }
                },year,month,day).show();   //  弹出日历对话框时，默认显示 年，月，日
            }
        });
        submitButton = findViewById(R.id.bt_zsj_xj_submit);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean flag = false;
        boolean flag1 = false;
        if(currentImageFile != null && flag ==false){
            if (requestCode == Activity.DEFAULT_KEYS_DIALER) {
                ivWashingPhoto.setImageURI(Uri.fromFile(currentImageFile));
                flag =true;
            }
        }
        if(currentImageFile1 != null && flag1 == false){
            if (requestCode == Activity.DEFAULT_KEYS_DIALER) {
                ivWaterPhoto.setImageURI(Uri.fromFile(currentImageFile));
                flag1 =true;
            }
        }
    }

    //获取水井洗井实体类
    private InjWellWashingBeans GetInjWellWashing(){
        InjWellWashingBeans iwwb = new InjWellWashingBeans();
        iwwb.setWellId(IdUtils.createIdByDate());
        iwwb.setWashingWellName(etWashingWellName.getText().toString());
        iwwb.setWashingType(etWashingType.getText().toString());
        iwwb.setInjMedium(etInjMedium.getText().toString());
        iwwb.setMatchInj(etMatchInj.getText().toString());
        iwwb.setWashingTeam(etWashingTeam.getText().toString());
        iwwb.setOliPress(etOilPress.getText().toString());
        iwwb.setCasingPrss(etCasingPress.getText().toString());
        iwwb.setComingTime(Date.valueOf(etComingTime.getText().toString()));
        iwwb.setWashingTime(Date.valueOf(etWashingTime.getText().toString()));
        iwwb.setLeavingTime(Date.valueOf(etLeavingTime.getText().toString()));
        iwwb.setSupervior(etSupervior.getText().toString());
        //洗井照片待定
        iwwb.setWashingPush(etWashingPush.getText().toString());
        iwwb.setPress(etPress.getText().toString());
        iwwb.setWashingPhoto(Uri.fromFile(currentImageFile).toString());
        iwwb.setWaterQulifyPhoto(Uri.fromFile(currentImageFile1).toString());
        //水质照片待定
        return iwwb;
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
    //插入数据库
    public int InsertInjWellWashing(final InjWellWashingBeans ijwb)throws InterruptedException{
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String sql = "insert into PC_SJ_MEASURE_WASHING values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                DBUtils db = new DBUtils();
                PreparedStatement ps = null;
                try {
                    ps = db.GetConDatabase(sql);
                    ps.setString(1, ijwb.getWellId());
                    ps.setString(2, ijwb.getWashingTeam());
                    ps.setString(3, ijwb.getWashingWellName());
                    ps.setString(4, ijwb.getWashingType());
                    ps.setString(5, ijwb.getInjMedium());
                    ps.setString(6, ijwb.getMatchInj());
                    ps.setFloat(7, Float.parseFloat(ijwb.getOliPress()));
                    ps.setFloat(8, Float.parseFloat(ijwb.getCasingPrss()));
                    ps.setDate(9, ijwb.getComingTime());
                    ps.setDate(10, ijwb.getWashingTime());
                    ps.setDate(11,ijwb.getLeavingTime());
                    ps.setString(12,ijwb.getSupervior());
                    ps.setFloat(13,Float.parseFloat(ijwb.getWashingPush()));
                    ps.setFloat(14,Float.parseFloat(ijwb.getPress()));
                    ps.setString(15,ijwb.getWashingPhoto());
                    ps.setString(16,ijwb.getWaterQulifyPhoto());
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
        return i;
    }

}
