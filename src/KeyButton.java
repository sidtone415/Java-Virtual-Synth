import javax.swing.JButton;


public class KeyButton extends JButton{
	/**
	 * 
	 */
	private int midiNote;
	private String noteName;
	
	KeyButton(){
		
	}
	
	KeyButton(int num, String name){
		this.midiNote = num;
		this.noteName = name;
	}
	
	public void setNoteNum(int n){
		this.midiNote = n + 60;
	}
	
	public void setNoteName(String s){
		this.noteName = s;
	}
	
	public int getNoteNum(){
		return this.midiNote;
	}
	
	public String getName(){
		return this.noteName;
	}
}