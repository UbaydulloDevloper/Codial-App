package DB

object Constant_NAME {
    const val DB_NAME = "data_name"
    const val DB_VERSION = 1

    //tebllarning nomlari
    const val KURS_TABLE = "kurs_table"
    const val KURS_ID = "id"
    const val KURS_NAME = "name"
    const val KURS_ABOUT = "about"


    const val GURUH_TABLE = "guruh_table"
    const val GURUH_ID = "id"
    const val GURUH_NAME = "name"
    const val GURUH_MENTOR_ID = "mentor_id"
    const val GURUH_DATE = "date"
    const val GURUH_DAY = "day"
    const val GURUH_KURS_ID = "guruh_kurs_id"


    const val MENTOR_TABLE = "mentor_table"
    const val MENTOR_ID = "id"
    const val MENTOR_NAME = "name"
    const val MENTOR_LASTNAME = "lastName"
    const val MENTOR_SURE_NAME = "soreName"
    const val MENTOR_KURS_ID = "mentor_kurs_id"


    const val STUDENT_TABLE = "student_table"
    const val STUDENT_ID = "id"
    const val STUDENT_NAME = "name"
    const val STUDENT_LASTNAME = "lastName"
    const val STUDENT_PHONE = "phone"
    const val STUDENT_REG_DATA = "reg_date"
    const val STUDENT_GURUH_ID = "student_gruh_id"


}