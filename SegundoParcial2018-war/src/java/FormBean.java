/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.MedicionesFacade;
import entity.Clientes;
import entity.Mediciones;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Hector
 */
@Named(value = "formBean")
@RequestScoped
public class FormBean {

    /**
     * Creates a new instance of FormBean
     */
    
    @EJB
    MedicionesFacade medicionesFacade;
    
    List<Mediciones> md;
    Double kw;

    public Double getKw() {
        return kw;
    }

    public void setKw(Double kw) {
        this.kw = kw;
    }
    
    public FormBean() {
    }

    public List<Mediciones> getMd(Clientes c) {
        md=medicionesFacade.med(c);
        return md;
    }
    
    public String vistaClientes(Clientes c){
        Calendar cal = Calendar.getInstance();
       
        Mediciones md = new Mediciones(0, cal.getTime(), kw);
        md.setCliente(c);
        medicionesFacade.create(md);
        return "index";
    }
    
    
}
