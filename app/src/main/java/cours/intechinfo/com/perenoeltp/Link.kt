package cours.intechinfo.com.perenoeltp

import android.content.Context

class Link(val context: Context, val name: String, val url: String) {
    var clickAction: String? = null

    suspend fun getResult(): Int {
        Thread.sleep(2000)
        return 5
    }
}