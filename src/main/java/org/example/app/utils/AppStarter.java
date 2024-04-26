package org.example.app.utils;

import org.example.app.controller.AppController;
import org.example.app.service.AppService;
import org.example.app.view.UserView;

public class AppStarter {

    public static void startApp() {
        AppService service = new AppService();
        UserView view = new UserView();
        AppController controller = new AppController(view, service);
        controller.runApp();
    }
}
