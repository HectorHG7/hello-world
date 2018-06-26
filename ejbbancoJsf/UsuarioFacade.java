/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ejb;

import app.entity.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "banco-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    public Usuario buscarDni(String dni) {
        Query q = this.em.createNamedQuery("Usuario.findByDni");
       q.setParameter("dni", dni);
       if(q.getResultList().isEmpty())
           return null;
       else
        return (Usuario)q.getResultList().get(0);             

    }
    public List<Usuario> IdContraseña (String alias, String contrasena) {
        Query q = this.em.createQuery("select u from Usuario u " + 
                "where u.alias = :alias and u.contrasena = :contrasena");
        q.setParameter("alias", alias);
        q.setParameter("contrasena", contrasena);
        return q.getResultList();              
    } 
        public boolean loginOK(String nombreusuario, String contraseña){
        List<Usuario> list=IdContraseña(nombreusuario,contraseña);
        return list!=null && list.size()==1;
    }
        public boolean isUser(String alias){
           Query q = this.em.createQuery("SELECT u FROM Usuario u WHERE u.alias = :alias");
           q.setParameter("alias", alias);
            return !q.getResultList().isEmpty() && ((Usuario)q.getResultList().get(0)).getAlias().equals(alias);
        }
    
}
