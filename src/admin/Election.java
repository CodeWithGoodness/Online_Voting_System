package admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Election {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private String electionDate, votingDate2;
    private Date votingDate;
    private Date date = new Date();
    private String currentDate = format.format(date);
    private int difference;

//    public int getDifference() throws ParseException {
//        return difference = getCurrentDate().compareTo(getVotingDate2());
//    }
    public int getDifference() throws ParseException {
        getElectionDate();
        return difference = getDate().getDay()-getVotingDate().getDay();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getElectionDate() {
        return electionDate;
    }

    public void setElectionDate(String electionDate) {
        this.electionDate = electionDate;
    }

    public Date getVotingDate() throws ParseException {
        return votingDate = format.parse(electionDate);
    }

   public void setVotingDate(String electionDate) throws ParseException {
       this.votingDate = format.parse(electionDate);
    }

    public String getVotingDate2() throws ParseException {
        getVotingDate();
        return votingDate2 = format.format(votingDate);
    }

    public void setVotingDate2(Date votingDate) {
        this.votingDate2 = format.format(votingDate);
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String date) {
        currentDate = currentDate;
    }
}
