package com.medpatientrecord.repository;

import com.medpatientrecord.entity.Patient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>  {
    @Query("select p from Patient p where p.bmi > :bmi AND p.sBloodPressre > :sBloodPressre "+ "AND p.d_BloodPressue > :d_BloodPressue")
     List<Patient> findPatients(@Param("bmi") Double bmi, @Param("sBloodPressre") Double sBloodPressre, @Param("d_BloodPressue") Double d_BloodPressue);
    
}
