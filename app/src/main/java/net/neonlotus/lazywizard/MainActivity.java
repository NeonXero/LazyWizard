package net.neonlotus.lazywizard;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.TextView;

import com.activeandroid.query.Select;

import net.neonlotus.lazywizard.Fragments.Fragment_Arcane_;
import net.neonlotus.lazywizard.Fragments.Fragment_Battle_;
import net.neonlotus.lazywizard.Fragments.Fragment_Lab_;
import net.neonlotus.lazywizard.Fragments.Fragment_Stats_;
import net.neonlotus.lazywizard.Fragments.Fragment_Unit_;
import net.neonlotus.lazywizard.Fragments.TestFragment;
import net.neonlotus.lazywizard.activeandroid.Category;
import net.neonlotus.lazywizard.activeandroid.Item;
import net.neonlotus.lazywizard.activeandroid.Unit;
import net.neonlotus.lazywizard.appliation.App;
import net.neonlotus.lazywizard.appliation.MainService;
import net.neonlotus.lazywizard.appliation.Prefs_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

    @Pref
    static Prefs_ myPrefs;

    @ViewById
    static TextView tvSouls;

    /*@ViewById
    static Button topUnits;
    @ViewById
    static Button topLab;
    @ViewById
    static Button topArcane;
    @ViewById
    static Button topBattle;
    @ViewById
    static Button topStats;*/

    ActionBar ab;
    List<Item> itemList;
    List<Item> headItems, armorItems, weaponItems, accessoryItems;

    static long souls;
    FragmentTransaction ft;
    FragmentPagerAdapter adapterViewPager;

    Fragment_Unit_ fragmentUnit;
    Fragment_Arcane_ fragmentArcane;
    Fragment_Lab_ fragmentLab;
    Fragment_Battle_ fragmentBattle;
    Fragment_Stats_ fragmentStats;

    @AfterInject
    void onAfterInject() {
        fragmentUnit = new Fragment_Unit_();
        fragmentStats = new Fragment_Stats_();
        fragmentLab = new Fragment_Lab_();
        fragmentBattle = new Fragment_Battle_();
        fragmentArcane = new Fragment_Arcane_();
    }

    @AfterViews
    void onAfterViews() {
        // Add the fragment to the 'container' FrameLayout

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        /*FragmentManager fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.container, fragmentUnit).commit();
        ab = getActionBar();
        topUnits.setTextColor(Color.RED);
        ab.setTitle("Unit Summoning");*/

        souls = myPrefs.souls().get();
        tvSouls.setText(NumberFormat.getNumberInstance(Locale.US).format(souls));

        // Item DB Setup
        int setupCount=0;
        if (myPrefs.itemSetupCount().exists()) {
            if (App.getInstance().getItemList() != null) {
                if (App.getInstance().getItemList().size()>0) {
                    itemList = App.getInstance().getItemList();
                    headItems = App.getInstance().getHeadItems();
                    armorItems = App.getInstance().getArmorItems();
                    weaponItems = App.getInstance().getWeaponItems();
                    accessoryItems = App.getInstance().getAccessoryItems();
                    App.getInstance().setItemList(itemList);
                } else {
                    itemList = getAll();
                    headItems = getHead();
                    armorItems = getArmor();
                    weaponItems = getWeapon();
                    accessoryItems = getAccessory();
                }
            } else {
                itemList = getAll();
                headItems = getHead();
                armorItems = getArmor();
                weaponItems = getWeapon();
                accessoryItems = getAccessory();
                App.getInstance().setItemList(itemList);
                App.getInstance().setHeadItems(headItems);
                App.getInstance().setArmorItems(armorItems);
                App.getInstance().setWeaponItems(weaponItems);
                App.getInstance().setAccessoryItems(accessoryItems);
            }
        } else {
            setupDB();
            itemList = getAll();
            headItems = getHead();
            armorItems = getArmor();
            weaponItems = getWeapon();
            accessoryItems = getAccessory();
            App.getInstance().setItemList(itemList);
            App.getInstance().setHeadItems(headItems);
            App.getInstance().setArmorItems(armorItems);
            App.getInstance().setWeaponItems(weaponItems);
            App.getInstance().setAccessoryItems(accessoryItems);
        }
        myPrefs.itemSetupCount().put(setupCount);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService(new Intent(this, MainService.class));

        if (findViewById(R.id.container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            fragmentUnit.setArguments(getIntent().getExtras());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.getInstance().saveAll();
        stopService(new Intent(this, MainService.class)); //not working?
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().saveAll();
        stopService(new Intent(this, MainService.class)); //not working?
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//        switch (item.getItemId()) {
//            case R.id.mUnits:
//                transaction.replace(R.id.container, fragmentUnit).commit();
//                ab.setTitle("Unit Summoning");
//                return true;
//            case R.id.mArcane:
//                transaction.replace(R.id.container, fragmentArcane).commit();
//                ab.setTitle("Arcane Experiments");
//                return true;
//            case R.id.mLab:
//                transaction.replace(R.id.container, fragmentLab).commit();
//                ab.setTitle("Lab Research");
//                return true;
//            case R.id.mBattle:
//                transaction.replace(R.id.container, fragmentBattle).commit();
//                ab.setTitle("Battlegrounds");
//                return true;
//            case R.id.mStats:
//                transaction.replace(R.id.container, fragmentStats).commit();
//                ab.setTitle("Statistics");
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }

    public static Prefs_ getPrefs() {
        return myPrefs;
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
        souls = myPrefs.souls().get();
        tvSouls.setText(NumberFormat.getNumberInstance(Locale.US).format(souls));
    }

    /*@Click(R.id.topUnits)
    void unitClick() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragmentUnit).commit();
        topUnits.setTextColor(Color.RED);
        topLab.setTextColor(Color.WHITE);
        topArcane.setTextColor(Color.WHITE);
        topBattle.setTextColor(Color.WHITE);
        topStats.setTextColor(Color.WHITE);
        ab.setTitle("Unit Summoning");
    }

    @Click(R.id.topArcane)
    void arcaneClick() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragmentArcane).commit();
        topUnits.setTextColor(Color.WHITE);
        topLab.setTextColor(Color.WHITE);
        topArcane.setTextColor(Color.RED);
        topBattle.setTextColor(Color.WHITE);
        topStats.setTextColor(Color.WHITE);
        ab.setTitle("Arcane Experiments");
    }

    @Click(R.id.topLab)
    void labClick() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragmentLab).commit();
        topUnits.setTextColor(Color.WHITE);
        topLab.setTextColor(Color.RED);
        topArcane.setTextColor(Color.WHITE);
        topBattle.setTextColor(Color.WHITE);
        topStats.setTextColor(Color.WHITE);
        ab.setTitle("Lab Research");
    }

    @Click(R.id.topBattle)
    void battleClick() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragmentBattle).commit();
        topUnits.setTextColor(Color.WHITE);
        topLab.setTextColor(Color.WHITE);
        topArcane.setTextColor(Color.WHITE);
        topBattle.setTextColor(Color.RED);
        topStats.setTextColor(Color.WHITE);
        ab.setTitle("Battlegrounds");
    }

    @Click(R.id.topStats)
    void statsClick() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragmentStats).commit();
        topUnits.setTextColor(Color.WHITE);
        topLab.setTextColor(Color.WHITE);
        topArcane.setTextColor(Color.WHITE);
        topBattle.setTextColor(Color.WHITE);
        topStats.setTextColor(Color.RED);
        ab.setTitle("Statistics");
    }*/

    public void setupDB() {

        //========================== UNITS
        //================================
        Category category = new Category();
        category.name = "Unit";
        category.save();

        Unit unit = new Unit();
        unit.category = category;
        unit.name="Minion";
        unit.cost=1;
        unit.rate=1;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Wisp";
        unit.cost=5;
        unit.rate=2;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Jester";
        unit.cost=10;
        unit.rate=3;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Shaman";
        unit.cost=25;
        unit.rate=4;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Hex Master";
        unit.cost=80;
        unit.rate=5;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Shadowbeast";
        unit.cost=150;
        unit.rate=6;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Elemental";
        unit.cost=225;
        unit.rate=7;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Necromancer";
        unit.rate=8;
        unit.cost=500;
        unit.save();

        //========================== ITEMS
        //================================
        Category category = new Category();
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

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 5;

        public MyPagerAdapter(android.support.v4.app.FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TestFragment.newInstance(0, "Page # 1");
                case 1:
                    return TestFragment.newInstance(1, "Page # 2");
                case 2:
                    return TestFragment.newInstance(2, "Page # 3");
                case 3:
                    return TestFragment.newInstance(3, "Page 4");
                case 4:
                    return TestFragment.newInstance(4, "Page 5");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Unit Summoning";
                case 1:
                    return "Arcane Experiments";
                case 2:
                    return "Lab Research";
                case 3:
                    return "Battlegrounds";
                case 4:
                    return "Statistics";
                default:
                    return null;
            }
        }

    }

}