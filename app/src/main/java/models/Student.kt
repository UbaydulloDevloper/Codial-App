package models

class Student {
    var id: Int? = null
    var name: String? = null
    var lastname: String? = null
    var phone: Int? = null
    var regDate: String? = null
    var guruhId: Guruh? = null

    constructor(id: Int?, name: String?, lastname: String?, phone: Int?, regDate: String?) {
        this.id = id
        this.name = name
        this.lastname = lastname
        this.phone = phone
        this.regDate = regDate
    }


    constructor()
    constructor(
        id: Int?,
        name: String?,
        lastname: String?,
        phone: Int?,
        regDate: String?,
        guruhId: Guruh?
    ) {
        this.id = id
        this.name = name
        this.lastname = lastname
        this.phone = phone
        this.regDate = regDate
        this.guruhId = guruhId
    }
}