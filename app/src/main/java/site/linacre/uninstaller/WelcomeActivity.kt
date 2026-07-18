package site.linacre.uninstaller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        if (!prefs.getBoolean("first_run", true)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_welcome)

        findViewById<Button>(R.id.btn_start).setOnClickListener {
            prefs.edit().putBoolean("first_run", false).apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
