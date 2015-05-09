package net.neonlotus.lazywizard.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.activeandroid.query.Select;

import net.neonlotus.lazywizard.R;
import net.neonlotus.lazywizard.activeandroid.Unit;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;



@EFragment
public class frag_Stats extends Fragment {
//    @App
//    MyApplication myApplication;

    @ViewById
    TextView tvTotalUnits;
    @ViewById
    TextView tvTotalRate;

    //List<Unit> unitList;
    //newUnitAdapter unitAdapter;

    public static frag_Stats newInstance(int page, String title) {
        frag_Stats fragmentFirst = new frag_Stats();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_stats_fragment, container, false);
        //ListView lvUnits = (ListView) view.findViewById(R.id.lvUnits);
        //unitList = getAll();
        //MyApplication.getInstance().setUnitList(unitList);



        //unitAdapter = new newUnitAdapter(getActivity(), R.layout.unit_row, unitList, frag_Unit.this);
        //lvUnits.setAdapter(unitAdapter);
        int owned = 0;
        float rate = 0;
        //for (Unit un : unitList) {
            //owned+= un.owned;
            //rate+= (un.owned*un.rate);
            //Log.d("zz", "owned: " + un.owned + " rate: " + un.rate);
        //}

        //tvTotalUnits.setText("Total Units Owned: "+owned);
        //tvTotalRate.setText("Total Production: "+rate);

        return view;
    }

    public static List<Unit> getAll() {
        return new Select()
                .from(Unit.class)
                .execute();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("zz","stats attach");
        //unitList = MyApplication.getInstance().getUnitList();

        /*for (Unit un : myApplication.getUnitList()) {
            Log.d("zz", "owned: " + un.owned + " rate: " + un.rate);
        }*/
    }
}
