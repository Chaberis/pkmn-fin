package melnikov.pkmn.services.impls;
import melnikov.pkmn.dao.StudentDao;
import melnikov.pkmn.entities.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import melnikov.pkmn.services.StudentService;
import java.util.List;
import melnikov.pkmn.clients.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    @Autowired
    private final StudentDao studentDao;

    @Autowired
    private RestClient restClient; // Внедрение RestClient

    @Override
    public List<StudentEntity> getAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public StudentEntity getStudentById(UUID id) {
        return studentDao.findById(id).orElse(null);
    }

    @Override
    public StudentEntity saveStudent(StudentEntity student) {
        if (studentDao.findByFullName(student.getFirstName(), student.getSurName(), student.getFatherName()).isPresent()) {
            throw new RuntimeException("Student already exists.");
        }
        return studentDao.save(student);
    }

    @Override
    public StudentEntity updateStudent(UUID id, StudentEntity student) {
        if (!studentDao.findById(id).isPresent()) {
            throw new RuntimeException("Student not found.");
        }
        student.setId(id);
        return studentDao.save(student);
    }

    @Override
    public void deleteStudent(UUID id) {
        studentDao.deleteById(id);
    }

    @Override
    public List<StudentEntity> getStudentsByGroup(String group) {
        return studentDao.findByGroup(group);
    }

    @Override
    public StudentEntity getStudentByFullName(String firstName, String surName, String familyName) {
        return studentDao.findByFullName(firstName, surName, familyName).orElse(null);
    }

}