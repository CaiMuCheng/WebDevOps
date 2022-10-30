package com.mucheng.web.devops.support

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mucheng.web.devops.BuildConfig
import com.mucheng.web.devops.data.model.UpdatedInfo
import com.mucheng.web.devops.openapi.util.ContextUtil.openBrowser
import com.mucheng.web.devops.path.OutCacheDir
import com.mucheng.web.devops.ui.activity.MainActivity
import com.mucheng.web.devops.util.AppCoroutine
import com.mucheng.web.devops.util.await
import com.mucheng.web.devops.util.installApk
import com.mucheng.web.devops.util.request
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.FileOutputStream

object Updater {

    private const val UPDATER_URL = "https://sumucheng.mucute.cn/webdev/api/update.json"

    fun updateIfNeeded(activity: MainActivity) {
        AppCoroutine.launch(Dispatchers.IO) {
            runCatching {
                val body = request(UPDATER_URL).await()
                val data = body.string()
                val updateData = parseData(data)
                if (updateData.versionCode <= BuildConfig.VERSION_CODE) {
                    return@launch
                }

                val targetVersionCode = updateData.versionCode
                val sharedPreference =
                    activity.getSharedPreferences("ignoreUpdate", Context.MODE_PRIVATE)
                val ignoreVersionCode = sharedPreference.getInt("versionCode", 1)
                if (ignoreVersionCode < targetVersionCode) {
                    showDialog(activity, updateData)
                }
            }.exceptionOrNull()?.printStackTrace()
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun showDialog(activity: MainActivity, updateData: UpdatedInfo) {
        withContext(Dispatchers.Main) {
            val alertDialog = MaterialAlertDialogBuilder(activity)
                .setTitle("发现新版本 - ${updateData.version}")
                .setMessage(updateData.description)
                .setCancelable(false)
                .setNeutralButton("浏览器更新", null)
                .setPositiveButton("更新", null)
                .setNegativeButton("忽略") { _, _ ->
                    val sharedPreference =
                        activity.getSharedPreferences("ignoreUpdate", Context.MODE_PRIVATE)
                    sharedPreference.edit {
                        putInt("versionCode", updateData.versionCode)
                    }
                }
                .show()
            val button = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL)
            val button2 = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val button3 = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            button.setOnClickListener {
                openBrowser(activity, updateData.updatedUrl)
            }
            var block: (() -> Unit)? = null
            block = {
                button2.setOnClickListener(null)
                button3.setOnClickListener(null)
                button3.text = ""
                AppCoroutine.launch(Dispatchers.IO) {
                    val url = updateData.updatedUrl
                    val body: ResponseBody
                    try {
                        body = request(url).await()
                    } catch (e: Throwable) {
                        withContext(Dispatchers.Main) {
                            Toasty.error(
                                activity,
                                "更新时发生错误: $e, 若多次尝试错误, 请选择浏览器更新"
                            )
                        }
                        button2.setOnClickListener { block?.invoke() }
                        return@launch
                    }
                    val total: Long = body.contentLength()
                    var currentBytes = 0f
                    val inputStream = body.byteStream().buffered()
                    val filePath =
                        "$OutCacheDir/WebDev_${updateData.version}_code${updateData.versionCode}.apk"
                    val outputStream =
                        FileOutputStream(filePath).buffered()
                    val buffer = ByteArray(4096)
                    var len: Int
                    outputStream.use {
                        inputStream.use {
                            while (inputStream.read(buffer).also { len = it } != -1) {
                                outputStream.write(buffer, 0, len)
                                outputStream.flush()
                                currentBytes += len
                                withContext(Dispatchers.Main) {
                                    val progress = (currentBytes / total * 100).toInt()
                                    button2.text = "${progress}%"
                                }
                            }
                        }
                    }
                    withContext(Dispatchers.Main) {
                        activity.installApk(filePath)
                        button2.text = "安装"
                        button2.setOnClickListener {
                            activity.installApk(filePath)
                        }
                    }
                }
            }
            button2.setOnClickListener { block() }
        }
    }

    private suspend fun parseData(json: String): UpdatedInfo {
        val obj = JSONObject(json)
        val version = obj.getString("version")
        val versionCode = obj.getInt("versionCode")
        val descriptionUrl = obj.getString("descriptionUrl")
        val updatedUrl = obj.getString("updatedUrl")
        val description = request(descriptionUrl).await().string()
        return UpdatedInfo(version, versionCode, description, updatedUrl)
    }

}