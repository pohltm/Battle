import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QIntValidator;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QValidator;
import com.trolltech.qt.gui.QWidget;


public class SetupScreen2 extends QWidget {
	
	QWidget parent;
	
	public SetupScreen2(QWidget parent) {
		super(parent);
		this.parent = parent;
		this.parent.setWindowTitle("Setup Screen 2");
		
		QWidget menu = createMenu(5);
		
		QVBoxLayout widgetLayout = new QVBoxLayout();
		widgetLayout.addWidget(menu);
		
		this.setLayout(widgetLayout);
		
		this.show();
	}
	
	public QWidget createMenu(int numberShips) {
		QWidget menu = new QWidget(this);
		QFont font = new QFont("Kristen ITC", 12);
		
		QGridLayout menuLayout = new QGridLayout();
		QValidator intValidator = new QIntValidator(this);
		
		for(int i = 0; i < numberShips; i++) {
			QLabel shipLength = new QLabel("Length of Ship " + (i + 1) + " : ");
			shipLength.setFont(font);
			QLineEdit length = new QLineEdit("2");
			length.setValidator(intValidator);
			menuLayout.addWidget(shipLength, i + 1, 1);
			menuLayout.addWidget(length, i + 1, 2);
		}
		
		QPushButton back = new QPushButton("Back");
		back.setFont(font);
		QPushButton play = new QPushButton("Play");
		play.setFont(font);
		
		menuLayout.addWidget(back, numberShips + 1, 1);
		menuLayout.addWidget(play, numberShips + 1, 2);
		
		back.clicked.connect(this.parent, "showSetupScreen1()");
		play.clicked.connect(this.parent, "showBoardScreen()");
		
		menu.setLayout(menuLayout);
		
		return menu;
	}
}

