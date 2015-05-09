package net.neonlotus.lazywizard.Fragments;

import android.app.Fragment;

import net.neonlotus.lazywizard.R;

import org.androidannotations.annotations.EFragment;
//tools:context=".MainActivity$PlaceholderFragment"
@EFragment (R.layout.fragment_unit)
public class old_Fragment_Unit extends Fragment {
/*

    Context context = getActivity();

    @Pref
    Prefs_ prefs;

    //Currency
    static int souls;

    List<Unit> unitList;
    UnitAdapter unitAdapter;

    @ViewById
    static TextView tvSouls;
    @ViewById
    ListView lvUnits;


    @AfterViews
    void onAfterViews() {

        SnackbarManager.show(
                Snackbar.with(getActivity())
                        .text("Unit Snackbar Test"));

        int setupCount=0;

        if (prefs.setupCount().exists()) {
            if (prefs.setupCount().get()==10 ) {
                unitList = MyApplication.getInstance().getUnitList(); //???
                MyApplication.getInstance().setUnitList(unitList);
            } else {
                if (MyApplication.getInstance().getUnitList() != null) {
                    if (MyApplication.getInstance().getUnitList().size()>0) {
                        // setup count exists, getUnitList not null and size > 0
                        unitList = MyApplication.getInstance().getUnitList(); //???
                        MyApplication.getInstance().setUnitList(unitList);
                    } else {
                        // setup count exists, getUnitList not null and size ! > 0
                        unitList = getAll();
                    }
                } else {
                    // setup count exists, getUnitList is null
                    unitList = getAll();
                    MyApplication.getInstance().setUnitList(unitList);
                }
            }
        } else {
            // Fresh install
            Log.d("ryan", "Fresh install");
            prefs.souls().put(15);
            prefs.bonusSouls().put(0);
            prefs.moonRocks().put(0);
            setupDB();
            unitList = getAll();

            MyApplication.getInstance().setUnitList(unitList);
            prefs.setupCount().put(setupCount);
        }


        //unitAdapter = new UnitAdapter(getActivity(), R.layout.unit_row, unitList, Fragment_Unit.this);

//        unitAdapter = new UnitAdapter(getActivity(), R.layout.haxrow, unitList, Fragment_Unit.this);
        lvUnits.setAdapter(unitAdapter);

    }

    public static List<Unit> getAll() {
        //Log.d("Ryan", "units get all db query");
        return new Select()
                .from(Unit.class)
                .execute();
    }

    public void setupDB() {
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
        unit.name="Jelly";
        unit.cost=5;
        unit.rate=2;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Skelebro";
        unit.cost=10;
        unit.rate=3;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Cup Bearer";
        unit.cost=25;
        unit.rate=4;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Whelp";
        unit.cost=80;
        unit.rate=5;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Monolith";
        unit.cost=150;
        unit.rate=6;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Farmer";
        unit.cost=225;
        unit.rate=7;
        unit.save();

        unit = new Unit();
        unit.category = category;
        unit.name="Mule";
        unit.rate=8;
        unit.cost=500;
        unit.save();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        MyApplication.getInstance().saveAll();
    }

    @Override
    public void onResume() {
        super.onResume();
        //unitAdapter = new UnitAdapter(getActivity(), R.layout.unit_row, unitList, Fragment_Unit.this);
        lvUnits.setAdapter(unitAdapter);
    }



*/

}
