package solution;

import environment.Environment;

public class LogFactory {

    public static MyLog getLog(Class<MyLog> logClass) {
        //模拟在单元测试时报错的情况
        if(Environment.isTest) {
            throw new RuntimeException("单元测试时会报错!");
        }
        return new MyLog();
    }
}
