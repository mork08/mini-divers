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

    private final ProgramController programController;

    /**
     * Objekterzeugung
     * @param programController Nötig als Objekt vom Controllerbereich, das informiert wird
     */
    public InputManager(ProgramController programController){
        this.programController = programController;

    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_LEFT){programController.setTranslateX(programController.getTranslateX() + 10);}
        else if(key == KeyEvent.VK_RIGHT){programController.setTranslateX(programController.getTranslateX() - 10);}
        else if(key == KeyEvent.VK_UP){programController.setTranslateY(programController.getTranslateY() + 10);}
        else if(key == KeyEvent.VK_DOWN){programController.setTranslateY(programController.getTranslateY() - 10);}
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

}
