import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;


public class StartScreen extends QWidget {
	QWidget parent;
	
	public StartScreen(QWidget parent) {
		super(parent);
		this.parent = parent;
		this.parent.setWindowTitle("Start Screen");
		
		QWidget title = createTitle();
		
		QVBoxLayout widgetLayout = new QVBoxLayout();
		widgetLayout.addWidget(title);
		
		this.setLayout(widgetLayout);
		
		this.show();
	}
	
	public QWidget createTitle() {
		QWidget title = new QWidget(this);
		QFont font = new QFont("Purisa", 12);
		
		QLabel battleship = new QLabel("BATTLESHIP");
		battleship.setFont(new QFont("Kristen ITC", 75));
		QPushButton start = new QPushButton("Start");
		start.setFont(font);
		
		QVBoxLayout titleLayout = new QVBoxLayout();
		titleLayout.addWidget(battleship);
		titleLayout.addWidget(start);
		
		start.clicked.connect(this.parent, "showSetupScreen1()");
		
		title.setLayout(titleLayout);
		
		return title;
	}
}
