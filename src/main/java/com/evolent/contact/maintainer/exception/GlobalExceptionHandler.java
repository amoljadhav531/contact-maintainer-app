package com.evolent.contact.maintainer.exception;

import java.sql.SQLException;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleValidationError(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String defaultMessage = fieldError.getDefaultMessage();
        ExceptionData exData = new ExceptionData();
        exData.setError("Validation Error");
        exData.setMessage(defaultMessage);
        exData.setStatus("400");
        ModelAndView mav = new ModelAndView("error");
	    mav.addObject("ex", exData);
	    return mav;
    }
	
	@ExceptionHandler(SQLException.class)
    public ModelAndView handleSQLException(SQLException ex) {
        ExceptionData exData = new ExceptionData();
        exData.setError("Internal Server Error");
        exData.setMessage(ex.getMessage());
        exData.setStatus("500");
        ModelAndView mav = new ModelAndView("error");
	    mav.addObject("ex", exData);
	    return mav;
    }
	
	@ExceptionHandler(Exception.class)
    public ModelAndView handleSQLException(Exception ex) {
        ExceptionData exData = new ExceptionData();
        exData.setError("Internal Server Error");
        exData.setMessage(ex.getMessage());
        exData.setStatus("500");
        ModelAndView mav = new ModelAndView("error");
	    mav.addObject("ex", exData);
	    return mav;
    }
}
