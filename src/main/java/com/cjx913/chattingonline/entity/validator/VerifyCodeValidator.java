package com.cjx913.chattingonline.entity.validator;



import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

public class VerifyCodeValidator implements ConstraintValidator<VerifyCode,Object> {
    private String verifyCode;
    private String canvasVerifyCode;
    @Override
    public void initialize(VerifyCode constraintAnnotation) {
//        获取要验证字段的名称
        this.verifyCode = constraintAnnotation.verifyCode();
        this.canvasVerifyCode = constraintAnnotation.canvasVerifyCode();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
//            获取验证字段的值
            String verifyCodeValue = BeanUtils.getProperty(value,verifyCode);
            String canvasVerifyCodeValue = BeanUtils.getProperty(value,canvasVerifyCode);
//            如果要验证的两个值都为null，
            boolean valid=(verifyCodeValue==null||verifyCodeValue=="")&&(canvasVerifyCodeValue==null||canvasVerifyCodeValue=="");
            if(valid){
                return true;
            }
//            验证两个值是否相等
            boolean verify = (verifyCodeValue!=null) && verifyCodeValue.equalsIgnoreCase(canvasVerifyCodeValue);
            //如果不相等
            if(!verify){
                //设置错误信息显示在那个字段上
                String messageTemplate = context.getDefaultConstraintMessageTemplate();
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(messageTemplate)
                        .addNode(verifyCode)
                        .addConstraintViolation();
            }
            return verify;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return true;
    }
}
