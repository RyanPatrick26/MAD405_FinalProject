package confusedgriffinproductions.dudesinadungeon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Locale;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new DudesInADungeonPreferencesFragment()).commit();
    }

    // Create a class for the Preferences Fragment inside of the SettingsActivity
    public static class DudesInADungeonPreferencesFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

            settings.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
                /**
                 * When the user selects a different language, the application will change the device's
                 * location settings to display the app in either French of English
                 *
                 * @author Nicholas Allaire
                 * @param sharedPreferences
                 * @param key
                 */
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    String selection = sharedPreferences.getString(key, "");
                    // check to see if the language option was selected
                    if (selection == "en" || selection == "fr") {
                        Locale locale = new Locale(selection);
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getActivity().getBaseContext().getResources().updateConfiguration(config,
                                getActivity().getBaseContext().getResources().getDisplayMetrics());
                        Toast.makeText(getActivity().getBaseContext(), getString(R.string.changed_lang),
                                Toast.LENGTH_SHORT).show();
                        restartApplication();
                    } else {
                        // if language option wasn't selected, change the application colour theme
                    }

                }
            });

        }

        /**
         * Restarts the application by relaunching the MainActivity in order to
         * change the colour theme of the application or the language.
         *
         * @author Nicholas Allaire
         */
        private void restartApplication() {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            getActivity().finish();
            startActivity(intent);
        }

    }


}
