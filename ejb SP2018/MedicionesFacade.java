/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Clientes;
import entity.Mediciones;
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
public class MedicionesFacade extends AbstractFacade<Mediciones> {

    @PersistenceContext(unitName = "SegundoParcial2018-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicionesFacade() {
        super(Mediciones.class);
    }
    
    public List<Mediciones> med(Clientes c){
       Query q = this.em.createQuery("SELECT m FROM Mediciones m WHERE m.cliente = :c ORDER BY m.fechahora DESC");
       q.setParameter("c", c);
        return q.getResultList();  
    }
    
}
