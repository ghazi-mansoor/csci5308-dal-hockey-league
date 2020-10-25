package com.groupten.leagueobjectmodel.season;

import java.util.Calendar;

public class Season {
    private Calendar currentDate = Calendar.getInstance();

    public Calendar getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Calendar currentDate) {
        this.currentDate = currentDate;
    }

    public void advanceTime(){
        currentDate.add(Calendar.DATE,1);
    }
}
