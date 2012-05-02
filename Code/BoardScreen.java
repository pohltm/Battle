import java.util.ResourceBundle;

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
		
		QTableWidget table = createTable();
		
		QVBoxLayout widgetLayout = new QVBoxLayout();
		widgetLayout.addWidget(table);
		
		this.setLayout(widgetLayout);
		
		this.show();
	}
	
	public QTableWidget createTable() {
		QTableWidget table = new QTableWidget(gb.getWidth(), gb.getHeight());
		for (int i = 0; i < table.columnCount(); i++) {
			table.setColumnWidth(i, 73);
		}
		for (int i = 0; i < table.rowCount(); i++) {
			table.setRowHeight(i, 73);
		}
		return table;
}
}
