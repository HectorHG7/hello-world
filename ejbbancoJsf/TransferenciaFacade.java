/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ejb;

import app.entity.Transferencia;
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
public class TransferenciaFacade extends AbstractFacade<Transferencia> {

    @PersistenceContext(unitName = "banco-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransferenciaFacade() {
        super(Transferencia.class);
    }
    
    public List<Transferencia> getTransferenciasUsuario(int nCuenta) {
        Query q = this.em.createQuery("select t from Transferencia t " + 
                "where t.usuarioNCuenta.nCuenta=:nCuenta");
       q.setParameter("nCuenta", nCuenta);
        return q.getResultList();             

    }
}
