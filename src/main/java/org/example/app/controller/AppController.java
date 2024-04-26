package org.example.app.controller;

import org.example.app.service.AppService;
import org.example.app.view.UserView;

public class AppController {

    UserView view;
    AppService service;

    public AppController(UserView view, AppService service) {
        this.view = view;
        this.service = service;
    }

    public void runApp() {
        service.handleOption(view.getOption());
    }
}
