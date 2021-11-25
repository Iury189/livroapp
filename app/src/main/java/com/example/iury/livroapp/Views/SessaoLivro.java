package com.example.iury.livroapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.example.iury.livroapp.R;

public class SessaoLivro extends AppCompatActivity {
    // Declarando variáveis
    Button button_add_livro, button_list_livros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessao_livro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Sessão livro");
        // Atribuindo os ID dos objetos do layout nas variáveis declaradas
        button_add_livro = findViewById(R.id.button_add_livro);
        button_list_livros = findViewById(R.id.button_list_livros);
        // Botão para ir a página Cadastrar livro
        button_add_livro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_add_livro = new Intent(getApplicationContext(), CadastrarLivro.class);
                startActivity(tela_add_livro);
            }
        });
        // Botão para a ir página Listar livros
        button_list_livros.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_list_livros = new Intent(getApplicationContext(), ListarLivros.class);
                startActivity(tela_list_livros);
            }
        });
    }
}