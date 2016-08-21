package com.erudition.interceptor;

import com.erudition.util.GlobalVariable;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 注入全局变量
 * Created by sl on 16-3-3.
 *
 */
public class FirstInterceptor extends HandlerInterceptorAdapter {

    GlobalVariable globalVar = GlobalVariable.getInstance();

    @Override
    public boolean preHandle(HttpServletRequest request , HttpServletResponse response ,
                             Object object) throws Exception{

        HttpSession session = request.getSession();

        //isHasGlobalVar：用于判断是否已经拦截过
        if(session.getAttribute("isHasGlobalVar") == null){
            session.setAttribute("isHasGlobalVar", true);

            Map<String, String> variables = globalVar.getAll();
            for (Map.Entry<String, String> entry : variables.entrySet()) {
                session.setAttribute(entry.getKey(), entry.getValue());
            }
            System.out.println(session.getAttribute("index"));
        }
        return  true;
    }
}
