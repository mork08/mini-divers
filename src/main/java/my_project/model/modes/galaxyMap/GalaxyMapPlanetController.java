package my_project.model.modes.galaxyMap;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.*;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.view.DrawTool;
import my_project.model.AStar;
import my_project.model.AStarVertex;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class GalaxyMapPlanetController extends GraphicalObject {
    public Graph<GalaxyMapPlanet, AStarVertex<GalaxyMapPlanet>> planets;
    private List<AStarVertex<GalaxyMapPlanet>> planetList;
    private List<Edge<GalaxyMapPlanet, AStarVertex<GalaxyMapPlanet>>> planetEdgeList;
    private int planetCount = 400;
    private int planetSpawnRadius = 5000;
    private GalaxyMapMode galaxyMapMode;
    private AStarVertex<GalaxyMapPlanet> currentPlanet;

    private boolean easeIn = true;
    private double radius = 5;
    private DrawTool drawTool;
    private double cooldown = 0;

    private double mouseX = 0, mouseY = 0;

    private String[] occupations = {"Terminis", "Iluminis", "MiniBots"};
    private double occupationBudget = 20000;

    public GalaxyMapPlanetController(GalaxyMapMode galaxyMapMode) {
        this.galaxyMapMode = galaxyMapMode;
        planets = new Graph<>();

        initiatePlanetInGraph();
        //addEdgesToGraph();
        connectIsland();
        planetEdgeList = planets.getEdges();

        currentPlanet = planets.getVertex("0");
        galaxyMapMode.setCurrentPlanet(currentPlanet.getContent());
        spreadOccupation();
    }

    //TODO: When going to GalaxyMapPlanet, animate Path with Edges (A*)

    @Override
    public void draw(DrawTool drawTool) {
        this.drawTool = drawTool;

        drawTool.setTranslate(GalaxyMapMode.getTranslateX(),  GalaxyMapMode.getTranslateY());
        drawTool.setScale(GalaxyMapMode.getScale());

        //Draw Edges
        planetEdgeList.toFirst();
        while(planetEdgeList.hasAccess()) {
            if(planetEdgeList.getContent().isMarked()) {
                drawTool.setCurrentColor(new Color(122, 222, 253));
            }else {
                drawTool.setCurrentColor(new Color(43, 42, 42));
            }
            Vertex<GalaxyMapPlanet>[] tempTwoPlanets = planetEdgeList.getContent().getVertices();
            drawTool.drawLine(tempTwoPlanets[0].getContent().getX(), tempTwoPlanets[0].getContent().getY(), tempTwoPlanets[1].getContent().getX(), tempTwoPlanets[1].getContent().getY());
            planetEdgeList.next();
        }

        //Let GalaxyMapPlanet be drawn
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
        galaxyMapMode.setCurrentPlanet(currentPlanet.getContent());

        if(galaxyMapMode.getSpaceShip().getReady()) cooldown = 0;
        if(easeIn) radius -= dt * 10;
        else radius += dt * 10;
        if(radius < 2) easeIn = false;
        if(radius > 10) easeIn = true;
    }

    private void initiatePlanetInGraph() {
        for(int i = 0;i < planetCount;i++) {
            AStarVertex<GalaxyMapPlanet> planet = new AStarVertex(String.valueOf(i));
            boolean fitting = false;
            while(!fitting) {
                fitting = true;
                double r = planetSpawnRadius * Math.sqrt(Math.random());
                double alpha = 2 * Math.PI * Math.random();
                GalaxyMapPlanet newPlanet = new GalaxyMapPlanet(r * Math.cos(alpha) + 500, r * Math.sin(alpha) + 500, GalaxyMapPlanetInfoContainer.getPlanetSize());
                planetList = planets.getVertices();
                planetList.toFirst();
                while(!planetList.isEmpty() && planetList.hasAccess()) {
                    GalaxyMapPlanet checkingPlanet = planetList.getContent().getContent();
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
                    planets.addEdge(new Edge<GalaxyMapPlanet, AStarVertex<GalaxyMapPlanet>>(planets.getVertex(String.valueOf(i)), planets.getVertex(String.valueOf(j)), distance));
                }
            }
        }
    }

    //TODO: When going to Planet, animate Path with Edges (A*)

    private void connectIsland() {
        planets.setAllVertexMarks(false);
        planetList.toFirst();
        List<AStarVertex<GalaxyMapPlanet>> island = new List<>();
        modifiedBFS(island, planetList.getContent());
        while(!planets.allVerticesMarked()) {
            planetList.toFirst();
            double minDistance = Double.MAX_VALUE;
            AStarVertex<GalaxyMapPlanet>[] connectionVertices = new AStarVertex[2];
            while(planetList.hasAccess()) {
                if (!planetList.getContent().isMarked()) {
                    island.toFirst();
                    while (island.hasAccess()) {
                        double newDistance = planetList.getContent().getContent().getDistanceTo(island.getContent().getContent());
                        if (newDistance < minDistance) {
                            //if ((int)(Math.random() * 100) < 1) planets.addEdge(new Edge<GalaxyMapPlanet>(connectionVertices[0], connectionVertices[1], minDistance));
                            minDistance = newDistance;
                            connectionVertices[0] = island.getContent();
                            connectionVertices[1] = planetList.getContent();
                        }
                        island.next();
                    }
                }
                planetList.next();
            }

            List<AStarVertex<GalaxyMapPlanet>> tempIsland = new List<>();
            modifiedBFS(tempIsland, connectionVertices[1]);

            island.concat(tempIsland);
            planets.addEdge(new Edge<GalaxyMapPlanet, AStarVertex<GalaxyMapPlanet>>(connectionVertices[0], connectionVertices[1], minDistance));
        }
/*
        double minDistance = Double.MAX_VALUE;
        AStarVertex<GalaxyMapPlanet>[] connectionVertices = new AStarVertex[2];
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

    private void modifiedBFS(List<AStarVertex<GalaxyMapPlanet>> island, AStarVertex<GalaxyMapPlanet> start) {
        Queue<AStarVertex<GalaxyMapPlanet>> queue = new Queue<>();
        queue.enqueue(start);
        while(!queue.isEmpty()) {

            island.append(queue.front());
            queue.front().setMark(true);

            List<AStarVertex<GalaxyMapPlanet>> list = planets.getNeighbours(queue.front());
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

        planetList.toFirst();
        while(planetList.hasAccess()) {
            GalaxyMapPlanet p = planetList.getContent().getContent();
            if(Math.sqrt( Math.pow(mouseX-p.getX(), 2) + Math.pow(mouseY-p.getY(),2)) <= p.getRadius()) {
                //System.out.println(mouseX+","+mouseY+","+p.getRadius());
                if(currentPlanet == planetList.getContent()) {
                    galaxyMapMode.startMission();
                    return;
                }

                cooldown = 1;
                //List<AStarVertex<GalaxyMapPlanet>> path = dijkstra(planets, currentPlanet, planetList.getContent());
                List<AStarVertex<GalaxyMapPlanet>> path = new AStar(planets, currentPlanet, planetList.getContent()).findPath();
                planets.setAllEdgeMarks(false);
                path.toFirst();
                while(path.hasAccess()) {
                    AStarVertex<GalaxyMapPlanet> current = path.getContent();
                    path.next();
                    if(path.hasAccess()) planets.getEdge(current, path.getContent()).setMark(true);
                }
                galaxyMapMode.getSpaceShip().moveOnPath(path);
                currentPlanet = planetList.getContent();
                return;
            }
            planetList.next();
        }
    }

    public void checkForHover(MouseEvent e) {
        if(drawTool == null) return;

        planetList.toFirst();
        while(planetList.hasAccess()) {
            GalaxyMapPlanet p = planetList.getContent().getContent();
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
            Queue<AStarVertex<GalaxyMapPlanet>> queue = new Queue<>();
            while(!planetList.getContent().getContent().getOccupation().equals("MiniEarth")) {
                planetList.next();
            }
            planetList.getContent().getContent().setOccupation(occupations[i]);
            queue.enqueue(planetList.getContent());
            while(!queue.isEmpty()) {

                queue.front().setMark(true);

                List<AStarVertex<GalaxyMapPlanet>> neighbours = planets.getNeighbours(queue.front());
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
    public void setMousePos(double x, double y) {
        mouseX = x;
        mouseY = y;
    }

    public List<AStarVertex<GalaxyMapPlanet>> dijkstra(Graph<GalaxyMapPlanet, AStarVertex<GalaxyMapPlanet>> pGraph, AStarVertex<GalaxyMapPlanet> startVertex, AStarVertex<GalaxyMapPlanet> pZiel) {
        pGraph.setAllVertexMarks(false);
        pGraph.setDistanceForAll(Double.MAX_VALUE);
        pGraph.setParentToNull();
        startVertex.setPathDistance(0);
        List<AStarVertex<GalaxyMapPlanet>> list = new List<>();
        list.append(startVertex);

        while(!list.isEmpty()) {
            list.toFirst();
            AStarVertex<GalaxyMapPlanet> smallestVertex = list.getContent();
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

            List<AStarVertex<GalaxyMapPlanet>> neighbors = pGraph.getNeighbours(smallestVertex);
            neighbors.toFirst();
            while(neighbors.hasAccess()) {
                if(!neighbors.getContent().isMarked()) {
                    double newDistance = smallestVertex.getPathDistance() + pGraph.getEdge(smallestVertex, neighbors.getContent()).getWeight();
                    if(neighbors.getContent().getPathDistance() > newDistance) {
                        neighbors.getContent().setPathDistance(newDistance);
                        if(neighbors.getContent().getParent() == null) list.append(neighbors.getContent());
                        neighbors.getContent().setParent(smallestVertex);
                    }
                }
                neighbors.next();
            }
        }

        Stack<AStarVertex<GalaxyMapPlanet>> stack = new Stack<>();
        AStarVertex<GalaxyMapPlanet> backTracker = pZiel;
        stack.push(backTracker);
        while(backTracker.getParent() != null) {
            backTracker = backTracker.getParent();
            stack.push(backTracker);
        }
        List<AStarVertex<GalaxyMapPlanet>> path = new List<>();
        while(!stack.isEmpty()) {
            path.append(stack.top());
            stack.pop();
        }
        return path;
    }
}
