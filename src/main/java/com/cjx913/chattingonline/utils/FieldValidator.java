package com.cjx913.chattingonline.utils;

import javax.crypto.MacSpi;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

public class FieldValidator {

    /**
     * @param t   需要校检的对象
     * @param <T>
     * @return 错误字段和信息的映射
     */
    public static final <T> Map <String, String> jsr303Validator(T t,Class<?>...groups) {
//        t爲空，范围null
        if (t == null) {
            return null;
        }
        Map <String, String> map = null;
//        创建检验器
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
//        对t进行校验
        Set <ConstraintViolation <T>> violations = validator.validate(t,groups);
//        校检内有错误
        return getFieldErrorMap(violations);
    }

    public static final <T> Map <String, String> jsr303Validator(T t,String propertyName,Class<?>...groups) {
//        t爲空，范围null
        if (propertyName==null||t == null) {
            return null;
        }
        Map <String, String> map = null;
        //ResourceBundle bundle = null;
//        创建检验器
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
//        对t进行校验
        Set <ConstraintViolation <T>> violations = validator.validateProperty(t,propertyName,groups);

        return getFieldErrorMap(violations);
    }

    public static final <T> Map <String, String> jsr303Validator(Class<T> beanType,String propertyName,Object value,Class<?>...groups) {
//        t爲空，范围null
        if (propertyName==null||value == null) {
            return null;
        }
        Map <String, String> map = null;
        //ResourceBundle bundle = null;
//        创建检验器
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
//        对t进行校验
        Set <ConstraintViolation <T>> violations = validator.validateValue(beanType,propertyName,value,groups);

        return getFieldErrorMap(violations);
    }

    private static final   <T> Map<String,String> getFieldErrorMap(Set <ConstraintViolation <T>> violations){

        Map<String,String> map = null;
        //        校检内有错误
        if (violations.size() == 0) {
            return null;
        } else {
            map = new HashMap <>();
//            i18l
            //ResourceBundle bundle = null;
            // bundle = ResourceBundle.getBundle("messages");
//        保存错误信息：Map<Field,Message>
            Iterator <ConstraintViolation <T>> iterator = violations.iterator();
            ConstraintViolation <T> violation = null;
//            遍历校验信息
            while (iterator.hasNext()) {
                violation = iterator.next();
                String field = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                map.put(field, message);
            }
            return map;
        }
    }
}
