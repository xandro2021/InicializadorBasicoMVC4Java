package com.syncronizarworkspace.mvc.views;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class LaminaPrincipal extends JPanel {

  private JPanel cajaVertical;
  private String urlEclipseWorkspace, urlDoomWorkspace;
  public ArrayList<JTextField> campos;
  public JButton btn_doom_eclipse;
  public JButton btn_eclipse_doom;

  public LaminaPrincipal() {

    setLayout(new BorderLayout());

    urlEclipseWorkspace = "/home/xandro/Documentos/Java/EclipseWorkSpace/";
    urlDoomWorkspace = "/home/xandro/Documentos/Java/DoomEmacsWorkSpace/";
    campos = new ArrayList<>(5);

    cajaFormulario();

  }

  private JPanel crearCajaHorizontalFileE(String etiqueta, String urlWorkspace) {

    JLabel rotulo1 = new JLabel(etiqueta);

    JTextField texto1 = new JTextField(35);
    texto1.setText(urlWorkspace);
    campos.add(texto1);

    JPanel caja = new JPanel(new GridLayout(2, 1));

    JPanel celda1 = new JPanel();
    celda1.add(rotulo1);

    JPanel celda2 = new JPanel();
    celda2.add(texto1);


    JButton btn = new JButton("Buscar");
    btn.addActionListener(new AbrirFileExplorer(texto1));
    celda2.add(btn);

    caja.add(celda1);
    caja.add(celda2);

    return caja;
  }

  private JPanel crearCajaNombreProyecto() {

    JLabel rotulo1 = new JLabel("Nombre del Proyecto");

    JTextField texto1 = new JTextField(35);
    texto1.addKeyListener(new NombrarProyecto());

    JPanel caja = new JPanel(new GridLayout(2, 1));

    JPanel celda1 = new JPanel();
    celda1.add(rotulo1);

    JPanel celda2 = new JPanel();
    celda2.add(texto1);

    caja.add(celda1);
    caja.add(celda2);

    return caja;
  }

  private void cajaFormulario() {

    cajaVertical = new JPanel(new GridLayout(4, 1));

    cajaVertical.add(crearCajaNombreProyecto());
    cajaVertical.add(crearCajaHorizontalFileE("Workspace Eclipse", urlEclipseWorkspace));
    cajaVertical.add(crearCajaHorizontalFileE("Workspace DoomEmacs", urlDoomWorkspace));

    cajaVertical.add(agregarBotones());

    cajaVertical.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK, 2));
    add(cajaVertical, BorderLayout.CENTER);

  }

  private JPanel agregarBotones() {
    JPanel lamina_botones = new JPanel();
    btn_doom_eclipse = new JButton("Doom to Eclipse");
    btn_eclipse_doom = new JButton("Eclipse to Doom");
    lamina_botones.add(btn_doom_eclipse);
    lamina_botones.add(btn_eclipse_doom);
    return lamina_botones;
  }

  private class AbrirFileExplorer implements ActionListener {
    private JTextField pathField;

    public AbrirFileExplorer(JTextField pathField) {
      this.pathField = pathField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Solo directorios
      fileChooser.setAcceptAllFileFilterUsed(false);

      int returnValue = fileChooser.showOpenDialog(null);

      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFolder = fileChooser.getSelectedFile();
        pathField.setText(selectedFolder.getAbsolutePath().concat("/"));
      }

    }
  }

  private class NombrarProyecto extends KeyAdapter {
    private String proyect_name;

    public NombrarProyecto() {
      proyect_name = "";
    }

    @Override
    public void keyTyped(KeyEvent e) {

      proyect_name = ((JTextField) e.getSource()).getText() + e.getKeyChar();

      for (JTextField campo : campos) {
        campo.setText(campo.getText().substring(0, campo.getText().lastIndexOf("/") + 1) + proyect_name);
      }

    }
  }

}
