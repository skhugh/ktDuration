import java.math.BigDecimal
import java.time.Duration

val Number.nanos: Duration
    get() = Duration.ofNanos(toLong())

val Number.micros: Duration
    get() = toDuration(nanosPerUnit = 1000)

val Number.millis: Duration
    get() = toDuration(nanosPerUnit = 1000000)

val Number.seconds: Duration
    get() = when (this) {
        is BigDecimal -> convertSecondsToDuration()
        else -> toString().toBigDecimal().convertSecondsToDuration()
    }

val Number.minutes: Duration
    get() = toDuration(secondsPerUnit = 60)

val Number.hours: Duration
    get() = toDuration(secondsPerUnit = 3600)

val Number.days: Duration
    get() = toDuration(secondsPerUnit = 86400)

private fun BigDecimal.convertSecondsToDuration(): Duration {
    val parts = divideAndRemainder(BigDecimal.ONE)
    val integerPart = parts.first().toLong()
    val decimalPart = parts.last().toDouble()

    return if (decimalPart == 0.0) {
        Duration.ofSeconds(integerPart)
    } else {
        Duration.ofSeconds(
            integerPart,
            (decimalPart * 1e9).toLong()
        )
    }
}

private fun Number.toDuration(
    secondsPerUnit: Int? = null,
    nanosPerUnit: Int? = null
): Duration {
    val multiplier = secondsPerUnit?.toBigDecimal()
        ?: nanosPerUnit?.toBigDecimal()
        ?: throw IllegalStateException("At least one parameter must be non-null.")

    val converted = when (this) {
        is BigDecimal -> this * multiplier
        else -> toString().toBigDecimal() * multiplier
    }

    return when {
        secondsPerUnit != null -> converted.seconds
        else -> converted.nanos
    }
}

operator fun Duration.times(multiplicand: Int): Duration = multipliedBy(multiplicand.toLong())

operator fun Duration.times(multiplicand: Long): Duration = multipliedBy(multiplicand)

operator fun Duration.div(divisor: Int): Duration = dividedBy(divisor.toLong())

operator fun Duration.div(divisor: Long): Duration = dividedBy(divisor)
