import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * Description:日志测试
 *
 * @author liuxl
 * @date 2018/1/17
 */
public class Log4j2Test {

    /**
     * @param args
     */
    //指定日志的配置器文件
//    static {
//        System.setProperty("log4j.configurationFile", "log4j2.xml");
//    }

    /**
     * @param args
     */
    //根据类的全名(com.undergrowth.Log4j2_Test)获取Logger
    //因为com.undergrowth.Log4j2_Test中没有Logger的实现类
    //所以logger继承rootLogger
    public static Logger logger = LogManager.getLogger(Log4j2Test.class);

    public static Logger logger_logs = LogManager.getLogger("LOGS_TEST");

    //输出日志的六个级别的信息
    public static void outLogger() {
        logger.trace("trace to console");
        logger.debug("debug to console");
        logger.info("info to console");
        logger.warn("warn to console");
        logger.error("error to console");
        logger.fatal("fatal to console");

        logger_logs.trace("trace to console");
        logger_logs.debug("debug to console");
        logger_logs.info("info to console");
        logger_logs.warn("warn to console");
        logger_logs.error("error to console");
        logger_logs.fatal("fatal to console");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //因为logger继承rootLogger 这里又没有配置文件设定rootLogger 所以采用默认的配置方式
        //即rootLogger的日志级别(level)为error 输出源(appender)为控制台
        outLogger();
    }

}
