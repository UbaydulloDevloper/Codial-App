package DB

import models.Guruh
import models.Kurs
import models.Mentor
import models.Student

interface DBInterface {

    fun addKurs(kurs: Kurs)
    fun editKurs(kurs: Kurs):Int
    fun deleteKurs(kurs: Kurs)
    fun getAllKurs(): List<Kurs>
    fun getKursById(id: Int): Kurs

    fun addGuruh(guruh: Guruh)
    fun editGuruh(guruh: Guruh):Int
    fun deleteGuruh(guruh: Guruh)
    fun getAllGuruh(): List<Guruh>
    fun getGuruhById(id: Int): Guruh

    fun addMentor(mentor: Mentor)
    fun editMentor(mentor: Mentor):Int
    fun deleteMentor(mentor: Mentor)
    fun getAllMentor(): List<Mentor>
    fun getMentorById(id: Int): Mentor

    fun addStudent(student: Student)
    fun editStudent(student: Student):Int
    fun deleteStudent(student: Student)
    fun getAllStudent(): List<Student>
}