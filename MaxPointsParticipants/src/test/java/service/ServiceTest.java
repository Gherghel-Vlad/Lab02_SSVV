package service;

import domain.Student;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import view.UI;

public class ServiceTest {

    private Service service;
    private StudentXMLRepo studentXMLRepository;
    @Before
    public void before(){
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiereTest/Studenti.xml";
        String filenameTema = "fisiereTest/Teme.xml";
        String filenameNota = "fisiereTest/Note.xml";

        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    public void testAddStudent_IdNull(){
        Student student = new Student(null, "asd", 43, "asd@fdsf.com");
        try{
            service.addStudent(student);
            assert(false);
        }
        catch (Exception e){
            assert(true);
        }
    }

    @Test
    public void testAddStudent_CorrectAdd(){
        Student student = new Student("111", "asd", 43, "asd@fdsf.com");
        service.addStudent(student);

        Student studentFound = studentXMLRepository.findOne("111");

        assert studentFound != null;
    }
}
