package test
import Advent1
import org.testng.annotations.Test
import kotlin.test.assertEquals
class advent1test {

    @Test
    fun countsIncreaseInList() {
        val adv1 = Advent1()
        val testSubject = adv1.countIncreasingDepthFromList(listOf("1","2","3"))
        assertEquals(2, testSubject)
    }

    @Test
    fun countsIncreaseIgnoresDecreaseOrLevel() {
        val adv1 = Advent1()
        val testSubject = adv1.countIncreasingDepthFromList(listOf("3","1","2", "1","2", "2"))
        assertEquals(2, testSubject)
    }

    @Test
    fun countsIncreaseInThreeSequence() {
        val adv1 = Advent1()
        val testSubject = adv1.countIncreasingSeqDepthFromList(listOf("1","2","3", "4"))
        assertEquals(1, testSubject)
    }
}