/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import control.dao.LibroDAO;
import modelo.dto.LibroDTO;

/**
 *
 * @author Estudiante
 */
public class LibroCTO extends HttpServlet {

    LibroDAO ldao = new LibroDAO();
    LibroDTO li = new LibroDTO();
    
    long isbn2;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Libros")) {
            switch(accion){
                case "leerTodo":
                    List<LibroDTO> list = ldao.readAll();
                    request.setAttribute("lista", list);
                    break;
                case "Agregar":
                    long isbn = Long.parseLong(request.getParameter("txtIsbn"));
                    String nom = request.getParameter("txtNombres");
                    String aut = request.getParameter("txtAutor");
                    String edi = request.getParameter("txtEditorial");
                    int anio = Integer.parseInt(request.getParameter("txtAnio"));
                    li.setIsbn(isbn);
                    li.setNombre(nom);
                    li.setAutor(aut);
                    li.setEditorial(edi);
                    li.setAnio(anio);
                    ldao.create(li);
                    request.getRequestDispatcher("LibroCTO?menu=Libros&accion=leerTodo").forward(request, response);
                    break;
                case "Editar":
                    isbn2 = Long.parseLong(request.getParameter("isbn"));
                    LibroDTO l = new LibroDTO(isbn2);
                    l = ldao.read(l);
                    request.setAttribute("libro", l);
                    request.getRequestDispatcher("LibroCTO?menu=Libros&accion=leerTodo").forward(request, response);
                    break;
                case "Actualizar":
                    long isbnU = Long.parseLong(request.getParameter("txtIsbn"));
                    String nomU = request.getParameter("txtNombres");
                    String autU = request.getParameter("txtAutor");
                    String ediU = request.getParameter("txtEditorial");
                    int anioU = Integer.parseInt(request.getParameter("txtAnio"));
                    li.setIsbn(isbnU);
                    li.setNombre(nomU);
                    li.setAutor(autU);
                    li.setEditorial(ediU);
                    li.setAnio(anioU);
                    ldao.update(li);
                    request.getRequestDispatcher("LibroCTO?menu=Libros&accion=leerTodo").forward(request, response);
                    break;
                case "Eliminar":
                    isbn2 = Long.parseLong(request.getParameter("isbn"));
                    LibroDTO l2 = new LibroDTO(isbn2);
                    ldao.delete(l2);
                    request.getRequestDispatcher("LibroCTO?menu=Libros&accion=leerTodo").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("libro_vta.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
