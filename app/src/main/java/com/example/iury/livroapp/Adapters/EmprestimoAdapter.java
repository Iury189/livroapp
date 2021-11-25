package com.example.iury.livroapp.Adapters;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.iury.livroapp.R;
import com.example.iury.livroapp.SQLite.Conexao;
import com.example.iury.livroapp.Models.Emprestimo;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// Classe EmprestimoAdapter vai definir como os dados serão exibidas na listagem de elementos
public class EmprestimoAdapter extends RecyclerView.Adapter<EmprestimoAdapter.ViewHolder> {
    // Elementos de alguns componenetes sendo declarados
    List<Emprestimo> emprestimos;
    Context context;
    Conexao conexao;
    // Construtor
    public EmprestimoAdapter(List<Emprestimo> emprestimos, Context context) {
        this.emprestimos = emprestimos;
        this.context = context;
        conexao = new Conexao(context);
    }
    // Método para criar um novo item
    @NonNull
    @Override
    public EmprestimoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_view_emprestimos,parent,false);
        EmprestimoAdapter.ViewHolder viewHolder = new EmprestimoAdapter.ViewHolder(view);
        return viewHolder;
    }
    // Método para exibir um item ao usuário
    @Override
    public void onBindViewHolder(@NonNull final EmprestimoAdapter.ViewHolder holder, final int position) {
        final Emprestimo c_emprestimo = emprestimos.get(position);
        holder.textId_emprestimo.setText(Integer.toString(c_emprestimo.getId_emprestimo()));
        holder.autoDono.setText(c_emprestimo.getI_dono());
        holder.autoLivro.setText(c_emprestimo.getI_livro());
        holder.autoPessoa.setText(c_emprestimo.getI_pessoa());
        holder.editData_emprestimo.setText(c_emprestimo.getData_emprestimo());
        holder.editData_devolucao.setText(c_emprestimo.getData_devolucao());
        holder.editHora_devolucao.setText(c_emprestimo.getHora_devolucao());
        // Botão deletar empréstimo (possui AlertDialog para confirmar a operação)
        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setIcon(R.drawable.deletar);
                alerta.setTitle("Confirmação de exclusão");
                alerta.setMessage("Deseja excluir o empréstimo do livro " + c_emprestimo.getI_livro()
                        + " entre " + c_emprestimo.getI_dono() + " e " + c_emprestimo.getI_pessoa() + " realizado em "
                        + c_emprestimo.getData_emprestimo() + "?");
                alerta.setCancelable(true);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        conexao.DeleteEmprestimo(c_emprestimo.getId_emprestimo());
                        emprestimos.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Empréstimo do livro " + c_emprestimo.getI_livro() + " entre "
                                + c_emprestimo.getI_dono() + " e " + c_emprestimo.getI_pessoa() +
                                " foi excluído(a) com sucesso!",Toast.LENGTH_SHORT).show();
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
    }
    // Contabiliza a quantidade de linhas na widget
    @Override
    public int getItemCount() {
        return emprestimos.size();
    }
    // ViewHolder serve para armazenar as views
    public class ViewHolder extends RecyclerView.ViewHolder{
        // Declarando variavéis
        TextView textId_emprestimo;
        EditText editData_emprestimo, editData_devolucao, editHora_devolucao;
        AutoCompleteTextView autoDono, autoLivro, autoPessoa;
        Button button_delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Atribuindo os ID dos objetos do layout nas variáveis declaradas
            textId_emprestimo = itemView.findViewById(R.id.textId_emprestimo);
            autoDono = itemView.findViewById(R.id.autoDono);
            autoLivro = itemView.findViewById(R.id.autoLivro);
            autoPessoa = itemView.findViewById(R.id.autoPessoa);
            editData_emprestimo = itemView.findViewById(R.id.editData_emprestimo);
            editData_devolucao = itemView.findViewById(R.id.editData_devolucao);
            editHora_devolucao = itemView.findViewById(R.id.editHora_devolucao);
            button_delete = itemView.findViewById(R.id.button_delete_emprestimo);
        }
    }
}