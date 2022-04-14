package com.alwertus.appstore.controller;

import com.alwertus.appstore.view.request.LoginRq;
import com.alwertus.appstore.view.response.ResponseError;
import com.alwertus.appstore.view.response.ResponseOk;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiTest {
    @LocalServerPort
    private int port;

    private String URL;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Api api;

    @BeforeEach
    private void init() {
        URL =  "http://localhost:" + port + "/api/auth";
    }

    @Test
    void loginWrong() throws Exception {
        LoginRq rq = new LoginRq();
        rq.setLogin("gege");
        rq.setPassword("zeze");

        String rsString = this.restTemplate.postForObject(URL, rq, String.class);

        ObjectMapper mapper = new ObjectMapper();
        ResponseError rsError = mapper.readValue(rsString, ResponseError.class);
        assertThat(rsError.getError()).isEqualTo("Wrong login or password");
        assertThat(rsError.getResult()).isEqualTo("Error");
    }

    @Test
    void loginSuccess() throws Exception {
        LoginRq rq = new LoginRq();
        rq.setLogin("user");
        rq.setPassword("password");

        String rsString = this.restTemplate.postForObject(URL, rq, String.class);

        ObjectMapper mapper = new ObjectMapper();
        ResponseOk rsError = mapper.readValue(rsString, ResponseOk.class);
        assertThat(rsError.getResult()).isEqualTo("Ok");
    }

    @Test
    void errorHandler() {
        String message = "Some error";
        ResponseError rs = (ResponseError) api.errorHandler(new Exception(message));
        assertThat(rs.getError()).isEqualTo(message);
    }
}