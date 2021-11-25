package com.example.iury.livroapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.example.iury.livroapp.R;

public class SessaoEmprestimo extends AppCompatActivity {
    // Declarando variáveis
    Button button_add_emprestimo, button_list_emprestimos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessao_emprestimo);
        setTitle("Sessão empréstimo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Atribuindo os ID dos objetos do layout nas variáveis declaradas
        button_add_emprestimo = findViewById(R.id.button_add_emprestimo);
        button_list_emprestimos = findViewById(R.id.button_list_emprestimos);
        // Botão para a página Cadastrar empréstimo
        button_add_emprestimo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_add_emprestimo = new Intent(getApplicationContext(), CadastrarEmprestimo.class);
                startActivity(tela_add_emprestimo);
            }
        });
        // Botão para a página Listar empréstimos
        button_list_emprestimos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_list_emprestimos = new Intent(getApplicationContext(), ListarEmprestimos.class);
                startActivity(tela_list_emprestimos);
            }
        });
    }
}
