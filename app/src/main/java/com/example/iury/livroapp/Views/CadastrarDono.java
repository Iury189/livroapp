package com.example.iury.livroapp.Views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;
import com.example.iury.livroapp.SQLite.Conexao;
import com.example.iury.livroapp.Models.Dono;
import com.example.iury.livroapp.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class CadastrarDono extends AppCompatActivity {
    // Declarando variáveis
    Button button_insert_dono, button_list_donos;
    EditText editNome_dono, editEmail_dono, editTelefone_dono;
    AlertDialog.Builder alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_dono);
        setTitle("Cadastrar dono");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Atribuindo os ID dos objetos do layout nas variáveis declaradas
        alerta = new AlertDialog.Builder(this);
        button_insert_dono = findViewById(R.id.button_insert_dono);
        button_list_donos = findViewById(R.id.button_list_donos);
        editNome_dono = findViewById(R.id.editNome_dono);
        editEmail_dono = findViewById(R.id.editEmail_dono);
        editTelefone_dono = findViewById(R.id.editTelefone_dono);
        // Máscara telefone
	editTelefone_dono.addTextChangedListener(new MaskTextWatcher(editTelefone_dono, new SimpleMaskFormatter("(NN) NNNN-NNNN")));
        // Botão cadastrar dono (possui AlertDialog para confirmar a operação)
        button_insert_dono.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String nome_dono = editNome_dono.getText().toString();
                String email_dono = editEmail_dono.getText().toString();
                String telefone_dono = editTelefone_dono.getText().toString();
                alerta.setIcon(R.drawable.insert);
                alerta.setTitle("Confirmação de cadastro");
                alerta.setMessage("Deseja cadastrar um(a) novo(a) dono(a)?");
                alerta.setCancelable(true);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (nome_dono.isEmpty() || (email_dono.isEmpty() ||
                                !Patterns.EMAIL_ADDRESS.matcher(email_dono).matches()) || telefone_dono.length() != 14) {
                            Toast.makeText(CadastrarDono.this,"Nenhum campo pode ficar vazio " +
                                    "ou preenchido de forma incorreta!",Toast.LENGTH_SHORT).show();
                        } else {
                            Conexao conexao = new Conexao(CadastrarDono.this);
                            Dono dono = new Dono(nome_dono,email_dono,telefone_dono);
                            conexao.InsertDono(dono);
                            Toast.makeText(CadastrarDono.this, nome_dono + " foi adicionado(a) " +
                                    "com sucesso!",Toast.LENGTH_SHORT).show();
                            finish(); // Encerra a Activity
                            startActivity(getIntent()); // Começa uma nova Activity
                        }
                    }

                });
                alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alerta.show();
            }
        });
        // Botão para ir a página de Listar donos
        button_list_donos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_list_donos = new Intent(getApplicationContext(), ListarDonos.class);
                startActivity(tela_list_donos);
            }
        });
    }
}
