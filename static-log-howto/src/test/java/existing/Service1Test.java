package existing;

import environment.Environment;
import org.junit.jupiter.api.Test;

class Service1Test {
    @Test
    void testDoSomething() {
        //模拟测试环境
        Environment.isTest = true;

        Service1 service = new Service1();

        service.doSomething();

        //...
    }

}