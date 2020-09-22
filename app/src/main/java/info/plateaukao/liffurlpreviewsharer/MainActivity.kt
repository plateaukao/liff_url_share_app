package info.plateaukao.liffurlpreviewsharer

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent?.action == Intent.ACTION_SEND) {
            handleSendText(intent) // Handle text being sent
        }
        else {
        }
    }

    private fun handleSendText(intent: Intent) {
        var sharedUrl = intent.getStringExtra(Intent.EXTRA_TEXT)
        if (sharedUrl?.startsWith("http") != true) {
            sharedUrl = sharedUrl?.substring(sharedUrl.indexOf("http"))
        }

        var liffUrl = "https://liff.line.me/1654950578-67erzKdm"
        liffUrl += "?url=" + URLEncoder.encode(sharedUrl);
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(liffUrl))
        startActivity(webIntent)
        finish()
    }
}
