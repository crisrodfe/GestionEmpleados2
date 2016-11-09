/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package html_xsl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author CrisRodFe
 */
public class html_xsl 
{
   public void crearHTML() throws FileNotFoundException, TransformerConfigurationException, TransformerException, IOException
    {
        File paginaHTML = new File("EmpleadosHTML.html");
        FileOutputStream fos = new FileOutputStream(paginaHTML);
        Source estilos = new StreamSource("empleadosPlantilla.xsl");
        Source datos = new StreamSource("Empleados.xml");
        Result result = new StreamResult(fos);
        Transformer transformer= TransformerFactory.newInstance().newTransformer(estilos);
        transformer.transform(datos, result);
        fos.close();
    
    }   
}
