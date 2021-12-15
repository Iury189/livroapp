package com.example.iury.livroapp.Adapters;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.iury.livroapp.R;
import com.example.iury.livroapp.SQLite.Conexao;
import com.example.iury.livroapp.Models.Pessoa;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// Classe PessoaAdapter vai definir como os dados serão exibidas na listagem de elementos
public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.ViewHolder> {
    // Elementos de alguns componenetes sendo declarados
    List<Pessoa> pessoas;
    Context context;
    Conexao conexao;
    // Construtor
    public PessoaAdapter(List<Pessoa> pessoas, Context context) {
        this.pessoas = pessoas;
        this.context = context;
        conexao = new Conexao(context);
    }
    // Método para criar um novo item
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_view_pessoas,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    // Método para exibir um item ao usuário
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Pessoa c_pessoa = pessoas.get(position);
        holder.textId_pessoa.setText(Integer.toString(c_pessoa.getId_pessoa()));
        holder.editNome_pessoa.setText(c_pessoa.getNome_pessoa());
        holder.editEmail_pessoa.setText(c_pessoa.getEmail_pessoa());
        holder.editTelefone_pessoa.setText(c_pessoa.getTelefone_pessoa());
        // Botão atualizar pessoa (possui AlertDialog para confirmar a operação)
        holder.button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setIcon(R.drawable.update);
                alerta.setTitle("Confirmação de atualização");
                alerta.setMessage("Deseja atualizar as informações de " + c_pessoa.getNome_pessoa() + "?");
                alerta.setCancelable(true);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nome_pessoa = holder.editNome_pessoa.getText().toString();
                        String email_pessoa = holder.editEmail_pessoa.getText().toString();
                        String telefone_pessoa = holder.editTelefone_pessoa.getText().toString();
                        if (nome_pessoa.isEmpty() || (email_pessoa.isEmpty() ||
                                !Patterns.EMAIL_ADDRESS.matcher(email_pessoa).matches())
                                || telefone_pessoa.length() != 14){
                            Toast.makeText(context,"Nenhum campo com as informações de " + c_pessoa.getNome_pessoa() +
                                    " podem ficar vazias ou preenchidas de forma incorreta!",Toast.LENGTH_LONG).show();
                        } else {
                            conexao.UpdatePessoa(new Pessoa(c_pessoa.getId_pessoa(),
                                    nome_pessoa, email_pessoa,telefone_pessoa));
                            notifyDataSetChanged();
                            Toast.makeText(context,"As informações de " + c_pessoa.getNome_pessoa() +
                                    " foram atualizadas!",Toast.LENGTH_SHORT).show();
                            ((Activity) context).finish();
                            context.startActivity(((Activity) context).getIntent());
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
        // Botão deletar pessoa (possui AlertDialog para confirmar a operação)
        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setIcon(R.drawable.deletar);
                alerta.setTitle("Confirmação de exclusão");
                alerta.setMessage("Deseja excluir " + c_pessoa.getNome_pessoa() + "?");
                alerta.setCancelable(true);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        conexao.DeletePessoa(c_pessoa.getId_pessoa());
                        pessoas.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, c_pessoa.getNome_pessoa() +
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
        return pessoas.size();
    }
    // ViewHolder serve para armazenar as views
    public class ViewHolder extends RecyclerView.ViewHolder{
        // Declarando variavéis
        Button button_update, button_delete;
        TextView textId_pessoa;
        EditText editNome_pessoa, editEmail_pessoa, editTelefone_pessoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Atribuindo os ID dos objetos do layout nas variáveis declaradas
            textId_pessoa = itemView.findViewById(R.id.textId_pessoa);
            editNome_pessoa = itemView.findViewById(R.id.editNome_pessoa);
            editEmail_pessoa = itemView.findViewById(R.id.editEmail_pessoa);
            editTelefone_pessoa = itemView.findViewById(R.id.editTelefone_pessoa);
            button_delete = itemView.findViewById(R.id.button_delete_pessoa);
            button_update = itemView.findViewById(R.id.button_update_pessoa);
            editTelefone_pessoa.setTransformationMethod(null);
            editTelefone_pessoa.addTextChangedListener(new MaskTextWatcher(editTelefone_pessoa, new SimpleMaskFormatter("(NN) NNNN-NNNN")));
        }
    }
}
