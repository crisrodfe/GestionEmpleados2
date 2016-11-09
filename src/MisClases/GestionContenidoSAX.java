/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClases;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import entidades.Empleado;

/**
 *
 * @author CrisRodFe
 */
public class GestionContenidoSAX extends DefaultHandler {
        
     
     private ArrayList<Empleado> miArray = new ArrayList<>();
     private Empleado e;
     private String texto = null;
     private String nombreEtiqueta = null;

    public ArrayList<Empleado> getMiArray() {
        return miArray;
    }
     
     @Override
     public void startDocument() throws SAXException 
     {         
        System.out.println("\nLeyendo datos de Empleado.xml :\nPrincipio del documento XML\n"); 
     }
    
     @Override
     public void endDocument() throws SAXException 
     { 
         System.out.println("Fin del Documento\n");
     }
     
     @Override
     public void startElement(String uri, String localName, String name,Attributes attributes) throws SAXException 
     {  
        nombreEtiqueta = localName;
        if (localName.equals("empleado"))
        {    
            e = new Empleado();
        }    
     }
     
     @Override
     public void characters(char[] ch, int start, int length)throws SAXException 
     {
        texto = String.valueOf(ch, start, length).trim();        
        switch (nombreEtiqueta)
        {
            case "id":
                e.setId(Integer.valueOf(texto));
                break;
            case "nombre":
                e.setNombre(texto);
                break;
            case "apell1":
                e.setApell1(texto);
                break;
            case "apell2":
                e.setApell2(texto);
                break;
            case "salario":
                e.setSalario(Float.valueOf(texto));
                break;
        }        
     }
     
     @Override
     public void endElement(String uri, String localName, String name)throws SAXException 
     {   
        if(localName.equals("empleado"))
        {        
            miArray.add(e); 
            System.out.println("\tNuevo empleado leido: "+e.toString());
        }    
     } 
}
