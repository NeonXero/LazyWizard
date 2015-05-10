package net.neonlotus.lazywizard.models;

public class Unit {
    public String name;
    public long cost;
    public long costbase;
    public long costmulti;
    public long owned;
    public long rate;
    public int upgradelevel;
    public long upgrademulti;

    public Unit(String name, long cost, long costbase, long costmulti, long owned, long rate, int upgradelevel, long upgrademulti) {
        this.name = name;
        this.cost = cost;
        this.costbase = costbase;
        this.costmulti = costmulti;
        this.owned = owned;
        this.rate = rate;
        this.upgradelevel = upgradelevel;
        this.upgrademulti = upgrademulti;
    }
    public Unit() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getCostbase() {
        return costbase;
    }

    public void setCostbase(long costbase) {
        this.costbase = costbase;
    }

    public long getCostmulti() {
        return costmulti;
    }

    public void setCostmulti(long costmulti) {
        this.costmulti = costmulti;
    }

    public long getOwned() {
        return owned;
    }

    public void setOwned(long owned) {
        this.owned = owned;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    public int getUpgradelevel() {
        return upgradelevel;
    }

    public void setUpgradelevel(int upgradelevel) {
        this.upgradelevel = upgradelevel;
    }

    public long getUpgrademulti() {
        return upgrademulti;
    }

    public void setUpgrademulti(long upgrademulti) {
        this.upgrademulti = upgrademulti;
    }
}