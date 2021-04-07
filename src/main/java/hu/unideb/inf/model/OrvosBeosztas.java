/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import Extensions.Nevesitett_konst;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author admin
 * a tábla egy rekordja egy időintervallumot tartalmaz
 */
@Entity
@Table(name="OrvosBeosztasok")
public class OrvosBeosztas {
    @Id
    @GeneratedValue
    private int ID;
    private LocalDateTime  kezdesIdo;
    private LocalDateTime  vegzesIdo;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    

    public LocalDateTime getKezdesIdo() {
        return kezdesIdo;
    }

    public void setKezdesIdo(LocalDateTime kezdesIdo) {
        this.kezdesIdo = kezdesIdo;
    }

    public LocalDateTime getVegzesIdo() {
        return vegzesIdo;
    }

    public void setVegzesIdo(LocalDateTime vegzesIdo) {
        if (this.kezdesIdo.compareTo(vegzesIdo) < 0)
        {
            this.vegzesIdo = vegzesIdo;
        }
        this.vegzesIdo = this.kezdesIdo.plusHours(Nevesitett_konst.HOURS_ADDED_TO_START);       //hiba esetén a default értéket adjuk a kezdéshez
    }

    
}
