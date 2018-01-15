package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return when {
            this.year != other.year -> this.year - other.year
            this.year == other.year && this.month != other.month -> this.month - other.month
            this.year == other.year && this.month == other.month -> this.dayOfMonth - other.dayOfMonth
            else -> -1
        }
    }
}

operator fun MyDate.plus(ti: TimeInterval) = this.addTimeIntervals(ti, 1)
operator fun MyDate.plus(rti: RepeatedTimeInterval) = this.addTimeIntervals(rti.ti, rti.n)

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(times: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, times)

data class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return MyDateIterator(DateRange(start, endInclusive))
    }

    operator fun contains(other: MyDate): Boolean {
        return when {
            start <= other && other <= endInclusive -> true
            else -> false
        }
    }
}

class MyDateIterator(val d: DateRange) : Iterator<MyDate> {
    var current = d.start
    override fun next(): MyDate {
        val c = current
        current = c.nextDay()
        return c
    }

    override fun hasNext(): Boolean {
        return current <= d.endInclusive
    }
}
