package admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Election {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private String electionDate;
    private Date votingDate;
    private Date date = new Date();
    private String currentDate = format.format(date);

    public String getElectionDate() {
        return electionDate;
    }

    public void setElectionDate(String electionDate) {
        this.electionDate = electionDate;
    }

    public Date getVotingDate() throws ParseException {
        return votingDate = format.parse(electionDate);
    }

   public void setVotingDate(String electionDate) throws ParseException {;
       this.votingDate = format.parse(electionDate);
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String date) {
        currentDate = currentDate;
    }
}
