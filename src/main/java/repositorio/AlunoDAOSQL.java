package repositorio;

import dominio.Aluno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import interfaces.AlunoDAO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.stream.Collectors;

public class AlunoDAOSQL implements AlunoDAO {

    public AlunoDAOSQL() {
    }

    @Override
    public void adicionarAluno(String mat, Aluno aluno) {

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("exemplo-jpa");
            em = emf.createEntityManager();

            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();

        } catch (PersistenceException e) {
            System.out.println("ERROR " + e.getMessage());

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
    public List<Aluno> ListarAlunos() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        List<Aluno> alunos = new ArrayList<>();

        try {
            emf = Persistence.createEntityManagerFactory("exemplo-jpa");
            em = emf.createEntityManager();
            em.getTransaction().begin();
            alunos = em.createQuery("from Aluno", Aluno.class).getResultList();
            em.getTransaction().commit();

        } catch (PersistenceException e) {
            System.out.println("ERROR " + e.getMessage());
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
    public void removerAluno(String mat) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("exemplo-jpa");
            em = emf.createEntityManager();
            Aluno aluno = em.find(Aluno.class, mat);
            em.getTransaction().begin();
            em.remove(aluno);
            em.getTransaction().commit();

        } catch (PersistenceException e) {
            System.out.println("ERROR " + e.getMessage());
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
    public Aluno pesquisarAluno(String mat) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Aluno aluno = new Aluno();
        try {
            emf = Persistence.createEntityManagerFactory("exemplo-jpa");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            aluno = em.find(Aluno.class, mat);

            em.getTransaction().commit();

        } catch (PersistenceException e) {
            System.out.println("ERROR " + e.getMessage());

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
        return aluno;
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

                if (maisVelho != null) {
                    resultadoBusca.add(maisVelho);
                }
                if (maisNovo != null && !maisNovo.equals(maisVelho)) {
                    resultadoBusca.add(maisNovo);
                }
            }

        } catch (PersistenceException e) {
            System.out.println("ERROR " + e.getMessage());

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

    @Override
    public boolean salvarCSV(List<Aluno> alunos) {
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        boolean sucess = new File("c:\\temp").mkdir();
        File file = new File("c:\\temp\\alunos.csv");

        List<String> dados = alunos.stream()
                .map(a -> a.getMatricula() + ", "
                + a.getNome().toUpperCase() + "     ;"
                + a.getIdade() + ";"
                + a.getDataNas().format(fmt1) + "; "
                + a.getTelefone() + ";"
                + a.getCpf())
                .collect(Collectors.toList());
        String header = "Matricula,Nome,Telefone,CPF,Idade,Data de Nascimento";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(header);
            bw.newLine();
            for (String dado : dados) {
                bw.write(dado);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }
        return true;

    }

}
