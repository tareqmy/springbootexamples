package com.tareqmy.springbootexamples.service;

import com.tareqmy.springbootexamples.web.dto.ValidPassword;
import lombok.extern.slf4j.Slf4j;
import org.passay.*;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

@Slf4j
@Service
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
            new LengthRule(8, 20),
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            new CharacterRule(EnglishCharacterData.Digit, 1),
            new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(String.join(" ", validator.getMessages(result)))
            .addConstraintViolation()
            .disableDefaultConstraintViolation();
        return false;
    }
}
