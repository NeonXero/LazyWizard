package net.neonlotus.lazywizard.Views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import net.neonlotus.lazywizard.MainActivity;
import net.neonlotus.lazywizard.R;
import net.neonlotus.lazywizard.appliation.Prefs_;

/**
 * Created by Stankus on 11/27/2014.
 */
public class CharacterDialog extends Activity {

    Button b;
    EditText e1, e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.character_builder);
        b = (Button) findViewById(R.id.button);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Unit dUnit = Unit.load(Unit.class, 2);
//                dUnit.owned+=100;
//                dUnit.save();
//                MainActivity.updateSouls();
                Prefs_ pp = MainActivity.getPrefs();
                pp.battleName().put(e1.getText().toString());
                pp.battleStyle().put(e2.getText().toString());
                finish();
            }
        });
    }

}
