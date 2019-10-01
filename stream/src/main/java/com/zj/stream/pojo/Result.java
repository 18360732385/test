package com.zj.stream.pojo;

import com.zj.stream.enums.ResultCode;
import org.springframework.stereotype.Component;
import java.io.Serializable;

/**
 *返回体
 */
@Component
public class Result implements Serializable {

    private String code; //响应业务状态
    private String msg; //响应消息
    private Object data = null; //响应中的数据


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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode){
        this.code = resultCode.code();
        this.msg = resultCode.msg();
    }

    Result(ResultCode resultCode, Object data){
        this.code = resultCode.code();
        this.msg = resultCode.msg();
        this.data = data;
    }

    Result(ResultCode resultCode){
        this.code = resultCode.code();
        this.msg = resultCode.msg();
    }

    public Result(){}

    //成功(无data)
    public static Result success(){
        Result result = new Result(ResultCode.SUCCESS);
        return result;
    }

    //成功
    public static Result success(Object data){
        Result result = new Result(ResultCode.SUCCESS,data);
        return result;
    }

    //失败
    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result(resultCode,data);
        return result;
    }

    //失败（无data）
    public static Result failure(ResultCode resultCode) {
        Result result = new Result(resultCode);
        return result;
    }


    //检查是否成功
    public Boolean isOK() {
        return this.code.equals("000000");
    }


    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


}
