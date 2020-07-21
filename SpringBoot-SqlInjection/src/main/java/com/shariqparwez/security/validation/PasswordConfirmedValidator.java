package com.shariqparwez.security.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.shariqparwez.security.model.UserDto;

public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, Object>{

	@Override
	public boolean isValid(Object user, ConstraintValidatorContext context) {
		String password = ((UserDto)user).getPassword();
		String confirmedPassword = ((UserDto)user).getConfirmPassword();
		return password.equals(confirmedPassword);
	}

}
