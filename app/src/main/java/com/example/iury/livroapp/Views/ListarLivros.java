package com.example.iury.livroapp.Views;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.iury.livroapp.SQLite.Conexao;
import com.example.iury.livroapp.Adapters.LivroAdapter;
import com.example.iury.livroapp.Models.Livro;
import com.example.iury.livroapp.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ListarLivros extends AppCompatActivity {

    // RecyclerView serve para armazenar um enorme contâiner de dados
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_livros);
        setTitle("Listar livros");

        recyclerView = findViewById(R.id.recyclerView); // Atribuindo os ID dos objetos do layout nas variáveis declaradas
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Organiza itens em uma lista
        recyclerView.setHasFixedSize(true); // Faz com o RecyclerView mantenha o mesmo tamanho

        Conexao conexao = new Conexao(this); // Criando instância da classe Conexao
        List<Livro> livros = conexao.ReadLivros(); // É atribuida a função de listagem de livros
        // Verifica se existem registros de livros
        if (livros.size() > 0){
            LivroAdapter livroadapter = new LivroAdapter(livros,ListarLivros.this);
            recyclerView.setAdapter(livroadapter);
        } else {
            Toast.makeText(this, "Não existem livros no banco de dados.", Toast.LENGTH_SHORT).show();
        }
    }
}