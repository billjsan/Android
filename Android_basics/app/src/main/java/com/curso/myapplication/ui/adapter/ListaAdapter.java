package com.curso.myapplication.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.curso.myapplication.R;
import com.curso.myapplication.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAdapter extends BaseAdapter {

    private final List<Aluno> todosOsAlunos = new ArrayList<>();
    private final Context context;

    public ListaAdapter(Context context) {
        this.context = context;
    }

    public void remove(Aluno aluno) {
        todosOsAlunos.remove(aluno);
        notifyDataSetChanged();
    }

    public void atualiza(List<Aluno> alunos) {
        this.todosOsAlunos.clear();
        this.todosOsAlunos.addAll(alunos);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return todosOsAlunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return todosOsAlunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return todosOsAlunos.get(position).getId();
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View viewCriada = inflateLayout(viewGroup);
        binding(position, viewCriada);
        return viewCriada;
    }

    private void binding(int position, View viewCriada) {
        TextView nomeESobrenome = viewCriada.findViewById(R.id.tv_nome_aluno);
        TextView telefone = viewCriada.findViewById(R.id.tv_telefone_aluno);

        String nomeSobrenome = todosOsAlunos.get(position).getNome() + " " +
                todosOsAlunos.get(position).getSobrenome();

        nomeESobrenome.setText(nomeSobrenome);
        telefone.setText(todosOsAlunos.get(position).getTelefone());
    }

    private View inflateLayout(ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_lista_aluno,
                viewGroup, false);
    }
}
