package com.curso.myapplication.database;

import static com.curso.myapplication.database.AppMigrations.MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.curso.myapplication.database.dao.RoomAlunoDAO;
import com.curso.myapplication.model.Aluno;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "meuApp.db";

    public abstract RoomAlunoDAO getDAOInstance();

    private static AppDatabase dbInstance;

    public static synchronized AppDatabase getDataBaseInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATIONS)
                    .build();
        }
        return dbInstance;
    }
}
