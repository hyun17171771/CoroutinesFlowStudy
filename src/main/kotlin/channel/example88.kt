package channel

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach

//팬 인은 반대로 생산자가 많은 것이다
suspend fun produceNumbers88(channel: SendChannel<Int>, from: Int, interval: Long) {
    var x = from
    while (true) {
        channel.send(x)
        x += 2
        delay(interval)
    }
}

fun CoroutineScope.processNumbers2(channel: ReceiveChannel<Int>) = launch {
    channel.consumeEach {
        println("${it}을 받았습니다.")
    }
}

fun main() = runBlocking {
    val channel = Channel<Int>() // Channel = Receive Channel (receive) + Send Channel (send)
    //같은 채널을 사용하기 위해 채널을 인자로 전달
    launch {
        produceNumbers88(channel, 1, 100L) // 1, 3, 5, 7, 9
    }
    launch {
        produceNumbers88(channel, 2, 150L) // 2, 4, 6, 8, 10
    } //생산자2 소비자1
    processNumbers2(channel)
    delay(1000L)
    coroutineContext.cancelChildren()
}