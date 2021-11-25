package com.example.iury.livroapp.Models;
// Classe Dono
public class Dono {
    // Atributos
    int id_dono;
    String nome_dono, email_dono, telefone_dono;
    // Métodos getters e setters
    public int getId_dono() { return id_dono; }

    public void setId_dono(int id_dono) { this.id_dono = id_dono; }

    public String getNome_dono() { return nome_dono; }

    public void setNome_dono(String nome_dono) { this.nome_dono = nome_dono; }

    public String getEmail_dono() { return email_dono; }

    public void setEmail_dono(String email_dono) { this.email_dono = email_dono; }

    public String getTelefone_dono() { return telefone_dono; }

    public void setTelefone_dono(String telefone_dono) { this.telefone_dono = telefone_dono; }

    // Construtor para listagem
    public Dono(int id_dono, String nome_dono, String email_dono, String telefone_dono) {
        this.id_dono = id_dono;
        this.nome_dono = nome_dono;
        this.email_dono = email_dono;
        this.telefone_dono = telefone_dono;
    }
    // Construtor para inserção/atualização
    public Dono(String nome_dono, String email_dono, String telefone_dono) {
        this.nome_dono = nome_dono;
        this.email_dono = email_dono;
        this.telefone_dono = telefone_dono;
    }
}