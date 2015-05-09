package net.neonlotus.lazywizard.application;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

import net.neonlotus.lazywizard.activeandroid.Item;
import net.neonlotus.lazywizard.activeandroid.Tech;
import net.neonlotus.lazywizard.models.Unit;

import java.util.List;


public class MyApplication extends Application {




    public void onCreate() {
        super.onCreate();
        //initSomeStuff();
        //dog = "dog";
        ActiveAndroid.initialize(this);
    }



    //private static MyApplication mInstance = null;
    static MyApplication mInstance = null;

    List<Unit> unitList;
    List<Tech> techList;
    List<Item> itemList;

    List<Item> headItems;
    List<Item> armorItems;
    List<Item> weaponItems;
    List<Item> accessoryItems;

    private int souls;

    /*public MyApplication() {
        //minionOwned = 0;
    }*/

    public static MyApplication getInstance() {
        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    public boolean checkSouls(int s) {
        boolean b;

        int current = this.getSouls();
        int post = current - s;
        if (post >= 0) {
            b = true;
        } else {
            b = false;
        }

        return b;
    }

    public void saveAll() {
        if (unitList != null) {
            if (unitList.size() > 0) {
                for (int i = 0; i < unitList.size(); i++) {
                    final Unit uNit = unitList.get(i);
                    //uNit.save();
                }
            }
        }

        /*if (MyApplication.getInstance().getTechList() != null) {
            if (MyApplication.getInstance().getTechList().size() > 0) {
                for (int i = 0; i < MyApplication.getInstance().getTechList().size(); i++) {
                    final Tech tEch = MyApplication.getInstance().getTechList().get(i);
                    tEch.save();
                }
            }
        }*/

       /* if (MyApplication.getInstance().getItemList() != null) {
            if (MyApplication.getInstance().getItemList().size() > 0) {
                for (int i = 0; i < MyApplication.getInstance().getItemList().size(); i++) {
                    final Item iTem = MyApplication.getInstance().getItemList().get(i);
                    iTem.save();
                }
            }
        }*/

    }

    /*public void clearAll() {
        if (MyApplication.getInstance().getUnitAAList() != null) {
            if (MyApplication.getInstance().getUnitAAList().size() > 0) {
                for (int i = 0; i < MyApplication.getInstance().getUnitAAList().size(); i++) {
                    final UnitAA uNit = MyApplication.getInstance().getUnitAAList().get(i);
                    uNit.owned = 0;
                    uNit.rate = 1;
                    uNit.cost = 1;
                    uNit.save();
                }
            }
        }

        if (MyApplication.getInstance().getTechList() != null) {
            if (MyApplication.getInstance().getTechList().size() > 0) {
                for (int i = 0; i < MyApplication.getInstance().getTechList().size(); i++) {
                    final Tech tEch = MyApplication.getInstance().getTechList().get(i);
                    tEch.owned = 0;
                    tEch.rate = 1;
                    tEch.cost = 1;
                    tEch.save();
                }
            }
        }

        if (prefs.battleName().exists()) {
            prefs.battleName().remove();
        }

    }*/

    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }

    public int getSouls() {
        return souls;
    }

    public void setSouls(int souls) {
        this.souls = souls;
    }

    public List<Tech> getTechList() {
        return techList;
    }

    public void setTechList(List<Tech> techList) {
        this.techList = techList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Item> getHeadItems() {
        return headItems;
    }

    public void setHeadItems(List<Item> headItems) {
        this.headItems = headItems;
    }

    public List<Item> getArmorItems() {
        return armorItems;
    }

    public void setArmorItems(List<Item> armorItems) {
        this.armorItems = armorItems;
    }

    public List<Item> getWeaponItems() {
        return weaponItems;
    }

    public void setWeaponItems(List<Item> weaponItems) {
        this.weaponItems = weaponItems;
    }

    public List<Item> getAccessoryItems() {
        return accessoryItems;
    }

    public void setAccessoryItems(List<Item> accessoryItems) {
        this.accessoryItems = accessoryItems;
    }
}