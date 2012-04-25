import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QPaintEvent;
import com.trolltech.qt.gui.QPainter;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;


public class BoardScreen extends QWidget {
	
	QWidget parent;
	
	public BoardScreen(QWidget parent) {
		super(parent);
		this.parent = parent;
		this.parent.setWindowTitle("Board Screen");
		
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
		painter.setPen(QColor.black);
		painter.drawRect(10, 10, this.width() / 2 - 20, this.height() - 20);
		painter.setPen(QColor.blue);
		painter.drawRect(this.width() / 2 + 10, 10, this.width() / 2 - 20, this.height() - 20);
	}
}
