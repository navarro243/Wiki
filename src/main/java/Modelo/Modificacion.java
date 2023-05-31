package Modelo;
public class Modificacion {
    private int id;
    private String descripcionCambio;
    private String contenidoNuevo;
    private int cedula_Usuario;
    private int id_Articulo;

    public Modificacion() {
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcionCambio() {
        return descripcionCambio;
    }

    public void setDescripcionCambio(String descripcionCambio) {
        this.descripcionCambio = descripcionCambio;
    }

    public String getContenidoNuevo() {
        return contenidoNuevo;
    }

    public void setContenidoNuevo(String contenidoNuevo) {
        this.contenidoNuevo = contenidoNuevo;
    }

    public int getCedula_Usuario() {
        return cedula_Usuario;
    }

    public void setCedula_Usuario(int cedula_Usuario) {
        this.cedula_Usuario = cedula_Usuario;
    }

    public int getId_Articulo() {
        return id_Articulo;
    }

    public void setId_Articulo(int id_Articulo) {
        this.id_Articulo = id_Articulo;
    }
    
    
}
