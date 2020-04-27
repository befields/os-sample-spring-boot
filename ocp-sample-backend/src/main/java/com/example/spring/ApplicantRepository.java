package com.example.spring;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    List<Applicant> findByLastName(String lastName);

    Applicant findById(long id);
}
