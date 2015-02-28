package net.neonlotus.lazywizard.activeandroid;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Items")
public class Item extends Model {

    @Column(name = "Name")
    public String name;

    @Column(name = "Cost")
    public long cost;

    @Column(name = "Owned")
    public boolean owned;

    @Column(name = "Rarity")
    public String rarity;

    @Column(name = "EqType")
    public String eqType;

    @Column(name = "Category")
    public Category category;

    @Column(name = "Power")
    public long power;

    @Column(name = "Toughness")
    public long toughness;

    public Item() {
        super();
    }

    public Item(String name, Category category, long cost, boolean owned, String rarity, String eqType, long power, long toughness) {
        super();
        this.name = name;
        this.cost = cost;
        this.owned = owned;
        this.rarity = rarity;
        this.category = category;
        this.eqType = eqType;
        this.power = power;
        this.toughness = toughness;
    }

}