package com.curso.myapplication.model;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();

    public void salva(Aluno aluno) {

        alunos.add(aluno);
    }

    public List<Aluno> todosAlunos() {
        return new ArrayList<>(alunos);
    }
}
