package confusedgriffinproductions.dudesinadungeon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import java.util.Locale;

import static confusedgriffinproductions.dudesinadungeon.MainActivity.getLocale;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

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
                String language = sharedPreferences.getString(key, "en");
                Locale locale = new Locale(language);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(getBaseContext(), getString(R.string.changed_lang_toast),
                        Toast.LENGTH_SHORT).show();
                System.out.println("Language: "+language);
                restartActivity();
            }
        });
    }

    /**
     * Creates a new intent to return to the MainActivty in order to refresh the
     * application with the changed language.
     *
     * @author Nicholas Allaire
     */
    private void restartActivity() {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }


}
