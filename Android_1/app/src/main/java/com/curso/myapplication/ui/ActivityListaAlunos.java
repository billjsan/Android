package com.curso.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.curso.myapplication.R;
import com.curso.myapplication.model.AlunoDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityListaAlunos extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Lista de Alunos";
    private FloatingActionButton fabNovoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APP_BAR);

        referenciaElementosDaView();
        configuraBotaoAdicionarAluno();
    }

    private void configuraBotaoAdicionarAluno() {
        fabNovoAluno.setOnClickListener(view -> {
            abreFormularioInserirNovoAluno();
        });
    }

    private void abreFormularioInserirNovoAluno() {
        Intent i = new Intent(this, ActivityCadastroAlunos.class);
        startActivity(i);
    }

    private void referenciaElementosDaView() {
        fabNovoAluno = findViewById(R.id.floatingActionButton);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AlunoDAO dao = new AlunoDAO();

        ListView lv_listaDeAlunos = findViewById(R.id.main_activity_lista_de_alunos);
        lv_listaDeAlunos.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, dao.todosAlunos()));
    }
}