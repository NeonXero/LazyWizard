package net.neonlotus.lazywizard.models;

public class Tech {
    private String name;
    private String learned;
    private String cost;

    public Tech(String name, String learned, String cost) {
        this.name = name;
        this.learned = learned;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLearned() {
        return learned;
    }

    public void setLearned(String count) {
        this.learned = count;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
