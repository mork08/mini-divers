package my_project.model.modes.planet.missions;

public class ExterminationMission extends Mission{
    int killAmountNeeded;
    public ExterminationMission() {
        killAmountNeeded = (int)(Math.random()*25+25);
        shortDescription = "Exterminate " + killAmountNeeded + " Enemies!";
        longDescription = "Exterminate " + killAmountNeeded + " Enemies to thin their Numbers!";
    }

    @Override
    protected void customStep(double progress) {
        this.completion += progress/killAmountNeeded;
    }
    public int getKillAmountNeeded(){
        return killAmountNeeded;
    }
}
