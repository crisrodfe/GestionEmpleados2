package modelo;

import entidades.Empleado;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Jc
 */
public class Modelo implements Serializable {

    private ArrayList<Empleado> empleados;
    private File empleadosDelimitado;
    private File empleadosEncolumnado;
    private File empleadosBinario;
    private File empleadosObjeto;

    public Modelo() {
        this.empleadosDelimitado = new File("empleados.del");
        this.empleadosEncolumnado = new File("empleados.encol");
        this.empleadosBinario = new File("empleados.bin");
        this.empleadosObjeto = new File("empleados.obj");
        empleados = new ArrayList<>();
    }

    public void mostrarEmpleados() {
        System.out.printf("%nEmpleados%n%n");
        for (Empleado e : empleados) {
            System.out.println(e.toEncFormat());
        }
    }

    public void generarAleatorios(int n) {
        for (int i = 0; i < n; i++) {
            Empleado e = new Empleado();
            e.emp_aleatorio();
            empleados.add(e);
        }
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    public File getEmpleadosDelimitado() {
        return empleadosDelimitado;
    }

    public File getEmpleadosEncolumnado() {
        return empleadosEncolumnado;
    }

    public File getEmpleadosObjeto() {
        return empleadosObjeto;
    }

    public void setEmpleadosObjeto(File empleadosObjeto) {
        this.empleadosObjeto = empleadosObjeto;
    }
   
    public void setEmpleadosEncolumnado(File empleadosEncolumnado) {
        this.empleadosEncolumnado = empleadosEncolumnado;
    }

    public File getEmpleadosBinario() {
        return empleadosBinario;
    }

    public void setEmpleadosBinario(File empleadosBinario) {
        this.empleadosBinario = empleadosBinario;
    }

    public void setEmpleadosDelimitado(File empleadosDelimitado) {
        this.empleadosDelimitado = empleadosDelimitado;
    }

    @Override
    public String toString() {
        return "Modelo{" + "empleados=" + empleados + '}';
    }

    public void exportDelTo(File f, String delim) throws IOException 
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        String s;        
        //El bucle recorre la coleccion con los objetos Empleado
        //Extraemos una por una sus propiedades y las vamos añadiendo a un String 
        //poniendo entre ellas el caracter separador introducido como argumento.
        for(int i = 0; i < empleados.size(); i++)
        {
          s = (empleados.get(i).getId()+delim+
               empleados.get(i).getNombre()+delim+
               empleados.get(i).getApell1()+delim+
               empleados.get(i).getApell2()+delim+
               empleados.get(i).getSalario()+delim);
          bw.write(s);
        }
        bw.close();
    }

    public void importDelFrom(File f, String delim) {
        List<String> empleadosEnDelimitado = null;
        try {
            empleadosEnDelimitado = Files.readAllLines(f.toPath(),Charset.forName("UTF-8"));
        } catch (IOException ex) {
            System.err.println("No fue posible leer " + f.getName());
        }
        for (String s : empleadosEnDelimitado) {
            Empleado empl = new Empleado(s, delim);
            this.getEmpleados().add(empl);
        }
    }

    public void exportEncTo(File f, int longis[]) {
        ArrayList<String> lineas = new ArrayList<>();
        for (Empleado empl : empleados) {
            lineas.add(empl.toColumnadoString(longis));
        }
        try {
            Files.write(f.toPath(), lineas, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            System.err.println("No fue posible escribir " + f.getName());
            System.err.println("Debido a la excepción " + ex.getMessage() + ex.toString());
        }
    }

    public void importarEncFrom(File f, int longis[]) throws FileNotFoundException, IOException, ParseException 
    {
        empleados.clear();//Vaciamos la colección antes de introducir nuevos datos.
        
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null)
        {   
            int i = Integer.valueOf(linea.substring(0,longis[0]).trim());//Desde 0 hasta la primera dimension
            String n = linea.substring(longis[0],longis[0]+longis[1]).trim();//desde la primera hasta primera+segunda dimension...etc...
            String a1 = linea.substring(longis[0]+longis[1],longis[0]+longis[1]+longis[2]).trim();
            String a2 = linea.substring(longis[0]+longis[1]+longis[2],longis[0]+longis[1]+longis[2]+longis[3]).trim();
            
            NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
            Number number = format.parse(linea.substring(longis[0]+longis[1]+longis[2]+longis[3],linea.length()).trim());
            float s = number.floatValue();
          
            Empleado e = new Empleado(i,n,a1,a2,s);
            empleados.add(e);            
        }      
    }
}
