import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NoteHandler implements ActionListener{
	private MidiSetup midiObject;
	private KeyButton key;
	
	NoteHandler(MidiSetup mO){
		this.midiObject = mO;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof KeyButton){
			key = (KeyButton)e.getSource();
		
			//midiObject.getMidiChannel()[0].noteOn(key.getNoteNum(), 127);
			midiObject.getMidiChannel()[1].noteOn(key.getNoteNum(), 127);
			
		}
	}

}
