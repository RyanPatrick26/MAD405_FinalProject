package confusedgriffinproductions.dudesinadungeon;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import static java.net.Proxy.Type.HTTP;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MainFragment.OnFragmentInteractionListener,
        CharacterListFragment.OnFragmentInteractionListener,
        CharacterCreatorFragment.OnFragmentInteractionListener,
        ItemListFragment.OnFragmentInteractionListener,
        SpellListFragment.OnFragmentInteractionListener,
        ItemViewerFragment.OnFragmentInteractionListener,
        SpellViewerFragment.OnFragmentInteractionListener,
        CreditsFragment.OnFragmentInteractionListener{

    // Fragment manager to allow us to display, remove, and create fragments
    FragmentManager fm = getSupportFragmentManager();

    // Email address of the application creators
    // TODO: REMOVE MY EMAIL ADDRESS
    String creatorEmail = "nicholas.allaire@stclairconnect.ca";
    // SMS Message String
    String smsMessage = R.string.sms_message + " https://www.diad.app.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        FragmentTransaction trans = fm.beginTransaction();
        trans.replace(R.id.content_main, new CreditsFragment());
        trans.commit();

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
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // If Else Statement to navigate to each fragment throught the nav drawer
        if (id == R.id.nav_about) {
            // Navigate to the Main Fragment
            FragmentTransaction tran = fm.beginTransaction();
            tran.replace(R.id.content_main, new MainFragment());
            tran.commit();
        } else if (id == R.id.nav_create_char) {
            // Navigate to the Create Character Fragment
            FragmentTransaction tran = fm.beginTransaction();
            tran.replace(R.id.content_main, new CharacterCreatorFragment());
            tran.commit();
        } else if (id == R.id.nav_view_char) {
            // Navigate to the View Character List Fragment
            FragmentTransaction tran = fm.beginTransaction();
            tran.replace(R.id.content_main, new CharacterListFragment());
            tran.commit();
        } else if (id == R.id.nav_items) {
            // Navigate to the Item List Fragment
            FragmentTransaction tran = fm.beginTransaction();
            tran.replace(R.id.content_main, new ItemListFragment());
            tran.commit();
        } else if (id == R.id.nav_spells) {
            // Navigate to the Spells List Fragment
            FragmentTransaction tran = fm.beginTransaction();
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

}
