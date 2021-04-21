/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author admin
 */
@Entity
@Table(name="Szemelyek")
public class Szemely {
    @Id
    @GeneratedValue
    private int ID;
    private String nev;
    private int TAJ;
    private LocalDate SzuletesiDatum;
    @Enumerated(EnumType.STRING)
    NemTipus nem;

    @OneToMany(mappedBy = "user")    
    public Set<OltasEsemeny> oltasEsemeny = new HashSet<>();
    
    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getTAJ() {
        return TAJ;
    }

    public void setTAJ(int TAJ) {
        this.TAJ = TAJ;
    }

    public LocalDate getSzuletesiDatum() {
        return SzuletesiDatum;
    }

    public void setSzuletesiDatum(LocalDate SzuletesiDatum) {
        this.SzuletesiDatum = SzuletesiDatum;
    }
    
    public NemTipus getNem() {
        return nem;
    }

    public void setNem(NemTipus nem) {
        this.nem = nem;
    }

    public enum NemTipus{
    FERFI,NO;
    }
    
    @OneToMany
    @JoinColumn(name = "user_id")
    Set<OltasEsemeny> beoltas = new HashSet<>();
    
    @OneToMany
    @JoinColumn(name = "user_id")
    Set<VakcinaErtekeles> vakcina_ertekeles = new HashSet<>();
}
