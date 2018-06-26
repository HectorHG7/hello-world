/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.Film;
import app.Language;
import app.Rating;
import ejb.FilmFacade;
import ejb.LanguageFacade;
import ejb.RatingFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hector
 */
@Named(value = "formBean")
@RequestScoped
public class formBean {

   
    
    @EJB
    LanguageFacade languageFacade;
    
    @EJB
    RatingFacade ratingFacade;
    
    @EJB
    FilmFacade filmFacade;
    
    private String title;
    private String description;
    private Integer releaseYear;
    private short rentalDuration;
    private String rentalRate;
    private Short fLength;
    private String replacementCost;
    private String specialFeatures;
    private Date lastUpdate;
    private String originalLanguageId;
    private String languageId;
    private String ratingId;
    private Film fm;
    Map<String,String> olang;
    Map<String,String> lang;
    Map<String,String> rat;

    public Film getFm() {
        return fm;
    }

    public void setFm(Film fm) {
        this.fm = fm;
    }

    public Map<String,String> getOlang() {
        return olang;
    }

    public Map<String,String> getLang() {
        return lang;
    }

    public Map<String,String> getRat() {
        return rat;
    }

    public String getOriginalLanguageId() {
        return originalLanguageId;
    }

    public void setOriginalLanguageId(String originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }

    
    /**
     * Creates a new instance of formBean
     */
    public formBean() {
    }
    @PostConstruct
    public void iniciar(){
        olang=new HashMap();
        for(Language l:languageFacade.findAll()){
            olang.put(l.getName(),l.getLanguageId().toString());
        }
        lang=olang;
        
        rat=new HashMap();
        for(Rating r: ratingFacade.findAll()){
            rat.put(r.getDescription(),r.getRatingId().toString());
        }
        
    }
     public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public short getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(short rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public String getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(String rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Short getFLength() {
        return fLength;
    }

    public void setFLength(Short fLength) {
        this.fLength = fLength;
    }

    public String getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(String replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }
/*
    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    
    public Language getOriginalLanguageId() {
        return originalLanguageId;
    }

    public void setOriginalLanguageId(Language originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public Language getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Language languageId) {
        this.languageId = languageId;
    }

    public Rating getRatingId() {
        return ratingId;
    }

    public void setRatingId(Rating ratingId) {
        this.ratingId = ratingId;
    }
    */
    public String goBack(){
        fm=(Film)((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("peli");
        fm.setTitle("HOLAAA");
        filmFacade.edit(fm);
       return "index"; 
    }
}
