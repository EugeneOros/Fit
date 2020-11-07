package pl.edu.agh.oros.fit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {
    lateinit var logoText : TextView
    lateinit var logoImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        logoImage = findViewById(R.id.logo)
        logoText = findViewById(R.id.logo_text)

        logoImage.alpha = 0f
        logoText.alpha = 0f
        logoImage.animate().setDuration(3000).alpha(1f)
        logoText.animate().setDuration(3000).alpha(1f).withEndAction(){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

    }
}