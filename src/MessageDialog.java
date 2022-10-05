import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MessageDialog implements MessageDialogInterface {
	private JDialog messageDialog;
	private JFrame frame;
	
	public MessageDialog(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * This method closes the message dialog
	 * 
	 */
	private void closeMessageDialog() {
		this.messageDialog.dispose();
	}
	
	/**
	 * This method shows a pop-up with messages
	 * 
	 * @param message
	 */
	public void showMessage(String[] messages) {
		this.messageDialog = new JDialog(this.frame, "Message");
		if (this.messageDialog != null) {
			this.closeMessageDialog();
		}
		this.messageDialog.setSize(500, 200);
		this.messageDialog.setResizable(false);
		this.messageDialog.setVisible(true);
		this.messageDialog.setLocationRelativeTo(this.frame);
		
		String message = "";
		for(String msg: messages) {
			message = message + msg + "<br/>";
		}
		JLabel messageLabel = new JLabel("<html>"+ message +"</html>", SwingConstants.CENTER);
		JScrollPane scroll = new JScrollPane(messageLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.messageDialog.add(scroll);
	}
}
