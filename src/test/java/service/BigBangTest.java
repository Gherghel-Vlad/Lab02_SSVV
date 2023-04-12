package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class BigBangTest {

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
    public void test_AddAssignment(){
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
    public void test_addStudent(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrBefore = 0;
        for (Student stud : service.getAllStudenti()) nrBefore++;

        Student tema = new Student("1233", "desc", 3, "asd@gmail.com");
        service.addStudent(tema);

        int nrAfter = 0;
        for (Student stud : service.getAllStudenti()) nrAfter++;

        assertEquals(nrAfter, nrBefore + 1);

        service.deleteStudent("1233");

        nrAfter = 0;
        for (Student stud : service.getAllStudenti()) nrAfter++;

        assertEquals(nrAfter, nrBefore);
    }

    @Test
    public void test_addGrade(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrBefore = 0;
        for (Nota stud : service.getAllNote()) nrBefore++;

        Nota tema = new Nota("1233", "1", "1", 5, LocalDate.parse("2018-10-10"));
        service.addNota(tema, "Bad");

        int nrAfter = 0;
        for (Nota stud : service.getAllNote()) nrAfter++;

        assertEquals(nrAfter, nrBefore + 1);

        service.deleteNota("1233");

        nrAfter = 0;
        for (Nota stud : service.getAllNote()) nrAfter++;

        assertEquals(nrAfter, nrBefore);
    }

    @Test
    public void test_addStudent_addAssignment_AddGrade(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrBefore = 0;
        for (Student stud : service.getAllStudenti()) nrBefore++;

        Student tema = new Student("1233", "desc", 3, "asd@gmail.com");
        service.addStudent(tema);

        int nrAfter = 0;
        for (Student stud : service.getAllStudenti()) nrAfter++;

        assertEquals(nrAfter, nrBefore + 1);


        int nrOfTemaBefore = 0;
        for (Tema stud : service.getAllTeme()) nrOfTemaBefore++;

        Tema tema2 = new Tema("1233", "desc", 2, 1);
        service.addTema(tema2);

        int nrOfTemeAfter = 0;
        for (Tema stud : service.getAllTeme()) nrOfTemeAfter++;


        assertEquals(nrOfTemeAfter, nrOfTemaBefore + 1);

        nrBefore = 0;
        for (Nota stud : service.getAllNote()) nrBefore++;

        Nota nota = new Nota("1233", "1233", "1233", 5, LocalDate.parse("2018-10-18"));
        service.addNota(nota, "Bad");

         nrAfter = 0;
        for (Nota stud : service.getAllNote()) nrAfter++;

        assertEquals(nrAfter, nrBefore + 1);


        service.deleteNota("1233");
        service.deleteTema("1233");
        service.deleteStudent("1233");
    }

}
