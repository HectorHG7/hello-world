/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ejb;

import app.entity.Empleado;
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
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "banco-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }
    public List<Empleado> IdContraseña (Integer id, String contrasena) {
        Query q = this.em.createQuery("select e from Empleado e " +
                                  "where e.id = :id and e.contrasena = :contrasena");
        q.setParameter("id", id);
        q.setParameter("contrasena", contrasena);
        return q.getResultList();              
    }
    public boolean loginOK(Integer id, String contraseña){
        List<Empleado> list=IdContraseña(id,contraseña);
        return list!=null && list.size()==1;
    }
    
}
