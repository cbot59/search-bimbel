package it.aldi.app.controller;

import org.springframework.stereotype.Component;

@Component
public class Routes {

    /*
     * MVC endpoint
     */
    public static final String HOME = "/home";
    public static final String INDEX = "/";
    public static final String LOGOUT = "/logout";
    public static final String REGISTER = "/register";
    public static final String SEARCH = "/search";
    public static final String SIGNIN = "/signin";
    // TUTOR
    public static final String TUTOR_HOME = "/tutor";
    public static final String TUTOR_SEARCH_JOB = TUTOR_HOME + "/search_job";
    public static final String TUTOR_SEARCH_JOB_DETAILS = TUTOR_SEARCH_JOB + "/details";
    public static final String TUTOR_SCHEDULE = TUTOR_HOME + "/schedule";
    // STUDENT
    public static final String STUDENT_HOME = "/student";
    // OWNER
    public static final String OWNER_HOME = "/owner";
    public static final String OWNER_MANAGE_JOB = OWNER_HOME + "/manage_job";
    public static final String OWNER_MANAGE_JOB_APPLICATION = OWNER_HOME + "/manage_job_application";
    public static final String OWNER_MANAGE_JOB_ADD = OWNER_MANAGE_JOB + "/add";
    public static final String OWNER_MANAGE_STUDENT = OWNER_HOME + "/manage_student";
    public static final String OWNER_MANAGE_SUBJECT = OWNER_HOME + "/manage_subject";
    public static final String OWNER_MANAGE_SUBJECT_ADD = OWNER_MANAGE_SUBJECT + "/add";
    public static final String OWNER_MANAGE_TUTOR = OWNER_HOME + "/manage_tutor";
    public static final String OWNER_UPLOAD_MATERIAL = OWNER_HOME + "/upload_material";

    /*
     * Rest endpoint
     */
    public static final String API = "/api";
    public static final String CITIES = "/cities";
    public static final String JOBS = "/jobs";
    public static final String JOB_APPLICATIONS = "/job_applications";
    public static final String ORGANIZATIONS = "/organizations";
    public static final String PROVINCES = "/provinces";
    public static final String STUDENTS = "/students";
    public static final String SUBJECTS = "/subjects";
    public static final String TUTORS = "/tutors";
    public static final String API_CITIES = API + CITIES;
    public static final String API_ORGANIZATIONS_JOBS = API + ORGANIZATIONS + "/{orgId}" + JOBS;
    public static final String API_ORGANIZATIONS_JOB_APPLICATIONS = API + ORGANIZATIONS + "/{orgId}" + JOB_APPLICATIONS;
    public static final String API_JOBS = API + JOBS;
    public static final String API_PROVINCE = API + PROVINCES;
    public static final String API_STUDENTS = API + STUDENTS;
    public static final String API_SUBJECTS = API + SUBJECTS;
    public static final String API_TUTORS = API + TUTORS;
}
