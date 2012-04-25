import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QMainWindow;


/**
 * TODO Put here a description of what this class does.
 *
 * @author plungicb.
 *         Created Apr 19, 2012.
 */
public class GameStarter extends QMainWindow {

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		QApplication.initialize(args);
		QMainWindow mainWindow = new GameStarter();
		
		mainWindow.show();
		QApplication.exec();
	}
	
	public GameStarter() {
		super();
		this.showStartScreen();
	}
	
	public void showStartScreen() {
		this.setCentralWidget(new StartScreen(this));
	}
	
	public void showSetupScreen1() {
		this.setCentralWidget(new SetupScreen1(this));
	}
	
	public void showSetupScreen2() {
		this.setCentralWidget(new SetupScreen2(this));
	}
	
	public void showBoardScreen() {
		this.setCentralWidget(new BoardScreen(this));
	}
}
