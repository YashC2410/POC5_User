package com.springbootapp.users;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception{
public UserNotFoundException(String message) {
	super(message);
}
public UserNotFoundException(String message,Throwable t) {
	super(message,t);
}
}