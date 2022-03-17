package com.sdi21.socialnetwork.validators;

import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SignUpFormValidator implements Validator {

    @Autowired
    private UsersService usersService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        //Email, name and surname must not be empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "Error.empty");

        //checking email does not exist
        if (usersService.getUserByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "Error.signup.email.duplicate");
        }


        if (user.getName().length() < 5 || user.getName().length() > 24) {
            errors.rejectValue("name", "Error.signup.name.length");
        }

        if (user.getSurname().length() < 5 || user.getSurname().length() > 24) {
            errors.rejectValue("surname", "Error.signup.surname.length");
        }

        if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
            errors.rejectValue("password", "Error.signup.password.length");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Error.signup.passwordConfirm.coincidence");
        }
    }
}
