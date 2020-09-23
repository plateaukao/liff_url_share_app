package info.plateaukao.liffurlpreviewsharer

import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.service.quicksettings.TileService
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class LiffShareTileService : TileService() {
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }

    override fun onTileAdded() {
        super.onTileAdded()
    }

    override fun onTileRemoved() {
        super.onTileRemoved()
    }

    override fun onStartListening() {
        super.onStartListening()
    }

    override fun onStopListening() {
        super.onStopListening()
    }

    override fun onClick() {
        super.onClick()

        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        startActivityAndCollapse(intent)
    }
}