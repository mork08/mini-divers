package my_project.model.modes.map;

public class PlanetNames {
    private static String[] firstPart = {"Duran","Thon","Undr","En","Sind","Ecrun","Dat","Accind","Hophor","Cean","Liph","Chanaril","Don","Grill","Xepr","Tros"};
    private static String[] secondPart = {"ov","eth","us","ia","ea","uno","on","es","is","i"};
    private static String[] thirdPart = {"M","G","Z","B","K","Δ","α","∑","π","Æ","Q","F","23W","7H","E"};
    private static String[] fourthPart = {"127","35B","64-STK","13","42","67","69","33J","13A-or-B","2","17","52","025","39","612"};

    private static String[] terrainTypes = {"flat","hilly"};
    private static double[] planetSize = {20,30,40,50};

    public PlanetNames() {

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
