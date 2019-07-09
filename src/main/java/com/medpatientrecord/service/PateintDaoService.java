package com.medpatientrecord.service;

import com.medpatientrecord.config.variables.Const;
import com.medpatientrecord.config.variables.UserTypeEnum;
import com.medpatientrecord.entity.Patient;
import com.medpatientrecord.entity.UserInfo;
import com.medpatientrecord.exception.UserNotFoundException;
import com.medpatientrecord.repository.LoginRepository;
import com.medpatientrecord.repository.PatientRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PateintDaoService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private LoginRepository loginRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private Integer authenticatedUserID;
    private static final Logger LOG = Logger.getLogger(PateintDaoService.class.getName());
    public UserInfo userLogin(UserInfo login) {
        LOG.info(Const.USER_LOGIN_IN);
        UserInfo userInfo = loginRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());
        if (userInfo == null) {
            throw new UserNotFoundException(Const.INVALID_CREDENTIALS);
        }
        authenticatedUserID = userInfo.getId();
        LOG.info(Const.VALID_CREDENTIALS);
        return userInfo;
    }
    //This method adds patient information
    public Patient addPatientInformation(Patient patient) {
        if(authenticatedUserID != null){
        UserInfo userInfo = getCurrentUserLoggedIn(authenticatedUserID);
        UserInfo userLog = loginRepository.findOne(userInfo.getId());
        if ((userLog.getType().equalsIgnoreCase(UserTypeEnum.ADMIN.getUserType())) ||(userLog.getType().equalsIgnoreCase(Const.CAPTURE))) {
            // check if all mandatory fields have be filled by the loggedin user 
          boolean madatoryFields = patient.getName() != null && patient.getSurname() != null && patient.getEmail() != null && patient.getHeight() != null && patient.getWeight() != null && patient.getsBloodPressre() != null && patient.getD_BloodPressue() != null;
            if (madatoryFields) {
                patient.setBmi(calculatPatientBMI(patient));
                patient.setCapturedDate(new Date());
                patient.setCapturedBy(userLog.getId());
                patientRepository.save(patient);
                LOG.info(Const.PATIENT_ID + patient.getId()  + Const.INFOMATION_SAVED_BY + userLog.getId());
            } else {
                LOG.warning(Const.FILL_MODATORY_FIELDS);
            }
      }
       return patient;
     } else{
        return new Patient();
      }
}
    //Display Patients information
    public  List<Patient> viewPatientInformation(){      
        List<Patient> patientsList = new ArrayList<Patient>();
        if(authenticatedUserID != null){
        UserInfo userLog =  getCurrentUserLoggedIn(authenticatedUserID);
        userLog = loginRepository.findOne(userLog.getId());
        Double highRiskBMI = Const.HIGH_RISK_BMI;
        Double highRiskSystolicBloodPressure = Const.HIGH_RISK_SYSTOLIC_BLOOD_PRESSURE;
        Double highRiskDiastolicBloodPressure = Const.HIGH_RISK_DIASTOLIC_BLOOD_PRESSURE;
        List<Patient> patients;
        List<Patient> patientsWithLowRiskByCapturer = new  ArrayList<Patient>();
        Patient patient = null;
        if (userLog.getType().equalsIgnoreCase(Const.ADMIN)) {
         patients = patientRepository.findPatients(highRiskBMI, highRiskSystolicBloodPressure, highRiskDiastolicBloodPressure);
         if (patients != null && !patients.isEmpty()){
             LOG.info(patients.size() + Const.PATIENT_FOUND);
             patientsList =  patients;
         }
        } else if (userLog.getType().equalsIgnoreCase(Const.CAPTURE)) {
            patients = patientRepository.findPatients(highRiskBMI, highRiskSystolicBloodPressure, highRiskDiastolicBloodPressure);

             List<Patient> allPatientsIntTheDB = new  ArrayList<Patient>();
             List<Patient> patientListWhenListEmpty = new  ArrayList<Patient>();
             
             allPatientsIntTheDB = patientRepository.findAll();
             Integer patientsSize = patients.size();
             Integer allPatientsIntTheDBSize = allPatientsIntTheDB.size();
             if(patientsSize>0 && allPatientsIntTheDBSize > 0){
             
             for(Patient patientWithHighRisk : patients){
                 for(Patient patien : allPatientsIntTheDB){
                 if(!patientWithHighRisk.getId().equals(patien.getId())){
                         if(userLog.getId().equals(patien.getCapturedBy())){
                            patientsWithLowRiskByCapturer.add(patien);
                          }
                      }
                    }
                }
             patientsList =  patientsWithLowRiskByCapturer;
         } else{
             for(Patient patientsIntTheDB : allPatientsIntTheDB){ 
                
               if(userLog.getId().equals(patientsIntTheDB.getCapturedBy())){
                   
                   patientListWhenListEmpty.add(patientsIntTheDB);
               }
             }
             patientsList =  patientListWhenListEmpty;
             }
        }
     }
         return patientsList;   
}
    private Double calculatPatientBMI(Patient p) {
        Double calculateBMI = (p.getWeight() / p.getHeight()) / p.getHeight();
        return Math.abs(calculateBMI);
    }
    public UserInfo getCurrentUserLoggedIn(Integer userId) {
         UserInfo userLog = entityManager.find(UserInfo.class, userId);
        return  userLog;
    }
    
}
