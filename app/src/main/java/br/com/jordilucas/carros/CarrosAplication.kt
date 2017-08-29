package br.com.jordilucas.carros

import android.app.Application
import android.util.Log

/**
 * Created by jordi on 29/08/17.
 */
class CarrosAplication : Application() {
    private val TAG = "CarrosAplication"
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: CarrosAplication? = null
        fun getInstance(): CarrosAplication{
            if(appInstance == null){
                throw IllegalStateException("Configure a calsse de application no AndroidManifest.xml")
            }
            return appInstance!!
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "CarrosApplication.onTerminate()")
    }

}