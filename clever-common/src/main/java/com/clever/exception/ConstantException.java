package com.clever.exception;

import com.clever.bean.model.Result;

/**
 * @Author xixi
 * @Date 2023-12-15 09:39
 **/
public class ConstantException {
    public final Integer code;
    public String msg;

    public ConstantException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ConstantException format(Object... params) {
        this.msg = String.format(this.msg, params);
        return this;
    }

    public Result<String> getResult() {
        return Result.ofFail(code, msg);
    }

    public Result<String> getResult(String... params) {
        String message = msg;
        for (String p : params) {
            message = message.replace("{}", p);
        }
        return Result.ofFail(code, message);
    }


    public static ConstantException USER_NO_ONLINE = new ConstantException(1001, "用户未登录");
    public static ConstantException USER_PASSWORD_ERROR_SO_MANY = new ConstantException(1002, "密码错误次数过多,30分钟后再试");
    public static ConstantException USER_ACCOUNT_NOT_FOUND = new ConstantException(1003, "账号不存在");
    public static ConstantException USER_LOGIN_PASSWORD_ERROR = new ConstantException(1004, "账号或密码不正确");
    public static ConstantException PARAMETER_VERIFICATION_FAIL = new ConstantException(1005, "参数校验失败");
    public static ConstantException DATA_IS_EXIST = new ConstantException(1005, "%s已存在");
}
