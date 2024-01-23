package quanlydoanvien;

import java.awt.Dimension;
import java.sql.Statement;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class CentrePanel extends JPanel{
	public Danhsachdoanvien danhsach;
	public SearchAndDeletePanel searchAndDeletePanel;
	public AddPanel addPanel;
	private Statement stmt;
	public CentrePanel(Statement _stmt) {
		this.stmt= _stmt;
		System.out.println(stmt);
		setUp();
	}
	private void setUp() {
		setPreferredSize(new Dimension(550,650));
		danhsach = new Danhsachdoanvien(this.stmt);
		searchAndDeletePanel = new SearchAndDeletePanel(stmt);
		addPanel = new AddPanel(this.stmt);
		setLayout(new OverlayLayout(this));
		this.add(danhsach);
		this.add(searchAndDeletePanel);
		searchAndDeletePanel.setVisible(false);
		addPanel.setVisible(false);
		this.add(addPanel);
		
	}
	

}
