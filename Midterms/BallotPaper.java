package randomstuff;

public class BallotPaper implements Comparable<BallotPaper> {

    private String firstChoice;
    private String secondChoice;

    BallotPaper(String firstChoice, String secondChoice){
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
    }
    public String getFirstChoice() {
        return firstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public void transferVotes() {
        this.firstChoice = this.secondChoice;
        this.secondChoice = null;
    }

    @Override
    public String toString() {
        return "1:" + this.firstChoice + " 2:" + this.secondChoice;
    }

    @Override
    public boolean equals(Object o) {
        // Test if string == null outcome
        // Use .equals
        if (o instanceof BallotPaper) {
            if (((BallotPaper) o).getFirstChoice() == this.firstChoice) {
                if (((BallotPaper) o).getSecondChoice() == this.secondChoice) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public int compareTo(BallotPaper o) {
        return this.firstChoice.compareTo(o.getFirstChoice());
    }
}

