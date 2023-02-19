package channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//send나 receive가 suspension point이고 서로에게 의존적이기 때문에 같은 코루틴에서 사용하는 것은 위험할 수 있따.

fun main() = runBlocking<Unit> {
    val channel = Channel<Int>()
    launch {
        for (x in 1..10) {
            channel.send(x) //send를 수행할 때 린치 블록이 잠이 듬 -> 무한대기
        }
        repeat(10) {
            println(channel.receive()) // receive를 수행할 때 런치 블록이 잠이 듬 -> 무한대기
        }
        println("완료")
    }
}