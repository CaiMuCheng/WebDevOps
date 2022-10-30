package com.mucheng.annotations.mark

/**
 * 此注解表明修饰对象的操作需要调用 View#invalidate 刷新视图
 * */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
annotation class InvalidateRequired