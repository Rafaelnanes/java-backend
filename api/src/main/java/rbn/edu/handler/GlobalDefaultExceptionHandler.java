package rbn.edu.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import rbn.edu.exceptions.BusinessException;
import rbn.edu.model.ResponseError;

@ControllerAdvice
class GlobalDefaultExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseError exceptionHandler(BusinessException ex) {
	ResponseError error = new ResponseError();
	error.setStatusCode(HttpStatus.BAD_REQUEST.value());
	List<String> list = new ArrayList<String>();
	list.addAll(ex.getMessages());
	error.setMessages(list);
	return error;
    }
}