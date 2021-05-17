package com.fv.ContainerApp.View;

import com.fv.ContainerApp.Controller.Controller;

import java.util.Arrays;

/**
 * The Tester class just has the static main method and runs the init method from the
 * Controller class and displays the application in the terminal
 *
 * @author Fabian Valerius
 * @version 1.0.2
 */

public class Tester {

    public static void main(String[] args) {
        Controller containerApp = new Controller();

        System.out.println("   ______            __        _                                    \n" +
                "  / ____/___  ____  / /_____ _(_)___  ___  _____   ____ _____  ____ \n" +
                " / /   / __ \\/ __ \\/ __/ __ `/ / __ \\/ _ \\/ ___/  / __ `/ __ \\/ __ \\\n" +
                "/ /___/ /_/ / / / / /_/ /_/ / / / / /  __/ /     / /_/ / /_/ / /_/ /\n" +
                "\\____/\\____/_/ /_/\\__/\\__,_/_/_/ /_/\\___/_/      \\__,_/ .___/ .___/ \n" +
                "                                                     /_/   /_/      ");

     try {
            containerApp.init();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong , please call your java developer and show him the message " +
                    "up below.");
        }

    }

}
