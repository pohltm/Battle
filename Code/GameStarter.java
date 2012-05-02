import java.util.Locale;
import java.util.ResourceBundle;

import com.trolltech.qt.core.QFile;
import com.trolltech.qt.core.QIODevice.OpenModeFlag;
import com.trolltech.qt.core.QSize;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QMainWindow;


/**
 * TODO Put here a description of what this class does.
 *
 * @author plungicb.
 *         Created Apr 19, 2012.
 */
public class GameStarter extends QMainWindow {
	
	public static ResourceBundle bundle;
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		QApplication.initialize(args);
		QMainWindow mainWindow = new GameStarter();
		mainWindow.setFixedSize(new QSize(1000, 800));
		
		mainWindow.show();
		QApplication.exec();
	}
	
	public GameStarter() {
		super();
		
		Locale currentLocale;
		currentLocale = new Locale("en", "US");
		
		bundle = setupBundle(currentLocale);
		styleSetup("mainStyleSheet");
		
		this.showStartScreen();
	}
	
	public static ResourceBundle setupBundle(Locale currentLocale) {
		return ResourceBundle.getBundle("BattleshipBundle", currentLocale);
	}
	
	public void styleSetup(String ssFile) {
		String fullFile = "classpath:" + ssFile + ".qss";
		QFile file = new QFile(fullFile);
		
		file.open(OpenModeFlag.ReadOnly);
		String styleSheet = file.readAll().toString();
		this.setStyleSheet(styleSheet);
		file.close();
	}
	
	public void showStartScreen() {
		this.setCentralWidget(new StartScreen(this, bundle));
	}
	
	public void showSetupScreen1() {
		this.setCentralWidget(new SetupScreen1(this, bundle));
	}
	
	public void showSetupScreen2(GameBoard gb) {
		this.setCentralWidget(new SetupScreen2(this, bundle, gb));
	}
	
	public void showplaceShipScreen(GameBoard gb) {
		this.setCentralWidget(new PlaceShipScreen(this, bundle, gb));
	}
	
	public void showBoardScreen(GameBoard gb) {
		this.setCentralWidget(new BoardScreen(this, bundle, gb));
	}
	
	public static void updateBundle(ResourceBundle newBundle) {
		bundle = newBundle;
	}
}
