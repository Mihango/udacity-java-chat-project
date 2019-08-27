package com.example.demo.controller;

import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest // (controllers = UserController.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JacksonTester<CreateUserRequest> createUserTester;

    @Test
    public void create_user_success() throws Exception {
        mockMvc.perform(
                post(new URI("/api/user/create"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(createUserTester.write(getCreateModel()).getJson()))
                .andExpect(content().json("{'id':1, 'username': Sareeta}"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_mismatch_password_or_wrong_size_password() throws Exception {
        mockMvc.perform(
                post(new URI("/api/user/create"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(createUserTester.write(getCreateMismatchModel()).getJson()))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'message':'Password is not correct'}"));
    }

    private CreateUserRequest getCreateModel() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("Sareeta");
        request.setPassword("123@pass");
        request.setConfirmPassword("123@pass");

        return request;
    }

    private CreateUserRequest getCreateMismatchModel() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("Sareeta");
        request.setPassword("123@pa");
        request.setConfirmPassword("123@pass");

        return request;
    }
}
