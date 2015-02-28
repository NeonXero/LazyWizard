package net.neonlotus.lazywizard.appliation;

import org.androidannotations.annotations.sharedpreferences.SharedPref;


@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface Prefs {


    long souls();
    long bonusSouls();

    int setupCount();
    int techSetupCount();
    int itemSetupCount();

    int rateCalculation();

    String battleName();
    String battleStyle();

    int moonRocks();
}