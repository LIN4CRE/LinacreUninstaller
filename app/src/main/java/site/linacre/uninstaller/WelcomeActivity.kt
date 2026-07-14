package site.linacre.uninstaller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        
        findViewById<Button>(R.id.btn_start).setOnClickListener {
            getSharedPreferences("prefs", Context.MODE_PRIVATE).edit().putBoolean("first_run", false).apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
