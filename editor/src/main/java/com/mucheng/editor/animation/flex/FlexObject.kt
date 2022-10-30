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

package com.mucheng.editor.animation.flex

import com.google.android.material.math.MathUtils
import com.mucheng.editor.view.MuCodeEditor
import kotlin.math.abs

/**
 * 一个实验性的动画系统
 * An experimental animation system
 * @author Hamster5295
 * */

abstract class FlexObject(protected val editor: MuCodeEditor, speed: Float) {

    private val leastDiff = 0.01f

    var speed: Float

    init {
        this.speed = speed
    }

    /**
     * 每一次 onDraw() 时都需要调用一次，作为每一帧的更新
     * This method should be called at every onDraw() call
     * */
    open fun update() {}

    /**
     * 工具函数，计算 a 与 b 关于 speed 线性插值
     * Util, used to calculate the linear interpolation between a and b according to speed
     * @param a the beginning value
     * @param b the ending value
     * @return the interpolation between a and b
     * */
    @Suppress("SpellCheckingInspection")
    protected fun lerp(a: Float, b: Float): Float {
        return MathUtils.lerp(a, b, speed)
    }

    /**
     * 工具函数，检查 a 与 b 是否大致相等
     * Util, check if a and b is approximately equal
     * @param a the first Value
     * @param b the second Value
     * @return whether a and b is equal approximately
     * */
    protected fun isEqualApproximately(a:Float,b:Float):Boolean{
        return abs(a - b) <= leastDiff
    }

    /**
     * 工具函数，将 current 取约数为 target
     * Util, assign current as target when they're approximately equal
     * @param current the current Value
     * @param target the target Value
     * @return returns target when current and target is approximately equal
     * and returns current vice versa
     * */
    protected fun round(current: Float, target: Float): Float {
        return if (abs(current - target) <= leastDiff) target else current
    }
}