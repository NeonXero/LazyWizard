package net.neonlotus.lazywizard.Fragments;

import android.app.Fragment;

import net.neonlotus.lazywizard.R;

import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_stats)
//@EFragment(R.layout.wheel_test)
public class old_Fragment_Stats extends Fragment {
/*
    @Pref
    Prefs_ prefs;

    @AfterViews
    void onAfterViews() {
        // do wat now?
        //initWheel(R.id.slot_1);


        *//*Button mix = (Button) getActivity().findViewById(R.id.btn_mix);
        mix.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mixWheel(R.id.slot_1);
                //mixWheel(R.id.slot_2);
                //mixWheel(R.id.slot_3);
            }
        });*//*

        //updateStatus();
    }

    // Wheel scrolled flag
    private boolean wheelScrolled = false;

    // Wheel scrolled listener
    OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
        public void onScrollingStarted(WheelView wheel) {
            wheelScrolled = true;
        }
        public void onScrollingFinished(WheelView wheel) {
            wheelScrolled = false;
            //updateStatus();
        }
    };

    // Wheel changed listener
    private OnWheelChangedListener changedListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            if (!wheelScrolled) {
                //updateStatus();
            }
        }
    };

    *//**
     * Updates status
     *//*
//    private void updateStatus() {
//        TextView text = (TextView) getActivity().findViewById(R.id.pwd_status);
//        if (test()) {
//            text.setText("Congratulation!");
//        } else {
//            text.setText("");
//        }
//    }

    *//**
     * Initializes wheel
     * @param id the wheel widget Id
     *//*
    private void initWheel(int id) {
        WheelView wheel = getWheel(id);
        wheel.setViewAdapter(new SlotMachineAdapter(getActivity()));
        wheel.setCurrentItem((int)(Math.random() * 10));

        wheel.addChangingListener(changedListener);
        wheel.addScrollingListener(scrolledListener);
        wheel.setCyclic(true);
        wheel.setEnabled(false);
    }

    *//**
     * Returns wheel by Id
     * @param id the wheel Id
     * @return the wheel with passed Id
     *//*
    private WheelView getWheel(int id) {
        return (WheelView) getActivity().findViewById(id);
    }

    *//**
     * Tests wheels
     * @return true
     *//*
//    private boolean test() {
//        int value = getWheel(R.id.slot_1).getCurrentItem();
//        return testWheelValue(R.id.slot_2, value) && testWheelValue(R.id.slot_3, value);
//    }

    *//**
     * Tests wheel value
     * @param id the wheel Id
     * @param value the value to test
     * @return true if wheel value is equal to passed value
     *//*
    private boolean testWheelValue(int id, int value) {
        return getWheel(id).getCurrentItem() == value;
    }

    *//**
     * Mixes wheel
     * @param id the wheel id
     *//*
    private void mixWheel(int id) {
        WheelView wheel = getWheel(id);
        wheel.scroll(-350 + (int)(Math.random() * 50), 2000);
    }

    *//**
     * Slot machine adapter
     *//*
    private class SlotMachineAdapter extends AbstractWheelAdapter {
        // Image size
//        final int IMAGE_WIDTH = 60;
//        final int IMAGE_HEIGHT = 36;
        final int IMAGE_WIDTH = 100;
        final int IMAGE_HEIGHT = 50;

        // Slot machine symbols
        private final int items[] = new int[] {
                android.R.drawable.star_big_on,
                android.R.drawable.stat_sys_warning,
                android.R.drawable.radiobutton_on_background,
                android.R.drawable.ic_delete
        };
        private final String iiii[] = new String[] {
                "item",
                "winz",
                "failz",
                "haha"
        };

        // Cached images
        //private List<SoftReference<Bitmap>> images;
        private List<String> images;

        // Layout inflater
        private Context context;

        *//**
         * Constructor
         *//*
        public SlotMachineAdapter(Context context) {
            this.context = context;
            //images = new ArrayList<SoftReference<Bitmap>>(items.length);
            images = new ArrayList<String>(iiii.length);
            *//*for (int id : items) {
                images.add(new SoftReference<Bitmap>(loadImage(id)));
            }*//*
            for (String n : iiii) {
                images.add(n);
            }
        }

        *//**
         * Loads image from resources
         *//*
        private Bitmap loadImage(int id) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, IMAGE_WIDTH, IMAGE_HEIGHT, true);
            bitmap.recycle();
            return scaled;
        }

        @Override
        public int getItemsCount() {
            //return items.length;
            return iiii.length;
        }

        // Layout params for image view
        final ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(IMAGE_WIDTH, IMAGE_HEIGHT);

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            //ImageView img;
            TextView img;
            if (cachedView != null) {
                //img = (ImageView) cachedView;
                img = (TextView) cachedView;
            } else {
                //img = new ImageView(context);
                img = new TextView(context);
            }
            //img.setLayoutParams(params);
            //SoftReference<Bitmap> bitmapRef = images.get(index);
            String wheelString = images.get(index);

            //Bitmap bitmap = bitmapRef.get();

           *//* if (bitmap == null) {
                bitmap = loadImage(items[index]);
                images.set(index, new SoftReference<Bitmap>(bitmap));
            }*//*
            if (wheelString==null) {
                //ryan what even
            }

            //img.setImageBitmap(bitmap);
            img.setText(wheelString);

            return img;
        }
    }

    @Click(R.id.btnWipe)
    void wipe() {
        long currentSouls = prefs.souls().get();
        long gainSouls = roundDown(currentSouls, 100);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Exorcising Souls");
        builder.setMessage("Exorcising: " + currentSouls + " souls.\nGaining " + gainSouls + " bonus souls.\nContinue?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        doReset();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Click(R.id.rain)
    void makeItRain() {
        prefs.souls().put(prefs.souls().get() + 100000);
        MainActivity.updateSouls();
    }

    public void doReset() {
        long currentSouls = prefs.souls().get();
        long currentBonus = prefs.bonusSouls().get();
        long gainSouls = roundDown(currentSouls, 100);
        long newBonus = currentBonus + gainSouls;

        prefs.bonusSouls().put(newBonus);
        prefs.souls().put(15 + newBonus);

        List<UnitAA> unitAAList = MyApplication.getInstance().getUnitAAList();
        List<Tech> techList = MyApplication.getInstance().getTechList();
        List<Item> itemList = MyApplication.getInstance().getItemList();

        for (int i = 0; i < unitAAList.size(); i++) {
            unitAAList.get(i).owned = 0;
            if (techList != null)
                techList.get(i).owned = 0;
            //if (itemList != null)
            //itemList.get(i).owned = 0;
        }

        unitAAList.get(0).cost = 1;
        unitAAList.get(1).cost = 5;
        unitAAList.get(2).cost = 10;
        unitAAList.get(3).cost = 25;
        unitAAList.get(4).cost = 80;
        unitAAList.get(5).cost = 150;
        unitAAList.get(6).cost = 225;
        unitAAList.get(7).cost = 500;

        unitAAList.get(0).rate = 1;
        unitAAList.get(1).rate = 2;
        unitAAList.get(2).rate = 3;
        unitAAList.get(3).rate = 4;
        unitAAList.get(4).rate = 5;
        unitAAList.get(5).rate = 6;
        unitAAList.get(6).rate = 7;
        unitAAList.get(7).rate = 8;

        if (techList != null) {
            techList.get(0).cost = 1;
            techList.get(1).cost = 10;
            techList.get(2).cost = 100;
            techList.get(3).cost = 1000;
            techList.get(4).cost = 2000;
            techList.get(5).cost = 3000;
            techList.get(6).cost = 4000;
            techList.get(7).cost = 10000;

            techList.get(0).rate = 1;
            techList.get(1).rate = 2;
            techList.get(2).rate = 3;
            techList.get(3).rate = 4;
            techList.get(4).rate = 5;
            techList.get(5).rate = 6;
            techList.get(6).rate = 7;
            techList.get(7).rate = 8;
        }

        *//*if (itemList != null) {
            itemList.get(0).cost = 1;
            itemList.get(1).cost = 10;
            itemList.get(2).cost = 100;
            itemList.get(3).cost = 1000;
            itemList.get(4).cost = 2000;
            itemList.get(5).cost = 3000;
            itemList.get(6).cost = 4000;
            itemList.get(7).cost = 10000;
        }*//*

        MyApplication.getInstance().setUnitAAList(unitAAList);
        MyApplication.getInstance().setTechList(techList);
        MyApplication.getInstance().setItemList(itemList);
        MyApplication.getInstance().saveAll();

        prefs.battleName().remove();
        prefs.battleStyle().remove();

        MainActivity.updateSouls();
    }

    public static long roundDown(long num, long divisor) {
        if (((num + divisor - 1) / divisor) > 0) {
            return ((num + divisor - 1) / divisor) - 1;
        } else {
            return ((num + divisor - 1) / divisor);
        }
    }*/
}