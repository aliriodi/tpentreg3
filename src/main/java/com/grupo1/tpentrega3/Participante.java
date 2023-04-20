/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo1.tpentrega3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author mcasatti
 */
public class Participante implements Comparable<Participante>{
    private int idParticipante;
    private String nombre;
    private ListaPronosticos pronosticos;
    public static ListaPronosticos p;
    //private String connectionDB;
    
    public Participante() {
        this.idParticipante = 0;
        this.nombre = null;
        this.pronosticos = new ListaPronosticos();
 //       this.connectionDB = "jdbc:sqlite:"+System.getProperty("user.dir")+"/src/main/java/com/grupo1/tpentrega3/pronosticos.db";
    }
    
    public Participante(int idParticipante, String nombre, ListaPronosticos pronosticos) {
        this.idParticipante = idParticipante;
        this.nombre = nombre;
        this.pronosticos = pronosticos;
  //      this.connectionDB =  "jdbc:sqlite:"+System.getProperty("user.dir")+"/src/main/java/com/grupo1/tpentrega3/pronosticos.db";
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
    
     public void cargarPronosticosBD(ListaEquipos equipos, ListaPartidos partidos){
       p = new ListaPronosticos();
       String connectionDB = "jdbc:sqlite:"+System.getProperty("user.dir")+"/src/main/java/com/grupo1/tpentrega3/pronosticos.db";
       //   System.out.println(connectionDB);
         Connection conn = null;
            try {
            // Establcer una conexion
            conn = DriverManager.getConnection(connectionDB);
            System.out.println("Conexion establecida pronosticos");
            Statement stmt = conn.createStatement();
       //     String sql2 = "SELECT * FROM pronosticos ";
           String sql = "SELECT " + " idPronostico, idParticipante, idPartido, idEquipo, resultado " + "FROM pronosticos";
            ResultSet rs = stmt.executeQuery(sql);
           //  ResultSet rs2 = stmt.executeQuery(sql2);
            while (rs.next()){
            if(idParticipante==rs.getInt("idParticipante"))
            {
              Pronostico pronostico = new Pronostico (rs.getInt("idPronostico"),
                                                   rs.getInt("idParticipante"), 
                                                  equipos.getEquipo(rs.getInt("idEquipo")),
                                                   partidos.getPartido(rs.getInt("idPartido")),
                                                   rs.getString("resultado").charAt(0));   
                
          //Pronostico pronostico = new Pronostico (rs.getInt("idPronostico"),
            //                                       rs.getInt("idParticipante"), 
              //                                    equipos.getEquipo(rs.getInt("Equipo")),
                //                                   partidos.getPartido(rs.getInt("idPartido")),
                  //                                 rs.getString("Resultado").charAt(1));
         //   System.out.println("imprimiendo"+rs2.toString());
         
         p.addPronostico(pronostico);
         System.out.println(pronostico.toString());
            }
                  
            
                    }
            this.setPronosticos(p);
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
    
    
    
    // Calculando los puntos de las salidas de los particpantes
    public int resultados() {
        int salida = 0;
    for(Pronostico pronostico: getPronosticos().getPronosticos()) {
       salida = salida + pronostico.puntos();
    }
    return  salida;
    }

    /**
     * compara puntaje totoal
     * @param o
     * @return
     */
    @Override
     public int compareTo(Participante o) {
        if(this.resultados()== o.resultados()) return 0;
        else if (this.resultados() > o.resultados()) return 1;
        else return -1;
    }
    
}

