package sv.com.tecnocompu.tecnocompuapp;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import sv.com.tecnocompu.tecnocompuapp.fragments.AboutFragment;
import sv.com.tecnocompu.tecnocompuapp.fragments.ContactFragment;
import sv.com.tecnocompu.tecnocompuapp.fragments.DealsFragment;
import sv.com.tecnocompu.tecnocompuapp.fragments.HomeFragment;
import sv.com.tecnocompu.tecnocompuapp.fragments.ProductsFragment;
import sv.com.tecnocompu.tecnocompuapp.notification.RegistrationService;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        ProductsFragment.OnFragmentInteractionListener,
        DealsFragment.OnFragmentInteractionListener,
        ContactFragment.OnFragmentInteractionListener,
        AboutFragment.OnFragmentInteractionListener
{

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    HomeFragment homeFragment;
    DealsFragment dealsFragment;
    ProductsFragment productsFragment;
    ContactFragment contactFragment;
    AboutFragment aboutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = new Intent(this, RegistrationService.class);
        startService(i);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        homeFragment = new HomeFragment();
        dealsFragment = new DealsFragment();
        productsFragment = new ProductsFragment();
        contactFragment = new ContactFragment();
        aboutFragment = new AboutFragment();

        fragmentTransaction.add(R.id.fragment_container, homeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction fragmentTransactionMenu = getFragmentManager().beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nave_home) {
            fragmentTransactionMenu.replace(R.id.fragment_container, homeFragment);
            fragmentTransactionMenu.addToBackStack(null);
            fragmentTransactionMenu.commit();
        } else if (id == R.id.nav_deals) {
            fragmentTransactionMenu.replace(R.id.fragment_container, dealsFragment);
            fragmentTransactionMenu.addToBackStack(null);
            fragmentTransactionMenu.commit();

        } else if (id == R.id.nav_products) {
            fragmentTransactionMenu.replace(R.id.fragment_container, productsFragment);
            fragmentTransactionMenu.addToBackStack(null);
            fragmentTransactionMenu.commit();
        } else if (id == R.id.nav_contacts) {
            fragmentTransactionMenu.replace(R.id.fragment_container, contactFragment);
            fragmentTransactionMenu.addToBackStack(null);
            fragmentTransactionMenu.commit();
        } else if (id == R.id.nav_about) {
            fragmentTransactionMenu.replace(R.id.fragment_container, aboutFragment);
            fragmentTransactionMenu.addToBackStack(null);
            fragmentTransactionMenu.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
