/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestempl;

import XStream.EscribirEmpleadosToXML;
import html_xsl.html_xsl;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import modelo.Modelo;
import org.xml.sax.SAXException;
import vista.Vista;

/**
 *
 * @author Jc
 */
public class GestEmpl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException, ParserConfigurationException, TransformerException, SAXException {
        Modelo m = new Modelo();
        Vista v = new Vista(m);
        EscribirEmpleadosToXML xstream = new EscribirEmpleadosToXML();
        html_xsl html = new html_xsl();
        boolean salir = false;
        Scanner sc = new Scanner(System.in);
        String menu = "\nInicio: 01 Mostrar empleados, 02 Generar aleatorios\n"
                + "Ficheros texto: 11 Escribir delimitado, 12 Escribir encolumnado, 13 Leer delimitado, 14 Leer encolumnado\n"
                + "XML: 21 Escribir DOM, 24 Leer SAX, 25 Escribir XStream,26 Crear pagina HTML\nSalir\n\n"
                + "Escriba una opción: ";
        String opcion;
        do {
            System.out.print(menu);
            opcion = sc.nextLine();
            switch (opcion.toLowerCase()) {
                case "01":
                case "mostrar empleados":
                case "mostrar":
                    m.mostrarEmpleados();
                    break;
                case "02":
                case "generar aleatorios":
                case "generar":
                case "aleatorios":
                    v.cargar_aleatorios();
                    break;
                
                case "11":
                case "escribir delimitado":
                    v.escribirDelimitado();
                    break;
                case "12":
                case "escribir encolumnado":
                    v.escribirEncolumnado();
                    break;
                case "13":
                case "leer delimitado":
                    v.leerDelimitado();
                    break;
                case "14":
                case "leer encolumnado":
                    v.leerEncolumnado();
                    break;
                
                case "21":
                case "escribir dom":
                    v.escribirDOM();
                    break;
                
                case "24":
                case "leer sax":
                    v.leerSAX();
                    break;
                case "25":
                case "Escribir XStream":
                    xstream.escribirXStream(m);
                    break;
                case "26":
                case "Crear pagina HTML":
                    html.crearHTML();
                    break;
                case "salir":
                case "q":
                    salir = true;
                    break;
                default:
                    System.err.printf("%nOpción incorrecta%n%n");
                    break;
            } // fin de switch
        } while (!salir);
    }

}
