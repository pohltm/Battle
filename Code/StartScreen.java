import java.util.ResourceBundle;

import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QMenuBar;
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
		
		QMenuBar menu = new QMenuBar();
		QMenu edit = new QMenu(bundle.getString("edit"));
		edit.addAction(bundle.getString("changeLanguage"));
		QMenu help = new QMenu(bundle.getString("help"));
		help.addAction(bundle.getString("help"));
		menu.addMenu(edit);
		menu.addMenu(help);
		
		QVBoxLayout widgetLayout = new QVBoxLayout();
		widgetLayout.setMargin(0);
		widgetLayout.addWidget(menu);
		widgetLayout.addWidget(title);
		
		this.setLayout(widgetLayout);
		
		this.show();
	}
	
	public QWidget createTitle() {
		QWidget title = new QWidget(this);
		QFont font = new QFont("Purisa", 12);
		
		QLabel battleship = new QLabel(bundle.getString("battleship"));
		battleship.setFont(new QFont("Kristen ITC", 75));
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
