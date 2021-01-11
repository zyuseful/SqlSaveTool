package com.zzmxtd.sqltool.common;

/**
 * controller返回类
 *
 * @author zy
 * @date 2021/01/06
 */
/**
 *
 * @author zy
 * @date 2020/10/09
 */
public class Res{

    //请求成功
    public static final int SUCCESS = 1;

    //请求失败
    public static final int FAILURE = 0;

    public static final int KICKOUT = -1;

    //请求状态码
    private int respCode;

    //请求失败信息
    private String respMsg;

    //请求成功返回
    private Object respBody;

    public Res() {}

    public static Res failure(){
        Res res = new Res();
        res.setRespCode(FAILURE);
        res.setRespMsg("");
        return res;
    }

    public static Res failure(String respMsg){
        Res res = failure();
        res.setRespMsg(respMsg);
        return res;
    }

    public static Res kickOut(String respMsg){
        Res res = new Res();
        res.setRespCode(KICKOUT);
        res.setRespMsg(respMsg);
        return res;
    }

    public static Res success(){
        Res res = new Res();
        res.setRespCode(SUCCESS);
        return res;
    }

    public static Res success(Object respBody){
        Res res = success();
        res.setRespBody(respBody);
        return res;
    }


    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public Object getRespBody() {
        return respBody;
    }

    public void setRespBody(Object respBody) {
        this.respBody = respBody;
    }

    @Override
    public String toString() {
        return "Res [respCode=" + respCode + ", respMsg=" + respMsg + ", respBody=" + respBody + "]";
    }
}
