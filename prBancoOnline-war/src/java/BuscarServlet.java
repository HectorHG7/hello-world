/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.ejb.MovimientoFacade;
import app.entity.Movimiento;
import java.io.IOException;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * @author Gabi
 */
@WebServlet(urlPatterns = {"/BuscarServlet"})
public class BuscarServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession miSesion = request.getSession(true);
        String codigo, fecha1,fecha2;
        codigo = (String)request.getParameter("codigo");
        fecha1 = request.getParameter("fecha1");
        fecha2 = request.getParameter("fecha2");
        Movimiento mov;
        List<Movimiento> ListaMovLimit = new ArrayList<>();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate1 = null;
        Date fechaDate2 = null;
         try {
            fechaDate1 = formato.parse(fecha1,new ParsePosition(0));
            fechaDate2 = formato.parse(fecha2,new ParsePosition(0));
        } catch(Exception e) {
            
        }
        
        if(codigo.equals("") && (fecha1.equals("") || fecha2.equals(""))){
            Integer pagRetorno=5;
           request.setAttribute("pagRetorno", pagRetorno);
           request.setAttribute("msgError", "Numero de telefono invalido");
           RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/operacionErronea.jsp");
            
         
           
        } else if(codigo.equals("")) {
            ListaMovLimit = this.movimientoFacade.movimientosFechas((Integer)miSesion.getAttribute("nCuenta"),fechaDate1,fechaDate2 );  //Añade a la lista nueva creada la lista de los movimientos en ese intervalo
        } else {
            try{
           mov = this.movimientoFacade.find(new Integer(codigo)); //coge el movimiento segun codigo y lo añade
            ListaMovLimit.add(mov);
       }catch(NumberFormatException e){
           
           Integer pagRetorno=5;
           request.setAttribute("pagRetorno", pagRetorno);
           request.setAttribute("msgError", "invalid code");
           request.getRequestDispatcher("/operacionErronea.jsp").forward(request, response);
       }
                  
        }
        
        miSesion.setAttribute("ListaMovLimit", ListaMovLimit); //Devuelve al jsp la lista limitada 
        request.setAttribute("buscar",1);
        miSesion.setAttribute("page", "buscar");
        //request.setAttribute("insert", "openTab('click','buscar')");
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/UsuarioServlet"); //envia la lista a la pagina transferencias
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
