package com.example.company.device_library.util.annotations;



import com.example.company.device_library.util.validators.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {
    String message() default "Walidacja adresu email";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
