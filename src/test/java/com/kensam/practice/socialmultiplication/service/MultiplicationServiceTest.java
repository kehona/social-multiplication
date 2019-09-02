package com.kensam.practice.socialmultiplication.service;

import com.kensam.practice.socialmultiplication.domain.Multiplication;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.mockito.BDDMockito.given;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiplicationServiceTest {

    @MockBean
    RandomGeneratorService randomGeneratorService;

    @Autowired
    MultiplicationService multiplicationService;

    @Test
    public void createRandomMultiplicationTest() {

        // given (our mocked Random Generator Service will return first 50, then 30
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);
        // when
        Multiplication  multiplication = multiplicationService.createRandomMultiplication();
        // then
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
        // assertThat(multiplication.getResult()).isEqualTo(1500);
    }
}
