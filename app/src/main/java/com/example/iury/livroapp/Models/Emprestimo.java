package com.example.iury.livroapp.Models;
// Classe Empréstimo
public class Emprestimo {
    // Atributos
    int id_emprestimo;
    String i_dono, i_livro, i_pessoa, data_emprestimo, data_devolucao, hora_devolucao;
    // Métodos getters e setters
    public int getId_emprestimo() { return id_emprestimo; }

    public void setId_emprestimo(int id_emprestimo) { this.id_emprestimo = id_emprestimo; }

    public String getI_dono() { return i_dono; }

    public void setI_dono(String i_dono) { this.i_dono = i_dono; }

    public String getI_livro() { return i_livro; }

    public void setI_livro(String i_livro) { this.i_livro = i_livro; }

    public String getI_pessoa() { return i_pessoa; }

    public void setI_pessoa(String i_pessoa) { this.i_pessoa = i_pessoa; }

    public String getData_emprestimo() { return data_emprestimo; }

    public void setData_emprestimo(String data_emprestimo) { this.data_emprestimo = data_emprestimo; }

    public String getData_devolucao() { return data_devolucao; }

    public void setData_devolucao(String data_devolucao) { this.data_devolucao = data_devolucao; }

    public String getHora_devolucao() { return hora_devolucao; }

    public void setHora_devolucao(String hora_devolucao) { this.hora_devolucao = hora_devolucao; }

    // Construtor para listagem
    public Emprestimo(int id_emprestimo, String i_dono, String i_livro,
                      String i_pessoa, String data_emprestimo, String data_devolucao, String hora_devolucao) {
        this.id_emprestimo = id_emprestimo;
        this.i_dono = i_dono;
        this.i_livro = i_livro;
        this.i_pessoa = i_pessoa;
        this.data_emprestimo = data_emprestimo;
        this.data_devolucao = data_devolucao;
        this.hora_devolucao = hora_devolucao;
    }
    // Construtor para atualização/inserção
    public Emprestimo(String i_dono, String i_livro, String i_pessoa, String data_devolucao, String hora_devolucao) {
        this.i_dono = i_dono;
        this.i_livro = i_livro;
        this.i_pessoa = i_pessoa;
        this.data_devolucao = data_devolucao;
        this.hora_devolucao = hora_devolucao;
    }
}