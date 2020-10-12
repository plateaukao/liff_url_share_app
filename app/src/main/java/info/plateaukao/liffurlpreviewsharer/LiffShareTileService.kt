package info.plateaukao.liffurlpreviewsharer

import android.content.Intent
import android.os.Build
import android.service.quicksettings.TileService
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class LiffShareTileService : TileService() {
    override fun onClick() = startActivityAndCollapse(
        Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(MainActivity.AUTO_SEND, true)
        }
    )
}