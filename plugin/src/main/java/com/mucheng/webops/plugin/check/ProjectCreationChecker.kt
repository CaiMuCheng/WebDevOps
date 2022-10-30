package com.mucheng.webops.plugin.check

import android.content.Context
import androidx.annotation.Keep
import es.dmoral.toasty.Toasty
import java.io.File

@Keep
object ProjectCreationChecker {

    fun checkProjectName(context: Context, projectDir: File, projectName: String): Boolean {
        if (projectName.isEmpty()) {
            Toasty.info(context, "工程名称不能为空").show()
            return false
        }

        if (projectName.contains('/')) {
            Toasty.info(context, "不合法的工程名").show()
            return false
        }

        if (File(projectDir, projectName).exists()) {
            Toasty.info(context, "项目已存在").show()
            return false
        }

        return true
    }

}