/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.ejb.MovimientoFacade;
import app.ejb.UsuarioFacade;
import app.entity.Movimiento;


import app.entity.Usuario;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabi
 */
@WebServlet(urlPatterns = {"/MovimientosServlet"})
public class MovimientosServlet extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;

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
        Movimiento mov;
        mov = new Movimiento();
        
        String operacion,cantidad,cuenta, fecha,entidad;
        cuenta= request.getParameter("cuenta"); //recibe la cuenta origen
        operacion= request.getParameter("operacion"); //recibe la cuenta destino
        cantidad = request.getParameter("cantidad");//recibe la cantidad
        entidad = request.getParameter("entidad");
        fecha = request.getParameter("fecha");
        Usuario cliente;
        cliente = this.usuarioFacade.find(cuenta);
          SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate1 = null;
        
         try {
            fechaDate1 = formato.parse(fecha);
            
        } 
        catch (ParseException ex) 
        {
            
        }
        
        
        // mov.setUsuario(cliente);
         mov.setConcepto(cantidad);
         mov.setTipo(new Integer(operacion));
         mov.setFecha( fechaDate1);
         mov.setEntidad(entidad);
         this.movimientoFacade.create(mov);
       
        
        List<Movimiento> listaMovimientos;  //crea lista de la transferencias y le incluye todasd las transferencias que hay
        
        listaMovimientos= this.movimientoFacade.findAll();
       
        //listaClientes = this.customerFacade.buscarPorNombreYPorSupermercado("Comp", "95117");
        
        request.setAttribute("listam", listaMovimientos);
        
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("movimiento.jsp"); //envia la lista a la pagina transferencias
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
