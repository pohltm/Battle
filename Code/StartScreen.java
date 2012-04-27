import java.util.Locale;
import java.util.ResourceBundle;

import com.trolltech.qt.core.Qt.AlignmentFlag;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QFont;
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
		this.parent.setWindowTitle(bundle.getString("startScreen"));
		
		QWidget title = createTitle();
		
		QMenuBar menu = createMenu();
		
		QVBoxLayout widgetLayout = new QVBoxLayout();
		widgetLayout.setMargin(0);
		widgetLayout.addWidget(menu);
		widgetLayout.addWidget(title);
		
		this.setLayout(widgetLayout);
		
		this.show();
	}
	
	public QMenuBar createMenu() {
		QMenuBar menu = new QMenuBar();
		
		QMenu edit = new QMenu(bundle.getString("edit"));
		QMenu language = new QMenu(bundle.getString("changeLanguage"));
		
		QAction englishAct = new QAction(bundle.getString("english"), menu);
		englishAct.setShortcut("Ctrl+E");
		englishAct.setStatusTip(bundle.getString("changeToEnglish"));
		englishAct.triggered.connect(this, "english()");
		
		QAction germanAct = new QAction(bundle.getString("german"), menu);
		germanAct.setShortcut("Ctrl+G");
		germanAct.setStatusTip(bundle.getString("changeToGerman"));
		germanAct.triggered.connect(this, "german()");
		
		language.addAction(englishAct);
		language.addAction(germanAct);
		edit.addMenu(language);
		
		QMenu help = new QMenu(bundle.getString("help"));
		
		QAction helpAct = new QAction(bundle.getString("help"), help);
		helpAct.setShortcut("Ctrl+H");
		helpAct.triggered.connect(this, "help()");
		
		QAction aboutAct = new QAction(bundle.getString("about"), help);
		aboutAct.setShortcut("Ctrl+A");
		aboutAct.triggered.connect(this, "about()");
		
		help.addAction(helpAct);
		help.addAction(aboutAct);
		
		menu.addMenu(edit);
		menu.addMenu(help);
		
		return menu;
	}
	
	@SuppressWarnings("unused")
	private void english() {
		ResourceBundle newBundle = ResourceBundle.getBundle("BattleshipBundle", new Locale("en", "US"));
		((GameStarter) this.parent).setCentralWidget(new StartScreen(this.parent, newBundle));
	}
	
	@SuppressWarnings("unused")
	private void german() {
		ResourceBundle newBundle = ResourceBundle.getBundle("BattleshipBundle", new Locale("de", "DE"));
		((GameStarter) this.parent).setCentralWidget(new StartScreen(this.parent, newBundle));
	}
	
	@SuppressWarnings("unused")
	private void help() {
		QMessageBox helpBox = new QMessageBox(this);
		helpBox.setWindowTitle(bundle.getString("help"));
		helpBox.setText(bundle.getString("aboutGoal") + bundle.getString("aboutSetup") + bundle.getString("aboutPlay") + bundle.getString("aboutVictory"));
		helpBox.show();
	}
	
	@SuppressWarnings("unused")
	private void about() {
		QMessageBox aboutBox = new QMessageBox(this);
		aboutBox.setWindowTitle(bundle.getString("about"));
		aboutBox.setText(bundle.getString("applicationInfo"));
		aboutBox.show();
	}
	
	public QWidget createTitle() {
		QWidget title = new QWidget(this);
		QFont font = new QFont("Kristen ITC", 12);
		
		QLabel battleship = new QLabel(bundle.getString("battleship"));
		battleship.setFont(new QFont("Kristen ITC", 75));
		battleship.setAlignment(AlignmentFlag.AlignCenter);
		QPushButton start = new QPushButton(bundle.getString("start"));
		start.setFont(font);
		
		QVBoxLayout titleLayout = new QVBoxLayout();
		titleLayout.addWidget(battleship);
		titleLayout.addWidget(start);
		
		start.clicked.connect(this.parent, "showSetupScreen1()");
		
		title.setLayout(titleLayout);
		
		return title;
	}
}
