package com.netmed.usermodule;

import org.javers.core.selftest.Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserModuleApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void main() {
        Application.main(new String[]{
        });
    }
}
