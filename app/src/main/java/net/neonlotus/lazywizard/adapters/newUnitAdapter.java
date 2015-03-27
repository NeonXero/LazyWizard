package net.neonlotus.lazywizard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.IconButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

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
        protected IconButton stats;
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
            viewHolder.stats = (IconButton) convertView.findViewById(R.id.btnStats);

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
                //Toast.makeText(getContext(), "POS: " + position + " up", Toast.LENGTH_SHORT).show();
                //showUpgradeDialog(position);
                //showListAlertDialog();
            }
        });

        viewHolder.stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoDialog(position);
            }
        });


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
        final TextView one = (TextView) dialog.getCustomView().findViewById(R.id.tvOneCost);
        final TextView ten = (TextView) dialog.getCustomView().findViewById(R.id.tvTenCost);
        final TextView fifty = (TextView) dialog.getCustomView().findViewById(R.id.tvFiftyCost);
        final TextView hundred = (TextView) dialog.getCustomView().findViewById(R.id.tvHundredCost);

        one.setText(""+NumberFormat.getNumberInstance(Locale.US).format(thisUnit.cost));
        final long tenBuy = doMass(thisUnit, 10);
        ten.setText(""+NumberFormat.getNumberInstance(Locale.US).format(tenBuy));
        final long fiftyBuy = doMass(thisUnit, 50);
        fifty.setText(""+NumberFormat.getNumberInstance(Locale.US).format(fiftyBuy));
        final long hundredBuy = doMass(thisUnit, 100);
        hundred.setText(""+NumberFormat.getNumberInstance(Locale.US).format(hundredBuy));

        final Button bone = (Button) dialog.getCustomView().findViewById(R.id.btnOne);
        final Button bten = (Button) dialog.getCustomView().findViewById(R.id.btnTen);
        final Button bfifty = (Button) dialog.getCustomView().findViewById(R.id.btnFifty);
        final Button bhundred = (Button) dialog.getCustomView().findViewById(R.id.btnHundred);
        //eyy
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
                updateButtonViews(thisUnit, bone, bten, bfifty, bhundred);
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
                updateButtonViews(thisUnit, bone, bten, bfifty, bhundred);
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
                updateButtonViews(thisUnit, bone, bten, bfifty, bhundred);
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
                updateButtonViews(thisUnit, bone, bten, bfifty, bhundred);
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
        long totalcost = (u.costbase + owned * u.costmulti);
        long cost;
        int i = 0;
        while (i < amount) {
            owned++;
            cost = (u.costbase + (owned * u.costmulti));
            totalcost += cost;
            ++i;
        }

        return totalcost;
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
    public void updateButtonViews(Unit thisUnit, Button one, Button ten, Button fifty, Button hundred) {
        final long tenBuy = doMass(thisUnit, 10);
        final long fiftyBuy = doMass(thisUnit, 50);
        final long hundredBuy = doMass(thisUnit, 100);

        one.setText("BUY 1\n"+NumberFormat.getNumberInstance(Locale.US).format(thisUnit.cost));
        ten.setText("BUY 10\n"+NumberFormat.getNumberInstance(Locale.US).format(tenBuy));
        fifty.setText("BUY 50\n"+NumberFormat.getNumberInstance(Locale.US).format(fiftyBuy));
        hundred.setText("BUY 100\n"+NumberFormat.getNumberInstance(Locale.US).format(hundredBuy));
    }



}