import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.Instrument;
import javax.swing.JComboBox;


public class InstrumentHandler implements ActionListener{
	private MidiSetup midiObject;
	JComboBox jb;
	
	InstrumentHandler(MidiSetup mO){
		this.midiObject = mO;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JComboBox){
			jb = (JComboBox)e.getSource();
			System.out.println("Combo Box has: " + jb.getSelectedItem());
			midiObject.setInstrument(jb.getSelectedIndex());
		}
	}

}
