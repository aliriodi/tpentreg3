/*
Para entrega 3
 */
package com.grupo1.tpentrega3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;




/**
 *
 * @author aguzman
 */
public class PronosticoDeportivo {
    private ListaEquipos equipos;
    private ListaPartidos partidos;
    private ListaParticipantes participantes;
    private ListaPronosticos pronosticos; 
   // private ListaParticipantes participantesOrdenados;
   // private int i;

    public PronosticoDeportivo() {
        equipos = new ListaEquipos();
        partidos = new ListaPartidos();
        participantes = new ListaParticipantes();
        pronosticos = new ListaPronosticos(); 
     //   participantesOrdenados = new ListaParticipantes();
     //   i=0;
    }

    public void play(){
        // cargar y listar los equipos
        equipos.cargarDeBD();
        //equipos.cargarDeArchivo();
        System.out.println("Los equipos cargados son: " + equipos.listar());
 

      //  partidos.cargarDeArchivo(equipos);
        partidos.cargarDeBD(equipos);
        System.out.println("Los partidos cargados son: " + partidos.listar());
      
      //  pronosticos.cargarDeArchivo();
        
      //  participantes.cargarDeArchivo();
        participantes.cargarDeBD();
        // Una vez cargados los participantes, para cada uno de ellos
        // cargar sus pronósticos
    
       // for (Participante p : participantes.getParticipantes()) {
       //      System.out.println("Cargando pronosticos de Archivo");
          //     p.cargarPronosticos(pronosticos);
       // }
        System.out.println("Cargando pronosticos de BD por cada participante");
         for (Participante p : participantes.getParticipantes()) {
               System.out.println("Cargando pronosticos para"+p);
            p.cargarPronosticosBD(equipos, partidos);
        }
        
        System.out.println("Los participantes cargados son: " + participantes.listar());
           System.out.println( "Nombre               " + "Puntos");
       for(Participante participante : participantes.getParticipantes()) {
        
        System.out.println(  participante.getNombre()+"         "+participante.resultados());

    }

        //Ordenando Partcipantes
        /*
        while(participantesOrdenados.getParticipantes().size()!=participantes.getParticipantes().size())
        {
        Participante P0 = participantes.getParticipantes().get(i);
        System.out.println("P0: "+P0);
        System.out.println(participantesOrdenados.getParticipantes().contains(P0));
        System.out.println("Vuelta: "+i);
        if(participantesOrdenados.getParticipantes().contains(P0)){
        i=i+1;
        P0 = participantes.getParticipantes().get(i);
        } else {
        }
        for(Participante P1: participantes.getParticipantes())
        {
        if(P1.resultados()>P0.resultados()) {P0=P1;}
        }
        participantesOrdenados.getParticipantes().add(P0);
        }
         */
     
        
      //Reimprimiendo
        System.out.println("Los participantes Ordenados por puntos son: ");
//           System.out.println( "Nombre               " + "Puntos");
/*     for(Participante participante2 : participantesOrdenados.getParticipantes()) {
        
        System.out.println(  participante2.getNombre()+"         "+participante2.resultados());
        
    }
*/  
 //System.out.println("Los participantes cargados son: " + participantes.listar());
       System.out.println( "Nombre               " + "Puntos");
       for(Participante participante : participantes.getOrdenadosPorPuntaje()) {
        
        System.out.println(  participante.getNombre()+"         "+participante.resultados());

    }

        // agregar y/o modificar el codigo que quieran
        
    }    
}
