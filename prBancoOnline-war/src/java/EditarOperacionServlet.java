/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.ejb.MovimientoFacade;
import app.entity.Movimiento;
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
@WebServlet(urlPatterns = {"/EditarOperacionServlet"})
public class EditarOperacionServlet extends HttpServlet {

    @EJB 
    MovimientoFacade movimientoFacade;
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
       
        Integer codigoParam=null, seleccionParam;
       String conceptoParam, entidadParam, descripcionParam, cantidadParam;
       Boolean editOk=true;
       
       try{
       codigoParam=new Integer(request.getParameter("codigo"));
       }catch(NumberFormatException e){
           Integer pagRetorno=3;
           request.setAttribute("pagRetorno", pagRetorno);
           request.setAttribute("msgError", "Codigo invalido");
           request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
       }
      seleccionParam=new Integer(request.getParameter("seleccion"));
      
       Movimiento mov;
       
       if((mov=movimientoFacade.find(codigoParam))!=null){
       
        if(seleccionParam==1){
            movimientoFacade.remove(mov);
        }else{
            
            conceptoParam= request.getParameter("concepto").toUpperCase();
            if(!conceptoParam.equals("")) mov.setConcepto(conceptoParam);
            
            entidadParam=request.getParameter("entidad");
            if(!entidadParam.equals("")) mov.setEntidad(entidadParam);

            
            cantidadParam = request.getParameter("cantidad");
            if(!cantidadParam.equals("")) {
            Integer cantidad=null;
            try{
                 cantidad=new Integer(cantidadParam);
            }catch(NumberFormatException e){
                editOk=false;
                Integer pagRetorno=3;
                request.setAttribute("pagRetorno", pagRetorno);
                request.setAttribute("msgError", "Cantidad invalida");
                request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
            }
            mov.setCantidad(cantidad);
            }
            
            descripcionParam = request.getParameter("descripcion");
            if(!descripcionParam.equals("")) mov.setDescripcion(descripcionParam);
            
            if(editOk){
                movimientoFacade.edit(mov);
            }

        }
       }else{
           Integer pagRetorno=3;
           request.setAttribute("pagRetorno", pagRetorno);
           request.setAttribute("msgError", "El codigo de movimiento no existe");
           request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
       }
      
        Integer pagRetorno=3;
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
