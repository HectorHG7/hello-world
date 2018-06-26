/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.ejb.EmpleadoFacade;
import app.ejb.MovimientoFacade;
import app.ejb.UsuarioFacade;
import app.entity.Movimiento;
import app.entity.Usuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.DecimalMin;

/**
 *
 * @author Hector
 */

@Named(value = "crearOperacionBean")
@RequestScoped
public class CrearOperacionBean{
    /**
     * Creates a new instance of CrearOperacionBean
     */
    
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    EmpleadoFacade empleadoFacade;
    @EJB
    MovimientoFacade movimientoFacade;
    @Inject
    private LoginBean login;
    
    @DecimalMin("0.00")
    private Double cantidadParam=null;
    
    private Integer operacionParam=null, cuentaParam = null;
    private String conceptoParam=null,  entidadParam=null, descripcionParam=null;
    private Date fecha=null;
    private Boolean message;
    
    
    public CrearOperacionBean() {
        fecha = Calendar.getInstance().getTime();
        message=false;
    }
      @PostConstruct
    public void setPagRetorno(){
        login.setPagRetornoEmpelado("openTab('click','operaciones')");
    }

    public Boolean getMessage() {
        return message;
    }

    public Integer getCuentaParam() {
        return cuentaParam;
    }

    public void setCuentaParam(Integer cuentaParam) {
        this.cuentaParam = cuentaParam;
    }

    public MovimientoFacade getMovimientoFacade() {
        return movimientoFacade;
    }

    public Double getCantidadParam() {
        return cantidadParam;
    }

    public void setCantidadParam(Double cantidadParam) {
        this.cantidadParam = cantidadParam;
    }

    public String getConceptoParam() {
        return conceptoParam;
    }

    public void setConceptoParam(String conceptoParam) {
        this.conceptoParam = conceptoParam;
    }

    public String getEntidadParam() {
        return entidadParam;
    }

    public void setEntidadParam(String entidadParam) {
        this.entidadParam = entidadParam;
    }

    public String getDescripcionParam() {
        return descripcionParam;
    }

    public void setDescripcionParam(String descripcionParam) {
        this.descripcionParam = descripcionParam;
    }
    private void clear(){
    operacionParam=null;
    cuentaParam = null;
    cantidadParam=null;
    conceptoParam=null;
    entidadParam=null;
    descripcionParam=null;
    fecha=null;
    }
    public void validateCuenta(FacesContext context,
                                 UIComponent componentToValidate,
                                 Object value)      throws ValidatorException {
    
    if (usuarioFacade.find((Integer)value)==null) {
      FacesMessage errorMessage = new FacesMessage("El numero de cuenta no existe");
      errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
      throw new ValidatorException(errorMessage);
    }
  }
   
    public String hacerOperacion(){
        Usuario user =usuarioFacade.find(cuentaParam);           
        if(user!=null){
         operacionParam = new Integer(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("operacion"));
         if(!(operacionParam==-1 && cantidadParam > movimientoFacade.balanceNCuenta(cuentaParam))){
             Movimiento mov=new Movimiento(0,conceptoParam, fecha, entidadParam, operacionParam, cantidadParam);
             mov.setDescripcion(descripcionParam);
             mov.setUsuarioNCuenta(user);
             mov.setEmpleadoId(empleadoFacade.find(new Integer(login.getUsername())));
             movimientoFacade.create(mov);
             clear();
   
             return("operacionCompletada");
         }
        } 
        message=true;
        return ("");
    }
}
