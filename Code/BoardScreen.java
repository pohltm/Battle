import java.util.ArrayList;
import java.util.ResourceBundle;

import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QAbstractItemView.EditTrigger;
import com.trolltech.qt.gui.QAbstractItemView.SelectionMode;


public class BoardScreen extends QWidget {
	
	QWidget parent;
	ResourceBundle bundle;
	GameBoard gb;
	QTableWidget tableTop;
	
	public BoardScreen(QWidget parent, ResourceBundle bundle, GameBoard gb, int[] lengths) {
		super(parent);
		this.bundle = bundle;
		this.parent = parent;
		this.parent.setWindowTitle(bundle.getString("boardScreen"));
		
		this.gb = gb;
		
		this.tableTop = createTable();
		tableTop.setFixedSize(390,390);
		QTableWidget tableBottom = createTable();
		tableBottom.setFixedSize(390,390);
		
		//TODO: THIS IS A TEST, REMOVE THIS WHEN DONE TESTING
//		ArrayList<Ship> ships1 = new ArrayList<Ship>();
//		ships1.add(new Ship(3,3,3,true));
//		ships1.add(new Ship(4,6,2,true));
//		ships1.add(new Ship(1,1,5,false));
//		gb.checkAndPlaceShips(ships1, "top");
		
		ArrayList<Ship> ships2 = new ArrayList<Ship>();
		ships2.add(new Ship(4,4,3,false));
		ships2.add(new Ship(7,6,2,true));
		ships2.add(new Ship(1,1,5,true));
		gb.checkAndPlaceShips(ships2, "bottom");
		
		AI ai =  new AI(this.gb,lengths);
		ai.placeShips();
		
		this.populateTopTable(tableTop);
		this.populateBottomTable(tableBottom);
		
		QGridLayout widgetLayout = new QGridLayout();
		
		widgetLayout.addWidget(tableTop,1,2);
		widgetLayout.addWidget(tableBottom,2,2);
		
		this.setLayout(widgetLayout);
		
		this.show();
	}
	
	public QTableWidget createTable() {
		QTableWidget table = new QTableWidget(this.gb.getHeight(), gb.getWidth());
		for (int i = 0; i < table.columnCount(); i++) {
			table.setColumnWidth(i, (int)(365.0/((double)(this.gb.getWidth()))));
		}
		for (int i = 0; i < table.rowCount(); i++) {
			table.setRowHeight(i, (int)(365.0/((double)(this.gb.getHeight()))));
		}
		table.setEditTriggers(EditTrigger.NoEditTriggers);
		table.setSelectionMode(SelectionMode.SingleSelection);
		table.cellClicked.connect(this, "shotFired(int, int)");
		return table;
	}
	
	public void shotFired(int row, int col) {
		if(gb.shootTop(row, col)){
			QMessageBox sunkShip = new QMessageBox();
			sunkShip.setWindowTitle("You sunk a ship!");
			sunkShip.setText("You sunk a ship! Good job!");
			sunkShip.exec();
		}
		populateTopTable(tableTop);
		
	}
	
	private void populateTopTable(QTableWidget table){
		int width = gb.getTopGrid().getWidth();
		int height = gb.getTopGrid().getHeight();
		IGridCell[][] gridCells = gb.getTopGrid().getGrid();
		
		for(int r = 0; r < height; r++){
			for(int c = 0; c < width; c++){
				String val = gridCells[r][c] instanceof ShipCell ? new Empty().toString() : gridCells[r][c].toString();
				QTableWidgetItem item = new QTableWidgetItem(val);
				if (val.equals("H")) {
					item.setBackground(new QBrush(QColor.red));
				} else if (val.equals("M")) {
					item.setBackground(new QBrush(QColor.white));
				} else {
					item.setBackground(new QBrush(new QColor(0, 154, 255)));
				}
				table.setItem(r, c, item);
			}
		}
	}
	
	private void populateBottomTable(QTableWidget table){
		int width = gb.getBottomGrid().getWidth();
		int height = gb.getBottomGrid().getHeight();
		IGridCell[][] gridCells = gb.getBottomGrid().getGrid();
		
		for(int r = 0; r < height; r++){
			for(int c = 0; c < width; c++){
				QTableWidgetItem item = new QTableWidgetItem(gridCells[r][c].toString());
				table.setItem(r, c, item);
			}
		}
	}
}