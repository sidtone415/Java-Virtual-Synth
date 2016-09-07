import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class ChorusHandler implements ActionListener{
	private MidiSetup midiObject;
	
	ChorusHandler(MidiSetup mO){
		this.midiObject = mO;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JButton){
			JButton jb = (JButton)e.getSource();
		
			if(e.getActionCommand().equals("OFF")){
				midiObject.getMidiChannel()[1].controlChange(93, 127);
				jb.setText("ON");
			}
			else if(e.getActionCommand().equals("ON")){
				midiObject.getMidiChannel()[1].controlChange(93, 0);
				jb.setText("OFF");
			}
		}
		
	}

}


