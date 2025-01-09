package melnikov.pkmn.repositories;
import org.springframework.stereotype.Repository;
import melnikov.pkmn.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    List<StudentEntity> findByGroup(String group);
    Optional<StudentEntity> findByFirstNameAndSurNameAndFamilyName(String firstName, String surName, String familyName);
}