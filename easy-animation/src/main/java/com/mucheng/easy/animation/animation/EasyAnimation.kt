@file:Suppress("FunctionName", "UNUSED_PARAMETER")

package com.mucheng.easy.animation.animation

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

internal val map: MutableMap<String, Animator> = HashMap()

suspend inline fun CrossFadeAnimation(
    key: String,
    durations: Long,
    view: View,
    interpolator: Interpolator = LinearInterpolator(),
    crossinline crossScope: () -> Unit = {}
) {
    CrossFadeAnimation(key, durations, durations, view, interpolator, crossScope)
}

suspend inline fun CrossFadeAnimation(
    key: String,
    firstDuration: Long,
    lastDuration: Long,
    view: View,
    interpolator: Interpolator = LinearInterpolator(),
    crossinline crossScope: () -> Unit = {}
) {
    withContext(Dispatchers.Main) {
        CancellableAnimation(
            key, firstDuration, {
                setFloatValues(view.alpha, 0f)
            }, {
                view.alpha = it.animatedValue as Float
            }
        )
        crossScope()
        CancellableAnimation(
            key, lastDuration, {
                setFloatValues(view.alpha, 1f)
            }, {
                view.alpha = it.animatedValue as Float
            }
        )
    }
}

suspend fun CancellableAnimation(
    key: String,
    duration: Long,
    scope: ValueAnimator.() -> Unit,
    animate: (animator: ValueAnimator) -> Unit,
    interpolator: Interpolator = LinearInterpolator()
) {
    withContext(Dispatchers.Main) {
        suspendCoroutine { continuation ->
            var isResumed = AtomicBoolean(false)
            val beforeAnimation = map[key]
            beforeAnimation?.cancel()

            // 放置一个新的动画
            val animator = ValueAnimator()
            animator.duration = duration
            animator.interpolator = interpolator
            animator.scope()
            animator.addUpdateListener {
                if (isActive) {
                    animate(it)
                } else {
                    animator.cancel()
                }
            }
            animator.addListener(object : Animator.AnimatorListener {

                override fun onAnimationStart(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {
                    if (!isResumed.get()) {
                        continuation.resume(Unit)
                        isResumed.set(true)
                    }
                }

                override fun onAnimationCancel(animation: Animator?) {
                    if (!isResumed.get()) {
                        continuation.resumeWithException(CancellationException())
                        isResumed.set(true)
                    }
                }

                override fun onAnimationRepeat(animation: Animator?) {}

            })
            animator.start()
        }
    }
}