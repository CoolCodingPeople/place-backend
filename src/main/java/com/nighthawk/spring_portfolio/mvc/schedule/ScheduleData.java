package com.nighthawk.spring_portfolio.mvc.schedule;

public class ScheduleData {
    private String classPeriod1;
    private String classPeriod2;
    private String classPeriod3;
    private String classPeriod4;
    private String classPeriod5;

    // Default constructor
    public ScheduleData() {
    }

    // Constructor with all class periods
    public ScheduleData(String classPeriod1, String classPeriod2, String classPeriod3, String classPeriod4, String classPeriod5) {
        this.classPeriod1 = classPeriod1;
        this.classPeriod2 = classPeriod2;
        this.classPeriod3 = classPeriod3;
        this.classPeriod4 = classPeriod4;
        this.classPeriod5 = classPeriod5;
    }

    public String getClassPeriod1() {
        return classPeriod1;
    }

    public void setClassPeriod1(String classPeriod1) {
        this.classPeriod1 = classPeriod1;
    }

    public String getClassPeriod2() {
        return classPeriod2;
    }

    public void setClassPeriod2(String classPeriod2) {
        this.classPeriod2 = classPeriod2;
    }

    public String getClassPeriod3() {
        return classPeriod3;
    }

    public void setClassPeriod3(String classPeriod3) {
        this.classPeriod3 = classPeriod3;
    }

    public String getClassPeriod4() {
        return classPeriod4;
    }

    public void setClassPeriod4(String classPeriod4) {
        this.classPeriod4 = classPeriod4;
    }

    public String getClassPeriod5() {
        return classPeriod5;
    }

    public void setClassPeriod5(String classPeriod5) {
        this.classPeriod5 = classPeriod5;
    }

    ScheduleData sampleScheduleData = new ScheduleData(
    "Math",
    "Science",
    "History",
    "English",
    "Art"
);
}