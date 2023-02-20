package channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//채널 인자로 버퍼의 사이즈를 지정받는다. 지정하지 않으면 버퍼를 생성하지 않는다. 버퍼를 생성하면 중단하지 않는다

fun main() = runBlocking {
    val channel = Channel<Int>(10) //채널의 버퍼 갯수 10
    launch {
        for (x in 1..20) {
            println("${x} 전송중")
            channel.send(x) // 받든 안받든 채널로 계속 보낸다
        }
        channel.close()
    }

    for(x in channel) {
        println("${x} 수신")
        delay(100L)
    }
    println("완료")
}