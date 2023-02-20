package channel

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//공정한 채널 - 두 개의 코루틴에서 채널을 서로 사용할 때 공정하게 기회를 준다

suspend fun someOne(channel: Channel<String>, name: String) {
    for (comment in channel) {
        println("${name}: ${comment}")
        channel.send(comment.drop(1) + comment.first()) //민준 다음 서연 - "스트 캠퍼스" + "패" - "스트 캠퍼스패" 번갈아가면서 실행
        delay(100L)
    }
}

fun main() = runBlocking {
    val channel = Channel<String>()
    launch {
        someOne(channel, "민준")
    }
    launch {
        someOne(channel, "서연")
    }
    channel.send("패스트 캠퍼스")
    delay(1000L)
    coroutineContext.cancelChildren()
}