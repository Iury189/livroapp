package com.example.iury.livroapp.Views;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.iury.livroapp.SQLite.Conexao;
import com.example.iury.livroapp.Models.Pessoa;
import com.example.iury.livroapp.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class CadastrarPessoa extends AppCompatActivity {
    // Declarando variáveis
    Button button_insert_pessoa, button_list_pessoas;
    EditText editNome_pessoa, editEmail_pessoa, editTelefone_pessoa;
    AlertDialog.Builder alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pessoa);
        setTitle("Cadastrar pessoa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Atribuindo os ID dos objetos do layout nas variáveis declaradas
        alerta = new AlertDialog.Builder(this);
        button_insert_pessoa = findViewById(R.id.button_insert_pessoa);
        button_list_pessoas = findViewById(R.id.button_list_pessoas);
        editNome_pessoa = findViewById(R.id.editNome_pessoa);
        editEmail_pessoa = findViewById(R.id.editEmail_pessoa);
        editTelefone_pessoa = findViewById(R.id.editTelefone_pessoa);
        editTelefone_pessoa.setTransformationMethod(null);
        // Máscara telefone
        editTelefone_pessoa.addTextChangedListener(new MaskTextWatcher(editTelefone_pessoa, new SimpleMaskFormatter("(NN) NNNN-NNNN")));
        // Botão cadastrar pessoa (possui AlertDialog para confirmar a operação)
        button_insert_pessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome_pessoa = editNome_pessoa.getText().toString();
                String email_pessoa = editEmail_pessoa.getText().toString();
                String telefone_pessoa = editTelefone_pessoa.getText().toString();
                alerta.setIcon(R.drawable.insert);
                alerta.setTitle("Confirmação de cadastro");
                alerta.setMessage("Deseja cadastrar uma nova pessoa?");
                alerta.setCancelable(true);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (nome_pessoa.isEmpty() || (email_pessoa.isEmpty() ||
                                !Patterns.EMAIL_ADDRESS.matcher(email_pessoa).matches()) || telefone_pessoa.length() != 14) {
                            Toast.makeText(CadastrarPessoa.this,"Nenhum campo pode ficar vazio " +
                                    "ou preenchido de forma incorreta!",Toast.LENGTH_SHORT).show();
                        } else {
                            Conexao conexao = new Conexao(CadastrarPessoa.this);
                            Pessoa pessoa = new Pessoa(nome_pessoa,email_pessoa,telefone_pessoa);
                            conexao.InsertPessoa(pessoa);
                            Toast.makeText(CadastrarPessoa.this, nome_pessoa + " foi adicionado(a) " +
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
        // Botão para a página de Listar pessoas
        button_list_pessoas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_list_pessoas = new Intent(getApplicationContext(), ListarPessoas.class);
                startActivity(tela_list_pessoas);
            }
        });
    }
}
