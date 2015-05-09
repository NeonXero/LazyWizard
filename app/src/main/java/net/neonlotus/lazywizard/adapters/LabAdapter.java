package net.neonlotus.lazywizard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.expandablelayout.library.ExpandableLayoutItem;

import net.neonlotus.lazywizard.Fragments.old_Fragment_Lab;
import net.neonlotus.lazywizard.MainActivity;
import net.neonlotus.lazywizard.R;
import net.neonlotus.lazywizard.activeandroid.Tech;
import net.neonlotus.lazywizard.application.Prefs_;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LabAdapter extends ArrayAdapter<Tech> {

    List<Tech> techList;
    old_Fragment_Lab fragment;
    ExpandableLayoutItem eli;
    List<ExpandableLayoutItem> myList = new ArrayList<ExpandableLayoutItem>();

    ArrayList<String> minionTiers;
    ArrayList<String> jellyTiers;

    TextView name, count, rate;
    Button buy;

    public LabAdapter(Context context, int resource, List<Tech> items, old_Fragment_Lab fragment) {
        super(context, resource, items);
        this.techList = items;
        this.fragment = fragment;
        this.minionTiers = new ArrayList<String>();

        this.minionTiers.add("Lazy Minions"); // 1
        this.minionTiers.add("Tired Minions"); // 2
        this.minionTiers.add("Adventurous Minions"); // 3
        this.minionTiers.add("Wild Minions"); // 4
        this.minionTiers.add("Tyrant Minions"); // 5
        this.minionTiers.add("Overlord Minions"); // 6
        this.minionTiers.add("Galactic Minions"); // 7
        this.minionTiers.add("Diety Minions"); // 8

        /*
        1 % 8 = 1
        2 % 8 = 2
        3 % 8 = 3
        8 % 8 = 0

        9 & 8 = 1
        10 % 8 = 2
        16 % 8 = 0

        17, 8 = 1
        18, 8 = 2
        24, 8, 0

        owned - (mod answer / 8 )
        71 % 8 = 7 :: 71 - 7 = 64 :: 64/8 = 8
        18 % 8 = 2 :: 18 - 2 = 16 :: 16/8 = 2
        [got it]
         */


        this.jellyTiers = new ArrayList<String>();
        this.jellyTiers.add("Opaque Jellies");
        this.jellyTiers.add("Solid Jellies");
        this.jellyTiers.add("Viscous Jellies");
        this.jellyTiers.add("Shining Jellies");
        this.jellyTiers.add("Rainbow Jellies");
        this.jellyTiers.add("Iridescent Jellies");
        this.jellyTiers.add("Holographic Jellies");
        this.jellyTiers.add("Radiant Jellies");
    }

    static class ViewHolder {
        protected TextView viewName;
        protected TextView viewCount;
        protected TextView viewRate;
        protected Button viewBuy;
        protected Button buyTen;
        protected Button buyFifty;
        protected Button buyHundred;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            //convertView = vi.inflate(R.layout.unit_row, null);
            convertView = vi.inflate(R.layout.haxrow, null);
            eli = (ExpandableLayoutItem) convertView.findViewById(R.id.expandableLayout);
            myList.add(eli);
            viewHolder = new ViewHolder();
            viewHolder.viewName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.viewCount = (TextView) convertView.findViewById(R.id.tvCount);
            viewHolder.viewRate = (TextView) convertView.findViewById(R.id.tvRate);
            viewHolder.viewBuy = (Button) convertView.findViewById(R.id.btnBuy);

            viewHolder.buyTen = (Button) convertView.findViewById(R.id.btnTen);
            viewHolder.buyFifty = (Button) convertView.findViewById(R.id.btnFifty);
            viewHolder.buyHundred = (Button) convertView.findViewById(R.id.btnHundred);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        eli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < myList.size(); i++) {
                    if (i == position) {
                        myList.get(i).show();
                    } else {
                        myList.get(i).hide();
                    }
                }
            }
        });

        final Tech dTech = techList.get(position);
        ////final UnitAA dUnit = MyApplication.getInstance().getUnitAAList().get(position);
        //viewHolder.viewName.setText(dTech.name+" "+dTech.owned);
        viewHolder.viewName.setText(dTech.name);
        viewHolder.viewCount.setText("Owned: "+ NumberFormat.getNumberInstance(Locale.US).format(dTech.owned));
//        viewHolder.viewCount.setText(dUnit.name+"s now produce "+ NumberFormat.getNumberInstance(Locale.US).format((dUnit.owned)*(dUnit.rate)));
        ////viewHolder.viewCount.setText(dUnit.name+"s now produce "+ NumberFormat.getNumberInstance(Locale.US).format((dUnit.rate)));

        //viewHolder.viewRate.setText("Provides: "+ NumberFormat.getNumberInstance(Locale.US).format((dTech.rate*dTech.owned)));
        viewHolder.viewRate.setText("");

        viewHolder.viewBuy.setText("Buy for "+NumberFormat.getNumberInstance(Locale.US).format(dTech.cost));
        //
        long x = doMass(position, 10);
        long y = doMass(position, 50);
        long z = doMass(position, 100);
        viewHolder.buyTen.setText("Buy 10 for " + NumberFormat.getNumberInstance(Locale.US).format(x));
        viewHolder.buyFifty.setText("Buy 50 for " + NumberFormat.getNumberInstance(Locale.US).format(y));
        viewHolder.buyHundred.setText("Buy 100 for " + NumberFormat.getNumberInstance(Locale.US).format(z));

        viewHolder.viewBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs_ p = MainActivity.getPrefs();
                if (dTech.name.contains("Min")) { //Minion Hats
                    if (MainActivity.checkSouls(dTech.cost)) {
                        p.souls().put(p.souls().get()-dTech.cost);
                        ////MyApplication.getInstance().getTechList().get(position).owned++;
                        long a = dTech.owned%8;
                        long b = dTech.owned-a;
                        long c = b/8;
                        if (c!=0) {
                            dTech.name = minionTiers.get(((int)dTech.owned%8))+" "+c;
                        } else {
                            dTech.name = minionTiers.get(((int)dTech.owned%8));
                        }
                        ////dUnit.rate = (dTech.rate+dTech.owned);
                        ////long oNew = MyApplication.getInstance().getTechList().get(position).owned;
                       //// MyApplication.getInstance().getTechList().get(position).cost = (1+(oNew*2));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                } else if (dTech.name.contains("Jel")) {
                    if (MainActivity.checkSouls(dTech.cost)) {
                        p.souls().put(p.souls().get() - dTech.cost);
                       //// MyApplication.getInstance().getTechList().get(position).owned++;
                        long a = dTech.owned%8;
                        long b = dTech.owned-a;
                        long c = b/8;
                        if (c!=0) {
                            dTech.name = jellyTiers.get(((int)dTech.owned%8))+" "+c;
                        } else {
                            dTech.name = jellyTiers.get(((int)dTech.owned%8));
                        }
                      ////  dUnit.rate = (dTech.rate+dTech.owned);;
                      ////  long oNew = MyApplication.getInstance().getTechList().get(position).owned;
                      ////  MyApplication.getInstance().getTechList().get(position).cost = (10+(oNew*5));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                } else if (dTech.name.equals("Extra Bones")) {
                    if (MainActivity.checkSouls(dTech.cost)) {
                        p.souls().put(p.souls().get() - dTech.cost);
                       //// MyApplication.getInstance().getTechList().get(position).owned++;
                      ////  dUnit.rate = (dTech.rate+dTech.owned);
                      ////  long oNew = MyApplication.getInstance().getTechList().get(position).owned;
                      ////  MyApplication.getInstance().getTechList().get(position).cost = (100+(oNew*9));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                } else if (dTech.name.equals("Cup Polish")) {
                    if (MainActivity.checkSouls(dTech.cost)) {
                        p.souls().put(p.souls().get()-dTech.cost);
                      ////  MyApplication.getInstance().getTechList().get(position).owned++;
                      ////  dUnit.rate = (dTech.rate+dTech.owned);;
                      ////  long oNew = MyApplication.getInstance().getTechList().get(position).owned;
                      ////  MyApplication.getInstance().getTechList().get(position).cost = (1000+(oNew*17));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                } else if (dTech.name.equals("Turbo Wings")) {
                    if (MainActivity.checkSouls(dTech.cost)) {
                        p.souls().put(p.souls().get()-dTech.cost);
                      ////  MyApplication.getInstance().getTechList().get(position).owned++;
                      ////  dUnit.rate = (dTech.rate+dTech.owned);
                      ////  long oNew = MyApplication.getInstance().getTechList().get(position).owned;
                     ////   MyApplication.getInstance().getTechList().get(position).cost = (2000+(oNew*31));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                } else if (dTech.name.equals("Crag Swag")) {
                    if (MainActivity.checkSouls(dTech.cost)) {
                        p.souls().put(p.souls().get()-dTech.cost);
                    ////    MyApplication.getInstance().getTechList().get(position).owned++;
                      ////  dUnit.rate = (dTech.rate+dTech.owned);
                     ////   long oNew = MyApplication.getInstance().getTechList().get(position).owned;
                     ////   MyApplication.getInstance().getTechList().get(position).cost = (3000+(oNew*86));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                } else if (dTech.name.equals("Magic Beans")) {
                    if (MainActivity.checkSouls(dTech.cost)) {
                        p.souls().put(p.souls().get()-dTech.cost);
                     ////   MyApplication.getInstance().getTechList().get(position).owned++;
                      ////  dUnit.rate = (dTech.rate+dTech.owned);
                     ////   long oNew = MyApplication.getInstance().getTechList().get(position).owned;
                    ////    MyApplication.getInstance().getTechList().get(position).cost = (4000+(oNew*124));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                } else if (dTech.name.equals("Mule Sauce")) {
                    if (MainActivity.checkSouls(dTech.cost)) {
                        p.souls().put(p.souls().get()-dTech.cost);
                  ////      MyApplication.getInstance().getTechList().get(position).owned++;
                  ////      dUnit.rate = (dTech.rate+dTech.owned);
                   ////     long oNew = MyApplication.getInstance().getTechList().get(position).owned;
                   ////     MyApplication.getInstance().getTechList().get(position).cost = (10000+(oNew*599));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                }

                MainActivity.updateSouls();
                notifyDataSetChanged();
            }
        });

        viewHolder.buyTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs_ p = MainActivity.getPrefs();

                final Tech dTech = techList.get(position);

                int baseCost = -1;
                int multiCost = -1;
                switch (position) {
                    case 0:
                        baseCost = 1;
                        multiCost = 2;
                        break;
                    case 1:
                        baseCost = 10;
                        multiCost = 5;
                        break;
                    case 2:
                        baseCost = 100;
                        multiCost = 9;
                        break;
                    case 3:
                        baseCost = 1000;
                        multiCost = 17;
                        break;
                    case 4:
                        baseCost = 2000;
                        multiCost = 31;
                        break;
                    case 5:
                        baseCost = 3000;
                        multiCost = 86;
                        break;
                    case 6:
                        baseCost = 4000;
                        multiCost = 124;
                        break;
                    case 7:
                        baseCost = 10000;
                        multiCost = 599;
                        break;
                }

                long owned = dTech.owned;
                long totalcost = (baseCost + owned * multiCost);
                long cost = dTech.cost;
                int i = 0;
                while (i < 10) {
                    owned++;
                    cost = (baseCost + (owned * multiCost));
                    totalcost += cost;
                    ++i;
                }

                //Log.d("ryan", "Owned " + owned + " cost " + totalcost);

                if (MainActivity.checkSouls(totalcost)) {
                    p.souls().put(p.souls().get() - totalcost);
                  ////  MyApplication.getInstance().getTechList().get(position).owned += 10;
                 ////   long oNew = MyApplication.getInstance().getTechList().get(position).owned;

                    long a = dTech.owned%8;
                    long b = dTech.owned-a;
                    long c = b/8;
                    if (c!=0) {
                        //dTech.name = minionTiers.get(((int)dTech.owned%8))+" "+c;
                    } else {
                        //dTech.name = minionTiers.get(((int)dTech.owned%8));
                    }
                 ////   dUnit.rate = (dTech.rate+oNew);

                ////    MyApplication.getInstance().getTechList().get(position).cost = (baseCost + (oNew * multiCost));
                    MainActivity.updateSouls();
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }


                MainActivity.updateSouls();

                notifyDataSetChanged();
            }
        });

        viewHolder.buyFifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs_ p = MainActivity.getPrefs();
                //Toast.makeText(getContext(),"Pos "+position+" Ten",Toast.LENGTH_SHORT).show();

                final Tech dTech = techList.get(position);

                int baseCost = -1;
                int multiCost = -1;
                switch (position) {
                    case 0:
                        baseCost = 1;
                        multiCost = 2;
                        break;
                    case 1:
                        baseCost = 10;
                        multiCost = 5;
                        break;
                    case 2:
                        baseCost = 100;
                        multiCost = 9;
                        break;
                    case 3:
                        baseCost = 1000;
                        multiCost = 17;
                        break;
                    case 4:
                        baseCost = 2000;
                        multiCost = 31;
                        break;
                    case 5:
                        baseCost = 3000;
                        multiCost = 86;
                        break;
                    case 6:
                        baseCost = 4000;
                        multiCost = 124;
                        break;
                    case 7:
                        baseCost = 10000;
                        multiCost = 599;
                        break;
                }

                long owned = dTech.owned;
                long totalcost = (baseCost + owned * multiCost);
                long cost = dTech.cost;
                int i = 0;
                while (i < 50) {
                    owned++;
                    cost = (baseCost + (owned * multiCost));
                    totalcost += cost;
                    ++i;
                }

                //Log.d("ryan", "Owned " + owned + " cost " + totalcost);

                if (MainActivity.checkSouls(totalcost)) {
                    p.souls().put(p.souls().get() - totalcost);
                 ////   MyApplication.getInstance().getTechList().get(position).owned += 50;
                 ////   long oNew = MyApplication.getInstance().getTechList().get(position).owned;

                    long a = dTech.owned%8;
                    long b = dTech.owned-a;
                    long c = b/8;
                    if (c!=0) {
                        dTech.name = minionTiers.get(((int)dTech.owned%8))+" "+c;
                    } else {
                        dTech.name = minionTiers.get(((int)dTech.owned%8));
                    }

               ////     MyApplication.getInstance().getTechList().get(position).cost = (baseCost + (oNew * multiCost));
                    //MyApplication.getInstance().getUnitAAList().get(position).rate+= (dTech.rate*50);
               ////     dUnit.rate = (dTech.rate+oNew);
                    MainActivity.updateSouls();
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }


                MainActivity.updateSouls();

                notifyDataSetChanged();
            }
        });

        viewHolder.buyHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs_ p = MainActivity.getPrefs();
                //Toast.makeText(getContext(),"Pos "+position+" Ten",Toast.LENGTH_SHORT).show();

                final Tech dTech = techList.get(position);

                int baseCost = -1;
                int multiCost = -1;
                switch (position) {
                    case 0:
                        baseCost = 1;
                        multiCost = 2;
                        break;
                    case 1:
                        baseCost = 10;
                        multiCost = 5;
                        break;
                    case 2:
                        baseCost = 100;
                        multiCost = 9;
                        break;
                    case 3:
                        baseCost = 1000;
                        multiCost = 17;
                        break;
                    case 4:
                        baseCost = 2000;
                        multiCost = 31;
                        break;
                    case 5:
                        baseCost = 3000;
                        multiCost = 86;
                        break;
                    case 6:
                        baseCost = 4000;
                        multiCost = 124;
                        break;
                    case 7:
                        baseCost = 10000;
                        multiCost = 599;
                        break;
                }

                long owned = dTech.owned;
                long totalcost = (baseCost + owned * multiCost);
                long cost = dTech.cost;
                int i = 0;
                while (i < 100) {
                    owned++;
                    cost = (baseCost + (owned * multiCost));
                    totalcost += cost;
                    ++i;
                }

                //Log.d("ryan", "Owned " + owned + " cost " + totalcost);

                if (MainActivity.checkSouls(totalcost)) {
                    p.souls().put(p.souls().get() - totalcost);
                    dTech.owned += 100;
                ////    long oNew = MyApplication.getInstance().getTechList().get(position).owned;

                    long a = dTech.owned%8;
                    long b = dTech.owned-a;
                    long c = b/8;
                    if (c!=0) {
                        dTech.name = minionTiers.get(((int)dTech.owned%8))+" "+c;
                    } else {
                        dTech.name = minionTiers.get(((int)dTech.owned%8));
                    }

              ////      dTech.cost = (baseCost + (oNew * multiCost));
                    //MyApplication.getInstance().getUnitAAList().get(position).rate+= (dTech.rate*100);
              ////      dUnit.rate = (dTech.rate+oNew);
                    MainActivity.updateSouls();
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }


                MainActivity.updateSouls();

                notifyDataSetChanged();
            }
        });



        return convertView;

    }

    public long doMass(int position, int amount) {
        final Tech dTech = techList.get(position);

        int baseCost = -1;
        int multiCost = -1;
        switch (position) {
            case 0:
                baseCost = 1;
                multiCost = 2;
                break;
            case 1:
                baseCost = 5;
                multiCost = 5;
                break;
            case 2:
                baseCost = 10;
                multiCost = 10;
                break;
            case 3:
                baseCost = 25;
                multiCost = 41;
                break;
            case 4:
                baseCost = 80;
                multiCost = 95;
                break;
            case 5:
                baseCost = 150;
                multiCost = 150;
                break;
            case 6:
                baseCost = 225;
                multiCost = 273;
                break;
            case 7:
                baseCost = 500;
                multiCost = 814;
                break;
        }

        long owned = dTech.owned;
        long totalcost = (baseCost + owned * multiCost);
        long cost = dTech.cost;
        int i = 0;
        while (i < amount) {
            owned++;
            cost = (baseCost + (owned * multiCost));
            totalcost += cost;
            ++i;
        }

        return totalcost;
    }
}