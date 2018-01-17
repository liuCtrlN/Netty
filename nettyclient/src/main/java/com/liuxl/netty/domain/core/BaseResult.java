package com.liuxl.netty.domain.core;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:返回内容接口
 *
 * @author liuxl
 * @date 2018 /1/11
 */
public interface BaseResult extends Serializable {

    /**
     * Is success boolean.
     *
     * @return the boolean
     */
    boolean isSuccess();

    /**
     * Sets success.
     *
     * @param success the success
     */
    void setSuccess(boolean success);

    /**
     * Gets err code.
     *
     * @return the err code
     */
    String getErrCode();

    /**
     * Sets err code.
     *
     * @param errCode the err code
     */
    void setErrCode(String errCode);

    /**
     * Gets err msg.
     *
     * @return the err msg
     */
    String getErrMsg();
}
