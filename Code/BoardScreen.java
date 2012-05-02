import java.util.ResourceBundle;

import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QPaintEvent;
import com.trolltech.qt.gui.QPainter;
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
		
		QWidget board1 = createBoard1(10, 10);
		
		QVBoxLayout widgetLayout = new QVBoxLayout();
		widgetLayout.addWidget(board1);
		
		this.setLayout(widgetLayout);
		
		this.show();
	}
	
	public QWidget createBoard1(int width, int height) {
		QWidget board1 = new QWidget(this);
		
		QGridLayout boardLayout = new QGridLayout();
		
		board1.setLayout(boardLayout);
		
		return board1;
	}
	
	public void paintEvent(QPaintEvent h) {
		QPainter painter = new QPainter(this);
		
		int widthOfGrid = gb.getWidth()*30;
		int heightOfGrid = gb.getHeight()*30;
		
		painter.setPen(QColor.black);
		painter.drawRect(10, 10, widthOfGrid, heightOfGrid);
		painter.setPen(QColor.blue);
		painter.drawRect(10, heightOfGrid + 50, widthOfGrid, heightOfGrid);
	}
}
