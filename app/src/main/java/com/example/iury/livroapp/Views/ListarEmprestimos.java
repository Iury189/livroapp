package com.example.iury.livroapp.Views;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import com.example.iury.livroapp.SQLite.Conexao;
import com.example.iury.livroapp.Adapters.EmprestimoAdapter;
import com.example.iury.livroapp.Models.Emprestimo;
import com.example.iury.livroapp.R;
import java.util.List;

public class ListarEmprestimos extends AppCompatActivity {

    // RecyclerView serve para armazenar um enorme contâiner de dados
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_emprestimos);
        setTitle("Listar empréstimos");

        recyclerView = findViewById(R.id.recyclerView); // Atribuindo os ID dos objetos do layout nas variáveis declaradas
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Organiza itens em uma lista
        recyclerView.setHasFixedSize(true); // Faz com o RecyclerView mantenha o mesmo tamanho

        Conexao conexao = new Conexao(this); // Criando instância da classe Conexao
        List<Emprestimo> emprestimos = conexao.ReadEmprestimos(); // É atribuida a função de listagem de empréstimos
        // Verifica se existem registros de empréstimos
        if (emprestimos.size() > 0){
            EmprestimoAdapter emprestimoadapter = new EmprestimoAdapter(emprestimos,ListarEmprestimos.this);
            recyclerView.setAdapter(emprestimoadapter);
        } else {
            Toast.makeText(this, "Não existem empréstimos no banco de dados.", Toast.LENGTH_SHORT).show();
        }
    }
}