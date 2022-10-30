package com.mucheng.web.devops.config

import com.mucheng.web.devops.base.BaseBean
import com.mucheng.web.devops.path.FilesDir
import com.mucheng.web.devops.path.GlobalConfigFile
import com.mucheng.web.devops.util.AppCoroutine
import com.mucheng.web.devops.util.Context
import com.mucheng.web.devops.util.isSystemInDarkTheme
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

enum class ConfigKeyEnum {
    AppTypefacePath,
    AutoCompletionEnabled,
    OperatorPanelEnabled,
    CursorVisibleAnimationEnabled,
    CursorAnimationEnabled,
    LineNumberEnabled,
    StickyLineNumber,
    DividingLineEnabled,
    CursorAnimationType,
    MeasureType,
    OperatorInputCharTable,
    EditorTypefacePath
}

enum class CursorAnimationType {
    TranslationAnimation,
    ScaleAnimation,
    FadeAnimation
}

enum class MeasureType {
    LineRowVisible,
    LineVisible
}

class GlobalConfig private constructor() : BaseBean() {

    companion object {

        private var instance: GlobalConfig? = null

        fun getInstance(): GlobalConfig {
            if (instance == null) {
                synchronized(GlobalConfig::class.java) {
                    if (instance == null) {
                        instance = GlobalConfig()
                        val props = instance!!.getProperties()
                        runCatching {
                            val input = GlobalConfigFile.inputStream().buffered()
                            input.use {
                                props.loadFromXML(input)
                            }
                        }.exceptionOrNull()?.printStackTrace()
                    }
                }
            }
            return instance!!
        }

        fun isDarkThemeEnabled(): Boolean {
            return try {
                val darkThemeFile = File("$FilesDir/darkTheme.conf")
                val bufferedReader = darkThemeFile.bufferedReader()
                bufferedReader.use {
                    val result =
                        bufferedReader.readLine().toBooleanStrictOrNull() ?: false
                    result
                }
            } catch (e: Throwable) {
                Context.isSystemInDarkTheme()
            }
        }

        fun setDarkThemeEnabled(isEnabled: Boolean) {
            AppCoroutine.launch(CoroutineName("SetDarkThemeConfigCoroutine") + Dispatchers.IO) {
                val e = runCatching {
                    val darkThemeFile = File("$FilesDir/darkTheme.conf")
                    val bufferedWriter = darkThemeFile.bufferedWriter()
                    bufferedWriter.use {
                        bufferedWriter.write(isEnabled.toString())
                        bufferedWriter.flush()
                    }
                }.exceptionOrNull()
                if (e != null) {
                    withContext(Dispatchers.Main) {
                        Toasty.error(Context, "设置启用深色主题失败: ${e.message}").show()
                    }
                }
            }
        }

    }

    fun setAppTypefacePath(path: String?) {
        set(ConfigKeyEnum.AppTypefacePath, path ?: "null")
    }

    fun getAppTypefacePath(): String {
        return get(ConfigKeyEnum.AppTypefacePath) ?: "null"
    }

    fun setAutoCompletionEnabled(isEnabled: Boolean) {
        set(ConfigKeyEnum.AutoCompletionEnabled, isEnabled.toString())
    }

    fun isAutoCompletionEnabled(): Boolean {
        return get(ConfigKeyEnum.AutoCompletionEnabled)?.toBooleanStrictOrNull() ?: true
    }

    fun setOperatorPanelEnabled(isEnabled: Boolean) {
        set(ConfigKeyEnum.OperatorPanelEnabled, isEnabled.toString())
    }

    fun isOperatorPanelEnabled(): Boolean {
        return get(ConfigKeyEnum.OperatorPanelEnabled)?.toBooleanStrictOrNull() ?: true
    }

    fun setCursorVisibleAnimationEnabled(isEnabled: Boolean) {
        set(ConfigKeyEnum.CursorVisibleAnimationEnabled, isEnabled.toString())
    }

    fun isCursorVisibleAnimationEnabled(): Boolean {
        return get(ConfigKeyEnum.CursorVisibleAnimationEnabled)?.toBooleanStrictOrNull() ?: true
    }

    fun setCursorAnimationEnabled(isEnabled: Boolean) {
        set(ConfigKeyEnum.CursorAnimationEnabled, isEnabled.toString())
    }

    fun isCursorAnimationEnabled(): Boolean {
        return get(ConfigKeyEnum.CursorAnimationEnabled)?.toBooleanStrictOrNull() ?: false
    }

    fun setLineNumberEnabled(isEnabled: Boolean) {
        set(ConfigKeyEnum.LineNumberEnabled, isEnabled.toString())
    }

    fun isLineNumberEnabled(): Boolean {
        return get(ConfigKeyEnum.LineNumberEnabled)?.toBooleanStrictOrNull() ?: true
    }

    fun setStickyLineNumberEnabled(isEnabled: Boolean) {
        set(ConfigKeyEnum.StickyLineNumber, isEnabled.toString())
    }

    fun isStickyLineNumberEnabled(): Boolean {
        return get(ConfigKeyEnum.StickyLineNumber)?.toBooleanStrictOrNull() ?: false
    }

    fun setDividingLineEnabled(isEnabled: Boolean) {
        set(ConfigKeyEnum.DividingLineEnabled, isEnabled.toString())
    }

    fun isDividingLineEnabled(): Boolean {
        return get(ConfigKeyEnum.DividingLineEnabled)?.toBooleanStrictOrNull() ?: false
    }

    fun setCursorAnimationType(type: CursorAnimationType) {
        set(ConfigKeyEnum.CursorAnimationType, type.name)
    }

    fun getCursorAnimationType(): CursorAnimationType {
        val type = get(ConfigKeyEnum.CursorAnimationType)
        for (cursorAnimationType in CursorAnimationType.values()) {
            if (cursorAnimationType.name == type) {
                return cursorAnimationType
            }
        }
        return CursorAnimationType.TranslationAnimation
    }

    fun setMeasureType(type: MeasureType) {
        set(ConfigKeyEnum.MeasureType, type.name)
    }

    fun getMeasureType(): MeasureType {
        val type = get(ConfigKeyEnum.MeasureType)
        for (measureType in MeasureType.values()) {
            if (measureType.name == type) {
                return measureType
            }
        }
        return MeasureType.LineRowVisible
    }

    fun setOperatorInputCharTable(input: String) {
        val value = input.trim().replace(" +".toRegex(), "")
        set(ConfigKeyEnum.OperatorInputCharTable, value)
    }

    fun getOperatorInputCharTable(): List<String> {
        val value = get(ConfigKeyEnum.OperatorInputCharTable)
        return value?.split(" ") ?: getOperators()
    }

    fun setEditorTypefacePath(path: String?) {
        set(ConfigKeyEnum.EditorTypefacePath, path ?: "null")
    }

    fun getEditorTypefacePath(): String {
        return get(ConfigKeyEnum.EditorTypefacePath) ?: "null"
    }

    fun apply() {
        AppCoroutine.launch(CoroutineName("SaveGlobalConfigCoroutine") + Dispatchers.IO) {
            val file = GlobalConfigFile
            val properties = getProperties()
            val e = runCatching {
                val output = file.outputStream().buffered()
                properties.storeToXML(output, null, "UTF-8")
            }.exceptionOrNull()
            if (e != null) {
                withContext(Dispatchers.Main) {
                    Toasty.error(Context, "配置保存失败: ${e.message}").show()
                }
            }
        }
    }


    @Suppress("NOTHING_TO_INLINE")
    private inline fun getOperators(): List<String> {
        return listOf(
            "<",
            ">",
            "/",
            "=",
            "\"",
            ":",
            ";",
            "(",
            ")",
            ",",
            ".",
            "$",
            "?",
            "|",
            "\\",
            "&",
            "!",
            "[",
            "]",
            "{",
            "}",
            "_",
            "-"
        )
    }

}