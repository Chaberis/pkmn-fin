package melnikov.pkmn.services.impls;
import melnikov.pkmn.dao.StudentDao;
import melnikov.pkmn.entities.StudentEntity;
import melnikov.pkmn.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import melnikov.pkmn.services.StudentService;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    @Override
    public Student getStudentBySurNameAndFirstNameAndFamilyName(String surName, String firstName, String familyName) {
        StudentEntity studentEntity = studentDao.getBySurNameAndFirstNameAndFamilyName(surName, firstName, familyName);
        return Student.fromEntity(studentEntity);
    }

    @Override
    public List<Student> getStudentsByGroup(String group) {
        return studentDao.getByGroup(group).stream().map(Student::fromEntity).toList();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAll().stream().map(Student::fromEntity).toList();
    }

    @Override
    public Student saveStudent(Student student) {
        if (studentDao.studentExists(student)) {
            throw new IllegalArgumentException("Студент уже есть в бд");
        }
        return Student.fromEntity(studentDao.saveStudent(StudentEntity.toEntity(student)));
    }
}