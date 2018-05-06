package com.cjx913.chattingonline.Interceptor;

import com.cjx913.chattingonline.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionProxy actionProxy = actionInvocation.getProxy();
//        获取action的命名空间
        String namespace = actionProxy.getNamespace();
//        获取action的url
        String actionName = actionProxy.getActionName();
//        获得请求url
        String url = namespace+"/"+actionName;
//        登陆或注册操作放行
        if(url.equals("/user/toRegister")||url.equals("/user/toLogin")||url.equals("/user/register")||url.equals("/user/login")){
//            先清seesion的user
            actionInvocation.getInvocationContext().getSession().remove("user");
            return actionInvocation.invoke();
        }
        User user = (User) actionInvocation.getInvocationContext().getSession().get("user");
        if(user==null){
//            未登录
            return "nonLogin";
        }
        return actionInvocation.invoke();
    }
}
