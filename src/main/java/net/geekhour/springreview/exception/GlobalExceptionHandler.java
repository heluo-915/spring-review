package net.geekhour.springreview.exception;

import net.geekhour.springreview.common.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<String>> handleException(Exception e){
        return ResponseEntity.status(500).body(Result.error(500,"服务器内部错误"));
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Result<String>> handleBusinessException(BusinessException e){
        return ResponseEntity.status(404).body(Result.error(404,e.getMessage()));
    }
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<Result<Object>> handleInvalidParameterException(InvalidParameterException e){
        return ResponseEntity.status(400).body(Result.error(400,e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result<String>> handleValidationException(MethodArgumentNotValidException e){
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();

        for (FieldError error : errors) {
            sb.append(error.getDefaultMessage());
            sb.append("；");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        String message = sb.toString();
        return ResponseEntity.status(400).body(Result.error(400,message));
    }
}
