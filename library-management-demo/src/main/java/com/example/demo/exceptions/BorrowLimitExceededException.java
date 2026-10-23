package com.example.demo.exceptions;

public class BorrowLimitExceededException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	public BorrowLimitExceededException(String message) {
        super(message);
    }
}
