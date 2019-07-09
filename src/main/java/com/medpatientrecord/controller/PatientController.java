package com.medpatientrecord.controller;

import com.medpatientrecord.config.variables.Const;
import com.medpatientrecord.dto.LoginStatus;
import com.medpatientrecord.entity.Patient;
import com.medpatientrecord.entity.UserInfo;
import com.medpatientrecord.service.PateintDaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("usertype")
public class PatientController {
    @Autowired
    PateintDaoService pateintDaoService;

    @PostMapping("/login")
    public ResponseEntity<LoginStatus> login(@RequestBody UserInfo login) {
        UserInfo loginStatus = pateintDaoService.userLogin(login);
        if (loginStatus == null) {
            LoginStatus loginMessage = new LoginStatus();
            loginMessage.setLoginStatusMessage(Const.INVALID_CREDENTIALS);
            return new ResponseEntity(loginMessage, HttpStatus.OK);
        }
        LoginStatus loginMessage = new LoginStatus();
        loginMessage.setLoginStatusMessage(Const.VALID_CREDENTIALS);
        loginMessage.setUserType(loginStatus.getType());
        loginMessage.setUserId(loginStatus.getId());
        return new ResponseEntity(loginMessage, HttpStatus.OK);
    }
        @PostMapping("addpatient")
	public ResponseEntity<Patient> addPatientInformation(@RequestBody Patient patient) {
         Patient patientAdded = pateintDaoService.addPatientInformation(patient);
        if (patientAdded.getCapturedBy() == null) {
            LoginStatus loginMessage = new LoginStatus();
            loginMessage.setLoginStatusMessage(Const.INVALID_CREDENTIALS);
            return new ResponseEntity(loginMessage, HttpStatus.OK);
        }
        return new ResponseEntity(patientAdded, HttpStatus.OK);
        }

    @GetMapping("/viewpatients")
    public ResponseEntity<List<Patient>> viewPatientInformation() {
        List<Patient> list = pateintDaoService.viewPatientInformation();
        if (list.size() > 0) {
            return new ResponseEntity<List<Patient>>(list, HttpStatus.OK);
        } else {
            LoginStatus loginMessage = new LoginStatus();
            loginMessage.setLoginStatusMessage(Const.NO_HIGH_RISK_PATIENT);
            return new ResponseEntity(loginMessage, HttpStatus.OK);
        }
    }
}
