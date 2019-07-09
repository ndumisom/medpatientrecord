package com.medpatientrecord.service;

import com.medpatientrecord.entity.Patient;
import com.medpatientrecord.entity.UserInfo;
import java.util.List;
public interface PatientService {
     List<Patient> getPatientInformation();
     UserInfo login(UserInfo login);
     void captureResult(Patient p);
     List<Patient> viewPatientInformation();
}
