import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Function_Edit {
	GUI gui;
	Toolkit t = Toolkit.getDefaultToolkit();
	Clipboard clipboard = t.getSystemClipboard();
	JFrame f;
	public Function_Edit(GUI gui)
	{
		this.gui=gui;
	}
	
	public void cut()
	{
		//gui.textArea.cut();
		String cutString=gui.textArea.getSelectedText();
		StringSelection cutSelection=new StringSelection(cutString);
		clipboard.setContents(cutSelection, cutSelection);
		gui.textArea.replaceRange("",gui.textArea.getSelectionStart(),gui.textArea.getSelectionEnd());
	}
	public void copy()
	{
		//gui.textArea.copy();
		String copyText=gui.textArea.getSelectedText();
		StringSelection copySelection= new StringSelection(copyText);
		clipboard.setContents(copySelection, copySelection);
	}
	public void paste() 
	{
		//gui.textArea.paste();
		try
		{
			Transferable pasteText=clipboard.getContents(gui);
			String sel=(String) pasteText.getTransferData(DataFlavor.stringFlavor);
		    gui.textArea.replaceRange(sel, gui.textArea.getSelectionStart(), gui.textArea.getSelectionEnd());
		}

		catch(Exception e)
		{
			JOptionPane.showMessageDialog(f,"No content to paste!","Alert",JOptionPane.WARNING_MESSAGE); 
		}
		
	}
	public void selectAll()
	{
		gui.textArea.selectAll();
	}
	
	public void undo()
	{
		try
		{
			gui.um.undo();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(f,"Cannot Undo!","Alert",JOptionPane.WARNING_MESSAGE);  
		}
	}
	public void redo()
	{
		try {
			gui.um.redo();
		}
		catch(Exception e)
		{
			System.out.println("Cannot Redo");
			JOptionPane.showMessageDialog(f,"Cannot Redo!","Alert",JOptionPane.WARNING_MESSAGE);  
		}
		
		
	}

}
