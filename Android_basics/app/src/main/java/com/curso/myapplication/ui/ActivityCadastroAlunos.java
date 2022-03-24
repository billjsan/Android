package com.curso.myapplication.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.curso.myapplication.R;
import com.curso.myapplication.model.Aluno;
import com.curso.myapplication.model.AlunoDAO;

public class ActivityCadastroAlunos extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoTelefone;
    private Button bt_salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_alunos);

        referenciaElementosDaView();
        configuraBotaoSalvar();
    }

    private void configuraBotaoSalvar() {
        bt_salvar.setOnClickListener(view -> {
            Aluno aluno = criaNovoAluno();
            salvaAluno(aluno);
        });
    }

    private void salvaAluno(Aluno aluno) {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(aluno);
        finish();
    }

    private Aluno criaNovoAluno() {
        return new Aluno(campoNome.getText().toString(),
                campoTelefone.getText().toString(),
                campoEmail.getText().toString());
    }

    private void referenciaElementosDaView() {
        campoNome = findViewById(R.id.formulario_aluno_nome);
        campoEmail = findViewById(R.id.et_formulario_aluno_email);
        campoTelefone = findViewById(R.id.et_formulario_aluno_telefone);
        bt_salvar = findViewById(R.id.bt_formulario_aluno_confirmar);
    }

}