package net.neonlotus.lazywizard.views;

import android.content.Context;
import android.widget.IconButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.neonlotus.lazywizard.R;
import net.neonlotus.lazywizard.activeandroid.Unit;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;


@EViewGroup(R.layout.unit_row)
public class UnitAdapterRow extends LinearLayout {

    @ViewById
    TextView tvName;
    @ViewById
    IconButton btnBuy;
    @ViewById
    IconButton btnUp;
    @ViewById
    IconButton btnStats;

    public UnitAdapterRow(Context context) {
        super(context);
    }

    public void bind(Unit unit) {
        tvName.setText(unit.name);
    }
}