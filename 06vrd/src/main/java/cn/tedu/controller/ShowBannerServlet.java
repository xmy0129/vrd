package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entity.Banner;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowBannerServlet",urlPatterns = "/showbanner")
public class ShowBannerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询所有的轮播图信息 并且显示到页面中
        BannerDao dao = new BannerDao();
        List<Banner> list = dao.findAll();
        Context context = new Context();
        context.setVariable("list",list);
        ThUtils.print("banner.html",context,response);
    }
}
