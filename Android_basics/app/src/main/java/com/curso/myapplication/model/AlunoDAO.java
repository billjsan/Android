package com.curso.myapplication.model;

import android.util.Log;


import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final String TAG = AlunoDAO.class.getSimpleName();
    private final boolean isLoggable = true;
    private final static List<Aluno> alunos = new ArrayList<>();
    private static int idDoAluno = 1;

    public void salva(Aluno aluno) {
        if (isLoggable) Log.d(TAG, "onSalva");
        aluno.setId(idDoAluno);
        alunos.add(aluno);
        idDoAluno++;
    }

    /**
     * recebe um aluno via parametro para edicao
     * caso o aluno exista na lista de alunos e nao seja nulo
     * ele sera substituido pelo aluno recebido
     *
     *
     */
    public void edita(Aluno aluno) {
        if (isLoggable) Log.d(TAG, "onEdita");
        Aluno alunoEncontrado = null;

        for (Aluno alunoNaListaDeAlunos : alunos) {

            if (alunoNaListaDeAlunos.getId() == aluno.getId()) {
                alunoEncontrado = alunoNaListaDeAlunos;
            }
        }

        if (alunoEncontrado != null) {
            int posicaoAlunoEncontrado = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAlunoEncontrado, aluno);
        }
    }

    public List<Aluno> todosAlunos() {
        return new ArrayList<>(alunos);
    }

    public void deleta(Aluno alunoPressionado) {

        if (alunoPressionado == null) return;
        for (Aluno alunoNaLista : alunos) {

            if (alunoNaLista.getId() == alunoPressionado.getId()) {
                alunos.remove(alunoPressionado);
                if (isLoggable) Log.d(TAG, "deleted");
                return;
            }
        }

        if (isLoggable) Log.d(TAG, "aluno not found");
    }
}
