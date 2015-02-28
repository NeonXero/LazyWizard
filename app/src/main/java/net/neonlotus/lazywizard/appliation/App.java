package net.neonlotus.lazywizard.appliation;


import net.neonlotus.lazywizard.activeandroid.Item;
import net.neonlotus.lazywizard.activeandroid.Tech;
import net.neonlotus.lazywizard.activeandroid.Unit;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.List;

@EBean
public class App {
    @Pref
    Prefs_ prefs;

    //private static App mInstance = null;
    static App mInstance = null;

    List<Unit> unitList;
    List<Tech> techList;
    List<Item> itemList;

    List<Item> headItems;
    List<Item> armorItems;
    List<Item> weaponItems;
    List<Item> accessoryItems;

    private int souls;

    public App() {
        //minionOwned = 0;
    }

    public static App getInstance() {
        if (mInstance == null) {
            mInstance = new App();
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
        if (App.getInstance().getUnitList() != null) {
            if (App.getInstance().getUnitList().size() > 0) {
                for (int i = 0; i < App.getInstance().getUnitList().size(); i++) {
                    final Unit uNit = App.getInstance().getUnitList().get(i);
                    uNit.save();
                }
            }
        }

        if (App.getInstance().getTechList() != null) {
            if (App.getInstance().getTechList().size() > 0) {
                for (int i = 0; i < App.getInstance().getTechList().size(); i++) {
                    final Tech tEch = App.getInstance().getTechList().get(i);
                    tEch.save();
                }
            }
        }

        if (App.getInstance().getItemList() != null) {
            if (App.getInstance().getItemList().size() > 0) {
                for (int i = 0; i < App.getInstance().getItemList().size(); i++) {
                    final Item iTem = App.getInstance().getItemList().get(i);
                    iTem.save();
                }
            }
        }

    }

    public void clearAll() {
        if (App.getInstance().getUnitList() != null) {
            if (App.getInstance().getUnitList().size() > 0) {
                for (int i = 0; i < App.getInstance().getUnitList().size(); i++) {
                    final Unit uNit = App.getInstance().getUnitList().get(i);
                    uNit.owned = 0;
                    uNit.rate = 1;
                    uNit.cost = 1;
                    uNit.save();
                }
            }
        }

        if (App.getInstance().getTechList() != null) {
            if (App.getInstance().getTechList().size() > 0) {
                for (int i = 0; i < App.getInstance().getTechList().size(); i++) {
                    final Tech tEch = App.getInstance().getTechList().get(i);
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

    }

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