package com.cjx913.chattingonline.utils;


import javax.swing.text.html.Option;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;

public class ObjectReplace {

    public static final <T extends Object> T replacePropertyExceptNull(T original, T replace) throws IntrospectionException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class clazz = replace.getClass();//获得实体类名
        /*如果original爲空，创建original*/
        if(original==null){
            original = (T) clazz.newInstance();
        }
        /*如果replace爲空，直接返回original*/
        if(replace==null){
            return original;
        }
        Field[] fields = clazz.getDeclaredFields();//获得属性
        for (Field field : fields) {
            //判断是否为静态属性
            if(Modifier.isStatic(field.getModifiers())){
                continue;
            }
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
            //获得get方法
            Optional<Method> getterOptional = Optional.ofNullable(pd.getReadMethod());
            Method getter = getterOptional.orElse(null);
            //获得set方法
            Optional<Method> setterOptional = Optional.ofNullable(pd.getWriteMethod());
            Method setter = setterOptional.orElse(null);
            if (getter==null&&setter==null){
                continue;
            }
            //getter.invoke(obj);//此处为执行该Object对象的get方法
            //setter.invoke(obj, "参数");//此处为执行该Object对象的set方法
            if (getter.invoke(replace) != null) {
                setter.invoke(original, getter.invoke(replace));
            }
        }
        return original;
    }
}
