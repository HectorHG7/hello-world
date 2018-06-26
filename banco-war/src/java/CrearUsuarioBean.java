/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.ejb.EmpleadoFacade;
import app.ejb.UsuarioFacade;
import app.entity.Empleado;
import app.entity.Usuario;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author Hector
 */
@Named(value = "crearUsuarioBean")
@RequestScoped
public class CrearUsuarioBean {
    
    @EJB
    UsuarioFacade usuarioFacade;
     @EJB
     EmpleadoFacade empleadoFacade;
    
    @Inject
    private LoginBean log;     
    private Integer cuenta, telefonoParam;//Se autoincrementa en la bbdd cuando el valor es 0.
    private String dniParam,usuarioParam, contrasenaParam, nombreParam, apellidosParam, domicilioParam,  emailParam;
    private boolean message;
    /**
     * Creates a new instance of CrearUsuarioBean
     */
    public CrearUsuarioBean() {
        message = false;
    }
    @PostConstruct
    public void setPagRetorno(){
        if(!FacesContext.getCurrentInstance().getViewRoot().getViewId().equals("/operacionCompletada.xhtml"))
        log.setPagRetornoEmpelado("openTab('click','nuevoUsuario')");
    }

    public boolean isMessage() {
        return message;
    }
    public String getDniParam() {
        return dniParam;
    }

    public void setDniParam(String dniParam) {
        this.dniParam = dniParam;
    }

    public String getUsuarioParam() {
        return usuarioParam;
    }

    public void setUsuarioParam(String usuarioParam) {
        this.usuarioParam = usuarioParam;
    }

    public String getContrasenaParam() {
        return contrasenaParam;
    }

    public void setContrasenaParam(String contrasenaParam) {
        this.contrasenaParam = contrasenaParam;
    }

    public String getNombreParam() {
        return nombreParam;
    }

    public void setNombreParam(String nombreParam) {
        this.nombreParam = nombreParam;
    }

    public String getApellidosParam() {
        return apellidosParam;
    }

    public void setApellidosParam(String apellidosParam) {
        this.apellidosParam = apellidosParam;
    }

    public String getDomicilioParam() {
        return domicilioParam;
    }

    public void setDomicilioParam(String domicilioParam) {
        this.domicilioParam = domicilioParam;
    }

    public Integer getTelefonoParam() {
        return telefonoParam;
    }

    public void setTelefonoParam(Integer telefonoParam) {
        this.telefonoParam = telefonoParam;
    }

    public String getEmailParam() {
        return emailParam;
    }

    public void setEmailParam(String emailParam) {
        this.emailParam = emailParam;
    }

    public Integer getCuenta() {
        return cuenta;
    }
    
      public void validateUsuario(FacesContext context,
                                 UIComponent componentToValidate,
                                 Object value)      throws ValidatorException {
    
      if (usuarioFacade.isUser((String)value)) {
      FacesMessage errorMessage = new FacesMessage("El usuario ya existe");
      errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
      throw new ValidatorException(errorMessage);
    }
  }
       public void validateDni(FacesContext context,
                                 UIComponent componentToValidate,
                                 Object value)      throws ValidatorException {
    
      if (usuarioFacade.buscarDni((String)value)!=null) {
      FacesMessage errorMessage = new FacesMessage("El dni ya existe");
      errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
      throw new ValidatorException(errorMessage);
    }
  }
    
    public String crearUsuario(){
        Usuario cliente=new Usuario(0,dniParam,contrasenaParam,usuarioParam);
       cliente.setNombre(nombreParam);
       cliente.setApellidos(apellidosParam);
       cliente.setEmail(emailParam);
       cliente.setTeléfono(new Integer(telefonoParam));
       cliente.setDomicilio(domicilioParam);
       Empleado em =empleadoFacade.find(new Integer(log.getUsername()));
       cliente.setEmpleadoId(em);
       usuarioFacade.create(cliente);
       cuenta=usuarioFacade.buscarDni(dniParam).getNCuenta();
       message=true;
       log.refresh();
       return ("operacionCompletada");
    }
    
}
