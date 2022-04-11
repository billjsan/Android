package com.curso.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity(tableName = "aluno")
public class Aluno implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nome,sobrenome,  telefone, email;
    private Calendar dataCriacao = Calendar.getInstance();;

    public Aluno() {

    }

    @Ignore
    public Aluno(String nome,String sobrenome, String telefone, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.dataCriacao = Calendar.getInstance();
    }

    @Ignore
    private Aluno(Parcel parcel) {
        nome = parcel.readString();
        sobrenome = parcel.readString();
        telefone = parcel.readString();
        email = parcel.readString();
        id = parcel.readInt();
        dataCriacao.setTimeInMillis(parcel.readLong());
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSobrenome(){
        return sobrenome;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeString(sobrenome);
        parcel.writeString(telefone);
        parcel.writeString(email);
        parcel.writeInt(id);
        parcel.writeLong(dataCriacao.getTimeInMillis());
    }

    public static final Parcelable.Creator<Aluno> CREATOR =
            new Parcelable.Creator<Aluno>() {
                @Override
                public Aluno createFromParcel(Parcel parcel) {
                    return new Aluno(parcel);
                }

                @Override
                public Aluno[] newArray(int i) {
                    return new Aluno[i];
                }
            };
}
