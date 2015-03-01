package net.neonlotus.lazywizard.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.alertdialogpro.AlertDialogPro;

import net.neonlotus.lazywizard.R;
import net.neonlotus.lazywizard.activeandroid.Unit;
import net.neonlotus.lazywizard.adapters.newUnitAdapter;

import java.util.List;

public class TestFragment extends Fragment {
    //private String title;
    //private int page;
    List<Unit> unitList;
    newUnitAdapter unitAdapter;

    public static TestFragment newInstance(int page, String title) {
        TestFragment fragmentFirst = new TestFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //page = getArguments().getInt("someInt", 0);
        //title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        SnackbarManager.show(
//                Snackbar.with(getActivity())
//                        .text("Tap Rows To Expand"));

        View view = inflater.inflate(R.layout.new_unit_fragment, container, false);
        ListView lvUnits = (ListView) view.findViewById(R.id.lvUnits);
        unitList = getAll();
        unitAdapter = new newUnitAdapter(getActivity(), R.layout.unit_row, unitList, TestFragment.this);
        lvUnits.setAdapter(unitAdapter);

        lvUnits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*AlertDialogPro.Builder builder = new AlertDialogPro.Builder(getActivity());
                builder.setTitle(unitList.get(position).name).
                        setMessage("Message Body").
                        setNeutralButton("", null).
                        setPositiveButton("Positive", null).
                        setNegativeButton("Negative", null).
                        show();*/

                //showCustomViewDialog(position);
            }
        });

        return view;
    }

    public static List<Unit> getAll() {
        return new Select()
                .from(Unit.class)
                .execute();
    }


}
