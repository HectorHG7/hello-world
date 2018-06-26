/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.CategoryFacade;
import ejb.FilmFacade;
import ejb.LanguageFacade;
import ejb.RatingFacade;
import entity.Category;
import entity.Film;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(urlPatterns = {"/GuardarServlet"})
public class GuardarServlet extends HttpServlet {

    @EJB
    CategoryFacade categoryFacade;
    
    @EJB
    RatingFacade ratingFacade;
    
    @EJB
    LanguageFacade languageFacade;
    
    @EJB
    FilmFacade filmFacade;
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
        
        String tit=request.getParameter("titulo");
                String des=request.getParameter("des");
        String anio=request.getParameter("anio");
        String[] categ=request.getParameterValues("categ");
        String lang=request.getParameter("lang");
        String rat=request.getParameter("rat");
        
        Film f= new Film((short)0, tit, (short)0, "0", "0", "Hector");
        f.setDescription(des);
        f.setReleaseYear(new Integer(anio));
        
        f.setLanguageId(languageFacade.find(new Short(lang)));
        f.setRatingId(ratingFacade.find(new Short(rat)));
        
        List<Category> cs=new ArrayList();
        
        for(String s: categ){
            
            cs.add(categoryFacade.find(new Short(s)));
        }
        
        f.setCategoryList(cs);
        
        filmFacade.create(f);
        
        
        request.getRequestDispatcher("/peli.jsp").forward(request, response);

        
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
