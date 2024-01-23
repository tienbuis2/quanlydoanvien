package quanlydoanvien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddPanel extends JPanel{
	private JPanel addPanel;
	private JTextField idTextField,nameTextField,birthTextField,joinTextField;
	private JLabel idLabel,nameLabel,birthLabel,joinLabel;
	private JButton addButton;
	private Statement stmt;
	public AddPanel(Statement _stmt) {
		this.stmt= _stmt;
		setUp();
		addGUI();
	}
	private void setUp() {
		setBackground(new Color(220,224,164));
		setLayout(new BorderLayout());
	}
	private void addGUI() {
		addaddPanelGUI();
		addaddAction();
		
		
	}
	private void addaddPanelGUI() {
    	addPanel = new JPanel();
    	addPanel.setPreferredSize(new Dimension(300,250));
    	addPanel.setBackground(new Color(245,255,158));
    	addPanel.setLayout(new GridLayout(6,2,10,10));
    	

    	idLabel= new JLabel("ID  :");
    	nameLabel= new JLabel("Họ và tên  :");
    	birthLabel= new JLabel("Ngày sinh  :");
    	joinLabel= new JLabel("Ngày gia nhập  :");
    	idTextField= new JTextField();
    	setPlaceholder(idTextField, "Nhập ID Đoàn viên ");
    	nameTextField= new JTextField();
    	setPlaceholder(nameTextField, "Nhập tên Đoàn viên ");
    	birthTextField= new JTextField();
    	setPlaceholder(birthTextField, "Nhập ngày sinh của Đoàn viên ");
    	joinTextField= new JTextField();
    	setPlaceholder(joinTextField, "Nhập ngày gia nhập Đoàn viên ");
    	idTextField.setEditable(true);
    	nameTextField.setEditable(true);
    	birthTextField.setEditable(true);
    	joinTextField.setEditable(true);
    	addPanel.add(idLabel);
    	addPanel.add(idTextField);
    	addPanel.add(nameLabel);
    	addPanel.add(nameTextField);
    	addPanel.add(birthLabel);
    	addPanel.add(birthTextField);
    	addPanel.add(joinLabel);
    	addPanel.add(joinTextField);
    	addButton = new JButton("Thêm");
    	JButton addButton2 = new JButton("Thêm");
    	addButton2.setPreferredSize(new Dimension(100,30));
    	addButton2.setVisible(false);
    	addPanel.add(addButton2);
    	addPanel.add(addButton);
    	addButton.setPreferredSize(new Dimension(100,30));

    	addButton.setVisible(true);
    	addButton.setBackground(new Color(95,179,179));
    	addButton.setForeground(Color.WHITE); // Set text color to white

        // Add hover effect to the button
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addButton.setBackground(new Color(34,140,140)); // Change background color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                addButton.setBackground(new Color(95,179,179)); // Change back to the original background color
            }
        });
    	this.add(addPanel, BorderLayout.CENTER);
    	// add padding
	    int topPadding = 40;
        int leftPadding = 120;
        int bottomPadding = 100;
        int rightPadding = 100;
        addPanel.setBorder(new EmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
	}
	private void addaddAction() {
    	addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sql="INSERT INTO danhsachdoanvien.thongtindoanvien(id, name, ngaysinh, ngaygianhap) values('"+idTextField.getText()+"'"+","+"'" +nameTextField.getText()+"'"+","+"'"+dateProcessReverse(birthTextField.getText())+"'"+","+"'"+dateProcessReverse(joinTextField.getText())+"')"; 
				try {
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null,"Thêm Đoàn viên thành công!");
					setPlaceholder(idTextField, "Nhập ID Đoàn viên ");
					setPlaceholder(nameTextField, "Nhập tên Đoàn viên");
					setPlaceholder(birthTextField, "Nhập ngày sinh của Đoàn viên ");
					setPlaceholder(joinTextField, "Nhập ngày gia nhập của Đoàn viên ");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"Thêm Đoàn viên thất bại!");
				}
				
			}
		});
    }
	private static void setPlaceholder(JTextField textField, String placeholder) {
	        textField.setText(placeholder);
	        textField.setForeground(java.awt.Color.GRAY);

	        textField.addFocusListener(new FocusListener() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if (textField.getText().equals(placeholder)) {
	                    textField.setText("");
	                    textField.setForeground(java.awt.Color.BLACK);
	                }
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                if (textField.getText().isEmpty()) {
	                    textField.setText(placeholder);
	                    textField.setForeground(java.awt.Color.GRAY);
	                }
	            }
	        });
	    }
    private String dateProcess(String s){
     	 s= s.substring(0,s.indexOf(" "));

          // Tách xâu thành các phần
          String[] parts = s.split("-");

          // Đảo ngược vị trí các phần
          return s = parts[2] + "-" + parts[1] + "-" + parts[0];
   }
    private String dateProcessReverse(String s){
         String[] parts = s.split("-");
         return s = parts[2] + "-" + parts[1] + "-" + parts[0];
  }
}
