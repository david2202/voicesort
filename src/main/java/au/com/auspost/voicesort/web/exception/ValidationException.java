package au.com.auspost.voicesort.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

@Getter @AllArgsConstructor
public class ValidationException extends RuntimeException {

    private BindingResult bindingResult;

}
