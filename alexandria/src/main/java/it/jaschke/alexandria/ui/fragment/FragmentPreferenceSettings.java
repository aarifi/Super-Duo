package it.jaschke.alexandria.ui.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import it.jaschke.alexandria.R;

/**
 * Created by AdonisArifi on 11.1.2016 - 2016 . alexandria
 */
public class FragmentPreferenceSettings extends PreferenceFragment {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
