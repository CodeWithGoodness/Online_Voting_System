package admin;

public class Candidates {
    private String position, party;
    private int numberOfVotes = 0;

    public int getNumberOfVotes() {
        return numberOfVotes;
    }
    public String getPosition() {
        return position;
    }
    public String getParty() {
        return party;
    }
    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
