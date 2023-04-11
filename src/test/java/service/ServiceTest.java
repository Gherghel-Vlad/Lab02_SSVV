package service;

import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.Assert.*;

public class ServiceTest {
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();

    String filenameStudent = "fisiereTest/Studenti.xml";
    String filenameTema = "fisiereTest/Teme.xml";
    String filenameNota = "fisiereTest/Note.xml";
    StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);


    @Test
    public void test_NoTC_1() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1", "newName", 930, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);
        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore + 1);

        service.deleteStudent("id1");

        nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_2() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("", "newName", 930, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (ValidationException e) {

        }

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_3() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student newStudent = new Student("id1", "newName", 930, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        try {
            Student newStudent2 = new Student("id1", "newName", 930, "new@scs.ubbcluj.ro");
            service.addStudent(newStudent2);
        } catch (Exception e) {
        }

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);

        service.deleteStudent("id1");
        int nrofStudents = 0;
        for (Student stud : service.getAllStudenti()) nrofStudents++;

        assertEquals(nrofStudents, 6);
    }

    @Test
    public void test_NoTC_4() {

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1", "", 930, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (Exception e) {
        }
        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_5() {

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("", "newName", 930, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (Exception e) {
        }
        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_6() {

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student(" ", "newName", 930, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (Exception e) {
        }
        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        service.deleteStudent(" ");

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_7() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("abc", "newName", 930, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore + 1);

        service.deleteStudent("abc");

        nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_8() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("124", "newName", 930, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore + 1);

        service.deleteStudent("124");

        nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_9() {

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1", "", 930, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (Exception e) {
        }
        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        service.deleteStudent("id1");

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_10() {

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1", " ", 930, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (Exception e) {
        }
        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        service.deleteStudent("id1");

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_11() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1", "abc", 930, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore + 1);

        service.deleteStudent("id1");

        nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_12() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1", "123", 930, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore + 1);

        service.deleteStudent("id1");

        nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_13() {

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1", "newName", -1, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (Exception e) {
        }
        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        service.deleteStudent("id1");

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_14() {

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1", "newName", -1, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (Exception e) {
        }
        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        service.deleteStudent("id1");

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_15() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1", "newName", 1, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore + 1);

        service.deleteStudent("id1");

        nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_16() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1", "newName", 999999999, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore + 1);

        service.deleteStudent("id1");

        nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_AddAssignment_1(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrOfTemaBefore = 0;
        for (Tema stud : service.getAllTeme()) nrOfTemaBefore++;

        Tema tema = new Tema("1233", "desc", 3, 1);
        service.addTema(tema);

        int nrOfTemeAfter = 0;
        for (Tema stud : service.getAllTeme()) nrOfTemeAfter++;


        assertEquals(nrOfTemeAfter, nrOfTemaBefore + 1);

        service.deleteTema("1233");

        nrOfTemeAfter = 0;
        for (Tema stud : service.getAllTeme()) nrOfTemeAfter++;

        assertEquals(nrOfTemeAfter, nrOfTemaBefore);
    }
    @Test
    public void test_AddAssignment_2(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("", "desc", 3, 1);
        try {
            service.addTema(tema);
            fail();
        }
        catch (Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void test_AddAssignment_TC_1(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("", "descr", 13, 13);
        try {
            service.addTema(tema);
            fail();
        }
        catch (ValidationException e){
            assertTrue(true);
        }
    }

    @Test
    public void test_AddAssignment_TC_2(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema(null, "descr", 13, 13);
        try {
            service.addTema(tema);
            fail();
        }
        catch (ValidationException e){
            assertTrue(true);
        }
    }

    @Test
    public void test_AddAssignment_TC_3(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("100", "", 13, 13);
        try {
            service.addTema(tema);
            fail();
        }
        catch (ValidationException e){
            assertTrue(true);
        }
    }

    @Test
    public void test_AddAssignment_TC_4(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("100", "descr", 0, 13);
        try {
            service.addTema(tema);
            fail();
        }
        catch (ValidationException e){
            assertTrue(true);
        }
    }

    @Test
    public void test_AddAssignment_TC_5(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("100", "descr", 15, 13);
        try {
            service.addTema(tema);
            fail();
        }
        catch (ValidationException e){
            assertTrue(true);
        }
    }

    @Test
    public void test_AddAssignment_TC_6(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("100", "descr", 13, 0);
        try {
            service.addTema(tema);
            fail();
        }
        catch (ValidationException e){
            assertTrue(true);
        }
    }

    @Test
    public void test_AddAssignment_TC_7(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("100", "descr", 13, 15);
        try {
            service.addTema(tema);
            fail();
        }
        catch (ValidationException e){
            assertTrue(true);
        }
    }

    @Test
    public void test_AddAssignment_TC_8(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema("100", "descr", 13, 13);

        service.addTema(tema);

        assert(true);

        service.deleteTema("100");
    }
}
