/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.ejb.UsuarioFacade;
import app.entity.Usuario;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Gabi
 */
@Named(value = "editarUsuarioBean")
@RequestScoped
public class editarUsuarioBean {

    @EJB
    private UsuarioFacade usuarioFacade;
    @Inject
    private LoginBean log;

    private String operacion = null;
  
   private  int seleccion = 0;
    private String nCuenta = null, dni = null , usuario = null , contrasena = null , nombre = null;
    private  String apellidos = null , domicilio = null , telefono = null , email = null;
     
    private Usuario user;
    /**
     * Creates a new instance of editarUsuario
     */
    public editarUsuarioBean() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getnCuenta() {
        return nCuenta;
    }

    public void setnCuenta(String nCuenta) {
        this.nCuenta = nCuenta;
    }
    
       public String editarBorrar(){
            seleccion = new Integer(nCuenta);
             user = this.usuarioFacade.find(seleccion);
            
         if(operacion.equals("1")){  //Editar
          if(!dni.equals("")){
            user.setDni(dni);
          }  
          if(!usuario.equals("")){
            user.setAlias(usuario);
          }
          if(!nombre.equals("")){
              user.setNombre(nombre);
          }
           if(!apellidos.equals("")){
               user.setApellidos(apellidos);
           }
            if(!domicilio.equals("")){
               user.setDomicilio(domicilio);
           } if(!telefono.equals("")){
               user.setTeléfono(new Integer(telefono));
           } if(!email.equals("")){
               user.setEmail(email);
           }
            this.usuarioFacade.edit(user);
            log.refresh();
        }else if (operacion.equals("-1")){
           this.usuarioFacade.remove(user);
           log.refresh();
        }
         return "operacionCompletada.xhtml";
}
}
