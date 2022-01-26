package teccart.herrera.calculatorr

class Bande {
    private var couleur: String? = null
    private var codeCouleur: Int
    private var idCouleur = 0

    constructor(couleur: String?, codeCouleur: Int, idCouleur: Int) {
        this.couleur = couleur
        this.codeCouleur = codeCouleur
        this.idCouleur = idCouleur
    }

    fun getCouleur(): String? {
        return couleur
    }

    fun setCouleur(couleur: String?) {
        this.couleur = couleur
    }

    fun getCodeCouleur(): Int {
        return codeCouleur
    }

    fun setCodeCouleur(codeCouleur: Int) {
        this.codeCouleur = codeCouleur
    }

    fun getIdCouleur(): Int {
        return idCouleur
    }

    fun setIdCouleur(idCouleur: Int) {
        this.idCouleur = idCouleur
    }

}