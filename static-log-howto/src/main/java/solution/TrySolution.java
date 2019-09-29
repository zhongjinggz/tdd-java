package solution;

import environment.Environment;

public class TrySolution {
    public static void main(String[] Args) {

        //把当前环境指定为非测试环境
        Environment.isTest=false;

        new Service1().doSomething();
        new Service2().doSomething();
    }
}
