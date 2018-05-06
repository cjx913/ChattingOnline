package com.cjx913.chattingonline.action;

import com.cjx913.chattingonline.entity.*;
import com.cjx913.chattingonline.exception.ServiceException;
import com.cjx913.chattingonline.utils.MD5Encryption;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Namespace("/user")
@ParentPackage("myDefaultPackage")
@Scope("prototype")
public class UserAction extends BaseAction implements ModelDriven <UserModelDriven> {

    private static final long serialVersionUID = 5293162803847837187L;

    private UserModelDriven umd = new UserModelDriven();

    //    模型驱动
    @Override
    public UserModelDriven getModel() {
        return umd;
    }



    @Action(value = "/toRegister",
            results = {@Result(name = "success", location = "register.jsp")})
    public String toRegister() {
        return SUCCESS;
    }

    @Action(value = "/register",
            results = {@Result(name = "success", location = "information.jsp"),
                    @Result(name = "error", location = "register.jsp")})
    public String register() {
        try {
            User user = getUserService().saveOrUpdateUser(umd.getName());
            Password p = getUserService().saveOrUpdatePassword(user.getId(), umd.getPassword());
            ActionContext.getContext().getSession().put("userId",user.getId());
            ActionContext.getContext().getSession().put("user", user);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    @Action(value = "/toLogin",
            results = {@Result(name = "success", location = "login.jsp")})
    public String toLogin() {
        return SUCCESS;
    }

    @Action(value = "/login",
            results = {@Result(name = "success", location = "chatting.jsp"),
                    @Result(name = "error", location = "login.jsp")})
    public String login() {
        User user = getUserService().findUserByAccountAndPassword(umd.getAccount(), umd.getPassword());
        if (user == null) {
            ActionContext.getContext().put("loginError","用户名或密码错误");
            return ERROR;
        }
        ActionContext.getContext().getSession().put("userId",user.getId());
        ActionContext.getContext().getSession().put("user", user);
        ActionContext.getContext().getSession().put("information", user.getUserInformation());
        ActionContext.getContext().getSession().put("friends",user.getFriends());
        ActionContext.getContext().getSession().put("groups",user.getGroups());
        return SUCCESS;
    }

    @Action(value = "/logout",
            results = {@Result(name = "success", location = "toLogin",type = "redirectAction")})
    public String logout(){
        ActionContext.getContext().getSession().remove("user");
        return SUCCESS;
    }

    @Action(value = "/toEditInformation",
            results = {@Result(name = "success", location = "editInformation.jsp")})
    public String toEditInformation() {
        return SUCCESS;
    }

    @Action(value = "/eidtInformation",
            results = {@Result(name = "success", location = "chatting.jsp"),
                    @Result(name = "input", location = "editInformation.jsp")})
    public String eidtInformation(){
        return saveInformation();
    }

    @Action(value = "/toInformation",
            results = {@Result(name = "success", location = "information.jsp")})
    public String toInformation() {
        return SUCCESS;
    }

    @Action(value = "/saveInformation",
            results = {@Result(name = "success", location = "chatting.jsp"),
                    @Result(name = "input", location = "information.jsp")})
    public String saveInformation() {
        try {
            getUserService().saveOrUpdateInformation(
                    umd.getId(), umd.getGender(), umd.getAge(),
                    umd.getBirth(), umd.getAddress(), umd.getHeadPortrait(),
                    umd.getEmail(), umd.getPhone());
            return SUCCESS;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    @Action(value = "/toEditPassword",
            results = {@Result(name = "success", location = "paw.jsp")})
    public String toEditPassword(){
        return SUCCESS;
    }

    @Action(value = "/editPassword",
            results = {@Result(name = "success", location = "chatting.jsp"),
                    @Result(name = "input", location = "paw.jsp"),
                    @Result(name = "error", location = "paw.jsp")})
    public String editPassword(){
        //验证两次密码输入是否一致
        if(!umd.getPassword().equals(umd.getAgainPassword())){
            Map<String,String> fieldError = new HashMap <>();
            fieldError.put("againPassword","两次输入密码不一致");
            ActionContext.getContext().put("fieldError",fieldError);
            return ERROR;
        }
        //验证密码是否正确
        if(!getUserService().existsPasswordByUserIdAndPassword(umd.getId(),umd.getOriginalPassword())){
            Map<String,String> fieldError = new HashMap <>();
            fieldError.put("password","密码不正确");
            return ERROR;
        }
        //更改密码
        getUserService().saveOrUpdatePassword(umd.getId(),umd.getPassword());
        return SUCCESS;
    }
}
