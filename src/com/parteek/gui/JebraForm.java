package com.parteek.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import util.Constants;
import util.LinePainter;
import util.TextLineNumber;
import util.Utils;

import com.parteek.jebra.GenrateTestCase;
import com.parteek.jebra.JebraJunitMaker;
import com.parteek.jebra.MainClassStructure;
import com.parteek.jebra.methods.MethodStructure;
import com.parteek.jebra.methods.TestMethodStructure;
import com.parteek.jebra.statements.JebraStmts;
import com.sun.org.apache.xml.internal.security.encryption.EncryptedType;

public class JebraForm extends JFrame implements ActionListener{
	JPanel rightPanel;
	DefaultTableModel dm;
	JTextArea junitTextArea;
	MainClassStructure myLoadedData;
	JTable testMethodtable;
	JTextPane textPane;
	JComboBox<MethodStructure> methodsListComboBox=null;
	public JebraForm(){
		super("Jebra - Auto Junit Genrator");
		String filePath = null;//"E:\\workspace\\jebra\\src\\example\\parent.java";
		getContentPane().setBackground(Color.CYAN);
		setLocationRelativeTo(null);
		createMenu();
		loadData(filePath); //load file data
		GenrateTestCase.getInstance(); //loading singleton object need to handle test methods genrateda data
		createGui();
	}
	
	public void createGui() {
		leftScreen();
		rightScreen();
		
//		PropertyChangeListener resizeHandler = new PropertyChangeListener() {
//			
//			@Override
//			public void propertyChange(PropertyChangeEvent evt) {
//				System.out.println(rightPanel.getHeight());
//				
//			}
//		};
		
//		JSplitPane splitPane;
//		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel,rightPanel);
//		splitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, resizeHandler);
//		splitPane.setContinuousLayout(true);
//		splitPane.setDividerLocation(0.5);
//	    splitPane.setOneTouchExpandable(true);
//		getContentPane().add(splitPane);
//	    this.add(splitPane);
//	    this.pack();
	    this.setVisible(true);
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setVisible(true);
	    
	}
	
	public void leftScreen(){

		DefaultStyledDocument doc = new DefaultStyledDocument();
		textPane = new JTextPane(doc);
		TextLineNumber tln = new TextLineNumber(textPane);
		LinePainter painter = new LinePainter(textPane);
		refreshLeftScreen();
		JScrollPane jScrollPane= new JScrollPane(textPane);
		jScrollPane.setRowHeaderView( tln );
		add(jScrollPane, BorderLayout.CENTER );
			
	}
	
	public void refreshLeftScreen() {
		if(myLoadedData==null)
			return;
		try {
			textPane.getDocument().insertString(0, myLoadedData.getMyClass().toString(), null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void updateText(String text) {
		// TODO Auto-generated method stub
		StyledDocument doc = (StyledDocument)textPane.getDocument();
		try {
			
			doc.remove(0, doc.getLength());
			doc.insertString(0, text, null);
		} catch (BadLocationException e) {
			
			e.printStackTrace();
		}
	}
	
	public void rightScreen() {
		rightPanel = new JPanel();
		rightPanel.setPreferredSize( new Dimension( 800, 600 ) );
		
		BorderLayout rightPanelLayout = new BorderLayout();
		rightPanel.setLayout(rightPanelLayout);
		rightPanelLayout.setHgap(10);
		rightPanelLayout.setVgap(10);
		
		CreateGuiForMethodSelection();
		CreateGuiListForTestMethod();
		CreateGuiForTestCaseMethod();
		add(rightPanel,BorderLayout.EAST);
		
		
	}

	private void CreateGuiForMethodSelection() {
		
		JPanel childPanel1 = new JPanel();
		childPanel1.setPreferredSize( new Dimension( 200, 30 ) );
		childPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
		BorderLayout layoutChildPanel1 = new BorderLayout();
		childPanel1.setLayout(layoutChildPanel1);
		layoutChildPanel1.setHgap(10);
		layoutChildPanel1.setVgap(10);
		
		
		
		JLabel combolabel = new JLabel("Please select method to generate juint");
		
		methodsListComboBox = new JComboBox<MethodStructure>();
		RefreshGuiForMethodSelection();
		methodsListComboBox.setForeground(Color.BLACK);
		methodsListComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
		methodsListComboBox.setMaximumRowCount(5);
		methodsListComboBox.setMaximumSize(new Dimension(10,20));
		methodsListComboBox.setSelectedIndex(-1);
		
		ItemListener itemListener = new ItemListener() {
		      public void itemStateChanged(ItemEvent itemEvent) {
		        int state = itemEvent.getStateChange();
		        
		        MethodStructure obj = (MethodStructure)itemEvent.getItem();
		        if(obj.getName() != null){
			        updateText(obj.getMyMethod().toString()); 
			        List<TestMethodStructure> testCases= obj.getAllTestMethods();
			        Vector dummyHeader = new Vector();
		    	    dummyHeader.addElement("");
		    	    dm.setDataVector(strArray2Vector(testCases), dummyHeader);
			    }
		        else{
		        	updateText(myLoadedData.getMyClass().toString());
		        	Vector dummyHeader = new Vector();
		    	    dummyHeader.addElement("");
		    	    dm.setDataVector(null, dummyHeader);
		        }
		       }
		    };
			    
		methodsListComboBox.addItemListener(itemListener); 
			
		childPanel1.add(combolabel,BorderLayout.WEST);
		childPanel1.add(methodsListComboBox,BorderLayout.EAST);
		rightPanel.add(childPanel1,BorderLayout.NORTH);

	}
	
	private void RefreshGuiForMethodSelection() {
		if(myLoadedData == null) {
			return;
		}
		ArrayList<MethodStructure>  allMethods= myLoadedData.getAllMethods();
		MethodStructure array [] = new MethodStructure [allMethods.size()];
		for (int i = 0; i < allMethods.size(); i++) {
			if(!allMethods.get(i).isContructor())
				array[i] = allMethods.get(i);
			else{
				array[i] = new MethodStructure();
			}
		}
		MyComboBoxModel myModel = new MyComboBoxModel( array);
		methodsListComboBox.setModel(myModel);
	}
	private void CreateGuiListForTestMethod() {
		JPanel childPanel2 = new JPanel();
		childPanel2.setPreferredSize( new Dimension( 480, 100 ) );
		//childPanel2.setBorder(BorderFactory.createLineBorder(Color.black));
	    
		BorderLayout layoutChildPanel2 = new BorderLayout();
		childPanel2.setLayout(layoutChildPanel2);
		layoutChildPanel2.setHgap(10);
		layoutChildPanel2.setVgap(10);
		
		/////2nd panel data components
		dm = new DefaultTableModel();
 		testMethodtable = new JTable(dm);
 		testMethodtable.setShowGrid(false);
 		testMethodtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    JScrollPane scrollTable = new JScrollPane(testMethodtable);
	    scrollTable.setColumnHeader(null);
	    scrollTable.setMaximumSize(new Dimension(1250, 140));
	    Box tableBox = new Box(BoxLayout.Y_AXIS);
	    tableBox.add(new JLabel("Please select test method to generate junit"));
	    tableBox.add(scrollTable);
	    childPanel2.add(tableBox,BorderLayout.EAST);
	    
	    testMethodtable.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent arg0) {
			}
		});
	    testMethodtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(testMethodtable.getSelectedRow()<0)
					return;
			//	System.out.println(testMethodtable.getValueAt(testMethodtable.getSelectedRow(), 0).toString());
				TestMethodStructure structure = (TestMethodStructure) testMethodtable.getValueAt(testMethodtable.getSelectedRow(), 0);
				updateTextStyle(structure);
				updateJuintTestArea(structure);
				//System.out.println();
				
			}
		});
		
		rightPanel.add(childPanel2,BorderLayout.WEST);
	}
	
	
	private void CreateGuiForTestCaseMethod() {
		JPanel childPanel3 = new JPanel();
		childPanel3.setPreferredSize( new Dimension( 200, 500 ) );
		childPanel3.setBorder(BorderFactory.createLineBorder(Color.black));
		BorderLayout layoutChildPanel3 = new BorderLayout();
		childPanel3.setLayout(layoutChildPanel3);
		layoutChildPanel3.setHgap(10);
		layoutChildPanel3.setVgap(10);
		
		Box panel = new Box(BoxLayout.Y_AXIS);
        JLabel label = new JLabel("Generated juint (You can edit if you want)", JLabel.LEFT);
        label.setAlignmentY(JLabel.TOP_ALIGNMENT);
        junitTextArea = new JTextArea(24, 3);
        TextLineNumber tln = new TextLineNumber(junitTextArea);
        JScrollPane testTextAreaSP = new JScrollPane(junitTextArea);
        testTextAreaSP.setRowHeaderView( tln );
        junitTextArea.setAlignmentY(JTextField.TOP_ALIGNMENT);

        panel.add(label);
        panel.add(testTextAreaSP);
	    
		childPanel3.add(panel,BorderLayout.NORTH);
		rightPanel.add(childPanel3,BorderLayout.SOUTH);
		
	}
	
	private void updateTextStyle(TestMethodStructure structure) {
		List<JebraStmts> jebraStmts = structure.getJebraStmts();
		StyledDocument doc = (StyledDocument)textPane.getDocument();
		try {
			String methodText = doc.getText(0, doc.getLength());
			String[] textArray = methodText.split("\n");
			doc.remove(0, doc.getLength());
			// Create a style object and then set the style attributes
	        Style style = doc.addStyle("textBgColor", null);
	        StyleConstants.setBackground(style, Color.CYAN);
	        
	        for (int i = 0; i < textArray.length; i++) {
	        	String str = (i==0?textArray[i] :"\n" + textArray[i]);

	        	if(structure.CheckMethodContainsStatement(str)) {
        			textPane.getDocument().insertString(doc.getLength(), str, style);
	        	}
	        	else{
	        		textPane.getDocument().insertString(doc.getLength(), str, null);
	        	}	
		}	
	} catch (BadLocationException e) {
		
		e.printStackTrace();
	}	
	}
	private void updateTextStyle1(TestMethodStructure structure) {
		
		ArrayList<Integer> lineNumbers = structure.getLineNumbers();
		StyledDocument doc = (StyledDocument)textPane.getDocument();
		try {
			String methodText = doc.getText(0, doc.getLength());
			String[] textArray = methodText.split("\n");
			doc.remove(0, doc.getLength());
			int minusFactor = lineNumbers.get(0);
			
			// Create a style object and then set the style attributes
	        Style style = doc.addStyle("textBgColor", null);
	        StyleConstants.setBackground(style, Color.CYAN);
	        
	        for (int i = 0; i < textArray.length; i++) {
	        	String str = (i==0?textArray[i] :"\n" + textArray[i]);
	        	//String ifstatement = "";
	        	if(lineNumbers.contains(i+minusFactor-1)/* && !(str.contains("if") || str.contains("else"))*/){
//	        		boolean showColor =true;
//	        		if(str.contains("if")) {
//	        			ifstatement = "if";
//	        		}
//	        		if(str.contains("else") && ifstatement =="if") {
//	        			ifstatement = "";
//	        			showColor =false;
//	        		}
//	        		if(showColor)
	        			textPane.getDocument().insertString(doc.getLength(), str, style);
//	        		else {
//	        			textPane.getDocument().insertString(doc.getLength(), str, null);
//	        		}
	        	}
	        	else{
	        		textPane.getDocument().insertString(doc.getLength(), str, null);
	        	}	
			}	
		} catch (BadLocationException e) {
			
			e.printStackTrace();
		}	
	}
	
	private void updateJuintTestArea(TestMethodStructure structure) {
		
		Map<String, ArrayList<String>> annotationStatements = structure.getMockedRefernce();
		Set<String> testStatements = structure.getTestBodyStatments();
		
		ArrayList<String> allTextLine = new ArrayList<String>();
		
		/* Pre-Added Text*/
		allTextLine.add("/*");
		allTextLine.add("*  Genrated by Jebra Software");
		allTextLine.add("*/ \n\n");
		for(Map.Entry<String, ArrayList<String>> entry : annotationStatements.entrySet()) {
			ArrayList<String>  annotationStmts= entry.getValue();
			String junitText ="";
			for (String string : annotationStmts) {
				junitText +=string+"\n";
				
			}
			allTextLine.add(junitText);
		}
		
		if(testStatements!=null) {
			String junitText ="";
			for (String str : testStatements) {
				if(! ( str.contains("@") || str.contains("public void")  || str.contains("End of Test-case") ) ) {
					junitText+="\t";
				}
				
				junitText +=str;
				
				if(str.contains(Constants.TEST)) {
					junitText +="\n";
				}
				else
					junitText +="\n\n";
			}
			allTextLine.add(junitText);
		}
		
		if(junitTextArea!=null) {
			String junitText ="";
			for (String string : allTextLine) {
				junitText +=string+"\n";
				
			}
			junitTextArea.setText(junitText);
		}
	}
	
	private Vector<Vector<TestMethodStructure>> strArray2Vector(List<TestMethodStructure> str) {
	    Vector<Vector<TestMethodStructure>> vector = new Vector<Vector<TestMethodStructure>>();
	    for (int i = 0; i < str.size(); i++) {
	    	Vector<TestMethodStructure> v = new Vector<TestMethodStructure>();
	    	v.addElement(str.get(i));
	    	vector.addElement(v);
		}
	    return vector;
	  }
	
	public void loadData(String filePath) {
		
		if(Utils.isNotBlank(filePath)) {
		//String filePath = "E:\\workspace\\jebra\\src\\example\\getpaidFile.java";
		JebraJunitMaker jebraJunitMaker= JebraJunitMaker.getInstance();
		try {
			jebraJunitMaker.startProcessing(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		myLoadedData = jebraJunitMaker.getMainClassStructure();
		}
	}
	
	public void createMenu() {
		 JMenu menu ,menuAbout;  
         JMenuItem i1, i2, i3,i4 ,i5;  
         
         JMenuBar mb=new JMenuBar();  
         menu=new JMenu("File");  
         menuAbout=new JMenu("Info"); 
         i1=new JMenuItem("Upload Java Class");  
         i2=new JMenuItem("Export Full Class");  
         i3=new JMenuItem("Exit"); 
         
         i4=new JMenuItem("About");  
         i5=new JMenuItem("Version");
         
         i1.addActionListener(this);
         i2.addActionListener(this);
         i3.addActionListener(this);
         i4.addActionListener(this);
         i5.addActionListener(this);
         
         menu.add(i1); menu.add(i2); menu.add(i3);  
         menuAbout.add(i4); menuAbout.add(i5);
         mb.add(menu); mb.add(menuAbout);  
         this.setJMenuBar(mb);  
        // this.setSize(400,400);  
        // this.setLayout(null);  
         this.setVisible(true);
	}
	public static String LAST_USED_FOLDER ="hellp";
	public void actionPerformed(ActionEvent e) {    
		if(e.getActionCommand().equals("Upload Java Class"))   {
			 
			Preferences prefs = Preferences.userRoot().node(getClass().getName());
			JFileChooser fc=new JFileChooser(prefs.get(LAST_USED_FOLDER, new File(".").getAbsolutePath()));    
		    int i=fc.showOpenDialog(this);    
		    if(i==JFileChooser.APPROVE_OPTION){    
		        File f=fc.getSelectedFile();    
		        String filepath=f.getPath();    
		        refreshAppWithNewFile(filepath);
		           
		        prefs.put(LAST_USED_FOLDER, fc.getSelectedFile().getParent());
		    }    
		}
		if(e.getActionCommand().equals("Export Full Class")) {   
			//ta.paste();    
		}
		if(e.getActionCommand().equals("Exit")) {    
			//ta.copy();    
		}if(e.getActionCommand().equals("About"))   { 
			String str = "JEBRA TECHNOLOGY (Auto Junit Genrator)\n"
					+ "\nDeveloped by Parteek Singh Slathia \n"
					+ "\nEmail: slathia.parteeksingh@gmail.com"
					+ "\nHappy Coding";
			JOptionPane.showMessageDialog(this, str, "About", JOptionPane.INFORMATION_MESSAGE);    
		}
		if(e.getActionCommand().equals("Version"))   { 
			JOptionPane.showMessageDialog(this, "\nProduct Name- JEBRA(Auto Junit Genrator)\nVersion-1.0.0");  
		}
	}
	
	private void refreshAppWithNewFile(String filepath) {
		loadData(filepath); 
		updateText(myLoadedData.getMyClass().toString());
        RefreshGuiForMethodSelection(); 
	}
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JebraForm f1=new JebraForm();	
		
	}
	
	
}
