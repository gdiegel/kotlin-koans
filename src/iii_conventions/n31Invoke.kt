package iii_conventions

import util.TODO


class Invokable(var invocations: Int = 0) {
    fun getNumberOfInvocations(): Int = invocations
}

operator fun Invokable.invoke(): Invokable {
    invocations++
    return Invokable(invocations)
}

fun todoTask31(): Nothing = TODO(
        """
        Task 31.
        Change the class 'Invokable' to count the number of invocations:
        for 'invokable()()()()' it should be 4.
    """,
        references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
    return invokable()()()().getNumberOfInvocations()
}
