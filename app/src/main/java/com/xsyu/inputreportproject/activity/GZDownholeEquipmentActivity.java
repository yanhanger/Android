package com.xsyu.inputreportproject.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xsyu.inputreportproject.Adapter.IndexAdapter;
import com.xsyu.inputreportproject.Dialog.CustomDialog;
import com.xsyu.inputreportproject.R;
import com.xsyu.inputreportproject.beans.GZDownholeEquipmentBeans;
import com.xsyu.inputreportproject.utils.DBUtils;
import com.xsyu.inputreportproject.utils.EditTextUtils;
import com.xsyu.inputreportproject.utils.IdUtils;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class GZDownholeEquipmentActivity extends AppCompatActivity {
    //找到页面上方的返回按钮
    private ImageView imgView = null;
    //管器材发放模块的title
    private TextView txtView = null;
    //页面中的spinner
    private Spinner spMaterial = null;
    private Spinner spSource = null;
    private Spinner spType = null;
    private Spinner spSpec = null;
    private Spinner spModelName = null;
    private Spinner spManufacture = null;
    private Spinner spNumber = null;
    private Spinner spPickPoint = null;
    //定义基本的适配类
    private BaseAdapter myAdapter = null;
    //定义ArriyList对象
    private ArrayList<String> material = new ArrayList<String>();
    private ArrayList<String> source = new ArrayList<String>();
    private ArrayList<String> type = new ArrayList<String>();
    private ArrayList<String> spec = new ArrayList<String>();
    private ArrayList<String> modelName = new ArrayList<String>();
    private ArrayList<String> manufactures = new ArrayList<String>();
    private ArrayList<String> number = new ArrayList<String>();
    private ArrayList<String> pickPoint = new ArrayList<String>();
    //判断是不是第一次点进去判断标志
    private boolean oneSelected = false;
    //定义获取的spinner值
    private String materialSp = null;
    private String sourceSp = null;
    private String typeSp = null;
    private String specSp = null;
    private String modelNameSp = null;
    private String manufactureSp = null;
    private String numberSp = null;
    private String pickPointSp = null;
    private int isChanged = 0;
    //定义提交和重置按钮
    private Button submitButton = null;
    private Button resetButton = null;
    private Switch changeSwitch = null;
    //定义井号
    private EditText etWellName = null;
    private EditText etUnitPrice = null;
    private EditText etSumPrice = null;
    public static int i;
    private ImageView ivGzQcWellName = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gz_downholeequipment);
        //初始化页面
        InitView();
        //提交事件响应
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                // 等待10000毫秒后销毁此页面，并提示登陆成功
                    final CustomDialog customDialog = new CustomDialog(GZDownholeEquipmentActivity.this,"提交中...");
                    customDialog.show();
                    try{
                        i = InsertDownholeEquip(GetDownholeEquip());
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
        txtView.setText("井下器材发放明细表");
        //找到对应的Spinner
        spMaterial = findViewById(R.id.sp_gz_qc_material);
        material.add(0,"材料1");
        material.add(1,"材料2");
        spSource = findViewById(R.id.sp_gz_qc_source);
        source.add(0,"来源1");
        source.add(1,"来源2");
        spType = findViewById(R.id.sp_gz_qc_type);
        type.add(0,"类型1");
        type.add(1,"类型2");
        spSpec = findViewById(R.id.sp_gz_qc_spec);
        spec.add(0,"规格1");
        spec.add(1,"规格2");
        spModelName = findViewById(R.id.sp_gz_qc_modelname);
        modelName.add(0,"型号1");
        modelName.add(1,"型号2");
        spManufacture = findViewById(R.id.sp_gz_qc_fanufacture);
        manufactures.add(0,"厂家1");
        manufactures.add(1,"厂家2");
        spNumber = findViewById(R.id.sp_gz_qc_number);
        for(int i=0;i<10;i++){
            number.add(i,String.valueOf(i+1));
        }
        spPickPoint = findViewById(R.id.sp_gz_qc_pickpoint);
        pickPoint.add(0,"领料点1");
        pickPoint.add(1,"领料点2");
        //材料
        myAdapter = new IndexAdapter<String>(material,R.layout.spinner_item) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_spinner_itemname,obj);
                Log.e("abc",obj);
            }
        };
        spMaterial.setAdapter(myAdapter);
        spMaterial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(oneSelected){
                    TextView txt_name = (TextView) view.findViewById(R.id.tv_spinner_itemname);
                    materialSp = txt_name.getText().toString();
                }else oneSelected = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //来源
        myAdapter = new IndexAdapter<String>(source,R.layout.spinner_item) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_spinner_itemname,obj);
                Log.e("abc",obj);
            }
        };
        spSource.setAdapter(myAdapter);
        spSource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(oneSelected){
                    TextView txt_name = (TextView) view.findViewById(R.id.tv_spinner_itemname);
                    sourceSp = txt_name.getText().toString();
                }else oneSelected = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //类别
        myAdapter = new IndexAdapter<String>(type,R.layout.spinner_item) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_spinner_itemname,obj);
                Log.e("abc",obj);
            }
        };
        spType.setAdapter(myAdapter);
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(oneSelected){
                    TextView txt_name = (TextView) view.findViewById(R.id.tv_spinner_itemname);
                    typeSp = txt_name.getText().toString();
                }else oneSelected = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //规格
        myAdapter = new IndexAdapter<String>(spec,R.layout.spinner_item) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_spinner_itemname,obj);
                Log.e("abc",obj);
            }
        };
        spSpec.setAdapter(myAdapter);
        spSpec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(oneSelected){
                    TextView txt_name = (TextView) view.findViewById(R.id.tv_spinner_itemname);
                    specSp = txt_name.getText().toString();
                }else oneSelected = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //型号
        myAdapter = new IndexAdapter<String>(modelName,R.layout.spinner_item) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_spinner_itemname,obj);
                Log.e("abc",obj);
            }
        };
        spModelName.setAdapter(myAdapter);
        spModelName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(oneSelected){
                    TextView txt_name = (TextView) view.findViewById(R.id.tv_spinner_itemname);
                    modelNameSp = txt_name.getText().toString();
                }else oneSelected = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //厂家
        myAdapter = new IndexAdapter<String>(manufactures,R.layout.spinner_item) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_spinner_itemname,obj);
                Log.e("abc",obj);
            }
        };
        spManufacture.setAdapter(myAdapter);
        spManufacture.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(oneSelected){
                    TextView txt_name = (TextView) view.findViewById(R.id.tv_spinner_itemname);
                    manufactureSp = txt_name.getText().toString();
                }else oneSelected = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //数量
        myAdapter = new IndexAdapter<String>(number,R.layout.spinner_item) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_spinner_itemname,obj);
                Log.e("abc",obj);
            }
        };
        spNumber.setAdapter(myAdapter);
        spNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(oneSelected){
                    TextView txt_name = (TextView) view.findViewById(R.id.tv_spinner_itemname);
                    numberSp = txt_name.getText().toString();
                }else oneSelected = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //领料点
        myAdapter = new IndexAdapter<String>(pickPoint,R.layout.spinner_item) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_spinner_itemname,obj);
                Log.e("abc",obj);
            }
        };
        spPickPoint.setAdapter(myAdapter);
        spPickPoint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(oneSelected){
                    TextView txt_name = (TextView) view.findViewById(R.id.tv_spinner_itemname);
                    pickPointSp = txt_name.getText().toString();
                }else oneSelected = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //spMaterial.setOnItemClickListener(this);
        //给按钮设置点击事件，返回首页
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GZDownholeEquipmentActivity.this.finish();
            }
        });
        //找到提交和重置按钮
        submitButton = findViewById(R.id.bt_gz_qc_submit);
        resetButton = findViewById(R.id.bt_gz_qc_reset);
        changeSwitch = findViewById(R.id.swh_status);
        changeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    isChanged = 1;
                    etUnitPrice.setText("10.86");
                    etSumPrice.setText(String.valueOf(Float.parseFloat(etUnitPrice.getText().toString()) * Integer.parseInt(numberSp)));
                }
                else{
                    isChanged = 0;
                }
            }
        });
        //找到井号
        etWellName = findViewById(R.id.et_gz_qc_wellname);
        etUnitPrice = findViewById(R.id.et_gz_qc_unitprice);
        etSumPrice = findViewById(R.id.et_gz_qc_sumprice);
        ivGzQcWellName = findViewById(R.id.iv_gz_qc_del_wellname);
        EditTextUtils.addEditListener(etWellName,ivGzQcWellName);
    }
    //获取井下器材实体类
    private GZDownholeEquipmentBeans GetDownholeEquip(){
        GZDownholeEquipmentBeans gzeb = new GZDownholeEquipmentBeans();
        gzeb.setId(IdUtils.createIdbyUUID());
        gzeb.setDataInputId(IdUtils.createIdByDate());
        gzeb.setMateria(materialSp);
        gzeb.setSource(sourceSp);
        gzeb.setType(typeSp);
        gzeb.setSpec(specSp);
        gzeb.setWellName(etWellName.getText().toString());
        gzeb.setModelName(modelNameSp);
        gzeb.setManuFacture(manufactureSp);
        gzeb.setNumber(Integer.parseInt(numberSp));
        gzeb.setIsChanged(isChanged);
        gzeb.setUnitPrice(Float.parseFloat(etUnitPrice.getText().toString()));
        gzeb.setPickPoint(pickPointSp);
        gzeb.setInputDate(new Date(System.currentTimeMillis()));
        return gzeb;

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
    public int InsertDownholeEquip(final GZDownholeEquipmentBeans gzeb)throws InterruptedException{
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String sql = "insert into PC_JX_DH_EQUIP_G_D_DATA(PRIMARY_ID,RPT_DATA_ID,RPT_DATE,WELL_NAME,MATERIAL_NAME,SOURCE,TYPE_NAME,SPEC_NAME,MODEL_NAME,FANUFACTURER_NAME,NUM,IS_WHOLE_WELL_REPLACE,PICK_POINT_NAME,UNIT_PRICE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                DBUtils db = new DBUtils();
                PreparedStatement ps = null;
                try {
                    ps = db.GetConDatabase(sql);
                    ps.setString(1, gzeb.getId());
                    ps.setString(2, gzeb.getDataInputId());
                    ps.setDate(3, gzeb.getInputDate());
                    ps.setString(4, gzeb.getWellName());
                    ps.setString(5, gzeb.getMateria());
                    ps.setString(6, gzeb.getSource());
                    ps.setString(7, gzeb.getType());
                    ps.setString(8, gzeb.getSpec());
                    ps.setString(9, gzeb.getModelName());
                    ps.setString(10, gzeb.getManuFacture());
                    ps.setInt(11,gzeb.getNumber());
                    ps.setInt(12,gzeb.getIsChanged());
                    ps.setString(13,gzeb.getPickPoint());
                    ps.setFloat(14,gzeb.getUnitPrice());
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
