package my_project.model.modes.planet.missions;

public class ExterminationMission extends Mission{
    int killAmountNeeded;
    public ExterminationMission() {
        killAmountNeeded = (int)(Math.random()*100-50);
        shortDescription = "Exterminate " + killAmountNeeded + " Enemies to thin their Numbers!";
        longDescription = "Exterminate " + killAmountNeeded + " Enemies to thin their Numbers! (longer)";
    }

    @Override
    protected void customStep(double progress) {
        this.completion += progress/killAmountNeeded;
    }
}
