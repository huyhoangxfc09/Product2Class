package Controller;

import Model.Category;
import Model.Product;
import Service.CategoryServiceImpl;
import Service.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    static CategoryServiceImpl categoryService = new CategoryServiceImpl();
    static ProductServiceImpl productService = new ProductServiceImpl( categoryService);

    public ProductServlet() {
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request,response);
                break;
            case "update":
                showUpdateForm(request,response);
                break;
            case "delete":
                showDeleteForm(request,response);
                break;
            default:
                listProduct(request, response);
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
                createProduct(request,response);
                break;
            case "update":
                updateProduct(request,response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                break;
        }
    }
    private void listProduct(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayProduct/list.jsp");
        request.setAttribute("transmitted", productService.findAll());
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayProduct/create.jsp");
        request.setAttribute("category", categoryService.findAll());
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private  void createProduct(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        String name =request.getParameter("name");
        String nameCategory =request.getParameter("category");
        Category category = null;
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        for (Category c :categoryService.findAll()) {
            if (c.getName().equals(nameCategory)){
                category=c;
                break;
            }
        }
        Product product = new Product(id,name,price,category,quantity);
        productService.save(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayProduct/create.jsp");
        request.setAttribute("message","New product war created");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException|IOException e) {
            e.printStackTrace();
        }
    }
    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response){
        int id= Integer.parseInt(request.getParameter("id"));
        Product product=productService.findById(id);
        request.setAttribute("transmitted",product);
        request.setAttribute("category",categoryService.findAll());
        RequestDispatcher dispatcher=request.getRequestDispatcher("DisplayProduct/update.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException |IOException e) {
            e.printStackTrace();
        }
    }
    private  void  updateProduct(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        String name = request.getParameter("name");
        String nameCategory =request.getParameter("category");
        Category category = null;
        for (Category c :categoryService.findAll()) {
            if (c.getName().equals(nameCategory)){
                category=c;
                break;
            }
        }
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        RequestDispatcher dispatcher;
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setQuantity(quantity);
        int index = productService.findAll().indexOf(product);
        productService.update(index,product);
        request.setAttribute("transmitted",product);
        request.setAttribute("message", "Product information war updated.");
        dispatcher = request.getRequestDispatcher("DisplayProduct/update.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response){
        int id= Integer.parseInt(request.getParameter("id"));
        Product product=productService.findById(id);
        request.setAttribute("transmitted",product);
        RequestDispatcher dispatcher=request.getRequestDispatcher("DisplayProduct/delete.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException|IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Product product=productService.findById(id);
        productService.delete(product);
        try {
            response.sendRedirect("/ProductServlet");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
