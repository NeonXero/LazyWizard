package net.neonlotus.lazywizard.models;

public class Unit {
    private String name;
    private int count;
    private int cost;
    private int id;

    public Unit(String name, int count, int cost, int id) {
        this.name = name;
        this.count = count;
        this.cost = cost;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
