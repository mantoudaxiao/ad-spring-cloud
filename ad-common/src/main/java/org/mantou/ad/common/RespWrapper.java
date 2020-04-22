package org.mantou.ad.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.mantou.ad.enums.ResultEnum;

@Data
@NoArgsConstructor
public class RespWrapper {

    private Object data;
    private String msg;
    private Integer status;

    public RespWrapper(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public RespWrapper(Integer status, Object data) {
        this.status = status;
        this.data = data;
    }

    public RespWrapper(ResultEnum resultEnum) {
        this.status = resultEnum.getStatus();
        this.msg = resultEnum.getMsg();
    }

    public RespWrapper(ResultEnum resultEnum, Object data) {
        this.status = resultEnum.getStatus();
        this.msg = resultEnum.getMsg();
        this.data = data;
    }

    public static RespWrapper error() {
        return new RespWrapper(500, "失败");
    }

    public static RespWrapper error(String msg) {
        return new RespWrapper(500, msg);
    }

    public static RespWrapper error(Object data) {
        return new RespWrapper(500, data);
    }

    public static RespWrapper error(ResultEnum resultEnum) {
        return new RespWrapper(resultEnum);
    }

    public static RespWrapper error(ResultEnum resultEnum, Object data) {
        return new RespWrapper(resultEnum, data);
    }

    public static RespWrapper ok() {
        return new RespWrapper(200, "成功");
    }

    public static RespWrapper ok(String msg) {
        return new RespWrapper(200, msg);
    }

    public static RespWrapper ok(Object data) {
        return new RespWrapper(200, data);
    }

    public static RespWrapper ok(ResultEnum resultEnum, Object data) {
        return new RespWrapper(resultEnum, data);
    }

}
