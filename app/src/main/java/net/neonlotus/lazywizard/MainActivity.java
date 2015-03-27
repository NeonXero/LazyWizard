package net.neonlotus.lazywizard;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.activeandroid.query.Select;

import net.neonlotus.lazywizard.Fragments.frag_Unit_;
import net.neonlotus.lazywizard.activeandroid.Category;
import net.neonlotus.lazywizard.activeandroid.Item;
import net.neonlotus.lazywizard.activeandroid.Unit;
import net.neonlotus.lazywizard.application.MainService;
import net.neonlotus.lazywizard.application.MyApplication;
import net.neonlotus.lazywizard.application.Prefs_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

    @Pref
    static Prefs_ prefs;

    @ViewById
    static TextView tvSouls;

    static ActionBar ab;

    List<Item> itemList;
    List<Item> headItems, armorItems, weaponItems, accessoryItems;

    static List<Unit> unitList;

    MyApplication app;

    static long souls;
    FragmentTransaction ft;


    frag_Unit_ fragUnit;

    @AfterInject
    void onAfterInject() {

        fragUnit = new frag_Unit_();
    }

    @AfterViews
    void onAfterViews() {
        Log.d("zz", "AFTER VIEWS");

        app = MyApplication.getInstance();


        FragmentManager fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.container, fragUnit).commit();
        ab = getActionBar();


        //Get Units
        int setupCount=0;
        if (prefs.setupCount().exists()) {
            //nothing for now
        } else {
            setupUnitDatabase();
        }

        prefs.setupCount().put(setupCount);
        this.unitList = getAllUnits();
        souls = prefs.souls().get();
        tvSouls.setText(NumberFormat.getNumberInstance(Locale.US).format(souls));



        final Handler mHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                if (app.getUnitList() != null) {
                                    if (app.getUnitList().size() > 0) {
                                        for (int i = 0; i < app.getUnitList().size(); i++) {
                                            Unit un = app.getUnitList().get(i);
                                            long owned = un.owned;
                                            long rate = un.rate;
                                            long pay = owned * rate;
                                            prefs.souls().put(prefs.souls().get() + pay);
                                            MainActivity.updateSouls();
                                        }
                                    }
                                }
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("zz", "CREATE");

    }

    @Override
    protected void onPause() {
        super.onPause();
        app.saveAll();
        stopService(new Intent(this, MainService.class)); //not working?
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        app.saveAll();
        stopService(new Intent(this, MainService.class)); //not working?
    }

    public static ActionBar getAB() {
        return ab;
    }


    public static Prefs_ getPrefs() {
        return prefs;
    }

    public static List<Unit> getUnits() {
        return unitList;
    }

    public static boolean checkSouls(long s) {
        boolean b;

        long current = getPrefs().souls().get();
        long post = current - s;
        if (post >= 0) {
            b = true;
        } else {
            b = false;
        }

        return b;
    }

    public static void updateSouls() {
        souls = prefs.souls().get();
        tvSouls.setText(NumberFormat.getNumberInstance(Locale.US).format(souls));
    }



    public void setupUnitDatabase() {
        Category category = new Category();
        category.name = "Unit";
        category.save();

        Unit unit = new Unit();
        unit.category = category;
        unit.name="Minion";
        unit.cost=1;
        unit.costbase=1;
        unit.costmulti=2;
        unit.rate=1;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Wisp";
        unit.cost=5;
        unit.costbase=5;
        unit.costmulti=5;
        unit.rate=2;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Jester";
        unit.cost=10;
        unit.costbase=10;
        unit.costmulti=10;
        unit.rate=3;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Shaman";
        unit.cost=25;
        unit.costbase=25;
        unit.costmulti=41;
        unit.rate=4;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Hex Master";
        unit.cost=80;
        unit.costbase=80;
        unit.costmulti=95;
        unit.rate=5;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Shadowbeast";
        unit.cost=150;
        unit.costbase=150;
        unit.costmulti=150;
        unit.rate=6;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Elemental";
        unit.cost=225;
        unit.costbase=225;
        unit.costmulti=273;
        unit.rate=7;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Necromancer";
        unit.rate=8;
        unit.cost=500;
        unit.costbase=500;
        unit.costmulti=814;
        unit.save();
    }

    public void setupDB() {

        //========================== UNITS
        //================================


        //========================== ITEMS
        //================================
        Category category = new Category();
        category = new Category();
        category.name = "Item";
        category.save();

        //COMMON - 1 of each type
        Item item= new Item();
        item.category = category;
        item.name="Stupid Hat";
        item.cost=1;
        item.eqType="Head";
        item.rarity="Common";
        item.owned=true;
        item.save();

        item = new Item();
        item.category = category;
        item.name="Stupid Armor";
        item.cost=1;
        item.eqType="Armor";
        item.rarity="Common";
        item.save();

        item = new Item();
        item.category = category;
        item.name="Stupid Weapon";
        item.cost=1;
        item.eqType="Weapon";
        item.rarity="Common";
        item.save();

        item = new Item();
        item.category = category;
        item.name="Stupid Accessory";
        item.cost=1;
        item.eqType="Accessory";
        item.rarity="Common";
        item.save();

        //SUPERIOR - 1 of each type
        item = new Item();
        item.category = category;
        item.name="Fun Hat";
        item.cost=1;
        item.eqType="Head";
        item.rarity="Superior";
        item.save();

        item = new Item();
        item.category = category;
        item.name="Fun Armor";
        item.cost=1;
        item.owned=true;
        item.eqType="Armor";
        item.rarity="Superior";
        item.save();

        item = new Item();
        item.category = category;
        item.name="Fun Weapon";
        item.cost=1;
        item.eqType="Weapon";
        item.rarity="Superior";
        item.save();

        item = new Item();
        item.category = category;
        item.name="Fun Accessory";
        item.cost=1;
        item.eqType="Accessory";
        item.rarity="Superior";
        item.save();

        //EPIC - 1 of each type
        item = new Item();
        item.category = category;
        item.name="Amazing Hat";
        item.cost=1;
        item.eqType="Head";
        item.rarity="Epic";
        item.save();

        item = new Item();
        item.category = category;
        item.name="Amazing Armor";
        item.cost=1;
        item.eqType="Armor";
        item.rarity="Epic";
        item.save();

        item = new Item();
        item.category = category;
        item.name="Amazing Weapon";
        item.cost=1;
        item.owned=true;
        item.eqType="Weapon";
        item.rarity="Epic";
        item.save();

        item = new Item();
        item.category = category;
        item.name="Amazing Accessory";
        item.cost=1;
        item.eqType="Accessory";
        item.rarity="Epic";
        item.save();

        //MYSTIC
        item = new Item();
        item.category = category;
        item.name="Dope Accessory";
        item.cost=1;
        item.eqType="Accessory";
        item.rarity="Mystic";
        item.owned=true;
        item.save();
    }

    public static List<Unit> getAllUnits() {
        return new Select()
                .from(Unit.class)
                .execute();
    }

    public static List<Item> getAll() {
        return new Select()
                .from(Item.class)
                .execute();
    }

    public static List<Item> getHead() {
        return new Select()
                .from(Item.class)
                //.where("eqType = ? AND owned = ?", "Head",1)
                .where("eqType = ?", "Head")
                .execute();
    }
    public static List<Item> getArmor() {
        return new Select()
                .from(Item.class)
                .where("eqType = ?", "Armor")
                .execute();
    }
    public static List<Item> getWeapon() {
        return new Select()
                .from(Item.class)
                .where("eqType = ?", "Weapon")
                .execute();
    }
    public static List<Item> getAccessory() {
        return new Select()
                .from(Item.class)
                .where("eqType = ?", "Accessory")
                .execute();
    }


}