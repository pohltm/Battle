import java.util.ResourceBundle;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.Qt.ScrollBarPolicy;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QPixmap;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QAbstractItemView.EditTrigger;
import com.trolltech.qt.gui.QAbstractItemView.SelectionMode;


public class BoardScreen extends QWidget {
	
	QWidget parent;
	ResourceBundle bundle;
	GameBoard gb;
	QTableWidget tableTop;
	QTableWidget tableBottom;
	AI ai;
	int[] lengths;
	
	public BoardScreen(QWidget parent, ResourceBundle bundle, GameBoard gb, int[] lengths) {
		super(parent);
		this.bundle = bundle;
		this.parent = parent;
		this.parent.setWindowTitle(bundle.getString("boardScreen"));
		this.gb = gb;
		this.lengths = lengths;
		
		MenuBar menuBar = new MenuBar(this.parent, this.bundle, "board", this.gb, this.lengths, false);
		QWidget mainWidget = createMainWidget();
		
		QVBoxLayout widgetLayout = new QVBoxLayout();
		widgetLayout.setMargin(0);
		widgetLayout.addWidget(menuBar);
		widgetLayout.addWidget(mainWidget);
		
		this.setLayout(widgetLayout);
		this.show();
	}
	
	public QWidget createMainWidget() {
		QWidget mainWidget = new QWidget();
		
		this.tableTop = createTable(true);
		int tableWidth = (tableTop.columnWidth(0) * tableTop.columnCount()) + tableTop.verticalHeader().width();
		int tableHeight = (tableTop.rowHeight(0) * tableTop.rowCount()) + tableTop.horizontalHeader().height();
		tableTop.setHorizontalScrollBarPolicy(ScrollBarPolicy.ScrollBarAlwaysOff);
		tableTop.setVerticalScrollBarPolicy(ScrollBarPolicy.ScrollBarAlwaysOff);
		tableTop.setFixedSize(tableWidth, tableHeight);
		this.tableBottom = createTable(false);
		tableBottom.setHorizontalScrollBarPolicy(ScrollBarPolicy.ScrollBarAlwaysOff);
		tableBottom.setVerticalScrollBarPolicy(ScrollBarPolicy.ScrollBarAlwaysOff);
		tableBottom.setFixedSize(tableWidth, tableHeight);
		
		ai =  new AI(this.gb, this.lengths);
		ai.placeShips();
		
		this.populateTopTable(tableTop);
		this.populateBottomTable(tableBottom);
		
		QGridLayout widgetLayout = new QGridLayout();
		
		widgetLayout.addWidget(tableTop,1,2);
		widgetLayout.addWidget(tableBottom,2,2);
		
		mainWidget.setLayout(widgetLayout);
		
		QMessageBox overkill = new QMessageBox(this);
		overkill.setWindowTitle(bundle.getString("startGameTitle"));
		overkill.setText(bundle.getString("startGameText"));
		overkill.exec();
		
		return mainWidget;
	}
	
	public QTableWidget createTable(boolean top) {
		QTableWidget table = new QTableWidget(this.gb.getHeight(), gb.getWidth());
		double tableSize = this.parent.height() / 2 - 60;
		
		for (int i = 0; i < table.columnCount(); i++) {
			table.setColumnWidth(i, (int)(tableSize/((double)(this.gb.getWidth()))));
		}
		
		for (int i = 0; i < table.rowCount(); i++) {
			table.setRowHeight(i, (int)(tableSize/((double)(this.gb.getHeight()))));
		}
		
		table.setEditTriggers(EditTrigger.NoEditTriggers);
		table.setSelectionMode(SelectionMode.SingleSelection);
		
		if(top){
			table.cellClicked.connect(this, "shotFired(int, int)");
		} else{
			table.setSelectionMode(SelectionMode.NoSelection);
		}
		
		return table;
	}
	
	public void shotFired(int row, int col) {
		if (this.gb.checkShotTop(row, col)) {
			if (gb.shootTop(row, col)) {
				if(gb.playerWon()) {
					showEndScreen(true);
				} else {
					QMessageBox sunkShip = new QMessageBox(this);
					sunkShip.setWindowTitle(bundle.getString("sunkShipTitle"));
					sunkShip.setText(bundle.getString("sunkShipText"));
					sunkShip.exec();
					populateTopTable(tableTop);
				}
			} else {
				populateTopTable(tableTop);
				this.tableTop.repaint();
				ai.shoot();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (gb.AIWon()) {
					showEndScreen(false);
				} else{ 
					populateBottomTable(tableBottom);
				}
			}
		} else {
			QMessageBox overkill = new QMessageBox(this);
			overkill.setWindowTitle(bundle.getString("overkillTitle"));
			overkill.setText(bundle.getString("overkillText"));
			overkill.exec();
		}
	}
	
	
	private void populateTopTable(QTableWidget table) {
		int width = gb.getTopGrid().getWidth();
		int height = gb.getTopGrid().getHeight();
		IGridCell[][] gridCells = gb.getTopGrid().getGrid();
		
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				String val = gridCells[r][c] instanceof ShipCell ? new Empty(r, c).toString() : gridCells[r][c].toString();
				QTableWidgetItem item = new QTableWidgetItem(val);
				if (val.equals("H")) {
					QBrush brush = new QBrush();
					QPixmap pixmap = new QPixmap("shiphit.bmp");
					brush.setTexture(pixmap);
					item.setBackground(brush);
					//item.setBackground(new QBrush(QColor.red));
				} else if (val.equals("M")) {
					QBrush brush = new QBrush();
					QPixmap pixmap = new QPixmap("miss.bmp");
					brush.setTexture(pixmap);
					item.setBackground(brush);
					//item.setBackground(new QBrush(QColor.white));
				} else {
					QBrush brush = new QBrush();
					QPixmap pixmap = new QPixmap("water2.bmp");
					brush.setTexture(pixmap);
					item.setBackground(brush);
				}
				table.setItem(r, c, item);
			}
		}
	}
	
	private void populateBottomTable(QTableWidget table) {
		int width = gb.getBottomGrid().getWidth();
		int height = gb.getBottomGrid().getHeight();
		IGridCell[][] gridCells = gb.getBottomGrid().getGrid();
		
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				String val = gridCells[r][c].toString();
				QTableWidgetItem item = new QTableWidgetItem(val);
				if (val.equals("H")) {
					QBrush brush = new QBrush();
					QPixmap pixmap = new QPixmap("shiphit.bmp");
					brush.setTexture(pixmap);
					item.setBackground(brush);
					//item.setBackground(new QBrush(QColor.red));
				} else if (val.equals("M")) {
					QBrush brush = new QBrush();
					QPixmap pixmap = new QPixmap("miss.bmp");
					brush.setTexture(pixmap);
					item.setBackground(brush);
					//item.setBackground(new QBrush(QColor.white));
				} else if (val.equals("S")){
					QBrush brush = new QBrush();
					QPixmap pixmap = new QPixmap("wholeship.bmp");
					brush.setTexture(pixmap);
					item.setBackground(brush);
					//item.setBackground(new QBrush(QColor.gray));
				} else {
					QBrush brush = new QBrush();
					QPixmap pixmap = new QPixmap("water2.bmp");
					brush.setTexture(pixmap);
					item.setBackground(brush);
				}
				table.setItem(r, c, item);
			}
		}
	}
	
	public void showEndScreen(boolean win) {
		((GameStarter) parent).showEndScreen(win);
	}
	
	public void doNothing(){
		System.out.println("nothing");
	}
}
