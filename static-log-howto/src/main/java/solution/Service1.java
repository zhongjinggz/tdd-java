package solution;

public class Service1 extends AbstractService {

    //重构: 用于非单元测试
    public Service1() {
        super();
    }

    //重构: 下面的构造器仅用于单元测试
    public Service1(MyLog theLog) {
        super(theLog);
    }

    public void doSomething() {
        log.info("Service1 is running (solution).");
    }
}
