import java.util.ResourceBundle;

import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QIntValidator;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QValidator;
import com.trolltech.qt.gui.QWidget;

public class SetupScreen1 extends QWidget {
	
	QWidget parent;
	ResourceBundle bundle;
	QLineEdit boardWidth;
	QLineEdit boardHeight;
	QLineEdit shipNumber;
	
	public SetupScreen1(QWidget parent, ResourceBundle bundle) {
		super(parent);
		this.bundle = bundle;
		this.parent = parent;
		this.parent.setWindowTitle(bundle.getString("setupScreen") + "1");
		
		MenuBar menuBar = new MenuBar(this.parent, this.bundle, "setup1", null, null, false);
		QWidget menu = createMenu();
		
		QVBoxLayout widgetLayout = new QVBoxLayout();
		widgetLayout.setMargin(0);
		widgetLayout.addWidget(menuBar);
		widgetLayout.addWidget(menu);
		
		this.setLayout(widgetLayout);
		this.show();
	}
	
	private QWidget createMenu() {
		QWidget menu = new QWidget(this);
		
		QValidator intValidator = new QIntValidator(this);
		
		QLabel boardSize = new QLabel(bundle.getString("enterBoardSize"));
		this.boardWidth = new QLineEdit("10");
		boardWidth.setValidator(intValidator);
		this.boardHeight = new QLineEdit("10");
		boardHeight.setValidator(intValidator);
		QLabel numberShips = new QLabel(bundle.getString("enterNumShips"));
		this.shipNumber = new QLineEdit("5");
		shipNumber.setValidator(intValidator);
		
		QPushButton back = new QPushButton(bundle.getString("back"));
		QPushButton next = new QPushButton(bundle.getString("next"));
		
		QGridLayout menuLayout = new QGridLayout();
		menuLayout.addWidget(boardSize, 1, 1);
		menuLayout.addWidget(boardWidth, 1, 2);
		menuLayout.addWidget(boardHeight, 1, 3);
		menuLayout.addWidget(numberShips, 2, 1);
		menuLayout.addWidget(shipNumber, 2, 2, 1, 2);
		menuLayout.addWidget(back, 3, 1);
		menuLayout.addWidget(next, 3, 3);

		back.clicked.connect(this.parent, "showStartScreen()");
		next.clicked.connect(this, "showSetupScreen2()");
		
		menu.setLayout(menuLayout);
		return menu;
	}
	
	@SuppressWarnings("unused")
	private void showSetupScreen2() {
		((GameStarter) this.parent).showSetupScreen2(new GameBoard (new Integer(boardWidth.text()), new Integer(boardHeight.text()), new Integer(shipNumber.text())));
	}
}
