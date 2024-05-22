/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicos;

import dominio.Aluno;
import exceptions.AlunoException;
import java.time.LocalDate;
import java.util.List;
import repositorio.AlunoDAOSQL;

/**
 *
 * @author DANILO
 */
public class AlunoServico {

    
    private static AlunoDAOSQL aad=new AlunoDAOSQL();
    
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
    
    public void removerAluno (String mat) throws AlunoException {
        List<Aluno> alunos = aad.ListarAlunos();
        boolean i = false;
        
        for (Aluno x : alunos) {
            if (x.getMatricula().equals(mat)) {
                i = true;
                break;
            }
        }
        
        if(!i) {
            throw new AlunoException("Matricula não existe.\n");
        }
        
        aad.removerAluno(mat);
    }
    
    
    public List<Aluno> pesquisarAluno(String mat) throws AlunoException {
        List<Aluno> alunos = aad.ListarAlunos();
        
        if (alunos == null) {
            throw new AlunoException("Não existem alunos cadastrados.\n");
        }
      
        return aad.pesquisarAluno(mat);
    }

    public List<Aluno> velhoNovoAluno() throws AlunoException {
        // Se tiver apenas 1 aluno, a pesquisa de velhoNovoAluno ainda funciona
        
        List<Aluno> alunos = aad.ListarAlunos();

        if (alunos.isEmpty()) {
            throw new AlunoException("Não existem alunos cadastrados.\n");

        }
       
        return aad.velhoNovoAluno();
    }

    
}
