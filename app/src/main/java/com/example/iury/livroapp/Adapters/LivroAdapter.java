package com.example.iury.livroapp.Adapters;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.iury.livroapp.R;
import com.example.iury.livroapp.SQLite.Conexao;
import com.example.iury.livroapp.Models.Livro;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// Classe LivroAdapter vai definir como os dados serão exibidas na listagem de elementos
public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.ViewHolder> {
    // Elementos de alguns componenetes sendo declarados
    List<Livro> livros;
    Context context;
    Conexao conexao;
    // Construtor
    public LivroAdapter(List<Livro> livros, Context context) {
        this.livros = livros;
        this.context = context;
        conexao = new Conexao(context);
    }
    // Método para criar um novo item
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_view_livros,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    // Método para exibir um item ao usuário
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Livro c_livro = livros.get(position);
        holder.textId_livro.setText(Integer.toString(c_livro.getId_livro()));
        holder.editTitulo_livro.setText(c_livro.getTitulo_livro());
        holder.editAutor_livro.setText(c_livro.getAutor_livro());
        holder.autoCategoria_livro.setText(c_livro.getCategoria_livro());
        holder.editEditora_livro.setText(c_livro.getEditora_livro());
        holder.editPaginas_livro.setText(c_livro.getPaginas_livro());
        holder.editAno_publicacao.setText(c_livro.getAno_publicacao());
        // Botão atualizar livro (possui AlertDialog para confirmar a operação)
        holder.button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setIcon(R.drawable.update);
                alerta.setTitle("Confirmação de atualização");
                alerta.setMessage("Deseja atualizar as informações de " + c_livro.getTitulo_livro() + "?");
                alerta.setCancelable(true);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String titulo_livro = holder.editTitulo_livro.getText().toString();
                        String autor_livro = holder.editAutor_livro.getText().toString();
                        String categoria_livro = holder.autoCategoria_livro.getText().toString();
                        String editora_livro = holder.editEditora_livro.getText().toString();
                        String paginas_livro = holder.editPaginas_livro.getText().toString();
                        String ano_publicacao = holder.editAno_publicacao.getText().toString();
                        if (titulo_livro.isEmpty() || autor_livro.isEmpty() || categoria_livro.isEmpty()
                                || editora_livro.isEmpty() || paginas_livro.isEmpty()
                                || paginas_livro.equals("0") || ano_publicacao.isEmpty() || ano_publicacao.equals("0")) {
                            Toast.makeText(context,"Nenhum campo com as informações do livro " + c_livro.getTitulo_livro() +
                                    " podem ficar vazias ou preenchidas de forma incorreta!",Toast.LENGTH_LONG).show();
                        } else {
                            conexao.UpdateLivro(new Livro(c_livro.getId_livro(),titulo_livro,autor_livro,
                                    categoria_livro,editora_livro,paginas_livro,ano_publicacao));
                            notifyDataSetChanged();
                            Toast.makeText(context,"As informações do livro " + c_livro.getTitulo_livro() +
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
        // Botão deletar livro (possui AlertDialog para confirmar a operação)
        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setIcon(R.drawable.deletar);
                alerta.setTitle("Confirmação de exclusão");
                alerta.setMessage("Deseja excluir " + c_livro.getTitulo_livro() + "?");
                alerta.setCancelable(true);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        conexao.DeleteLivro(c_livro.getId_livro());
                        livros.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, c_livro.getTitulo_livro() +
                                " foi excluído com sucesso!",Toast.LENGTH_SHORT).show();
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
        return livros.size();
    }
    // ViewHolder serve para armazenar as views
    public class ViewHolder extends RecyclerView.ViewHolder{
        // Declarando variavéis
        TextView textId_livro;
        EditText editTitulo_livro, editAutor_livro, editEditora_livro, editPaginas_livro, editAno_publicacao;
        Button button_update, button_delete;
        AutoCompleteTextView autoCategoria_livro;
        String[] Categoria_livro;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Atribuindo os ID dos objetos do layout nas variáveis declaradas
            textId_livro = itemView.findViewById(R.id.textId_livro);
            editTitulo_livro = itemView.findViewById(R.id.editTitulo_livro);
            editAutor_livro = itemView.findViewById(R.id.editAutor_livro);
            autoCategoria_livro = itemView.findViewById(R.id.autoCategoria_livro);
            editEditora_livro = itemView.findViewById(R.id.editEditora_livro);
            editPaginas_livro = itemView.findViewById(R.id.editPaginas_livro);
            editAno_publicacao = itemView.findViewById(R.id.editAno_publicacao);
            editEditora_livro.setTransformationMethod(null);
            editPaginas_livro.setTransformationMethod(null);
            button_update = itemView.findViewById(R.id.button_update_livro);
            button_delete = itemView.findViewById(R.id.button_delete_livro);
            // Trecho para o campo da categoria do livro pegar valores do array em string.xml
            Categoria_livro = itemView.getResources().getStringArray(R.array.categoria_livro);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(itemView.getContext(),android.R.layout.simple_list_item_1, Categoria_livro);
            autoCategoria_livro.setThreshold(0);
            autoCategoria_livro.setAdapter(adapter);
            autoCategoria_livro.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    autoCategoria_livro.showDropDown();
                }
            });
        }
    }
}
