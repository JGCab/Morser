import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

public class CustomButton extends JLabel implements MouseInputListener {
	public interface Command {
		public void exec();
	}

	Command currCommand;
	String text;
	Color fgColor;
	Color bgColor;
	int fontSize = 20;
	int borderSize = 5;
	Border borderVisible = BorderFactory.createLineBorder(Color.WHITE, borderSize);
	Font font = new Font("Roboto", Font.BOLD, fontSize);

	private void executeCommand(Command command){
		command.exec();
	}

	private void resetStyle(){
		this.setBackground(this.bgColor);
		this.setForeground(this.fgColor);
	}

	public void setStyle(Color fgColor, Color bgColor){
		this.fgColor = fgColor;
		this.bgColor = bgColor;
		this.resetStyle();
	}

	private void hoverStyle(){
		this.setBackground(Color.WHITE);
		this.setForeground(Main.black);
	}

	private void pressedStyle(){
		this.setBackground(Main.gray);
		this.setForeground(Main.black);
	}

	CustomButton(String text, Color fgColor, Color bgColor) {
		this.text = text;
		this.fgColor = fgColor;
		this.bgColor = bgColor;
		this.resetStyle();
		this.setText(text);
		this.setOpaque(true);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.addMouseListener(this);
		this.setFont(font);
	}

	public void setFontSize(int size){
		this.font = new Font("Roboto", Font.BOLD, size);
		this.setFont(font);
	}

	public void setCommand(Command command){
		currCommand = command;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		executeCommand(currCommand);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.pressedStyle();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.resetStyle();

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.hoverStyle();

	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.resetStyle();

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// nothing

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// the void

	}


}
