import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

/**
 * aync 키워드를 이용하면 동시에 다른 블록을 수행할 수 있다 launch와 비슷하게 보이지만 수행 결과를 await 키워드를 통해 받을 수 있다는 차이점이 있다
 * 결과를 받아야 한다면 async, 결과를 받지 않아도 된다면 launch를 선택할 수 있다
 * awit 키워드를 만나면 async 블록이 수행이 끝났는지 확인하고 아직 끝나지 않았으면 suspend 되었다가 나중에 다시 깨어나고
 * 반환값을 받아온다
 */
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