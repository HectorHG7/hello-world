/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import app.Film;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hector
 */
@Stateless
public class FilmFacade extends AbstractFacade<Film> {

    @PersistenceContext(unitName = "SegundoParcial2017-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FilmFacade() {
        super(Film.class);
    }
    
    public List<Film> pelisCat(String catId){
        Query q = this.em.createQuery("SELECT f FROM Film f, FilmCategory fc WHERE f.filmId = fc.filmCategoryPK.filmId and fc.filmCategoryPK.categoryId = :catId");
       Integer c= new Integer (catId);
        q.setParameter("catId", c);
        return q.getResultList(); 
    }
    
}
