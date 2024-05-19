/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicos;

import dominio.Aluno;
import exceptions.AlunoException;
import java.time.LocalDate;
import java.util.List;
import repositorio.AlunoDAOJPA;

/**
 *
 * @author DANILO
 */
public class AlunoServico {

    
    private static AlunoDAOJPA aad=new AlunoDAOJPA();
    
    public AlunoServico() {
        
    }
    
   
    public void adicionar(String mat, Aluno aluno, List<Aluno> a) throws AlunoException{
        
        List<Aluno> alunos = aad.ListarAlunos();
        LocalDate atual = LocalDate.now();
        
        if(aluno.getDataNas().getYear() >atual.getYear() || aluno.getDataNas().getMonthValue()>12 
           || aluno.getDataNas().getMonthValue()<=0 || aluno.getDataNas().getDayOfMonth()>31
           || aluno.getDataNas().getDayOfMonth()<=0 ){
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
        aad.adicionarAluno(mat, aluno, a);
    }

    
 
    public List<Aluno> ListarAlunos() {
        return aad.ListarAlunos();
    }
    
    
    
    
    
    
}
