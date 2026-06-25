package my_project.control;

import KAGO_framework.control.ViewController;


import my_project.model.modes.galaxyMap.GalaxyMapPlanetInfoContainer;
import my_project.model.modes.planet.Tilesystem.SurfaceMapContainer;
import my_project.model.modes.planet.entity.EntityPlayer;
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


    // Referenzen
    private final ViewController viewController;  // diese Referenz soll auf ein Objekt der Klasse viewController zeigen. Über dieses Objekt wird das Fenster gesteuert.
    private ModeController modeController; // deklariert eine Referenz für ein Objekt der Klasse House
    private Mouse mouse;
    private InputManager inputManager;
    private SurfaceMapContainer surfaceMapContainer;

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
        new EntityPlayer("player", 64, 64, 32, 32);
        surfaceMapContainer = new SurfaceMapContainer();
        modeController = new ModeController();
        mouse = new Mouse();
        inputManager = new InputManager(this);

        GalaxyMapPlanetInfoContainer galaxyMapPlanetInfoContainer = new GalaxyMapPlanetInfoContainer();
        // Teile dem ViewController-Objekt mit, dass das House-Objekt gezeichnet werden soll
        viewController.draw(modeController);
        viewController.draw(mouse);
        viewController.register(mouse);
        viewController.register(inputManager);
        viewController.register(modeController);
    }

    /**
     * Diese Methode wird vom ViewController-Objekt automatisch mit jedem Frame aufgerufen (ca. 60mal pro Sekunde)
     * @param dt Zeit seit letztem Frame in Sekunden
     */
    public void updateProgram(double dt){

    }
}
