package abyss.plugin.api.variables.extensions

import abyss.plugin.api.extensions.Extension
import abyss.plugin.api.variables.VariableManager
import kraken.plugin.api.Client
import kraken.plugin.api.Inventory
import java.util.function.Supplier

/**
43188 -> Copper Ore
43190 -> Tin Ore
43192 -> Iron Ore
43194 -> Coal Ore
43196 -> Silver Ore
43198 -> Mithril Ore
43200 -> Adamanite Ore
43202 -> Luminite Ore
43204 -> Gold Ore
43206 -> Runite Ore
43208 -> Orichalcite Ore
43210 -> Drakolith Ore
43212 -> Necrite Ore
43214 -> Phasmatite Ore
43216 -> Banite Ore
43218 -> Light Animica
43220 -> Dark Animica

ID= 44779 Name= Bronze ore box
ID= 44781 Name= Iron ore box
ID= 44783 Name= Steel ore box
ID= 44785 Name= Mithril ore box
ID= 44787 Name= Adamant ore box
ID= 44789 Name= Rune ore box
ID= 44791 Name= Orikalkum ore box
ID= 44793 Name= Necronium ore box
ID= 44795 Name= Bane ore box
ID= 44797 Name= Elder rune ore box
 */

class OreBoxExtension(
    val oreBoxId: Int,
    val copperOre: Int,
    val tinOre: Int,
    val ironOre: Int,
    val coalOre: Int,
    val silverOre: Int,
    val mithrilOre: Int,
    val adamaniteOre: Int,
    val luminiteOre: Int,
    val goldOre: Int,
    val runiteOre: Int,
    val orichalciteOre: Int,
    val drakolithOre: Int,
    val necriteOre: Int,
    val phasmatiteOre: Int,
    val baniteOre: Int,
    val lightAnimicaOre: Int,
    val darkAnimicaOre: Int
) : Extension {

    fun getOreCount(type: Int) : Int {
        return VariableManager.getVarbitById(type)
    }

    fun isFull(type: Int) : Boolean {
        val amount = getOreCount(type)
        val stat = Client.getStatById(Client.MINING)

        if(type == goldOre || type == silverOre) {
            return amount == 100
        }

        val max = if(true) 120 else 140 // find achievement varbit

        when {
            stat.current >= 95 -> {
                return amount == max
            }
            stat.current <= 6 -> {
                return amount == 100
            }
            stat.current >= 18 && type == ironOre -> {
                return amount == max
            }
            stat.current >= 29 && type == coalOre -> {
                return amount == max
            }
            stat.current >= 37 && type == mithrilOre -> {
                return amount == max
            }
            stat.current >= 41 && (type == adamaniteOre || type == luminiteOre) -> {
                return amount == max
            }
            stat.current >= 55 && type == runiteOre -> {
                return amount == max
            }
            stat.current >= 66 && (type == drakolithOre || type == orichalciteOre) -> {
                return amount == max
            }
            stat.current >= 72 && (type == necriteOre || type == phasmatiteOre) -> {
                return amount == max
            }
            stat.current >= 85 && type == baniteOre -> {
                return amount == max
            }
            else -> return amount == 100
        }
    }

    companion object {
        fun init() {
            var count = 44779
            while(count <= 44797) {
                val c = count
                VariableManager.setExt(c) {
                    OreBoxExtension(
                        c,
                        43188,
                        43190,
                        43192,
                        43194,
                        43196,
                        43198,
                        43200,
                        43202,
                        43204,
                        43206,
                        43208,
                        43210,
                        43212,
                        43214,
                        43216,
                        43218,
                        43220
                    )
                }
                count += 2
            }
        }
    }
}