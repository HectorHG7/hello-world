/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.ClientesFacade;
import ejb.ProvinciasFacade;
import entity.Clientes;
import entity.Provincias;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Hector
 */
@Named(value = "clientesBean")
@SessionScoped
public class ClientesBean implements Serializable {
    
    @EJB
    ProvinciasFacade provinciasFacade;
    
    @EJB
    ClientesFacade clientesFacade;
    
    List<Clientes> cl;
    List<String> pr;
    String[] p=null;
    Clientes cs;

    public String[] getP() {
        return p;
    }

    public void setP(String[] p) {
        this.p = p;
    }
    /**
     * Creates a new instance of ClientesBean
     */
    public ClientesBean() {
        pr=new ArrayList<>();
    }

    public List<String> getPr() {
        return pr;
    }

    public Clientes getCs() {
        return cs;
    }
    
    public List<Clientes> getCl() {
      if(p!=null){
          List<Clientes> c=new ArrayList<>();
          for(String s:p){
           for(Clientes cs: clientesFacade.findAll()){
               if(cs.getCodigoprovincia().getProvincia().equals(s))
                   c.add(cs);
            }
          }
           return c; 
        }else
            cl=clientesFacade.findAll();
        return cl;
    }
    public String valueChanged() {
      return ("");
    }
    public String avance(Clientes c){
        cs=c;
        return "formulario";
    }
    public String editCliente(Clientes c){
        cs=c;
        return "clientes";
    }
    @PostConstruct
    public void inicializar(){
        cl=clientesFacade.findAll();
        List<Provincias> prov =provinciasFacade.findAll();
        for(Provincias p: prov){
            pr.add(p.getProvincia());
        }
    }
    
}
