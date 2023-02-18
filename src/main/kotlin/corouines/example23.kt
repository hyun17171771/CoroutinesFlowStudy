import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.system.measureTimeMillis

suspend fun getRandom1(): Int {
    delay(1000L)
    return Random.nextInt(0, 500)
}

suspend fun getRandom2(): Int {
    delay(1000L)
    return Random.nextInt(0, 500)
}

//23
/*fun main() = runBlocking {
    val elapseTime = measureTimeMillis {
        val value1 = getRandom1()
        val value2 = getRandom2()
        println("$value1 + $value2 = ${value1 + value2}")
    }
    println(elapseTime)
}*/

//24
/**
 * aync 키워드를 이용하면 동시에 다른 블록을 수행할 수 있다. launch와 비슷하게 보이지만 수행 결과를 await 키워드를 통해 받을 수 있다는 차이가 있다
 */
/*
fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        val value1 = async {
            getRandom1()
        }
        val value2 = async {
            getRandom2()
        }
        //await -> job.join() + 결과 가져옴
        println("${value1.await()} + ${value2.await()} = ${value1.await() + value2.await()}") //suspension point
    }
    println(elapsedTime)
}
*/


//25
/**
 * async 키워드를 사용하는 순간 블록이 수행을 준비하는데
 * async(start = CoroutineStart.LAZY)로 인자를 전달하면 우리가 원하는 순간 수행을 준비할 수 있다
 * 이후 start 메서드를 이용해 수행을 준비하게 할 수 있다
 */

fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        val value1 = async(start = CoroutineStart.LAZY) {
            getRandom1()
        }
        val value2 = async(start = CoroutineStart.LAZY) {
            getRandom2()
        }

        value1.start() // 큐에 수행 예약을 한다
        value2.start()

        println("${value1.await()} + ${value2.await()} = ${value1.await() + value2.await()}") //suspension point
    }
    println(elapsedTime)
}