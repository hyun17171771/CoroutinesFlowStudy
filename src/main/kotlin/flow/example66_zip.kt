package flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

//zip은 양쪽의 데이터를 한꺼번에 묶어 새로운 데이터를 만들어 낸다
fun main() = runBlocking {
    val nums = (1..3).asFlow()
    val strs = flowOf("일", "이", "삼")
    nums.zip(strs) {a, b -> "${a}은(는) $b"}
        .collect { println(it) }
}