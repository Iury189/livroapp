package com.example.iury.livroapp.Views;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.iury.livroapp.SQLite.Conexao;
import com.example.iury.livroapp.Models.Pessoa;
import com.example.iury.livroapp.Adapters.PessoaAdapter;
import com.example.iury.livroapp.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ListarPessoas extends AppCompatActivity {

    // RecyclerView serve para armazenar um enorme contâiner de dados
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pessoas);
        setTitle("Listar pessoas");

        recyclerView = findViewById(R.id.recyclerView); // Atribuindo os ID dos objetos do layout nas variáveis declaradas
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Organiza itens em uma lista
        recyclerView.setHasFixedSize(true); // Faz com o RecyclerView mantenha o mesmo tamanho

        Conexao conexao = new Conexao(this); // Criando instância da classe Conexao
        List<Pessoa> pessoas = conexao.ReadPessoas(); // É atribuida a função de listagem de pessoas
        // Verifica se existem registros de pessoas
        if (pessoas.size() > 0){
            PessoaAdapter pessoaadapter = new PessoaAdapter(pessoas,ListarPessoas.this);
            recyclerView.setAdapter(pessoaadapter);
        } else {
            Toast.makeText(this, "Não existem pessoas no banco de dados.", Toast.LENGTH_SHORT).show();
        }
    }
}