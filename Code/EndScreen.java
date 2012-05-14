import java.util.ResourceBundle;

import com.trolltech.qt.core.Qt.AlignmentFlag;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QWidget;


public class EndScreen extends QWidget {
	
	private QWidget parent;
	private ResourceBundle bundle;
	private boolean win;
	
	public EndScreen(QWidget parent, ResourceBundle bundle, boolean win) {
		super(parent);
		this.bundle = bundle;
		this.parent = parent;
		this.win = win;
		this.parent.setWindowTitle(bundle.getString("endScreen"));
		
		
		QLabel winMessage = new QLabel(this.bundle.getString("winMessage"));
		winMessage.setAlignment(AlignmentFlag.AlignCenter);
		QLabel lossMessage = new QLabel(this.bundle.getString("lossMessage"));
		lossMessage.setAlignment(AlignmentFlag.AlignCenter);
		QPushButton playAgain = new QPushButton(this.bundle.getString("playAgain"));
		QPushButton exit = new QPushButton(this.bundle.getString("endGame"));
		
		QGridLayout widgetLayout = new QGridLayout();
		
		widgetLayout.addWidget(playAgain,2,0);
		widgetLayout.addWidget(exit,2,2);
		
		if (this.win) {
			widgetLayout.addWidget(winMessage,1,1);
		} else {
			widgetLayout.addWidget(lossMessage,1,1);
		}
		
		playAgain.clicked.connect(this.parent, "showStartScreen()");
		exit.clicked.connect(this, "exitGame()");
		
		this.setLayout(widgetLayout);
		this.show();
	}
	
	public void exitGame() {
		this.parent.close();
	}
}
