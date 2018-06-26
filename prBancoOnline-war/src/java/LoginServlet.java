/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.ejb.EmpleadoFacade;
import app.ejb.TransferenciaFacade;
import app.ejb.UsuarioFacade;
import app.entity.Empleado;

import app.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hector
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @EJB
    private EmpleadoFacade empleadoFacade;

    @EJB
    private UsuarioFacade usuarioFacade;
    
    @EJB
    private TransferenciaFacade transferenciaFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       String user,contrasena;
       user=request.getParameter("user");
       contrasena=request.getParameter("pass");
       HttpSession miSesion = request.getSession(true); 
      miSesion.setAttribute("page","");
       
       if(this.usuarioFacade.loginOK(user, contrasena)){
            miSesion.setAttribute("user",user);
            miSesion.setAttribute("pass", contrasena);
     
            List<Usuario> list=this.usuarioFacade.IdContraseña(user,contrasena);
           RequestDispatcher rd=request.getRequestDispatcher("/UsuarioServlet");
           Integer nCuenta=list.get(0).getNCuenta();
           miSesion.setAttribute("nCuenta",nCuenta);
           miSesion.setAttribute("listaT",transferenciaFacade.getTransferenciasUsuario(nCuenta));
           rd.forward(request, response);
           
       }
       try{
        if(this.empleadoFacade.loginOK(new Integer (user), contrasena)){
             miSesion.setAttribute("user",new Integer(user));
            miSesion.setAttribute("pass", contrasena);
          RequestDispatcher rd=request.getRequestDispatcher("/empleado.jsp");
          rd.forward(request, response);
        }
       }catch(NumberFormatException e){
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.jsp"); 
        rd.forward(request, response); 
                }
         
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.jsp");
        rd.forward(request, response);
       
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
