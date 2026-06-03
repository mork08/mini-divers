package my_project.view;

import KAGO_framework.model.InteractiveGraphicalObject;
import my_project.control.ProgramController;
import my_project.model.modes.map.MapMode;

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
        if(key == KeyEvent.VK_LEFT){MapMode.setTranslateX(1);}
        else if(key == KeyEvent.VK_RIGHT){MapMode.setTranslateX(-1);}
        else if(key == KeyEvent.VK_UP){MapMode.setTranslateY(1);}
        else if(key == KeyEvent.VK_DOWN){MapMode.setTranslateY(-1);}
    }

    @Override
    public void keyReleased(int key) {
        if(key == KeyEvent.VK_LEFT){MapMode.setTranslateX(0);}
        else if(key == KeyEvent.VK_RIGHT){MapMode.setTranslateX(0);}
        else if(key == KeyEvent.VK_UP){MapMode.setTranslateY(0);}
        else if(key == KeyEvent.VK_DOWN){MapMode.setTranslateY(0);}
    }

}
