import java.awt.EventQueue;

/**
 * @author Tushar Anand
 * This class launches the application
 *
 */
public class TravelCitiesMain {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new InitializeFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
}
