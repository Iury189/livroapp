package com.example.iury.livroapp.SQLite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import com.example.iury.livroapp.Models.Dono;
import com.example.iury.livroapp.Models.Emprestimo;
import com.example.iury.livroapp.Models.Livro;
import com.example.iury.livroapp.Models.Pessoa;
import java.util.List;
import java.util.ArrayList;
import androidx.annotation.Nullable;

// Criação da classe Conexão que cria o banco de dados que também pode se versionar
public class Conexao extends SQLiteOpenHelper {

    private static int versao = 1; // Versão do app
    private static String banco = "livroapp"; // Nome do banco de dados
    private SQLiteDatabase SQLiteBD; // Variável com métodos de manipulação de banco de dados
    // Tabela Dono
    private static final String tabela_dono = "dono";
    public static final String id_dono = "id_dono";
    public static final String nome_dono = "nome_dono";
    public static final String email_dono = "email_dono";
    public static final String telefone_dono = "telefone_dono";
    // Tabela Livro
    private static final String tabela_livro = "livro";
    public static final String id_livro = "id_livro";
    public static final String titulo_livro = "titulo_livro";
    public static final String autor_livro = "autor_livro";
    public static final String categoria_livro = "categoria_livro";
    public static final String editora_livro = "editora_livro";
    public static final String paginas_livro = "paginas_livro";
    public static final String ano_publicacao = "ano_publicacao";
    // Tabela Pessoa
    private static final String tabela_pessoa = "pessoa";
    public static final String id_pessoa = "id_pessoa";
    public static final String nome_pessoa = "nome_pessoa";
    public static final String email_pessoa = "email_pessoa";
    public static final String telefone_pessoa = "telefone_pessoa";
    // Tabela empréstimo
    private static final String tabela_emprestimo = "emprestimo";
    public static final String id_emprestimo = "id_emprestimo";
    public static final String i_dono = "i_dono";
    public static final String i_livro = "i_livro";
    public static final String i_pessoa = "i_pessoa";
    public static final String data_emprestimo = "data_emprestimo";
    public static final String data_devolucao = "data_devolucao";
    public static final String hora_devolucao = "hora_devolucao";
    // Construtor
    public Conexao(@Nullable Context context){
        super(context, banco, null, versao);
    }
    // Criar tabelas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tabela_dono +
                "(" + id_dono + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + nome_dono + " TEXT NOT NULL,"
                + email_dono + " TEXT NOT NULL,"
                + telefone_dono + " TEXT NOT NULL);"
        );
        db.execSQL("CREATE TABLE " + tabela_livro +
                "(" + id_livro + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + titulo_livro + " TEXT NOT NULL,"
                + autor_livro + " TEXT NOT NULL,"
                + categoria_livro + " TEXT NOT NULL,"
                + editora_livro + " TEXT NOT NULL,"
                + paginas_livro + " TEXT NOT NULL,"
                + ano_publicacao + " TEXT NOT NULL);"
        );
        db.execSQL("CREATE TABLE " + tabela_pessoa +
                "(" + id_pessoa + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + nome_pessoa + " TEXT NOT NULL,"
                + email_pessoa + " TEXT NOT NULL UNIQUE,"
                + telefone_pessoa + " TEXT NOT NULL UNIQUE);"
        );
        db.execSQL("CREATE TABLE " + tabela_emprestimo +
                "(" + id_emprestimo + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + i_dono + " TEXT NOT NULL,"
                + i_livro + " TEXT NOT NULL,"
                + i_pessoa + " TEXT NOT NULL,"
                + data_emprestimo + " DATETIME NOT NULL DEFAULT (STRFTIME('%d/%m/%Y %H:%M:%S', 'now', 'localtime')),"
                + data_devolucao + " TEXT NOT NULL,"
                + hora_devolucao + " TEXT NOT NULL);"
        );
    }
    // Atualiza banco
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String pessoa_sql = "DROP TABLE IF EXISTS " + tabela_pessoa;
        String dono_sql = "DROP TABLE IF EXISTS " + tabela_dono;
        String livro_sql = "DROP TABLE IF EXISTS " + tabela_livro;
        String emprestimo_sql = "DROP TABLE IF EXISTS " + tabela_emprestimo;
        db.execSQL(pessoa_sql);
        db.execSQL(dono_sql);
        db.execSQL(livro_sql);
        db.execSQL(emprestimo_sql);
    }
    //--------------------------------- DONO ---------------------------------------------------------
    // Inserir dono
    public void InsertDono(Dono dono){
        ContentValues c1 = new ContentValues();
        c1.put(Conexao.nome_dono, dono.getNome_dono());
        c1.put(Conexao.email_dono, dono.getEmail_dono());
        c1.put(Conexao.telefone_dono, dono.getTelefone_dono());
        SQLiteBD = this.getWritableDatabase();
        SQLiteBD.insert(Conexao.tabela_dono,null,c1);
    }
    // Listar donos
    public List<Dono> ReadDonos(){
        String sql = "SELECT * FROM " + tabela_dono;
        SQLiteBD = this.getReadableDatabase();
        List<Dono> ViewDonos = new ArrayList<>();
        Cursor cursor = SQLiteBD.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            do {
                int id_dono = Integer.parseInt(cursor.getString(0));
                String nome_dono = cursor.getString(1);
                String email_dono = cursor.getString(2);
                String telefone_dono = cursor.getString(3);
                ViewDonos.add(new Dono(id_dono,nome_dono,email_dono,telefone_dono));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ViewDonos;
    }
    // Atualizar dono
    public void UpdateDono(Dono dono){
        ContentValues c1 = new ContentValues();
        c1.put(Conexao.nome_dono, dono.getNome_dono());
        c1.put(Conexao.email_dono, dono.getEmail_dono());
        c1.put(Conexao.telefone_dono, dono.getTelefone_dono());
        SQLiteBD = this.getWritableDatabase();
        SQLiteBD.update(tabela_dono,c1, id_dono + " = ? " , new String[]
                {String.valueOf(dono.getId_dono())});
    }
    // Deletar dono
    public void DeleteDono(int id_dono){
        SQLiteBD = this.getWritableDatabase();
        SQLiteBD.delete(tabela_dono, Conexao.id_dono + " = ? ", new String[]
                {String.valueOf(id_dono)});
    }
    //--------------------------------- LIVRO ---------------------------------------------------------
    // Inserir livro
    public void InsertLivro(Livro livro){
        ContentValues c2 = new ContentValues();
        c2.put(Conexao.titulo_livro, livro.getTitulo_livro());
        c2.put(Conexao.autor_livro, livro.getAutor_livro());
        c2.put(Conexao.categoria_livro, livro.getCategoria_livro());
        c2.put(Conexao.editora_livro, livro.getEditora_livro());
        c2.put(Conexao.paginas_livro, livro.getPaginas_livro());
        c2.put(Conexao.ano_publicacao, livro.getAno_publicacao());
        SQLiteBD = this.getWritableDatabase();
        SQLiteBD.insert(Conexao.tabela_livro,null,c2);
    }
    // Listar livros
    public List<Livro> ReadLivros(){
        String sql = "SELECT * FROM " + tabela_livro;
        SQLiteBD = this.getReadableDatabase();
        List<Livro> ViewLivros = new ArrayList<>();
        Cursor cursor = SQLiteBD.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            do {
                int id_livro = Integer.parseInt(cursor.getString(0));
                String titulo_livro = cursor.getString(1);
                String autor_livro = cursor.getString(2);
                String categoria_livro = cursor.getString(3);
                String editora_livro = cursor.getString(4);
                String paginas_livro = cursor.getString(5);
                String ano_publicacao = cursor.getString(6);
                ViewLivros.add(new Livro(id_livro,titulo_livro,autor_livro,
                        categoria_livro,editora_livro,paginas_livro,ano_publicacao));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ViewLivros;
    }
    // Atualizar livro
    public void UpdateLivro(Livro livro){
        ContentValues c2 = new ContentValues();
        c2.put(Conexao.titulo_livro, livro.getTitulo_livro());
        c2.put(Conexao.autor_livro, livro.getAutor_livro());
        c2.put(Conexao.categoria_livro, livro.getCategoria_livro());
        c2.put(Conexao.editora_livro, livro.getEditora_livro());
        c2.put(Conexao.paginas_livro, livro.getPaginas_livro());
        c2.put(Conexao.ano_publicacao, livro.getAno_publicacao());
        SQLiteBD = this.getWritableDatabase();
        SQLiteBD.update(tabela_livro,c2, id_livro + " = ? " , new String[]
                {String.valueOf(livro.getId_livro())});
    }
    // Deletar livro
    public void DeleteLivro(int id_livro){
        SQLiteBD = this.getWritableDatabase();
        SQLiteBD.delete(tabela_livro, Conexao.id_livro + " = ? ", new String[]
                {String.valueOf(id_livro)});
    }
    //--------------------------------- PESSOA ---------------------------------------------------------
    // Inserir pessoa
    public void InsertPessoa(Pessoa pessoa){
        ContentValues c3 = new ContentValues();
        c3.put(Conexao.nome_pessoa, pessoa.getNome_pessoa());
        c3.put(Conexao.email_pessoa, pessoa.getEmail_pessoa());
        c3.put(Conexao.telefone_pessoa, pessoa.getTelefone_pessoa());
        SQLiteBD = this.getWritableDatabase();
        SQLiteBD.insert(Conexao.tabela_pessoa,null,c3);
    }
    // Listar pessoas
    public List<Pessoa> ReadPessoas(){
        String sql = "SELECT * FROM " + tabela_pessoa;
        SQLiteBD = this.getReadableDatabase();
        List<Pessoa> ViewPessoas = new ArrayList<>();
        Cursor cursor = SQLiteBD.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            do {
                int id_pessoa = Integer.parseInt(cursor.getString(0));
                String nome_pessoa = cursor.getString(1);
                String email_pessoa = cursor.getString(2);
                String telefone_pessoa = cursor.getString(3);
                ViewPessoas.add(new Pessoa(id_pessoa,nome_pessoa,email_pessoa,telefone_pessoa));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ViewPessoas;
    }
    // Atualizar pessoa
    public void UpdatePessoa(Pessoa pessoa){
        ContentValues c3 = new ContentValues();
        c3.put(Conexao.nome_pessoa, pessoa.getNome_pessoa());
        c3.put(Conexao.email_pessoa, pessoa.getEmail_pessoa());
        c3.put(Conexao.telefone_pessoa, pessoa.getTelefone_pessoa());
        SQLiteBD = this.getWritableDatabase();
        SQLiteBD.update(tabela_pessoa,c3, id_pessoa + " = ? " , new String[]
                {String.valueOf(pessoa.getId_pessoa())});
    }
    // Deletar pessoa
    public void DeletePessoa(int id_pessoa){
        SQLiteBD = this.getWritableDatabase();
        SQLiteBD.delete(tabela_pessoa, Conexao.id_pessoa + " = ? ", new String[]
                {String.valueOf(id_pessoa)});
    }
    //--------------------------------- EMPRÉSTIMO ---------------------------------------------------------
    // Inserir empréstimo
    public void InsertEmprestimo(Emprestimo emprestimo){
        ContentValues c4 = new ContentValues();
        c4.put(Conexao.i_dono, emprestimo.getI_dono());
        c4.put(Conexao.i_livro, emprestimo.getI_livro());
        c4.put(Conexao.i_pessoa, emprestimo.getI_pessoa());
        c4.put(Conexao.data_devolucao, emprestimo.getData_devolucao());
        c4.put(Conexao.hora_devolucao, emprestimo.getHora_devolucao());
        SQLiteBD = this.getWritableDatabase();
        SQLiteBD.insert(Conexao.tabela_emprestimo,null, c4);
    }
    // Listar empréstimos
    public List<Emprestimo> ReadEmprestimos(){
        String sql = "SELECT * FROM " + tabela_emprestimo;
        SQLiteBD = this.getReadableDatabase();
        List<Emprestimo> ViewEmprestimos = new ArrayList<>();
        Cursor cursor = SQLiteBD.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            do {
                int id_emprestimo = Integer.parseInt(cursor.getString(0));
                String i_dono = cursor.getString(1);
                String i_livro = cursor.getString(2);
                String i_pessoa = cursor.getString(3);
                String data_emprestimo = cursor.getString(4);
                String data_devolucao = cursor.getString(5);
                String hora_devolucao = cursor.getString(6);
                ViewEmprestimos.add(new Emprestimo(id_emprestimo,i_dono,i_livro,i_pessoa,data_emprestimo,data_devolucao,hora_devolucao));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ViewEmprestimos;
    }
    // Deletar empréstimo
    public void DeleteEmprestimo(int id_emprestimo){
        SQLiteBD = this.getWritableDatabase();
        SQLiteBD.delete(tabela_emprestimo, Conexao.id_emprestimo + " = ? ", new String[]
                {String.valueOf(id_emprestimo)});
    }
    //--------------- POPULAR AUTOCOMPLETETEXTVIEW NO CADASTRO DE EMPRÉSTIMOS ---------------
    // Listar todos os nomes de donos
    public Cursor NomeDonos() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + nome_dono + " FROM " + tabela_dono + " ORDER BY " + id_dono;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
    // Listar todos os nomes de livros
    public Cursor NomeLivros() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + titulo_livro + " FROM " + tabela_livro + " ORDER BY " + id_livro;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
    // Listar todos os nomes de pessoas
    public Cursor NomePessoas() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + nome_pessoa + " FROM " + tabela_pessoa + " ORDER BY " + id_pessoa;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}