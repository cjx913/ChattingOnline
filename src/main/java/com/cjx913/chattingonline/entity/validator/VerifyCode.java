package com.cjx913.chattingonline.entity.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VerifyCodeValidator.class)
@Documented
public @interface VerifyCode {
    String message() default "";

    Class <?>[] groups() default {};

    Class <? extends Payload>[] payload() default {};

    String verifyCode();

    String canvasVerifyCode();

}
