package cn.tedu.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class MyListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public MyListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("工程发布时间");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("工程停止运行时间");
    }

    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session创建");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session销毁");
    }

    public void attributeAdded(HttpSessionBindingEvent sbe) {
        System.out.println("session添加属性");
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        System.out.println("session删除属性");
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attribute
         is replaced in a session.
      */
    }
}
