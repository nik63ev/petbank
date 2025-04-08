package ru.bank.petbank.exception;

public class RegisterException extends RuntimeException {
    public RegisterException(String message) {
        super(message);
    }
}
