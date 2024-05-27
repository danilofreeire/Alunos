/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 *
 * @author DANILO
 */
@Entity
public class Aluno implements Serializable {

    private static final long serialVersion = 1L;

    
    @Id
    private String matricula;
    private String nome;
    private Integer idade;
    private LocalDate dataNas;
    private String telefone;
    private String cpf;

    public Aluno() {

    }

    public Aluno(String matricula, String nome, Integer idade, LocalDate dataNas, String telefone, String cpf) {
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
        this.dataNas = dataNas;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public LocalDate getDataNas() {
        return dataNas;
    }

    public void setDataNas(LocalDate dataNas) {
        this.dataNas = dataNas;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Aluno{" + "matricula=" + matricula + ", nome=" + nome + ", dataNas=" + dataNas + ", telefone=" + telefone + ", cpf=" + cpf + '}';
    }

}
