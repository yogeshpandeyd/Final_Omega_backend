package com.myteam.carloanapp.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandaller {

	ExceptionInfo exceptionInfo = new ExceptionInfo();

	@ExceptionHandler(value = userNotPresentException.class)
	public ResponseEntity<ExceptionInfo> handeluserNotPresentException(userNotPresentException userNotPresentException) {
		exceptionInfo.setMsg(userNotPresentException.getMessage());
		exceptionInfo.setCode("1001");
		return new ResponseEntity<ExceptionInfo>(exceptionInfo, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = CustomerNotSavedException.class)
	public ResponseEntity<ExceptionInfo> handleCustomerNotSAvedException() {
		CustomerNotSavedException customerException=new CustomerNotSavedException(null);
		exceptionInfo.setMsg(customerException.getMessage());
		exceptionInfo.setCode("1002");
		return new ResponseEntity<ExceptionInfo>(exceptionInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = CustomerNotPresentException.class)					//getting custExpn ref 
	public ResponseEntity<ExceptionInfo> handleCustomerWithIdAndStatusNotFound(CustomerNotPresentException customerNotPresentException)
	{
		exceptionInfo.setMsg(customerNotPresentException.getMessage()); ///custom msg
		exceptionInfo.setCode("1004");		//custom code
		return new ResponseEntity<ExceptionInfo> (exceptionInfo,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
