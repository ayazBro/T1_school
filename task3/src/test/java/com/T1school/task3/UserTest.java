package com.T1school.task3;

import com.T1school.task3.entity.User;
import com.T1school.task3.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(OutputCaptureExtension.class)
@SpringBootTest
class UserTest {
    @Autowired
    private UserService userService;

    @Test
    void createUserTest(CapturedOutput output) {
        User user = userService.createUser("ayaz", "ayaz@gmail.com");
        /*
        assertTrue(output.getOut().contains("Before: createUser| using arguments: [ayaz, ayaz@gmail.com]"));
        assertTrue(output.getOut().contains("AfterReturning in: createUser"));
        assertTrue(output.getOut().contains("After: createUser"));
        */
    }
    @Test
    void notFoundUser(CapturedOutput output) {
        assertThrows(IllegalArgumentException.class, () -> userService.getUser(2L));
        /*
        assertTrue(output.getOut().contains("Before: getUser| using arguments: [2]"));
        assertTrue(output.getOut().contains("AfterThrowing in: getUser| exception details: User not found"));
        assertTrue(output.getOut().contains("After: getUser"));
        */
    }
}