package net.neonlotus.lazywizard.Fragments;

import android.widget.TextView;

import net.neonlotus.lazywizard.R;
import net.neonlotus.lazywizard.application.MyApplication;
import net.neonlotus.lazywizard.models.Unit;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


@EFragment (R.layout.new_stats_fragment)
public class frag_Stats extends android.app.Fragment {

    @ViewById
    TextView tvOwnedValue;
    @ViewById
    TextView tvUpgradeValue;
    @ViewById
    TextView tvProductionValue;

    //List<UnitAA> unitAAList;
    //newUnitAdapter unitAdapter;

    @AfterViews
    void calledAfterViewInjection() {
        long ownedUnits = 0;
        long upgradesBought = 0;
        long productionTotal = 0;
        List<Unit> owned = MyApplication.getInstance().getUnitList();
        for (Unit u : owned) {
            ownedUnits += u.owned;
            upgradesBought+= u.upgradelevel;
            productionTotal += (u.rate*u.owned);
        }

        tvOwnedValue.setText(NumberFormat.getNumberInstance(Locale.US).format(ownedUnits));
        tvUpgradeValue.setText(NumberFormat.getNumberInstance(Locale.US).format(upgradesBought));
        tvProductionValue.setText(NumberFormat.getNumberInstance(Locale.US).format(productionTotal));
    }

    /*public static frag_Stats newInstance(int page, String title) {
        frag_Stats fragmentFirst = new frag_Stats();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }*/

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_stats_fragment, container, false);

        long ownedUnits = 0;
        List<UnitAA> owned = MyApplication.getInstance().getUnitAAList();
        for (UnitAA u : owned) {
            ownedUnits += u.owned;
        }
        if (ownedUnits>-1) {
            tvOwnedValue.setText(""+ownedUnits);
        }

        //ListView lvUnits = (ListView) view.findViewById(R.id.lvUnits);
        //unitAAList = getAll();
        //MyApplication.getInstance().setUnitAAList(unitAAList);



        //unitAdapter = new newUnitAdapter(getActivity(), R.layout.unit_row, unitAAList, frag_Unit.this);
        //lvUnits.setAdapter(unitAdapter);
//        int owned = 0;
//        float rate = 0;
        //for (UnitAA un : unitAAList) {
            //owned+= un.owned;
            //rate+= (un.owned*un.rate);
            //Log.d("zz", "owned: " + un.owned + " rate: " + un.rate);
        //}

        //tvTotalUnits.setText("Total Units Owned: "+owned);
        //tvTotalRate.setText("Total Production: "+rate);

        return view;
    }*/

//    public static List<UnitAA> getAll() {
//        return new Select()
//                .from(UnitAA.class)
//                .execute();
//    }

    /*@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("zz","stats attach");
        //unitAAList = MyApplication.getInstance().getUnitAAList();

        *//*for (UnitAA un : myApplication.getUnitAAList()) {
            Log.d("zz", "owned: " + un.owned + " rate: " + un.rate);
        }*//*
    }*/
}
