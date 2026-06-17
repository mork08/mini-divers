package my_project.model.modes.planet.missions;

public abstract class Mission {
    protected String shortDescription;
    protected String longDescription;
    boolean isCompleted;
    double completion = 0;
    public boolean isCompleted() {
        if (completion >= 1) isCompleted = true;
        return isCompleted;
    }
    public void progress(String Type, double progress) {
        switch (Type) {
            case "addValue":
                completion += progress;
            break;
            case "setValue":
                completion = progress;
            break;
            case "step":
                customStep(progress);
            break;
        }
    }
    protected abstract void customStep(double progress);
    public String getShortDescription() {
        return shortDescription;
    }
    public String getLongDescription() {
        return longDescription;
    }
}
