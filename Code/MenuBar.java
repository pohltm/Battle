import java.util.Locale;
import java.util.ResourceBundle;

import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QMenuBar;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QWidget;


public class MenuBar extends QMenuBar {
	
	ResourceBundle bundle;
	QWidget parent;
	String screen;
	GameBoard gb;
	int[] lengths;
	boolean win;

	public MenuBar(QWidget parent, ResourceBundle bundle, String screen, GameBoard gb, int[] lengths, boolean win) {
		this.parent = parent;
		this.bundle = bundle;
		this.screen = screen;
		this.gb = gb;
		this.lengths = lengths;
		this.win = win;
		
		QMenu edit = new QMenu(this.bundle.getString("edit"));
		QMenu language = new QMenu(this.bundle.getString("changeLanguage"));
		
		QAction englishAct = new QAction(this.bundle.getString("english"), language);
		englishAct.setShortcut("Ctrl+E");
		englishAct.setStatusTip(this.bundle.getString("changeToEnglish"));
		englishAct.triggered.connect(this, "switchToEnglish()");
		
		QAction germanAct = new QAction(this.bundle.getString("german"), language);
		germanAct.setShortcut("Ctrl+G");
		germanAct.setStatusTip(this.bundle.getString("changeToGerman"));
		germanAct.triggered.connect(this, "switchToGerman()");
		
		language.addAction(englishAct);
		language.addAction(germanAct);
		edit.addMenu(language);
		
		QMenu help = new QMenu(this.bundle.getString("help"));
		QMenu howToPlay = new QMenu(this.bundle.getString("howToPlay"));
		
		QAction goalAct = new QAction(this.bundle.getString("goal"), howToPlay);
		goalAct.triggered.connect(this, "explainGoal()");
		
		QAction setupAct = new QAction(this.bundle.getString("setup"), howToPlay);
		setupAct.triggered.connect(this, "explainSetup()");
		
		QAction playAct = new QAction(this.bundle.getString("play"), howToPlay);
		playAct.triggered.connect(this, "explainPlay()");
		
		QAction victoryAct = new QAction(this.bundle.getString("victory"), howToPlay);
		victoryAct.triggered.connect(this, "explainVictory()");
		
		QAction aboutAct = new QAction(this.bundle.getString("about"), help);
		aboutAct.setShortcut("Ctrl+A");
		aboutAct.triggered.connect(this, "about()");
		
		howToPlay.addAction(goalAct);
		howToPlay.addAction(setupAct);
		howToPlay.addAction(playAct);
		howToPlay.addAction(victoryAct);
		help.addMenu(howToPlay);
		help.addAction(aboutAct);
		
		this.addMenu(edit);
		this.addMenu(help);
	}
	
	@SuppressWarnings("unused")
	private void switchToEnglish() {
		this.bundle = ResourceBundle.getBundle("BattleshipBundle", new Locale("en", "US"));
		GameStarter.updateBundle(this.bundle);
		if (this.screen.equals("start")) {
			((GameStarter) this.parent).setCentralWidget(new StartScreen(this.parent, this.bundle));	
		} else if (this.screen.equals("setup1")) {
			((GameStarter) this.parent).setCentralWidget(new SetupScreen1(this.parent, this.bundle));
		} else if (this.screen.equals("setup2")) {
			((GameStarter) this.parent).setCentralWidget(new SetupScreen2(this.parent, this.bundle, this.gb));
		} else if (this.screen.equals("placeShip")) {
			((GameStarter) this.parent).setCentralWidget(new PlaceShipScreen(this.parent, this.bundle, this.gb, this.lengths));
		} else if (this.screen.equals("board")) {
			((GameStarter) this.parent).setCentralWidget(new BoardScreen(this.parent, this.bundle, this.gb, this.lengths));
		} else if (this.screen.equals("end")) {
			((GameStarter) this.parent).setCentralWidget(new EndScreen(this.parent, this.bundle, this.win));
		} else {
			System.out.println("An error occurred.");
		}
	}
	
	@SuppressWarnings("unused")
	private void switchToGerman() {
		this.bundle = ResourceBundle.getBundle("BattleshipBundle", new Locale("de", "DE"));
		GameStarter.updateBundle(this.bundle);
		if (this.screen.equals("start")) {
			((GameStarter) this.parent).setCentralWidget(new StartScreen(this.parent, this.bundle));	
		} else if (this.screen.equals("setup1")) {
			((GameStarter) this.parent).setCentralWidget(new SetupScreen1(this.parent, this.bundle));
		} else if (this.screen.equals("setup2")) {
			((GameStarter) this.parent).setCentralWidget(new SetupScreen2(this.parent, this.bundle, this.gb));
		} else if (this.screen.equals("placeShip")) {
			((GameStarter) this.parent).setCentralWidget(new PlaceShipScreen(this.parent, this.bundle, this.gb, this.lengths));
		} else if (this.screen.equals("board")) {
			((GameStarter) this.parent).setCentralWidget(new BoardScreen(this.parent, this.bundle, this.gb, this.lengths));
		} else if (this.screen.equals("end")) {
			((GameStarter) this.parent).setCentralWidget(new EndScreen(this.parent, this.bundle, this.win));
		} else {
			System.out.println("An error occurred.");
		}
	}
	
	@SuppressWarnings("unused")
	private void explainGoal() {
		QMessageBox goalBox = new QMessageBox(this);
		goalBox.setWindowTitle(this.bundle.getString("goal"));
		goalBox.setText(this.bundle.getString("aboutGoal"));
		goalBox.show();
	}
	
	@SuppressWarnings("unused")
	private void explainSetup() {
		QMessageBox setupBox = new QMessageBox(this);
		setupBox.setWindowTitle(this.bundle.getString("setup"));
		setupBox.setText(this.bundle.getString("aboutSetup"));
		setupBox.show();
	}
	
	@SuppressWarnings("unused")
	private void explainPlay() {
		QMessageBox playBox = new QMessageBox(this);
		playBox.setWindowTitle(this.bundle.getString("play"));
		playBox.setText(this.bundle.getString("aboutPlay"));
		playBox.show();
	}
	
	@SuppressWarnings("unused")
	private void explainVictory() {
		QMessageBox victoryBox = new QMessageBox(this);
		victoryBox.setWindowTitle(this.bundle.getString("victory"));
		victoryBox.setText(this.bundle.getString("aboutVictory"));
		victoryBox.show();
	}
	
	@SuppressWarnings("unused")
	private void about() {
		QMessageBox aboutBox = new QMessageBox(this);
		aboutBox.setWindowTitle(this.bundle.getString("about"));
		aboutBox.setText(this.bundle.getString("applicationInfo"));
		aboutBox.show();
	}
}
