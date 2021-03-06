package net.neonlotus.lazywizard.activeandroid;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Units")
public class UnitAA extends Model {

    @Column(name = "Name")
    public String name;

    @Column(name= "Cost")
    public long cost;

    @Column(name="CostBase")
    public long costbase;

    @Column(name="CostMulti")
    public long costmulti;

    @Column(name= "Owned")
    public long owned;

    @Column(name = "Rate")
    public long rate;

    @Column(name = "Category")
    public Category category;

    @Column(name="UpgradeLevel")
    public int upgradelevel;
    @Column(name="UpgradeMulti")
    public long upgrademulti;

    public UnitAA(){
        super();
    }
    public UnitAA(String name, Category category, long cost, long owned, long rate, long costmulti, long costbase, int upgradelevel, long upgrademulti){
        super();
        this.name = name;
        this.cost = cost;
        this.owned = owned;
        this.rate = rate;
        this.category = category;
        this.costmulti = costmulti;
        this.costbase = costbase;
        this.upgradelevel = upgradelevel;
        this.upgrademulti = upgrademulti;
    }

}