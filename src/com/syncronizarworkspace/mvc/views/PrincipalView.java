package com.syncronizarworkspace.mvc.views;

import javax.swing.JFrame;

public class PrincipalView extends JFrame {
  public LaminaPrincipal principal;

  public PrincipalView() {
    setTitle("Syncronizar Workspaces");
    setBounds(450, 350, 550, 500);
    principal = new LaminaPrincipal();
    add(principal);
  }

  public void init() {
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

}