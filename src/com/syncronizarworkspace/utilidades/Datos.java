package com.syncronizarworkspace.utilidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Datos {

  public static String leerData(String url_origen) {

    StringBuilder info_archivo = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader(url_origen))) {

      String linea;

      while ((linea = reader.readLine()) != null) {
        info_archivo.append(linea + "\n");
      }

    } catch (IOException e) {
      // updateFullData(DummyData.datosGenericos());
      // info_archivo.append(DummyData.datosGenericos());
      System.out.println("Error");
    }

    return info_archivo.toString().trim();

  }

  public static void escribirDatos(String data, String url_destino) {

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(url_destino, false))) {

      writer.write(data);
      writer.newLine();
      writer.flush();

    } catch (IOException e) {
      System.out.println("Error " + e.getMessage());
      e.printStackTrace();
    }

  }

  public static String getData() {

    StringBuilder info_archivo = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader("src/com/tarea1/utilidades/input_data.txt"))) {

      String linea;

      while ((linea = reader.readLine()) != null) {
        info_archivo.append(linea);
      }

    } catch (IOException e) {
      updateFullData(DummyData.datosGenericos());
      info_archivo.append(DummyData.datosGenericos());
    }

    return info_archivo.toString().trim();

  }

  public static void saveData(String data) {
    updateDataSource(data, true);
  }

  public static void updateFullData(String data) {
    updateDataSource(data, false);
  }

  private static void updateDataSource(String data, boolean preserve_old_data) {

    try (BufferedWriter writer = new BufferedWriter(
        new FileWriter("src/com/tarea1/utilidades/input_data.txt", preserve_old_data))) {

      writer.write(data);
      // writer.newLine();
      // writer.flush();

    } catch (IOException e) {
      System.out.println("Error " + e.getMessage());
      e.printStackTrace();
    }

  }

}

class DummyData {
  public static String datosGenericos() {
    return "1,Bruce Wayne,35,Limón/2,Jack Sparrow,40,Alajuela/3,Peter Parker,21,Cartago/4,Tony Stark,45,San José/5,Clark Kent,33,Heredia/6,Diana Prince,29,Puntarenas/7,Steve Rogers,37,Guanacaste/8,Natasha Romanoff,32,Limón/9,Barry Allen,28,San José/10,Wanda Maximoff,26,Cartago/11,Logan Howlett,42,Heredia/12,Stephen Strange,48,Alajuela/13,Selina Kyle,31,Puntarenas/14,Arthur Curry,36,Guanacaste/15,Charles Xavier,50,San José/16,Jean Grey,27,Cartago/17,Hal Jordan,34,Heredia/18,Raven Darkhölme,30,Limón/19,Billy Batson,20,Alajuela/20,Bruce Banner,39,Puntarenas/";
  }
}