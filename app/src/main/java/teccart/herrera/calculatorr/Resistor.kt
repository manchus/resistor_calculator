package teccart.herrera.calculatorr

import android.content.Context
import android.graphics.Color
import android.widget.ListView
import java.math.BigInteger
import java.util.*

class Resistor{
   private var bande1: Int = 0
   private var bande2: Int = 0
   private var bande3: Int = 0
   private var bande4: Int = 0
   private var valeur: String =""
   private lateinit var contexto: Context

    val resColorArrayList: ArrayList<Bande> = ArrayList<Bande>()
    val resToleranceArrayList: ArrayList<Bande> = ArrayList<Bande>()

    companion object {
        private var quantit : Int = 0
        private var totalSerie = BigInteger("0")
        private var totalParallel : Double = 0.0

        fun getQuantite():Int{
            return quantit
        }
        private fun addSerie(r1: String){
            totalSerie += r1.toBigInteger()
        }
        private fun addPalallel(r1: String){
            if(r1.equals("0"))
                totalParallel=0.0
            else
                totalParallel += 1/r1.toDouble()
        }
        fun tSerie():String{
            return totalSerie.toString()+"Ω"
        }
        fun tParallel():String{
            var a : Double = 1/totalParallel
            return a.toInt().toString()+"Ω"
        }
        fun restart(){
            totalParallel=0.0
            totalSerie = BigInteger("0")
        }

    }

    constructor(b1: Int = 0, b2: Int = 0, b3: Int = 0, b4: Int = 0){
        valeur = (b1*10+b2).toString()
        for(i in 1..b3){
            valeur += '0'
        }
        bande1 = b1
        bande2 = b2
        bande3 = b3
        bande4 = b4

        declareBand()
        quantit++
        addSerie(valeur)
        addPalallel(valeur)
    }
    constructor(valeur_: String){
        valeur = valeur_
        if (valeur.length.equals(1)) {
            bande1 = 0
            bande2 = valeur.toInt()
            bande3 = 0
            bande4 = 0
        }
        else
        {
            bande1=valeur.substring(0, 1).toInt()
            bande2=valeur.substring(1, 2).toInt()
            bande3=valeur.length - 2
            bande4=0
        }
        declareBand()
        quantit++
        addSerie(valeur)
        addPalallel(valeur)
    }

    constructor(){
        valeur = "0"
        if (valeur.length.equals(1)) {
            bande1 = 0
            bande2 = valeur.toInt()
            bande3 = 0
            bande4 = 0
        }
        else
        {
            bande1=valeur.substring(0, 1).toInt()
            bande2=valeur.substring(1, 2).toInt()
            bande3=valeur.length - 2
            bande4=0
        }
        declareBand()
        quantit++
        addSerie(valeur)
        addPalallel(valeur)
    }

    fun update(b1: Int = 0, b2: Int = 0, b3: Int = 0, b4: Int = 0){
        valeur = (b1*10+b2).toString()
        for(i in 1..b3){
            valeur += '0'
        }
        bande1 = b1
        bande2 = b2
        bande3 = b3
        bande4 = b4

        declareBand()
        quantit++
        addSerie(valeur)
        addPalallel(valeur)
    }
    fun update(valeur_: String){
        valeur = valeur_
        if (valeur.length.equals(1)) {
            bande1 = 0
            bande2 = valeur.toInt()
            bande3 = 0
            bande4 = 0
        }
        else
        {
            bande1=valeur.substring(0, 1).toInt()
            bande2=valeur.substring(1, 2).toInt()
            bande3=valeur.length - 2
            bande4=0
        }
        declareBand()
        quantit++
        addSerie(valeur)
        addPalallel(valeur)
    }


    fun getB_1():Int
    {
        return bande1
    }
    fun getB_2():Int
    {
        return bande2
    }
    fun getB_3():Int
    {
        return bande3
    }
    fun getB_4():Int
    {
        return bande4
    }
    fun getValeur():String
    {
        return valeur
    }
    fun simplifier():String
    {
        var sResistor: String = ""
        if (valeur.length.equals(1)) {
            return valeur + "Ω"
        }
        else{
            sResistor = valeur.substring(0, 2)
            when(bande3){
                0 -> {
                    sResistor = sResistor + " Ω"
                }
                1 -> {
                    sResistor = sResistor + "0 Ω"
                }
                2 -> {
                    sResistor = sResistor + "00 Ω"
                }
                3 -> {
                    sResistor = sResistor + "k Ω"
                }
                4 -> {
                    sResistor = sResistor + "0k Ω"
                }
                5 -> {
                    sResistor = sResistor + "00k Ω"
                }
                6 -> {
                    sResistor = sResistor + "M Ω"
                }
                7 -> {
                    sResistor = sResistor + "0M Ω"
                }
                8 -> {
                    sResistor = sResistor + "00M Ω"
                }
                9 -> {
                    sResistor = sResistor + "G Ω"
                }
        }

        return sResistor

        }
    }

    fun declareBand(){
        resColorArrayList.add(Bande("black", Color.BLACK, 0))
        resColorArrayList.add(Bande("brown", Color.argb(100, 128, 64, 0), 1))
        resColorArrayList.add(Bande("red", Color.argb(100, 255, 0, 0), 2))
        resColorArrayList.add(Bande("orange", Color.argb(100, 255, 128, 0), 3))
        resColorArrayList.add(Bande("yellow", Color.argb(100, 255, 255, 0), 4))
        resColorArrayList.add(Bande("green", Color.argb(100, 0, 255, 0), 5))
        resColorArrayList.add(Bande("blue", Color.argb(100, 0, 0, 255), 6))
        resColorArrayList.add(Bande("purple", Color.argb(100, 125, 33, 129), 7))
        resColorArrayList.add(Bande("grey", Color.argb(100, 153, 153, 153), 8))
        resColorArrayList.add(Bande("white", Color.argb(100, 255, 255, 255), 9))

        resToleranceArrayList.add(Bande("gold", Color.argb(100, 255, 191, 0), 0))
        resToleranceArrayList.add(Bande("silver", Color.argb(100, 227, 228, 229), 0))

    }

    }



