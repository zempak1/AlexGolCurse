package ru.makhmudov.validations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;

/*Валидатор, который будет разбираться с ошибками приходящими на наш сервер*/


/*Возвращает мапу с ошибками*/
@Service
public class ResponseErrorValidation {

    /*BindingResult приходит из Validation framework
    *Содержит ошибки, которые могут здесь сохраниться
    *например если в форму введут пустой никнейм
    *
    * Прежде чем ошибка куда-то выскочит, здесь мы ее должны получить*/

    public ResponseEntity<Object> mapValidationService(BindingResult result) {

        /*Если есть ошибки*/
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            /*Если коллекция с ошибками не пустая*/
            if (!CollectionUtils.isEmpty(result.getAllErrors())) {
                for (ObjectError error : result.getAllErrors()) {
                    errorMap.put(error.getCode(), error.getDefaultMessage());
                }
            }

            /*FieldError отвечает конкретно за поля, а getAllErrors может содержать в себе объекты*/
            /*error.getField() в каком поле была ошибка*/
            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
