package net.neonlotus.lazywizard.application;

import org.androidannotations.annotations.sharedpreferences.DefaultLong;
import org.androidannotations.annotations.sharedpreferences.SharedPref;


@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface Prefs {

    @DefaultLong(123000)
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