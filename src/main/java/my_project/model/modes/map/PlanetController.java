package my_project.model.modes.map;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.*;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.view.DrawTool;
import my_project.model.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class PlanetController extends GraphicalObject {
    public Graph<Planet> planets;
    private List<Vertex<Planet>> planetList;
    private List<Edge<Planet>> planetEdgeList;
    private int planetCount = 400;
    private int planetSpawnRadius = 5000;
    private MapMode mapMode;
    private Vertex<Planet> currentPlanet;

    private boolean easeIn = true;
    private double radius = 5;
    private DrawTool drawTool;
    private double cooldown = 0;

    private String[] occupations = {"Terminis", "Iluminis", "MiniBots"};
    private double occupationBudget = 20000;

    public PlanetController(MapMode mapMode) {
        this.mapMode = mapMode;
        planets = new Graph<Planet>();

        initiatePlanetInGraph();
        //addEdgesToGraph();
        connectIsland();
        planetEdgeList = planets.getEdges();

        currentPlanet = planets.getVertex("0");
        mapMode.setCurrentPlanet(currentPlanet.getContent());
        spreadOccupation();
    }

    @Override
    public void draw(DrawTool drawTool) {
        this.drawTool = drawTool;

        drawTool.setTranslate(MapMode.getTranslateX(),  MapMode.getTranslateY());
        drawTool.setScale(MapMode.getScale());

        //Draw Edges
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

        //Let Planet be drawn
        planetList.toFirst();
        while(planetList.hasAccess()) {
            if(Objects.equals(currentPlanet, planetList.getContent())) {
                drawTool.setCurrentColor(new Color(255, 255, 255, 255));
                drawTool.drawFilledCircle(planetList.getContent().getContent().getX(), planetList.getContent().getContent().getY(), planetList.getContent().getContent().getRadius() + radius);
            }
            planetList.getContent().getContent().draw(drawTool);
            planetList.next();
        }

        planetList.toFirst();
        while(planetList.hasAccess()) {
            planetList.getContent().getContent().drawUI(drawTool);
            planetList.next();
        }
    }

    @Override
    public void update(double dt) {
        mapMode.setCurrentPlanet(currentPlanet.getContent());
        if(mapMode.getSpaceShip().getReady()) cooldown = 0;
        if(easeIn) radius -= dt * 10;
        else radius += dt * 10;
        if(radius < 2) easeIn = false;
        if(radius > 10) easeIn = true;
    }

    private void initiatePlanetInGraph() {
        for(int i = 0;i < planetCount;i++) {
            Vertex<Planet> planet = new Vertex<>(String.valueOf(i));
            boolean fitting = false;
            while(!fitting) {
                fitting = true;
                double r = planetSpawnRadius * Math.sqrt(Math.random());
                double alpha = 2 * Math.PI * Math.random();
                Planet newPlanet = new Planet(r * Math.cos(alpha) + 500, r * Math.sin(alpha) + 500, PlanetInfoContainer.getPlanetSize());
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
                if(distance < 700 + (int)(Math.random()*200) && (int)(Math.random() * 100) < 2) {
                    planets.addEdge(new Edge<Planet>(planets.getVertex(String.valueOf(i)), planets.getVertex(String.valueOf(j)), distance));
                }
            }
        }
    }

    //TODO: When going to Planet, animate Path with Edges (A*)

    private void connectIsland() {
        planets.setAllVertexMarks(false);
        planetList.toFirst();
        List<Vertex<Planet>> island = new List<>();
        modifiedBFS(island, planetList.getContent());
        while(!planets.allVerticesMarked()) {
            planetList.toFirst();
            double minDistance = Double.MAX_VALUE;
            Vertex<Planet>[] connectionVertices = new Vertex[2];
            while(planetList.hasAccess()) {
                if (!planetList.getContent().isMarked()) {
                    island.toFirst();
                    while (island.hasAccess()) {
                        double newDistance = planetList.getContent().getContent().getDistanceTo(island.getContent().getContent());
                        if (newDistance < minDistance) {
                            //if ((int)(Math.random() * 100) < 1) planets.addEdge(new Edge<Planet>(connectionVertices[0], connectionVertices[1], minDistance));
                            minDistance = newDistance;
                            connectionVertices[0] = island.getContent();
                            connectionVertices[1] = planetList.getContent();
                        }
                        island.next();
                    }
                }
                planetList.next();
            }

            List<Vertex<Planet>> tempIsland = new List<>();
            modifiedBFS(tempIsland, connectionVertices[1]);

            island.concat(tempIsland);
            planets.addEdge(new Edge<Planet>(connectionVertices[0], connectionVertices[1], minDistance));
        }
/*
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
*/
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

    public void checkForContactOnClick(MouseEvent e) {
        if(cooldown > 0) return;
        double mouseX = (e.getX()/drawTool.getScaleX()) - drawTool.getTranslationX();
        double mouseY = (e.getY()/drawTool.getScaleY()) - drawTool.getTranslationY();
        planetList.toFirst();
        while(planetList.hasAccess()) {
            Planet p = planetList.getContent().getContent();
            if(Math.sqrt( Math.pow(mouseX-p.getX(), 2) + Math.pow(mouseY-p.getY(),2)) <= p.getRadius()) {
                //System.out.println(mouseX+","+mouseY+","+p.getRadius());
                if(currentPlanet == planetList.getContent()) {
                    mapMode.startMission();
                    return;
                }

                cooldown = 1;
                List<Vertex<Planet>> path = dijkstra(planets, currentPlanet, planetList.getContent());
                planets.setAllEdgeMarks(false);
                path.toFirst();
                while(path.hasAccess()) {
                    Vertex<Planet> current = path.getContent();
                    path.next();
                    if(path.hasAccess()) planets.getEdge(current, path.getContent()).setMark(true);
                }
                mapMode.getSpaceShip().moveOnPath(path);
                currentPlanet = planetList.getContent();
                return;
            }
            planetList.next();
        }
    }

    public void checkForHover(MouseEvent e) {
        if(drawTool == null) return;
        double mouseX = (e.getX()/drawTool.getScaleX()) - drawTool.getTranslationX();
        double mouseY = (e.getY()/drawTool.getScaleY()) - drawTool.getTranslationY();
        planetList.toFirst();
        while(planetList.hasAccess()) {
            Planet p = planetList.getContent().getContent();
            if(Math.sqrt(Math.pow(mouseX-p.getX(), 2) + Math.pow(mouseY-p.getY(),2)) <= p.getRadius()) {
                p.setNameShowing(true);
            }else {
                p.setNameShowing(false);
            }
            planetList.next();
        }
    }

    private void spreadOccupation() {
        planetList.toFirst();
        planetList.next();
        for(int i = 0;i < occupations.length;i++) {
            double tempBudget = occupationBudget;
            //planetList.getContent().getContent().setOccupation(occupations[i]);
            planets.setAllVertexMarks(false);
            Queue<Vertex<Planet>> queue = new Queue<>();
            while(!planetList.getContent().getContent().getOccupation().equals("MiniEarth")) {
                planetList.next();
            }
            planetList.getContent().getContent().setOccupation(occupations[i]);
            queue.enqueue(planetList.getContent());
            while(!queue.isEmpty()) {

                queue.front().setMark(true);

                List<Vertex<Planet>> neighbours = planets.getNeighbours(queue.front());
                neighbours.toFirst();
                while(neighbours.getContent() != null && (!neighbours.isEmpty() || neighbours.hasAccess())) {
                    if(!neighbours.getContent().isMarked() && neighbours.getContent().getContent().getOccupation().equals("MiniEarth")) {
                        double cost = planets.getEdge(queue.front(), neighbours.getContent()).getWeight();
                        if (cost < tempBudget) {
                            tempBudget -= cost;
                            neighbours.getContent().getContent().setOccupation(occupations[i]);
                            queue.enqueue(neighbours.getContent());
                        }
                    }
                    neighbours.next();
                }
                queue.dequeue();
                if(tempBudget < 100) {
                    break;
                }
            }
            planetList.next();
        }
    }

    public List<Vertex<Planet>> dijkstra(Graph<Planet> pGraph, Vertex<Planet> startVertex, Vertex<Planet> pZiel) {
        pGraph.setAllVertexMarks(false);
        pGraph.setDistanceForAll(Double.MAX_VALUE);
        pGraph.setPrevToNull();
        startVertex.setPathDistance(0);
        List<Vertex<Planet>> list = new List<>();
        list.append(startVertex);

        while(!list.isEmpty()) {
            list.toFirst();
            Vertex<Planet> smallestVertex = list.getContent();
            while(list.hasAccess()) {
                if(list.getContent().getPathDistance() < smallestVertex.getPathDistance()) {
                    smallestVertex = list.getContent();
                    break;
                }
                list.next();
            }
            list.toFirst();
            while(list.hasAccess()) {
                if(list.getContent() == smallestVertex) {
                    list.remove();
                    break;
                }
                list.next();
            }

            smallestVertex.setMark(true);
            if(smallestVertex == pZiel) break;

            List<Vertex<Planet>> neighbors = pGraph.getNeighbours(smallestVertex);
            neighbors.toFirst();
            while(neighbors.hasAccess()) {
                if(!neighbors.getContent().isMarked()) {
                    double newDistance = smallestVertex.getPathDistance() + pGraph.getEdge(smallestVertex, neighbors.getContent()).getWeight();
                    if(neighbors.getContent().getPathDistance() > newDistance) {
                        neighbors.getContent().setPathDistance(newDistance);
                        if(neighbors.getContent().getPrev() == null) list.append(neighbors.getContent());
                        neighbors.getContent().setPrev(smallestVertex);
                    }
                }
                neighbors.next();
            }
        }

        Stack<Vertex<Planet>> stack = new Stack<>();
        Vertex<Planet> backTracker = pZiel;
        stack.push(backTracker);
        while(backTracker.getPrev() != null) {
            backTracker = backTracker.getPrev();
            stack.push(backTracker);
        }
        List<Vertex<Planet>> path = new List<>();
        while(!stack.isEmpty()) {
            path.append(stack.top());
            stack.pop();
        }
        return path;
    }
}
