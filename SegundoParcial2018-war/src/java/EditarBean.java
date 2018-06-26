/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.CallespoblacionesFacade;
import ejb.ClientesFacade;
import ejb.PoblacionesFacade;
import ejb.ProvinciasFacade;
import entity.Callespoblaciones;
import entity.Clientes;
import entity.Poblaciones;
import entity.Provincias;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

/**
 *
 * @author Hector
 */
@Named(value = "editarBean")
@RequestScoped
public class EditarBean implements Serializable {

    private String nombre;
    private String apellido;
    private String apellido2;
    private int numero;
    private int piso;
    private int metros;
    private String calle;
    private String codigopoblacion;
    private String codigoprovincia;
    private Boolean mPob;
    private Boolean mCall;
    
    public Boolean getMostrarPoblaciones() {
        return mPob;
    }
     public Boolean getMostrarCalles() {
        return mPob;
    }
     
    @Inject
    ClientesBean cb;
    @EJB
    private PoblacionesFacade poblacionesFacade;
    @EJB
    private ProvinciasFacade provinciasFacade;
    @EJB
    private CallespoblacionesFacade callespoblacionesFacade;
    @EJB 
    private ClientesFacade clientesFacade;
    /**
     * Creates a new instance of EditarBean
     */
     private List<String> prov;
     private List<String> pob;
     private List<String> call;
     
    public List<String> getPob() {
        return pob;
    }

    public List<String> getCall() {
        return call;
    }
    
    public EditarBean() {
        mPob=true;
        mCall=true;
    }
    @PostConstruct
    public void iniciar(){
        
        prov=new ArrayList();
        for(Provincias p:provinciasFacade.findAll()){
            prov.add(p.getCodigoprovincia().toString());
        }
        pob=new ArrayList();
        for(Poblaciones p:poblacionesFacade.findAll()){
            pob.add(p.getCodigopoblacion());
        }
        call=new ArrayList();
        for(Callespoblaciones p : callespoblacionesFacade.findAll()){
            call.add(p.getIdcalle().toString());
        }
        //pob=poblacionesFacade.findAll();
        //call=callespoblacionesFacade.findAll();
        /*for(Poblaciones p : poblacionesFacade.findAll()){
            
        }*/
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getProv() {
        return prov;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getMetros() {
        return metros;
    }

    public void setMetros(int metros) {
        this.metros = metros;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCodigopoblacion() {
        return codigopoblacion;
    }

    public void setCodigopoblacion(String codigopoblacion) {
        this.codigopoblacion = codigopoblacion;
    }

    public String getCodigoprovincia() {
        return codigoprovincia;
    }

    public void setCodigoprovincia(String codigoprovincia) {
        this.codigoprovincia = codigoprovincia;
    }

    public String editar(){
        Clientes c =clientesFacade.find(cb.getCs().getCodigo());
        c.setCalle(callespoblacionesFacade.find(new Integer(calle)));
        c.setCodigopoblacion(poblacionesFacade.find(codigopoblacion));
        c.setCodigoprovincia(provinciasFacade.find(new Integer(codigoprovincia)));
        
        clientesFacade.edit(c);
        
        return "index";
       
    }
    public void someaction(AjaxBehaviorEvent event) {
       Collection<Poblaciones> cp=provinciasFacade.find(new Integer(codigoprovincia)).getPoblacionesCollection();
       pob=new ArrayList();
       for(Poblaciones p: cp){
           pob.add(p.getPoblacion());
       }
       mPob=false;
    }
    public void someaction1(AjaxBehaviorEvent event) {
        mCall=false;
    }
}
