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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.signup.email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.signup.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "Error.signup.surname.empty");

        if(user.getEmail() == null ||user.getEmail().isBlank()){
            errors.rejectValue("text","Error.signup.email.empty");

        }
        if(user.getName() == null ||user.getName().isBlank()){
            errors.rejectValue("text","Error.signup.name.empty");

        }
        if(user.getSurname() == null ||user.getSurname().isBlank()){
            errors.rejectValue("text","Error.signup.surname.empty");

        }


        //checking email does not exist
        if (usersService.getUserByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "Error.signup.email.duplicate");
        }


        if (user.getName().length() < 4 || user.getName().length() > 24) {
            errors.rejectValue("name", "Error.signup.name.length");
        }

        if (user.getSurname().length() < 5 || user.getSurname().length() > 24) {
            errors.rejectValue("surname", "Error.signup.surname.length");
        }

        if (user.getPassword().length() < 4 || user.getPassword().length() > 24) {
            errors.rejectValue("password", "Error.signup.password.length");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Error.signup.passwordConfirm.coincidence");
        }
    }
}
