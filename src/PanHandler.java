import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class PanHandler implements MouseListener, MouseMotionListener {
	MidiSetup midiObject;
	
	PanHandler(MidiSetup mO){
		this.midiObject = mO;
	}

	public void calcPan(int cA){
		if(cA > 1){
			//midiObject.getMidiChannel()[0].controlChange(10, 0);
			midiObject.getMidiChannel()[1].controlChange(10, 127);
		}
		else if(cA < -1){
			//midiObject.getMidiChannel()[0].controlChange(10, 0);
			midiObject.getMidiChannel()[1].controlChange(10, 0);
		}
		else if(cA == 0 || cA == -1 || cA == 1){
			midiObject.getMidiChannel()[1].controlChange(10, 64);
		}
	
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JKnob){
			JKnob jn = (JKnob)e.getSource();
		
			if (jn.pressedOnSpot) {
				jn.updateKnob(e.getPoint());
				calcPan((int) jn.getAngle());
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() instanceof JKnob){
			JKnob jn = (JKnob)arg0.getSource();
		
			Point mouseLoc = arg0.getPoint();
			jn.pressedOnSpot = jn.isOnSpot(mouseLoc);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() instanceof JKnob){
			JKnob jn = (JKnob)arg0.getSource();
		
			jn.pressedOnSpot = false;
		}
	}
}
