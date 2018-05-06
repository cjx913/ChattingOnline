package com.cjx913.chattingonline.Interceptor;

import com.cjx913.chattingonline.utils.FieldValidator;
import com.opensymphony.xwork2.*;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.dispatcher.HttpParameters;
import org.apache.struts2.dispatcher.Parameter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FieldValidationInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Map <String, String> map = null;

        //取得当前的action代理对象
        ActionProxy proxy = invocation.getProxy();
        //取得当前的action实例
        Object action =proxy.getAction();

        //校检模型驱动的字段
        //判断action是否实现了ModelDriven接口
        if (action instanceof ModelDriven) {
            //将action实例转为ModelDriven实例
            ModelDriven modelDriven = (ModelDriven) action;
            //获取Model
            Object model = modelDriven.getModel();
            if (model != null) {
                map = new HashMap <>();
                map = FieldValidator.jsr303Validator(model);
            }
        }

//        校检域对象字段
//        校检基本数据类型字段


//        如果存在校检错误
        if (map != null && map.size() > 0) {
//            if(action instanceof ActionSupport){
//                action = (ActionSupport)action;
//                Iterator <Map.Entry <String, String>> iterator = map.entrySet().iterator();
//                while (iterator.hasNext()){
//                    Map.Entry <String, String> next = iterator.next();
//                    ((ActionSupport) action).addFieldError(next.getKey(),next.getValue());
//                }
//            }
            invocation.getInvocationContext().put("fieldError", map);
            return "input";
        }
        return invocation.invoke();
    }
}
