package quanlydoanvien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SearchAndDeletePanel extends JPanel{
	//toppanel
	private JPanel topPanel;
	private JTextField topTextField;
	private JButton topSearchButton;
	//infopanel
	private JPanel infoPanel;
	private JTextField idTextField,nameTextField,birthTextField,joinTextField;
	private JLabel idLabel,nameLabel,birthLabel,joinLabel;
	//botpanel
	private JPanel bottomPanel;
	private JButton changeButton,deleteButton;
	//sql
	private Statement stmt;
	//id
	private String idToDelete="";
	public SearchAndDeletePanel(Statement _stmt) {
		this.stmt= _stmt;
		setUp();
		addGUI();
		displayInfoWhenPressSearchButton();
    }
	private void setUp() {
		setBackground(new Color(220,224,164));
		setLayout(new BorderLayout());
		
	}
	private void addGUI() {
		addTopPanelGUI();
		addInfopanelGUI();
		addBottompanelGUI();
		addDeleteAction();
		addChangeAction();
	}
	private void addTopPanelGUI() {
		topPanel= new JPanel();
		topPanel.setPreferredSize(new Dimension(600,100));
		topPanel.setBackground(new Color(120,134,135));
		
		//setlayout
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topSearchButton = new JButton("Tìm");
		topSearchButton.setPreferredSize(new Dimension(100,30));
		topSearchButton.setBackground(new Color(216,242,240));
		topSearchButton.setForeground(Color.black); // Set text color to white

	    // Add hover effect to the top button
	    topSearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseEntered(java.awt.event.MouseEvent evt) {
	            topSearchButton.setBackground(new Color(176, 224, 230)); // Change background color on hover
	        }

	        public void mouseExited(java.awt.event.MouseEvent evt) {
	            topSearchButton.setBackground(new Color(216, 242, 240)); // Change back to the original background color
	        }
	    });
		topTextField = new JTextField();
		topTextField.setPreferredSize(new Dimension(300,30));
		setPlaceholder(topTextField, "Nhập ID Đoàn viên cần tìm");
		//addd
		topPanel.add(topTextField);
		topPanel.add(topSearchButton);
		
		// add padding
	    int topPadding = 25;
        int leftPadding = 100;
        int bottomPadding = 50;
        int rightPadding = 50;
        topPanel.setBorder(new EmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
        this.add(topPanel, BorderLayout.NORTH);
	}
    private void addInfopanelGUI() {
    	infoPanel = new JPanel();
    	infoPanel.setPreferredSize(new Dimension(300,250));
    	infoPanel.setBackground(new Color(181,186,186));
    	infoPanel.setLayout(new GridLayout(6,2,10,10));
    	idLabel= new JLabel("ID  :");
    	nameLabel= new JLabel("Họ và tên  :");
    	birthLabel= new JLabel("Ngày sinh  :");
    	joinLabel= new JLabel("Ngày gia nhập  :");
    	idTextField= new JTextField();
    	nameTextField= new JTextField();
    	birthTextField= new JTextField();
    	joinTextField= new JTextField();
    	idTextField.setEditable(false);
    	nameTextField.setEditable(false);
    	birthTextField.setEditable(false);
    	joinTextField.setEditable(false);
    	infoPanel.add(idLabel);
    	infoPanel.add(idTextField);
    	infoPanel.add(nameLabel);
    	infoPanel.add(nameTextField);
    	infoPanel.add(birthLabel);
    	infoPanel.add(birthTextField);
    	infoPanel.add(joinLabel);
    	infoPanel.add(joinTextField);
    	this.add(infoPanel, BorderLayout.CENTER);
    	// add padding
	    int topPadding = 70;
        int leftPadding = 70;
        int bottomPadding = 30;
        int rightPadding = 50;
        infoPanel.setBorder(new EmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
    	
    }
    private void addBottompanelGUI() {
    	bottomPanel = new JPanel();
    	bottomPanel.setPreferredSize(new Dimension(640,150));
    	bottomPanel.setBackground(new Color(120,134,135));
    	deleteButton = new JButton("Xóa");
    	deleteButton.setVisible(false);
    	deleteButton.setBackground(new Color(204,96,105));
    	deleteButton.setForeground(Color.WHITE); // Set text color to white

        // Add hover effect to the top button
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteButton.setBackground(new Color(250,0,67)); // Change background color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteButton.setBackground(new Color(204,96,105)); // Change back to the original background color
            }
        });
    	changeButton = new JButton("Chỉnh Sửa");
    	changeButton.setVisible(false);
    	changeButton.setBackground(new Color(155,222,129));
    	changeButton.setForeground(Color.WHITE); // Set text color to white

        // Add hover effect to the top button
        changeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                changeButton.setBackground(new Color(102,222,89)); // Change background color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                changeButton.setBackground(new Color(155,222,129)); // Change back to the original background color
            }
        });
    	
    	//layout
    	bottomPanel.setLayout(new GridLayout(1,1,100,0));
    	bottomPanel.add(deleteButton);
    	bottomPanel.add(changeButton);
    	
    	// add padding
	    int topPadding = 50;
        int leftPadding =130;
        int bottomPadding = 50;
        int rightPadding = 130;
        bottomPanel.setBorder(new EmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
        this.add(bottomPanel, BorderLayout.SOUTH);
    	
    	
    }
    private void displayInfoWhenPressSearchButton() {
		topSearchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!topTextField.getText().isEmpty()) {
					String  inputID=topTextField.getText().trim();
					// check xem id đó có tồn tại không
					String sql="select * from danhsachdoanvien.thongtindoanvien where id='"+inputID+"'";
					try {					
						ResultSet resultSet= stmt.executeQuery(sql);
						setPlaceholder(topTextField, "Nhập ID Đoàn viên cần tìm");
						if(!resultSet.next()) {
							return;
						}else {
							deleteButton.setVisible(true);
							changeButton.setVisible(true);
						}
							idTextField.setText(resultSet.getString("id"));
							idToDelete=resultSet.getString(("id"));
							nameTextField.setText(resultSet.getString("name"));
							System.out.println(resultSet.getString("id").trim());
							System.out.println(resultSet.getString("name").trim());
							System.out.println(resultSet.getString("ngaysinh").trim());
							System.out.println(resultSet.getString("ngaygianhap").trim());
							birthTextField.setText(dateProcess(resultSet.getString("ngaysinh")));
							joinTextField.setText(dateProcess(resultSet.getString("ngaygianhap")));	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
    private void addDeleteAction() {
    	deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sql="delete from danhsachdoanvien.thongtindoanvien where id='"+idToDelete+"'";
				try {
					stmt.executeUpdate(sql);
					idToDelete="";
					deleteButton.setVisible(false);
					changeButton.setVisible(false);
					idTextField.setText("");
					nameTextField.setText("");
					birthTextField.setText("");
					joinTextField.setText("");
					JOptionPane.showMessageDialog(null,"Đã xóa thông tin Đoàn viên!");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
    }
    private void addChangeAction() {
    	changeButton.addActionListener(new ActionListener() {
            private String originalName;
            private String originalBirth;
            private String originalJoin;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (changeButton.getText().equals("Chỉnh Sửa")) {
                    originalName = nameTextField.getText();
                    originalBirth = birthTextField.getText();
                    originalJoin = joinTextField.getText();

                    nameTextField.setEditable(true);
                    birthTextField.setEditable(true);
                    joinTextField.setEditable(true);
                    changeButton.setText("Lưu");
                } else {
                    try {
                        String sql = "Update danhsachdoanvien.thongtindoanvien set name = N'" + nameTextField.getText() + "'";
                        String sql2 = "Update danhsachdoanvien.thongtindoanvien set ngaysinh = N'" + dateProcessReverse(birthTextField.getText()) + "'";
                        String sql3 = "Update danhsachdoanvien.thongtindoanvien set ngaygianhap = N'" + dateProcessReverse(joinTextField.getText()) + "'";
                        String sql4 = "where id='" + idTextField.getText() + "'";

                        stmt.executeUpdate(sql + sql4);
                        stmt.executeUpdate(sql2 + sql4);
                        stmt.executeUpdate(sql3 + sql4);

                        changeButton.setText("Chỉnh Sửa");
                        nameTextField.setEditable(false);
                        birthTextField.setEditable(false);
                        joinTextField.setEditable(false);
                        JOptionPane.showMessageDialog(null, "Chỉnh sửa thông tin Đoàn viên thành công!");
                    } catch (SQLException e1) {
                        nameTextField.setText(originalName);
                        birthTextField.setText(originalBirth);
                        joinTextField.setText(originalJoin);

                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra. Vui lòng kiểm tra lại dữ liệu!");
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
    private String dateProcess(String s){
      	 s= s.substring(0,s.indexOf(" "));
           String[] parts = s.split("-");
           return s = parts[2] + "-" + parts[1] + "-" + parts[0];
    }
    private String dateProcessReverse(String s){
          String[] parts = s.split("-");
          return s = parts[2] + "-" + parts[1] + "-" + parts[0];
   }
}

	
