import java.util.ArrayList;
import java.util.ResourceBundle;

import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QPaintEvent;
import com.trolltech.qt.gui.QPainter;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;


public class BoardScreen extends QWidget {
	
	QWidget parent;
	ResourceBundle bundle;
	GameBoard gb;
	
	public BoardScreen(QWidget parent, ResourceBundle bundle, GameBoard gb) {
		super(parent);
		this.bundle = bundle;
		this.parent = parent;
		this.parent.setWindowTitle(bundle.getString("boardScreen"));
		
		this.gb = gb;
		
		QTableWidget tableTop = createTable();
		QTableWidget tableBottom = createTable();
		
		//TODO: THIS IS A TEST, REMOVE THIS WHEN DONE TESTING
		ArrayList<Ship> ships1 = new ArrayList<Ship>();
		ships1.add(new Ship(3,3,3,true));
		ships1.add(new Ship(4,6,2,true));
		ships1.add(new Ship(1,1,5,false));
		gb.checkAndPlaceShips(ships1, "top");
		
		ArrayList<Ship> ships2 = new ArrayList<Ship>();
		ships2.add(new Ship(4,4,3,false));
		ships2.add(new Ship(7,6,2,true));
		ships2.add(new Ship(1,1,5,true));
		gb.checkAndPlaceShips(ships2, "bottom");
		
		this.populateTable(tableTop, gb.getTopGrid());
		this.populateTable(tableBottom, gb.getBottomGrid());
		
		QVBoxLayout widgetLayout = new QVBoxLayout();
		
		widgetLayout.addWidget(tableTop);
		widgetLayout.addWidget(tableBottom);
		
		this.setLayout(widgetLayout);
		
		this.show();
	}
	
	public QWidget createBoard1(int width, int height) {
		QWidget board1 = new QWidget(this);
		
		QGridLayout boardLayout = new QGridLayout();
		
		board1.setLayout(boardLayout);
		
		
		return board1;
	}
	
	public QTableWidget createTable() {
		QTableWidget table = new QTableWidget(this.gb.getHeight(), gb.getWidth());
		for (int i = 0; i < table.columnCount(); i++) {
			table.setColumnWidth(i, (int)(390.0/this.gb.getWidth()));
		}
		for (int i = 0; i < table.rowCount(); i++) {
			table.setRowHeight(i, (int)(354.0/this.gb.getHeight()));
		}
		return table;
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
	
	public void paintEvent(QPaintEvent h) {
		QPainter painter = new QPainter(this);
		painter.setPen(QColor.black);
		painter.drawRect(10, 10, this.width() - 20, this.height() / 2 - 20);
		painter.setPen(QColor.blue);
		painter.drawRect(10, this.height() / 2 + 10, this.width() - 20, this.height() / 2 - 20);
	}
}