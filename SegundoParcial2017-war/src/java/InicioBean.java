/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.Category;
import app.Film;
import app.FilmActor;
import app.FilmActorPK;
import app.FilmCategory;
import ejb.CategoryFacade;
import ejb.FilmActorFacade;
import ejb.FilmFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Hector
 */
@Named(value = "inicioBean")
@RequestScoped
public class InicioBean implements Serializable{

    /**
     * Creates a new instance of InicioBean
     */
    @EJB
    FilmFacade filmFacade;
    @EJB
    CategoryFacade categoryFacade;
    @EJB
    FilmActorFacade filmActorFacade;
    
   
    private Map<String,String> listcat;
    private String idcateg;
    private Film flm;
    private String actor;
    
    public InicioBean() {
    }
    
    @PostConstruct
    public void init(){
        
        listcat=new HashMap<>();
        
        for(Category c : categoryFacade.findAll()){
            listcat.put(c.getName(), c.getCategoryId().toString());
        }
        idcateg=(String)((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("idcat");
            
       
    }

    public String getActor() {
        return actor;
    }

    public Film getFlm() {
        return flm;
    }

    public void setFlm(Film flm) {
        this.flm = flm;
    }

    public String getIdcateg() {
        return idcateg;
    }

    public void setIdcateg(String idcateg) {
        this.idcateg = idcateg;
    }
    
    public List<Film> getListfilm() {
        
        if(idcateg!=null){
            
            List<Film> lf=new ArrayList();
            for(Film f: filmFacade.findAll()){
                boolean enc=false;
                for(FilmCategory fc: f.getFilmCategoryList()){
                    if(fc.getFilmCategoryPK().getCategoryId()==(short)Short.valueOf(idcateg)){
                        enc=true;
                        break;
                    }
                }
                if(enc)lf.add(f);
            }
        return lf;
        }
            
        return filmFacade.findAll();
    }

    public Map<String, String> getListcat() {
        return listcat;
    }
    
    public void someaction(Film f) {
           filmFacade.remove(f);
    }
    
    public String modifi(Film f){
        HttpSession s=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        s.setAttribute("peli", f);
        s.setAttribute("idcat",idcateg );
        flm=f;
        return "modP";
    }
    
    public String eliminar(Film f){
        filmFacade.remove(f);
        return "index";
    }
  //return new ArrayList(filmFacade.pelisCat(idcateg));  
}
