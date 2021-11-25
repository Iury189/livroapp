package com.example.iury.livroapp.Views;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.iury.livroapp.SQLite.Conexao;
import com.example.iury.livroapp.Adapters.DonoAdapter;
import com.example.iury.livroapp.Models.Dono;
import com.example.iury.livroapp.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ListarDonos extends AppCompatActivity {

    // RecyclerView serve para armazenar um enorme contâiner de dados
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_donos);
        setTitle("Listar donos");

        recyclerView = findViewById(R.id.recyclerView); // Atribuindo os ID dos objetos do layout nas variáveis declaradas
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Organiza itens em uma lista
        recyclerView.setHasFixedSize(true); // Faz com o RecyclerView mantenha o mesmo tamanho

        Conexao conexao = new Conexao(this); // Criando instância da classe Conexao
        List<Dono> donos = conexao.ReadDonos(); // É atribuida a função de listagem de donos
        // Verifica se existem registros de donos
        if (donos.size() > 0){
            DonoAdapter donoadapter = new DonoAdapter(donos,ListarDonos.this);
            recyclerView.setAdapter(donoadapter);
        } else {
            Toast.makeText(this, "Não existem donos no banco de dados.", Toast.LENGTH_SHORT).show();
        }
    }
}