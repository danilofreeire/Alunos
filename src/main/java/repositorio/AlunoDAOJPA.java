/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;

import dominio.Aluno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import interfaces.AlunoDAO;



/**
 *
 * @author DANILO
 */
public class AlunoDAOJPA implements AlunoDAO{
    
    
    
    public AlunoDAOJPA() {
    }
    
    
    @Override
    public void adicionarAluno(String mat, Aluno aluno, List<Aluno> a) {
       
        EntityManagerFactory emf = null;
        EntityManager em = null;
        
        try{
            emf = Persistence.createEntityManagerFactory("exemplo-jpa");
            em = emf.createEntityManager();

            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            
        }catch(PersistenceException e){
            System.out.println("ERROR "+ e.getMessage());

        }finally {
            if (em != null) {
                em.close(); 
            }
            if (emf != null) {
                emf.close();
            }
        }
        
        
      
        
    }
    @Override
    public List<Aluno> ListarAlunos(){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        List<Aluno> alunos = new ArrayList<>();
        
        try{
            emf = Persistence.createEntityManagerFactory("exemplo-jpa");
            em = emf.createEntityManager();
            em.getTransaction().begin();
            alunos = em.createQuery("from Aluno", Aluno.class).getResultList();
            em.getTransaction().commit();

        
        }catch(PersistenceException e){
            System.out.println("ERROR "+ e.getMessage());
        }finally {
            if (em != null) {
                em.close(); 
            }
            if (emf != null) {
                emf.close();
            }
        }
        
        return alunos;
        
    }
    
    @Override
    public void removerAluno (String mat) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
       
        try {
            emf = Persistence.createEntityManagerFactory("exemplo-jpa");
            em = emf.createEntityManager();
            
            em.getTransaction().begin();
            Aluno aluno = em.createQuery("SELECT a FROM Aluno a WHERE a.matricula = :matricula", Aluno.class).setParameter("matricula", mat).getSingleResult();
            em.remove(aluno);
            em.getTransaction().commit();
    
        } catch(PersistenceException e) {
            System.out.println("ERROR "+ e.getMessage());
        } finally {
            if (em != null) {
                em.close(); 
            }
            if (emf != null) {
                emf.close();
            }
        }
            
    }
    
}
