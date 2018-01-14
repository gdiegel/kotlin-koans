package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return when {
            this.year > other.year -> 1
            this.year == other.year && (this.month > other.month) -> 1
            this.year == other.year && this.month == other.month && (this.dayOfMonth > other.dayOfMonth) -> 1
            this.year == other.year && this.month == other.month && this.dayOfMonth == other.dayOfMonth -> 0
            else -> -1
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(other: MyDate): Boolean {
        return when {
            listOf(0, 1).contains(other.compareTo(start)) && listOf(0, -1).contains(other.compareTo(endInclusive)) -> true
            else -> false
        }
    }

}
