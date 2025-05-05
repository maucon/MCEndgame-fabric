package de.fuballer.mcendgame.main.util.minecraft

import com.mojang.datafixers.util.Either
import com.mojang.serialization.Codec

object CodecUtil {
    fun <T : Enum<T>> ofEnum(enumClass: Class<T>): Codec<T> =
        Codec.STRING.xmap(
            { name -> enumClass.enumConstants.first { it.name == name } },
            { it.name }
        )

    inline fun <A : Any, reified F : A, reified S : A> ofTwo(first: Codec<F>, second: Codec<S>): Codec<A> =
        Codec.either(first, second)
            .xmap(
                { either -> either.map({ it }, { it }) },
                { superInstance ->
                    when (superInstance) {
                        is F -> Either.left(superInstance)
                        is S -> Either.right(superInstance)
                        else -> throw IllegalArgumentException("Unknown subclass of ${superInstance.javaClass}")
                    }
                }
            )

    inline fun <A : Any, reified F : A, reified S : A, reified T : A> ofThree(first: Codec<F>, second: Codec<S>, third: Codec<T>): Codec<A> =
        Codec.either(Codec.either(first, second), third)
            .xmap(
                { either ->
                    either.map(
                        { innerEither -> innerEither.map({ it }, { it }) },
                        { it }
                    )
                },
                { superInstance ->
                    when (superInstance) {
                        is F -> Either.left(Either.left(superInstance))
                        is S -> Either.left(Either.right(superInstance))
                        is T -> Either.right(superInstance)
                        else -> throw IllegalArgumentException("Unknown subclass of ${superInstance.javaClass}")
                    }
                }
            )
}