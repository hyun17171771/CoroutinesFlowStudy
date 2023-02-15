import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random


suspend fun getRandom3(): Int {
    try {
        delay(1000L)
        return Random.nextInt(0, 500)
    } finally {
        println("getRandom3 is cancelled")
    }
}

suspend fun getRandom4(): Int {
        delay(500L)
        throw IllegalStateException()
}

suspend fun doSomething() = coroutineScope {//부모 코루틴 - 캔슬
    val value1 = async { getRandom3() } //자식 코루틴 //문제발생했으니 캔슬하라
    val value2 = async { getRandom4() } //자식 코루틴 //문제 발생
    try {
        println("${value1.await()} + ${value2.await()} = ${value1.await() + value2.await()}")
    } finally {
        println("doSomething is cancelled")
    }
}

suspend fun main() = runBlocking {
    try {
        doSomething()
    } catch (e: IllegalStateException) {
        println("doSomething failed: $e") //자식에 문제가 생기면 부모까지 전파(계층적 구조이기 때문) 때문에 부모에서 예외가 발생함
    }
}