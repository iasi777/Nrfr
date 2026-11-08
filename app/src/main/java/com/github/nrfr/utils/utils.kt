package com.github.nrfr.utils

import android.os.BaseBundle
import android.os.Bundle
import android.os.PersistableBundle

fun toPersistableBundle(bundle: Bundle): PersistableBundle {
    val persistableBundle = PersistableBundle()
    for (key in bundle.keySet()) {
        val value = bundle.get(key)
        if (isPersistableBundleType(value)) {
            putIntoBundle(persistableBundle, key, value!!)
        }
    }
    return persistableBundle
}
fun isPersistableBundleType(value: Any?): Boolean =
    (
            (value is PersistableBundle) ||
                    (value is Int) || (value is IntArray) ||
                    (value is Long) || (value is LongArray) ||
                    (value is Double) || (value is DoubleArray) ||
                    (value is String) || (value is Array<*> && value.isArrayOf<String>()) ||
                    (value is Boolean) || (value is BooleanArray)
            )

@Throws(IllegalArgumentException::class)
fun putIntoBundle(
    baseBundle: BaseBundle,
    key: String?,
    value: Any?,
) {
    requireNotNull(value != null) { "Unable to determine type of null values" }
    if (value is Int) {
        baseBundle.putInt(key, value)
    } else if (value is IntArray) {
        baseBundle.putIntArray(key, value)
    } else if (value is Long) {
        baseBundle.putLong(key, value)
    } else if (value is LongArray) {
        baseBundle.putLongArray(key, value)
    } else if (value is Double) {
        baseBundle.putDouble(key, value)
    } else if (value is DoubleArray) {
        baseBundle.putDoubleArray(key, value)
    } else if (value is String) {
        baseBundle.putString(key, value)
    } else if (value is Array<*> && value.isArrayOf<String>()) {
        baseBundle.putStringArray(key, value as Array<String?>)
    } else if (value is Boolean) {
        baseBundle.putBoolean(key, value)
    } else if (value is BooleanArray) {
        baseBundle.putBooleanArray(key, value)
    } else {
        throw IllegalArgumentException(
            ("Objects of type ${value?.javaClass?.simpleName ?: "Unknown"} can not be put into a ${BaseBundle::class.java.simpleName}"),
        )
    }
}