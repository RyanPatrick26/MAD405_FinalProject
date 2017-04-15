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

    private static Locale locale;
    private static Configuration config;
    private static String selection;

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
                    switch (key) {
                        case "language_preference":
                            System.out.println(key);
                            // Change the application's colour based on the user's selection
                            selection = sharedPreferences.getString(key, "en");
                            System.out.println(selection);
                            locale = new Locale(selection.toString());
                            Locale.setDefault(locale);
                            config = new Configuration();
                            config.setLocale(locale);
                            getActivity().getBaseContext().getResources().updateConfiguration(config,
                                    getActivity().getBaseContext().getResources().getDisplayMetrics());


                            Toast.makeText(getActivity().getBaseContext(), "PLACEHOLDER: SETTINGS CHANGED",
                                    Toast.LENGTH_SHORT).show();

                            // Restart the application to ensure a proper language transition.
                            restartApplication();
                            break;

                        case "colour_preference":
                            // Change the application's colour based on the user's selection
                            selection = sharedPreferences.getString(key, "light");

                            break;
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
