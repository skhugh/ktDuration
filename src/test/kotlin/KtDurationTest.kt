import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.Duration

class KtDurationTest {

    @Test
    fun nanos() {
        assertThat(1.nanos).isEqualTo(Duration.ofNanos(1))
        assertThat(0.234f.nanos).isEqualTo(Duration.ofNanos(0))
        assertThat(1000000.245678.nanos).isEqualTo(Duration.ofNanos(1000000))
    }

    @Test
    fun micros() {
        assertThat(1.micros).isEqualTo(Duration.ofNanos(1000))
        assertThat(0.234f.micros).isEqualTo(Duration.ofNanos(234))
        assertThat(1000000.234567.micros).isEqualTo(Duration.ofNanos(1000000234))
    }

    @Test
    fun millis() {
        assertThat(1.millis).isEqualTo(Duration.ofMillis(1))
        assertThat(0.234f.millis).isEqualTo(Duration.ofNanos(234000))
        assertThat(1000000.234567890.millis).isEqualTo(Duration.ofNanos(1000000234567))
    }

    @Test
    fun seconds() {
        assertThat(1.seconds).isEqualTo(Duration.ofSeconds(1))
        assertThat(0.234f.seconds).isEqualTo(Duration.ofSeconds(0, 234000000))
        assertThat(1000000.23456.seconds).isEqualTo(Duration.ofSeconds(1000000, 234560000))
    }

    @Test
    fun minutes() {
        assertThat(1.minutes).isEqualTo(Duration.ofMinutes(1))
        assertThat(0.234f.minutes).isEqualTo((0.234 * 60).seconds)
        assertThat(1000000.23456.minutes).isEqualTo((1000000.23456 * 60).seconds)
    }

    @Test
    fun hours() {
        assertThat(1.hours).isEqualTo(Duration.ofHours(1))
        assertThat(0.234f.hours).isEqualTo((0.234 * 60).minutes)
        assertThat(1000000.23456.hours).isEqualTo((1000000.23456 * 60).minutes)
    }

    @Test
    fun days() {
        assertThat(1.days).isEqualTo(Duration.ofDays(1))
        assertThat(0.234f.days).isEqualTo((0.234 * 24).hours)
        assertThat(100.23456.days).isEqualTo((100.23456 * 24).hours)
    }

    @Test
    fun multiplyDurations() {
        assertThat(2.5.days * 2).isEqualTo(5.days)
        assertThat(2.5.days * 2L).isEqualTo(5.days)
    }

    @Test
    fun divideDurations() {
        assertThat(5.days / 2).isEqualTo(2.5.days)
        assertThat(5.days / 2L).isEqualTo(2.5.days)
    }
}
