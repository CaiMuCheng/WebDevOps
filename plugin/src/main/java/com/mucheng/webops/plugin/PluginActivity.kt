package com.mucheng.webops.plugin

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.Keep
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.mucheng.webops.plugin.data.Workspace
import kotlinx.coroutines.CoroutineScope

@Keep
abstract class PluginActivity(open val resources: Resources) {

    lateinit var activity: AppCompatActivity
        private set

    lateinit var mainScope: CoroutineScope
        private set

    lateinit var workspace: Workspace
        private set

    val layoutInflater: LayoutInflater
        get() {
            return LayoutInflater.from(activity)
        }

    val intent: Intent
        get() {
            return activity.intent
        }

    open fun onInit(activity: AppCompatActivity, mainScope: CoroutineScope, workspace: Workspace) {
        this.activity = activity
        this.mainScope = mainScope
        this.workspace = workspace
    }

    open fun onCreate(savedInstanceState: Bundle?) {}

    open fun onStart() {}

    open fun onRestart() {}

    open fun onResume() {}

    open fun onPause() {}

    open fun onStop() {}

    open fun onDestroy() {}

    open fun loadLayout(@LayoutRes layoutId: Int): View {
        val layout = resources.getLayout(layoutId)
        return layoutInflater.inflate(layout, null)
    }

    open fun setContentView(@LayoutRes layoutId: Int) {
        setContentView(loadLayout(layoutId))
    }

    open fun setContentView(view: View) {
        activity.setContentView(view)
    }

    open fun <T : View> findViewById(@IdRes id: Int): T {
        return activity.findViewById(id)
    }

    open fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }

    open fun onOptionsItemSelected(item: MenuItem): Boolean {
        return false
    }

}