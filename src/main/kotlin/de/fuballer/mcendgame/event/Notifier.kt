package de.fuballer.mcendgame.event

import java.util.function.Consumer

class Notifier<T> {
    private val listeners: MutableList<Consumer<T>> = mutableListOf()

    fun listen(listener: Consumer<T>) {
        listeners += listener
    }

    fun interact(value: T) {
        for (listener in listeners) {
            listener.accept(value)
        }
    }
}