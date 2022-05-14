package com.example.conexaobdsqlite_v1;

public class Usuario {

    public Usuario() {
    }

    public Usuario(int idUsuario, String nomeUsuario, int idadeUsuario) {
        this.setIdUsuario(idUsuario);
        this.setNomeUsuario(nomeUsuario);
        this.setIdadeUsuario(idadeUsuario);
    }

    public Usuario(String nomeUsuario, int idadeUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.idadeUsuario = idadeUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nomeUsuario='" + nomeUsuario + '\'' + ", idadeUsuario=" + idadeUsuario + '}';
        //"Usuario{idUsuario=1, nomeUsuario=Ronan, idadeUsuario=30}"
    }

    private int idUsuario;
    private String nomeUsuario;
    private int idadeUsuario;



    protected int getIdUsuario() {
        return idUsuario;
    }

    protected void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    protected String getNomeUsuario() {
        return nomeUsuario;
    }

    protected void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    protected int getIdadeUsuario() {
        return idadeUsuario;
    }

    protected void setIdadeUsuario(int idadeUsuario) {
        this.idadeUsuario = idadeUsuario;
    }
}