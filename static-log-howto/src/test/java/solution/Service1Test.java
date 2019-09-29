package solution;

import environment.Environment;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class Service1Test {
    @Test
    void testDoSomething() {
        //模拟测试环境
        Environment.isTest = true;

        MyLog mockLog = mock(MyLog.class);

        Service1 service = new Service1(mockLog);

        service.doSomething();

        verify(mockLog).info("Service1 is running (solution).");

        //...
    }

}