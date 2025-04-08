package ru.bank.petbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.bank.petbank.controller.CommonResponse;
import ru.bank.petbank.controller.RegisterResponse;
import ru.bank.petbank.controller.Status;

@ControllerAdvice()
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
    @ExceptionHandler(RegisterException.class)
    public ResponseEntity<RegisterResponse> handleRegisterException(RegisterException exception) {
        RegisterResponse response = new RegisterResponse();
        response.setStatus(new Status());
        response.getStatus().setMessage(exception.getMessage());
        response.getStatus().setCode(1);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse> handleRegisterException(MethodArgumentNotValidException exception) {
        CommonResponse response = new CommonResponse();
        response.setStatus(new Status());
        response.getStatus().setMessage(exception.getMessage());
        response.getStatus().setCode(1);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
