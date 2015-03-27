package net.neonlotus.lazywizard.Fragments;

import android.app.Fragment;

import net.neonlotus.lazywizard.R;

import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_lab)
public class old_Fragment_Lab extends Fragment {

    /*Context context = getActivity();
    @Pref
    Prefs_ prefs;

    List<Tech> labList;
    LabAdapter labAdapter;

    @ViewById
    static TextView tvSouls;
    @ViewById
    ListView lvTech;

    @AfterViews
    void onAfterViews() {
        int setupCount=0;

        if (prefs.techSetupCount().exists()) {
            if (MyApplication.getInstance().getTechList() != null) {
                if (MyApplication.getInstance().getTechList().size()>0) {
                    labList = MyApplication.getInstance().getTechList(); //???
                    MyApplication.getInstance().setTechList(labList);
                } else {
                    labList = getAll();
                }
            } else {
                labList = getAll();
                MyApplication.getInstance().setTechList(labList);
            }
        } else {
            setupDB();
            labList = getAll();
            MyApplication.getInstance().setTechList(labList);
        }


        prefs.techSetupCount().put(setupCount);

        //labAdapter = new LabAdapter(getActivity(), R.layout.unit_row, labList, Fragment_Lab.this);
        labAdapter = new LabAdapter(getActivity(), R.layout.haxrow, labList, old_Fragment_Lab.this);
        lvTech.setAdapter(labAdapter);


    }

    public static List<Tech> getAll() {
       // Log.d("Ryan", "lab get all db query");
        return new Select()
                .from(Tech.class)
                .execute();
    }

    public void setupDB() {
        Category category = new Category();
        category.name = "Tech";
        category.save();

        Tech tech = new Tech();
        tech.category = category;
        tech.name="Lazy Minions";
        tech.cost=1;
        tech.rate=1;
        tech.save();

        tech = new Tech();
        tech.category = category;

        tech.name="Opaque Jelly";
        tech.cost=10;
        tech.rate=2;
        tech.save();

        tech = new Tech();
        tech.category = category;
        tech.name="Extra Bones";
        tech.cost=100;
        tech.rate=3;
        tech.save();

        tech = new Tech();
        tech.category = category;
        tech.name="Cup Polish";
        tech.cost=1000;
        tech.rate=4;
        tech.save();

        tech = new Tech();
        tech.category = category;
        tech.name="Turbo Wings";
        tech.cost=2000;
        tech.rate=5;
        tech.save();

        tech = new Tech();
        tech.category = category;
        tech.name="Crag Swag";
        tech.cost=3000;
        tech.rate=6;
        tech.save();

        tech = new Tech();
        tech.category = category;
        tech.name="Magic Beans";
        tech.cost=4000;
        tech.rate=7;
        tech.save();

        tech = new Tech();
        tech.category = category;
        tech.name="Mule Sauce";
        tech.cost=10000;
        tech.rate=8;
        tech.save();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        MyApplication.getInstance().saveAll();
    }*/


}