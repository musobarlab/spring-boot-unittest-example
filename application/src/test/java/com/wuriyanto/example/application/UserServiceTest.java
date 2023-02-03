package com.wuriyanto.example.application;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.wuriyanto.example.application.entity.RegisterRequest;
import com.wuriyanto.example.application.entity.User;
import com.wuriyanto.example.application.repository.UserRepository;
import com.wuriyanto.example.application.service.IUserService;
import com.wuriyanto.example.application.service.UserService;

@SpringBootTest
public class UserServiceTest {
    
    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Bean
        public IUserService userServiceTest() {
            return new UserService();
        }
    }

    @Autowired
    private IUserService userServiceTest;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        User farid = new User("Farid", "farid@gmail.com", new Date(), new Date());
        Mockito.when(userRepository.findByEmail(farid.getEmail())).thenReturn(farid);

        User alex = new User("Alex", "alex@gmail.com", new Date(), new Date());
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(alex);
    }

    @Test
    public void registerUserShouldReturnExpectedUser() throws Exception {
        String expected = "Alex";

        RegisterRequest registerData = new RegisterRequest("Alex", "alex@gmail.com");
        
        User actualUser = userServiceTest.register(registerData);

        Assertions.assertEquals(expected, actualUser.getFullName());
    }

    @Test
    public void getUserShouldReturnExpectedUser() throws Exception {
        String expected = "Farid";

        User actualUser = userServiceTest.getUser("farid@gmail.com");

        Assertions.assertEquals(expected, actualUser.getFullName());
    }
    
}
