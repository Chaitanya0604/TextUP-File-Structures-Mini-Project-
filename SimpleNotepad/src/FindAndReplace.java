import java.awt.Color;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class FindAndReplace {
	GUI gui;
	public FindAndReplace(GUI gui)
	{
		this.gui=gui;
	}
	int currentPosition =0;
	public void find()
	{
	    String findFromText=gui.textArea.getText();	
	    String toFindText=gui.findtextField.getText();
	    Highlighter h= gui.textArea.getHighlighter();
	    h.removeAllHighlights();
	    
		while(findFromText.indexOf(toFindText,currentPosition) !=-1)
		{
		    int indexOf=findFromText.indexOf(toFindText,currentPosition);
		    int length=toFindText.length();		    
		    try
			{
				h.addHighlight(indexOf,indexOf+length,new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
			}catch(BadLocationException ex)
			{
				System.out.println("Error");
			}
	
		    currentPosition=indexOf+length;
		}

		if(currentPosition>=findFromText.length())
		{
			currentPosition=0;
		}
		if(findFromText.indexOf(toFindText,currentPosition)==-1)
		{
			currentPosition =0;
		}
	}

	public void replace()
	{
		

		String findFromText=gui.textArea.getText();	
	    String toFindText=gui.findtextField.getText();
		String withReplaceText=gui.replacetextField.getText();
		gui.textArea.setText(findFromText.replaceAll(toFindText, withReplaceText));
		currentPosition=0;
	}

	
	
}
