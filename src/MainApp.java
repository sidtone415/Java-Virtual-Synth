import java.awt.*;

import javax.swing.*;


public class MainApp extends JFrame{
	private MidiSetup midiObj;
	private NoteHandler noteHand;
	private VolumeHandler volHand;
	private InstrumentHandler instrHand;
	private PanHandler panHand;
	private VerbHandler verbHand;
	private TremHandler tremHand;
	private ChorusHandler chorHand;
	
	public MainApp(){
		midiObj = new MidiSetup();
		noteHand = new NoteHandler(midiObj);
		instrHand = new InstrumentHandler(midiObj);
		volHand = new VolumeHandler(midiObj);
		panHand = new PanHandler(midiObj);
		verbHand = new VerbHandler(midiObj);
		tremHand = new TremHandler(midiObj);
		chorHand = new ChorusHandler(midiObj);
		initUI();
	}
	
	private void initUI(){
		
		// local variables
		Color c1 = new Color(102,102,102);
		Color c2 = new Color(45,45,48);
		
		// panel for knobs and sliders
		JPanel knobPanel = new JPanel();
		knobPanel.setPreferredSize(new Dimension(650,50));
		knobPanel.setBackground(c2);
		makeKnobPanel(knobPanel);
		
		// pane for keyboard
		JLayeredPane keyPane = new JLayeredPane();
		keyPane.setPreferredSize(new Dimension(650,200));
		keyPane.setBackground(c1);	
		keyPane.setOpaque(true);
		makeKeyboard(keyPane);
		
		
		createLayout(knobPanel, keyPane);
		
		// main JFrame window
		setTitle("Vitrual / Synth Prototype");
		setSize(810, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void createLayout(JPanel jp, JLayeredPane jlp ){//JPanel... args){
		
		// Create Containers
		Container paneKnob = getContentPane();
		Container paneKey = getContentPane();
		
		// Create Layout types
		BoxLayout b1 = new BoxLayout(paneKnob, BoxLayout.PAGE_AXIS);
		
		// Set containers to specific layouts
		paneKnob.setLayout(b1);
	
		// add panels to containers
		paneKnob.add(jp);
		paneKnob.add(Box.createRigidArea(new Dimension(0,5)));
		
		paneKey.add(jlp);
		paneKey.add(Box.createRigidArea(new Dimension(5,0)));
	
	}
	
	private void makeKeyboard(JLayeredPane jlp){
		// variables
		Point orgin = new Point(0,0);
		KeyButton[] kArray = new KeyButton[24];
		
		for(int i=0; i < kArray.length; i++)
		{
			if(i==1||i==3||i==6||i==8||i==10||i==13||i==15||i==18||i==20||i==22)
			{
				kArray[i] = new KeyButton();
				kArray[i].setNoteNum(i);
				kArray[i].addActionListener(noteHand);
				kArray[i].setPreferredSize(new Dimension(25, 115));
				kArray[i].setBackground(Color.BLACK);
				kArray[i].setBounds((orgin.x + 35),orgin.y,25,115);
				jlp.add(kArray[i], new Integer(10));
			}
			else
			{
				kArray[i] = new KeyButton();
				kArray[i].setNoteNum(i);
				kArray[i].addActionListener(noteHand);
				kArray[i].setPreferredSize(new Dimension(50, 225));
				kArray[i].setBackground(Color.WHITE);
				kArray[i].setBounds((orgin.x += 50), orgin.y, 50, 225);
				jlp.add(kArray[i], new Integer(0));
				
			}
		}
	}
	
	private void makeKnobPanel(JPanel jp){
		// variables
		Color b1 = new Color(102,102,102);
		Color b2 = new Color(45,45,48);
		String[] instrument = midiObj.getInstrNames();
		//midiObj.getMidiInfo();
		
		JComboBox jb = new JComboBox(instrument);
		JSlider js = new JSlider();
		JKnob jn = new JKnob(0);
		JButton jVerbBut = new JButton();
		JButton jTremBut = new JButton();
		JButton jChorBut = new JButton();
		JLabel jl1 = new JLabel();
		JLabel jl2 = new JLabel();
		JLabel jv1 = new JLabel();
		JLabel jt1 = new JLabel();
		JLabel jc1 = new JLabel();
		JLabel jp1 = new JLabel();
		
		JPanel ip1 = new JPanel();
		ip1.setBackground(b2);
		JPanel ip2 = new JPanel(new BorderLayout());
		ip2.setBackground(b2);
		JPanel ip3 = new JPanel(new BorderLayout());
		ip3.setBackground(b2);
		ip3.setPreferredSize(new Dimension(55,55));
		JPanel ip4 = new JPanel(new BorderLayout());
		ip4.setBackground(b2);
		JPanel ip5 = new JPanel(new BorderLayout());
		ip5.setBackground(b2);
		JPanel ip6 = new JPanel(new BorderLayout());
		ip6.setBackground(b2);
		
		/* setup components */
		// JComboBox setup
		jl1.setBackground(b1);
		jl1.setForeground(Color.WHITE);
		jl1.setText("Instrument");
		jb.setOpaque(true);
		jb.setForeground(Color.WHITE);
		jb.setBackground(b1);
		jb.setSelectedIndex(0);
		jb.addActionListener(instrHand);
		
		
		// JSlider setup
		jl2.setBackground(b1);
		jl2.setForeground(Color.WHITE);
		jl2.setText("Volume");

		
		js.setOrientation(JSlider.VERTICAL);
		js.setPreferredSize(new Dimension(10,80));
		js.setBackground(b1);
		js.setForeground(Color.WHITE);
		js.setMaximum(127);
		js.setMinimum(0);
		js.setValue(64);
		
		// setting volume to 64 in the midi controller
		midiObj.getMidiChannel()[1].controlChange(7, 64);

		// adding change listener to slider
		js.addChangeListener(volHand);

		// JKnob setup
		jp1.setBackground(b1);
		jp1.setForeground(Color.WHITE);
		jp1.setText("Pan");
		jn.setBackground(b1);
		jn.addMouseListener(panHand);
		jn.addMouseMotionListener(panHand);
		
		// JButton setup for reverb on/off
		jv1.setBackground(b1);
		jv1.setForeground(Color.WHITE);
		jv1.setText("Reverb");
		jVerbBut.setBackground(b1);
		jVerbBut.setText("OFF");
		jVerbBut.setForeground(Color.WHITE);
		jVerbBut.addActionListener(verbHand);
		
		// JButton setup for tremelo on/off
		jt1.setBackground(b1);
		jt1.setForeground(Color.WHITE);
		jt1.setText("Tremolo");
		jTremBut.setBackground(b1);
		jTremBut.setText("OFF");
		jTremBut.setForeground(Color.WHITE);
		jTremBut.addActionListener(tremHand);
		
		// JButton setup for chorus on/off
		jc1.setBackground(b1);
		jc1.setForeground(Color.WHITE);
		jc1.setText("Chorus");
		jChorBut.setBackground(b1);
		jChorBut.setText("OFF");
		jChorBut.setForeground(Color.WHITE);
		jChorBut.addActionListener(chorHand);
		
		// add components to panel
		ip1.add(jl1);
		ip1.add(jb);
		
		ip2.add(jl2, BorderLayout.NORTH);
		ip2.add(js, BorderLayout.CENTER);
		
		ip3.add(jp1, BorderLayout.NORTH);
		ip3.add(jn, BorderLayout.CENTER);
		
		ip4.add(jv1, BorderLayout.NORTH);
		ip4.add(jVerbBut, BorderLayout.CENTER);
		
		ip5.add(jt1, BorderLayout.NORTH);
		ip5.add(jTremBut, BorderLayout.CENTER);
		
		ip6.add(jc1, BorderLayout.NORTH);
		ip6.add(jChorBut, BorderLayout.CENTER);
		
		// passed JPanel setup
		jp.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
		jp.add(ip1);
		jp.add(ip2);
		jp.add(ip3);
		jp.add(ip4);
		jp.add(ip5);
		jp.add(ip6);
	}

	public MidiSetup getMidiObject(){
		return midiObj;
	}
	
	public NoteHandler getNoteHandler(){
		return noteHand;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable(){
			
			public void run(){
				MainApp instrument = new MainApp();
				instrument.setVisible(true);
			}
		});
	}

}
