/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XStream;


import com.thoughtworks.xstream.XStream;
import entidades.Empleado;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import modelo.Modelo;

/**
 *
 * @author CrisRodFe
 */
public class EscribirEmpleadosToXML 
{
   public void escribirXStream(Modelo m) throws FileNotFoundException 
    {
        XStream xStream = new XStream();

        xStream.alias("empleado", Empleado.class);
        xStream.alias("Empleados", List.class);
        xStream.addImplicitCollection(Modelo.class, "empleados");
        
        xStream.toXML(m.getEmpleados(),new FileOutputStream("EmpleadosXStream.xml"));
    }  
}
