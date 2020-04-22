package org.mantou.ad.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(200, "成功"),
    /**
     * 正确
     **/
    SUCCESS_STATUS(200),

    /**
     * 参数错误
     **/
    PARAM_ERROR_CODE(400),

    /**
     * 限制调用
     **/
    LIMIT_ERROR_CODE(401),

    /**
     * token 过期
     **/
    TOKEN_TIMEOUT_CODE(402),

    /**
     * 禁止访问
     **/
    NO_AUTH_CODE(403),

    /**
     * 资源没找到
     **/
    NOT_FOUND(404),

    /**
     * 服务器错误
     **/
    SERVER_ERROR_CODE(500),

    /**
     * 服务降级中
     **/
    DOWNGRADE(406),

    /**
     * 禁止访问
     */
    NO_AUTH(403,"禁止访问！");

    private String msg;

    private Integer status;

    private ResultEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ResultEnum(Integer status) {
        this.status = status;
    }

}
