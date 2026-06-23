package my_project.model.modes.map;

import KAGO_framework.view.DrawTool;
import beckerStructures.BeckerMap;
import my_project.model.spritesheetSystem.QuadSheet;

import java.awt.image.BufferedImage;

public class PlanetInfoContainer {
    private static String[] firstPart = {"Duran","Thon","Undr","En","Sind","Ecrun","Dat","Accind","Hophor","Cean","Liph","Chanaril","Don","Grill","Xepr","Tros","At","Geat"};
    private static String[] secondPart = {"ov","eth","us","ia","ea","uno","on","es","is","i"};
    private static String[] thirdPart = {"M","G","Z","B","K","Δ","α","∑","π","Æ","Q","F","23W","7H","E"};
    private static String[] fourthPart = {"127","35B","64-STK","13","42","67","69","33J","13A-or-B","2","17","52","025","39","612"};

    private static String[] terrainTypes = {"rocky","sandy","earthlike"};
    private static double[] planetSize = {20,30,40,50};

    private static BeckerMap<String ,QuadSheet> planetTextures;


    public PlanetInfoContainer() {
        planetTextures = new BeckerMap<>();
        setUpPlanetTexture("sandy");
        setUpPlanetTexture("earthlike");
        setUpPlanetTexture("rocky");
    }
    public static BufferedImage getPlanetTexture(String terrainType, double size) {
        for (int i = 0; i < planetSize.length; i++) {

            if (size == planetSize[i]) {
                if (planetTextures.contains(terrainType)) {

                    return planetTextures.get(terrainType).getSprite(i);

                }
            }
        }
        return null;
    }
    private void setUpPlanetTexture(String terrainType){
        String path = "src/main/resources/graphic/planet textures/" + terrainType + ".png";
        BufferedImage img = DrawTool.getNewImage(path);
        if (img == null) return;
        QuadSheet sheet = new QuadSheet(img);
        for (int i = 0; i < img.getWidth() / img.getHeight(); i++) {
            sheet.addQuad(i * img.getHeight(), 0, img.getHeight(), img.getHeight());
        }
        planetTextures.add(terrainType, sheet);
    }

    public static String generateName() {
        if(Math.random()<0.7) {
            return firstPart[(int) (Math.random() * firstPart.length)] + secondPart[(int) (Math.random() * secondPart.length)] + " " +
                    thirdPart[(int) (Math.random() * thirdPart.length)] + fourthPart[(int) (Math.random() * fourthPart.length)];
        }else {
            return firstPart[(int) (Math.random() * firstPart.length)] + secondPart[(int) (Math.random() * secondPart.length)];
        }
    }

    public static double getPlanetSize() {
        return planetSize[(int) (Math.random() * planetSize.length)];
    }

    public static String getTerrainType() {
        return terrainTypes[(int) (Math.random() * terrainTypes.length)];
    }
}
