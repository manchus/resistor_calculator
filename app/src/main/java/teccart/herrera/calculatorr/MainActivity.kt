package teccart.herrera.calculatorr

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private var resQuantite: Int = 2
    private var resNumero: Int = 0
    lateinit var edtValeur: EditText
    lateinit var tvListValues: TextView
    lateinit var tvNumero: TextView
    private var resList: String =""
    val ResistanceTmp =  IntArray(5)
    val ArrayRes = arrayOf(Resistor("0"),Resistor("0"),Resistor("0"),Resistor("0"))
    val resColorArrayList: ArrayList<Bande> = ArrayList<Bande>()
    val resToleranceArrayList: ArrayList<Bande> = ArrayList<Bande>()
    var serieCircuit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val tbType: ToggleButton = findViewById(R.id.tbType)
        val tvQuantite = findViewById<TextView>(R.id.tvQuantite)
        val btnQuantiteMoins = findViewById<Button>(R.id.btnQuatiteMoins)
        val btnQuantitePlus = findViewById<Button>(R.id.btnQuatitePlus)
        val imgType = findViewById<ImageView>(R.id.imgType)

        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val btnNumero = findViewById<Button>(R.id.btnNumero)
        val btnValeur = findViewById<Button>(R.id.btnValeur)
        val btnCalculRt = findViewById<Button>(R.id.btnCalculRt)
        val lvResist1: ListView = findViewById(R.id.lvR1)
        val lvResist2: ListView = findViewById(R.id.lvR2)
        val lvResist3: ListView = findViewById(R.id.lvR3)
        val lvResist4: ListView = findViewById(R.id.lvR4)
        val tvResist1: TextView = findViewById(R.id.tvR1)
        val tvResist2: TextView = findViewById(R.id.tvR2)
        val tvResist3: TextView = findViewById(R.id.tvR3)
        val tvResist4: TextView = findViewById(R.id.tvR4)

        edtValeur = findViewById(R.id.edtValeur) as EditText
        tvListValues = findViewById(R.id.tvListValues) as TextView
        tvNumero = findViewById(R.id.tvNumero) as TextView

        tvNumero.setText("1 / 2")
        tvQuantite.setText("Rt: 2")

        tbType.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                imgType.setImageResource(R.drawable.serie)
                serieCircuit = true
            } else {
                imgType.setImageResource(R.drawable.parallel)
                serieCircuit = false
            }
        }

        btnQuantiteMoins.setOnClickListener {
            if (resQuantite>2)
                {
                    resQuantite--

                }
            resNumero=0
            Resistor.restart()
            btnValeur.setEnabled(true)
            tvListValues.setText("")
            this.edtValeur.setText("Équivalence")
            tvQuantite.setText("Rt: "+resQuantite)
            tvNumero.setText(""+(resNumero+1)+" / "+resQuantite)


            btnValeur.setEnabled(true)
        }
        btnQuantitePlus.setOnClickListener {
            if (resQuantite<20) {
                resQuantite++

            }
            resNumero=0
            Resistor.restart()
            btnValeur.setEnabled(true)
            tvListValues.setText("")
            this.edtValeur.setText("Équivalence")
            tvQuantite.setText("Rt: "+resQuantite)
            tvNumero.setText(""+(resNumero+1)+" / "+resQuantite)

        }
        btnNumero.setOnClickListener {
            tvListValues.setText("")
            ArrayRes[resNumero]!!.update( tvResist1.getText().toString()+
                    tvResist2.getText().toString()+tvResist3.getText().toString())
            if(!edtValeur.getText().equals("") && resQuantite>resNumero)
                resNumero++
            if(resQuantite.equals(resNumero))
                btnValeur.setEnabled(false)
            tvNumero.setText(""+(resNumero+1)+" / "+resQuantite)

            showNumber()
        }
        btnValeur.setOnClickListener {
            tvListValues.setText("")

           ArrayRes[resNumero]!!.update( tvResist1.getText().toString()+
                    tvResist2.getText().toString()+tvResist3.getText().toString())

            showNumber()

            if(!edtValeur.getText().equals("") && resQuantite>resNumero)
                resNumero++
            if(resQuantite.equals(resNumero))
                btnValeur.setEnabled(false)
            else
                tvNumero.setText(""+(resNumero+1)+" / "+resQuantite)

        }
        btnCalculRt.setOnClickListener {
            if(serieCircuit)
                tvTotal.setText(Resistor.tSerie())
            else
                tvTotal.setText(Resistor.tParallel())
        }

        declareBand()

        val adapter = BandeArrayAdapter(this, resColorArrayList)
        val adapterT = BandeArrayAdapter(this, resToleranceArrayList)

        var idColor = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)


        lvResist1.adapter = adapter
        lvResist1.setFooterDividersEnabled(true);
        lvResist2.adapter = adapter
        lvResist2.setFooterDividersEnabled(true);
        lvResist3.adapter = adapter
        lvResist3.setFooterDividersEnabled(true);
        lvResist4.adapter = adapterT
        lvResist4.setFooterDividersEnabled(true);

        lvResist1.setOnItemClickListener { parent, view, position, id ->
            this.ResistanceTmp[0]= idColor[position]
            tvResist1.setText( idColor[position].toString())
            this.itemEquivalence()
         }
        lvResist2.setOnItemClickListener { parent, view, position, id ->
            ResistanceTmp[1] = idColor[position]
            tvResist2.setText( idColor[position].toString())
            this.itemEquivalence()
        }
        lvResist3.setOnItemClickListener { parent, view, position, id ->
            ResistanceTmp[2] = idColor[position]
            var valeur: String = ""
            for(i in 1..position){
                valeur += '0'
            }
            tvResist3.setText( valeur)
            this.itemEquivalence()
        }

        lvResist4.setOnItemClickListener { parent, view, position, id ->
            ResistanceTmp[3] = idColor[position]
            tvResist4.setText( idColor[position].toString())
            this.itemEquivalence()
        }

    }

    fun showNumber(){

        resList=""
        if(resQuantite<resNumero)
            resNumero--
        for(i in 0..resNumero){
            resList += "R[${i+1}]: "+ ArrayRes[i].simplifier()+" "
        }
        tvListValues.setText(resList)
        tvNumero.setText(""+(resNumero+1)+" / "+resQuantite)
    }

    fun itemEquivalence() {
        var total:String = ""
        total = ((this.ResistanceTmp[0]*10)+this.ResistanceTmp[1]).toString()
        for(i in 1..this.ResistanceTmp[2]){
            total += '0'
        }
        this.edtValeur.setText(total)



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

