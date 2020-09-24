package info.plateaukao.liffurlpreviewsharer

import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    private lateinit var inputTextField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputTextField = findViewById<EditText>(R.id.textInputEditText)
        val shareButton = findViewById<Button>(R.id.button_share)

        shareButton.setOnClickListener { _ ->
            handleSendText(inputTextField.text.toString())
        }

        if (intent?.action == Intent.ACTION_SEND) {
            var url = intent.getStringExtra(Intent.EXTRA_TEXT) ?: ""
            handleSendText(url) // Handle text being sent
        }
    }

    private fun handleSendText(url: String) {
        var sharedUrl = url
        if (sharedUrl.isEmpty()) return

        if (!sharedUrl.startsWith("http")) {
            sharedUrl = sharedUrl.substring(sharedUrl.indexOf("http"))
        }

        var liffUrl = "https://liff.line.me/1654950578-67erzKdm"
        liffUrl += "?url=" + URLEncoder.encode(sharedUrl)
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(liffUrl))
        startActivity(webIntent)
        finish()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        // what the heck!
        val clipBoardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val copiedString = clipBoardManager.primaryClip?.getItemAt(0)?.text?.toString() ?: ""
        if (copiedString.isNotEmpty()) {
            handleSendText(copiedString)
        }
    }
}
