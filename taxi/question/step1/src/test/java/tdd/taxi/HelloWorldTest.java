package tdd.taxi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloWorldTest {
    @Test
    void should_say_hello() {
        HelloWorld hello = new HelloWorld();
        assertEquals("Hello world!", hello.greeting());

    }

}