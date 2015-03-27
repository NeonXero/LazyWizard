package net.neonlotus.lazywizard.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import net.neonlotus.lazywizard.Fragments.frag_Unit;
import net.neonlotus.lazywizard.activeandroid.Unit;

import java.util.List;

public class UnitAdapter extends ArrayAdapter<Unit> {

    public UnitAdapter(Context context, int resource, List<Unit> items, frag_Unit fragment) {
        super(context, resource, items);
        //this.unitList = items;
        //this.fragment = fragment;
    }

   /* List<Unit> unitList;
    //Fragment_Unit fragment;
    frag_Unit fragment;
    ExpandableLayoutItem eli;
    List<ExpandableLayoutItem> myList = new ArrayList<ExpandableLayoutItem>();

    //public UnitAdapter(Context context, int resource, List<Unit> items, Fragment_Unit fragment) {
    public UnitAdapter(Context context, int resource, List<Unit> items, frag_Unit fragment) {
        super(context, resource, items);
        this.unitList = items;
        this.fragment = fragment;
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

        *//*eli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ryan","eli position " + position+" name "+ MyApplication.getInstance().getUnitList().get(position).name);
                for (int i = 0; i < myList.size(); i++) {
                    if (i == position) {
                        myList.get(i).show();
                    } else {
                        myList.get(i).hide();
                    }
                }
            }
        });*//*

        final Unit dUnit = unitList.get(position);
        //final Unit dz = MyApplication.getInstance().getUnitList().get(position);
        //Log.d("ryan","dunit owned: "+dUnit.owned+" cost "+dUnit.cost);
        //Log.d("ryan","app owned: "+dz.owned+" cost "+dz.cost);
        //Log.d("ryan","--");

        viewHolder.viewName.setText(dUnit.name);
        viewHolder.viewCount.setText("Owned: " + NumberFormat.getNumberInstance(Locale.US).format(dUnit.owned));
        viewHolder.viewRate.setText("Provides: " + NumberFormat.getNumberInstance(Locale.US).format((dUnit.rate * dUnit.owned)));
        viewHolder.viewBuy.setText("Buy for " + NumberFormat.getNumberInstance(Locale.US).format(dUnit.cost));
        //
        long x = doMass(position, 10);
        long y = doMass(position, 50);
        long z = doMass(position, 100);
        viewHolder.buyTen.setText("Buy 10 for " + NumberFormat.getNumberInstance(Locale.US).format(x));
        viewHolder.buyFifty.setText("Buy 50 for " + NumberFormat.getNumberInstance(Locale.US).format(y));
        viewHolder.buyHundred.setText("Buy 100 for " + NumberFormat.getNumberInstance(Locale.US).format(z));

        final List<Unit> temp = MyApplication.getInstance().getUnitList();

        viewHolder.viewBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs_ p = MainActivity.getPrefs();
                if (dUnit.name.equals("Minion")) {
                    if (MainActivity.checkSouls(dUnit.cost)) {
                        p.souls().put(p.souls().get() - dUnit.cost);
                        temp.get(position).owned++;
                        long oNew = MyApplication.getInstance().getUnitList().get(position).owned;
                        temp.get(position).cost = (1 + (oNew * 2));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                } else if (dUnit.name.equals("Jelly")) {
                    if (MainActivity.checkSouls(dUnit.cost)) {
                        p.souls().put(p.souls().get() - dUnit.cost);
                        MyApplication.getInstance().getUnitList().get(position).owned++;
                        long oNew = MyApplication.getInstance().getUnitList().get(position).owned;
                        MyApplication.getInstance().getUnitList().get(position).cost = (5 + (oNew * 5));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                } else if (dUnit.name.equals("Skelebro")) {
                    if (MainActivity.checkSouls(dUnit.cost)) {
                        p.souls().put(p.souls().get() - dUnit.cost);
                        MyApplication.getInstance().getUnitList().get(position).owned++;
                        long oNew = MyApplication.getInstance().getUnitList().get(position).owned;
                        MyApplication.getInstance().getUnitList().get(position).cost = (10 + (oNew * 10));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                } else if (dUnit.name.equals("Cup Bearer")) {
                    if (MainActivity.checkSouls(dUnit.cost)) {
                        p.souls().put(p.souls().get() - dUnit.cost);
                        MyApplication.getInstance().getUnitList().get(position).owned++;
                        long oNew = MyApplication.getInstance().getUnitList().get(position).owned;
                        MyApplication.getInstance().getUnitList().get(position).cost = (25 + (oNew * 41));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                } else if (dUnit.name.equals("Whelp")) {
                    if (MainActivity.checkSouls(dUnit.cost)) {
                        p.souls().put(p.souls().get() - dUnit.cost);
                        MyApplication.getInstance().getUnitList().get(position).owned++;
                        long oNew = MyApplication.getInstance().getUnitList().get(position).owned;
                        MyApplication.getInstance().getUnitList().get(position).cost = (80 + (oNew * 95));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                } else if (dUnit.name.equals("Monolith")) {
                    if (MainActivity.checkSouls(dUnit.cost)) {
                        p.souls().put(p.souls().get() - dUnit.cost);
                        MyApplication.getInstance().getUnitList().get(position).owned++;
                        long oNew = MyApplication.getInstance().getUnitList().get(position).owned;
                        MyApplication.getInstance().getUnitList().get(position).cost = (150 + (oNew * 150));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                } else if (dUnit.name.equals("Farmer")) {
                    if (MainActivity.checkSouls(dUnit.cost)) {
                        p.souls().put(p.souls().get() - dUnit.cost);
                        MyApplication.getInstance().getUnitList().get(position).owned++;
                        long oNew = MyApplication.getInstance().getUnitList().get(position).owned;
                        MyApplication.getInstance().getUnitList().get(position).cost = (225 + (oNew * 273));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                } else if (dUnit.name.equals("Mule")) {
                    if (MainActivity.checkSouls(dUnit.cost)) {
                        p.souls().put(p.souls().get() - dUnit.cost);
                        MyApplication.getInstance().getUnitList().get(position).owned++;
                        long oNew = MyApplication.getInstance().getUnitList().get(position).owned;
                        MyApplication.getInstance().getUnitList().get(position).cost = (500 + (oNew * 814));
                    } else {
                        Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                    }
                }

                //MyApplication.getInstance().setUnitList(temp);
                MainActivity.updateSouls();
                notifyDataSetChanged();
            }
        });


        viewHolder.buyTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs_ p = MainActivity.getPrefs();

                final Unit dUnit = unitList.get(position);
                //Toast.makeText(getContext(),"unit "+dUnit.name,Toast.LENGTH_SHORT).show();
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

                long owned = dUnit.owned;
                long totalcost = (baseCost + owned * multiCost);
                long cost = dUnit.cost;
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
                    MyApplication.getInstance().getUnitList().get(position).owned += 10;
                    long oNew = MyApplication.getInstance().getUnitList().get(position).owned;
                    MyApplication.getInstance().getUnitList().get(position).cost = (baseCost + (oNew * multiCost));

                    //Toast.makeText(getContext(),""+(1+(oNew*2)),Toast.LENGTH_SHORT).show();

                    MainActivity.updateSouls();
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }


                //MainActivity.updateSouls();

                notifyDataSetChanged();
            }
        });

        viewHolder.buyFifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs_ p = MainActivity.getPrefs();
                //Toast.makeText(getContext(),"Pos "+position+" Ten",Toast.LENGTH_SHORT).show();

                final Unit dUnit = unitList.get(position);

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

                long owned = dUnit.owned;
                long totalcost = (baseCost + owned * multiCost);
                long cost = dUnit.cost;
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
                    MyApplication.getInstance().getUnitList().get(position).owned += 50;
                    long oNew = MyApplication.getInstance().getUnitList().get(position).owned;
                    MyApplication.getInstance().getUnitList().get(position).cost = (baseCost + (oNew * multiCost));
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

                final Unit dUnit = unitList.get(position);

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

                long owned = dUnit.owned;
                long totalcost = (baseCost + owned * multiCost);
                long cost = dUnit.cost;
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
                    MyApplication.getInstance().getUnitList().get(position).owned += 100;
                    long oNew = MyApplication.getInstance().getUnitList().get(position).owned;
                    MyApplication.getInstance().getUnitList().get(position).cost = (baseCost + (oNew * multiCost));
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
        final Unit dUnit = unitList.get(position);

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

        long owned = dUnit.owned;
        long totalcost = (baseCost + owned * multiCost);
        long cost = dUnit.cost;
        int i = 0;
        while (i < amount) {
            owned++;
            cost = (baseCost + (owned * multiCost));
            totalcost += cost;
            ++i;
        }

        return totalcost;
    }
*/

}