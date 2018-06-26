/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.ejb.MovimientoFacade;
import app.entity.Movimiento;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Gabi
 */
@Named(value = "editarBorrarOperacion")
@RequestScoped
public class EditarBorrarOperacion {
  @EJB
    private MovimientoFacade movimientoFacade;
    @Inject
    private LoginBean  login;
    
    private String operacion = null ;
    private int seleccion = 0;
    private String codigomov =null;
    private  Movimiento mov = null;
private String cantidad = null,concepto = null,entidad = null,descripcion = null;

    /**
     * Creates a new instance of EditarBorrarOperacion
     */
    public EditarBorrarOperacion() {
    }

    public Movimiento getMov() {
        return mov;
    }

    public void setMov(Movimiento mov) {
        this.mov = mov;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

   

    public String getCodigomov() {
        return codigomov;
    }

    public void setCodigomov(String codigomov) {
        this.codigomov = codigomov;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
        public String editarBorrar(){
            seleccion = new Integer(codigomov);
             mov = this.movimientoFacade.find(seleccion);
            
         if(operacion.equals("1")){  //Editar
          if(!cantidad.equals("")){
            mov.setCantidad(new Double(cantidad));
          }  
          if(!concepto.equals("")){
            mov.setConcepto(concepto);
          }
          if(!entidad.equals("")){
              mov.setEntidad(entidad);
          }
           if(!descripcion.equals("")){
               mov.setDescripcion(descripcion);
           }
            this.movimientoFacade.edit(mov);
        }else if (operacion.equals("-1")){
           this.movimientoFacade.remove(mov);
        }
         return "operacionCompletada.xhtml";
    }
   
         


}
    /**
     * Creates a new instance of EditarBorrarOperacion
     */
   
    

