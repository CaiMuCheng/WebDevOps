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

package com.mucheng.editor.span

import android.util.Log
import com.mucheng.editor.view.MuCodeEditor
import java.util.concurrent.locks.ReentrantReadWriteLock

open class Spans(val editor: MuCodeEditor) {

    private val spanList: MutableList<MutableList<Span>> = ArrayList()

    protected val lock = ReentrantReadWriteLock()

    open fun addLineSpans(line: Int, spans: MutableList<Span>): Spans {
        withLock(writeLock = true) {
            val index = line - 1
            if (index < spanList.size) {
                spanList.add(index, spans)
            } else {
                spanList.add(spans)
            }
        }
        return this
    }

    open fun getLineSpan(line: Int): List<Span> {
        return withLock(writeLock = false) {
            spanList[line - 1]
        }
    }

    open fun getLineSpanInRange(line: Int, startRow: Int, endRow: Int): List<Span> {
        return withLock(writeLock = false) {
            val lineSpan = spanList[line - 1]
            val size = lineSpan.size
            var index = 0
            val spanList: MutableList<Span> = ArrayList(endRow - startRow)
            while (index < size) {
                val span = lineSpan[index]
                Log.e(
                    "ThisSpan", """
                    startRow: ${span.startRow}
                    endRow: ${span.endRow}
                """.trimIndent()
                )
                if (span.startRow >= startRow && span.endRow <= endRow) {
                    spanList.add(span)
                }
                ++index
            }
            spanList
        }
    }

    open fun clear(): Spans {
        withLock(writeLock = true) {
            spanList.clear()
        }
        return this
    }

    fun appendSpan(line: Int, span: Span): Spans {
        withLock(writeLock = true) {
            try {
                spanList[line - 1].add(span)
            } catch (_: IndexOutOfBoundsException) {
            }
        }
        return this
    }

    fun createLineSpans(lineCount: Int): Spans {
        withLock(writeLock = true) {
            var workLine = 1
            while (workLine <= lineCount) {
                spanList.add(ArrayList())
                ++workLine
            }
        }
        return this
    }

    fun isEmpty(): Boolean {
        return withLock(writeLock = false) {
            spanList.isEmpty()
        }
    }

    fun isNotEmpty(): Boolean {
        return withLock(writeLock = false) {
            spanList.isNotEmpty()
        }
    }

    /**
     * 给 block 块加锁
     *
     * @param writeLock 加写锁, 否则加读锁
     * @param block 代码块
     * @return T 目标类型
     * */
    @Suppress("MemberVisibilityCanBePrivate")
    protected inline fun <T> withLock(writeLock: Boolean, block: () -> T): T {
        val currentLock = lock
        if (writeLock) currentLock.writeLock().lock() else currentLock.readLock().lock()
        return try {
            block()
        } finally {
            if (writeLock) currentLock.writeLock().unlock() else currentLock.readLock().unlock()
        }
    }

}