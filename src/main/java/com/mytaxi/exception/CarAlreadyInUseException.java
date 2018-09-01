package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Selected Car has been opted by someone already")
public class CarAlreadyInUseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String ErrorMessage = "We regret to your inform you that selected car has been already"
			+ "opted by other driver.Please select different car!!Happy Driving!!";
	
	 public CarAlreadyInUseException(final String message)
	    {
	        super(ErrorMessage);
	    }
}
