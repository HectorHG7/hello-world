/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.ejb.UsuarioFacade;
import app.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hector
 */
@WebServlet(urlPatterns = {"/EditarUsuarioServlet"})
public class EditarUsuarioServlet extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;
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
       
       Integer nCuenta=null,seleccionParam;
       String dniParam,usuarioParam, contrasenaParam, nombreParam, apellidosParam, domicilioParam, telefonoParam, emailParam;
       Boolean editOk=true;
       
       try{
       nCuenta=new Integer(request.getParameter("ncuenta"));
       }catch(NumberFormatException e){
           Integer pagRetorno=2;
           request.setAttribute("pagRetorno", pagRetorno);
           request.setAttribute("msgError", "El numero de cuenta debe tener 10 digitos");
           request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
       }
       seleccionParam=new Integer(request.getParameter("seleccion"));
      
       Usuario cliente;
       
       if((cliente=usuarioFacade.find(nCuenta))!=null){
       
        if(seleccionParam==1){
            usuarioFacade.remove(cliente);
        }else{
            
            dniParam= request.getParameter("dni").toUpperCase();
            if(!dniParam.equals("")) cliente.setDni(dniParam);
            
            usuarioParam=request.getParameter("user");
            if(!usuarioParam.equals("")) cliente.setAlias(usuarioParam);

            contrasenaParam=request.getParameter("pass");
            if(!contrasenaParam.equals("")) cliente.setContrasena(contrasenaParam);

                        
            nombreParam = request.getParameter("nombre");
            if(!nombreParam.equals("")) cliente.setNombre(nombreParam);
            
            apellidosParam = request.getParameter("apellidos");
            if(!apellidosParam.equals("")) cliente.setApellidos(apellidosParam);
           
            domicilioParam = request.getParameter("domicilio");
            if(!domicilioParam.equals("")) cliente.setDomicilio(domicilioParam);
            
            telefonoParam = request.getParameter("telefono");
            if(!telefonoParam.equals("")) {
            Integer telefono=null;
            try{
                 telefono=new Integer(telefonoParam);
            }catch(NumberFormatException e){
                editOk=false;
                Integer pagRetorno=2;
                request.setAttribute("pagRetorno", pagRetorno);
                request.setAttribute("msgError", "El nuemero de telefono no es valido");
                request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
            }
            cliente.setTeléfono(telefono);
            }
            
            emailParam = request.getParameter("email");
            if(!emailParam.equals("")) cliente.setEmail(emailParam);
            
            if(editOk){
                usuarioFacade.edit(cliente);
            }

        }
       }else{
           Integer pagRetorno=2;
           request.setAttribute("pagRetorno", pagRetorno);
           request.setAttribute("msgError", "El numero de cuenta no existe");
           request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
       }
      
        Integer pagRetorno=2;
        request.setAttribute("pagRetorno", pagRetorno);
        request.getRequestDispatcher("/operacionCompletada.jsp").forward(request, response);
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
