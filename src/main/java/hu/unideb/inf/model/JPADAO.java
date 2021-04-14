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
        save(o);
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
        System.out.println("\nOrvosok\n");
        System.out.println(orvosok.size());
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
   
}
