
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.trolltech.qt.core.Qt.ScrollBarPolicy;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QCheckBox;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QAbstractItemView.EditTrigger;
import com.trolltech.qt.gui.QAbstractItemView.SelectionMode;


public class PlaceShipScreen extends QWidget {
	
	QWidget parent;
	ResourceBundle bundle;
	GameBoard gb;
	int shipNum;
	int[] lengths;
	boolean horiz;
	ArrayList<Ship> ships;
	QTableWidget table;
	
	public PlaceShipScreen(QWidget parent, ResourceBundle bundle, GameBoard gb, int[] lengths) {
		super(parent);
		this.shipNum = 0;
		this.bundle = bundle;
		this.lengths = lengths;
		this.parent = parent;
		this.horiz = false;
		this.parent.setWindowTitle(bundle.getString("placeShipScreen"));
		ships = new ArrayList<Ship>();
		
		this.gb = gb;
		
		table = createTable();
		
		QGridLayout widgetLayout = new QGridLayout();
		widgetLayout.addWidget(table, 1, 2);
		
		QPushButton back = new QPushButton(bundle.getString("back"));
		QPushButton play = new QPushButton(bundle.getString("play"));
		
		QCheckBox horizontal = new QCheckBox(bundle.getString("horizontal"));
		
		widgetLayout.addWidget(back, 2, 1);
		widgetLayout.addWidget(play, 2, 3);
		widgetLayout.addWidget(horizontal, 1,3);
		
		back.clicked.connect(this, "showSetupScreen2()");
		play.clicked.connect(this, "play()");
		horizontal.clicked.connect(this, "setHorizontal()");
		
		this.setLayout(widgetLayout);
		int tableWidth = (table.columnWidth(0) * table.columnCount()) + table.verticalHeader().width();
		int tableHeight = (table.rowHeight(0) * table.rowCount()) + table.horizontalHeader().height();
		table.setHorizontalScrollBarPolicy(ScrollBarPolicy.ScrollBarAlwaysOff);
		table.setVerticalScrollBarPolicy(ScrollBarPolicy.ScrollBarAlwaysOff);
		table.setFixedSize(tableWidth, tableHeight);
		
		this.show();
	}
	
	public QTableWidget createTable() {
		QTableWidget table = new QTableWidget(this.gb.getHeight(), gb.getWidth());
		double tableSize = this.parent.height() - 300;
		
		for (int i = 0; i < table.columnCount(); i++) {
			table.setColumnWidth(i, (int)(tableSize/((double)this.gb.getWidth())));
		}
		
		for (int i = 0; i < table.rowCount(); i++) {
			table.setRowHeight(i, (int)(tableSize/((double)this.gb.getHeight())));
		}
		
		table.setEditTriggers(EditTrigger.NoEditTriggers);
		table.setSelectionMode(SelectionMode.SingleSelection);
		table.cellClicked.connect(this, "place1(int,int)");
		
		return table;
	}
	
	public void setHorizontal() {
		horiz = !horiz;
	}
	
	public void place1(int r, int c) {
		try{
			ships.add(new Ship(r,c,lengths[shipNum],horiz));
			if (horiz) {
				for (int col = c; col < c+lengths[shipNum];col++) {
					QTableWidgetItem item = new QTableWidgetItem("S");
					item.setBackground(new QBrush(QColor.gray));
					table.setItem(r, col, item);
				}
			} else {
				for (int row = r; row < r+lengths[shipNum]; row++) {
					QTableWidgetItem item = new QTableWidgetItem("S");
					item.setBackground(new QBrush(QColor.gray));
					table.setItem(row, c, item);
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			QMessageBox overplay = new QMessageBox();
			overplay.setWindowTitle(bundle.getString("placeTooManyTitle"));
			overplay.setText(bundle.getString("placeTooManyText"));
			overplay.exec();
		}
		if (shipNum<lengths.length) {
			shipNum++;
		}
	}
	
	public void play() {
		if (gb.checkAndPlaceShips(ships, "bottom")) {
			((GameStarter) parent).showBoardScreen(gb, lengths);
		} else {
			QMessageBox mess = new QMessageBox();
			mess.setWindowTitle(bundle.getString("placeErrorTitle"));
			mess.setText(bundle.getString("placeErrorText"));
			mess.exec();
			((GameStarter) this.parent).showPlaceShipScreen(gb,lengths);
		}
	}
	
	public void showSetupScreen2() {
		((GameStarter) parent).showSetupScreen2(gb);
	}
}
