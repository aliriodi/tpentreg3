/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo1.tpentrega3;

//Importamos las librerias que hacen falta para importar el csv
// y el arraylist
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
//import java.util.logging.Logger;
/**
 *
 * @author Grupo 1 
 */
public class ListaParticipantes {
     // atributo
    private List<Participante> participantes;
    private String participantesCSV;
    private String connectionDB;
    
     public ListaParticipantes(List<Participante> participantes, String participantesCSV, String connectionDB) {
        this.participantes = participantes;
        this.participantesCSV = participantesCSV;
        this.connectionDB = connectionDB;
    }
    
     //Declarando constructor vacio
     public ListaParticipantes() {
         //Nuevo Array List
        this.participantes = new ArrayList<Participante>();
        //Declarando ruta relativa de ubicacion de path archivo
        this.participantesCSV = System.getProperty("user.dir")+"/src/main/java/com/grupo1/tpentrega3/participantes.csv";
        this.connectionDB = "jdbc:sqlite:"+System.getProperty("user.dir")+"/src/main/java/com/grupo1/tpentrega3/pronosticos.db";
     }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public String getParticipantesCSV() {
        return participantesCSV;
    }

    public void setParticipantesCSV(String participantesCSV) {
        this.participantesCSV = participantesCSV;
    }
   
       // add y remove elementos
    public void addParticipante(Participante p) {
        this.participantes.add(p);
    }
    
    public void removeParticipante(Participante p) {
        this.participantes.remove(p);
    }
    
    
     
     /***
     * Este método devuelve un Participante (o null) buscandolo por 
     * idParticipante  @param idParticipante Identificador del participante deseado
     * @param idParticipante
     * @return Objeto participante (o null si no se encuentra)
     */
    public Participante getParticipante (int idParticipante) {
        // Defini un objeto de tipo Equipo en dónde va a ir mi resultado
        // Inicialmente es null, ya que no he encontrado el equipo que 
        // buscaba todavía.
        Participante encontrado = null;
        // Recorro la lista de equipos que está cargada
        for (Participante eq : this.getParticipantes()) {
            // recorrido de todos los participantes de la lista
            if (eq.getIdParticipante() == idParticipante) {
                // Si lo encuentro (son iguales) lo asigno como valor de encontrado
                encontrado = eq;
                // Y hago un break para salir del ciclo ya que no hace falta seguir buscando
                break;
            }
        }
    return encontrado;  }

    @Override
    public String toString() {
        return "ListaParticipantes{" + "participantes=" + participantes + ", participantesCSV=" + participantesCSV + '}';
    }
    
    
      public String listar() {
        String lista = "";
         for (Participante participante: participantes) {
            lista += "\n" + participante;
        }           
        return lista;
    }
    
       // cargar desde el archivo
    public void cargarDeArchivo() {
        // para las lineas del archivo csv
        String datosParticipante;
        // para los datos individuales de cada linea
        String vectorParticipante[];
        // para el objeto en memoria
        Participante participante;
        int fila = 0;
       
        try { 
            Scanner sc = new Scanner(new File(this.getParticipantesCSV()));
            sc.useDelimiter("\n");   //setea el separador de los datos
                
            while (sc.hasNext()) {
                // levanta los datos de cada linea
                datosParticipante = sc.next();
                // Descomentar si se quiere mostrar cada línea leída desde el archivo
                // System.out.println(datosEquipo);  //muestra los datos levantados 
                fila ++;
                // si es la cabecera la descarto y no se considera para armar el listado
                if (fila == 1)
                    continue;              
                 
                //Proceso auxiliar para convertir los string en vector
                // guarda en un vector los elementos individuales
                vectorParticipante = datosParticipante.split(",");   
                
                // graba el equipo en memoria
                //convertir un string a un entero;
                int idParticipante = Integer.parseInt(vectorParticipante[0]);
                String nombre = vectorParticipante[1];
                
                // crea el objeto en memoria
                participante = new Participante(idParticipante, nombre,null);
                
                // llama al metodo add para grabar el equipo en la lista en memoria
                this.addParticipante(participante);
            }
            //closes the scanner
        } catch (IOException ex) {
                System.out.println("Mensaje: " + ex.getMessage());
        }       
}
    
        
    // cargar desde la BASE DE DATOS
    public void cargarDeBD() {
            Connection conn = null;
            try {
            // Establcer una conexion
            conn = DriverManager.getConnection(connectionDB);
            System.out.println("Conexion establecida para Participantes");
            Statement stmt = conn.createStatement();
            String sql = "SELECT " + "idParticipante, nombre " + "FROM participantes ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
            Participante participante = new Participante (rs.getInt("idParticipante"),
                                                          rs.getString("nombre"),
                                                          null);
            System.out.println(participante.toString());
            this.addParticipante(participante);
            
                    }
            }
            catch(SQLException ex) {
            System.out.println(ex.getMessage());
            } finally {
            try {
                if (conn!=null) {
                conn.close();}
            }
            catch(SQLException ex){
             System.out.println(ex.getMessage());
            }
            }
    }
    
    //se invoca al método sort de la interface Collections, para
    //ordenar la lista
    public List<Participante> getOrdenadosPorPuntaje(){
        //Se genera una nueva lista vacia llamada ordenados
    List<Participante> ordenados = new ArrayList<Participante>();
    ordenados.addAll(participantes);
    
    //Usando libreria collectios para ordenar la nueva lista ordenados
    Collections.sort(ordenados, Collections.reverseOrder());
    return ordenados;
    }
    
    //se invoca al método que ordena la lista y se arma una cadena con
    //los resultados
    
    public String listarOrdendosPorPuntaje()
    {
        List<Participante> ordenados = this.getOrdenadosPorPuntaje();
        String lista = "";
        for (Participante participante: ordenados)
        {
            lista+="\n" + participante;
        }
        return lista;
    }
}