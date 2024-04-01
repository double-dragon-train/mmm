package com.spring.mmm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("dev")
@SpringBootTest
class MmmApplicationTests {

    @Test
    void contextLoads() {
    }

}
