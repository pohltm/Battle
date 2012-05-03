import java.util.ResourceBundle;

import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QPaintEvent;
import com.trolltech.qt.gui.QPainter;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QTableWidget;
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
	
	public void paintEvent(QPaintEvent h) {
		QPainter painter = new QPainter(this);
		painter.setPen(QColor.black);
		painter.drawRect(10, 10, this.width() - 20, this.height() / 2 - 20);
		painter.setPen(QColor.blue);
		painter.drawRect(10, this.height() / 2 + 10, this.width() - 20, this.height() / 2 - 20);
	}
}