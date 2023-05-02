package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.sql.Struct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();

    StudentXMLRepo studentXMLRepository;
    TemaXMLRepo temaXMLRepository;
    NotaValidator notaValidator;
    NotaXMLRepo notaXMLRepository;

    @Before
    public void setup(){

        temaXMLRepository = mock(TemaXMLRepo.class);
        studentXMLRepository = mock(StudentXMLRepo.class);
        notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        notaXMLRepository = mock(NotaXMLRepo.class);

    }

    @Test
    public void test_addStudent(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("1", "nume1", 1, "email1@gmail.com"));
        students.add(new Student("2", "nume2", 1, "email2@gmail.com"));
        students.add(new Student("3", "nume3", 2, "email3@gmail.com"));

        when(studentXMLRepository.findAll()).thenReturn(students);
        when(studentXMLRepository.save(any())).then(value -> {
            students.add(value.getArgument(0, Student.class));
            return value.getArgument(0, Student.class);
        });

        int nrBefore = 0;
        for (Student stud : service.getAllStudenti()) nrBefore++;

        verify(studentXMLRepository).findAll();

        Student tema = new Student("1233", "desc", 3, "asd@gmail.com");
        service.addStudent(tema);

        verify(studentXMLRepository).save(tema);

        int nrAfter = 0;
        for (Student stud : service.getAllStudenti()) nrAfter++;

        verify(studentXMLRepository, times(2)).findAll();

        assertEquals(nrAfter, nrBefore + 1);
    }

    @Test
    public void test_addStudent_AddAssignment(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("1", "nume1", 1, "email1@gmail.com"));
        students.add(new Student("2", "nume2", 1, "email2@gmail.com"));
        students.add(new Student("3", "nume3", 2, "email3@gmail.com"));

        when(studentXMLRepository.findAll()).thenReturn(students);
        when(studentXMLRepository.save(any())).then(value -> {
            students.add(value.getArgument(0, Student.class));
            return value.getArgument(0, Student.class);
        });

        ArrayList<Tema> assignments = new ArrayList<>();
        assignments.add(new Tema("1", "nume1", 1, 1));
        assignments.add(new Tema("2", "nume2", 2, 1));
        assignments.add(new Tema("3", "nume3", 3, 1));

        when(temaXMLRepository.findAll()).thenReturn(assignments);
        when(temaXMLRepository.save(any())).then(value -> {
            assignments.add(value.getArgument(0, Tema.class));
            return value.getArgument(0, Tema.class);
        });

        int nrBefore = 0;
        for (Tema stud : service.getAllTeme()) nrBefore++;

        verify(temaXMLRepository).findAll();

        Tema tema = new Tema("1233", "desc", 3, 1);
        service.addTema(tema);

        verify(temaXMLRepository).save(tema);

        int nrAfter = 0;
        for (Tema stud : service.getAllTeme()) nrAfter++;

        verify(temaXMLRepository, times(2)).findAll();

        assertEquals(nrAfter, nrBefore + 1);


        nrBefore = 0;
        for (Student stud : service.getAllStudenti()) nrBefore++;

        verify(studentXMLRepository).findAll();

        Student student = new Student("1233", "desc", 3, "asd@gmail.com");
        service.addStudent(student);

        verify(studentXMLRepository).save(student);

        nrAfter = 0;
        for (Student stud : service.getAllStudenti()) nrAfter++;

        verify(studentXMLRepository, times(2)).findAll();

        assertEquals(nrAfter, nrBefore + 1);
    }

    @Test
    public void test_addStudent_addAssignment_AddGrade(){
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("1", "nume1", 1, "email1@gmail.com"));
        students.add(new Student("2", "nume2", 1, "email2@gmail.com"));
        students.add(new Student("3", "nume3", 2, "email3@gmail.com"));

        when(studentXMLRepository.findAll()).thenReturn(students);
        when(studentXMLRepository.save(any())).then(value -> {
            students.add(value.getArgument(0, Student.class));
            return value.getArgument(0, Student.class);
        });

        ArrayList<Tema> assignments = new ArrayList<>();
        assignments.add(new Tema("1", "nume1", 1, 1));
        assignments.add(new Tema("2", "nume2", 2, 1));
        assignments.add(new Tema("3", "nume3", 3, 1));

        when(temaXMLRepository.findAll()).thenReturn(assignments);
        when(temaXMLRepository.save(any())).then(value -> {
            assignments.add(value.getArgument(0, Tema.class));
            return value.getArgument(0, Tema.class);
        });

        ArrayList<Nota> notas = new ArrayList<>();
        notas.add(new Nota("1", "1", "1", 5.5, LocalDate.parse("2018-10-18")));
        notas.add(new Nota("2", "2", "2", 6.5, LocalDate.parse("2018-10-18")));
        notas.add(new Nota("3", "3", "3", 3.5, LocalDate.parse("2018-10-18")));

        when(notaXMLRepository.findAll()).thenReturn(notas);
        when(notaXMLRepository.save(any())).then(value -> {
            notas.add(value.getArgument(0, Nota.class));
            return value.getArgument(0, Nota.class);
        });

        int nrBefore = 0;
        for (Student stud : service.getAllStudenti()) nrBefore++;

        verify(studentXMLRepository).findAll();

        Student student = new Student("1233", "desc", 3, "asd@gmail.com");
        service.addStudent(student);

        verify(studentXMLRepository).save(student);

        int nrAfter = 0;
        for (Student stud : service.getAllStudenti()) nrAfter++;

        verify(studentXMLRepository, times(2)).findAll();

        assertEquals(nrAfter, nrBefore + 1);

         nrBefore = 0;
        for (Tema stud : service.getAllTeme()) nrBefore++;

        verify(temaXMLRepository).findAll();

        Tema tema = new Tema("1233", "desc", 3, 1);
        service.addTema(tema);

        verify(temaXMLRepository).save(tema);

         nrAfter = 0;
        for (Tema stud : service.getAllTeme()) nrAfter++;

        verify(temaXMLRepository, times(2)).findAll();

        assertEquals(nrAfter, nrBefore + 1);


        nrBefore = 0;
        for (Nota stud : service.getAllNote()) nrBefore++;

        when(studentXMLRepository.findOne("1233")).thenReturn(
                student
        );
        when(temaXMLRepository.findOne("1233")).thenReturn(
                tema
        );

        verify(notaXMLRepository).findAll();

        Nota nota = new Nota("1233", "1233", "1233", 5, LocalDate.parse("2018-10-18"));
        service.addNota(nota, "Bad");

        verify(notaXMLRepository).save(nota);

        nrAfter = 0;
        for (Nota stud : service.getAllNote()) nrAfter++;

        verify(notaXMLRepository, times(2)).findAll();
        assertEquals(nrAfter, nrBefore + 1);

    }
}
