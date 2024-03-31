package com.T1school.task3;

import com.T1school.task3.entity.Order;
import com.T1school.task3.entity.User;
import com.T1school.task3.service.OrderService;
import com.T1school.task3.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(OutputCaptureExtension.class)
@SpringBootTest
class OrderTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Test
    void createOrderTest(CapturedOutput output){
        User user = userService.createUser("ayaz", "ayaz@gmail.com");
        Order order = orderService.createOrder("test", "delivered",1L);
        /*
        assertTrue(output.getOut().contains("Before: createUser| using arguments: [ayaz, ayaz@gmail.com]"));
        assertTrue(output.getOut().contains("AfterReturning in: createUser| result:"));
        assertTrue(output.getOut().contains("After: createUser"));
        assertTrue(output.getOut().contains("Before: createOrder| using arguments: [test, delivered, 1]"));
        assertTrue(output.getOut().contains(" Before: getUser| using arguments: [1]"));
        assertTrue(output.getOut().contains("AfterReturning in: getUser| result:"));
        assertTrue(output.getOut().contains("After: getUser"));
        assertTrue(output.getOut().contains("AfterReturning in: createOrder| result: Order(id=1, description=test, status=delivered"));
        assertTrue(output.getOut().contains("After: createOrder"));
        */
    }

}