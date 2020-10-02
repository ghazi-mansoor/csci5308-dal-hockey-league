package com.groupten.injector;

import com.groupten.console.Console;
import com.groupten.console.ConsoleInterface;
import com.groupten.json.JSON;
import com.groupten.json.JSONInterface;
import com.groupten.useraction.UserAction;
import com.groupten.useraction.UserActionInterface;

public class Injector {

    private static Injector injector = null;
    private ConsoleInterface consoleInterface;
    private JSONInterface jsonInterface;
    private UserActionInterface userActionInterface;

    private Injector() {
        consoleInterface = new Console();
        jsonInterface = new JSON();
        userActionInterface = new UserAction();
    }

    public static Injector injector(){
        if(injector == null) {
            injector = new Injector();
        }

        return injector;
    }

    public static void setInjector(Injector injector) {
        Injector.injector = injector;
    }

    public void setConsoleObject(ConsoleInterface consoleInterface) {
        this.consoleInterface = consoleInterface;
    }

    public void setJSONObject(JSONInterface jsonInterface) {
        this.jsonInterface = jsonInterface;
    }

    public void setUserActionObject(UserActionInterface userActionInterface) {
        this.userActionInterface = userActionInterface;
    }

    public ConsoleInterface getConsoleObject() {
        return consoleInterface;
    }

    public JSONInterface getJSONObject() {
        return jsonInterface;
    }

    public UserActionInterface getUserActionObject() {
        return userActionInterface;
    }

}
