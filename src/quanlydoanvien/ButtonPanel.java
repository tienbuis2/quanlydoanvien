package quanlydoanvien;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel{
	public RoundButton changeButton, searchButton, deleteButton, addButton, listButton;
	public ButtonPanel() {
		addGUI();
		setUpPanel();
	}
	private void setUpPanel() {
		  this.setPreferredSize(new Dimension(180, 400));
		  this.setBackground( new Color(136,193,203));
	}
	private  void addGUI() {
		searchButton=new  RoundButton("Tìm Kiếm");
		this.add(searchButton);
		addButton=new  RoundButton("Thêm đoàn viên");
		this.add(addButton);
		listButton=new  RoundButton("Danh sách đoàn viên");
		this.add(listButton);

	}
	
}