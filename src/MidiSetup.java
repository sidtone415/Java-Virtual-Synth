import java.util.List;
import java.util.Map;

import javax.sound.midi.*;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.ReverbType;
import javax.sound.sampled.SourceDataLine;

import com.sun.media.sound.AudioSynthesizer;
import com.sun.media.sound.AudioSynthesizerPropertyInfo;

/**
 * 
 */

/**
 * @author Anthony Harrell
 *
 */
public class MidiSetup {

	private Synthesizer synth;
	private MidiChannel[] mc;
	Instrument[] instr;
	
	MidiSetup(){
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			mc = synth.getChannels();
			instr = synth.getDefaultSoundbank().getInstruments();
			synth.loadInstrument(instr[0]);
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void setInstrument(int nInstr){
		int p = mc[1].getProgram();
		synth.unloadInstrument(instr[p]);
		synth.loadInstrument(instr[nInstr]);
		mc[1].programChange(nInstr);
		mc[0].programChange(nInstr);
	}
	
	public MidiChannel[] getMidiChannel(){
		return this.mc;
	}

	public Synthesizer getSynth(){
		return this.synth;
	}
	
	public Instrument[] getInstrument(){
		return this.instr;
	}
	
	public String[] getInstrNames(){
		String[] instrArray = new String[this.instr.length];
		
		for(int i=0; i < this.instr.length; i++){
			instrArray[i] = this.instr[i].getName();
		}
		return instrArray;
	}
	
	public void getMidiInfo(){
		Info[] info = MidiSystem.getMidiDeviceInfo();
		try {
			for(int i=0; i < info.length; i++){
				System.out.println(MidiSystem.getMidiDevice(info[i]));
				//System.out.println(info[i]);
			}
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
