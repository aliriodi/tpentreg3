/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo1.tpentrega3;

/**
 *
 * @author mcasatti
 */
public class Participante {
    private int idParticipante;
    private String nombre;
    private ListaPronosticos pronosticos;
    public static ListaPronosticos p;
    
    public Participante() {
        
    }
    
    public Participante(int idParticipante, String nombre, ListaPronosticos pronosticos) {
        this.idParticipante = idParticipante;
        this.nombre = nombre;
        this.pronosticos = pronosticos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

     public int getIdParticipante() {
        return idParticipante;
    }
     
    public ListaPronosticos getPronosticos() {
        return pronosticos;
    }

    public void setPronosticos(ListaPronosticos pronosticos) {
        this.pronosticos = pronosticos;
    }

    @Override
    public String toString() {
        return "Participante{" + "idParticipante=" + idParticipante + ", nombre=" + nombre + ", pronosticos=" + pronosticos + '}';
    }
        
    public void cargarPronosticos(ListaPronosticos pronosticos){
       p = new ListaPronosticos();
    for(Pronostico pronostico : pronosticos.getPronosticos()){
    if(pronostico.getIdParticipante()== idParticipante) {p.addPronostico(pronostico);}
    }
    setPronosticos(p);
    }
    
    // Calculando los puntos de las salidas de los particpantes
    public int resultados() {
        int salida = 0;
    for(Pronostico pronostico: getPronosticos().getPronosticos()) {
       salida = salida + pronostico.puntos();
    }
    return  salida;
    }
}

