package org.example.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.result.ResultCodeEnum;

/**
 * 自定义异常
 *
 * @author Administrator
 * @date 2023/03/17
 *         <p>
 *         Data 注入参数
 *         AllArgsConstructor 有参构造
 *         NoArgsConstructor 无参构造
 */
@Data
public class KeyRepeatException extends RuntimeException {

    /**
     * 状态 代码
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;

    public KeyRepeatException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 接收枚举类型对象
     *
     * @param resultCodeEnum
     */
    public KeyRepeatException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "KeyRepeatException{" + "code=" + code + ", msg='" + msg + '\'' + '}';
    }

}
