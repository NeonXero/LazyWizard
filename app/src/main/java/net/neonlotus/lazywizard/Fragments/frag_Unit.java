package net.neonlotus.lazywizard.Fragments;

import android.widget.ListView;

import net.neonlotus.lazywizard.MainActivity;
import net.neonlotus.lazywizard.R;
import net.neonlotus.lazywizard.adapters.newUnitAdapter;
import net.neonlotus.lazywizard.application.MyApplication;
import net.neonlotus.lazywizard.models.Unit;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment (R.layout.fragment_unit)
public class frag_Unit extends android.app.Fragment {

    List<Unit> unitList;
    newUnitAdapter unitAdapter;

    @ViewById
    ListView lvUnits;

    @AfterViews
    void calledAfterViewInjection() {
        MainActivity.getAB().setTitle("Unit Summoning");
        unitList = MainActivity.getUnits();
        MyApplication.getInstance().setUnitList(unitList);
        unitAdapter = new newUnitAdapter(getActivity(), R.layout.unit_row, unitList, frag_Unit.this);
        lvUnits.setAdapter(unitAdapter);
    }
}