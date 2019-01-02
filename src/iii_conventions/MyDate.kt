package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this,other);

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : Iterable<MyDate>, ClosedRange<MyDate> {
    override fun iterator(): Iterator<MyDate> = DateIterator(this);

    class DateIterator(val dateRange: DateRange) : Iterator<MyDate>{
        var it = dateRange.start;

        override fun hasNext(): Boolean = it <= dateRange.endInclusive;

        override fun next(): MyDate {
            val it_current = it
            it = it.nextDay()
            return it_current
        }
    }

    override fun contains(d: MyDate): Boolean = start <= d && d <= endInclusive;
}
