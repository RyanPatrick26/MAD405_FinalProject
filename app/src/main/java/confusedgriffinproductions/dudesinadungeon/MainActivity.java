package confusedgriffinproductions.dudesinadungeon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Locale;

import static java.net.Proxy.Type.HTTP;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MainFragment.OnFragmentInteractionListener,
        CharacterListFragment.OnFragmentInteractionListener,
        CharacterCreatorFragment.OnFragmentInteractionListener,
        ItemListFragment.OnFragmentInteractionListener,
        SpellListFragment.OnFragmentInteractionListener,
        CreditsFragment.OnFragmentInteractionListener,
        ItemViewerFragment.OnFragmentInteractionListener,
        SpellViewerFragment.OnFragmentInteractionListener{

    // Fragment manager to allow us to display, remove, and create fragments
    FragmentManager fm = getSupportFragmentManager();

    // Email address of the application creators
    // TODO: REMOVE MY EMAIL ADDRESS
    String creatorEmail = "nicholas.allaire@stclairconnect.ca";
    // SMS Message String
    String smsMessage = R.string.sms_message + " https://www.diad.app.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Check to make sure the correct language is set OR set the proper one
        checkLanguage();

        // This will prepare the app to load the dark theme if the value stored in SharedPreferences is set to TRUE
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        boolean useGreenTheme = settings.getBoolean("colour_preference", false);

        if (useGreenTheme) {
            setTheme(R.style.AppTheme_Dark);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_settings_black_24dp);
        toolbar.setOverflowIcon(drawable);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction tran = fm.beginTransaction();
        tran.replace(R.id.content_main, new MainFragment());
        tran.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // get the Fragment Manager
        FragmentManager fm = getSupportFragmentManager();
        // First, check if the drawer is open
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            // If it is, simply close the drawer
            drawer.closeDrawer(GravityCompat.START);
        } else if (fm.getBackStackEntryCount() > 0) {
            // If the drawer is closed and there is something on the backstack, go to it
            fm.popBackStack();
        } else {
            // If not, proceed as usual
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // Go to the settings menu
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.credits_settings) {
            // Navigate to the Credits Fragment
            FragmentTransaction tran = fm.beginTransaction();
            tran.replace(R.id.content_main, new CreditsFragment());
            tran.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // If Else Statement to navigate to each fragment through the nav drawer
        if (id == R.id.nav_about) {
            // Navigate to the Main Fragment
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.slide_down_in, R.anim.slide_down_out);
            tran.addToBackStack(null);
            tran.replace(R.id.content_main, new MainFragment());
            tran.commit();
        } else if (id == R.id.nav_create_char) {
            // Navigate to the Create Character Fragment
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.slide_down_in, R.anim.slide_down_out);
            tran.addToBackStack(null);
            tran.replace(R.id.content_main, new CharacterCreatorFragment());
            tran.commit();
        } else if (id == R.id.nav_view_char) {
            // Navigate to the View Character List Fragment
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.slide_down_in, R.anim.slide_down_out);
            tran.addToBackStack(null);
            tran.replace(R.id.content_main, new CharacterListFragment());
            tran.commit();
        } else if (id == R.id.nav_items) {
            // Navigate to the Item List Fragment
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.slide_down_in, R.anim.slide_down_out);
            tran.addToBackStack(null);
            tran.replace(R.id.content_main, new ItemListFragment());
            tran.commit();
        } else if (id == R.id.nav_spells) {
            // Navigate to the Spells List Fragment
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.slide_down_in, R.anim.slide_down_out);
            tran.addToBackStack(null);
            tran.replace(R.id.content_main, new SpellListFragment());
            tran.commit();
        } else if (id == R.id.nav_email) {
            // Send an email to the app creators
            emailAppCreators();
        } else if (id == R.id.nav_share) {
            // Share the application with a friend via SMS
            shareViaSMS();
        } else if (id == R.id.nav_website) {
            // Navigate to the company website
            visitWebsite();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Empty method to allow interaction between fragments
     *
     * @param uri
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * Prepares an email to send to the application creators
     * @author Nicholas Allaire
     */
    public void emailAppCreators() {
        // Get the email address of the app creators
        String[] emailaddresses = {creatorEmail};
        // Create a SendTo intent
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        // Put the email address, body, and subject into the email
        intent.putExtra(Intent.EXTRA_EMAIL, emailaddresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body));
        // If the device has email capabilities, launch the intent
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        else{
            // if the device does not have email capabilities, display a snackbar
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    getString(R.string.snackbar_no_software), Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
    }

    /**
     * Prepares a text message to send to someone in regards to the application
     * @author Nicholas Allaire
     */
    public void shareViaSMS() {
        // Create the SMS intent
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // Set the type to SMS
        intent.setData(Uri.parse("sms:"));
        // Set the body of the SMS
        intent.putExtra("sms_body", smsMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            // if the device has SMS capabilities, launch the intent
            startActivity(intent);
        } else {
            // if the device does not have SMS capabilities, display a snackbar
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    getString(R.string.snackbar_no_software), Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
    }

    /**
     * Opens the user's device's web browser and navigates to the game website
     * @author Nicholas Allaire
     */
    public void visitWebsite() {
        // Create a string of the website URL
        String url = "https://en.wikipedia.org/wiki/Tabletop_role-playing_game";
        // Set the type of intent
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // Set the URL for the intent
        intent.setData(Uri.parse(url));
        if (intent.resolveActivity(getPackageManager()) != null) {
            // if the device has SMS capabilities, launch the intent
            startActivity(intent);
        } else {
            // if the device does not have Web Browsing capabilities, display a snackbar
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    getString(R.string.snackbar_no_software), Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
    }

    /**
     * Gets the current Locale of the application in order to properly set the application language.
     *
     * @param context
     * @return new Locale containing the correct language
     * @author Nicholas Allaire
     */
    public static Locale getLocale(Context context){
        // Get the shared preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        // Get the language from the shared preferences
        String lang = sharedPreferences.getString("language_preference", "en");
        // Switch statement to determine which language code to return
        switch (lang) {
            case "en":
                lang = "en";
                break;
            case "fr":
                lang = "fr";
                break;
        }
        // return the proper language
        return new Locale(lang);
    }

    /**
     * Uses the getLocale method to properly set the Locale for the entire application
     * in order to display the application in the correct language.
     *
     * @author Nicholas Allaire
     */
    public void checkLanguage() {
        // Get the application resources
        Resources resources = getResources();
        // Get the application's configuration
        Configuration config = resources.getConfiguration();
        // Get the current Locale
        Locale locale = getLocale(this);
        // If the Locale doesn't equal the current locale, set it as such
        if (!config.locale.equals(locale)) {
            config.setLocale(locale);
            // update the configuration
            resources.updateConfiguration(config, null);
        }
    }


    public void themeChange(Toolbar toolbar) {
        // Check the theme

        // Change the theme based on the returned theme
    }

}
