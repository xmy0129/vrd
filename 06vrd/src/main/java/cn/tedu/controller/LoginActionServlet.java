package cn.tedu.controller;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@WebServlet(name = "LoginActionServlet",urlPatterns = "/loginaction")
public class LoginActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        //获取传递过来的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rem = request.getParameter("rem");
        System.out.println(username+":"+password+":"+rem);
        //创建Dao并调用login方法
        UserDao dao = new UserDao();
        User user = dao.login(username,password);
        if(user!=null){//登录成功
            //判断是否需要记住用户名和密码
            if(rem!=null){//需要记住用户名和密码
                //如果保存到cookie中的内容有中文,之前版本会有乱码问题
                username = URLEncoder.encode(username,"UTF-8");
                //从Cookie中获取数据时,还需要解码
               /* username = URLDecoder.decode(username,"UTF-8");*/

                //创建Cookie对象把用户名和密码保存
                Cookie c1 = new Cookie("username",username);
                Cookie c2 = new Cookie("password",password);
                //设置cookie保存时间
                c1.setMaxAge(60*60*24);
                //把cookie下发到浏览器
                response.addCookie(c1);
                response.addCookie(c2);
            }

            //记住登录状态
            HttpSession session = request.getSession();
            //把登录成功的用户对象保存到Session中
            session.setAttribute("user",user);
            System.out.println("记住登录状态:"+user);


            response.sendRedirect(request.getContextPath()+"/home");
        }else{//登录失败
            //如果登录失败则重定向到登录页面
            response.sendRedirect(request.getContextPath()+"/login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
