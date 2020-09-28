package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteServlet",urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductDao dao = new ProductDao();
        //得到删除作品的详情
        Product p = dao.findById(id);
        String filePath = request.getServletContext().getRealPath(p.getImgUrl());
        new File(filePath).delete();//把文件删除
        dao.deleteById(id);
        //重定向到首页
        response.sendRedirect(request.getContextPath()+"/home");
    }
}
