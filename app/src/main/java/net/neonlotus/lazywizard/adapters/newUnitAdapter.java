package net.neonlotus.lazywizard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.IconButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import net.neonlotus.lazywizard.Fragments.frag_Unit;
import net.neonlotus.lazywizard.MainActivity;
import net.neonlotus.lazywizard.R;
import net.neonlotus.lazywizard.application.MyApplication;
import net.neonlotus.lazywizard.application.Prefs_;
import net.neonlotus.lazywizard.models.Unit;

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
                .cancelable(false)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        goAway(dialog);
                    }
                })
                .build();

        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);

        final TextView data = (TextView) dialog.getCustomView().findViewById(R.id.tvData);
        //data.setText("Owned: "+thisUnitAA.owned+"\nProducing: "+thisUnitAA.rate*thisUnitAA.owned);
        data.setText("Owned: "+
                NumberFormat.getNumberInstance(Locale.US).format(thisUnit.owned)
                +"\nProducing: "+NumberFormat.getNumberInstance(Locale.US).format(thisUnit.rate* thisUnit.owned));

//tvSouls.setText(NumberFormat.getNumberInstance(Locale.US).format(souls));

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
                //updateTextViews(thisUnitAA, one, ten, fifty, hundred);
                app.getUnitList().set(position, thisUnit);
                MainActivity.updateSouls();
                updateButtonViews(thisUnit, bone, bten, bfifty, bhundred, data);
            }
        });
        bten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (MainActivity.checkSouls(tenBuy)) {
                if (MainActivity.checkSouls(doMass(thisUnit,10))) {
                    //p.souls().put(p.souls().get() - tenBuy);
                    p.souls().put(p.souls().get() - doMass(thisUnit,10));
                    thisUnit.owned+=10;
                    long oNew = thisUnit.owned;
                    thisUnit.cost = (thisUnit.costbase + (oNew * thisUnit.costmulti));
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }
                //updateTextViews(thisUnitAA, one, ten, fifty, hundred);
                app.getUnitList().set(position, thisUnit);
                MainActivity.updateSouls();
                updateButtonViews(thisUnit, bone, bten, bfifty, bhundred, data);
            }
        });
        bfifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (MainActivity.checkSouls(fiftyBuy)) {
                if (MainActivity.checkSouls(doMass(thisUnit,50))) {
                    //p.souls().put(p.souls().get() - fiftyBuy);
                    p.souls().put(p.souls().get() - doMass(thisUnit,50));
                    thisUnit.owned+=50;
                    long oNew = thisUnit.owned;
                    thisUnit.cost = (thisUnit.costbase + (oNew * thisUnit.costmulti));
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }
                //updateTextViews(thisUnitAA, one, ten, fifty, hundred);
                app.getUnitList().set(position, thisUnit);
                MainActivity.updateSouls();
                updateButtonViews(thisUnit, bone, bten, bfifty, bhundred, data);
            }
        });
        bhundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (MainActivity.checkSouls(hundredBuy)) {
                if (MainActivity.checkSouls(doMass(thisUnit,100))) {
                    //p.souls().put(p.souls().get() - hundredBuy);
                    p.souls().put(p.souls().get() - doMass(thisUnit,100));
                    thisUnit.owned+=100;
                    long oNew = thisUnit.owned;
                    thisUnit.cost = (thisUnit.costbase + (oNew * thisUnit.costmulti));
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }
                //updateTextViews(thisUnitAA, one, ten, fifty, hundred);
                app.getUnitList().set(position, thisUnit);
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
                .cancelable(false)
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
        final CheckBox c3 = (CheckBox) dialog.getCustomView().findViewById(R.id.cbThree);
        final CheckBox c4 = (CheckBox) dialog.getCustomView().findViewById(R.id.cbFour);
        final CheckBox c5 = (CheckBox) dialog.getCustomView().findViewById(R.id.cbFive);

        final Button b1 = (Button) dialog.getCustomView().findViewById(R.id.upOne);
        final Button b2 = (Button) dialog.getCustomView().findViewById(R.id.upTwo);
        final Button b3 = (Button) dialog.getCustomView().findViewById(R.id.upThree);
        final Button b4 = (Button) dialog.getCustomView().findViewById(R.id.upFour);
        final Button b5 = (Button) dialog.getCustomView().findViewById(R.id.upFive);

        final Button i1 = (Button) dialog.getCustomView().findViewById(R.id.inOne);
        final Button i2 = (Button) dialog.getCustomView().findViewById(R.id.inTwo);
        final Button i3 = (Button) dialog.getCustomView().findViewById(R.id.inThree);
        final Button i4 = (Button) dialog.getCustomView().findViewById(R.id.inFour);
        final Button i5 = (Button) dialog.getCustomView().findViewById(R.id.inFive);

        final LinearLayout lOne = (LinearLayout) dialog.getCustomView().findViewById(R.id.llUpOne);
        final LinearLayout lTwo = (LinearLayout) dialog.getCustomView().findViewById(R.id.llUpTwo);
        final LinearLayout lThree = (LinearLayout) dialog.getCustomView().findViewById(R.id.llUpThree);
        final LinearLayout lFour = (LinearLayout) dialog.getCustomView().findViewById(R.id.llUpFour);
        final LinearLayout lFive = (LinearLayout) dialog.getCustomView().findViewById(R.id.llUpFive);

        final TextView data = (TextView) dialog.getCustomView().findViewById(R.id.tvDataUpgrade);

        updateUpgradeViews(thisUnit, c1, c2, c3, c4, c5, b1, b2, b3, b4, b5, i1, i2, i3, i4, i5, data, lOne, lTwo, lThree, lFour, lFive);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.checkSouls(1* thisUnit.upgrademulti)) {
                    c1.setChecked(true);
                    b1.setEnabled(false);
                    p.souls().put(p.souls().get()-1* thisUnit.upgrademulti);
                    thisUnit.upgradelevel++;
                    thisUnit.rate = thisUnit.rate*2;
                    //thisUnit.save();
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }
                MainActivity.updateSouls();
                updateUpgradeViews(thisUnit, c1, c2, c3, c4, c5, b1, b2, b3, b4, b5, i1, i2, i3, i4, i5, data, lOne, lTwo, lThree, lFour, lFive);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.checkSouls(2* thisUnit.upgrademulti)) {
                    c2.setChecked(true);
                    b2.setEnabled(false);
                    p.souls().put(p.souls().get()-2* thisUnit.upgrademulti);
                    thisUnit.upgradelevel++;
                    thisUnit.rate = thisUnit.rate*2;
                    //thisUnitAA.save();
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }
                MainActivity.updateSouls();
                updateUpgradeViews(thisUnit, c1, c2, c3, c4, c5, b1, b2, b3, b4, b5, i1, i2, i3, i4, i5, data, lOne, lTwo, lThree, lFour, lFive);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.checkSouls(3* thisUnit.upgrademulti)) {
                    c2.setChecked(true);
                    b2.setEnabled(false);
                    p.souls().put(p.souls().get()-3* thisUnit.upgrademulti);
                    thisUnit.upgradelevel++;
                    thisUnit.rate = thisUnit.rate*2;
                    //thisUnitAA.save();
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }
                MainActivity.updateSouls();
                updateUpgradeViews(thisUnit, c1, c2, c3, c4, c5, b1, b2, b3, b4, b5, i1, i2, i3, i4, i5, data, lOne, lTwo, lThree, lFour, lFive);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.checkSouls(4* thisUnit.upgrademulti)) {
                    c2.setChecked(true);
                    b2.setEnabled(false);
                    p.souls().put(p.souls().get()-4* thisUnit.upgrademulti);
                    thisUnit.upgradelevel++;
                    thisUnit.rate = thisUnit.rate*2;
                    //thisUnitAA.save();
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }
                MainActivity.updateSouls();
                updateUpgradeViews(thisUnit, c1, c2, c3, c4, c5, b1, b2, b3, b4, b5, i1, i2, i3, i4, i5, data, lOne, lTwo, lThree, lFour, lFive);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.checkSouls(5* thisUnit.upgrademulti)) {
                    c2.setChecked(true);
                    b2.setEnabled(false);
                    p.souls().put(p.souls().get()-5* thisUnit.upgrademulti);
                    thisUnit.upgradelevel++;
                    thisUnit.rate = thisUnit.rate*2;
                    //thisUnitAA.save();
                } else {
                    Toast.makeText(getContext(), "Not enough souls", Toast.LENGTH_SHORT).show();
                }
                MainActivity.updateSouls();
                updateUpgradeViews(thisUnit, c1, c2, c3, c4, c5, b1, b2, b3, b4, b5, i1, i2, i3, i4, i5, data, lOne, lTwo, lThree, lFour, lFive);
            }
        });


        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Snackbar.with(getContext()) // context
                        .text("Single-line snackbar") // text to display
                        .show(((MainActivity)getContext())); // activity where it is displayed*/
                Toast.makeText(getContext(),""+1* thisUnit.upgrademulti+" souls",Toast.LENGTH_LONG).show();
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SnackbarManager.show(
                        Snackbar.with((MainActivity)getContext())
                                .type(SnackbarType.MULTI_LINE)
                                .duration(Snackbar.SnackbarDuration.LENGTH_LONG)
                                .text("Snazzy Snackbar popup that shows you some information"));*/
                Toast.makeText(getContext(),""+2* thisUnit.upgrademulti+" souls",Toast.LENGTH_LONG).show();
            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),""+3* thisUnit.upgrademulti+" souls",Toast.LENGTH_LONG).show();
            }
        });
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),""+4* thisUnit.upgrademulti+" souls",Toast.LENGTH_LONG).show();
            }
        });
        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),""+5* thisUnit.upgrademulti+" souls",Toast.LENGTH_LONG).show();
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
        long gen = thisUnit.owned* thisUnit.rate;
        generating.setText("Generating: "+NumberFormat.getNumberInstance(Locale.US).format(gen));


        dialog.show();
        positiveAction.setEnabled(true); // disabled by default
    }

    public void goAway(MaterialDialog md) {
        app.saveAll();
        md.dismiss();
    }


    public long doMass(Unit u, int amount) {

        long owned = u.owned;
        long postOwned = owned+amount;
        long totalcost = 0;

        for (long i=owned+1;i<=postOwned+amount;i++) {
            //Log.d("zz","loop i- "+i+" cost "+((u.costmulti*i)-u.costbase));
            totalcost+= ((u.costmulti*i)-u.costbase);
            //Log.d("zz", "Cost so far " + totalcost);
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
    public void updateButtonViews(Unit thisUnit, Button one, Button ten, Button fifty, Button hundred, TextView data) {
        final long tenBuy = doMass(thisUnit, 10);
        final long fiftyBuy = doMass(thisUnit, 50);
        final long hundredBuy = doMass(thisUnit, 100);

        one.setText("BUY 1\n"+NumberFormat.getNumberInstance(Locale.US).format(thisUnit.cost));
        ten.setText("BUY 10\n"+NumberFormat.getNumberInstance(Locale.US).format(tenBuy));
        fifty.setText("BUY 50\n"+NumberFormat.getNumberInstance(Locale.US).format(fiftyBuy));
        hundred.setText("BUY 100\n"+NumberFormat.getNumberInstance(Locale.US).format(hundredBuy));

        //data.setText("Owned: "+thisUnitAA.owned+"\nProducing: "+thisUnitAA.rate*thisUnitAA.owned);
        data.setText("Owned: "+
                NumberFormat.getNumberInstance(Locale.US).format(thisUnit.owned)
                +"\nProducing: "+NumberFormat.getNumberInstance(Locale.US).format(thisUnit.rate* thisUnit.owned));
    }

    public void updateUpgradeViews(Unit thisUnit, CheckBox c1, CheckBox c2, CheckBox c3, CheckBox c4, CheckBox c5, Button b1, Button b2, Button b3, Button b4, Button b5, Button i1, Button i2, Button i3, Button i4, Button i5, TextView data, LinearLayout lOne, LinearLayout lTwo, LinearLayout lThree, LinearLayout lFour, LinearLayout lFive) {
        data.setText("Level "+ thisUnit.upgradelevel);
        switch (thisUnit.upgradelevel) {
            case 0:
                lTwo.setVisibility(View.GONE);
                lThree.setVisibility(View.GONE);
                lFour.setVisibility(View.GONE);
                lFive.setVisibility(View.GONE);

                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                c5.setChecked(false);

                b1.setEnabled(true);
                b2.setEnabled(false);
                b3.setEnabled(false);
                b4.setEnabled(false);
                b5.setEnabled(false);

                i1.setEnabled(true);
                i2.setEnabled(false);
                i3.setEnabled(false);
                i4.setEnabled(false);
                i5.setEnabled(false);
                break;
            case 1:
                lTwo.setVisibility(View.VISIBLE);
                lThree.setVisibility(View.GONE);
                lFour.setVisibility(View.GONE);
                lFive.setVisibility(View.GONE);

                c1.setChecked(true);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                c5.setChecked(false);

                b1.setEnabled(false);
                b2.setEnabled(true);
                b3.setEnabled(false);
                b4.setEnabled(false);
                b5.setEnabled(false);

                i1.setEnabled(false);
                i2.setEnabled(true);
                i3.setEnabled(false);
                i4.setEnabled(false);
                i5.setEnabled(false);
                break;
            case 2:
                lTwo.setVisibility(View.VISIBLE);
                lThree.setVisibility(View.VISIBLE);
                lFour.setVisibility(View.GONE);
                lFive.setVisibility(View.GONE);

                c1.setChecked(true);
                c2.setChecked(true);
                c3.setChecked(false);
                c4.setChecked(false);
                c5.setChecked(false);

                b1.setEnabled(false);
                b2.setEnabled(false);
                b3.setEnabled(true);
                b4.setEnabled(false);
                b5.setEnabled(false);

                i1.setEnabled(false);
                i2.setEnabled(false);
                i3.setEnabled(true);
                i4.setEnabled(false);
                i5.setEnabled(false);
                break;
            case 3:

                lTwo.setVisibility(View.VISIBLE);
                lThree.setVisibility(View.VISIBLE);
                lFour.setVisibility(View.VISIBLE);
                lFive.setVisibility(View.GONE);

                c1.setChecked(true);
                c2.setChecked(true);
                c3.setChecked(true);
                c4.setChecked(false);
                c5.setChecked(false);

                b1.setEnabled(false);
                b2.setEnabled(false);
                b3.setEnabled(false);
                b4.setEnabled(true);
                b5.setEnabled(false);

                i1.setEnabled(false);
                i2.setEnabled(false);
                i3.setEnabled(false);
                i4.setEnabled(true);
                i5.setEnabled(false);
                break;
            case 4:
                lTwo.setVisibility(View.VISIBLE);
                lThree.setVisibility(View.VISIBLE);
                lFour.setVisibility(View.VISIBLE);
                lFive.setVisibility(View.VISIBLE);

                c1.setChecked(true);
                c2.setChecked(true);
                c3.setChecked(true);
                c4.setChecked(true);
                c5.setChecked(false);

                b1.setEnabled(false);
                b2.setEnabled(false);
                b3.setEnabled(false);
                b4.setEnabled(false);
                b5.setEnabled(true);

                i1.setEnabled(false);
                i2.setEnabled(false);
                i3.setEnabled(false);
                i4.setEnabled(false);
                i5.setEnabled(true);
                break;
            case 5:
                c1.setChecked(true);
                c2.setChecked(true);
                c3.setChecked(true);
                c4.setChecked(true);
                c5.setChecked(true);

                b1.setEnabled(false);
                b2.setEnabled(false);
                b3.setEnabled(false);
                b4.setEnabled(false);
                b5.setEnabled(false);

                i1.setEnabled(false);
                i2.setEnabled(false);
                i3.setEnabled(false);
                i4.setEnabled(false);
                i5.setEnabled(false);
                break;
            default:
                break;
        }
    }



}