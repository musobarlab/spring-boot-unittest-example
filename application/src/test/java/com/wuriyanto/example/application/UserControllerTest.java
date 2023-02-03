package com.wuriyanto.example.application;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.wuriyanto.example.application.entity.RegisterRequest;
import com.wuriyanto.example.application.entity.User;
import com.wuriyanto.example.application.repository.UserRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
// @AutoConfigureTestDatabase
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

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
    public void registerShouldReturnNewUserEqualToDemo() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("demo", "demo@yahoo.com");
        mvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(registerRequest)));

        String expected = "demo";

        User actualUser = userRepository.findByEmail("demo@yahoo.com");

        Assertions.assertEquals(expected, actualUser.getFullName());
    }

    @Test
    public void getUserShouldReturn200() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/users")
                        .param("email", "alex@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
