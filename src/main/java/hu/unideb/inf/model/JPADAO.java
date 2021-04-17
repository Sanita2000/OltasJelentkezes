/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author karal
 */
public class JPADAO implements DAO{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void save(Object o) {
        entityManager.getTransaction().begin();
        entityManager.persist(o);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Object o) {
        entityManager.getTransaction().begin();
        entityManager.merge(o);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Object o) {
        entityManager.getTransaction().begin();
        entityManager.remove(o);
        entityManager.getTransaction().commit();
    }
    
    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }  

    @Override
    public List<Orvos> getAllOrvos() {
        TypedQuery<Orvos> query = entityManager.createQuery("SELECT a FROM Orvos a", Orvos.class);
        List<Orvos> orvosok = query.getResultList();
        return orvosok;
    }

    @Override
    public List<OrvosBeosztas> GetOrvosBeosztas(Orvos o) {
        TypedQuery<OrvosBeosztas> query = entityManager.createQuery("SELECT b FROM OrvosBeosztas b WHERE b.orvos.ID = " + o.getID(), OrvosBeosztas.class);
        //query.setParameter("id", o.getID());
        System.out.println(o.getID());
        List<OrvosBeosztas> beosztas = query.getResultList();
        return beosztas;
    }

    @Override
    public Vakcina GetVakcinaById(int id) {
        TypedQuery<Vakcina> query = entityManager.createQuery("SELECT a FROM Vakcina a WHERE ID = " + id, Vakcina.class);
        return query.getResultList().get(0);
    }

    @Override
    public Orvos GetOrvosById(int id) {
        TypedQuery<Orvos> o_query = entityManager.createQuery("SELECT a FROM Orvos a WHERE ID = " + id, Orvos.class);
        return o_query.getSingleResult();
    }

    @Override
    public List<OltasEsemeny> GetUserOltasEsemenyei(int userid) {
        TypedQuery<OltasEsemeny> query = entityManager.createQuery("SELECT a FROM OltasEsemeny a WHERE user_id = " + userid, OltasEsemeny.class);
        return query.getResultList();
    }

    @Override
    public Szemely GetUserById(int userid) {
        TypedQuery<Szemely> o_query = entityManager.createQuery("SELECT a FROM Szemely a WHERE ID = " + userid, Szemely.class);
        return o_query.getSingleResult();
    }
   
}
