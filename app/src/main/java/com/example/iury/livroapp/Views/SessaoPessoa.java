package com.example.iury.livroapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.example.iury.livroapp.R;

public class SessaoPessoa extends AppCompatActivity {
    // Declarando variáveis
    Button button_add_pessoa, button_list_pessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessao_pessoa);
        setTitle("Sessão pessoa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Atribuindo os ID dos objetos do layout nas variáveis declaradas
        button_add_pessoa = findViewById(R.id.button_add_pessoa);
        button_list_pessoas = findViewById(R.id.button_list_pessoas);
        // Botão para ir a página Cadastrar pessoa
        button_add_pessoa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_add_pessoa = new Intent(getApplicationContext(), CadastrarPessoa.class);
                startActivity(tela_add_pessoa);
            }
        });
        // Botão para a ir página Listar pessoas
        button_list_pessoas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_list_pessoas = new Intent(getApplicationContext(), ListarPessoas.class);
                startActivity(tela_list_pessoas);
            }
        });
    }
}
