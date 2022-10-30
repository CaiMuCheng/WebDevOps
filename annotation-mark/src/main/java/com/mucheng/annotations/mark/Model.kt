package com.mucheng.annotations.mark

/**
 * 此注解表示修饰的对象为数据存储模型
 * */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD)
annotation class Model