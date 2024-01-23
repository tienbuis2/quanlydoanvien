package quanlydoanvien;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Danhsachdoanvien extends JPanel {
    private JPanel contentPanel;
    private JScrollPane jScrollPane;
    private JPanel  titlePanel;
    private Statement stmt;
    public Danhsachdoanvien(Statement _stmt) {
    	this.stmt= _stmt;
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.orange);
        jScrollPane = new JScrollPane(contentPanel);
        addGUI();
        setUp();
       
    }

    private void setUp() {
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        jScrollPane.setOpaque(true); // Bật tính năng vẽ nền     
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(jScrollPane);
    }

    private void addGUI() {
        
        addLabelTitle();
        updateData();
    }

    private void addLabelTitle() {
    	titlePanel =new JPanel(new GridLayout(1,4));
    	titlePanel.setBackground(Color.orange);
    	//this.setLayout(new GridLayout(0, 1));
    	titlePanel.add(new JLabel("Mã đoàn viên"));
    	titlePanel.add(new JLabel("Họ và tên"));
    	titlePanel.add(new JLabel("Ngày sinh"));
    	titlePanel.add(new JLabel("Ngày vào đoàn"));
    	this.add(titlePanel);
    }

    private void updateData() {
    	contentPanel.setLayout(new GridLayout(0, 4));
        ArrayList<String> studentInfoList = new ArrayList<>();
        // nhập dữ liệu  tất cả các sinh viên ở database và hiển thị
        String sql="select * from danhsachdoanvien.thongtindoanvien";
		ResultSet resultSet;
		try {
			resultSet = stmt.executeQuery(sql);
			// thêm vào danh sách
			while (resultSet.next()) {
	        	JTextField id= new JTextField(resultSet.getString("id"));
	        	id.setEditable(false);
	        	id.setFont(new Font("Arial", Font.BOLD, 12));
	            contentPanel.add(id);
	            JTextField name= new JTextField(resultSet.getString("name"));
	        	name.setEditable(false);
	        	name.setFont(new Font("Arial", Font.BOLD, 12));
	            contentPanel.add(name);            
	            JTextField ngaysinh= new JTextField(dateProcess(resultSet.getString("ngaysinh")));
	        	ngaysinh.setEditable(false);
	        	ngaysinh.setFont(new Font("Arial", Font.BOLD, 12));
	            contentPanel.add(ngaysinh);
	            JTextField ngaygianhap= new JTextField(dateProcess(resultSet.getString("ngaygianhap")));
	        	ngaygianhap.setEditable(false);
	        	ngaygianhap.setFont(new Font("Arial", Font.BOLD, 12));
	            contentPanel.add(ngaygianhap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private String dateProcess(String s){
   	 s= s.substring(0,s.indexOf(" "));

        // Tách xâu thành các phần
        String[] parts = s.split("-");

        // Đảo ngược vị trí các phần
        return s = parts[2] + "-" + parts[1] + "-" + parts[0];
   }
}