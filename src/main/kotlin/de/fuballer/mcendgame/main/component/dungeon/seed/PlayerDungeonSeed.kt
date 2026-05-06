package de.fuballer.mcendgame.main.component.dungeon.seed

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import de.fuballer.mcendgame.main.component.dungeon.type.DungeonType
import net.minecraft.storage.ReadView
import net.minecraft.storage.WriteView
import kotlin.jvm.optionals.getOrNull

private const val DUNGEON_SEED_NBT = "PlayerDungeonSeed"

data class PlayerDungeonSeed(
    var seed: Long,
    var type: DungeonType,
    var hasBeenUsed: Boolean = false,
) {
    companion object {
        val CODEC: Codec<PlayerDungeonSeed> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.LONG.fieldOf("seed").forGetter(PlayerDungeonSeed::seed),
                Codec.STRING.fieldOf("type").forGetter { it.type.name },
                Codec.BOOL.optionalFieldOf("hasBeenUsed", false).forGetter { it.hasBeenUsed },
            ).apply(instance) { seed, typeName, hasBeenOpened ->
                val type = runCatching { DungeonType.valueOf(typeName) }.getOrDefault(DungeonType.STRONGHOLD)
                PlayerDungeonSeed(seed, type, hasBeenOpened)
            }
        }

        fun write(seed: PlayerDungeonSeed, view: WriteView) {
            view.put(DUNGEON_SEED_NBT, CODEC, seed)
        }

        fun read(view: ReadView): PlayerDungeonSeed? = view.read(DUNGEON_SEED_NBT, CODEC).getOrNull()
    }
}