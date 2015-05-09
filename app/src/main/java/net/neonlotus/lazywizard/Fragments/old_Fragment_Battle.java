package net.neonlotus.lazywizard.Fragments;

import android.app.Fragment;

import net.neonlotus.lazywizard.R;

import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_battle)
public class old_Fragment_Battle extends Fragment {
/*
    Context context = getActivity();
    int unitTotal, sending;

    @Pref
    Prefs_ prefs;

    private String[] arraySpinner;


    *//*@ViewById (R.id.unitLabel)
    TextView unitLabel;
    @ViewById
    TextView sendCount;
    @ViewById
    SeekBar minionSeek;
    @ViewById
    Button btnStartBattle;
    @ViewById
    Button btnCharacter;
    @ViewById
    TextView textView;*//*
    @ViewById
    Button btnSaveName;
    @ViewById
    EditText etWizName;

    @ViewById
    Spinner spJewelry;
    @ViewById
    Spinner spHead;
    @ViewById
    Spinner spBody;
    @ViewById
    Spinner spWeapon;


    @AfterViews
    void onAfterViews() {
        int setupCount = 0;
        MainActivity.updateSouls();

        this.arraySpinner = new String[]{
                "1", "2", "3", "4", "5"
        };
        //List<Item> itemList = getAll();
        List<Item> itemList = MyApplication.getInstance().getItemList(); //naw

        List<Item> headItems = MyApplication.getInstance().getHeadItems();
        List<Item> armorItems = MyApplication.getInstance().getArmorItems();
        List<Item> weaponItems = MyApplication.getInstance().getWeaponItems();
        List<Item> accessoryItems = MyApplication.getInstance().getAccessoryItems();

        List<String> itemNames = new ArrayList<String>();
        for (int i=0;i<itemList.size();i++) {
            itemNames.add(itemList.get(i).name);
        }

        List<String> headNames = new ArrayList<String>();
        for (int i=0;i<headItems.size();i++) {
            headNames.add(headItems.get(i).name);
        }
        List<String> armorNames = new ArrayList<String>();
        for (int i=0;i<armorItems.size();i++) {
            armorNames.add(armorItems.get(i).name);
        }
        List<String> weaponNames = new ArrayList<String>();
        for (int i=0;i<weaponItems.size();i++) {
            weaponNames.add(weaponItems.get(i).name);
        }
        List<String> accessoryNames = new ArrayList<String>();
        for (int i=0;i<accessoryItems.size();i++) {
            accessoryNames.add(accessoryItems.get(i).name);
        }


        //ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.equip_spinner, arraySpinner);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.equip_spinner, arraySpinner);
        //ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(getActivity(), R.layout.equip_spinner, getAll());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.equip_spinner, itemNames);

        ArrayAdapter<String> headAdapter = new ArrayAdapter<String>(getActivity(), R.layout.equip_spinner, headNames);
        ArrayAdapter<String> armorAdapter = new ArrayAdapter<String>(getActivity(), R.layout.equip_spinner, armorNames);
        ArrayAdapter<String> weaponAdapter = new ArrayAdapter<String>(getActivity(), R.layout.equip_spinner, weaponNames);
        ArrayAdapter<String> accessoryAdapter = new ArrayAdapter<String>(getActivity(), R.layout.equip_spinner, accessoryNames);

        spHead.setAdapter(headAdapter);
        spBody.setAdapter(armorAdapter);
        spWeapon.setAdapter(weaponAdapter);
        spJewelry.setAdapter(accessoryAdapter);

        //int unitTotal = 0;

        // [ seek setup here ]
        //setupSeekBar();

        *//*btnStartBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Under construction", Toast.LENGTH_SHORT).show();
                //doBattle(sending);
            }
        });*//*


*//*

        if (prefs.setupCount().exists()) {
            //Do nothing
        } else {
            //setupDB();
        }
*//*


//        prefs.setupCount().put(setupCount);


    }

    public static List<Item> getAll() {
        return new Select()
                .from(Item.class)
                .execute();
    }


    *//*public void doBattle(int total) {
        int min = 0, jel = 0, ske = 0, cup = 0, whe = 0, mon = 0, far = 0, mul = 0;
        int us = 0;

        for (int i=1;i<9;i++) {
            final Unit u = Unit.load(Unit.class,i);
            if (u.owned>0) {
                switch (i) {
                    case 1:
                        min = u.owned;
                        us++;
                        break;
                    case 2:
                        jel = u.owned;
                        us++;
                        break;
                    case 3:
                        ske=u.owned;
                        us++;
                        break;
                    case 4:
                        cup=u.owned;
                        us++;
                        break;
                    case 5:
                        whe=u.owned;
                        us++;
                        break;
                    case 6:
                        mon=u.owned;
                        us++;
                        break;
                    case 7:
                        far=u.owned;
                        us++;
                        break;
                    case 8:
                        mul=u.owned;
                        us++;
                        break;
                    default:
                        break;
                }
            }
        }
        //Log.d("ryan","break "+min+" "+jel+" "+ske+" "+cup+" "+whe+" "+mon+" "+far+" "+mul+" // "+us);

        int dog = fight(); //0, 1, 2
        if (dog==0) {
            final Unit u = Unit.load(Unit.class,1);
            if (u.owned>0) {
                u.owned--;
                u.save();
            }

        } else if (dog==1) {
            //nothing
        } else {
            final Unit u = Unit.load(Unit.class,1);
            u.owned++;
            u.save();
        }
        setupSeekBar();

    }*//*

    *//*public int fight() {
        Random randomGenerator = new Random();
        int a = randomGenerator.nextInt(3);
        return a;
    }*//*




    *//*public void setupSeekBar() {
        unitTotal = 0;
        for (int i=1;i<9;i++) {
            final Unit u = Unit.load(Unit.class,i);
            unitTotal += u.owned;
        }

        if (unitTotal<1) {
            unitLabel.setText("You don't own any units yet, try summoning some!");
        } else {
            unitLabel.setText("Total available war fodder: "+unitTotal+"\nSlide  the bar to select units to fight.");
        }

        minionSeek.setMax(unitTotal);
        minionSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
                sending = progress;
                sendCount.setText(progress+" units are going into the fray!");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getActivity(), "seek bar progress:" + progressChanged, Toast.LENGTH_SHORT).show();
            }
        });
    }*//*

    *//*@Click (R.id.btnCharacter)
    void thing() {
        //Toast.makeText(getActivity(),"test",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getActivity(), CharacterDialog.class);
        startActivity(intent);
    }*//*
    @Click(R.id.btnSaveName)
    void saveName() {
        prefs.battleName().put(etWizName.getText().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (prefs.battleName().exists()) {
            etWizName.setText(prefs.battleName().get().toString());
        } else {
            etWizName.setText("");
        }
    }*/
}
