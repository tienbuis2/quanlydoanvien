package quanlydoanvien;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

public class Title extends JLabel {
	public Title() {
		addTitle();
		// TODO Auto-generated constructor stub
	}
	private void addTitle() {
		this.setText("Quản lý Đoàn Viên");
		this.setPreferredSize(new Dimension(800,30));
		this.setBackground( new Color(213,238,244));
		this.setOpaque(true);
		// Đặt vị trí ngang của chữ là CENTER
        this.setHorizontalAlignment(JLabel.CENTER);

        // Đặt font chữ
        Font titleFont = new Font("Arial", Font.BOLD, 24); // Thay đổi thông số font chữ nếu cần
        this.setFont(titleFont);
	}

}
