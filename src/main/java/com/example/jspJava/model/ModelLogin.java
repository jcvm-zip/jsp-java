package com.example.jspJava.model;

import java.io.Serial;
import java.io.Serializable;

public class ModelLogin implements Serializable {
    @Serial
    private static final long serialVersionUID = -4708853568123485999L;

    private String login;
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "ModelLogin{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
