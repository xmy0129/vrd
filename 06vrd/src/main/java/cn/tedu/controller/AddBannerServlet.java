package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entity.Banner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "AddBannerServlet",urlPatterns = "/addbanner")
public class AddBannerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获取上传的文件
        Part part = request.getPart("file");
        String info = part.getHeader("content-disposition");
        String suffix = info.substring(info.lastIndexOf("."),info.length()-1);
        String fileName = UUID.randomUUID()+suffix;
        System.out.println(fileName);
        //得到保存文件的路径
        String path = request.getServletContext().getRealPath("images/");
        //把文件保存到这个路径
        part.write(path+fileName);
        //把轮播图信息保存到数据库
        Banner banner = new Banner(0,"images/"+fileName);
        BannerDao dao = new BannerDao();
        dao.insert(banner);
        //重定向到修改轮播图页面
        response.sendRedirect(request.getContextPath()+"/showbanner");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
