package com.xsyu.inputreportproject.beans;

/**
 * 压裂措施基本信息实体类
 */
public class CrushingBaseInfoBeans {
    private String wellId;      //井Id
    private String wellName;    //井号
    private String measureTeam; //措施队伍
    private String crushingTeam; //压裂队伍
    private String zdTechServTeam;  //暂堵剂技术服务队伍
    private String ylTechServTeam;  //压裂液技术服务队伍
    private String crushingMethod;    //压裂方式
    private String zcjType;         //支撑剂类型
    private String horizon;         //层位
    private String ylySetup;        //压裂液体系

    public String getWellId() {
        return wellId;
    }

    public void setWellId(String wellId) {
        this.wellId = wellId;
    }

    public String getWellName() {
        return wellName;
    }

    public void setWellName(String wellName) {
        this.wellName = wellName;
    }

    public String getMeasureTeam() {
        return measureTeam;
    }

    public void setMeasureTeam(String measureTeam) {
        this.measureTeam = measureTeam;
    }

    public String getCrushingTeam() {
        return crushingTeam;
    }

    public void setCrushingTeam(String crushingTeam) {
        this.crushingTeam = crushingTeam;
    }

    public String getZdTechServTeam() {
        return zdTechServTeam;
    }

    public void setZdTechServTeam(String zdTechServTeam) {
        this.zdTechServTeam = zdTechServTeam;
    }

    public String getYlTechServTeam() {
        return ylTechServTeam;
    }

    public void setYlTechServTeam(String ylTechServTeam) {
        this.ylTechServTeam = ylTechServTeam;
    }

    public String getCrushingMethod() {
        return crushingMethod;
    }

    public void setCrushingMethod(String crushingMethod) {
        this.crushingMethod = crushingMethod;
    }

    public String getZcjType() {
        return zcjType;
    }

    public void setZcjType(String zcjType) {
        this.zcjType = zcjType;
    }

    public String getHorizon() {
        return horizon;
    }

    public void setHorizon(String horizon) {
        this.horizon = horizon;
    }

    public String getYlySetup() {
        return ylySetup;
    }

    public void setYlySetup(String ylySetup) {
        this.ylySetup = ylySetup;
    }
}
