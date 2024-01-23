package quanlydoanvien;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

public class MyFrame {
    private CentrePanel centrePanel;
    private ButtonPanel buttonPanels;
    private JFrame frame;
    private Title addTitle;
    private static Statement stmt;
    public static void main(String[] args) {
    	String url="jdbc:mysql://localhost:3306/danhsachdoanvien";
		String user="root";
		String password="1234";
		String driverName="com.mysql.cj.jdbc.Driver";
        try {
			Class.forName(driverName);
			try {
				Connection myConnection= DriverManager.getConnection(url, user, password);
				stmt = myConnection.createStatement();
				String sql="select * from danhsachdoanvien.thongtindoanvien";
				ResultSet resultSet= stmt.executeQuery(sql);
				while (resultSet.next()) {
					System.out.println(resultSet.getString("id").trim());
					System.out.println(resultSet.getString("name").trim());
				//	string1= resultSet.getString("name").trim();
					System.out.println(resultSet.getString("ngaysinh").trim());
					System.out.println(resultSet.getString(4).trim());
				}
				// ket noi được thì mới tạo frame :))
		        MyFrame myFrame = new MyFrame();
		        myFrame.setUpFrame();
		        myFrame.addPressAction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    private void setUpFrame() {
        frame = new JFrame("ChuongTrinhQuanLyDoanVien");
        // Initialize Danhsachdoanvien and ButtonPanel
        buttonPanels = new ButtonPanel();
        centrePanel = new CentrePanel(stmt);
        addTitle = new Title();
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(centrePanel,BorderLayout.CENTER);
        frame.add(buttonPanels, BorderLayout.WEST);
        frame.add(addTitle, BorderLayout.NORTH);
        frame.setVisible(true);
    }
    private void addPressAction() {
    	//System.out.println(buttonPanels.listButton);
    	buttonPanels.listButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(centrePanel.danhsach.isVisible()) {
				
				}else {
				centrePanel.danhsach= new Danhsachdoanvien(stmt);
				centrePanel.add(centrePanel.danhsach);
				}
			   centrePanel.danhsach.setVisible(true);
			   centrePanel.searchAndDeletePanel.setVisible(false);
			   centrePanel.addPanel.setVisible(false);
			}
		});
    	buttonPanels.searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				centrePanel.danhsach.setVisible(false);
				centrePanel.searchAndDeletePanel.setVisible(true);
				centrePanel.addPanel.setVisible(false);
			}
		});
    	buttonPanels.addButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				centrePanel.danhsach.setVisible(false);
				centrePanel.searchAndDeletePanel.setVisible(false);
				centrePanel.addPanel.setVisible(true);
				
			}
		});
    }
    
}