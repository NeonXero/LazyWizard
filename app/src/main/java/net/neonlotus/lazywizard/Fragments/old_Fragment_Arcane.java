package net.neonlotus.lazywizard.Fragments;

import android.app.Fragment;

import net.neonlotus.lazywizard.R;

import org.androidannotations.annotations.EFragment;

@EFragment (R.layout.fragment_arcane)
public class old_Fragment_Arcane extends Fragment {

    /*Context context = getActivity();
    int rocks;
    List<Item> itemList;
    List<Item> headItems, armorItems, weaponItems, accessoryItems;

    @Pref
    Prefs_ prefs;

    @ViewById
    Button btnMotivation;
    @ViewById
    TextView tvMoonRocks;

    @AfterViews
    void onAfterViews() {


        rocks = prefs.moonRocks().get();
        tvMoonRocks.setText(NumberFormat.getNumberInstance(Locale.US).format(rocks));
    }

    public static List<Item> getCommon() {
        return new Select()
                .from(Item.class)
                .where("Rarity = ?", "Common")
                .execute();
    }

    public static List<Item> getSuperior() {
        return new Select()
                .from(Item.class)
                .where("Rarity = ?", "Superior")
                .execute();
    }

    public static List<Item> getEpic() {
        return new Select()
                .from(Item.class)
                .where("Rarity = ?", "Epic")
                .execute();
    }

    public static List<Item> getMystic() {
        return new Select()
                .from(Item.class)
                .where("Rarity = ?", "Mystic")
                .execute();
    }


    @Click(R.id.btnMotivation)
    void motivate() {
        if (MainActivity.checkSouls(1000000000)) {
            List<UnitAA> unitAAList = MyApplication.getInstance().getUnitAAList();
            long currentRate = unitAAList.get(0).rate;
            unitAAList.get(0).rate = currentRate * 2;
            MyApplication.getInstance().setUnitAAList(unitAAList);
            prefs.souls().put(prefs.souls().get() - 1000000000);
            MainActivity.updateSouls();
        } else {
            Toast.makeText(getActivity(), "Not enough souls", Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.btnConvert)
    void clickConvert() {
        if (MainActivity.checkSouls(2000000000)) {
            prefs.moonRocks().put(prefs.moonRocks().get() + 1);
            prefs.souls().put(prefs.souls().get() - 2000000000);
            MainActivity.updateSouls();
            this.updateMoonRocks();
        } else {
            Toast.makeText(getActivity(), "Not enough souls", Toast.LENGTH_SHORT).show();
        }
    }

    @UiThread
    void updateMoonRocks() {
        rocks = prefs.moonRocks().get();
        tvMoonRocks.setText(NumberFormat.getNumberInstance(Locale.US).format(rocks));
    }
*/
}