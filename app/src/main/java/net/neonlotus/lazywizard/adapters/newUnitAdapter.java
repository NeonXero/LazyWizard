package net.neonlotus.lazywizard.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.IconButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alertdialogpro.AlertDialogPro;
import com.andexert.expandablelayout.library.ExpandableLayoutItem;

import net.neonlotus.lazywizard.Fragments.TestFragment;
import net.neonlotus.lazywizard.R;
import net.neonlotus.lazywizard.activeandroid.Unit;
import net.neonlotus.lazywizard.appliation.App;

import java.util.ArrayList;
import java.util.List;

public class newUnitAdapter extends ArrayAdapter<Unit> {

    List<Unit> unitList;
    //Fragment_Unit fragment;
    TestFragment fragment;
    ExpandableLayoutItem eli;
    List<ExpandableLayoutItem> myList = new ArrayList<ExpandableLayoutItem>();

    //public UnitAdapter(Context context, int resource, List<Unit> items, Fragment_Unit fragment) {
    public newUnitAdapter(Context context, int resource, List<Unit> items, TestFragment fragment) {
        super(context, resource, items);
        this.unitList = items;
        this.fragment = fragment;
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


        final List<Unit> temp = App.getInstance().getUnitList();

        viewHolder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"POS: "+position+" buy",Toast.LENGTH_SHORT).show();
                //showBuyDialog(position);
                buyDialog(position);
            }
        });

        viewHolder.upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "POS: " + position + " up", Toast.LENGTH_SHORT).show();
                showUpgradeDialog(position);
                //showListAlertDialog();
            }
        });

        viewHolder.stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"POS: "+position+" stats",Toast.LENGTH_SHORT).show();
                showInfoDialog(position);
            }
        });


        return convertView;

    }

    private void showBuyDialog(int position) {
        createAlertDialogBuilder()
                .setTitle(unitList.get(position).name)
                .setMessage("BUY UEUEHUHEF")
                .setNegativeButton("Cancel", new ButtonClickedListener("Cancel"))
                .setPositiveButton("Ok", new ButtonClickedListener("Ok")).show();

    }

    private void showUpgradeDialog(int position) {


        createAlertDialogBuilder()
                .setTitle(unitList.get(position).name)
                .setMessage("UPGRADE HUEUEHUE")
                .setPositiveButton("Ok", new ButtonClickedListener("Ok")).show();

    }

    private void showInfoDialog(int position) {
        String o = "Owned: "+unitList.get(position).owned+"\n\n";
        String r = "Rate: "+unitList.get(position).rate+"\n\n";
        String xxx = "Other?";
        String message = o+r+xxx;

        createAlertDialogBuilder()
                .setTitle(unitList.get(position).name)
                .setMessage(message)
                .setNegativeButton("Cancel", new ButtonClickedListener("Cancel"))
                .setPositiveButton("Ok", new ButtonClickedListener("Ok")).show();
    }

    private void showListAlertDialog() {
        final String[] list = new String[]{"Argentina", "Canada", "China (中国)", "Japan (日本)",
                "United States"};
        createAlertDialogBuilder()
                .setTitle("Choose your country")
                .setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "" + list[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private AlertDialog.Builder createAlertDialogBuilder() {
        int mTheme = R.style.Theme_AlertDialogPro_Material;
        return new AlertDialogPro.Builder(getContext(), mTheme);
    }

    private class ButtonClickedListener implements DialogInterface.OnClickListener {
        private CharSequence mShowWhenClicked;

        public ButtonClickedListener(CharSequence showWhenClicked) {
            mShowWhenClicked = showWhenClicked;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which==-1) {
                Toast.makeText(getContext(), "neg one" + which, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "not neg one" + which, Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void buyDialog(int position) {
        new MaterialDialog.Builder(getContext())
                .title(unitList.get(position).name)
                .content("content")
                .positiveText("positive")
                .negativeText("negative")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        Log.d("zz", "posssss");
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        Log.d("zz", "neggg");
                    }

                    @Override
                    public void onNeutral(MaterialDialog dialog) {
                        Log.d("zz", "nuenuenuenuen");
                    }
                })
                .autoDismiss(false)
                .show();



    }



}