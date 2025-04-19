package de.fuballer.mcendgame.util

import com.mojang.serialization.Codec

object CodecExtension {
    fun <T> Codec<T>.setOf(): Codec<MutableSet<T>> =
        this.listOf().xmap(
            { it.toMutableSet() },
            { it.toList() }
        )
}