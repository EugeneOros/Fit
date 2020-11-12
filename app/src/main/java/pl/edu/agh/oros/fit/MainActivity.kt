package pl.edu.agh.oros.fit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){
//    lateinit var toggle: ActionBarDrawerToggle
//    lateinit var navController: NavController
//    lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
//
//    lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_settings, R.id.nav_about), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.drawer_nav_menu, menu)
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)  || super.onSupportNavigateUp()
    }


//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        super.onCreateOptionsMenu(menu)
//        menuInflater.inflate(R.menu.drawer_nav_menu, menu)
//        return true
//    }
//
//
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.about_item -> {
//                Toast.makeText(this, "Manu clicked", Toast.LENGTH_SHORT).show()
//            }
//            R.id.settings_item -> {
//                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
//            }
//        }
//        drawer_layout.closeDrawer(GravityCompat.START)
//        return true
//    }
//
//    private fun setCurrentFragment(fragment: Fragment){
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.main_frame_layout, fragment)
//            commit()
//        }
//    }













    //    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.about_item -> {
//                Toast.makeText(this, "Manu clicked", Toast.LENGTH_SHORT).show()
//            }
//            R.id.settings_item -> {
//                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}

// in onCreate
//        bottom_nav.itemIconTintList = null

//        peopleFragment = PeopleFragment()
//        teamsFragment = TeamsFragment()
//        tournamentFragment = TournamentFragment()

//        bottom_nav.setOnNavigationItemSelectedListener{
//            when(it.itemId){
//                R.id.people_item -> setCurrentFragment(peopleFragment)
//                R.id.teams_item -> setCurrentFragment(teamsFragment)
//                R.id.tournament_item -> setCurrentFragment(tournamentFragment)
//            }
//            true
//        }



//        toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, 0, 0)
//        drawer_layout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        nav_view.setNavigationItemSelectedListener(this)