import java.util.ResourceBundle;

import com.trolltech.qt.core.Qt.AlignmentFlag;
import com.trolltech.qt.gui.QLabel;
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
		
		MenuBar menu = new MenuBar(this.parent, this.bundle, "start", null, null, false);
		
		QVBoxLayout widgetLayout = new QVBoxLayout();
		widgetLayout.setMargin(0);
		widgetLayout.addWidget(menu);
		widgetLayout.addWidget(title);
		
		this.setLayout(widgetLayout);
		this.show();
	}
	
	private QWidget createTitle() {
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
}
