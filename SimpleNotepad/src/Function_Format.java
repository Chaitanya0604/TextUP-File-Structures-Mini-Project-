import java.awt.Font;

public class Function_Format {

	GUI gui;
	Font arial, comicSansMS, timesNewRoman, arialBold, arialItalic, comicSansMSBold, comicSansMSItalic, timesNewRomanBold,timesNewRomanItalic;
	String selectedFont, selectedFontStyle="Plain";
	int res=0;
	
	public Function_Format(GUI gui)
	{
		this.gui=gui;
	}
	
	public void wordWrap()
	{
		if(gui.wordWrapOn==false)
		{
			gui.wordWrapOn=true;
			gui.textArea.setLineWrap(true);
			gui.textArea.setWrapStyleWord(true);//line break does not happen in the middle of the lines.
			gui.iWrap.setText("Word Wrap : ON");
		}
		else if(gui.wordWrapOn==true)
		{
			gui.wordWrapOn=false;
			gui.textArea.setLineWrap(false);
			gui.textArea.setWrapStyleWord(false);//line break does not happen in the middle of the lines.
			gui.iWrap.setText("Word Wrap : OFF");
		}
	}
	
	public void createFont(int fontSize)
	{
		arial = new Font("Arial", Font.PLAIN, fontSize);
		comicSansMS=new Font("Comic Sans MS", Font.PLAIN, fontSize);
		timesNewRoman=new Font("Times New Roman", Font.PLAIN, fontSize);
		
		arialBold = new Font("Arial", Font.BOLD, fontSize);
		comicSansMSBold=new Font("Comic Sans MS", Font.BOLD, fontSize);
		timesNewRomanBold=new Font("Times New Roman", Font.BOLD, fontSize);
		
		arialItalic = new Font("Arial", Font.ITALIC, fontSize);
		comicSansMSItalic=new Font("Comic Sans MS", Font.ITALIC, fontSize);
		timesNewRomanItalic=new Font("Times New Roman", Font.ITALIC, fontSize);
		
		
		setFont(selectedFont);
		
		
	}
	
	public void setFont(String font) {
		selectedFont=font;
		
		switch(selectedFont)
		{
		case "Arial":
			gui.textArea.setFont(arial);
			break;
		
		case "Comic Sans MS":
			gui.textArea.setFont(comicSansMS);
			break;
			
		case "Times New Roman":
			gui.textArea.setFont(timesNewRoman);
			break;
					
		}
		setFontStyle(selectedFontStyle);
	}
	public void setFontStyle(String fontStyle)
		{
			selectedFontStyle=fontStyle;
			
			switch(selectedFontStyle)
			{
				case "Bold":
					if(selectedFont.equals("Times New Roman"))
					{
						gui.textArea.setFont(timesNewRomanBold);
					}
					else if(selectedFont.equals("Comic Sans MS"))
					{
						gui.textArea.setFont(comicSansMSBold);
					}
					else
					{
						gui.textArea.setFont(arialBold);
					}
					break;
				
				case "Italic":
					if(selectedFont.equals("Times New Roman"))
					{
						gui.textArea.setFont(timesNewRomanItalic);
					}
					else if(selectedFont.equals("Comic Sans MS"))
					{
						gui.textArea.setFont(comicSansMSItalic);
					}
					else
					{
						gui.textArea.setFont(arialItalic);
					}
					break;
					
				case "Plain":
					if(selectedFont.equals("Times New Roman"))
					{
						gui.textArea.setFont(timesNewRoman);
					}
					else if(selectedFont.equals("Comic Sans MS"))
					{
						gui.textArea.setFont(comicSansMS);
					}
					else
					{
						gui.textArea.setFont(arial);
					}
					break;
				default : gui.textArea.setFont(arial);
			}
		}
		
	}

