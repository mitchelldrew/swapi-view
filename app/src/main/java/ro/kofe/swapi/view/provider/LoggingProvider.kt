package ro.kofe.swapi.view.provider

import android.util.Log
import ro.kofe.swapi.presenter.provider.ILoggingProvider

class LoggingProvider: ILoggingProvider {
    override fun log(logTag: String, message: String) {
        Log.v(logTag, message)
    }
}