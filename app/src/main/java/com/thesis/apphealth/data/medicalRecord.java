package com.thesis.apphealth.data;

public class medicalRecord {
    public String getMedicalID() {
        return MedicalID;
    }

    public void setMedicalID(String medicalID) {
        MedicalID = medicalID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getSymptomListByPatient() {
        return SymptomListByPatient;
    }

    public void setSymptomListByPatient(String symptomListByPatient) {
        SymptomListByPatient = symptomListByPatient;
    }

    public String getSymptomListAfterExam() {
        return SymptomListAfterExam;
    }

    public void setSymptomListAfterExam(String symptomListAfterExam) {
        SymptomListAfterExam = symptomListAfterExam;
    }

    public String getDiagnosis() {
        return Diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        Diagnosis = diagnosis;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }
    public medicalRecord(String PatientID, String Date,String Time,String SymptomListByPatient,String MedicalHistory,String Drug){
        this.Date=Date;
        this.Time=Time;
        this.SymptomListByPatient=SymptomListByPatient;
        this.MedicalHistory=MedicalHistory;
        this.Drug=Drug;
        this.PatientID=PatientID;
    }
    String MedicalID;
    String Date;

    public medicalRecord(String result) {
        Result = result;
    }
    String Time;
    String SymptomListByPatient;

    public medicalRecord(String medicalID, String date, String time, String symptomListByPatient, String medicalHistory, String testResult, String symptomListAfterExam, String diagnosis, String result,String Drug) {
        MedicalID = medicalID;
        Date = date;
        Time = time;
        SymptomListByPatient = symptomListByPatient;
        MedicalHistory = medicalHistory;
        TestResult = testResult;
        SymptomListAfterExam = symptomListAfterExam;
        Diagnosis = diagnosis;
        Result = result;
        this.Drug=Drug;
    }
    String MedicalHistory;

    public String getMedicalHistory() {
        return MedicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        MedicalHistory = medicalHistory;
    }

    public String getTestResult() {
        return TestResult;
    }

    public void setTestResult(String testResult) {
        this.TestResult = testResult;
    }

    String TestResult;
    String SymptomListAfterExam;
    String Diagnosis;
    String Result;
    String Drug;

    public String getDrug() {
        return Drug;
    }

    public void setDrug(String drug) {
        Drug = drug;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    String PatientID;
}
