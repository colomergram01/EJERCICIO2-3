package com.example.ejercicio_2_3.Configuraciones;

public class ModelRegistro {
    private Integer id;
    private byte[] imagen;
    //    private int imagen2;
    private String descripcion;

    public ModelRegistro(Integer id, byte[] imagen, String descripcion) {
        this.id = id;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public ModelRegistro() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
