/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Alumnos;
import modelo.AlumnosDAO;

/**
 *
 * @author Alicia
 */
@WebServlet(name = "AlumnosController", urlPatterns = {"/AlumnosController"})
public class AlumnosController extends HttpServlet {

 
 /*   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AlumnosController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AlumnosController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
*/
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AlumnosDAO alumnosDao=new AlumnosDAO();
        String accion;
        RequestDispatcher dispatcher=null;
        accion=request.getParameter("accion");
        
        if(accion==null||accion.isEmpty())
        {
            dispatcher=request.getRequestDispatcher("Vistas/alumnos.jsp");
        }
        else if(accion.equals("modificar"))
        {
            dispatcher=request.getRequestDispatcher("Vistas/modificar.jsp");
        }
        else if(accion.equals("actualizar"))
        {
            int id=Integer.parseInt(request.getParameter("id"));
            String nombre=request.getParameter("nombre");
            String apellido=request.getParameter("apellido");
            String mail=request.getParameter("mail");         
            
            Alumnos alumno=new Alumnos(id,nombre,apellido,mail);
            alumnosDao.actualizarAlumno(alumno);
            dispatcher=request.getRequestDispatcher("Vistas/alumnos.jsp");
        }   
        else if(accion.equals("eliminar"))
        {
            int id=Integer.parseInt(request.getParameter("id"));
            alumnosDao.eliminarAlumno(id);
            dispatcher=request.getRequestDispatcher("Vistas/alumnos.jsp"); 
        }
        else if(accion.equals("nuevo"))
        {
            dispatcher=request.getRequestDispatcher("Vistas/nuevo.jsp");
        }
        else if(accion.equals("insert"))
        {
            String nombre=request.getParameter("nombre");
            String apellido=request.getParameter("apellido");
            String mail=request.getParameter("mail");
            
            Alumnos alumno=new Alumnos(0,nombre, apellido, mail);
            alumnosDao.insertarAlumno(alumno);
            dispatcher=request.getRequestDispatcher("Vistas/alumnos.jsp");
        }  
        else
        {
            dispatcher=request.getRequestDispatcher("Vistas/alumnos.jsp"); 
        } 
        dispatcher.forward(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
