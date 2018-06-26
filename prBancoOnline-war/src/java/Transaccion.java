/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.ejb.TransferenciaFacade;
import app.ejb.UsuarioFacade;
import app.entity.Transferencia;
import static app.entity.Transferencia_.fecha;
import app.entity.Usuario;
import java.io.IOException;
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
@WebServlet(urlPatterns = {"/Transaccion"})
public class Transaccion extends HttpServlet {

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
        Transferencia transf;
        String cuentaDest,cantidad,cuentaOrigen,Descripcion, Fecha;
        cuentaOrigen= request.getParameter("cuentaOrigen"); //recibe la cuenta origen
        cuentaDest= request.getParameter("cuentaDest"); //recibe la cuenta destino
        cantidad = request.getParameter("cantidad");//recibe la cantidad
        Descripcion= request.getParameter("Descripcion");
        Fecha = request.getParameter("Fecha"); 
        Usuario clienteOrig, clienteDest;
        clienteOrig = this.usuarioFacade.find(cuentaOrigen); //busca el numero de cuenta origen y destino en la bd
        clienteDest = this.usuarioFacade.find(cuentaDest);
        transf = new Transferencia();
       // transf.setUsuario(clienteOrig); //crea una transferencia y le asigna un cliente Orig , cliente Dest y cantidad
        //transf.setUsuario1(clienteDest);
        transf.setCantidad(new Integer(cantidad));
        transf.setDescripción(Descripcion);
        transf.setFecha((Date) fecha);
        
        List<Transferencia> listaTransferencias;  //crea lista de la transferencias y le incluye todasd las transferencias que hay
        
        listaTransferencias = this.transferenciaFacade.findAll();
        //listaClientes = this.customerFacade.buscarPorNombreYPorSupermercado("Comp", "95117");
        
        request.setAttribute("listat", listaTransferencias);
        
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("transferencias.jsp"); //envia la lista a la pagina transferencias
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

