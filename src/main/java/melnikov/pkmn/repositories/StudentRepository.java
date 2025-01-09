package melnikov.pkmn.repositories;
import org.springframework.stereotype.Repository;
import melnikov.pkmn.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    Optional<StudentEntity> findBySurNameAndFirstNameAndFamilyName(String surName, String firstName, String familyName);
    List<StudentEntity> findByGroup(String group);
    List<StudentEntity> findAll();
    Optional<StudentEntity> findBySurNameAndFirstNameAndFamilyNameAndGroup(String surName, String firstName, String familyName, String group);
}