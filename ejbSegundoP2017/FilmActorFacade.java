/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import app.FilmActor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hector
 */
@Stateless
public class FilmActorFacade extends AbstractFacade<FilmActor> {

    @PersistenceContext(unitName = "SegundoParcial2017-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FilmActorFacade() {
        super(FilmActor.class);
    }
    
}
