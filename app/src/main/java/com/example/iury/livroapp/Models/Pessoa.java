package com.example.iury.livroapp.Models;
// Classe Pessoa
public class Pessoa {
    // Atributos
    int id_pessoa;
    String nome_pessoa, email_pessoa, telefone_pessoa;
    // Métodos getters e setters
    public int getId_pessoa() { return id_pessoa; }

    public void setId_pessoa(int id_pessoa) { this.id_pessoa = id_pessoa; }

    public String getNome_pessoa() { return nome_pessoa; }

    public void setNome_pessoa(String nome_pessoa) { this.nome_pessoa = nome_pessoa; }

    public String getEmail_pessoa() { return email_pessoa; }

    public void setEmail_pessoa(String email_pessoa) { this.email_pessoa = email_pessoa; }

    public String getTelefone_pessoa() { return telefone_pessoa; }

    public void setTelefone_pessoa(String telefone_pessoa) { this.telefone_pessoa = telefone_pessoa; }

    // Construtor para listagem
    public Pessoa(int id_pessoa, String nome_pessoa, String email_pessoa, String telefone_pessoa) {
        this.id_pessoa = id_pessoa;
        this.nome_pessoa = nome_pessoa;
        this.email_pessoa = email_pessoa;
        this.telefone_pessoa = telefone_pessoa;
    }
    // Construtor para atualização/inserção
    public Pessoa(String nome_pessoa, String email_pessoa, String telefone_pessoa) {
        this.nome_pessoa = nome_pessoa;
        this.email_pessoa = email_pessoa;
        this.telefone_pessoa = telefone_pessoa;
    }
}