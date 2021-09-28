package models

class Mentor {
    var id:Int? = null
    var name: String? = null
    var lastName: String? = null
    var sureName: String? = null
    var kursId:Kurs? = null


    constructor()


    constructor(name: String?, lastName: String?, sureName: String?) {
        this.name = name
        this.lastName = lastName
        this.sureName = sureName
    }


    constructor(id: Int?, name: String?, lastName: String?, sureName: String?) {
        this.id = id
        this.name = name
        this.lastName = lastName
        this.sureName = sureName
    }

    constructor(id: Int?, name: String?, lastName: String?, sureName: String?, kursId: Kurs?) {
        this.id = id
        this.name = name
        this.lastName = lastName
        this.sureName = sureName
        this.kursId = kursId
    }
}