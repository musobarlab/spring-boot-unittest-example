package com.wuriyanto.example.application;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wuriyanto.example.application.entity.User;
import com.wuriyanto.example.application.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        User farid = new User("Farid", "farid@gmail.com", new Date(), new Date());
        User alex = new User("Alex", "alex@gmail.com", new Date(), new Date());

        userRepository.save(farid);
        userRepository.save(alex);

    }

    @AfterEach
	public void cleanup() {
		userRepository.deleteAll();
	}

    @Test
    public void saveNewDataShouldIncreasedTheData() {
        long expected = 3;
        User bony = new User("Bony", "bony@gmail.com", new Date(), new Date());

        userRepository.save(bony);
        Assertions.assertEquals(expected, userRepository.count());
    }

    @Test
    public void findUserByEmailShouldReturnTheDataWhenEmailIsAvailable() {
        String expected = "Alex";

        User findResult = userRepository.findByEmail("alex@gmail.com");
        Assertions.assertEquals(expected, findResult.getFullName());
    }

    @Test
    public void findUserByEmailShouldReturnNullWhenEmailIsUnavailable() {
        User findResult = userRepository.findByEmail("vivi@gmail.com");
        Assertions.assertNull(findResult, "user is null");
    }
}
