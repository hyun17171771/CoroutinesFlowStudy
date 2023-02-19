package channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//채널은 일종의 파이프이다. 송신측에서 채널에 send로 데이터를 전달하고 수신 측에서 채널을 통해 receive 받는다.
fun main() = runBlocking {
    val channel = Channel<Int>()
    launch {
        for (x in 1..10) {
            channel.send(x) //suspension point 받는 사람이 없으면 잠이듬
        }
    }

    repeat(10) {
        println(channel.receive()) //suspension point 받을 데이터가 없으면 잠이 든다
    }
    println("완료")
}