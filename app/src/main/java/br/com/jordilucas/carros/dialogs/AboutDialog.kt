package br.com.jordilucas.carros.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.widget.TextView

import br.com.jordilucas.carros.R

/**
 * Created by jordi on 30/08/17.
 */
class AboutDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val aboutBody = SpannableStringBuilder()
        val versionName = getAppVersionName()

        val html = Html.fromHtml(getString(R.string.about_dialog_text, versionName))
        aboutBody.append(html)

        val inflater = LayoutInflater.from(activity)
        val view = inflater.inflate(R.layout.dialog_about, null) as TextView
        view.text = aboutBody
        view.movementMethod = LinkMovementMethod()

        return AlertDialog.Builder(activity)
                .setTitle(R.string.about_dialog_title)
                .setView(view)
                .setPositiveButton(R.string.ok){dialog, _ -> dialog.dismiss()}
                .create()
    }




    fun getAppVersionName(): String{
        val pm = activity.packageManager
        val packageName = activity.packageName
        var versionName:String
        try {
            val info = pm.getPackageInfo(packageName,0)
            versionName = info.versionName
        }
        catch (ex: PackageManager.NameNotFoundException){
            versionName = "N/A"
        }
        return versionName
    }

    companion object {
        fun showAbout(fm: android.support.v4.app.FragmentManager){
            val ft = fm.beginTransaction()
            val prev = fm.findFragmentByTag("about_dialog")
            if(prev != null){
                ft.remove(prev)
            }
            ft.addToBackStack(null)
            AboutDialog().show(ft, "about_dialog")
        }
    }

}