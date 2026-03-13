/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ASUS
 */



import java.io.*;
import java.util.ArrayList;
import model.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import model.Vehiculo;

public class VehiculoDAO {

    private final String archivo = "vehiculos.txt";

    public void guardarVehiculo(Vehiculo v){

        try{

            FileWriter fw = new FileWriter(archivo, true);
            PrintWriter pw = new PrintWriter(fw);

            String tipo = v.getClass().getSimpleName();

            pw.println(v.getPlaca() + ";" + v.getRuta() + ";" + tipo);

            pw.close();

        }catch(IOException e){

            System.out.println("Error guardando vehiculo");

        }

    }

    public ArrayList<Vehiculo> cargarVehiculos(){

        ArrayList<Vehiculo> lista = new ArrayList<>();

        try{

            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;

            while((linea = br.readLine()) != null){

                String[] datos = linea.split(";");

                String placa = datos[0];
                String ruta = datos[1];
                String tipo = datos[2];

                Vehiculo v = null;

                if(tipo.equals("Buseta"))
                    v = new Buseta(placa,ruta);

                if(tipo.equals("MicroBus"))
                    v = new MicroBus(placa,ruta);

                if(tipo.equals("Bus"))
                    v = new Bus(placa,ruta);

                lista.add(v);

            }

            br.close();

        }catch(IOException e){

            System.out.println("Archivo no encontrado, se creará al guardar datos");

        }

        return lista;

    }

}
