import java.util.Locale;
import java.util.ResourceBundle;

import com.trolltech.qt.core.Qt.AlignmentFlag;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QMenuBar;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;


public class StartScreen extends QWidget {
	QWidget parent;
	ResourceBundle bundle;
	
	public StartScreen(QWidget parent, ResourceBundle bundle) {
		super(parent);
		this.bundle = bundle;
		this.parent = parent;
		this.parent.setWindowTitle(this.bundle.getString("startScreen"));
		
		QWidget title = createTitle();
		
		QMenuBar menu = createMenu();
		
		QVBoxLayout widgetLayout = new QVBoxLayout();
		widgetLayout.setMargin(0);
		widgetLayout.addWidget(menu);
		widgetLayout.addWidget(title);
		
		this.setLayout(widgetLayout);
		
		this.show();
	}
	
	public QWidget createTitle() {
		QWidget title = new QWidget(this);
		
		QLabel battleship = new QLabel(this.bundle.getString("battleship"));
		battleship.setObjectName("mainTitle");
		battleship.setAlignment(AlignmentFlag.AlignCenter);
		QPushButton start = new QPushButton(this.bundle.getString("start"));
		
		QVBoxLayout titleLayout = new QVBoxLayout();
		titleLayout.addWidget(battleship);
		titleLayout.addWidget(start);
		
		start.clicked.connect(this.parent, "showSetupScreen1()");
		
		title.setLayout(titleLayout);
		
		return title;
	}
	
	public QMenuBar createMenu() {
		QMenuBar menu = new QMenuBar();
		
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
		
		menu.addMenu(edit);
		menu.addMenu(help);
		
		return menu;
	}
	
	@SuppressWarnings("unused")
	private void switchToEnglish() {
		this.bundle = ResourceBundle.getBundle("BattleshipBundle", new Locale("en", "US"));
		GameStarter.updateBundle(this.bundle);
		((GameStarter) this.parent).setCentralWidget(new StartScreen(this.parent, this.bundle));
	}
	
	@SuppressWarnings("unused")
	private void switchToGerman() {
		this.bundle = ResourceBundle.getBundle("BattleshipBundle", new Locale("de", "DE"));
		GameStarter.updateBundle(this.bundle);
		((GameStarter) this.parent).setCentralWidget(new StartScreen(this.parent, this.bundle));
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
