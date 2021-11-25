package com.example.iury.livroapp.Models;
// Classe Livro
public class Livro {
    // Atributos
    int id_livro;
    String titulo_livro, autor_livro, categoria_livro, editora_livro, paginas_livro, ano_publicacao;
    // Métodos getters e setters
    public int getId_livro() { return id_livro; }

    public void setId_livro(int id_livro) { this.id_livro = id_livro; }

    public String getTitulo_livro() { return titulo_livro; }

    public void setTitulo_livro(String titulo_livro) { this.titulo_livro = titulo_livro; }

    public String getAutor_livro() { return autor_livro; }

    public void setAutor_livro(String autor_livro) { this.autor_livro = autor_livro; }

    public String getCategoria_livro() { return categoria_livro; }

    public void setCategoria_livro(String categoria_livro) { this.categoria_livro = categoria_livro; }

    public String getEditora_livro() { return editora_livro; }

    public void setEditora_livro(String editora_livro) { this.editora_livro = editora_livro; }

    public String getPaginas_livro() { return paginas_livro; }

    public void setPaginas_livro(String paginas_livro) { this.paginas_livro = paginas_livro; }

    public String getAno_publicacao() { return ano_publicacao; }

    public void setAno_publicacao(String ano_publicacao) { this.ano_publicacao = ano_publicacao; }

    // Construtor para listagem
    public Livro(int id_livro, String titulo_livro, String autor_livro, String categoria_livro,
                 String editora_livro, String paginas_livro, String ano_publicacao) {
        this.id_livro = id_livro;
        this.titulo_livro = titulo_livro;
        this.autor_livro = autor_livro;
        this.categoria_livro = categoria_livro;
        this.editora_livro = editora_livro;
        this.paginas_livro = paginas_livro;
        this.ano_publicacao = ano_publicacao;
    }
    // Construtor para atualização/inserção
    public Livro(String titulo_livro, String autor_livro, String categoria_livro,
                 String editora_livro, String paginas_livro, String ano_publicacao) {
        this.titulo_livro = titulo_livro;
        this.autor_livro = autor_livro;
        this.categoria_livro = categoria_livro;
        this.editora_livro = editora_livro;
        this.paginas_livro = paginas_livro;
        this.ano_publicacao = ano_publicacao;
    }
}