
import java.util.ResourceBundle;

import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;


public class PlaceShipScreen extends QWidget {
	
	QWidget parent;
	ResourceBundle bundle;
	GameBoard gb;
	
	public PlaceShipScreen(QWidget parent, ResourceBundle bundle, GameBoard gb) {
		super(parent);
		this.bundle = bundle;
		this.parent = parent;
		this.parent.setWindowTitle(bundle.getString("placeShipScreen"));
		
		this.gb = gb;
		
		QTableWidget table = createTable();
		
		QGridLayout widgetLayout = new QGridLayout();
		widgetLayout.addWidget(table, 1, 1, 1, 2);
		
		QPushButton back = new QPushButton(bundle.getString("back"));
		QPushButton play = new QPushButton(bundle.getString("play"));
		
		widgetLayout.addWidget(back, 2, 1);
		widgetLayout.addWidget(play, 2, 2);
		
		System.out.println(widgetLayout.columnCount());
		System.out.println(widgetLayout.rowCount());
		
		back.clicked.connect(this, "showSetupScreen2()");
		play.clicked.connect(this, "showBoardScreen()");
		
		this.setLayout(widgetLayout);
		
		this.show();
	}
	
	public QTableWidget createTable() {
		QTableWidget table = new QTableWidget(this.gb.getHeight(), gb.getWidth());
		for (int i = 0; i < table.columnCount(); i++) {
			table.setColumnWidth(i, (int)(780.0/this.gb.getWidth()));
		}
		for (int i = 0; i < table.rowCount(); i++) {
			table.setRowHeight(i, (int)(708.0/this.gb.getHeight()));
		}
		return table;
	}
	
	public void showBoardScreen(){
		((GameStarter) parent).showBoardScreen(gb);
	}
	
	public void showSetupScreen2(){
		((GameStarter) parent).showSetupScreen2(gb);
	}
}
