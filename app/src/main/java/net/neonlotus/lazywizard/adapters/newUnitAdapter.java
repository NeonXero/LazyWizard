package net.neonlotus.lazywizard.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.IconButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.enums.SnackbarType;

import net.neonlotus.lazywizard.Fragments.frag_Unit;
import net.neonlotus.lazywizard.MainActivity;
import net.neonlotus.lazywizard.R;
import net.neonlotus.lazywizard.activeandroid.Unit;
import net.neonlotus.lazywizard.application.MyApplication;
import net.neonlotus.lazywizard.application.Prefs_;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class newUnitAdapter extends ArrayAdapter<Unit> {



MyApplication app;

    List<Unit> unitList;

    frag_Unit fragment;


    public newUnitAdapter(Context context, int resource, List<Unit> items, frag_Unit fragment) {
        super(context, resource, items);
        this.unitList = items;
        this.fragment = fragment;

        app = MyApplication.getInstance();


    }

    static class ViewHolder {
        protected TextView viewName;
        protected IconButton buy;
        protected IconButton upgrade;
        //protected IconButton stats;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.unit_row, null);

            viewHolder = new ViewHolder();
            viewHolder.viewName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.buy = (IconButton) convertView.findViewById(R.id.btnBuy);
            viewHolder.upgrade = (IconButton) convertView.findViewById(R.id.btnUp);
            //viewHolder.stats = (IconButton) convertView.findViewById(R.id.btnStats);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        final Unit dUnit = unitList.get(position);
        viewHolder.viewName.setText(dUnit.name);

        viewHolder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPurchaseDialog(position);
            }
        });

        viewHolder.upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpgradeDialog(position);
            }
        });

        /*viewHolder.stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoDialog(position);
            }
        });*/


        return convertView;

    }




    private View positiveAction;

    private void showPurchaseDialog(final int position) {
        final Prefs_ p = MainActivity.getPrefs();
        final Unit thisUnit = unitList.get(position);

        MaterialDialog dialog = new MaterialDialog.Builder(getContext())
                .title(thisUnit.name)
                .customView(R.layout.dialog_unit_buy, true)
                .positiveText("Close")
                .theme(Theme.DARK)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        goAway(dialog);
                    }
                })
                .build();

        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);

        final TextView data = (TextView) dialog.getCustomView().findViewById(R.id.tvData);
        data.setText("Owned: "+thisUnit.owned+"\nProducing: "+thisUnit.rate*thisUnit.owned);


        final long tenBuy = doMass(thisUnit, 10);
        final long fiftyBuy = doMass(thisUnit, 50);
        final long hundredBuy = doMass(thisUnit, 100);


        final Button bone = (Button) dialog.getCustomView().findViewById(R.id.btnOne);
        final Button bten = (Button) dialog.getCustomView().findViewById(R.id.btnTen);
        final Button bfifty = (Button) dialog.getCustomView().findViewById(R.id.btnFifty);
        final Button bhundred = (Button) dialog.getCustomView().findViewById(R.id.btnHundred);

        bone.setText("BUY 1\n"+NumberFormat.getNumberInstance(Locale.US).format(thisUnit.cost));
        bten.setText("BUY 10\n"+NumberFormat.getNumberInstance(Locale.US).format(tenBuy));
        bfifty.setText("BUY 50\n"+NumberFormat.getNumberInstance(Locale.US).format(fiftyBuy));
        bhundred.setText("BUY 100\n"+NumberFormat.getNumberInstance(Locale.US).format(hundredBuy));

        bone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.checkSouls(thisUnit.cost)) {
                    p.souls().put(p.souls().get() - thisUnit.cost);
                    thisUnit.owned++;
                    long oNew = thisUnit.owned;
                    thisUnit.cost = (thisUnit.costbase + (oNew * thisUnit.costmulti));
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }
                //updateTextViews(thisUnit, one, ten, fifty, hundred);
                app.getUnitList().set(position,thisUnit);
                MainActivity.updateSouls();
                updateButtonViews(thisUnit, bone, bten, bfifty, bhundred, data);
            }
        });
        bten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.checkSouls(tenBuy)) {
                    p.souls().put(p.souls().get() - tenBuy);
                    thisUnit.owned+=10;
                    long oNew = thisUnit.owned;
                    thisUnit.cost = (thisUnit.costbase + (oNew * thisUnit.costmulti));
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }
                //updateTextViews(thisUnit, one, ten, fifty, hundred);
                app.getUnitList().set(position,thisUnit);
                MainActivity.updateSouls();
                updateButtonViews(thisUnit, bone, bten, bfifty, bhundred, data);
            }
        });
        bfifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.checkSouls(fiftyBuy)) {
                    p.souls().put(p.souls().get() - fiftyBuy);
                    thisUnit.owned+=50;
                    long oNew = thisUnit.owned;
                    thisUnit.cost = (thisUnit.costbase + (oNew * thisUnit.costmulti));
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }
                //updateTextViews(thisUnit, one, ten, fifty, hundred);
                app.getUnitList().set(position,thisUnit);
                MainActivity.updateSouls();
                updateButtonViews(thisUnit, bone, bten, bfifty, bhundred, data);
            }
        });
        bhundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.checkSouls(hundredBuy)) {
                    p.souls().put(p.souls().get() - hundredBuy);
                    thisUnit.owned+=100;
                    long oNew = thisUnit.owned;
                    thisUnit.cost = (thisUnit.costbase + (oNew * thisUnit.costmulti));
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }
                //updateTextViews(thisUnit, one, ten, fifty, hundred);
                app.getUnitList().set(position,thisUnit);
                MainActivity.updateSouls();
                updateButtonViews(thisUnit, bone, bten, bfifty, bhundred,data);
            }
        });

        dialog.show();
        positiveAction.setEnabled(true); // disabled by default
    }

    /*
    UPGRADE DIALOG
    UPGRADE DIALOG
    UPGRADE DIALOG
    UPGRADE DIALOG
    UPGRADE DIALOG
     */
    private void showUpgradeDialog(final int position) {
        final Prefs_ p = MainActivity.getPrefs();
        final Unit thisUnit = unitList.get(position);

        MaterialDialog dialog = new MaterialDialog.Builder(getContext())
                .title(thisUnit.name)
                .customView(R.layout.dialog_unit_upgrade, true)
                .positiveText("Close")
                .theme(Theme.DARK)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        goAway(dialog);
                    }
                })
                .build();

        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);

        final CheckBox c1 = (CheckBox) dialog.getCustomView().findViewById(R.id.cbOne);
        final CheckBox c2 = (CheckBox) dialog.getCustomView().findViewById(R.id.cbTwo);

        final Button b1 = (Button) dialog.getCustomView().findViewById(R.id.upOne);
        final Button b2 = (Button) dialog.getCustomView().findViewById(R.id.upTwo);
        final Button s1 = (Button) dialog.getCustomView().findViewById(R.id.snackOne);
        final Button s2 = (Button) dialog.getCustomView().findViewById(R.id.snackTwo);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1.setChecked(true);
                b1.setEnabled(false);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2.setChecked(true);
                b2.setEnabled(false);
            }
        });
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Snackbar.with(getContext()) // context
                        .text("Single-line snackbar") // text to display
                        .show(((MainActivity)getContext())); // activity where it is displayed*/
                Toast.makeText(getContext(),"Boring toast that shows you some information",Toast.LENGTH_LONG).show();
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarManager.show(
                        Snackbar.with((MainActivity)getContext())
                                .type(SnackbarType.MULTI_LINE)
                                .duration(Snackbar.SnackbarDuration.LENGTH_LONG)
                                .text("Snazzy Snackbar popup that shows you some information"));
            }
        });


        dialog.show();
        positiveAction.setEnabled(true); // disabled by default
    }

    private void showInfoDialog(final int position) {
        final Unit thisUnit = unitList.get(position);

        MaterialDialog dialog = new MaterialDialog.Builder(getContext())
                .title(thisUnit.name)
                .customView(R.layout.dialog_unit_info, true)
                .positiveText("Close")
                .theme(Theme.DARK)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        app.setUnitList(unitList);
                        goAway(dialog);
                    }
                })
                .build();

        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        final TextView owned = (TextView) dialog.getCustomView().findViewById(R.id.tvOwned);
        final TextView generating = (TextView) dialog.getCustomView().findViewById(R.id.tvGenerating);

        owned.setText("Owned: "+NumberFormat.getNumberInstance(Locale.US).format(thisUnit.owned));
        long gen = thisUnit.owned*thisUnit.rate;
        generating.setText("Generating: "+NumberFormat.getNumberInstance(Locale.US).format(gen));


        dialog.show();
        positiveAction.setEnabled(true); // disabled by default
    }

    public void goAway(MaterialDialog md) {
        md.dismiss();
    }


    public long doMass(Unit u, int amount) {

        long owned = u.owned;
        long postOwned = owned+amount;
        long totalcost = 0;
        //Log.d("zz", "Cost so far " + totalcost);
        //totalcost+= ((u.cost*amount)+(u.costmulti*amount));
        //Log.d("zz", "Cost so far " + totalcost);
        for (long i=owned+1;i<=u.owned+amount;i++) {
            //Log.d("zz","10:: "+""+((u.costmulti*amount)-(u.costbase)));
            //totalcost+= u.cost*i+(u.costmulti);
            //Log.d("zz", "Cost so far " + totalcost);
            //Log.d("zz","loop i- "+i+" cost "+((u.cost+((u.costmulti*i)-(u.costbase)))));
            Log.d("zz","loop i- "+i+" cost "+((u.costmulti*i)-u.costbase));
            totalcost+= ((u.costmulti*i)-u.costbase);
            Log.d("zz", "Cost so far " + totalcost);
        }




        /*for (long i=owned;i<postOwned;i++) {
            //totalcost+= ((i*u.costmulti)-0);
            totalcost+= (u.cost+((i*u.costmulti))-u.costbase);
            Log.d("zz", "Cost so far " + totalcost);
        }*/
        /*for (int i=0;i<amount;i++) {
            totalcost+= ((u.costmulti*i)-u.costbase);
            Log.d("zz", "Cost so far " + totalcost);
        }*/

//        long totalcost = (u.costbase + (owned * u.costmulti));
//        long cost;
//        int i = 0;
//        while (i < amount-1) {
//            owned++;
//            cost = (u.costbase + (owned * u.costmulti));
//            totalcost += cost;
//           i++;
//        }
//        long totalcost = ( u.cost +   (  (u.costmulti*amount)-u.costbase));

        return totalcost;//-u.costbase;
    }



    public void updateTextViews(Unit thisUnit, TextView one, TextView ten, TextView fifty, TextView hundred) {
        one.setText(""+NumberFormat.getNumberInstance(Locale.US).format(thisUnit.cost));
        long tenBuy = doMass(thisUnit, 10);
        ten.setText(""+NumberFormat.getNumberInstance(Locale.US).format(tenBuy));
        long fiftyBuy = doMass(thisUnit, 50);
        fifty.setText(""+NumberFormat.getNumberInstance(Locale.US).format(fiftyBuy));
        long hundredBuy = doMass(thisUnit, 100);
        hundred.setText(""+NumberFormat.getNumberInstance(Locale.US).format(hundredBuy));
    }
    public void updateButtonViews(Unit thisUnit, Button one, Button ten, Button fifty, Button hundred, TextView data) {
        final long tenBuy = doMass(thisUnit, 10);
        final long fiftyBuy = doMass(thisUnit, 50);
        final long hundredBuy = doMass(thisUnit, 100);

        one.setText("BUY 1\n"+NumberFormat.getNumberInstance(Locale.US).format(thisUnit.cost));
        ten.setText("BUY 10\n"+NumberFormat.getNumberInstance(Locale.US).format(tenBuy));
        fifty.setText("BUY 50\n"+NumberFormat.getNumberInstance(Locale.US).format(fiftyBuy));
        hundred.setText("BUY 100\n"+NumberFormat.getNumberInstance(Locale.US).format(hundredBuy));

        data.setText("Owned: "+thisUnit.owned+"\nProducing: "+thisUnit.rate*thisUnit.owned);
    }



}