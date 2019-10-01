package com.zj.stream.enums;

public enum ResultCode {
    SUCCESS("WXXCX000000","成功"),
    FAILURE("WXXCX999999","系统异常"),
    USER_NAME_ISBLANK("WXXCX000001","用户名参数空"),
    CITY_FAIL("WXXCX000002","查询城市失败");

    private String code;
    private String msg;

    ResultCode(String code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public String code(){
        return this.code;
    }

    public String msg(){
        return  this.msg;
    }

}
