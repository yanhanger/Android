package com.xsyu.inputreportproject.beans;

import java.sql.Date;

/**
 *
 *注水井洗井实体类
 */
public class InjWellWashingBeans {
    private String wellId;  //主键井ID
    private String washingWellName; //洗井井号
    private String washingType;//洗井方式
    private String injMedium;//注入介质
    private String washingTeam;//洗井队伍
    private String matchInj;//配注量
    private String oliPress;//油压
    private String casingPrss;//套压
    private Date comingTime;//入场时间
    private Date washingTime;//洗井时间
    private Date leavingTime;//离开时间
    private String supervior;//现场监督人
    private String washingPhoto;//洗井现场照片URL
    private String washingPush;//洗井排量
    private String press;//压力
    private String waterQulifyPhoto;//水质照片URL

    public String getWellId() {
        return wellId;
    }

    public void setWellId(String wellId) {
        this.wellId = wellId;
    }

    public String getWashingWellName() {
        return washingWellName;
    }

    public void setWashingWellName(String washingWellName) {
        this.washingWellName = washingWellName;
    }

    public String getWashingType() {
        return washingType;
    }

    public void setWashingType(String washingType) {
        this.washingType = washingType;
    }

    public String getInjMedium() {
        return injMedium;
    }

    public void setInjMedium(String injMedium) {
        this.injMedium = injMedium;
    }

    public String getWashingTeam() {
        return washingTeam;
    }

    public void setWashingTeam(String washingTeam) {
        this.washingTeam = washingTeam;
    }

    public String getMatchInj() {
        return matchInj;
    }

    public void setMatchInj(String matchInj) {
        this.matchInj = matchInj;
    }

    public String getOliPress() {
        return oliPress;
    }

    public void setOliPress(String oliPress) {
        this.oliPress = oliPress;
    }

    public String getCasingPrss() {
        return casingPrss;
    }

    public void setCasingPrss(String casingPrss) {
        this.casingPrss = casingPrss;
    }

    public Date getComingTime() {
        return comingTime;
    }

    public void setComingTime(Date comingTime) {
        this.comingTime = comingTime;
    }

    public Date getWashingTime() {
        return washingTime;
    }

    public void setWashingTime(Date washingTime) {
        this.washingTime = washingTime;
    }

    public Date getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(Date leavingTime) {
        this.leavingTime = leavingTime;
    }

    public String getSupervior() {
        return supervior;
    }

    public void setSupervior(String supervior) {
        this.supervior = supervior;
    }

    public String getWashingPhoto() {
        return washingPhoto;
    }

    public void setWashingPhoto(String washingPhoto) {
        this.washingPhoto = washingPhoto;
    }

    public String getWashingPush() {
        return washingPush;
    }

    public void setWashingPush(String washingPush) {
        this.washingPush = washingPush;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getWaterQulifyPhoto() {
        return waterQulifyPhoto;
    }

    public void setWaterQulifyPhoto(String waterQulifyPhoto) {
        this.waterQulifyPhoto = waterQulifyPhoto;
    }
}
