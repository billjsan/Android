package com.curso.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Aluno implements Parcelable {

    private String nome, telefone, email;
    private int id = 0;

    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    private Aluno(Parcel parcel) {
        nome = parcel.readString();
        telefone = parcel.readString();
        email = parcel.readString();
        id = parcel.readInt();
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
        parcel.writeString(email);
        parcel.writeString(telefone);
        parcel.writeInt(id);
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
