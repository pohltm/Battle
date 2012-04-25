import java.util.Locale;
import java.util.ResourceBundle;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QMenuBar;


/**
 * TODO Put here a description of what this class does.
 *
 * @author plungicb.
 *         Created Apr 19, 2012.
 */
public class GameStarter extends QMainWindow {
	
	ResourceBundle bundle;

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
		
		Locale currentLocale;
//		currentLocale = new Locale("en", "US");
		currentLocale = new Locale("de", "DE");
		
		bundle = setupBundle(currentLocale);
		
		this.showStartScreen();
	}
	
	public static ResourceBundle setupBundle(Locale currentLocale) {
		return ResourceBundle.getBundle("BattleshipBundle", currentLocale);
	}
	
	public void showStartScreen() {
		this.setCentralWidget(new StartScreen(this, bundle));
	}
	
	public void showSetupScreen1() {
		this.setCentralWidget(new SetupScreen1(this, bundle));
	}
	
	public void showSetupScreen2() {
		this.setCentralWidget(new SetupScreen2(this, bundle));
	}
	
	public void showBoardScreen() {
		this.setCentralWidget(new BoardScreen(this, bundle));
	}
}
