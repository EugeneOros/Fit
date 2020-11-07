package pl.edu.agh.oros.fit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.bottom_nav.*
import pl.edu.agh.oros.fit.fragments.PeopleFragment
import pl.edu.agh.oros.fit.fragments.TeamsFragment
import pl.edu.agh.oros.fit.fragments.TournamentFragment


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var peopleFragment: PeopleFragment
    lateinit var teamsFragment: TeamsFragment
    lateinit var tournamentFragment: TournamentFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_nav.itemIconTintList = null

        peopleFragment = PeopleFragment()
        teamsFragment = TeamsFragment()
        tournamentFragment = TournamentFragment()

        bottom_nav.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.people_item -> setCurrentFragment(peopleFragment)
                R.id.teams_item -> setCurrentFragment(teamsFragment)
                R.id.tournament_item -> setCurrentFragment(tournamentFragment)
            }
            true
        }



        toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, 0, 0)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nav_view.setNavigationItemSelectedListener(this)




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.drawer_nav_menu, menu)
        return true
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_item -> {
                Toast.makeText(this, "Manu clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.settings_item -> {
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_frame_layout, fragment)
            commit()
        }
    }


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