package com.syncronizarworkspace.mvc.controllers;

import com.syncronizarworkspace.mvc.views.PrincipalView;

public class ControllerProyectos {
  private PrincipalView v;

  public ControllerProyectos() {
    v = new PrincipalView();
  }

  public void init() {
    v.init();
  }

}
