package au.com.auspost.voicesort.web.controller.rest;

import au.com.auspost.voicesort.web.exception.ValidationException;
import lombok.Builder;
import lombok.Data;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static java.util.stream.Collectors.toList;
/*
 * Based on http://www.bbenson.co/post/spring-validations-with-examples/
 */
@ControllerAdvice(annotations = RestController.class)
public class ApiValidationExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrorsView apiErrorsView = buildErrorsView(ex.getBindingResult());
        return new ResponseEntity<>(apiErrorsView, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException ve) {
        ApiErrorsView apiErrorsView = buildErrorsView(ve.getBindingResult());
        return new ResponseEntity<>(apiErrorsView, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ApiErrorsView buildErrorsView(BindingResult bindingResult) {
        List<ApiFieldError> apiFieldErrors = bindingResult
                .getFieldErrors()
                .stream()
                .map(fieldError -> ApiFieldError.builder()
                        .field(fieldError.getField())
                        .code(fieldError.getCode())
                        .rejectedValue(fieldError.getRejectedValue())
                        .defaultMessage(fieldError.getDefaultMessage())
                        .build())
                .collect(toList());

        List<ApiGlobalError> apiGlobalErrors = bindingResult
                .getGlobalErrors()
                .stream()
                .map(globalError -> ApiGlobalError.builder()
                        .code(globalError.getCode())
                        .defaultMessage(globalError.getDefaultMessage())
                        .build())
                .collect(toList());

        return new ApiErrorsView(apiFieldErrors, apiGlobalErrors);
    }
}

@Data @Builder
class ApiErrorsView {
    private List<ApiFieldError> fieldErrors;
    private List<ApiGlobalError> globalErrors;
}

@Data @Builder
class ApiFieldError {
    private String field;
    private String code;
    private Object rejectedValue;
    private String defaultMessage;
}

@Data @Builder
class ApiGlobalError {
    private String code;
    private String defaultMessage;
}

