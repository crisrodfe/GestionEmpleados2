/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import MisClases.GestionContenidoSAX;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelo.Modelo;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Jc
 */
public class Vista {

    Modelo m;

    public Vista(Modelo m) {
        this.m = m;
    }

    public void cargar_aleatorios() {
        System.out.println("¿Cuántos empleados vas a generar?: ");
        Scanner sc = new Scanner(System.in);
        String ent;
        ent = sc.nextLine();
        m.generarAleatorios(Integer.parseInt(ent));
    }

   
    public void escribirDelimitado() throws IOException {
        m.exportDelTo(m.getEmpleadosDelimitado(), "#");
    }

    public void leerDelimitado() {
        m.importDelFrom(m.getEmpleadosDelimitado(), "#");
    }

    public void escribirEncolumnado() {
        int longis[] = {4, 20, 20, 20, 12};
        m.exportEncTo(m.getEmpleadosEncolumnado(), longis);
    }

    public void leerEncolumnado() throws IOException, FileNotFoundException, ParseException {
        int longis[] = {4, 20, 20, 20, 12};
        m.importarEncFrom(m.getEmpleadosEncolumnado(), longis);
    }

   
    public void escribirDOM() throws ParserConfigurationException, TransformerConfigurationException, TransformerException 
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document doc = implementation.createDocument(null, "Empleados", null);
        doc.setXmlVersion("1.0");

        for(int i = 0; i < m.getEmpleados().size(); i++)
        //Bucle que creará una estructura por cada uno de los elementos empleado de nuestra coleccion.    
        {
            Element raiz = doc.createElement("empleado");
            doc.getDocumentElement().appendChild(raiz);

            //Creamos las etiquetas para cada propiedaded de cada objeto empleado de la coleccion.
            Element id = doc.createElement("id");
            Element nombre = doc.createElement("nombre");
            Element apellido1 = doc.createElement("apell1");
            Element apellido2 = doc.createElement("apell2");
            Element salario = doc.createElement("salario");

            //Añadimos las etiquetas al elemento raiz de nuestro documento.
            raiz.appendChild(id);
            raiz.appendChild(nombre);
            raiz.appendChild(apellido1);
            raiz.appendChild(apellido2);
            raiz.appendChild(salario);
            
            //Añadimos a cada una de las etiquetas creadas un texto, su valor.
            nombre.appendChild(doc.createTextNode(m.getEmpleados().get(i).getNombre()));
            apellido1.appendChild(doc.createTextNode(m.getEmpleados().get(i).getApell1()));
            apellido2.appendChild(doc.createTextNode(m.getEmpleados().get(i).getApell2()));
            salario.appendChild(doc.createTextNode(String.valueOf(m.getEmpleados().get(i).getSalario())));
            id.appendChild(doc.createTextNode(String.valueOf(m.getEmpleados().get(i).getId())));
        } 
        Source source = new DOMSource(doc);//fuente xml
        Result result = new StreamResult(new java.io.File("Empleados.xml"));//fichero xml
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }

    
   
    public void leerSAX() throws SAXException, IOException 
    {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        GestionContenidoSAX gc = new GestionContenidoSAX();
        reader.setContentHandler(gc);        
        reader.parse(new InputSource(new FileInputStream("Empleados.xml")));
       
        m.getEmpleados().clear();
        m.setEmpleados(gc.getMiArray());  
    }

} // fin class Vista
