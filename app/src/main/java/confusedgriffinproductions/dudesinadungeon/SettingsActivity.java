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

    // Locale property
    private static Locale locale;
    // App configuration property
    private static Configuration config;
    // Preference selection
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
            // Add the preferences from the preferences XML
            addPreferencesFromResource(R.xml.preferences);
            // Get the sharedPreferences in order to interact with the application settings
            final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
            // Store the colour setting
            boolean greenTheme = settings.getBoolean("colour_preference", false);
            // Register the onSharedPreferenceChangeListener to the settings SHaredPreferences
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
                        // Change the application's app language based on the user's selection
                        case "language_preference":
                            // Get the user's selection from the shared preferences
                            selection = sharedPreferences.getString(key, "en");
                            // Set the locale to a new locale with a the user's selection
                            locale = new Locale(selection.toString());
                            // Set the default locale to Locale
                            Locale.setDefault(locale);
                            // Declare the configuration
                            config = new Configuration();
                            // Set the Locale to the new configuration
                            config.setLocale(locale);
                            // Update the configuration of the application to Config
                            getActivity().getBaseContext().getResources().updateConfiguration(config,
                                    getActivity().getBaseContext().getResources().getDisplayMetrics());
                            // Display a toast to inform the user of what happened
                            Toast.makeText(getActivity().getBaseContext(), R.string.changed_lang_toast,
                                    Toast.LENGTH_SHORT).show();
                            // Restart the application to ensure a proper language transition.
                            restartApplication();
                            break;

                        // Change the application's colour based on the user's selection
                        case "colour_preference":
                            boolean changeTheme = settings.getBoolean("colour_preference", false);
                            switchColourTheme(changeTheme);
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
            // Create and launch a new intent to launch the MainActivity.
            Intent intent = new Intent(getActivity(), MainActivity.class);
            getActivity().finish();
            startActivity(intent);
        }

        private void switchColourTheme(boolean greenTheme) {
            SharedPreferences.Editor editor = getActivity().getSharedPreferences("colour_preference", MODE_PRIVATE).edit();
            editor.putBoolean("colour_preference", greenTheme);
            editor.apply();

            Intent intent = new Intent(getActivity(), MainActivity.class);
            getActivity().finish();
            startActivity(intent);
        }

    }


}
