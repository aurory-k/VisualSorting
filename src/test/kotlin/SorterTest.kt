import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import sorter.bubbleSort
import sorter.insertionSort
import sorter.splitList
import sorter.startMergeSort
import java.awt.Color
import kotlin.random.Random.Default.nextDouble

internal class SorterTest {

    private val randomListOfBars = generateRandomList(2000)

    @Before
    fun setUp() {
    }

    @Test
    fun `bubble sort should sort a random list correctly`() {
        val sortedList = randomListOfBars.bubbleSort {}
        assertThat(sortedList).isEqualTo(randomListOfBars.sortedBy { it.height })
    }

    @Test
    fun `merge sort should sort a random list correctly`() {
        val sortedList = randomListOfBars.startMergeSort {}
        assertThat(sortedList).isEqualTo(randomListOfBars.sortedBy { it.height })
    }

    @Test
    fun `insertion sort should sort a random list correctly`() {
        val sortedList = randomListOfBars.insertionSort {}
        assertThat(sortedList).isEqualTo(randomListOfBars.sortedBy { it.height })
    }

    @Test
    fun `split list should return two lists`() {
        val (leftList, rightList) = randomListOfBars.splitList()
        assertThat(leftList.size).isBetween((randomListOfBars.size / 2) - 1, (randomListOfBars.size / 2) + 1)
        assertThat(rightList.size).isBetween((randomListOfBars.size / 2) - 1, (randomListOfBars.size / 2) + 1)
    }

    private fun generateRandomList(size: Int): List<Bar> {
        var randomList = listOf<Bar>()

        (0 until size).forEach { _ ->
            val bar = Bar(
                screenWidth.toDouble() / size.toDouble(), nextDouble(
                    100.0,
                    screenHeight - 50.0
                ),
                Color.BLUE
            )
            randomList = randomList.plus(bar)
        }

        return randomList
    }
}