import java.awt.*;

import javax.swing.JComponent;
import javax.swing.JSlider;
/**
 * JKnob.java - 
 *   A knob component.  The knob can be rotated by dragging 
 *   a spot on the knob around in a circle.
 *   The knob will report its position in radians when asked.
 *   
 *   Edited to fit the needs of the Java Synthesizer Prototype
 *   project by Anthony Harrell
 *
 * @author Grant William Braught
 * @author Dickinson College
 * @version 12/4/2000
 */

public class JKnob extends JComponent{
	//JSlider{
	private static final int knobRadius = 15;
	private static final int spotRadius = 4;
	
	private double theta;
	private Color knobColor;
	private Color spotColor;
	boolean pressedOnSpot;

	JKnob(){
		this(0);
	}
	
	JKnob(double initTheta){
		theta = initTheta;
		pressedOnSpot = false;
		knobColor = Color.gray;
		spotColor = Color.black;
	}
	
	public void paint(Graphics g){
		
		((Graphics2D)g).setRenderingHints(
			     new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
			   );
		// draw the knob
		g.setColor(knobColor);
		g.fillOval(0, 0, 2*knobRadius, 2*knobRadius);
		
		// get position of center spot
		Point pt = getSpotCenter();
		int xCenter = (int)pt.getX();
		int yCenter = (int)pt.getY();
		
		// draw spot
		g.setColor(spotColor);
		g.fillOval(xCenter-spotRadius, yCenter-spotRadius, 2*spotRadius, 2*spotRadius);
	}
	
	private Point getSpotCenter(){
		//spot relative to the knob circle
		int r = knobRadius - spotRadius;
		
		int xCenterPoint = (int)(r*Math.sin(theta));
		int yCenterPoint = (int)(r*Math.cos(theta));
		
		int xCenter = knobRadius + xCenterPoint;
		int yCenter = knobRadius - yCenterPoint;
		
		Point p = new Point(xCenter, yCenter);
		return p;
		
	}
	// get preferred size
	public Dimension getPreferredSize(){
		return new Dimension(2*knobRadius, 2*knobRadius);
	}
	
	// get minimum size
	public Dimension getMinimumSize(){
		return new Dimension(2*knobRadius, 2*knobRadius);
	}
	// get angle
	public double getAngle(){
		return theta;
	}
	
	public int getKnobRadius(){
		return knobRadius;
	}
	
	public int getSpotRadius(){
		return spotRadius;
	}
	
	boolean isOnSpot(Point pt){
		return (pt.distance(getSpotCenter()) < spotRadius);
	}
	
	// update the knob for repainting
	public void updateKnob(Point p){
	    int mx = (int) p.getX();
	    int my = (int) p.getY();

	    // Compute the x, y position of the mouse RELATIVE
	    // to the center of the knob.
	    int mxp = mx - knobRadius;
	    int myp = knobRadius - my;

	    // Compute the new angle of the knob from the
	    // new x and y position of the mouse.  
	    // Math.atan2(...) computes the angle at which
	    // x,y lies from the positive y axis with cw rotations
	    // being positive and ccw being negative.
	    System.out.println("Theta before atan2: " + theta);
	    theta = Math.atan2(mxp, myp);
	    System.out.println("Theta after atan2: " + theta);

	    repaint();
	}
}
