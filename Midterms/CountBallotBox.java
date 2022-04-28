package randomstuff;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class CountBallotBox {

    private ArrayList<BallotPaper> ballots;

    CountBallotBox() {
        ballots = new ArrayList<>();
    }

    public void addBallot(BallotPaper b) {
        ballots.add(b);
    }

    public int getVotesFor(String candidate) {
        int count = 0;
        for (BallotPaper ballot : ballots) {
            if (ballot.getFirstChoice() == candidate) {
                count = count + 1;
            }
        }
        return count;
    }

    private void sortBallots() {
        Collections.sort(ballots);
    }

    public void eliminateCandidate(String candidate) {
        for (BallotPaper ballot : ballots) {
            if (ballot.getFirstChoice() == candidate) {
                ballot.transferVotes();
            }
        }
    }

    public void transferCandidate (BallotPaper sampleBallotPaper, int numberOfVotes) {
        int num = numberOfVotes;
        for (BallotPaper ballot: ballots) {
            if (num == 0) {
                return;
            }
            if (ballot.equals(sampleBallotPaper)) {
                num = num - 1;
                ballot.transferVotes();
            }
        }
    }

    @Override
    public String toString() {
        sortBallots();
        String sortedString = "";
        for (BallotPaper ballot : ballots) {
            sortedString = sortedString + ballot.toString() + "\n";
        }
        return sortedString;
    }
}

