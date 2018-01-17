package com.liuxl.netty.common.commstants;

/**
 * Created with IntelliJ IDEA.
 * Description:常量类
 *
 * @author liuxl
 * @date 2018/1/11
 */
public class NettyConstants {
    //app代表具体业务的操作
    public final static String  MESSAGE_SEND ="MESSAGE_SEND";
    //基础操作类型
    public final static String OPERATION_TYPE_ADD = "I";
    public final static String OPERATION_TYPE_UPDATE = "U";
    public final static String OPERATION_TYPE_DEL = "D";
    public final static String OPERATION_TYPE_QUERY="Q";

    public final static Long OPERATE_RESULT_OK_CODE = 1000L;
    public final static Long OPERATE_RESULT_FAIL_CODE = -1000L;
    public final static String OPERATE_RESULT_OK_MESSAGE = "操作成功！";
    public final static String OPERATE_RESULT_FAIL_MESSAGE = "操作失败！";
}
