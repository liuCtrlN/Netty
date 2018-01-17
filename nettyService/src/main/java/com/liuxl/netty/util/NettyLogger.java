package com.liuxl.netty.util;

import com.liuxl.netty.domain.core.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created with IntelliJ IDEA.
 * Description:定义日志输出Logger
 *
 * @author liuxl
 * @date 2018/1/11
 */
public class NettyLogger {

    public static Logger getLogger(String name) {
        return LogManager.getLogger(name);
    }

    public static void record(String Name, String logMsg, Result result) {
        if (result.isSuccess()) {
            getLogger(Name).warn(logMsg);
        } else {
            getLogger(Name).error(logMsg);
        }

    }
}
