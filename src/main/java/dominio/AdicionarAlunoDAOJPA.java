/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import exceptions.AlunoException;
import java.time.LocalDate;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



/**
 *
 * @author DANILO
 */
public class AdicionarAlunoDAOJPA {
    
    
    
    public AdicionarAlunoDAOJPA() {
    }
    
    public void adicionarAluno(String mat, Aluno aluno, List<Aluno> a) throws AlunoException{
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager em = emf.createEntityManager();
        List<Aluno> alunos = ListarAlunos();
        
        LocalDate atual = LocalDate.now();
        
        if(aluno.getDataNas().getYear() >atual.getYear() || aluno.getDataNas().getMonthValue()>12 
                || aluno.getDataNas().getMonthValue()<=0 || aluno.getDataNas().getDayOfMonth()>31 || aluno.getDataNas().getDayOfMonth()<=0 ){
             throw new AlunoException("Data de Nascimento Invalida \n");

        }
        
        
        for(Aluno x : a){
            if(x.getMatricula().equals(mat)){
                throw new AlunoException("Matricula existe! \n");
            }
        }
        for(Aluno x : alunos){
            if(x.getMatricula().equals(mat)){
                throw new AlunoException("Matricula existe! \n");
            }
        }
      
      
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
        
        
        
        
        em.close();
        emf.close();
        
        
        
    }
    public List<Aluno> ListarAlunos(){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Aluno> alunos = em.createQuery("from Aluno", Aluno.class).getResultList();
        em.getTransaction().commit();

        
        em.close();
        emf.close();
        
        return alunos;
        
    }
    
    
}
