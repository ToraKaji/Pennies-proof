package deepdive.cnm.edu.penniesv10.view.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuView.ItemView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import deepdive.cnm.edu.penniesv10.R;
import deepdive.cnm.edu.penniesv10.model.db.PenniesDB;
import deepdive.cnm.edu.penniesv10.model.entities.User;
import deepdive.cnm.edu.penniesv10.view.activities.fragments.PennyPopper;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {
  //Set a user and database for the App
  private User user;
  private PenniesDB db;

  private NavigationView navigationView;
  private DrawerLayout drawer;
  private Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    navigationView = (NavigationView) findViewById(R.id.nav_view);
    toolbar = (Toolbar) findViewById(R.id.toolbar);

    //Create a database and user for the App
    db = PenniesDB.getInstance(getApplicationContext());
    new QueryUser().execute();


      setSupportActionBar(toolbar);

      ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
          this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
      drawer.addDrawerListener(toggle);
      toggle.syncState();


      navigationView.setItemIconTintList(null);
      navigationView.setNavigationItemSelectedListener(this);


  }
  @Override
  public void onBackPressed() {
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

    switch (id){
      //TODO Add cases for options menu
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    Bundle bundle = new Bundle();
    PennyPopper pennyPopper = new PennyPopper();
    switch (item.getItemId()){

      case R.id.three:
        bundle.putInt("rows", 3);
        bundle.putInt("cols", 3);
        pennyPopper.setArguments(bundle);
        switchFragment(pennyPopper, true, "penny_popper");
        break;

      case R.id.four:
        bundle.putInt("rows", 4);
        bundle.putInt("cols", 4);
        pennyPopper.setArguments(bundle);
        switchFragment(pennyPopper, true, "penny_popper");
        break;

      case R.id.five:
        bundle.putInt("rows", 5);
        bundle.putInt("cols", 5);
        pennyPopper.setArguments(bundle);
        switchFragment(pennyPopper, true, "penny_popper");
        break;

    }

    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }



  private void switchFragment(Fragment fragment, boolean useStack, String variant) {
    FragmentManager manager = getSupportFragmentManager();
    String tag = fragment.getClass().getSimpleName() + ((variant != null) ? variant : "");
    if (manager.findFragmentByTag(tag) != null) {
      manager.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.fragment_container, fragment, tag);
    if (useStack) {
      transaction.addToBackStack(tag);
    }
    transaction.commit();
  }


  private class QueryUser extends AsyncTask<Void, Void, User> {

    @Override
    protected void onPostExecute(User user) {
      setUser(user);
    }

    @Override
    protected User doInBackground(Void... voids) {
      User user;
      List<User> users = db.getUserDao().select();
      if (users.isEmpty()) {
        user = new User();
        long id = db.getUserDao().insert(user);
        user = db.getUserDao().select(id);
      } else {
        user = users.get(0);
      }
      return user;
    }
  }

}
