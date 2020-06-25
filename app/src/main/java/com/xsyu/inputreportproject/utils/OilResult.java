package com.xsyu.inputreportproject.utils;

public class OilResult {

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static OilResult build(Integer status, String msg, Object data) {
        return new OilResult(status, msg, data);
    }

    public static OilResult ok(Object data) {
        return new OilResult(data);
    }

    public static OilResult ok() {
        return new OilResult(null);
    }

    public OilResult() {

    }

    public static OilResult build(Integer status, String msg) {
        return new OilResult(status, msg, null);
    }

    public OilResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public OilResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

