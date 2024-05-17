package application;

import dominio.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;

public class Program {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager em = emf.createEntityManager();

        Aluno a1 = new Aluno("123", "Dainilo", 13,"023003");
/*
        em.getTransaction().begin();
        em.persist(a1);
        em.getTransaction().commit();
*/
        Aluno a2 = em.find(Aluno.class, "123");
        em.getTransaction().begin();
        em.remove(a2);
        em.getTransaction().commit();
        System.out.println("Pronto!");

        em.close();
        emf.close();

    }
}
