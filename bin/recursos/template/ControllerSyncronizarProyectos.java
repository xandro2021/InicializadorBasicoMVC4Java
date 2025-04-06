package com.syncronizarworkspace.mvc.controllers;

import com.syncronizarworkspace.mvc.views.Marco;

public class ControllerSyncronizarProyectos {
  private Marco marco;

  public ControllerSyncronizarProyectos() {
    marco = new Marco();
  }

  public void init() {
    marco.init();
  }

}