/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ejb;

import app.entity.Movimiento;
import app.entity.Transferencia;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hector
 */
@Stateless
public class MovimientoFacade extends AbstractFacade<Movimiento> {

    @PersistenceContext(unitName = "banco-ejbPU")
    private EntityManager em;
    
    @EJB
    TransferenciaFacade transferenciaFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoFacade() {
        super(Movimiento.class);
    }
       public List<Movimiento> buscarMovimientoNCuenta(int nCuenta) {
        Query q = this.em.createQuery("select m from Movimiento m " + 
                "where m.usuarioNCuenta.nCuenta=:nCuenta");
       q.setParameter("nCuenta", nCuenta);
       
        return q.getResultList();             

    }
    
    public List<Movimiento> buscarGastosNCuenta(int nCuenta) {
            List<Movimiento> lm= buscarMovimientoNCuenta(nCuenta);
            List<Movimiento> lm1=new ArrayList<>();
            for(Movimiento m : lm){
                if(m.getTipo()==-1){
                    lm1.add(m);
                }
            }
            return lm1;              
    }
    
    public List<Movimiento> buscarIngresosNCuenta(int nCuenta) {
            List<Movimiento> lm= buscarMovimientoNCuenta(nCuenta);
            List<Movimiento> lm1=new ArrayList<>();
            for(Movimiento m : lm){
                if(m.getTipo()==1){
                    lm1.add(m);
                }
            }
            return lm1;              
    }
     public Double balanceNCuenta(int nCuenta){
            double balance=0;
            List<Movimiento> lm= buscarMovimientoNCuenta(nCuenta);
            for(Movimiento m : lm){
               balance=balance+m.getCantidad()*m.getTipo();
            }
            List<Transferencia> lt= transferenciaFacade.getTransferenciasUsuario(nCuenta);
            for(Transferencia t : lt){
               balance=balance+t.getCantidad()*-1;
            }
            return balance;              
    }
          
        public List<Movimiento> movimientosFechas(int nCuenta, Date d1, Date d2) {
            List<Movimiento> lm= buscarMovimientoNCuenta(nCuenta);
            List<Movimiento> lm1=new ArrayList<>();
            for(Movimiento m : lm){
                if(m.getFecha().compareTo(d1)!=-1 && m.getFecha().compareTo(d2)!=1){
                    lm1.add(m);
                }
            }        
            return lm1;
    }
}
