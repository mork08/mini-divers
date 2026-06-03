package my_project.model.modes.map;

import KAGO_framework.control.Drawable;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.Edge;
import KAGO_framework.model.abitur.datenstrukturen.Graph;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import KAGO_framework.model.abitur.datenstrukturen.Vertex;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;
import my_project.model.*;

import java.awt.*;

public class PlanetController extends GraphicalObject {
    public Graph<Planet> planets;
    private List<Vertex<Planet>> planetList;
    private List<Edge<Planet>> planetEdgeList;
    private int planetCount = 10;

    public PlanetController() {
        planets = new Graph<Planet>();
        for(int i = 0;i < planetCount;i++) {
            Vertex<Planet> planet = new Vertex<>(String.valueOf(i));
            boolean fitting = false;
            while(!fitting) {
                fitting = true;
                Planet newPlanet = new Planet(Math.random() * 1000, Math.random() * 1000, Math.random() * 30 + 20);
                planetList = planets.getVertices();
                planetList.toFirst();
                while(!planetList.isEmpty() || planetList.hasAccess()) {
                    Planet checkingPlanet = planetList.getContent().getContent();
                    if(newPlanet.getDistanceTo(checkingPlanet) < (newPlanet.getRadius() + checkingPlanet.getRadius()) * 1) {
                        fitting = false;
                        break;
                    }
                    planetList.next();
                }
                if(fitting) {
                    planet.setContent(newPlanet);
                    planets.addVertex(planet);
                }
            }
        }
        planetList = planets.getVertices();

        for(int i = 0;i < planetCount;i++) {
            for (int j = 0; j < planetCount; j++) {
                if(i == j) continue;
                double distance = planets.getVertex(String.valueOf(i)).getContent().getDistanceTo(planets.getVertex(String.valueOf(j)).getContent());
                if(distance < 400) {
                    planets.addEdge(new Edge<>(planets.getVertex(String.valueOf(i)), planets.getVertex(String.valueOf(j)), distance));
                }
            }
        }
        connectIsland();
        planetEdgeList = planets.getEdges();
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(0,0,0));
        drawTool.drawFilledRectangle(0,0,1000,1000);

        drawTool.push();
        drawTool.setTranslate(MapMode.getTranslateX(),  MapMode.getTranslateY());

        planetEdgeList.toFirst();
        drawTool.setCurrentColor(new Color(122, 222, 253));
        while(planetEdgeList.hasAccess()) {
            Vertex<Planet>[] tempTwoPlanets = planetEdgeList.getContent().getVertices();
            drawTool.drawLine(tempTwoPlanets[0].getContent().getX(), tempTwoPlanets[0].getContent().getY(), tempTwoPlanets[1].getContent().getX(), tempTwoPlanets[1].getContent().getY());
            planetEdgeList.next();
        }

        planetList.toFirst();
        while(planetList.hasAccess()) {
            planetList.getContent().getContent().draw(drawTool);
            planetList.next();
        }

        drawTool.pop();
    }

    @Override
    public void update(double dt) {

    }

    private void connectIsland() {
        planets.setAllVertexMarks(false);
        planetList.toFirst();
        List<Vertex<Planet>> island = new List<>();
        modifiedBFS(island, planetList.getContent());
        while(planetList.hasAccess()) {
            if(planetList.getContent().isMarked()) {
                planetList.next();
                continue;
            }

            List<Vertex<Planet>> tempIsland = new List<>();
            modifiedBFS(tempIsland, planetList.getContent());

            double minDistance = Double.MAX_VALUE;
            Vertex<Planet>[] connectionVertices = new Vertex[2];
            connectionVertices[0] = planetList.getContent();
            connectionVertices[1] = planetList.getContent();
            island.toFirst();
            while(island.hasAccess()) {
                tempIsland.toFirst();
                while(tempIsland.hasAccess()) {
                    double distance = island.getContent().getContent().getDistanceTo(tempIsland.getContent().getContent());
                    if(distance < minDistance) {
                        minDistance = distance;
                        connectionVertices[0] = island.getContent();
                        connectionVertices[1] = tempIsland.getContent();
                    }
                    tempIsland.next();
                }
                island.next();
            }
            island.concat(tempIsland);
            planets.addEdge(new Edge<>(connectionVertices[0], connectionVertices[1], minDistance));
        }
    }

    private void modifiedBFS(List<Vertex<Planet>> island, Vertex<Planet> start) {
        Queue<Vertex<Planet>> queue = new Queue<>();
        queue.enqueue(start);
        while(!queue.isEmpty()) {

            island.append(queue.front());
            queue.front().setMark(true);

            List<Vertex<Planet>> list = planets.getNeighbours(queue.front());
            list.toFirst();
            while(list.getContent() != null && (!list.isEmpty() || list.hasAccess())) {
                if(!list.getContent().isMarked()) {
                    queue.enqueue(list.getContent());
                }
                list.next();
            }
            queue.dequeue();
        }
    }
}
