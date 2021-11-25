package com.example.iury.livroapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.example.iury.livroapp.R;

public class SessaoDono extends AppCompatActivity {
    // Declarando variáveis
    Button button_add_dono, button_list_donos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessao_dono);
        setTitle("Sessão dono");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Atribuindo os ID dos objetos do layout nas variáveis declaradas
        button_add_dono = findViewById(R.id.button_add_dono);
        button_list_donos = findViewById(R.id.button_list_donos);
        // Botão para ir a página Cadastrar dono
        button_add_dono.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_add_dono = new Intent(getApplicationContext(), CadastrarDono.class);
                startActivity(tela_add_dono);
            }
        });
        // Botão para ir a página Listar donos
        button_list_donos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_list_donos = new Intent(getApplicationContext(), ListarDonos.class);
                startActivity(tela_list_donos);
            }
        });
    }
}
