package com.mycompany.procesadodearchivoscsv.ad;
/**/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
/*PROCESADO DE ARCHIVOS CSV - ACCESO A DATOS
 *
 * @author Ridouan Tieb
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        HashMap<String, Integer>seLia = null;
        
        //Primera parte del ejercicio
        System.out.println("Introduzca el nombre del archivo");
        String nombreArchivo = sc.nextLine();

        //Menú con el resto de partes del ejercicio
        while (opcion != 4) {

            System.out.println("\n 1.Mostrar el contenido del archivo");
            System.out.println("\n 2.Mostrar el histograma");
            System.out.println("\n 3.Guarda el histograma en un archivo CSV");
            opcion = sc.nextInt();
            
            switch (opcion) {

                case 1:
                    mostrarArchivo(nombreArchivo);
                    break;
                case 2:
                    seLia=mostrarHistograma(nombreArchivo);                   
                    break;
                case 3:                   
                {
                    try {
                        crearCSV(seLia ,nombreArchivo);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;
                case 4:
                    break;
            }
        }
    }
   
    public static void mostrarArchivo(String nombreArchivo) {
        //En esta función mostraré el contenido del archivo.txt
        File f = new File(nombreArchivo);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException ex) {}
    }

    public static HashMap<String, Integer> mostrarHistograma(String nombreArchivo) {
        //En esta función mostraré el histograma de palabras dentro del archivo.txt
        File f = new File(nombreArchivo);
            String s;
            String completo ="";
            
        try (BufferedReader br = new BufferedReader(new FileReader(f))){
            while ((s=br.readLine()) !=null){
                completo+=s+"\n";
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            
                //Aquí quito puntos, comas, etc.
                completo=completo.replaceAll("[\\.\\,\\(\\)]", "");
                
                //Aquí separo cada palabra por espacio y convierto a Array
                String [] palabras = completo.split(" ");
                
                //Este es el mapa donde almaceno la frecuencia
                HashMap<String, Integer> mapaDeFrecuencias = new HashMap<>();
                for (String palabra: palabras) {
                    
              
                    if (mapaDeFrecuencias.containsKey(palabra)) {
                            mapaDeFrecuencias.replace(palabra, 
                            mapaDeFrecuencias.get(palabra), 
                            mapaDeFrecuencias.get(palabra)+1);
                    
                }   else if (!mapaDeFrecuencias.containsKey(palabra) && 
                        (palabra.length() >2)) mapaDeFrecuencias.put(palabra, 1);
                    
                }
    
                //Aquí imprimo el mapa
                for (HashMap.Entry<String, Integer> entry : mapaDeFrecuencias.entrySet()) {
                    System.out.printf("Palabra '%s' con frecuencia %d\n", entry.getKey(), entry.getValue());
                }
            return mapaDeFrecuencias;
    }
    
    public static void crearCSV(HashMap mapaDeFrecuencias, String nombre) throws IOException {
        FileWriter out = new FileWriter(nombre+"_histograma.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(nombre))) {
            mapaDeFrecuencias.forEach((palabra,numero) -> {                
                try {
                    printer.printRecord(palabra,numero);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        
    }
}
