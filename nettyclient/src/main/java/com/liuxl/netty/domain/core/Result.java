package com.liuxl.netty.domain.core;

/**
 * Created with IntelliJ IDEA.
 * Description:定义返回内容
 *
 * @author liuxl
 * @date 2018/1/11
 */
public class Result implements BaseResult {
    public boolean isSuccess() {
        return false;
    }

    public void setSuccess(boolean success) {

    }

    public String getErrCode() {
        return null;
    }

    public void setErrCode(String errCode) {

    }

    public String getErrMsg() {
        return null;
    }
}
