package com.syncronizarworkspace.mvc.controllers;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

import com.syncronizarworkspace.mvc.views.PrincipalView;
import com.syncronizarworkspace.utilidades.Datos;

public class ControllerSyncronizarProyectos {
  private PrincipalView v;
  private String urlDoom, urlEclipse, nombre_proyecto;

  public ControllerSyncronizarProyectos() {
    v = new PrincipalView();
    urlDoom = urlEclipse = "";
  }

  public void init() {
    v.init();
    v.principal.btn_doom_eclipse.addActionListener(e -> iniciarSync(v.principal.campos.get(1).getText()));
    v.principal.btn_eclipse_doom.addActionListener(e -> iniciarSync(v.principal.campos.get(0).getText()));
  }

  private void iniciarSync(String url) {
    urlDoom = v.principal.campos.get(1).getText();
    urlEclipse = v.principal.campos.get(0).getText();

    if (url.equals(urlDoom)) {
      crearProyecto(url, urlEclipse);
    }
    if (url.equals(urlEclipse)) {
      crearProyecto(url, urlDoom);
    }

  }

  private void crearProyecto(String url_origen, String url_copia) {
    Path ruta_proyecto = Paths.get(url_origen);
    Path ruta_copia = Paths.get(url_copia);

    if (!Files.exists(ruta_proyecto)) {

      try {

        crearEstructuraMVC(ruta_proyecto, url_origen);

        Path origen = Paths.get("bin/recursos/copia");
        copiarDirectorio(origen, ruta_proyecto);

        copiarArchivosTemplates(url_origen);

      } catch (IOException e) {
        System.out.println("Error " + e.getMessage());
        e.printStackTrace();
      }

    } else {
      SyncronizarProyectos(ruta_proyecto, ruta_copia);
    }

  }

  private void crearEstructuraMVC(Path ruta_proyecto, String url) throws IOException {

    nombre_proyecto = url.substring(url.lastIndexOf("/") + 1);

    url = url.concat("/src/com/").concat(nombre_proyecto.toLowerCase());

    ruta_proyecto = Paths.get(url + "/init");

    Files.createDirectories(ruta_proyecto);

    url = url.concat("/mvc/");

    ruta_proyecto = Paths.get(url + "models");
    Files.createDirectories(ruta_proyecto);

    ruta_proyecto = Paths.get(url + "controllers");
    Files.createDirectories(ruta_proyecto);

    ruta_proyecto = Paths.get(url + "views");
    Files.createDirectories(ruta_proyecto);

  }

  private void SyncronizarProyectos(Path url_origen, Path url_copia) {
    try {
      eliminarDirectorio(url_copia);
      copiarDirectorio(url_origen, url_copia);
    } catch (IOException e) {
      System.out.println("Error " + e.getMessage());
      e.printStackTrace();
    }
  }

  private void eliminarDirectorio(Path ruta) throws IOException {
    if (!Files.exists(ruta)) {
      System.out.println("La ruta no existe: " + ruta);
      return;
    }

    Files.walkFileTree(ruta, new SimpleFileVisitor<Path>() {

      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Files.delete(file);
        System.out.println("Eliminado archivo: " + file);
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        Files.delete(dir);
        System.out.println("Eliminado directorio: " + dir);
        return FileVisitResult.CONTINUE;
      }

    });
  }

  private void copiarDirectorio(Path origen, Path destino) throws IOException {

    Files.walkFileTree(origen, new SimpleFileVisitor<Path>() {
      @Override
      public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path target = destino.resolve(origen.relativize(dir));
        Files.createDirectories(target);
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path target = destino.resolve(origen.relativize(file));
        Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
        return FileVisitResult.CONTINUE;
      }
    });

  }

  private void copiarArchivosTemplates(String url) {
    /* Escribir script run.sh */
    String url_origen = "bin/recursos/template/run.sh";
    String mydata = Datos.leerData(url_origen);

    String modificar = "syncronizarworkspace";
    mydata = mydata.replace(modificar, nombre_proyecto.toLowerCase());
    Datos.escribirDatos(mydata, url + "/run.sh");

    /* Escribir el Main.java */
    url_origen = "bin/recursos/template/Main.java";
    mydata = Datos.leerData(url_origen);
    mydata = mydata.replaceAll(modificar, nombre_proyecto.toLowerCase());
    Datos.escribirDatos(mydata, url + "/src/com/" + nombre_proyecto.toLowerCase() + "/init/Main.java");

    /* Escribir template controller de nombre cambiante */
    url_origen = "bin/recursos/template/ControllerProyectos.java";
    mydata = Datos.leerData(url_origen);
    mydata = mydata.replaceAll(modificar, nombre_proyecto.toLowerCase());
    Datos.escribirDatos(mydata,
        url + "/src/com/" + nombre_proyecto.toLowerCase() + "/mvc/controllers/ControllerProyectos.java");

    /* Escribir Marco por defecto */
    url_origen = "bin/recursos/template/PrincipalView.java";
    mydata = Datos.leerData(url_origen);
    mydata = mydata.replaceAll(modificar, nombre_proyecto.toLowerCase());
    Datos.escribirDatos(mydata, url + "/src/com/" + nombre_proyecto.toLowerCase() + "/mvc/views/PrincipalView.java");

  }

}