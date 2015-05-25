package net.neonlotus.lazywizard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.daimajia.numberprogressbar.NumberProgressBar;

import net.neonlotus.lazywizard.MainActivity;
import net.neonlotus.lazywizard.R;
import net.neonlotus.lazywizard.application.MyApplication;
import net.neonlotus.lazywizard.models.Unit;

import java.util.List;

/**
 * Created by Stankus on 5/8/2015.
 */
public class UpgradeUnitAdapter extends ArrayAdapter<String> {
    MyApplication app;

    List<Unit> unitList;
    List<String> abc;
    NumberProgressBar progbar;

    //public UpgradeUnitAdapter(Context context, int resource, List<Unit> items) {
    public UpgradeUnitAdapter(Context context, int resource, List<String> items, NumberProgressBar prog) {
        super(context, resource, items);
        this.unitList = MainActivity.getUnits();
        this.abc = items;
        this.progbar = prog;

        app = MyApplication.getInstance();
    }

    static class ViewHolder {

        protected Button upgradeMe;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.upgrade_unit_row, null);

            viewHolder = new ViewHolder();
            viewHolder.upgradeMe = (Button) convertView.findViewById(R.id.btnUpgrade);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //viewHolder.upgradeMe.setText("Cool upgrade "+position+"\nCosts "+unitList.get(position).getUpgrademulti());
        viewHolder.upgradeMe.setText(""+abc.get(position)+"\nCosts "+unitList.get(position).getUpgrademulti());
        for (int i=0;i<unitList.size();i++) {
            if (unitList.get(i).upgradelevel>position) {
                viewHolder.upgradeMe.setEnabled(false);
            } else {
                viewHolder.upgradeMe.setEnabled(true);
            }
        }

        viewHolder.upgradeMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progbar.setProgress(unitList.get(position).upgradelevel++);
            }
        });

        /*final Unit dUnit = unitList.get(position);
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
        });*/

        /*viewHolder.stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoDialog(position);
            }
        });*/


        return convertView;

    }
}

