package com.curso.myapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.curso.myapplication.database.converter.Converter;
import com.curso.myapplication.database.dao.RoomAlunoDAO;
import com.curso.myapplication.model.Aluno;

@Database(entities = {Aluno.class}, version = 3, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "meuApp.db";

    public abstract RoomAlunoDAO getDAOInstance();

    private static AppDatabase dbInstance;

    public static synchronized AppDatabase getDataBaseInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(AppMigrations.MIGRATIONS_1_2,
                            AppMigrations.MIGRATION_2_3)
                    .build();
        }
        return dbInstance;
    }
}
