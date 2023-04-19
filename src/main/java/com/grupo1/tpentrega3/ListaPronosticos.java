
package com.grupo1.tpentrega3;
/*
Librerias necesesarias para leer archivos
cargar en memoria, crear Listas
y manejo de errore a la hora de leer archivo o ejecucion de tareas

*/
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.util.Objects;
import java.util.Scanner;
import static com.grupo1.tpentrega3.TP.LISTAEQUIPOS;
import static com.grupo1.tpentrega3.TP.LISTAPARTIDOS;
//import static com.grupo1.tpentrega3.TP.LISTAPARTIDOS;

/**
 *
 * @author Grupo1
 */
public class ListaPronosticos {
     private List<Pronostico> pronosticos;
     private String pronosticosCSV;
     //cargo mi lista de equipos de memoria para leerla y usars sus propiedades
     //para cuando cargo los campos en la lista de pronosticos segun
     //premisas de TP ENTREGA2
          
     
    /*Creando mi listas con tipos de datos 
     y su ubicacion como variable*/ 
       public ListaPronosticos(List<Pronostico> pronosticos, String pronosticosCSV) {
        this.pronosticos = pronosticos;
        this.pronosticosCSV = pronosticosCSV;
    }
    
            //Declarando constructor vacio
     public ListaPronosticos() {
         //Nuevo Array List
        this.pronosticos = new ArrayList<Pronostico>();
        //Declarando ruta relativa de ubicacion de path archivo
        this.pronosticosCSV = System.getProperty("user.dir")+"/src/main/java/com/grupo1/tpentrega3/pronosticos.csv";
    }

     // Getter and Setter creados por las funciones nativas
     
    public List<Pronostico> getPronosticos() {
        return pronosticos;
    }

    public void setPronosticos(List<Pronostico> pronosticos) {
        this.pronosticos = pronosticos;
    }

    public String getPronosticosCSV() {
        return pronosticosCSV;
    }

    public void setPronosticosCSV(String pronosticosCSV) {
        this.pronosticosCSV = pronosticosCSV;
    }

    @Override
    public String toString() {
        return "ListaPronosticos{" + "pronosticos=" + pronosticos + ", pronosticosCSV=" + pronosticosCSV + '}';
    }

          // add y remove elementos
    public void addPronostico(Pronostico p) {
        this.pronosticos.add(p);
    }
    
    public void removePronostico(Pronostico p) {
        this.pronosticos.remove(p);
    }
    
    public Pronostico getPronostico (int idPronostico){
    Pronostico encontrado = null;
    for (Pronostico eq : this.getPronosticos())
    {
    if(eq.getIdPronostico() == idPronostico) {encontrado = eq;
                                               break;}
    }
    return encontrado;
     }
    
         public String listar() {
        String lista = "";
         for (Pronostico pronostico: pronosticos) {
            lista += "\n" + pronostico;
        }           
        return lista;
    }
    
            // cargar desde el archivo
    public void cargarDeArchivo() {
        // para las lineas del archivo csv
        String datosPronostico;
        // para los datos individuales de cada linea
        String vectorPronostico[];
        // para el objeto en memoria
        Pronostico pronostico;
        int fila = 0;
       
        try { 
            Scanner sc = new Scanner(new File(this.getPronosticosCSV()));
            sc.useDelimiter("\n");   //setea el separador de los datos
                
            while (sc.hasNext()) {
                // levanta los datos de cada linea
                datosPronostico = sc.next();
                // Descomentar si se quiere mostrar cada línea leída desde el archivo
                // System.out.println(datosEquipo);  //muestra los datos levantados 
                fila ++;
                // si es la cabecera la descarto y no se considera para armar el listado
                if (fila == 1)
                    continue;              
                 
                //Proceso auxiliar para convertir los string en vector
                // guarda en un vector los elementos individuales
                vectorPronostico = datosPronostico.split(",");   
                
                // graba el equipo en memoria
                //convertir un string a un entero;
                int idPronostico = Integer.parseInt(vectorPronostico[0]);
                int idParticipante = Integer.parseInt(vectorPronostico[1]);
                int idPartido = Integer.parseInt(vectorPronostico[2]);
                int idEquipo = Integer.parseInt(vectorPronostico[3]);
                char Resultado = vectorPronostico[4].charAt(1);
                //ME TRAIGO DE MEMORIA LA LISTA DE EQUIPOS CARGADOS CON ANTERIORIDAD
                
                             
                
                // crea el objeto en memoria
                // idPronostico Equipo Partido Resultado
                pronostico = new Pronostico(idPronostico, idParticipante,
                                      LISTAEQUIPOS.getEquipo(idEquipo),
                                      LISTAPARTIDOS.getPartido(idPartido),
                                            Resultado);
                
                // llama al metodo add para grabar el equipo en la lista en memoria
                this.addPronostico(pronostico);
            }
            //closes the scanner
        } catch (IOException ex) {
                System.out.println("Mensaje: " + ex.getMessage());
        }       
}
         
}
