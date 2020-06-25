package com.xsyu.inputreportproject.beans;

public class Icon {
    private int Icon_Id;
    private String Icon_Name;

    public Icon(int icon_Id, String icon_Name) {
        Icon_Id = icon_Id;
        Icon_Name = icon_Name;
    }

    public int getIcon_Id() {
        return Icon_Id;
    }

    public void setIcon_Id(int icon_Id) {
        Icon_Id = icon_Id;
    }

    public String getIcon_Name() {
        return Icon_Name;
    }

    public void setIcon_Name(String icon_Name) {
        Icon_Name = icon_Name;
    }
}
