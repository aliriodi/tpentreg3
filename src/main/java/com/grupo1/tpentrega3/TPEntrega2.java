/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.grupo1.tpentrega3;

/**
 *
 * @author aliri
 */
public class TPEntrega2 {
    public static PronosticoDeportivo PRODE;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       System.out.println( "Sistema de simulacion de pronosticos deporitivs.");
       PRODE = new PronosticoDeportivo();
       PRODE.play();
    }
    
}

