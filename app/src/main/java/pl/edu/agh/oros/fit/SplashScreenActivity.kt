package pl.edu.agh.oros.fit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val logo:ImageView = findViewById(R.id.logo)
        val text:TextView = findViewById(R.id.logo_text)

        logo.alpha = 0f
        text.alpha = 0f
        logo.animate().setDuration(3000).alpha(1f)
        text.animate().setDuration(3000).alpha(1f).withEndAction(){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

    }
}