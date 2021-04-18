/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf;

import hu.unideb.inf.model.DAO;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.OltasEsemeny;
import hu.unideb.inf.model.Orvos;
import hu.unideb.inf.model.OrvosBeosztas;
import hu.unideb.inf.model.Szemely;
import hu.unideb.inf.model.Vakcina;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



/**
 *
 * @author Tamás Ádám
 */
public class TablaFeltoltes extends JPADAO{
    public static void feltolt()
    {
        JPADAO dao = new JPADAO();
        
        Szemely bacsi=new Szemely();
        bacsi.setNev("Bacsikateszt");
        bacsi.setNem(Szemely.NemTipus.FERFI);
        bacsi.setTAJ(772722010);
        bacsi.setSzuletesiDatum(new GregorianCalendar(1999,02,02));
        
        dao.save(bacsi);
        //--------------------------------
        
        Orvos szurkalo = new Orvos();
        szurkalo.setNev("Dr. Ügyes Zsolt");
        szurkalo.setErtekeles((float) 10.0);
        
        dao.save(szurkalo);
        /*
        OrvosBeosztas szurkalo_1 = new OrvosBeosztas();
        //LocalDateTime.parse("2018-12-03T12:39:10");
        szurkalo_1.setKezdesIdo(LocalDateTime.parse("2018-12-03T12:39:10"));
        szurkalo_1.setVegzesIdo(LocalDateTime.parse("2018-12-03T14:39:10"));

        
        dao.save(szurkalo_1);
        */
        //--------------------------------------------
        
        Orvos szurkalo2 = new Orvos();
        szurkalo2.setNev("Dr. Szuri Káta");
        szurkalo2.setErtekeles((float) 5.0);
        
        dao.save(szurkalo2);
        /*
        OrvosBeosztas szurkalo_2 = new OrvosBeosztas();
        szurkalo_2.setKezdesIdo(LocalDateTime.now().withHour(10).withMinute(0).withSecond(0));
        szurkalo_2.setVegzesIdo(LocalDateTime.MAX);        
        
        dao.save(szurkalo_2);
        */
        //-------------------------------------------
        
        Orvos szurkalo3 = new Orvos();
        szurkalo3.setNev("Dr. Ollókezű Edward");
        szurkalo3.setErtekeles((float) 7.0);
        
        dao.save(szurkalo3);
        /*
        OrvosBeosztas szurkalo_3 = new OrvosBeosztas();
        szurkalo_3.setKezdesIdo(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0));
        szurkalo_3.setVegzesIdo(LocalDateTime.MAX);        
        
        dao.save(szurkalo_3);
        */
        //-----------------------------------------
        
        Orvos szurkalo4 = new Orvos();
        szurkalo4.setNev("Dr. Padlógáz");
        szurkalo4.setErtekeles((float) 6.3);
        
        dao.save(szurkalo4);
        /*
        OrvosBeosztas szurkalo_4 = new OrvosBeosztas();
        szurkalo_4.setKezdesIdo(LocalDateTime.now().withHour(14).withMinute(0).withSecond(0));
        szurkalo_4.setVegzesIdo(LocalDateTime.MAX);        
        
        dao.save(szurkalo_4);
        */
        //-----------------------------------------------
        
        Orvos szurkalo5 = new Orvos();
        szurkalo5.setNev("Dr. Harry Potter");
        szurkalo5.setErtekeles((float) 9.0);
        
        dao.save(szurkalo5);
        /*
        OrvosBeosztas szurkalo_5 = new OrvosBeosztas();
        szurkalo_5.setKezdesIdo(LocalDateTime.now().withHour(16).withMinute(0).withSecond(0));
        szurkalo_5.setVegzesIdo(LocalDateTime.MAX);        
        
        dao.save(szurkalo_5);
        */
        //---------------------------------------------------
        
        Orvos szurkalo6 = new Orvos();
        szurkalo6.setNev("Dr. Lassúkezű Dénes");
        szurkalo6.setErtekeles((float) 2.0);
        
        dao.save(szurkalo6);
        /*
        OrvosBeosztas szurkalo_6 = new OrvosBeosztas();
        szurkalo_6.setKezdesIdo(LocalDateTime.now().withHour(18).withMinute(0).withSecond(0));
        szurkalo_6.setVegzesIdo(LocalDateTime.MAX);       
        
        
        dao.save(szurkalo_6);
        */
        /*
        OltasEsemeny esemeny = new OltasEsemeny();
        esemeny.setIdopont(LocalDateTime.parse("2021-04-04T10:30:00"));
        dao.save(esemeny);
        
        
        OltasEsemeny esemeny2 = new OltasEsemeny();
        esemeny2.setIdopont(LocalDateTime.parse("2021-04-04T11:00:00"));
        dao.save(esemeny2);
        
        OltasEsemeny esemeny3 = new OltasEsemeny();
        esemeny3.setIdopont(LocalDateTime.parse("2021-04-04T11:30:00"));
        dao.save(esemeny3);
        
        OltasEsemeny esemeny4 = new OltasEsemeny();
        esemeny4.setIdopont(LocalDateTime.parse("2021-04-04T12:00:00"));
        dao.save(esemeny4);
        OltasEsemeny esemeny5 = new OltasEsemeny();
        esemeny5.setIdopont(LocalDateTime.parse("2021-04-04T12:30:00"));
        dao.save(esemeny5);
        
        OltasEsemeny esemeny6 = new OltasEsemeny();
        esemeny6.setIdopont(LocalDateTime.parse("2021-04-04T13:00:00"));
        dao.save(esemeny6);
        
        OltasEsemeny esemeny7 = new OltasEsemeny();
        esemeny7.setIdopont(LocalDateTime.parse("2021-04-04T13:15:00"));
        dao.save(esemeny7);
        OltasEsemeny esemeny8 = new OltasEsemeny();
        esemeny8.setIdopont(LocalDateTime.parse("2021-04-04T13:30:00"));
        dao.save(esemeny8);
        OltasEsemeny esemeny9 = new OltasEsemeny();
        esemeny9.setIdopont(LocalDateTime.parse("2021-04-04T13:45:00"));
        dao.save(esemeny9);
        OltasEsemeny esemeny10 = new OltasEsemeny();
        esemeny10.setIdopont(LocalDateTime.parse("2021-04-04T14:30:00"));
        dao.save(esemeny10);
        OltasEsemeny esemeny11 = new OltasEsemeny();
        esemeny11.setIdopont(LocalDateTime.parse("2021-04-04T15:30:00"));
        dao.save(esemeny11);
        OltasEsemeny esemeny12 = new OltasEsemeny();
        esemeny12.setIdopont(LocalDateTime.parse("2021-04-04T17:30:00"));
        dao.save(esemeny12);
        */
        Vakcina vakcina1 = new Vakcina();
        
        vakcina1.setNev("Oltas1");
        vakcina1.setLeiras("Oltas1 leiras");
        dao.save(vakcina1);
        
        Vakcina vakcina2 = new Vakcina();
        
        vakcina2.setNev("Oltas2");
        vakcina2.setLeiras("Oltas2 leiras");
        dao.save(vakcina2);
        
        Vakcina vakcina3 = new Vakcina();
        
        vakcina3.setNev("Oltas3");
        vakcina3.setLeiras("Oltas3 leiras");
        dao.save(vakcina3);
        
        Vakcina vakcina4 = new Vakcina();
        
        vakcina4.setNev("Oltas4");
        vakcina4.setLeiras("Oltas4 leiras");
        dao.save(vakcina4);
        
        Vakcina vakcina5 = new Vakcina();
        
        vakcina5.setNev("Oltas5");
        vakcina5.setLeiras("Oltas5 leiras");
        dao.save(vakcina5);
        
        Vakcina vakcina6 = new Vakcina();
        
        vakcina6.setNev("Oltas6");
        vakcina6.setLeiras("Oltas6 leiras");
        dao.save(vakcina6);
        
        Vakcina vakcina7 = new Vakcina();
        
        vakcina7.setNev("Oltas7");
        vakcina7.setLeiras("Oltas7 leiras");
        dao.save(vakcina7);
        
        Vakcina vakcina8 = new Vakcina();
        
        vakcina8.setNev("Oltas8");
        vakcina8.setLeiras("Oltas8 leiras");
        dao.save(vakcina8);
        
        Vakcina vakcina9 = new Vakcina();
        
        vakcina9.setNev("Oltas9");
        vakcina9.setLeiras("Oltas9 leiras");
        dao.save(vakcina9);
        
        Vakcina vakcina10 = new Vakcina();
        
        vakcina10.setNev("Oltas10");
        vakcina10.setLeiras("Oltas10 leiras");
        dao.save(vakcina10);
        
        Vakcina vakcina11 = new Vakcina();
        
        vakcina11.setNev("Oltas11");
        vakcina11.setLeiras("Oltas11 leiras");
        dao.save(vakcina11);
        
        Vakcina vakcina12 = new Vakcina();
        
        vakcina12.setNev("Oltas12");
        vakcina12.setLeiras("Oltas12 leiras");
        dao.save(vakcina12);
        
        Vakcina vakcina13 = new Vakcina();
        
        vakcina13.setNev("Oltas13");
        vakcina13.setLeiras("Oltas13 leiras");
        dao.save(vakcina13);
        
        Vakcina vakcina14 = new Vakcina();
        
        vakcina14.setNev("Oltas14");
        vakcina14.setLeiras("Oltas14 leiras");
        dao.save(vakcina14);
        
        Vakcina vakcina15 = new Vakcina();
        
        vakcina15.setNev("Oltas15");
        vakcina15.setLeiras("Oltas15 leiras");
        dao.save(vakcina15);
        
        Vakcina vakcina16 = new Vakcina();
        
        vakcina16.setNev("Oltas16");
        vakcina16.setLeiras("Oltas16 leiras");
        dao.save(vakcina16);

     
        
    }
}
