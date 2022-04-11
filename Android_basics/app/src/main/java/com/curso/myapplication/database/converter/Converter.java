package com.curso.myapplication.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class Converter {

    @TypeConverter
    public Long paraInt(Calendar data){
        return data.getTimeInMillis();
    }

    @TypeConverter
    public Calendar paraCalendar(Long data){
        Calendar momentoAtual = Calendar.getInstance();
        if(data != null){
            momentoAtual.setTimeInMillis(data);
        }
        return momentoAtual;
    }
}
