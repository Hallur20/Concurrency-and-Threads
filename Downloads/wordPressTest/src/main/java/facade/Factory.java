package facade;

import entities.Test;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class Factory {

    EntityManagerFactory emf;

    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public String addTest(String name, int age) {
        try {
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            Test t = new Test();
            t.setName(name);
            t.setAge(age);
            em.persist(t);
            em.getTransaction().commit();
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public List<Test> getTest() {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Test> q = em.createQuery("select e from Test e", Test.class);
            return q.getResultList();

        } finally {
            em.close();
        }
    }
}
