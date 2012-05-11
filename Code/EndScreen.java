import java.util.ResourceBundle;

import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QAbstractItemView.EditTrigger;
import com.trolltech.qt.gui.QAbstractItemView.SelectionMode;


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
		QLabel lossMessage = new QLabel(this.bundle.getString("lossMessage"));
		QPushButton playAgain = new QPushButton(this.bundle.getString("playAgain"));
		QPushButton exit = new QPushButton(this.bundle.getString("endGame"));
		
		QGridLayout widgetLayout = new QGridLayout();
		
		widgetLayout.addWidget(playAgain,2,0);
		widgetLayout.addWidget(exit,2,2);
		
		if(this.win){
			widgetLayout.addWidget(winMessage,1,1);
		}
		else{
			widgetLayout.addWidget(lossMessage,1,1);
		}
		
		playAgain.clicked.connect(this.parent, "showStartScreen()");
		exit.clicked.connect(this, "exitGame()");
		
		this.setLayout(widgetLayout);
		
		this.show();
	}
	
	
	
	public void exitGame(){
		this.parent.close();
	}
}
