package com.curso.myapplication.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

class AppMigrations {

    protected static final Migration MIGRATIONS = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            database.execSQL("ALTER TABLE "+ AppDatabase.DATABASE_NAME +
                    " ADD COLUMN sobrenome TEXT");
        }
    };

}
