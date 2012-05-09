import java.util.ArrayList;
import java.util.ResourceBundle;

import com.trolltech.qt.gui.QGridLayout;
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
		
		this.populateTable(tableTop, gb.getTopGrid());
		this.populateTable(tableBottom, gb.getBottomGrid());
		
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
		System.out.printf("this row %d and this col %d.\n", row, col);
	}
	
	private void populateTable(QTableWidget table, Grid grid){
		int width = grid.getWidth();
		int height = grid.getHeight();
		IGridCell[][] gridCells = grid.getGrid();
		
		for(int r = 0; r < height; r++){
			for(int c = 0; c < width; c++){
				QTableWidgetItem item = new QTableWidgetItem(gridCells[r][c].toString());
				table.setItem(r, c, item);
			}
		}
	}
}