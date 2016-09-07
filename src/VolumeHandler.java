import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class VolumeHandler implements ChangeListener{
	MidiSetup midiObject;
	
	VolumeHandler(MidiSetup mO){
		this.midiObject = mO;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JSlider){
			JSlider js = (JSlider)e.getSource();
		
			if(js.getValueIsAdjusting()){
				int changeAmount = (int)js.getValue();
				calcVolume(changeAmount);
			}
		}
	}

	public void calcVolume(int cA){
		//midiObject.getMidiChannel()[0].controlChange(7, cA);
		midiObject.getMidiChannel()[1].controlChange(7, cA);
	}
}
