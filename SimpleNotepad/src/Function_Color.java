import java.awt.Color;

public class Function_Color {
	GUI gui;
	
	public Function_Color(GUI gui)
	{
		this.gui=gui;
	}
	public void changeColor(String color)
	{
		switch(color) {
		case "Light":
			gui.window.getContentPane().setBackground(Color.white);
			gui.textArea.setBackground(Color.white);
			gui.textArea.setForeground(Color.black);			
			break;
	
		case "Dark":
			gui.window.getContentPane().setBackground(Color.black);
			gui.textArea.setBackground(Color.black);
			gui.textArea.setForeground(Color.white);			
			break;
			
		case "Blue":
			gui.window.getContentPane().setBackground(new Color(17,105,137));
			gui.textArea.setBackground(new Color(17,105,137));
			gui.textArea.setForeground(Color.white);			
			break;
		}
	}
}
