import com.morser.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class Main{

	// color pallette
	static Color black = new Color(0x202230);
	static Color red = new Color(0xED0033);
	static Color orange = new Color(0xF24A46);
	static Color green = new Color(0x1AFFA7);
	static Color gray = new Color(0x696969);

	// enum for determining status
	public static enum Status{
		ENCODE, DECODE
	}

	// variables
	static Status status = Status.ENCODE;
	static int spacing = 30;
	static int borderSize = 1;
	static int[] resolution = { 16, 9 };
	static int resoMult = 75;
	static int width = resoMult * resolution[0];
	static int height = resoMult * resolution[1];
	static int IOFontSize = 18;

	// all components
	static MorseTree mt = new MorseTree();
	static Border leftPanelBorder = BorderFactory.createLineBorder(Color.WHITE, borderSize);
	static JFrame mainFrame = new JFrame();
	static CustomButton exitButton = new CustomButton("X", Color.WHITE, red);
	static CustomButton minimizeButton = new CustomButton("-", Color.WHITE, Color.gray);
	static CustomButton statusButton = new CustomButton("Click here to change mode", black, green);
	static JLabel title = new JLabel("[ -- --- .-. ... . .-. ]");
	static JLabel leftIndicator = new JLabel(">>> [T] INPUT");
	static JLabel rightIndicator = new JLabel(">>> [-] OUTPUT");
	static JLabel errorScreen = new JLabel();
	static JLabel errorMessage = new JLabel();
	static JPanel rightPanel = new JPanel();
	static JPanel leftPanel = new JPanel();
	static JTextArea input = new JTextArea();
	static JTextArea output = new JTextArea(" ");
	static Font IOFont = new Font("Roboto", Font.BOLD, IOFontSize);

	// buton methods
	static CustomButton.Command closeWindow = new CustomButton.Command() {
		@Override
		public void exec() {
			WindowEvent onClose = new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING);
			Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(onClose);
		}
	};

	static CustomButton.Command minimizeWindow = new CustomButton.Command() {
		@Override
		public void exec() {
			mainFrame.setState(JFrame.ICONIFIED);
		}
	};

	static private void clearFields(){
		input.setText("");
		output.setText(" ");
	}

	static CustomButton.Command changeStatus = new CustomButton.Command(){
		@Override
		public void exec(){
			if (status == Status.DECODE){
				status = Status.ENCODE;
				statusButton.setStyle(black, green);
				statusButton.setText("ENCODE");
				leftIndicator.setText(">>> [T]");
				rightIndicator.setText(">>> [-]");
				clearFields();
			} else if(status == Status.ENCODE){
				status = Status.DECODE;
				statusButton.setStyle(black, orange);
				statusButton.setText("DECODE");
				leftIndicator.setText(">>> [-]");
				rightIndicator.setText(">>> [T]");
				clearFields();
			}
		}
	};

	private static class DocListener implements DocumentListener{
		private void backToNormal(){
			errorMessage.setVisible(false);
			errorScreen.setVisible(false);
			output.setVisible(true);

		}

		private void informError(String informErrorMessage){
			errorMessage.setText(informErrorMessage);
			output.setVisible(false);
			errorMessage.setVisible(true);
			errorScreen.setVisible(true);
			
		}

		private void typing(){
			int errors = 0;
			String finalMessage = "";
			String message = input.getText();

			// if the string is empty
			if (message.isEmpty()){
				finalMessage = "";
				output.setText(finalMessage);
				backToNormal();
				return;
			}

			String[] wordsArray = message.split(" ");
			for(String a: wordsArray){
				if (a.equals("")){
					continue;
				}
				boolean noError = true;

				// the the status in to encode
				if(status == Status.ENCODE){
					if (mt.Encode(a) != null){
						finalMessage += mt.Encode(a);
					} else{
						informError(a + " contains a non-letter character");
						noError = false;
					}
				}else{ // if the status is to decode
					//check individual symbols first
					char[] individualSymbols = a.toCharArray();
					for(char b: individualSymbols){
						if(b != '.' && b != '-'){
							// creates an error if there is a non-binary symbol
							informError(a + " contains a character that isn't either [.] or [-]");
							noError = false;
							return;
						}
					// after the loop if the method did not return
					} if(a.length() > 4 || mt.Decode(a) == null){
						informError(a + " does not exist");
						noError = false;
					} else if (mt.Decode(a)!= null){
						finalMessage += mt.Decode(a);
					}
				} if (! noError){
					errors++;
				}
			} if(errors == 0){
				output.setText(finalMessage);
				backToNormal();
			}
			
		}

			@Override
		public void insertUpdate(DocumentEvent e) {
			typing();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			typing();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			typing();
		}
	}

	public static void main(String[] args) {
		// creating a frame

		mainFrame.setTitle("Morse Code Decoder/Encoder");
		mainFrame.setSize(new Dimension(width, height));
		mainFrame.setLayout(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.getContentPane().setBackground(black);
		mainFrame.setUndecorated(true);
		mainFrame.setVisible(true);

		// creating exit and minimize Button
		//exit button
		int exitButtonX = width - 40;
		int exitButtonY = 0;
		int exitButtonWidth = 40;
		int exitButtonHeight = 30;
		exitButton.setBounds(exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight);
		exitButton.setCommand(closeWindow);

		//minimize button
		int minimizeButtonX = width - exitButtonWidth - 40;
		int minimizeButtomY = exitButtonY;
		int minimizeButtonWidth = exitButtonWidth;
		int minimizeButtonHeight = exitButtonHeight;
		minimizeButton.setBounds(minimizeButtonX, minimizeButtomY, minimizeButtonWidth, minimizeButtonHeight);
		minimizeButton.setFontSize(30);
		minimizeButton.setCommand(minimizeWindow);

		// creating the title
		int titleFontSize = 70;
		int titleWidth = 550;
		int titleHeight = 70;
		int titleX = (width / 2) - (titleWidth / 2);
		int titleY = spacing;
		title.setBounds(titleX, titleY, titleWidth, titleHeight);
		title.setForeground(green);
		title.setFont(new Font("Roboto", Font.BOLD, titleFontSize));

		// creating the two indicators
		int indicatorFontSize = 20;
		int indicatorX = 20;
		int indicatorY = 20;
		int indicatorWidth = 300;
		int indiatoreHeight = 40;
		// left indicator
		leftIndicator.setBounds(indicatorX, indicatorY, indicatorWidth, indiatoreHeight);
		leftIndicator.setForeground(green);
		leftIndicator.setFont(new Font("Roboto", Font.BOLD, indicatorFontSize));
		// right indicator
		rightIndicator.setBounds(indicatorX, indicatorY, indicatorWidth, indiatoreHeight);
		rightIndicator.setForeground(orange);
		rightIndicator.setFont(new Font("Roboto", Font.BOLD, indicatorFontSize));

		// creating the right panel
		int rightPanelX = (width / 2) + spacing;
		int rightPanelY = titleY + titleHeight + spacing;
		int rightPanelWidth = (width / 2) - spacing * 2;
		int rightPanelHeight = height - titleY - titleHeight - spacing * 2;
		rightPanel.setBounds(rightPanelX, rightPanelY, rightPanelWidth, rightPanelHeight);
		rightPanel.setBackground(Color.WHITE);
		rightPanel.setLayout(null);

		// creating the left panel
		int leftPanelX = spacing + 10;
		int leftPanelY = rightPanelY;
		int leftPanelWidth = (width / 2) - 50;
		int leftPanelHeight = rightPanelHeight - 110;
		leftPanel.setBounds(leftPanelX, leftPanelY, leftPanelWidth, leftPanelHeight);
		leftPanel.setBackground(black);
		leftPanel.setBorder(leftPanelBorder);
		leftPanel.setLayout(null);

		// adding the text area
		int inputX = indicatorX;
		int inputY = indicatorY + spacing * 2;
		int inputWidth = leftPanelWidth - inputX - spacing;
		int inputHeight = leftPanelHeight - inputY - spacing;
		input.setBounds(inputX, inputY, inputWidth, inputHeight);
		input.setBackground(black);
		input.setForeground(green);
		input.setCaretColor(green);
		input.setLineWrap(true);
		input.setFont(IOFont);
		input.getDocument().addDocumentListener(new DocListener());

		// adding the status button
		int statusButtonX = leftPanelX;
		int statusButtonY = leftPanelHeight + leftPanelY + spacing;
		int statusButtonWidth = leftPanelWidth;
		int statusButtonHeight = rightPanelHeight - leftPanelHeight - spacing;
		statusButton.setBounds(statusButtonX, statusButtonY, statusButtonWidth, statusButtonHeight);
		statusButton.setCommand(changeStatus);
		statusButton.setFontSize(30);

		// adding output label
		int outputX = indicatorX;
		int outputY = indicatorY + spacing * 2;
		int outputWidth = rightPanelWidth - outputX - spacing;
		int outputHeight = rightPanelHeight - outputY - spacing;
		output.setBounds(outputX, outputY, outputWidth, outputHeight);
		output.setBackground(Color.WHITE);
		output.setForeground(orange);
		output.setEditable(false);
		output.setLineWrap(true);
		output.setAlignmentY(JTextArea.TOP_ALIGNMENT);
		output.setFont(IOFont);
		output.setVisible(true);

		//creating the error screen
		int errorScreenWidth = rightPanelWidth;
		int errorScreenHeight = (rightPanelHeight*2)/3;
		errorScreen.setBounds(0,0,errorScreenWidth, errorScreenHeight);
		errorScreen.setFont(new Font("Roboto", Font.BOLD, 200));
		errorScreen.setBackground(orange);
		errorScreen.setOpaque(true);
		errorScreen.setForeground(black);
		errorScreen.setHorizontalAlignment(JLabel.CENTER);
		errorScreen.setVerticalAlignment(JLabel.BOTTOM);
		errorScreen.setText("!");
		errorScreen.setVisible(false);

		// creating the error message
		int errorMessageX = 0;
		int errorMessageY = (rightPanelHeight*2)/3;
		int ErrorMessageWidth = rightPanelWidth;
		int errorMessageHeight = (rightPanelHeight*2)/3;
		errorMessage.setBounds(errorMessageX, errorMessageY, ErrorMessageWidth, errorMessageHeight);
		errorMessage.setFont(new Font("Roboto", Font.BOLD, 20));
		errorMessage.setOpaque(true);
		errorMessage.setBackground(orange);
		errorMessage.setForeground(black);
		errorMessage.setHorizontalAlignment(JLabel.CENTER);
		errorMessage.setVerticalAlignment(JLabel.TOP);
		errorMessage.setText("testingtesing");
		errorMessage.setVisible(false);

		// adding comps
		mainFrame.add(exitButton);
		mainFrame.add(minimizeButton);
		mainFrame.add(title);
		mainFrame.add(rightPanel);
		mainFrame.add(leftPanel);
		leftPanel.add(leftIndicator);
		leftPanel.add(input);
		rightPanel.add(rightIndicator);
		rightPanel.add(output);
		mainFrame.add(statusButton);
		rightPanel.add(errorScreen);
		rightPanel.add(errorMessage);

	}

	
}
