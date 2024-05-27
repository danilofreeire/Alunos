/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dominio.Aluno;
import exceptions.AlunoException;
import java.util.List;

/**
 *
 * @author DANILO
 */
public interface AlunoDAO {

    public void adicionarAluno(String mat, Aluno aluno) throws AlunoException;

    public List<Aluno> ListarAlunos();

    public void removerAluno(String mat) throws AlunoException;

    public Aluno pesquisarAluno(String mat);

    public List<Aluno> velhoNovoAluno();

    public boolean salvarCSV(List<Aluno> alunos);

}
