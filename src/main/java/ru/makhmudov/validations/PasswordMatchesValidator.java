package ru.makhmudov.validations;


import ru.makhmudov.annotations.PasswordMatches;
import ru.makhmudov.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*Класс отвечающий за настройку проверки паролей нашей аннотации
 * имплиментируем интерфейс ConstraintValidator и кладем туда нашу аннотацию */

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        SignupRequest userSignupRequest = (SignupRequest) obj;
        /*Сравниваем первое и второе поля с паролями из формы*/
        return userSignupRequest.getPassword().equals(userSignupRequest.getConfirmPassword());
    }
}
