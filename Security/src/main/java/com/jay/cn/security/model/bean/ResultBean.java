package com.jay.cn.security.model.bean;


import java.util.List;

public class ResultBean {

    public static final String SUCCESS_CODE="0000";
    public static final String FAIL_CODE="9999";

    private String code;
    private String msg;
    private Object result;
    private List<Object> resultList;

    public ResultBean() {
    }

    public ResultBean(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public List<Object> getResultList() {
        return resultList;
    }

    public void setResultList(List<Object> resultList) {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                ", resultList=" + resultList +
                '}';
    }
}
