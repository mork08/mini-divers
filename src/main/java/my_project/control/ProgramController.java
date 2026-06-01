package my_project.control;

import KAGO_framework.control.ViewController;


import my_project.model.modes.map.PlanetController;
import my_project.view.InputManager;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern.
 * Hinweise:
 * - Der Konstruktor sollte nicht geändert werden.
 * - Sowohl die startProgram()- als auch die updateProgram(...)-Methoden müssen vorhanden sein und ihre Signatur sollte
 *   nicht geändert werden
 * - Zusätzliche Methoden sind natürlich gar kein Problem
 */
public class ProgramController {

    //Attribute
    private double translateX, translateY;

    // Referenzen
    private final ViewController viewController;  // diese Referenz soll auf ein Objekt der Klasse viewController zeigen. Über dieses Objekt wird das Fenster gesteuert.
    private ModeController modeController; // deklariert eine Referenz für ein Objekt der Klasse House
    private Mouse mouse;
    private InputManager inputManager;

    private PlanetController planetController;

    /**
     * Konstruktor
     * Dieser legt das Objekt der Klasse ProgramController an, das den Programmfluss steuert.
     * Damit der ProgramController auf das Fenster zugreifen kann, benötigt er eine Referenz auf das Objekt
     * der Klasse viewController. Diese wird als Parameter übergeben.
     * @param viewController das viewController-Objekt des Programms
     */
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen. Hier sollte also alles geregelt werden,
     * was zu diesem Zeipunkt passieren muss.
     */
    public void startProgram() {
        // Erstelle ein Objekt der Klasse House und initialisiere damit die Referenz house1
        modeController = new ModeController();
        mouse = new Mouse();
        inputManager = new InputManager(this);
        planetController = new PlanetController(this);
        // Teile dem ViewController-Objekt mit, dass das House-Objekt gezeichnet werden soll
        viewController.draw(modeController);
        viewController.draw(mouse);
        viewController.register(mouse);
        viewController.register(inputManager);
        //viewController.draw(planetController);
    }

    /**
     * Diese Methode wird vom ViewController-Objekt automatisch mit jedem Frame aufgerufen (ca. 60mal pro Sekunde)
     * @param dt Zeit seit letztem Frame in Sekunden
     */
    public void updateProgram(double dt){

    }

    public double getTranslateX(){return translateX;}
    public double getTranslateY(){return translateY;}
    public void setTranslateX(double translateX){this.translateX = translateX;}
    public void setTranslateY(double translateY){this.translateY = translateY;}
}
