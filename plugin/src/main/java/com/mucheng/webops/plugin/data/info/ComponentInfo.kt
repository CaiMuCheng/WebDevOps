package com.mucheng.webops.plugin.data.info

sealed class ComponentInfo {

    data class TitleInfo(var title: String) : ComponentInfo()

    data class InputInfo(
        var title: String?,
        var hint: String?,
        var isSingleLine: Boolean
    ) : ComponentInfo()

    data class SelectorInfo(
        var items: Array<String>,
        var position: Int = 0
    ) : ComponentInfo() {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as SelectorInfo

            if (!items.contentEquals(other.items)) return false
            if (position != other.position) return false

            return true
        }

        override fun hashCode(): Int {
            var result = items.contentHashCode()
            result = 31 * result + position
            return result
        }

    }

}