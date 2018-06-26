/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.ejb.MovimientoFacade;
import app.ejb.TransferenciaFacade;
import app.ejb.UsuarioFacade;
import app.entity.Empleado;
import app.entity.Transferencia;
import app.entity.Usuario;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gabi
 */
@WebServlet(urlPatterns = {"/transferencia"})
public class transferencia extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private TransferenciaFacade transferenciaFacade;
    
     @EJB
     private MovimientoFacade movimientoFacade;

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
            
            String cuentaParam=request.getParameter("cuentaDest");
            String cantidadParam=request.getParameter("cantidad");
            Boolean transOk=true;
            HttpSession miSesion = request.getSession(false); 
            Integer cuentaOrig = (Integer)miSesion.getAttribute("nCuenta");
            miSesion.setAttribute("page","transf");
                    
            Double cantidad=null;
            try{
                cantidad = new Double(cantidadParam);
                
            }catch(NumberFormatException e){
                Integer pagRetorno=4;
                transOk=false;
                request.setAttribute("pagRetorno", pagRetorno);
                request.setAttribute("msgError", "Cantidad invalida");
                request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
            }
            Integer nCuentaDest=null;
            
            try{
                nCuentaDest = new Integer(cuentaParam);
                
            }catch(NumberFormatException e){
                Integer pagRetorno=4;
                transOk=false;
                request.setAttribute("pagRetorno", pagRetorno);
                request.setAttribute("msgError", "Numero de cuenta invalida");
                request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
            }
            Usuario cliente=null;
            
           if(transOk) cliente=usuarioFacade.find(nCuentaDest);
           
           if(cliente==null){
               Integer pagRetorno=4;
                transOk=false;
                request.setAttribute("pagRetorno", pagRetorno);
                request.setAttribute("msgError", "Numero de cuenta no existe");
                request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
           }else{
              if( cantidad > movimientoFacade.balanceNCuenta(cuentaOrig)){
                  Integer pagRetorno=4;
                transOk=false;
                request.setAttribute("pagRetorno", pagRetorno);
                request.setAttribute("msgError", "Balance negativo, la cantidad es demasiado elevada");
                request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
              }else{
                 Transferencia trans=new Transferencia(0,Calendar.getInstance().getTime(),cantidad);
                 trans.setUsuarioNCuenta(usuarioFacade.find(cuentaOrig));
                 trans.setUsuarioNCuenta1(cliente);
                 if(transOk)
                  transferenciaFacade.create(trans);
              }
           }
        miSesion.setAttribute("listaT",transferenciaFacade.getTransferenciasUsuario(cuentaOrig));
        Integer pagRetorno=4;
        request.setAttribute("pagRetorno", pagRetorno);
        //request.setAttribute("insert", "openTab('click','transferencias')");
        request.getRequestDispatcher("operacionCompletada.jsp").forward(request, response);
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
