package org.example.result;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "成功"),
    FAIL(201, "失败"),
    SERVICE_ERROR(201, "服务异常"),
    DATA_ERROR(201, "数据异常"),
    LOGIN_AUTH(201, "未登陆"),
    KEY_REPEAT(201, "数据重复"),
    PERMISSION(401, "没有权限");

    private Integer code;

    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
