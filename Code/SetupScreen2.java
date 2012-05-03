import java.util.ResourceBundle;

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
	ResourceBundle bundle;
	GameBoard gb;
	
	public SetupScreen2(QWidget parent, ResourceBundle bundle, GameBoard gb) {
		super(parent);
		this.bundle = bundle;
		this.parent = parent;
		this.parent.setWindowTitle(bundle.getString("setupScreen") + "2");
		
		this.gb = gb;
		
		QWidget menu = createMenu();
		
		QVBoxLayout widgetLayout = new QVBoxLayout();
		widgetLayout.addWidget(menu);
		
		this.setLayout(widgetLayout);
		
		this.show();
	}
	
	public QWidget createMenu() {
		QWidget menu = new QWidget(this);
		
		QGridLayout menuLayout = new QGridLayout();
		QValidator intValidator = new QIntValidator(this);
		
		for(int i = 0; i < gb.getNumberOfShips(); i++) {
			QLabel shipLength = new QLabel(bundle.getString("shipLength") + (i + 1) + ": ");
			QLineEdit length = new QLineEdit("2");
			length.setValidator(intValidator);
			menuLayout.addWidget(shipLength, i + 1, 1);
			menuLayout.addWidget(length, i + 1, 2);
		}
		
		QPushButton back = new QPushButton(bundle.getString("back"));
		QPushButton play = new QPushButton(bundle.getString("play"));
		
		menuLayout.addWidget(back, gb.getNumberOfShips() + 1, 1);
		menuLayout.addWidget(play, gb.getNumberOfShips() + 1, 2);
		
		back.clicked.connect(this.parent, "showSetupScreen1()");
		play.clicked.connect(this, "showPlaceShipScreen()");
		
		menu.setLayout(menuLayout);
		
		return menu;
	}
	
	public void showPlaceShipScreen(){
		((GameStarter) this.parent).showPlaceShipScreen(gb);
	}
}

