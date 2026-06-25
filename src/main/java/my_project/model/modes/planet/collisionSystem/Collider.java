package my_project.model.modes.planet.collisionSystem;

public class Collider {
    String shapeType;
    double radius;
    double width;
    double height;
    double x, y;
    double linVelX, linVelY;
    double breakingfactor = 1;
    public Collider(String shapeType, double x, double y, double radiusOrSideLength) {

        if (shapeType.equals("rectangle")) {
            this.width = radiusOrSideLength;
            this.height = radiusOrSideLength;
        }else if (shapeType.equals("circle")) {
            this.radius = radiusOrSideLength;
        }
    }
    public Collider(double x, double y, double radius) {
        shapeType = "circle";
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    public Collider(double x, double y, double width, double height) {
        shapeType = "rectangle";
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public Collider(double x, double y) {
        shapeType = "point";
        this.x = x;
        this.y = y;
    }
    public boolean collidesWith(Collider other) {
        if (shapeType.equals("rectangle") && other.shapeType.equals("rectangle")) {
            return ((x > other.x && x < other.x + other.width) || (other.x > x && other.x < x + width)) && ((y > other.y && y < other.y + other.width) || (other.y > y && other.y < y + width));
        }else if (shapeType.equals("circle") && other.shapeType.equals("circle")) {
            return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2)) <= radius + other.radius;
        }else if (shapeType.equals("rectangle") && other.shapeType.equals("circle")) {
            //TODO machen wa ma
        }else if (shapeType.equals("circle") && other.shapeType.equals("rectangle")) {
            other.collidesWith(this);
        }
        return false;
    }
    public void setLinVelX(double velX) {
        this.linVelX = velX;
    }
    public void setLinVelY(double velY) {
        this.linVelY = velY;
    }
    public void setLinVel(double velX, double velY) {
        setLinVelX(velX);
        setLinVelY(velY);
    }
    public void addLinVel(double velX, double velY) {
        setLinVel(this.linVelX + velX, this.linVelY + velY);
    }
    public void calculateLinVel(double dt) {
       this.x += linVelX * dt;
       this.y += linVelY * dt;

        this.linVelX -= (this.linVelX - breakingfactor * this.linVelX) * dt;
        this.linVelY -= (this.linVelY - breakingfactor * this.linVelY);
    }
    public void setBreakingFactor(double bF){
        this.breakingfactor = bF;
    }

    public void stepBack() {
        double stepX = (linVelX / Math.abs(linVelX));
        double stepY = (linVelY / Math.abs(linVelY));
        this.x -= stepX;
        this.y -= stepY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
