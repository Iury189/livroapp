package com.example.iury.livroapp.Views;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;
import com.example.iury.livroapp.SQLite.Conexao;
import com.example.iury.livroapp.Models.Livro;
import com.example.iury.livroapp.R;

public class CadastrarLivro extends AppCompatActivity {
    // Declarando variáveis
    Button button_insert_livro, button_list_livros;
    EditText editTitulo_livro, editAutor_livro, editEditora_livro, editPaginas_livro, editAno_publicacao;
    AutoCompleteTextView autoCategoria_livro;
    String[] Categoria_livro;
    AlertDialog.Builder alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_livro);
        setTitle("Cadastrar livro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Atribuindo os ID dos objetos do layout nas variáveis declaradas
        alerta = new AlertDialog.Builder(this);
        button_insert_livro = findViewById(R.id.button_insert_livro);
        button_list_livros = findViewById(R.id.button_list_livros);
        editTitulo_livro = findViewById(R.id.editTitulo_livro);
        editAutor_livro = findViewById(R.id.editAutor_livro);
        autoCategoria_livro = findViewById(R.id.autoCategoria_livro);
        editEditora_livro = findViewById(R.id.editEditora_livro);
        editPaginas_livro = findViewById(R.id.editPaginas_livro);
        editAno_publicacao = findViewById(R.id.editAno_publicacao);
        // Trecho para o campo da categoria do livro pegar valores do array em string.xml
        Categoria_livro = getResources().getStringArray(R.array.categoria_livro);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Categoria_livro);
        autoCategoria_livro.setThreshold(0);
        autoCategoria_livro.setAdapter(adapter);
        autoCategoria_livro.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                autoCategoria_livro.showDropDown();
            }
        });
        // Botão cadastrar livro (possui AlertDialog para confirmar a operação)
        button_insert_livro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String titulo_livro = editTitulo_livro.getText().toString();
                String autor_livro = editAutor_livro.getText().toString();
                String categoria_livro = autoCategoria_livro.getText().toString();
                String editora_livro = editEditora_livro.getText().toString();
                String paginas_livro = editPaginas_livro.getText().toString();
                String ano_publicacao = editAno_publicacao.getText().toString();
                alerta.setIcon(R.drawable.insert);
                alerta.setTitle("Confirmação de cadastro");
                alerta.setMessage("Deseja cadastrar um novo livro?");
                alerta.setCancelable(true);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (titulo_livro.isEmpty() || autor_livro.isEmpty() || categoria_livro.isEmpty()
                                || editora_livro.isEmpty() || paginas_livro.isEmpty()
                                || paginas_livro.equals("0") || ano_publicacao.isEmpty() || ano_publicacao.equals("0")) {
                            Toast.makeText(CadastrarLivro.this,"Nenhum campo pode ficar vazio ou preenchido " +
                                    "de forma incorreta!",Toast.LENGTH_SHORT).show();
                        } else {
                            Conexao conexao = new Conexao(CadastrarLivro.this);
                            Livro livro = new Livro(titulo_livro,autor_livro,categoria_livro,
                                    editora_livro,paginas_livro,ano_publicacao);
                            conexao.InsertLivro(livro);
                            Toast.makeText(CadastrarLivro.this,"Livro " + titulo_livro +
                                    " foi adicionado com sucesso!",Toast.LENGTH_SHORT).show();
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
        // Botão para ir a página de Listar livros
        button_list_livros.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_list_livros = new Intent(getApplicationContext(), ListarLivros.class);
                startActivity(tela_list_livros);
            }
        });
    }
}