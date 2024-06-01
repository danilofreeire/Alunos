package servicos;

import dominio.Aluno;
import exceptions.AlunoException;
import java.time.LocalDate;
import java.util.List;
import repositorio.AlunoDAOSQL;

public class AlunoServico {

    private static final AlunoDAOSQL aad = new AlunoDAOSQL();

    public AlunoServico() {

    }

    public void adicionar(String mat, Aluno aluno) throws AlunoException {

        List<Aluno> alunos = aad.ListarAlunos();
        LocalDate atual = LocalDate.now();

        if (aluno.getDataNas().getYear() > atual.getYear() || aluno.getDataNas().getMonthValue() > 12
                || aluno.getDataNas().getMonthValue() <= 0 || aluno.getDataNas().getDayOfMonth() > 31
                || aluno.getDataNas().getDayOfMonth() <= 0) {
            throw new AlunoException("Data de Nascimento Invalida \n");
        }

        for (Aluno x : alunos) {
            if (x.getMatricula().equals(mat)) {
                throw new AlunoException("Matricula existe! \n");
            }
        }
        aad.adicionarAluno(mat, aluno);
    }

    public List<Aluno> ListarAlunos() {
        return aad.ListarAlunos();
    }

    public void removerAluno(String mat) throws AlunoException {
        List<Aluno> alunos = aad.ListarAlunos();
        boolean i = false;

        for (Aluno x : alunos) {
            if (x.getMatricula().equals(mat)) {
                i = true;
                break;
            }
        }

        if (!i) {
            throw new AlunoException("Matricula não existe.\n");
        }

        aad.removerAluno(mat);
    }

    public Aluno pesquisarAluno(String mat) throws AlunoException {
        List<Aluno> alunos = aad.ListarAlunos();

        if (mat == null || mat.isEmpty()) {
            throw new AlunoException("Matricula Invalida.\n");
        }

        if (alunos == null || alunos.isEmpty()) {
            throw new AlunoException("Não existem alunos cadastrados.\n");
        }

        Aluno aluno = aad.pesquisarAluno(mat);
        if (aluno == null) {
            throw new AlunoException("Aluno não encontrado.\n");
        }

        return aluno;
    }

    public List<Aluno> velhoNovoAluno() throws AlunoException {

        List<Aluno> alunos = aad.ListarAlunos();

        if (alunos.isEmpty()) {
            throw new AlunoException("Não existem alunos cadastrados.\n");

        }

        return aad.velhoNovoAluno();
    }

    public void salvarCSV() throws AlunoException {
        List<Aluno> a = aad.ListarAlunos();
        if (!aad.salvarCSV(a)) {
            throw new AlunoException("Não foi possível salvar o CSV.\n");
        }

    }

}
