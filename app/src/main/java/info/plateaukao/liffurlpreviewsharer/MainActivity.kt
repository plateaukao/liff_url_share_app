package info.plateaukao.liffurlpreviewsharer

import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

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
        if (intent?.getBooleanExtra(LIFF_DRAW, false) == true) {
            launchDrawingLiff()
            finishAndRemoveTask()
        }
    }

    private fun launchDrawingLiff() {
        var liffUrl = "https://liff.line.me/1655257200-9lV3JD8k"
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(liffUrl))
        startActivity(webIntent)
    }

    private fun handleSendText(url: String) {
        var sharedUrl = url
        if (!sharedUrl.startsWith("http")) {
            sharedUrl = sharedUrl.substring(sharedUrl.indexOf("http"))
        }

        if (sharedUrl.isEmpty() || !URLUtil.isValidUrl(sharedUrl)) return

        var liffUrl = "https://liff.line.me/1654950578-67erzKdm"
        liffUrl += "?url=" + URLEncoder.encode(sharedUrl)
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(liffUrl))
        startActivity(webIntent)
        finishAndRemoveTask()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (intent?.extras?.get(AUTO_SEND) != true) return

        // what the heck!
        val clipBoardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val copiedString = clipBoardManager.primaryClip?.getItemAt(0)?.text?.toString() ?: ""
        if (copiedString.isNotEmpty()) {
            handleSendText(copiedString)
        }
    }

    companion object {
        const val AUTO_SEND = "auto_send_arg"
        const val LIFF_DRAW = "liff_draw_arg"
    }
}
