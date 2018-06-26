/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.ejb.EmpleadoFacade;
import app.ejb.UsuarioFacade;
import app.entity.Empleado;
import app.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/CrearUsuarioServlet"})
public class CrearUsuarioServlet extends HttpServlet {

    @EJB
    private EmpleadoFacade empleadoFacade;

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
       
       Integer nCuenta=0;//Se autoincrementa en la bbdd cuando el valor es 0.
       String dniParam,usuarioParam, contrasenaParam, nombreParam, apellidosParam, domicilioParam, telefonoParam, emailParam;
       //cada usuario una sola cuenta
       //en jsp operacionCompletada mostrar numero de cuenta;
       Boolean usuariOk=true;
       dniParam= request.getParameter("dni").toUpperCase();
       usuarioParam=request.getParameter("user");
       contrasenaParam=request.getParameter("pass");
       nombreParam = request.getParameter("nombre");
       apellidosParam = request.getParameter("apellidos");
       domicilioParam = request.getParameter("domicilio");
       telefonoParam = request.getParameter("telefono");
       emailParam = request.getParameter("email");
       
       if(dniParam.equals("") || usuarioParam.equals("") || contrasenaParam.equals("")){
           Integer pagRetorno=1;
           request.setAttribute("pagRetorno", pagRetorno);
           request.setAttribute("msgError", "Debe rellenar todos los campos obligatorios");
           request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
       }
       
       if(usuarioFacade.IdContraseña(usuarioParam, contrasenaParam).isEmpty()){
           
       }else{
           Integer pagRetorno=1;
           request.setAttribute("pagRetorno", pagRetorno);
           request.setAttribute("msgError", "El usuario y la contraseña ya estan siendo utilizados por otra persona");
           request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
       }
      
       Usuario cliente=this.usuarioFacade.buscarDni(dniParam);
       
       if(cliente==null){
           cliente=new Usuario(nCuenta,dniParam,contrasenaParam,usuarioParam);
       }else{
           Integer pagRetorno=1;
           request.setAttribute("pagRetorno", pagRetorno);
           request.setAttribute("msgError", "El usuario ya tiene asociado un numero de cuenta");
           request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
       }
       
       cliente.setNombre(nombreParam);
       cliente.setApellidos(apellidosParam);
       cliente.setEmail(emailParam);
       try{
          cliente.setTeléfono(new Integer(telefonoParam));
       }catch(NumberFormatException e){
           usuariOk=false;
           Integer pagRetorno=1;
           request.setAttribute("pagRetorno", pagRetorno);
           request.setAttribute("msgError", "Numero de telefono invalido");
           request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
       }
       cliente.setDomicilio(domicilioParam);
       
       HttpSession miSesion = request.getSession(false); 
       Empleado em = empleadoFacade.find((Integer)miSesion.getAttribute("user"));
       cliente.setEmpleadoId(em);
       
       if(usuariOk)
       usuarioFacade.create(cliente);
       
       
       Integer pagRetorno=1;
       request.setAttribute("pagRetorno", pagRetorno);
       request.setAttribute("cuenta", cliente.getNCuenta());
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
