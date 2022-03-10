package ProblemSet2B.piano;

import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.midi.MidiUnavailableException;

import ProblemSet2B.midi.Instrument;
import ProblemSet2B.midi.Midi;
import ProblemSet2B.music.NoteEvent;
import ProblemSet2B.music.Pitch;

public class PianoMachine {
	
	private Midi midi;
	private HashMap<Integer, Boolean> history = new HashMap<>();
	private Instrument instrument = midi.DEFAULT_INSTRUMENT;
	private int shift = 0;

    private boolean toggle = false;
	private ArrayList<NoteEvent> noteEventArrayList = new ArrayList<>();
    
	/**
	 * constructor for PianoMachine.
	 * 
	 * initialize midi device and any other state that we're storing.
	 */
    public PianoMachine() {
    	try {
            midi = Midi.getInstance();
        } catch (MidiUnavailableException e1) {
            System.err.println("Could not initialize midi device");
            e1.printStackTrace();
            return;
        }
    }
    
    //TODO write method spec
    public void beginNote(Pitch rawPitch) {
        Pitch newPitch = rawPitch.transpose(shift);
        if (history.get(newPitch.toMidiFrequency()) != null) {
            return;
        } else {
            if (toggle) {
                noteEventArrayList.add(
                        new NoteEvent(
                                newPitch, System.currentTimeMillis(), instrument,
                                NoteEvent.Kind.start));
            }
            midi.beginNote(newPitch.toMidiFrequency(), instrument);
            history.put(newPitch.toMidiFrequency(), true);
        }
    	//TODO implement for question 1
    }
    
    //TODO write method spec
    public void endNote(Pitch rawPitch) {
        Pitch newPitch = rawPitch.transpose(shift);
        if (history.get(newPitch.toMidiFrequency()) == null) {
            return;
        } else if (history.get(newPitch.toMidiFrequency()) == true) {
            if (toggle) {
                noteEventArrayList.add(
                        new NoteEvent(
                                newPitch, System.currentTimeMillis(), instrument,
                                NoteEvent.Kind.stop));
            }
            midi.endNote(newPitch.toMidiFrequency(), instrument);
            history.remove(newPitch.toMidiFrequency());
        }
    	//TODO implement for question 1
    }
    
    //TODO write method spec
    public void changeInstrument() {
        instrument = instrument.next();
       	//TODO: implement for question 2
    }
    
    //TODO write method spec
    public void shiftUp() {
        if (shift == 24) {
            return;
        } else {
            shift = shift + Pitch.OCTAVE;
        }
    	//TODO: implement for question 3
    }
    
    //TODO write method spec
    public void shiftDown() {
        if (shift == -24) {
            return;
        } else {
            shift = shift - Pitch.OCTAVE;
        }
    	//TODO: implement for question 3
    }
    
    //TODO write method spec
    public boolean toggleRecording() {
        if (toggle != true) {
            noteEventArrayList.clear();
            toggle = true;
            return true;
        } else {
            toggle = false;
            return false;
        }
    	//TODO: implement for question 4
    }
    
    //TODO write method spec
    public void playback(){
        long previousTime = noteEventArrayList.get(0).getTime();
        for (NoteEvent noteEvent: noteEventArrayList) {
            try {
                Thread.sleep(noteEvent.getTime() - previousTime);
            } catch (InterruptedException e) {
            }
            previousTime = noteEvent.getTime();
            if (noteEvent.getKind() == NoteEvent.Kind.start) {
                midi.beginNote(noteEvent.getPitch().toMidiFrequency(), noteEvent.getInstr());
            } else {
                midi.endNote(noteEvent.getPitch().toMidiFrequency(), noteEvent.getInstr());
            }
        }
        //TODO: implement for question 4
    }
}
