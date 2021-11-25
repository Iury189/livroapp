package com.example.iury.livroapp.Views;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import com.example.iury.livroapp.R;

// Página inicial do app
public class MainActivity extends AppCompatActivity {
    // Declarando variáveis
    Button button_dono, button_livro, button_pessoa, button_emprestimo;
    Toast mensagem_sair;
    private long pressionar_voltar_sair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Início");
        // Atribuindo os ID dos objetos do layout nas variáveis declaradas
        button_dono = findViewById(R.id.button_dono);
        button_livro = findViewById(R.id.button_livro);
        button_pessoa = findViewById(R.id.button_pessoa);
        button_emprestimo = findViewById(R.id.button_emprestimo);
        // Botão para ir a página de sessão Dono
        button_dono.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_sessao_dono = new Intent(getApplicationContext(), SessaoDono.class);
                startActivity(tela_sessao_dono);
            }
        });
        // Botão para ir a página de sessão Livro
        button_livro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_sessao_livro = new Intent(getApplicationContext(), SessaoLivro.class);
                startActivity(tela_sessao_livro);
            }
        });
        // Botão para ir a página de sessão Pessoa
        button_pessoa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_sessao_pessoa = new Intent(getApplicationContext(), SessaoPessoa.class);
                startActivity(tela_sessao_pessoa);
            }
        });
        // Botão para ir a página de sessão Empréstimo
        button_emprestimo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_sessao_emprestimo = new Intent(getApplicationContext(), SessaoEmprestimo.class);
                startActivity(tela_sessao_emprestimo);
            }
        });
    }
    // Trecho para o botão de saída no canto superior direito da tela
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.saida,menu);
        return true;
    }
    // Pergunta sobre a confirmação da saída do app
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int sessao = item.getItemId();
        if (sessao == R.id.tela_saida){
            AlertDialog.Builder alerta_saida = new AlertDialog.Builder(MainActivity.this);
            alerta_saida.setIcon(R.drawable.ic_exit_to_app);
            alerta_saida.setTitle("Finalizar aplicativo");
            alerta_saida.setMessage("Deseja realmente sair do aplicativo?");
            alerta_saida.setCancelable(true);
            alerta_saida.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alerta_saida.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alerta_saida.show();
        }
        return true;
    }
    // Trecho onde sai do aplicativo pressionando o botão de volta na página inicial
    @Override
    public void onBackPressed(){
        if (pressionar_voltar_sair + 2000 > System.currentTimeMillis()){
            mensagem_sair.cancel();
            super.onBackPressed();
            return;
        } else {
            mensagem_sair = Toast.makeText(getBaseContext(), "Pressione novamente para sair.", Toast.LENGTH_SHORT);
            mensagem_sair.show();
        }
        pressionar_voltar_sair = System.currentTimeMillis();
    }
}