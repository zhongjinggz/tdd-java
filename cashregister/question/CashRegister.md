# 收银机问题

## 问题

* 收银机（CashRegister）连着一台打印机（Printer）
* 收银机处理（Process）一次购买行为时（Purchase），使用打印机打印购买信息（Description) 
* Purchase类已经开发完成

1. 假设真实打印机的API还不清楚, 请在不调用实际的打印机对象的情况下，完成CashRegister#process的开发。
2. 考虑process调用CashRegister中的calculateDiscount方法计算折扣，并一起打印出来。请在不实际调用calculateDiscount方法的情况下，完成process开发。


## 编程提示

* 创建CashRegister和Printer类
* Printer通过构造器注入到CashRegister中
* 开发CashRegister#process方法

## 学习目的：

* 掌握Mock对象的原理
* 理解Mock和Stub对象的区别
* 学会Mockito的基本使用方法
* 理解Mock对象的使用场合
* 掌握通过继承和Spy两种方法实现部分Mock
* 掌握依赖注入对于Mock的意义，对于遗留代码没有依赖注入的处理方法（继承或Spy）

## 教学参考

* 引导学员考虑要测什么
* 引导学员以手工的方式实现Mock和Stub对象
* 然后再引入Mockito