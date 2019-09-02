package com.kensam.practice.socialmultiplication.service;

import com.kensam.practice.socialmultiplication.domain.Multiplication;
import com.kensam.practice.socialmultiplication.domain.MultiplicationResultAttempt;

public interface MultiplicationService {

    /**
     * Creates a Multiplication object with two randomly generated factors between 11 and 99
     * @return a Multiplication object with random factorsR
     */
    Multiplication createRandomMultiplication();

    /**
     * @return true if the attempt matches the result of the
     *         multiplication, false otherwise.
     */
    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);
}
