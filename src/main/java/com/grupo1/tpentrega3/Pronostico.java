/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo1.tpentrega3;

/**
 *
 * @author mcasatti
 */
public class Pronostico {
    private int idPronostico;
    private int idParticipante; 
    private Equipo equipo;
    private Partido partido;
    private char Resultado;
    
    public Pronostico() {
        
    }

    public Pronostico(int idPronostico, int idParticipante, Equipo equipo, Partido partido, char Resultado) {
        this.idPronostico = idPronostico;
        this.idParticipante = idParticipante;
        this.equipo = equipo;
        this.partido = partido;
        this.Resultado = Resultado;
    }
   
    public Equipo getEquipo() {
          return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public char getResultado() {
        return Resultado;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public void setResultado(char Resultado) {
        this.Resultado = Resultado;
    }

    public int getIdPronostico() {
        return idPronostico;
    }
  
    
    /*
    public String toString() {
        String res = "\nApuesto a que en el partido:\n"+
                this.getPartido()+
                this.getEquipo().getNombre()+" obtiene el siguiente Resultado: "+
                this.getResultado()+"\n";
        return res;
    }*/

    @Override
    public String toString() {
        return "Pronostico{" + "idPronostico=" + idPronostico + ", idParticipante=" + idParticipante + ", equipo=" + equipo + ", partido=" + partido + ", Resultado=" + Resultado + '}';
    }

    public int puntos() {
        int val = 0;
        if(equipo.getIdEquipo()==1)
        {equipo=partido.getEquipo1();}
        
        if(equipo.getIdEquipo()==2)
        {equipo=partido.getEquipo2();}
        
        if(partido.getEquipo1()==equipo && partido.getGolesEquipo1()>partido.getGolesEquipo2()
                                        && getResultado() == 'G')
        {val =1;}
        if(partido.getEquipo1()==equipo && partido.getGolesEquipo2()>partido.getGolesEquipo1()
                                        && getResultado() == 'P')
        {val =1;}
        if(partido.getEquipo2()==equipo && partido.getGolesEquipo2()>partido.getGolesEquipo1()
                                        && getResultado() == 'G')
        {val =1;}
        if(partido.getEquipo2()==equipo && partido.getGolesEquipo1()>partido.getGolesEquipo2()
                                        && getResultado() == 'P')
        {val =1;}
        if(partido.getGolesEquipo1()== partido.getGolesEquipo2()
                                        && getResultado() == 'E')
        {val =1;}
        
       // System.out.println(equipo);
        return val;
        
            
    }
}

