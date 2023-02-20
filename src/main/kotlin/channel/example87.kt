package channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//팬아웃 - 여러 코루틴이 동시에 채널을 구독할 수 있다

fun CoroutineScope.produceNumbers87() = produce<Int> {
    var x = 1
    while (true) {
        send(x++)
        delay(100L)
    }
}

fun CoroutineScope.processNumber(id: Int, channel: ReceiveChannel<Int>) = launch {//런치를 통해 코루틴을 생성하고 그 안에서 채널을 구독함ㅍ
    channel.consumeEach {
        println("${id}가 ${it}을 받았습니다.")
    }
}

fun main() = runBlocking {
    val producer = produceNumbers87()
    repeat(5) {
        processNumber(it, producer)
    }
    delay(1000L)
    producer.cancel()
}