package solution;


public abstract class AbstractService {

    //重构:通过构造器来初始化,并设法保证log的全局唯一性
    //protected static MyLog log = LogFactory.getLog(MyLog.class);
    protected static MyLog log;

    //注意这里是如何保证全局唯一性的(使用synchronized, 并判断两次null)
    public AbstractService() {
        if (log == null) {
            synchronized (AbstractService.class) {
                if (log == null) {
                    log = LogFactory.getLog(MyLog.class);
                }
            }
        }
    }

    //重构:下面的构造器用于单元测试
    public AbstractService(MyLog myLog) {
        log = myLog;
    }


    //....

}



