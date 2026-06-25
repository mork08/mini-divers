package my_project.view;

import KAGO_framework.model.InteractiveGraphicalObject;
import my_project.control.ProgramController;
import my_project.model.modes.galaxyMap.GalaxyMapMode;
import my_project.model.modes.planet.entity.EntityManager;

import java.awt.event.KeyEvent;

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
        if(key == KeyEvent.VK_LEFT){
            GalaxyMapMode.setTranslateX(1);}
        else if(key == KeyEvent.VK_RIGHT){
            GalaxyMapMode.setTranslateX(-1);}
        else if(key == KeyEvent.VK_UP){
            GalaxyMapMode.setTranslateY(1);}
        else if(key == KeyEvent.VK_DOWN){
            GalaxyMapMode.setTranslateY(-1);}
        else if(key == KeyEvent.VK_PLUS){
            GalaxyMapMode.setScale(1);}
        else if(key == KeyEvent.VK_MINUS){
            GalaxyMapMode.setScale(-1);}

        EntityManager.keypressedCallback(key);
    }

    @Override
    public void keyReleased(int key) {
        if(key == KeyEvent.VK_LEFT){
            GalaxyMapMode.setTranslateX(0);}
        else if(key == KeyEvent.VK_RIGHT){
            GalaxyMapMode.setTranslateX(0);}
        else if(key == KeyEvent.VK_UP){
            GalaxyMapMode.setTranslateY(0);}
        else if(key == KeyEvent.VK_DOWN){
            GalaxyMapMode.setTranslateY(0);}
        else if(key == KeyEvent.VK_PLUS){
            GalaxyMapMode.setScale(0);}
        else if(key == KeyEvent.VK_MINUS){
            GalaxyMapMode.setScale(0);}
    }

}
