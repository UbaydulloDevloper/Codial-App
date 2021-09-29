package DB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import models.Guruh
import models.Kurs
import models.Mentor
import models.Student

class MyDBHelper(context: Context) :
    SQLiteOpenHelper(context, Constant_NAME.DB_NAME, null, Constant_NAME.DB_VERSION), DBInterface {
    override fun onCreate(sql: SQLiteDatabase?) {
        sql?.execSQL("create table ${Constant_NAME.GURUH_TABLE}(${Constant_NAME.GURUH_ID} integer not null primary key autoincrement unique,${Constant_NAME.GURUH_NAME} text not null,${Constant_NAME.GURUH_MENTOR_ID} integer not null ,${Constant_NAME.GURUH_DATE} text not null, ${Constant_NAME.GURUH_DAY} text not null, ${Constant_NAME.GURUH_KURS_ID} integer not null,foreign key(${Constant_NAME.GURUH_MENTOR_ID}) references ${Constant_NAME.MENTOR_TABLE}(${Constant_NAME.MENTOR_ID}),foreign key(${Constant_NAME.GURUH_KURS_ID}) references ${Constant_NAME.KURS_TABLE}(${Constant_NAME.KURS_ID}))")
        sql?.execSQL("create table ${Constant_NAME.MENTOR_TABLE}(${Constant_NAME.MENTOR_ID} integer not null primary key autoincrement unique,${Constant_NAME.MENTOR_NAME} text not null,${Constant_NAME.MENTOR_LASTNAME} text not null ,${Constant_NAME.MENTOR_SURE_NAME} text not null, ${Constant_NAME.MENTOR_KURS_ID} integer not null,foreign key(${Constant_NAME.MENTOR_KURS_ID}) references ${Constant_NAME.KURS_TABLE}(${Constant_NAME.KURS_ID}))")
        sql?.execSQL("create table ${Constant_NAME.STUDENT_TABLE}(${Constant_NAME.STUDENT_ID} integer not null primary key autoincrement unique,${Constant_NAME.STUDENT_LASTNAME} text not null,${Constant_NAME.STUDENT_NAME} text not null ,${Constant_NAME.STUDENT_PHONE} text not null, ${Constant_NAME.STUDENT_REG_DATA} text not null,${Constant_NAME.STUDENT_GURUH_ID} integer not null,foreign key(${Constant_NAME.STUDENT_GURUH_ID}) references ${Constant_NAME.GURUH_TABLE}(${Constant_NAME.GURUH_ID}))")
        sql?.execSQL("create table ${Constant_NAME.KURS_TABLE}(${Constant_NAME.KURS_ID} integer not null primary key autoincrement unique,${Constant_NAME.KURS_NAME} text not null,${Constant_NAME.KURS_ABOUT} text not null)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    // interface's
    override fun addKurs(kurs: Kurs) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(Constant_NAME.KURS_NAME, kurs.name)
        contentValue.put(Constant_NAME.KURS_ABOUT, kurs.about)
        database.insert(Constant_NAME.KURS_TABLE, null, contentValue)
        database.close()
    }
    override fun editKurs(kurs: Kurs): Int {
        TODO("Not yet implemented")
    }
    override fun deleteKurs(kurs: Kurs) {
        TODO("Not yet implemented")
    }
    @SuppressLint("Recycle")
    override fun getAllKurs(): List<Kurs> {
        val list = ArrayList<Kurs>()
        val database = this.readableDatabase
        val cursor = database.rawQuery("select * from ${Constant_NAME.KURS_TABLE}", null)
        if (cursor.moveToFirst()) {
            do {
                val kurs = Kurs()
                kurs.id = cursor.getInt(0)
                kurs.name = cursor.getString(1)
                kurs.about = cursor.getString(2)
                list.add(kurs)
            } while (cursor.moveToNext())
        }
        return list
    }
    override fun getKursById(id: Int): Kurs {
        val database = this.readableDatabase
        val cursor = database.query(
            Constant_NAME.KURS_TABLE,
            arrayOf(Constant_NAME.KURS_ID, Constant_NAME.KURS_NAME, Constant_NAME.KURS_ABOUT),
            "${Constant_NAME.KURS_TABLE} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        cursor.moveToFirst()
        return Kurs(cursor.getInt(0), cursor.getString(1), cursor.getString(2))
    }


    override fun addGuruh(guruh: Guruh) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(Constant_NAME.GURUH_NAME, guruh.name)
        contentValue.put(Constant_NAME.GURUH_DATE, guruh.date)
        contentValue.put(Constant_NAME.GURUH_DAY, guruh.kunlar)
        contentValue.put(Constant_NAME.GURUH_MENTOR_ID, guruh.mentor?.id)
        contentValue.put(Constant_NAME.GURUH_KURS_ID, guruh.kurs_id?.id)
        database.insert(Constant_NAME.GURUH_TABLE, null, contentValue)
        database.close()

    }

    override fun editGuruh(guruh: Guruh): Int {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(Constant_NAME.GURUH_ID, guruh.id)
        contentValue.put(Constant_NAME.GURUH_NAME, guruh.name)
        contentValue.put(Constant_NAME.GURUH_DAY, guruh.kunlar)
        contentValue.put(Constant_NAME.GURUH_DATE, guruh.date)
        contentValue.put(Constant_NAME.GURUH_KURS_ID, guruh.kurs_id?.id)
        contentValue.put(Constant_NAME.GURUH_MENTOR_ID, guruh.mentor?.id)
        return database.update(
            Constant_NAME.GURUH_TABLE, contentValue, "${Constant_NAME.GURUH_ID} = ?",
            arrayOf(guruh.id.toString())
        )
    }

    override fun deleteGuruh(guruh: Guruh) {
        val database = this.writableDatabase
        for (student in getAllStudent()) {
            if (student.guruhId?.id == guruh.id) {
                database.delete(
                    Constant_NAME.STUDENT_TABLE,
                    "${Constant_NAME.STUDENT_ID} = ?",
                    arrayOf(student.id.toString())
                )
            }

        }
        database.delete(
            Constant_NAME.GURUH_TABLE,
            "${Constant_NAME.GURUH_ID} = ?",
            arrayOf(guruh.id.toString())
        )
        database.close()
    }

    override fun getAllGuruh(): List<Guruh> {
        val list = ArrayList<Guruh>()
        val database = this.readableDatabase
        val cursor = database.rawQuery("select * from ${Constant_NAME.GURUH_TABLE}", null)
        if (cursor.moveToFirst()) {
            do {
                val guruh = Guruh()
                guruh.id = cursor.getInt(0)
                guruh.name = cursor.getString(1)
                guruh.mentor = getMentorById(cursor.getInt(2))
                guruh.date = cursor.getString(3)
                guruh.kunlar = cursor.getString(4)
                guruh.kurs_id = getKursById(cursor.getInt(5))
                list.add(guruh)
            } while (cursor.moveToNext())
        }

        return list
    }

    override fun getGuruhById(id: Int): Guruh {
        val database = this.readableDatabase
        val cursor = database.query(
            Constant_NAME.GURUH_TABLE,
            arrayOf(
                Constant_NAME.GURUH_ID,
                Constant_NAME.GURUH_NAME,
                Constant_NAME.GURUH_MENTOR_ID,
                Constant_NAME.GURUH_DATE,
                Constant_NAME.GURUH_DAY,
                Constant_NAME.GURUH_KURS_ID
            ),
            "${Constant_NAME.GURUH_TABLE} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        cursor.moveToFirst()
        return Guruh(
            cursor.getInt(0),
            cursor.getString(1),
            getMentorById(cursor.getInt(1)),
            cursor.getString(3),
            cursor.getString(4),
            getKursById(cursor.getInt(5))
        )

    }

    override fun addMentor(mentor: Mentor) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(Constant_NAME.MENTOR_NAME, mentor.name)
        contentValue.put(Constant_NAME.MENTOR_LASTNAME, mentor.lastName)
        contentValue.put(Constant_NAME.MENTOR_SURE_NAME, mentor.sureName)
        contentValue.put(Constant_NAME.MENTOR_KURS_ID, mentor.kursId?.id)
        database.insert(Constant_NAME.MENTOR_TABLE, null, contentValue)
        database.close()
    }

    override fun editMentor(mentor: Mentor): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant_NAME.MENTOR_ID, mentor.id)
        contentValues.put(Constant_NAME.MENTOR_LASTNAME, mentor.lastName)
        contentValues.put(Constant_NAME.MENTOR_NAME, mentor.name)
        contentValues.put(Constant_NAME.MENTOR_SURE_NAME, mentor.sureName)
        contentValues.put(Constant_NAME.MENTOR_KURS_ID, mentor.kursId?.id)

        return database.update(
            Constant_NAME.MENTOR_TABLE, contentValues, "${Constant_NAME.MENTOR_ID}=?",
            arrayOf(mentor.id.toString())
        )
    }

    override fun deleteMentor(mentor: Mentor) {
        val database = this.writableDatabase
        for (guruh in getAllGuruh()) {
            if (guruh.mentor?.id == mentor.id) {
                for (student in getAllStudent()) {
                    if (student.guruhId?.id == guruh.id) {
                        database.delete(
                            Constant_NAME.STUDENT_TABLE,
                            "${Constant_NAME.STUDENT_ID} = ?",
                            arrayOf(student.id.toString())
                        )
                    }

                }
                database.delete(
                    Constant_NAME.GURUH_TABLE,
                    "${Constant_NAME.GURUH_ID} = ?",
                    arrayOf(guruh.id.toString())
                )
            }
        }
        database.delete(
            Constant_NAME.MENTOR_TABLE,
            "${Constant_NAME.MENTOR_ID} = ?",
            arrayOf(mentor.id.toString())
        )
        database.close()
    }

    override fun getAllMentor(): List<Mentor> {
        val list = ArrayList<Mentor>()
        val database = this.readableDatabase
        val cursor = database.rawQuery("select * from ${Constant_NAME.MENTOR_TABLE}", null)
        if (cursor.moveToFirst()) {
            do {
                val metor = Mentor()
                metor.id = cursor.getInt(0)
                metor.name = cursor.getString(1)
                metor.lastName = cursor.getString(2)
                metor.sureName = cursor.getString(3)
                metor.kursId = getKursById(cursor.getInt(4))
                list.add(metor)
            } while (cursor.moveToNext())
        }

        return list
    }

    override fun getMentorById(id: Int): Mentor {
        val database = this.readableDatabase
        val cursor = database.query(
            Constant_NAME.MENTOR_TABLE,
            arrayOf(
                Constant_NAME.MENTOR_ID,
                Constant_NAME.MENTOR_NAME,
                Constant_NAME.MENTOR_LASTNAME,
                Constant_NAME.MENTOR_SURE_NAME,
                Constant_NAME.MENTOR_KURS_ID
            ),
            "${Constant_NAME.MENTOR_TABLE} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        cursor.moveToFirst()
        return Mentor(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            getKursById(cursor.getInt(4))
        )
    }

    override fun addStudent(student: Student) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(Constant_NAME.STUDENT_NAME, student.name)
        contentValue.put(Constant_NAME.STUDENT_LASTNAME, student.lastname)
        contentValue.put(Constant_NAME.STUDENT_PHONE, student.phone)
        contentValue.put(Constant_NAME.STUDENT_REG_DATA, student.regDate)
        contentValue.put(Constant_NAME.STUDENT_GURUH_ID, student.guruhId?.id)
        database.insert(Constant_NAME.STUDENT_TABLE, null, contentValue)
        database.close()
    }

    override fun editStudent(student: Student): Int {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(Constant_NAME.STUDENT_ID, student.id)
        contentValue.put(Constant_NAME.STUDENT_PHONE, student.phone)
        contentValue.put(Constant_NAME.STUDENT_LASTNAME, student.lastname)
        contentValue.put(Constant_NAME.STUDENT_NAME, student.name)
        contentValue.put(Constant_NAME.STUDENT_GURUH_ID, student.guruhId?.id)
        contentValue.put(Constant_NAME.STUDENT_REG_DATA, student.regDate)
        contentValue.put(Constant_NAME.STUDENT_ID, student.id)

        return database.update(
            Constant_NAME.STUDENT_TABLE, contentValue, "${Constant_NAME.STUDENT_ID} = ?",
            arrayOf(student.id.toString())
        )
    }

    override fun deleteStudent(student: Student) {
        val database = this.writableDatabase
        database.delete(
            Constant_NAME.STUDENT_TABLE,
            "${Constant_NAME.STUDENT_ID} = ?",
            arrayOf(student.id.toString())
        )
        database.close()
    }

    override fun getAllStudent(): List<Student> {
        val list = ArrayList<Student>()
        val database = this.readableDatabase
        val cursor = database.rawQuery("select * from ${Constant_NAME.STUDENT_TABLE}", null)
        if (cursor.moveToFirst()) {
            do {
                val student = Student()
                student.id = cursor.getInt(0)
                student.lastname = cursor.getString(1)
                student.name = cursor.getString(2)
                student.phone = cursor.getInt(3)
                student.regDate = cursor.getString(4)
                student.guruhId = getGuruhById(cursor.getInt(5))
                list.add(student)
            } while (cursor.moveToNext())
        }
        return list
    }
}