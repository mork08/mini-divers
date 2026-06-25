package my_project.view;

import KAGO_framework.model.InteractiveGraphicalObject;
import my_project.control.ProgramController;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Realisiert ein Objekt, dass alle Eingaben empfängt und dann danach passende Methoden
 * im ProgramController aufruft.
 */
public class InputManager extends InteractiveGraphicalObject {
    public static boolean[] keys = new boolean[65489];



    private final ProgramController controller;

    /**
     * Objekterzeugung
     * @param controller Nötig als Objekt vom Controllerbereich, das informiert wird
     */
    public InputManager(ProgramController controller){
        this.controller = controller;

    }

    public static boolean isPressed(int key) {
        return keys[key];
    }
    public static boolean isPressed(String key) {
        return keys[KeyEvent.getExtendedKeyCodeForChar(key.charAt(0))];
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void keyPressed (int key){
        keys[key] = true;
        /*
        switch (key){
            case KeyEvent.VK_ESCAPE : mainController.processInput("settings");
                break;
            case KeyEvent.VK_O : mainController.processInput("zoomOut");
                break;
            case KeyEvent.VK_I : mainController.processInput("zoomIn");
                break;
            case KeyEvent.VK_P : mainController.processInput("centerCamera");
        }

         */
    }
    @Override
    public void keyReleased(int key) {
        keys[key] = false;
    }


}
