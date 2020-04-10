package com.birdsnail.accouting.exception;

/**
 * 自定义的错误码
 *
 * @author 31472
 */
public enum CustomErrorCode {

    /**
     * 认证凭证不正确
     */
    INCORRECT_CREDENTIALS,

    /**
     * 参数不正确
     */
    INVALID_PARAMETER,

    /**
     * 资源没有找到
     */
    RESOURCE_NOT_FOUND,

    /**
     * 没有进行登录
     */
    NO_AUTHORIZE;

}
