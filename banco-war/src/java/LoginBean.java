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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Hector
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{
    
    @EJB
    EmpleadoFacade empleadoFacade;
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    MovimientoFacade movimientoFacade;
    
    private String username;
    private String password;
    private String pagRetornoEmpelado;
    List<Usuario> usuarios;
/**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        pagRetornoEmpelado="openTab('click','operaciones')";
    }

    public String getPagRetornoEmpelado() {
        return pagRetornoEmpelado;
    }

    public void setPagRetornoEmpelado(String pagRetornoEmpelado) {
        this.pagRetornoEmpelado = pagRetornoEmpelado;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void refresh(){
        usuarios=usuarioFacade.findAll();
    }
    
    public String avance(){
        try{
            if(usuarioFacade.loginOK(username, password)) return "usuario";
            else if(empleadoFacade.loginOK(new Integer(username),password)){
                  usuarios=usuarioFacade.findAll();
                return("empleado");
            }
        }catch(NumberFormatException e){
            return "";
        }         
        return ""; 
    }   
}
