package com.example.iury.livroapp.Views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.content.Intent;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.example.iury.livroapp.SQLite.Conexao;
import com.example.iury.livroapp.Models.Emprestimo;
import com.example.iury.livroapp.R;
import java.util.ArrayList;
import java.util.Calendar;

public class CadastrarEmprestimo extends AppCompatActivity {
    // Declarando variáveis
    Button button_insert_emprestimo, button_list_emprestimos;
    EditText editData_devolucao, editHora_devolucao;
    AutoCompleteTextView autoDono, autoLivro, autoPessoa;
    Cursor Campo_dono, Campo_livro, Campo_pessoa;
    AlertDialog.Builder alerta;
    int dia, mes, ano, horas, minutos;
    String turno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_emprestimo);
        setTitle("Cadastrar empréstimo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Atribuindo os ID dos objetos do layout nas variáveis declaradas
        alerta = new AlertDialog.Builder(this);
        button_insert_emprestimo = findViewById(R.id.button_insert_emprestimo);
        button_list_emprestimos = findViewById(R.id.button_list_emprestimos);
        autoDono = findViewById(R.id.autoDono);
        autoLivro = findViewById(R.id.autoLivro);
        autoPessoa = findViewById(R.id.autoPessoa);
        autoDono.setKeyListener(null);
        autoLivro.setKeyListener(null);
        autoPessoa.setKeyListener(null);
        // Popular campos autoDono, autoLivro, autoPessoa com registros das tabelas do SQLite
        Conexao conexao = new Conexao(this);
        ArrayList<String> nomes_donos = new ArrayList<>();
        ArrayList<String> titulo_livros = new ArrayList<>();
        ArrayList<String> nomes_pessoas = new ArrayList<>();
        Campo_dono = conexao.NomeDonos();
        Campo_livro = conexao.NomeLivros();
        Campo_pessoa = conexao.NomePessoas();
        while (Campo_dono.moveToNext()) {
            nomes_donos.add(Campo_dono.getString(0));
        }
        while (Campo_livro.moveToNext()) {
            titulo_livros.add(Campo_livro.getString(0));
        }
        while (Campo_pessoa.moveToNext()) {
            nomes_pessoas.add(Campo_pessoa.getString(0));
        }
        Campo_dono.close();
        Campo_livro.close();
        Campo_pessoa.close();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nomes_donos);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titulo_livros);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nomes_pessoas);
        autoDono.setThreshold(0);
        autoLivro.setThreshold(0);
        autoPessoa.setThreshold(0);
        autoDono.setAdapter(adapter1);
        autoLivro.setAdapter(adapter2);
        autoPessoa.setAdapter(adapter3);
        autoDono.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                autoDono.showDropDown();
            }
        });
        autoLivro.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                autoLivro.showDropDown();
            }
        });
        autoPessoa.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                autoPessoa.showDropDown();
            }
        });
        // DatePickerDialog no editData_devolucao
        editData_devolucao = findViewById(R.id.editData_devolucao);
        Calendar calendar = Calendar.getInstance();
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        ano = calendar.get(Calendar.YEAR);
        editData_devolucao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CadastrarEmprestimo.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int ano, int mes, int dia){
                        mes += 1;
                        String data_dev = String.format("%02d/%02d/%04d",dia,mes,ano);
                        editData_devolucao.setText(data_dev);
                    }
                },ano,mes,dia);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        // TimePickerDialog no editHora_devolucao
        editHora_devolucao = findViewById(R.id.editHora_devolucao);
        editHora_devolucao.setOnClickListener(view -> {
            Calendar calendar1 = Calendar.getInstance();
            horas = calendar1.get(Calendar.HOUR_OF_DAY);
            minutos = calendar1.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(CadastrarEmprestimo.this,
                    (timePicker, horas, minutos) -> {
                if (horas <= 12) {
                    turno = "AM";
                } else {
                    turno = "PM";
                }
                editHora_devolucao.setText(String.format("%02d:%02d ", horas, minutos) + turno);
            }, horas, minutos, false);
            timePickerDialog.show();
        });
        // Botão cadastrar empréstimo (possui AlertDialog para confirmar a operação)
        button_insert_emprestimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String i_dono = autoDono.getText().toString();;
                String i_livro = autoLivro.getText().toString();;
                String i_pessoa = autoPessoa.getText().toString();
                String data_devolucao = editData_devolucao.getText().toString();
                String hora_devolucao = editHora_devolucao.getText().toString();
                alerta.setIcon(R.drawable.insert);
                alerta.setTitle("Confirmação de cadastro");
                alerta.setMessage("Deseja cadastrar um novo empréstimo?");
                alerta.setCancelable(true);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (i_dono.isEmpty() || i_livro.isEmpty() ||
                                i_pessoa.isEmpty() || data_devolucao.isEmpty()){
                            Toast.makeText(CadastrarEmprestimo.this,"Nenhum campo pode ficar vazio " +
                                    "ou preenchido de forma incorreta!",Toast.LENGTH_SHORT).show();
                        } else {
                            Conexao bd = new Conexao(CadastrarEmprestimo.this);
                            Emprestimo emprestimo = new Emprestimo(i_dono,i_livro,i_pessoa,data_devolucao,hora_devolucao);
                            bd.InsertEmprestimo(emprestimo);
                            Toast.makeText(CadastrarEmprestimo.this,"Empréstimo do livro " + i_livro +
                                    " entre " + i_dono + " e " + i_pessoa + " foi cadastrado com sucesso!",
                                    Toast.LENGTH_SHORT).show();
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
        // Botão para a página de Listar empréstimos
        button_list_emprestimos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tela_list_emprestimos = new Intent(getApplicationContext(), ListarEmprestimos.class);
                startActivity(tela_list_emprestimos);
            }
        });
    }
}