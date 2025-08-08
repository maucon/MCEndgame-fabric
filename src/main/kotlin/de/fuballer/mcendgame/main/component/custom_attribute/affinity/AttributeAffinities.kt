package de.fuballer.mcendgame.main.component.custom_attribute.affinity

//B -> Beneficial
//D -> Detrimental
//C -> Conditional
object AttributeAffinities {
    val EMPTY = listOf<AttributeAffinity>()
    val B = listOf(SimpleAttributeAffinity(true))
    val D = listOf(SimpleAttributeAffinity(false))
    val B_BC = listOf(SimpleAttributeAffinity(true), ConditionalAttributeAffinity(true, 0))
    val B_DC = listOf(SimpleAttributeAffinity(true), ConditionalAttributeAffinity(false, 0))
}