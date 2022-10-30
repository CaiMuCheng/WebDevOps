package com.mucheng.web.devops.tryeval

import kotlinx.coroutines.CancellationException

/**
 * Created by benny at 2021/12/21 11:54 AM.
 */

@JvmInline
value class TryResult<T>(val value: Any?)

inline fun <R> tryEval(block: () -> R): TryResult<R> {
    return try {
        TryResult(block())
    } catch (e: Throwable) {
        TryResult(e)
    }
}

infix fun <R> TryResult<R>.onCancel(block: (e: CancellationException) -> Unit): TryResult<R> {
    if (value is CancellationException) {
        block(value)
    }
    return this
}

@Suppress("UNCHECKED_CAST")
inline infix fun <reified T : Throwable, R> TryResult<R>.catch(block: (t: T) -> R): R {
    if (value is CancellationException) throw value
    return if (value is Throwable) {
        if (value is T) {
            block(value)
        } else {
            throw value
        }
    } else {
        value as R
    }
}

inline infix fun <reified R> TryResult<R>.catchAll(block: (t: Throwable) -> R): R {
    if (value is CancellationException) throw value
    return if (value is Throwable) {
        block(value)
    } else {
        value as R
    }
}

inline infix fun <reified R> TryResult<R>.catchAllWithUnit(block: (t: Throwable) -> Unit) {
    if (value is CancellationException) throw value
    if (value is Throwable) {
        block(value)
    } else {
        value as R
    }
}

inline infix fun <reified T : Throwable, R> TryResult<R>.catching(block: (t: T) -> R): TryResult<R> {
    return if (value is T) {
        TryResult(block(value))
    } else {
        this
    }
}

inline infix fun <reified R> TryResult<R>.finally(block: () -> Unit): R {
    block()
    if (value is Throwable) throw value
    return value as R
}