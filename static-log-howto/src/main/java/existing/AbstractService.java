package existing;


public  abstract class AbstractService {

    //在生产环境运行没有问题;但在单元测试时,由于一些系统配置尚不存在
    //所以可能报错.
    protected static MyLog log = LogFactory.getLog(MyLog.class);


    //....

}



