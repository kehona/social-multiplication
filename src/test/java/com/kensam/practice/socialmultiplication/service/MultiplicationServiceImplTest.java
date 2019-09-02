package com.kensam.practice.socialmultiplication.service;

import com.kensam.practice.socialmultiplication.domain.Multiplication;
import com.kensam.practice.socialmultiplication.domain.MultiplicationResultAttempt;
import com.kensam.practice.socialmultiplication.domain.User;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MultiplicationServiceImplTest {

    @Mock
    RandomGeneratorService randomGeneratorService;

    MultiplicationServiceImpl multiplicationService;


    @Before
    public void setUp() {

        // With this call to initMocks we tell Mockito to process the annotations
        MockitoAnnotations.initMocks(this);
        multiplicationService = new MultiplicationServiceImpl(randomGeneratorService);
    }
    @Test
    public void createRandomMultiplicationTest() {
        // given two random numbers;
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);
        // when I call multiplicationService
        Multiplication multiplication = multiplicationService.createRandomMultiplication();
        // then
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
       
    }
    
    @Test
    public void checkCorrectAttemptTest() {
    	
    	//given
    	Multiplication multiplication = new Multiplication(50, 60);
    	User user = new User("john_doe");
    	MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000);
    	
    	// when
    	boolean attemptResult = multiplicationService.checkAttempt(attempt);

    	// assert
        assertThat(attemptResult).isTrue();
    }

    @Test
    public void chheckWrongAttemptTest() {

        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("john_doe");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010);
        // when
        boolean attemptResult = multiplicationService.checkAttempt(attempt);
        // assert
        assertThat(attemptResult).isFalse();
    }
}
