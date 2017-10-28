package com.devhacks.bean;

/**
 * Created by Ciprian on 10/28/2017.
 */
public class WeekSprintBean {
    private Integer weekNo;
    private Integer sprintNo;

    public WeekSprintBean(Integer weekNo, Integer sprintNo) {
        this.weekNo = weekNo;
        this.sprintNo = sprintNo;
    }

    public Integer getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(Integer weekNo) {
        this.weekNo = weekNo;
    }

    public Integer getSprintNo() {
        return sprintNo;
    }

    public void setSprintNo(Integer sprintNo) {
        this.sprintNo = sprintNo;
    }
}
