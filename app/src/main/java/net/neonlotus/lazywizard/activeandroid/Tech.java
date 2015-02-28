package net.neonlotus.lazywizard.activeandroid;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Technologies")
public class Tech extends Model {

    @Column(name = "Name")
    public String name;

    @Column(name= "Cost")
    public long cost;

    @Column(name= "Owned")
    public long owned;

    @Column(name = "Rate")
    public long rate;

    @Column(name = "Category")
    public Category category;

    public Tech(){
        super();
    }
    public Tech(String name, Category category, long cost, long owned, long rate){
        super();
        this.name = name;
        this.cost = cost;
        this.owned = owned;
        this.rate = rate;
        this.category = category;
    }

}