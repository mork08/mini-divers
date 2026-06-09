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
import java.awt.event.MouseEvent;
import java.util.Objects;

public class PlanetController extends GraphicalObject {
    public Graph<Planet> planets;
    private List<Vertex<Planet>> planetList;
    private List<Edge<Planet>> planetEdgeList;
    private int planetCount = 200;
    private MapMode mapMode;
    private Planet currentPlanet;

    private boolean easeIn = true;
    private double radius = 5;
    private DrawTool drawTool;

    public PlanetController(MapMode mapMode) {
        this.mapMode = mapMode;
        planets = new Graph<Planet>();

        initiatePlanetInGraph();
        addEdgesToGraph();

        currentPlanet = planets.getVertex("0").getContent();
        mapMode.setCurrentPlanet(currentPlanet);
    }

    private void initiatePlanetInGraph() {
        for(int i = 0;i < planetCount;i++) {
            Vertex<Planet> planet = new Vertex<>(String.valueOf(i));
            boolean fitting = false;
            while(!fitting) {
                fitting = true;
                Planet newPlanet = new Planet(Math.random() * 10000, Math.random() * 10000, Math.random() * 30 + 20);
                planetList = planets.getVertices();
                planetList.toFirst();
                while(!planetList.isEmpty() && planetList.hasAccess()) {
                    Planet checkingPlanet = planetList.getContent().getContent();
                    if(newPlanet.getDistanceTo(checkingPlanet) < (newPlanet.getRadius() + checkingPlanet.getRadius()) * 2) {
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
    }

    private void addEdgesToGraph() {
        for(int i = 0;i < planetCount;i++) {
            for (int j = 0; j < planetCount; j++) {
                if(i == j) continue;
                double distance = planets.getVertex(String.valueOf(i)).getContent().getDistanceTo(planets.getVertex(String.valueOf(j)).getContent());
                if(distance < 500) {
                    planets.addEdge(new Edge<>(planets.getVertex(String.valueOf(i)), planets.getVertex(String.valueOf(j)), distance));
                }
            }
        }
        connectIsland();
        planetEdgeList = planets.getEdges();
    }

    //TODO: When going to Planet, animate Path with Edges (A*)

    @Override
    public void draw(DrawTool drawTool) {
        this.drawTool = drawTool;

        drawTool.setTranslate(MapMode.getTranslateX(),  MapMode.getTranslateY());
        drawTool.setScale(MapMode.getScale());

        planetEdgeList.toFirst();
        while(planetEdgeList.hasAccess()) {
            if(planetEdgeList.getContent().isMarked()) {
                drawTool.setCurrentColor(new Color(122, 222, 253));
            }else {
                drawTool.setCurrentColor(new Color(43, 42, 42));
            }
            Vertex<Planet>[] tempTwoPlanets = planetEdgeList.getContent().getVertices();
            drawTool.drawLine(tempTwoPlanets[0].getContent().getX(), tempTwoPlanets[0].getContent().getY(), tempTwoPlanets[1].getContent().getX(), tempTwoPlanets[1].getContent().getY());
            planetEdgeList.next();
        }

        planetList.toFirst();
        while(planetList.hasAccess()) {
            if(Objects.equals(currentPlanet, planetList.getContent().getContent())) {
                drawTool.setCurrentColor(new Color(255, 255, 255, 255));
                drawTool.drawFilledCircle(planetList.getContent().getContent().getX(), planetList.getContent().getContent().getY(), planetList.getContent().getContent().getRadius() + radius);
            }
            planetList.getContent().getContent().draw(drawTool);
            planetList.next();
        }
    }

    @Override
    public void update(double dt) {
        mapMode.setCurrentPlanet(currentPlanet);

        if(easeIn) radius -= dt * 7;
        else radius += dt * 7;
        if(radius < 2) easeIn = false;
        if(radius > 5) easeIn = true;
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

    public void checkForContact(MouseEvent e) {
        double mouseX = (e.getX()/drawTool.getScaleX()) - drawTool.getTranslationX();
        double mouseY = (e.getY()/drawTool.getScaleY()) - drawTool.getTranslationY();
        planetList.toFirst();
        while(planetList.hasAccess()) {
            Planet p = planetList.getContent().getContent();
            if(Math.sqrt( Math.pow(mouseX-p.getX(), 2) + Math.pow(mouseY-p.getY(),2)) <= p.getRadius()) {
                System.out.println(mouseX+","+mouseY+","+p.getRadius());
                currentPlanet = p;
            }
            planetList.next();
        }
    }
}
