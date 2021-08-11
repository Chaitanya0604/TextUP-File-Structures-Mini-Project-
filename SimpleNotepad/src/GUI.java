import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener{
	
	JFrame window;
	ImageIcon icon =new ImageIcon(getClass().getResource("edit-logo.png"));
	JFrame frame;
	ImageIcon search= new ImageIcon(getClass().getResource("search-logo.png"));
	
	
	//Text Area
	JTextArea textArea;
	JScrollPane scrollPane;
	boolean wordWrapOn = false;
	//Top Menu Bar
	JMenuBar menuBar;
	JMenu menuFile, menuEdit, menuFormat, menuColor;
	
	//File Menu
	JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
	
	//Edit Menu
	JMenuItem iCut, iCopy, iPaste, iSelectAll, iUndo, iRedo, iSearch, iFindAndReplace;
	
	//Format Menu
	JMenuItem iWrap, iFontArial,iFontCMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24, iFontSize28, iFontStyleBold, iFontStyleItalic, iFontStylePlain;
	JMenu menuFont, menuFontSize, menuFontStyle;
	
	//Color Menu
	JMenuItem iColor1,iColor2,iColor3;
	
	//Find and Replace
	JTextField findtextField;
    JTextField replacetextField;
	JButton findNewButton;
	JButton replaceNewButton;
	
	Function_file file = new Function_file(this);
	Function_Edit edit=new Function_Edit(this);
	Function_Format format=new Function_Format(this);
	Function_Color color=new Function_Color(this); 
    FindAndReplace fir=new FindAndReplace(this);
	
	KeyHandler kHandler=new KeyHandler(this);
	UndoManager um=new UndoManager();

	public static void main(String[] args) {
		new GUI();
		
	}
	public GUI()
	{
		createWindow();
		createTextArea();
		createMenuBar();
		createFileMenu();
		createEditMenu();
		createFormatMenu();
		createColorMenu();
		
		format.selectedFont="Arial";
		format.createFont(16);
		format.selectedFontStyle="Plain";
		format.wordWrap();
		color.changeColor("White");
		window.setVisible(true);
		
	}
	public void createWindow()
	{
		window=new JFrame("TextUp");
		window.setSize(800,600);
		window.setIconImage(icon.getImage());
		
	}
	public void createTextArea()
	{
		textArea=new JTextArea();
		
		
		textArea.setFont(format.arial);
		
		textArea.addKeyListener(kHandler);
		
		
		textArea.getDocument().addUndoableEditListener(
				new UndoableEditListener()
				{
					
					@Override
					public void undoableEditHappened(UndoableEditEvent e) {
						um.addEdit(e.getEdit());
					}
				});
		
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		window.add(scrollPane);
		//window.add(textArea);
	}
	public void createMenuBar()
	{
		menuBar=new JMenuBar();
		window.setJMenuBar(menuBar);
		
		menuFile=new JMenu("File");
		menuBar.add(menuFile);
		
		menuEdit=new JMenu("Edit");
		menuBar.add(menuEdit);
		
		menuFormat=new JMenu("Format");
		menuBar.add(menuFormat);
		
		menuColor=new JMenu("Theme");
		menuBar.add(menuColor);
	}
	public void createFileMenu()
	{
		iNew=new JMenuItem("New");
		iNew.addActionListener(this);
		iNew.setActionCommand("New");
		menuFile.add(iNew);
				
		iOpen=new JMenuItem("Open (Shift + O)");
		iOpen.addActionListener(this);
		iOpen.setActionCommand("Open");
		menuFile.add(iOpen);
				
		iSave=new JMenuItem("Save (Shift +S)");
		iSave.addActionListener(this);
		iSave.setActionCommand("Save");
		menuFile.add(iSave);
		
		iSaveAs=new JMenuItem("Save As (Shift + Control +S)");
		iSaveAs.addActionListener(this);
		iSaveAs.setActionCommand("Save As");
		menuFile.add(iSaveAs);
		
		iExit=new JMenuItem("Exit");
		iExit.addActionListener(this);
		iExit.setActionCommand("Exit");
		menuFile.add(iExit);
	}
	public void createEditMenu()
	{
		iCut=new JMenuItem("Cut");
		iCut.addActionListener(this);
		iCut.setActionCommand("Cut");
		menuEdit.add(iCut);
		
		iCopy=new JMenuItem("Copy");
		iCopy.addActionListener(this);
		iCopy.setActionCommand("Copy");
		menuEdit.add(iCopy);
		
		iPaste=new JMenuItem("Paste");
		iPaste.addActionListener(this);
		iPaste.setActionCommand("Paste");
		menuEdit.add(iPaste);
		
		iSelectAll=new JMenuItem("Select All");
		iSelectAll.addActionListener(this);
		iSelectAll.setActionCommand("Select All");
		menuEdit.add(iSelectAll);
		
		iUndo=new JMenuItem("Undo");
		iUndo.addActionListener(this);
		iUndo.setActionCommand("Undo");
		menuEdit.add(iUndo);
		
		iRedo=new JMenuItem("Redo");
		iRedo.addActionListener(this);
		iRedo.setActionCommand("Redo");
		menuEdit.add(iRedo);
				
		iFindAndReplace=new JMenuItem("Find and Replace");
		iFindAndReplace.addActionListener(this);
		iFindAndReplace.setActionCommand("FindAndReplace");
		menuEdit.add(iFindAndReplace);
		
	}
	public void createFormatMenu() 
	{
		iWrap=new JMenuItem("Word Wrap : OFF");
		iWrap.addActionListener(this);
		iWrap.setActionCommand("Word Wrap");
		menuFormat.add(iWrap);
		
		menuFont=new JMenu("Font");
		menuFormat.add(menuFont);
		
		iFontArial=new JMenuItem("Arial");
		iFontArial.addActionListener(this);
		iFontArial.setActionCommand("Arial");
		menuFont.add(iFontArial);
		
		iFontCMS=new JMenuItem("Comic Sans MS");
		iFontCMS.addActionListener(this);
		iFontCMS.setActionCommand("Comic Sans MS");
		menuFont.add(iFontCMS);
		
		iFontTNR=new JMenuItem("Times New Roman");
		iFontTNR.addActionListener(this);
		iFontTNR.setActionCommand("Times New Roman");
		menuFont.add(iFontTNR);
		
		menuFontSize=new JMenu("Font Size");
		menuFormat.add(menuFontSize);
		
		iFontSize8=new JMenuItem("8");
		iFontSize8.addActionListener(this);
		iFontSize8.setActionCommand("8");
		menuFontSize.add(iFontSize8);
		
		iFontSize12=new JMenuItem("12");
		iFontSize12.addActionListener(this);
		iFontSize12.setActionCommand("12");
		menuFontSize.add(iFontSize12);
		
		iFontSize16=new JMenuItem("16");
		iFontSize16.addActionListener(this);
		iFontSize16.setActionCommand("16");
		menuFontSize.add(iFontSize16);
		
		iFontSize20=new JMenuItem("20");
		iFontSize20.addActionListener(this);
		iFontSize20.setActionCommand("20");
		menuFontSize.add(iFontSize20);
		
		iFontSize24=new JMenuItem("24");
		iFontSize24.addActionListener(this);
		iFontSize24.setActionCommand("24");
		menuFontSize.add(iFontSize24);
		
		iFontSize28=new JMenuItem("28");
		iFontSize28.addActionListener(this);
		iFontSize28.setActionCommand("28");
		menuFontSize.add(iFontSize28);
		
		menuFontStyle=new JMenu("Font Style");
		menuFormat.add(menuFontStyle);
		
		iFontStyleBold=new JMenuItem("Bold");
		iFontStyleBold.addActionListener(this);
		iFontStyleBold.setActionCommand("Bold");
		menuFontStyle.add(iFontStyleBold);
		
		iFontStyleItalic=new JMenuItem("Italic");
		iFontStyleItalic.addActionListener(this);
		iFontStyleItalic.setActionCommand("Italic");
		menuFontStyle.add(iFontStyleItalic);
		
		iFontStylePlain=new JMenuItem("Plain");
		iFontStylePlain.addActionListener(this);
		iFontStylePlain.setActionCommand("Plain");
		menuFontStyle.add(iFontStylePlain);
		
		
	}
	public void createColorMenu()
	{
		iColor1=new JMenuItem("Light");
		iColor1.addActionListener(this);
		iColor1.setActionCommand("Light");
		menuColor.add(iColor1);
		
		iColor2=new JMenuItem("Dark");
		iColor2.addActionListener(this);
		iColor2.setActionCommand("Dark");
		menuColor.add(iColor2);
		
		iColor3=new JMenuItem("Shades Of Blue");
		iColor3.addActionListener(this);
		iColor3.setActionCommand("Blue");
		menuColor.add(iColor3);
		
	}
	public void createFindReplace()
	{
		frame = new JFrame();
		frame.setTitle("Find and Replace");
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setSize(800,115);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(search.getImage());
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 0, 102));
		panel.setBounds(0, 0, 786, 80);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Find Text : ");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setBackground(SystemColor.textHighlightText);
		lblNewLabel.setBounds(10, 13, 85, 16);
		panel.add(lblNewLabel);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		
		findtextField = new JTextField();
		findtextField.setBounds(114, 10, 520, 19);
		panel.add(findtextField);
		findtextField.setColumns(10);
		
		findNewButton = new JButton("FIND");
		findNewButton.setBackground(new Color(255, 255, 153));
		findNewButton.setBounds(650, 9, 106, 21);
		findNewButton.addActionListener(this);
		findNewButton.setActionCommand("Find");
		panel.add(findNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Replace Text : ");
		lblNewLabel_1.setForeground(SystemColor.textHighlightText);
		lblNewLabel_1.setBounds(10, 54, 85, 16);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		
		replacetextField = new JTextField();
		replacetextField.setBounds(114, 51, 520, 19);
		panel.add(replacetextField);
		replacetextField.setColumns(10);
		
		replaceNewButton = new JButton("REPLACE");
		replaceNewButton.setBackground(new Color(255, 255, 153));
		replaceNewButton.setBounds(650, 50, 106, 21);
		panel.add(replaceNewButton);	
		replaceNewButton.addActionListener(this);  
		replaceNewButton.setActionCommand("Replace");
		frame.setVisible(true);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		
		switch (command) {
		
		case "New": file.newFile();break;
		
		case "Open": file.open();break;	
		
		case "Save As": file.saveAs();break;
		
		case "Save":file.save();break;
		
		case "Exit":file.exit();break;
		
		case "Word Wrap":format.wordWrap();break;
		
		case "Arial" :format.setFont(command);break;
		
		case "Comic Sans MS" :format.setFont(command);break;
		
		case "Times New Roman" :format.setFont(command);break;
		
		case "8": format.createFont(8);break;
		
		case "12": format.createFont(12);break;
		
		case "16": format.createFont(16);break;
		
		case "20": format.createFont(20);break;
		
		case "24": format.createFont(24);break;
		
		case "28": format.createFont(28);break;
		
		case "Bold" : format.setFontStyle(command);break;
		
		case "Italic" : format.setFontStyle(command);break;
		
		case "Plain" : format.setFontStyle(command);break;		
		
		case "Light": color.changeColor(command);break;
		
		case "Dark": color.changeColor(command);break;
		
		case "Blue": color.changeColor(command);break;	
		
		case "Cut" : edit.cut(); break;
		
		case "Copy" : edit.copy(); break;
		
		case "Paste" : edit.paste(); break;
		
		case "Select All" : edit.selectAll(); break;
		
		case "Undo" : edit.undo();break;
		
		case "Redo" : edit.redo();break;
		
		case "FindAndReplace":createFindReplace();break;
		
		case "Find" :fir.find(); break;
		
		case "Replace": fir.replace();break;
		}
	}
	
}



