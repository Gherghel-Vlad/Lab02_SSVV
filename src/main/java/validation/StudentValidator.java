package validation;

import domain.Student;

public class StudentValidator implements Validator<Student> {

    /**
     * Valideaza un student
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
    public void validate(Student entity) throws ValidationException {
        if(entity.getID().isBlank()){
            throw new ValidationException("Id incorect!");
        }
        if(entity.getID() == null){
            throw new ValidationException("Id incorect!");
        }
        if(entity.getNume().isBlank()){
            throw new ValidationException("Nume incorect!");
        }
        if(entity.getGrupa() < 0) {
            throw new ValidationException("Grupa incorecta!");
        }
        if(entity.getEmail() == null){
            throw new ValidationException("Email incorect!");
        }
        if(entity.getNume() == null){
            throw new ValidationException("Nume incorect!");
        }
        if(entity.getEmail().isBlank()){
            throw new ValidationException("Email incorect!");
        }
    }
}