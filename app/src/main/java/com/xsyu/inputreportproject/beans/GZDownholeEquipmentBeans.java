package com.xsyu.inputreportproject.beans;

import java.sql.Date;

/**
 * 井下器材发放实体类
 */
public class GZDownholeEquipmentBeans {
    private String id;  //主键ID
    private String dataInputId; //数据填报ID
    private String wellName;    //井号
    private String materia;     //材料
    private String source;      //来源
    private String type;        //类型
    private String spec;        //规格
    private String modelName;   //型号
    private String manuFacture; //厂家
    private int number;         //数量
    private int isChanged;//是否更换
    private String pickPoint;//领料点
    private Date inputDate;//日期
    private float unitPrice;//单价

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataInputId() {
        return dataInputId;
    }

    public void setDataInputId(String dataInputId) {
        this.dataInputId = dataInputId;
    }

    public String getWellName() {
        return wellName;
    }

    public void setWellName(String wellName) {
        this.wellName = wellName;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getManuFacture() {
        return manuFacture;
    }

    public void setManuFacture(String manuFacture) {
        this.manuFacture = manuFacture;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getIsChanged() {
        return isChanged;
    }

    public void setIsChanged(int isChanged) {
        this.isChanged = isChanged;
    }

    public String getPickPoint() {
        return pickPoint;
    }

    public void setPickPoint(String pickPoint) {
        this.pickPoint = pickPoint;
    }
    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }
}
