package Modelo;

public class Usuario_articulo {
    private int cedula_usuario;
    private int id_Articulo;
    private String estado;

    public Usuario_articulo() {
    }

    public int getCedula_usuario() {
        return cedula_usuario;
    }

    public void setCedula_usuario(int cedula_usuario) {
        this.cedula_usuario = cedula_usuario;
    }

    public int getId_Articulo() {
        return id_Articulo;
    }

    public void setId_Articulo(int id_Articulo) {
        this.id_Articulo = id_Articulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
