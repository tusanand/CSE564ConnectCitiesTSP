import java.util.Timer;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MessageDialog implements MessageDialogInterface {
	private JDialog messageDialog;
	private JFrame frame;
	
	public MessageDialog(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * This method shows a pop-up with messages
	 * 
	 * @param message
	 */
	public void showMessage(String[] messages, boolean... timer) {
		this.messageDialog = new JDialog(this.frame, "Message");
		if (this.messageDialog != null) {
			this.messageDialog.dispose();
		}
		this.messageDialog.setSize(350, 200);
		this.messageDialog.setResizable(false);
		this.messageDialog.setVisible(true);
		this.messageDialog.setLocationRelativeTo(this.frame);
		
		int y = 20;
		for(String message: messages) {
			JLabel messageLabel = new JLabel("<html>"+ message +"</html>", SwingConstants.CENTER);
			messageLabel.setBounds(20, y, 150, 50);
			y+=20;
			this.messageDialog.add(messageLabel);
		}
		if(timer.length == 0) {
			this.startTimer(3000, messageDialog);
		}
	}
	
	private void startTimer(int millis, JDialog messageDialog) {
		Timer timer = new java.util.Timer();
		timer.schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				messageDialog.dispose();
				timer.cancel();
			}
		}, millis);
	}
}
