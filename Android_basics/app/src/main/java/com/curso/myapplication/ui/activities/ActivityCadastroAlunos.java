package com.curso.myapplication.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.curso.myapplication.R;
import com.curso.myapplication.database.AppDatabase;
import com.curso.myapplication.database.dao.RoomAlunoDAO;
import com.curso.myapplication.model.Aluno;

public class ActivityCadastroAlunos extends AppCompatActivity {

    private final String TAG = ActivityCadastroAlunos.class.getSimpleName();
    private final boolean isLoggable = true;
    private RoomAlunoDAO dao;
    private Aluno alunoSelecionado = null;
    private EditText et_campoNome;
    private EditText et_campoSobrenome;
    private EditText et_campoEmail;
    private EditText et_campoTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_cadastro_alunos);
        if (isLoggable) Log.d(TAG, "onCreate");

        dao = AppDatabase.getDataBaseInstance(this)
                .getDAOInstance();

        referenciaElementosDaView();
        recuperaInfoAlunoSelecionado();
        super.onCreate(savedInstanceState);
    }

    private void referenciaElementosDaView() {
        et_campoNome = findViewById(R.id.formulario_aluno_nome);
        et_campoSobrenome = findViewById(R.id.formulario_aluno_sobrenome);
        et_campoEmail = findViewById(R.id.et_formulario_aluno_email);
        et_campoTelefone = findViewById(R.id.et_formulario_aluno_telefone);
    }

    private void recuperaInfoAlunoSelecionado() {
        Intent i = getIntent();
        if (!i.hasExtra(ActivityListaAlunos.EXTRA_SELECTEC_USER)) {
            if (isLoggable) Log.d(TAG, "has no extra");
            return;
        }
        alunoSelecionado =
                i.getParcelableExtra(ActivityListaAlunos.EXTRA_SELECTEC_USER);
        mostraInfoAlunoSelecionado(alunoSelecionado);
    }

    private void mostraInfoAlunoSelecionado(@NonNull Aluno alunoSelecionado) {
        et_campoNome.setText(alunoSelecionado.getNome());
        et_campoSobrenome.setText(alunoSelecionado.getSobrenome());
        et_campoEmail.setText(alunoSelecionado.getEmail());
        et_campoTelefone.setText(alunoSelecionado.getTelefone());
    }

    private void editaAlunoExistente() {
        if (isLoggable) Log.d(TAG, "onEditaAlunoExistente");
        alunoSelecionado.setNome(et_campoNome.getText().toString());
        alunoSelecionado.setSobrenome(et_campoSobrenome.getText().toString());
        alunoSelecionado.setEmail(et_campoEmail.getText().toString());
        alunoSelecionado.setTelefone(et_campoTelefone.getText().toString());
        dao.edita(alunoSelecionado);
    }

    private void criaNovoAluno() {
        if (isLoggable) Log.d(TAG, "onCriaNovoAluno");
        Aluno novoAluno = new Aluno(
                et_campoNome.getText().toString(),
                et_campoSobrenome.getText().toString(),
                et_campoTelefone.getText().toString(),
                et_campoEmail.getText().toString()
        );
        dao.salva(novoAluno);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (isLoggable) Log.d(TAG, "onOptionsItemSelected");
        int itemId = item.getItemId();
        if (itemId == R.id.activity_menu_salvar) {
            if (alunoSelecionado != null) {
                editaAlunoExistente();
            } else {
                criaNovoAluno();
            }
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_lista_alunos_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }
}