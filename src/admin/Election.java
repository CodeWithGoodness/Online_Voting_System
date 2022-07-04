package admin;

import java.util.Date;

public class Election {
    private Date electionDate;
    java.util.Date currentDate = new java.util.Date();
    currentDate = new java.util.Date() ;

    public Date getElectionDate() {
        return electionDate;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public void setElectionDate(Date electionDate) {
        this.electionDate = electionDate;
    }
}
