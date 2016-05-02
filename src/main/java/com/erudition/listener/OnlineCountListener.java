package com.erudition.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by jeff on 16/2/28.
 */
public class OnlineCountListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ServletContext context = httpSessionEvent.getSession().getServletContext();
        Integer count = (Integer)context.getAttribute("OnlineCount");
        if(count == null){
            count = new Integer(1);
        }else{
            int countValue = count.intValue();
            count = new Integer(countValue + 1);
        }
        context.setAttribute("OnlineCount", count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext context = httpSessionEvent.getSession().getServletContext();
        Integer count=(Integer)context.getAttribute("OnlineCount");
        int countValue = count.intValue();
        count = new Integer(countValue - 1);
        context.setAttribute("OnlineCount", count);
    }
}
