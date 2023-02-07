package Controller;

import Model.Category;
import Model.Product;
import Service.CategoryServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CategoryServlet", value = "/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    ProductServlet productServlet = new ProductServlet();
    CategoryServiceImpl categoryService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                showCreatForm(request,response);
                break;
            case "update":
                showUpdateForm(request,response);
                break;
            case "delete":
                showDeleteForm(request,response);
                break;
            default:
                listCategory(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action=request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                createCategory(request, response);
                break;
            case "update":
                updateCategory(request, response);
                break;
            case "delete":
                deleteCategory(request,response);
                break;
        }
    }
    private void listCategory(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher dispatcher= request.getRequestDispatcher("DisplayCategory/list.jsp");
        request.setAttribute("transmitted", categoryService.findAll());
        try {
            dispatcher.forward(request,response);
        } catch (ServletException |IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreatForm(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayCategory/create.jsp");
        request.setAttribute("transmitted", categoryService.findAll());
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private void createCategory(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Category category = new Category(id, name);
        categoryService.save(category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayCategory/create.jsp");
        request.setAttribute("message","New product war created");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException|IOException e) {
            e.printStackTrace();
        }
    }
    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response){
        int id= Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findById(id);
        request.setAttribute("transmitted",category);
        RequestDispatcher dispatcher=request.getRequestDispatcher("DisplayCategory/update.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException |IOException e) {
            e.printStackTrace();
        }
    }
    private void updateCategory(HttpServletRequest request, HttpServletResponse response){
        int id= Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Category category = categoryService.findById(id);
        category.setName(name);
        int index = categoryService.findAll().indexOf(category);
        categoryService.update(index,category);
        request.setAttribute("transmitted",category);
        request.setAttribute("message", "Product information war updated.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayCategory/update.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findById(id);
        request.setAttribute("transmitted",category);
        RequestDispatcher dispatcher=request.getRequestDispatcher("DisplayCategory/delete.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException |IOException e) {
            e.printStackTrace();
        }
    }
    private  void  deleteCategory(HttpServletRequest request , HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findById(id);
        categoryService.delete(category);
        try {
            response.sendRedirect("/CategoryServlet");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
