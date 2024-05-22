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
import java.util.Comparator;



/**
 *
 * @author DANILO
 */
public class AlunoDAOSQL implements AlunoDAO{
    
    
    
    public AlunoDAOSQL() {
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
            //peguei o aluno a partir da matricula
            Aluno aluno = em.find(Aluno.class, mat);
            //removi o aluno
            em.getTransaction().begin();
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
    
    @Override
    public List<Aluno> pesquisarAluno(String mat) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        List<Aluno> alunos = new ArrayList<>();
            
        try {
            emf = Persistence.createEntityManagerFactory("exemplo-jpa");
            em = emf.createEntityManager();
            em.getTransaction().begin();
            
            Aluno aluno = em.find(Aluno.class, mat);
            
            if (aluno != null) {
                alunos.add(aluno);
            }
            
            em.getTransaction().commit();
            
        } catch(PersistenceException e) {
            System.out.println("ERROR "+ e.getMessage());
            
            if (em != null && em.getTransaction().isActive()) {
               em.getTransaction().rollback();
            }
            
        } finally {
            
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
    public List<Aluno> velhoNovoAluno() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        List<Aluno> alunos = new ArrayList<>();
        List<Aluno> resultadoBusca = new ArrayList<>();

        try {
            emf = Persistence.createEntityManagerFactory("exemplo-jpa");
            em = emf.createEntityManager();
            
            em.getTransaction().begin();
            alunos = em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
            em.getTransaction().commit();
            
            if (!alunos.isEmpty()) {
                Aluno maisVelho = alunos.stream().filter(a -> a.getDataNas() != null).min(Comparator.comparing(Aluno::getDataNas)).orElse(null);
                Aluno maisNovo = alunos.stream().filter(a -> a.getDataNas() != null).max(Comparator.comparing(Aluno::getDataNas)).orElse(null);
                
                if (maisVelho != null) resultadoBusca.add(maisVelho);
                if (maisNovo != null && !maisNovo.equals(maisVelho)) resultadoBusca.add(maisNovo);
            }
            
            
        } catch (PersistenceException e) {
            System.out.println("ERROR "+ e.getMessage());

           if (em != null && em.getTransaction().isActive()) {
              em.getTransaction().rollback();
            }
           
            e.printStackTrace();

        } finally {
           if (em != null) {
                em.close(); 
           }
           
           if (emf != null) {
                emf.close();
           } 
        }
        
        return resultadoBusca;          
        
    }

}
