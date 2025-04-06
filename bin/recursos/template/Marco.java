package com.syncronizarworkspace.mvc.views;

import javax.swing.JFrame;

public class Marco extends JFrame {
  // public LaminaPrincipal principal;

  public Marco() {
    setTitle("syncronizarworkspace");
    setBounds(450, 350, 550, 500);
    // principal = new LaminaPrincipal();
    // add(principal);
  }

  public void init() {
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

}