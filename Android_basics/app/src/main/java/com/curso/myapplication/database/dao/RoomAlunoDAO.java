package com.curso.myapplication.database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.curso.myapplication.model.Aluno;

import java.util.List;

@Dao
public interface RoomAlunoDAO {

    @Insert
    void salva(Aluno aluno);

    @Update
    void edita(Aluno aluno);

    @Query("SELECT * FROM aluno")
    List<Aluno> todosAlunos();

    @Delete
    void deleta(Aluno aluno);
}
