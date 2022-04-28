package ProblemSet2B.piano;

import javax.sound.midi.MidiUnavailableException;

import ProblemSet2B.midi.Midi;
import ProblemSet2B.music.Pitch;

/**
 * Created by ngaiman_cheung on 17/10/16.
 */
public class TestPiano {
    public static void main(String[] args) {

        try {
            Midi midi;
            PianoMachine pm;
            midi = Midi.getInstance();
            midi.clearHistory();
            pm = new PianoMachine();

//            // First test case
//            pm.beginNote(new Pitch(0));
//            pm.beginNote(new Pitch(0));
//            Midi.rest(50);
//            pm.endNote(new Pitch(0));
//            System.out.println("Test case 1:" + midi.history());
//            midi.clearHistory();
//
//            // Second test case
//            pm.beginNote(new Pitch(0));
//            Midi.rest(50);
//            pm.endNote(new Pitch(2));
//            System.out.println("Test case 2:" + midi.history());
//            midi.clearHistory();

//            // Third test case
//            pm.beginNote(new Pitch(0));
//            pm.beginNote(new Pitch(2));
//            Midi.rest(50);
//            pm.endNote(new Pitch(0));
//            pm.endNote(new Pitch(2));
//            System.out.println("Test case 3:" + midi.history());
//            midi.clearHistory();

//            // Default test case
//            pm.beginNote(new Pitch(0));
//            Midi.rest(50);
//            pm.endNote(new Pitch(0));
//
//            pm.changeInstrument();
//            Midi.rest(10);
//            pm.changeInstrument();
//
//            pm.beginNote(new Pitch(2));
//            Midi.rest(50);
//            pm.endNote(new Pitch(2));
//
//            System.out.println("Default test:" + midi.history());
//            midi.clearHistory();

            // Additional test case
            new TestQ1Hw().test();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }



    }

}