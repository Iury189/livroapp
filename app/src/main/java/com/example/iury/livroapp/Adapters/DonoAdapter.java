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
import com.example.iury.livroapp.Models.Dono;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// Classe DonoAdapter vai definir como os dados serão exibidas na listagem de elementos
public class DonoAdapter extends RecyclerView.Adapter<DonoAdapter.ViewHolder> {
    // Elementos de alguns componenetes sendo declarados
    List<Dono> donos;
    Context context;
    Conexao conexao;
    // Construtor
    public DonoAdapter(List<Dono> donos, Context context) {
        this.donos = donos;
        this.context = context;
        conexao = new Conexao(context);
    }
    // Método para criar um novo item
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_view_donos,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    // Método para exibir um item ao usuário
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Dono c_dono = donos.get(position);
        holder.textId_dono.setText(Integer.toString(c_dono.getId_dono()));
        holder.editNome_dono.setText(c_dono.getNome_dono());
        holder.editEmail_dono.setText(c_dono.getEmail_dono());
        holder.editTelefone_dono.setText(c_dono.getTelefone_dono());
        // Botão atualizar dono (possui AlertDialog para confirmar a operação)
        holder.button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setIcon(R.drawable.update);
                alerta.setTitle("Confirmação de atualização");
                alerta.setMessage("Deseja atualizar as informações de " + c_dono.getNome_dono() + "?");
                alerta.setCancelable(true);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nome_dono = holder.editNome_dono.getText().toString();
                        String email_dono = holder.editEmail_dono.getText().toString();
                        String telefone_dono = holder.editTelefone_dono.getText().toString();
                        if (nome_dono.isEmpty() || (email_dono.isEmpty() ||
                                !Patterns.EMAIL_ADDRESS.matcher(email_dono).matches())
                                || telefone_dono.length() != 14){
                            Toast.makeText(context,"Nenhum campo com as informações de " + c_dono.getNome_dono() +
                                    " podem ficar vazias ou preenchidas de forma incorreta!",Toast.LENGTH_LONG).show();
                        } else {
                            conexao.UpdateDono(new Dono(c_dono.getId_dono(),nome_dono,email_dono,telefone_dono));
                            notifyDataSetChanged();
                            Toast.makeText(context,"As informações de " + c_dono.getNome_dono() +
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
        // Botão deletar dono (possui AlertDialog para confirmar a operação)
        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setIcon(R.drawable.deletar);
                alerta.setTitle("Confirmação de exclusão");
                alerta.setMessage("Deseja excluir " + c_dono.getNome_dono() + "?");
                alerta.setCancelable(true);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        conexao.DeleteDono(c_dono.getId_dono());
                        donos.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, c_dono.getNome_dono() +
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
        return donos.size();
    }
    // ViewHolder serve para armazenar as views
    public class ViewHolder extends RecyclerView.ViewHolder{
        // Declarando variavéis
        Button button_update, button_delete;
        TextView textId_dono;
        EditText editNome_dono, editEmail_dono, editTelefone_dono;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Atribuindo os ID dos objetos do layout nas variáveis declaradas
            textId_dono = itemView.findViewById(R.id.textId_dono);
            editNome_dono = itemView.findViewById(R.id.editNome_dono);
            editEmail_dono = itemView.findViewById(R.id.editEmail_dono);
            editTelefone_dono = itemView.findViewById(R.id.editTelefone_dono);
            button_delete = itemView.findViewById(R.id.button_delete_dono);
            button_update = itemView.findViewById(R.id.button_update_dono);
            editTelefone_dono.setTransformationMethod(null);
            editTelefone_dono.addTextChangedListener(new MaskTextWatcher(editTelefone_dono, new SimpleMaskFormatter("(NN) NNNN-NNNN")));
        }
    }
}
