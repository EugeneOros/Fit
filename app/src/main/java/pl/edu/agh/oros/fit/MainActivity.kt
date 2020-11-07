package pl.edu.agh.oros.fit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, 0, 0)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nav_view.setNavigationItemSelectedListener(this)

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        setSupportActionBar(main_toolbar)
//        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//        supportActionBar!!.setCustomView(R.layout.action_bar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.nav_drawer_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_item -> {
                Toast.makeText(this, "Manu clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.settings_item -> {
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_item -> {
                Toast.makeText(this, "Manu clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.settings_item -> {
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}