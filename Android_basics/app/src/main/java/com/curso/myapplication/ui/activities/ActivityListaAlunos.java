package com.curso.myapplication.ui.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.curso.myapplication.R;
import com.curso.myapplication.database.AppDatabase;
import com.curso.myapplication.database.dao.RoomAlunoDAO;
import com.curso.myapplication.model.Aluno;
import com.curso.myapplication.ui.adapter.ListaAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ActivityListaAlunos extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Lista de Alunos";
    public static final String EXTRA_SELECTEC_USER = "extra.selected-user";

    private final String TAG = ActivityListaAlunos.class.getSimpleName();
    private final boolean isLoggable = true;
    private FloatingActionButton fabNovoAluno;
    private ListView lv_listaDeAlunos;
    private ListaAdapter adapter;
    private RoomAlunoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isLoggable) Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APP_BAR);
        referenciaElementosDaView();
        configuraAdapter();
        configuraBotaoAdicionarAluno();
        configuraCliqueListaDeAlunos();
    }

    private void referenciaElementosDaView() {
        fabNovoAluno = findViewById(R.id.floatingActionButton);
        lv_listaDeAlunos = findViewById(R.id.main_activity_lista_de_alunos);
    }

    private void configuraAdapter() {
        dao = AppDatabase.getDataBaseInstance(this)
                .getDAOInstance();

        adapter = new ListaAdapter(this);
        lv_listaDeAlunos.setAdapter(adapter);
        registerForContextMenu(lv_listaDeAlunos);
    }

    private void configuraBotaoAdicionarAluno() {
        fabNovoAluno.setOnClickListener(view -> abreFormularioInserirNovoAluno());
    }

    private void abreFormularioInserirNovoAluno() {
        Intent i = new Intent(this, ActivityCadastroAlunos.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isLoggable) Log.d(TAG, "onResume");
        adapter.atualiza(dao.todosAlunos());
    }

    private void configuraCliqueListaDeAlunos() {
        lv_listaDeAlunos.setOnItemClickListener((adapterView,
                                                 view, position, id) -> {
            if (isLoggable) Log.d(TAG, "onItemClick");
            Aluno alunoSelecionado = dao.todosAlunos().get(position);
            Intent i = new Intent(ActivityListaAlunos.this,
                    ActivityCadastroAlunos.class);
            i.putExtra(EXTRA_SELECTEC_USER, alunoSelecionado);
            startActivity(i);
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (isLoggable) Log.d(TAG, "onLongPress");
        int itemId = item.getItemId();

        //delete menu
        if (itemId == R.id.activity_context_menu) {
            if (isLoggable) Log.d(TAG, "onDeletePress");
            deleteConfirmationDialog(item);
        }
        return super.onContextItemSelected(item);
    }

    private void deleteConfirmationDialog(@NonNull MenuItem item) {
        new AlertDialog
                .Builder(this)
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_positive_buttom,
                        (dialogInterface, i) -> excuiAluno(item))
                .setNegativeButton(R.string.dialog_negative_buttom,
                        null)
                .show();
    }

    private void excuiAluno(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
        dao.deleta(alunoEscolhido);
        adapter.remove(alunoEscolhido);

        Toast.makeText(this, alunoEscolhido.toString()
                        + getString(R.string.toast_removed_confirmation),
                Toast.LENGTH_SHORT).show();
    }
}