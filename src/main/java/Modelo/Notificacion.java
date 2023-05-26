package Modelo;

public class Notificacion {
    private int id;
    private int estado;
    private int id_modificacion;
    private int cedula_usuario;
    private int id_Rol;
    private String asunto;

    public Notificacion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId_modificacion() {
        return id_modificacion;
    }

    public void setId_modificacion(int id_modificacion) {
        this.id_modificacion = id_modificacion;
    }

    public int getCedula_usuario() {
        return cedula_usuario;
    }

    public void setCedula_usuario(int cedula_usuario) {
        this.cedula_usuario = cedula_usuario;
    }

    public int getId_Rol() {
        return id_Rol;
    }

    public void setId_Rol(int id_Rol) {
        this.id_Rol = id_Rol;
    }
    

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
    
}
