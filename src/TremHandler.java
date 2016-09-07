
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class TremHandler implements ActionListener{
	private MidiSetup midiObject;
	
	TremHandler(MidiSetup mO){
		this.midiObject = mO;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JButton){
			JButton jb = (JButton)e.getSource();
		
			if(e.getActionCommand().equals("OFF")){
				midiObject.getMidiChannel()[1].controlChange(92, 127);
				jb.setText("ON");
			}
			else if(e.getActionCommand().equals("ON")){
				midiObject.getMidiChannel()[1].controlChange(92, 0);
				jb.setText("OFF");
			}
		}
		
	}

}

