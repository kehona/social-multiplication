package com.kensam.practice.socialmultiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kensam.practice.socialmultiplication.domain.Multiplication;
import com.kensam.practice.socialmultiplication.domain.MultiplicationResultAttempt;
import com.kensam.practice.socialmultiplication.domain.User;
import com.kensam.practice.socialmultiplication.service.MultiplicationService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class MultiplicationResultAttemptControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;
    @Autowired
    private MockMvc mvc;
    // This object will be magically initialized by the initFields method below.
    private JacksonTester<MultiplicationResultAttempt> jsonResult;
    private JacksonTester<ResultResponse> jsonResponse;
    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }
    @Test
    public void postResultReturnCorrect() throws Exception {
        genericParameterizedTest(true);
    }
    @Test
    public void postResultReturnNotCorrect() throws Exception {
        genericParameterizedTest(false);
    }
    void genericParameterizedTest(final boolean correct) throws Exception {
        // given (remember we're not testing here the service itself)
        given(multiplicationService
                .checkAttempt(any(MultiplicationResultAttempt.class)))
                .willReturn(correct);
        User user = new User("john");
        Multiplication multiplication = new Multiplication(50, 70);
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(
                user, multiplication, 3500);
        // when
        MockHttpServletResponse response = mvc.perform(
                post("/results").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonResult.write(attempt).getJson()))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonResponse.write(new ResultResponse(correct)).getJson());
    }

}
