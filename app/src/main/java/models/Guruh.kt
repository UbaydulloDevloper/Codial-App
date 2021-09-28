package models

class Guruh {
    var id: Int? = null
    var name: String? = null
    var mentor: Mentor? = null
    var date: String? = null
    var kunlar: String? = null
    var kurs_id: Kurs? = null


    constructor(name: String?, mentor: Mentor?, date: String?, kunlar: String?) {
        this.name = name
        this.mentor = mentor
        this.date = date
        this.kunlar = kunlar
    }

    constructor()
    constructor(id: Int?, name: String?, mentor: Mentor?, date: String?, kunlar: String?) {
        this.id = id
        this.name = name
        this.mentor = mentor
        this.date = date
        this.kunlar = kunlar
    }

    constructor(
        id: Int?,
        name: String?,
        mentor: Mentor?,
        date: String?,
        kunlar: String?,
        kurs_id: Kurs?
    ) {
        this.id = id
        this.name = name
        this.mentor = mentor
        this.date = date
        this.kunlar = kunlar
        this.kurs_id = kurs_id
    }
}