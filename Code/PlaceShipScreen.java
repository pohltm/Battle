
import java.util.List;
import java.util.ResourceBundle;

import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QAbstractItemView.EditTrigger;


public class PlaceShipScreen extends QWidget {
	
	QWidget parent;
	ResourceBundle bundle;
	GameBoard gb;
	int[] lengths;
	QTableWidget table;
	
	public PlaceShipScreen(QWidget parent, ResourceBundle bundle, GameBoard gb, int[] lengths) {
		super(parent);
		this.bundle = bundle;
		this.lengths = lengths;
		this.parent = parent;
		this.parent.setWindowTitle(bundle.getString("placeShipScreen"));
		
		this.gb = gb;
		
		this.table = createTable();
		
		QGridLayout widgetLayout = new QGridLayout();
		widgetLayout.addWidget(table, 1, 2);
		
		QPushButton back = new QPushButton(bundle.getString("back"));
		QPushButton play = new QPushButton(bundle.getString("play"));
		
		widgetLayout.addWidget(back, 2, 1);
		widgetLayout.addWidget(play, 2, 3);
		
		back.clicked.connect(this, "showSetupScreen2()");
		play.clicked.connect(this, "showBoardScreen()");
		
		this.setLayout(widgetLayout);
		table.setFixedSize(500,500);
		
		this.show();
	}
	
	public QTableWidget createTable() {
		QTableWidget table = new QTableWidget(this.gb.getHeight(), gb.getWidth());
		for (int i = 0; i < table.columnCount(); i++) {
			table.setColumnWidth(i, (int)(475.0/((double)this.gb.getWidth())));
		}
		for (int i = 0; i < table.rowCount(); i++) {
			table.setRowHeight(i, (int)(475.0/((double)this.gb.getHeight())));
		}
		table.setEditTriggers(EditTrigger.NoEditTriggers);
//		table.cellClicked.connect(this, "thisOne()");
		table.itemSelectionChanged.connect(this, "thisOne()");
		return table;
	}
	
	public void thisOne() {
		List<QTableWidgetItem> cellsSelected = this.table.selectedItems();
		System.out.println(cellsSelected.toString());
		this.table.selectedRanges();
	}
	
	public void showBoardScreen(){
		((GameStarter) parent).showBoardScreen(gb, lengths);
	}
	
	public void showSetupScreen2(){
		((GameStarter) parent).showSetupScreen2(gb);
	}
}
