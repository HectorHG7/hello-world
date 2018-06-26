/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.ejb.EmpleadoFacade;
import app.ejb.MovimientoFacade;
import app.ejb.UsuarioFacade;
import app.entity.Empleado;
import app.entity.Movimiento;
import app.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/operaciones"})
public class operaciones extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;
    
    @EJB
    private MovimientoFacade movimientoFacade;
  
    @EJB
    private EmpleadoFacade empleadoFacade;
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
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        
        Integer autoIncrement = new Integer(0), operacionParam=null, cuentaParam = null;
        Double cantidadParam=null;
        String conceptoParam=null,  entidadParam=null, descripcionParam=null;
        Date fecha = Calendar.getInstance().getTime();
        Boolean operacionOk=true;
        
        try{
           cuentaParam= new Integer(request.getParameter("cuenta")); 
        }catch(NumberFormatException e){
            Integer pagRetorno=0;
            request.setAttribute("pagRetorno", pagRetorno);
            request.setAttribute("msgError", "El numero de cuenta no tiene un formato valido use 10 numeros");
            request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
        }
        
        operacionParam= new Integer(request.getParameter("operacion"));
      
        try{
            cantidadParam = new Double(request.getParameter("cant"));
        }catch(NumberFormatException e){
            Integer pagRetorno=0;
            request.setAttribute("pagRetorno", pagRetorno);
            request.setAttribute("msgError", "Cantidad con formato invalido use numeros y punto decimal");
            request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);

        }
           
        Usuario cliente=usuarioFacade.find(cuentaParam);
        if(cliente==null){
            Integer pagRetorno=0;
            request.setAttribute("pagRetorno", pagRetorno);
            request.setAttribute("msgError", "El numero de cuenta no existe");
            request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
        }
         if( operacionParam==-1 && cantidadParam > movimientoFacade.balanceNCuenta(cuentaParam)){
             operacionOk=false;
             Integer pagRetorno=0;
             request.setAttribute("pagRetorno", pagRetorno);
             request.setAttribute("msgError", "Balance negativo, la cantidad a retirar debe ser menor");
             request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
            
         }
        
        if(operacionOk){
        conceptoParam = request.getParameter("concepto");
        entidadParam= request.getParameter("entidad");
        descripcionParam= request.getParameter("descripcion");
        
        if(!conceptoParam.equals("") && !entidadParam.equals("")){
        
        Movimiento mov=new Movimiento(autoIncrement,conceptoParam, fecha, entidadParam, operacionParam, cantidadParam);
        
        mov.setDescripcion(descripcionParam);
        mov.setUsuarioNCuenta(cliente);       
        
        HttpSession miSesion = request.getSession(false); 
        Empleado em = empleadoFacade.find((Integer)miSesion.getAttribute("user"));
        mov.setEmpleadoId(em);
        
        movimientoFacade.create(mov);
         
        }else{
            Integer pagRetorno=0;
            request.setAttribute("pagRetorno", pagRetorno);
            request.setAttribute("msgError", "Debe rellenar todos los campos obligatorios");
            request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
            }
        Integer pagRetorno = 0;
        request.setAttribute("pagRetorno", pagRetorno);
        request.getRequestDispatcher("/operacionCompletada.jsp").forward(request, response);
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
