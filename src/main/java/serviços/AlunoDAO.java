/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package serviços;

import dominio.Aluno;
import exceptions.AlunoException;
import java.util.List;

/**
 *
 * @author DANILO
 */
public interface AlunoDAO {
    

    public void adicionar(String mat, Aluno aluno, List<Aluno> a)throws AlunoException;
    public List<Aluno> ListarAlunos();
}
