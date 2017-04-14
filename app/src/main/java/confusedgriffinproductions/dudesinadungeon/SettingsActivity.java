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

//            settings.getString("language_pref", "en")

            settings.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
                /**
                 * When the user selects a different language or colour, the application will change the device's
                 * location settings to display the app in either French of English
                 * OR
                 * Change the application's colour theme to Light or Dark
                 *
                 * @author Nicholas Allaire
                 * @param sharedPreferences
                 * @param key
                 */
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    String selection = sharedPreferences.getString(key, "en");
                    System.out.println(key+": "+selection);
                    String keyString = key.toString();
                    System.out.println(keyString);
                    // check to see if the language option was selected
                    if (keyString == "language_preference") {
                        Locale locale = new Locale(selection);
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getActivity().getBaseContext().getResources().updateConfiguration(config,
                                getActivity().getBaseContext().getResources().getDisplayMetrics());
                        Toast.makeText(getActivity().getBaseContext(), "PLACEHOLDER: CHANGED LANG",
                                Toast.LENGTH_SHORT).show();
                        restartApplication();
                    } else if (keyString == "colour_preference") {
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
