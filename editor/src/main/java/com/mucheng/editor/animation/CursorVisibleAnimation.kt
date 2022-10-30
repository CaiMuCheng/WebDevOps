/*
 * CN:
 * 作者：SuMuCheng
 * 我的 QQ: 3578557729
 * Github 主页：https://github.com/CaiMuCheng
 * 项目主页: https://github.com/CaiMuCheng/MuCodeEditor
 *
 * 你可以免费使用、商用以下代码，也可以基于以下代码做出修改，但是必须在你的项目中标注出处
 * 例如：在你 APP 的设置中添加 “关于编辑器” 一栏，其中标注作者以及此编辑器的 Github 主页
 *
 * 此代码使用 MPL 2.0 开源许可证，你必须标注作者信息
 * 若你要修改文件，请勿删除此注释
 * 若你违反以上条例我们有权向您提起诉讼!
 *
 * EN:
 * Author: SuMuCheng
 * My QQ-Number: 3578557729
 * Github Homepage: https://github.com/CaiMuCheng
 * Project Homepage: https://github.com/CaiMuCheng/MuCodeEditor
 *
 * You can use the following code for free, commercial use, or make modifications based on the following code, but you must mark the source in your project.
 * For example: add an "About Editor" column in your app's settings, which identifies the author and the Github home page of this editor.
 *
 * This code uses the MPL 2.0 open source license, you must mark the author information.
 * Do not delete this comment if you want to modify the file.
 * If you violate the above regulations we have the right to sue you!
 */

package com.mucheng.editor.animation

import com.mucheng.editor.base.animation.CursorVisible
import com.mucheng.editor.event.SelectionChangedEvent
import com.mucheng.editor.view.MuCodeEditor
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@Suppress("LeakingThis")
open class CursorVisibleAnimation(protected val editor: MuCodeEditor) : CursorVisible,
    SelectionChangedEvent {

    private var job: Job? = null

    private var visible = true

    private var duration: Long = 800

    private var lastSelectionChangedTime: Long = 0

    private val baseCoroutine =
        CoroutineScope(Dispatchers.Default + CoroutineName("CursorVisibleAnimationCoroutine"))

    init {
        editor.eventManager.subscribeEvent(SelectionChangedEvent::class.java, this)
        onSelectionChanged()
    }

    override fun isVisible(): Boolean {
        return visible
    }

    override fun isRunning(): Boolean {
        return job?.isActive ?: false
    }

    override fun setDuration(duration: Long) {
        this.duration = duration
    }

    override fun getDuration(): Long {
        return duration
    }

    override fun start() {
        if (!editor.functionManager.isCursorVisibleAnimationEnabled) {
            return
        }

        cancel()
        job = baseCoroutine.launch {
            while (isActive && editor.functionManager.isCursorVisibleAnimationEnabled) {
                if (System.currentTimeMillis() - lastSelectionChangedTime >= duration * 2) {
                    visible = !visible
                    if (!editor.actionManager.selectingText) {
                        editor.postInvalidate()
                    }
                } else {
                    visible = true
                }
                delay(duration)
            }
        }
    }

    override fun cancel() {
        if (job?.isActive == true) {
            job!!.cancel()
        }
        editor.postInvalidate()
    }

    override fun onSelectionChanged() {
        lastSelectionChangedTime = System.currentTimeMillis()
        visible = true
    }

}