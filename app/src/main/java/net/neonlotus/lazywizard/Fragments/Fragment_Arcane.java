package net.neonlotus.lazywizard.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import net.neonlotus.lazywizard.MainActivity;
import net.neonlotus.lazywizard.R;
import net.neonlotus.lazywizard.activeandroid.Item;
import net.neonlotus.lazywizard.activeandroid.Unit;
import net.neonlotus.lazywizard.appliation.App;
import net.neonlotus.lazywizard.appliation.Prefs_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@EFragment (R.layout.fragment_arcane)
public class Fragment_Arcane extends Fragment {

    Context context = getActivity();
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
            List<Unit> unitList = App.getInstance().getUnitList();
            long currentRate = unitList.get(0).rate;
            unitList.get(0).rate = currentRate * 2;
            App.getInstance().setUnitList(unitList);
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

}