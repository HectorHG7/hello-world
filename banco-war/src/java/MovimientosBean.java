/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.ejb.MovimientoFacade;
import app.ejb.UsuarioFacade;
import app.entity.Movimiento;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Gabi
 */
@Named(value = "movimientosBean")
@Dependent
public class MovimientosBean {

    @EJB
    private MovimientoFacade movimientoFacade;

    @EJB
    private UsuarioFacade usuarioFacade;

    private  List<Movimiento> mov;
    
    private String cuenta,operacion,cantidad, entidad, fecha;
    /**
     * Creates a new instance of MovimientosBean
     */
    public MovimientosBean() {
    }

    public List<Movimiento> getMov() {
        return mov;
    }

    public void setMov(List<Movimiento> mov) {
        this.mov = mov;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    @PostConstruct 
    public void init (){
        this.mov = this.movimientoFacade.findAll();
    }
}
