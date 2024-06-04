package day01

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main(): Unit = runBlocking {
    val time = measureTimeMillis {
        val job1 = async { apiCall1() }
        val job2 = async { apiCall2() }
        printWithThread(job1.await() + job2.await())
    }
    println("소요 시간 : $time ms")
}

suspend fun apiCall1(): Int {
    delay(1_000L)
    return 1
}


suspend fun apiCall2(): Int {
    delay(1_000L)
    return 2
}

fun example5(): Unit = runBlocking {
    val job = async {
        3 + 5
    }
    val eight = job.await()
    printWithThread(eight)
}
fun example4(): Unit = runBlocking {
    val job1 = launch {
        delay(1_000L)
        printWithThread("Job 1")
    }
    job1.join()

    val job2 = launch {
        delay(1_000L)
        printWithThread("Job 2")
    }
}
fun example3(): Unit = runBlocking {
    val job = launch {
        (1..5).forEach {
            printWithThread(it)
            delay(500)
        }
    }

    delay(1_000L)
    job.cancel()
}

fun example2(): Unit = runBlocking {
    val job = launch(start = CoroutineStart.LAZY) {
        printWithThread("Hello launch")
    }

    delay(1_000L)
    job.start()
}

// runBlocking => coroutine builder
fun example1() {
    runBlocking {
        printWithThread("START")
        launch {
            delay(2_000L)
            printWithThread("LAUNCH END")
        }
    }
    printWithThread("END")
}
