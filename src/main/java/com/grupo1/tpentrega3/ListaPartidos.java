
package com.grupo1.tpentrega3;
//Importamos las librerias que hacen falta para importar el csv
// y el arraylist
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Grupo1
 */
public class ListaPartidos {
    
      private List<Partido> partidos;
      private String partidosCSV;

    public ListaPartidos(List<Partido> partidos, String partidosCSV) {
        this.partidos = partidos;
        this.partidosCSV = partidosCSV;
    }
    
     public ListaPartidos() {
        this.partidos = new ArrayList<Partido>();
        this.partidosCSV = System.getProperty("user.dir")+"/src/main/java/com/grupo1/tpentrega3//partidos.csv";
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public String getPartidosCSV() {
        return partidosCSV;
    }

    public void setPartidosCSV(String partidosCSV) {
        this.partidosCSV = partidosCSV;
    }
    
         // add y remove elementos
    public void addPartido(Partido p) {
        this.partidos.add(p);
    }
    public void removePartido(Partido p) {
        this.partidos.remove(p);
    }

    @Override
    public String toString() {
        return "ListaPartidos{" + "partidos=" + partidos + ", partidosCSV=" + partidosCSV + '}';
    }
    
    //Metodo para retornar lista de partidos separado
    //por un en enter
     public String listar() {
        String lista = "";
         for (Partido partido: partidos) {
            lista += "\n" + partido;
        }           
        return lista;
    }
    
  //METODO de Busqueda de partido por su id
  //una vez creada la lista
  // El metodo retorna el objeto con todos sus atributos  
    public Partido getPartido (int idPartido) {
          Partido encontrado = null;
          for (Partido eq : this.getPartidos()) {
              if (eq.getIdPartido() == idPartido) {
                  encontrado = eq;
                  break;
            }
          }
          return encontrado;
    }
    
     // cargar desde el archivo
    public void cargarDeArchivo(ListaEquipos equipos) {
        // para las lineas del archivo csv
        String datosPartido;
        // para los datos individuales de cada linea
        String vectorPartido[];
        // para el objeto en memoria
        Partido partido;
        int fila = 0;
       
        try { 
            Scanner sc = new Scanner(new File(this.getPartidosCSV()));
            sc.useDelimiter("\n");   //setea el separador de los datos
                
            while (sc.hasNext()) {
                // levanta los datos de cada linea
                datosPartido = sc.next();
                // Descomentar si se quiere mostrar cada línea leída desde el archivo
                // System.out.println(datosEquipo);  //muestra los datos levantados 
                fila ++;
                // si es la cabecera la descarto y no se considera para armar el listado
                if (fila == 1)
                    continue;              
                 
                //Proceso auxiliar para convertir los string en vector
                // guarda en un vector los elementos individuales
                vectorPartido = datosPartido.split(",");   
                
                // graba el equipo en memoria
                //convertir un string a un entero;
                int idPartido = Integer.parseInt(vectorPartido[0]);
                int idEquipo1 = Integer.parseInt(vectorPartido[1]);
                int idEquipo2 = Integer.parseInt(vectorPartido[2]);
                int GolesEquipo1 = Integer.parseInt(vectorPartido[3]);
                int GolesEquipo2 = Integer.parseInt(vectorPartido[4]);
               
                if(equipos.getEquipo(idEquipo1)!= null && 
                   equipos.getEquipo(idEquipo2)!= null){
                // crea el objeto en memoria
                partido = new Partido(idPartido, equipos.getEquipo(idEquipo1), equipos.getEquipo(idEquipo2),GolesEquipo1,GolesEquipo2);
                
                // llama al metodo add para grabar el equipo en la lista en memoria
                this.addPartido(partido);
                }
                
            }
            //closes the scanner
        } catch (IOException ex) {
                System.out.println("Mensaje: " + ex.getMessage());
        }       

    }

  
}
