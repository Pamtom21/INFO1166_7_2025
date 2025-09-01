package com.domain.model;

public class Empresa {
    private String rut;
    private String razon;
    private String direccion;

    public Empresa(String rut,String razon,String direccion){
        this.rut = rut;
        this.razon = razon;
        this.direccion = direccion;
    }
    // getters

    public String getRut(){
        return rut;
    }
    public String getRazon(){
        return razon;
    }
    public String getDireccion(){
        return direccion;
    }
    // setters
    public void setRut(String rut){
        this.rut = rut;
    }
    public void setRazon(String razon){
        this.razon = razon;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
}
