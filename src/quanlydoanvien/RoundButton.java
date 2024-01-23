package quanlydoanvien;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicBorders;

public class RoundButton extends JButton {
    public RoundButton(String text) {
        setText(text);
        setPreferredSize(new Dimension(170, 60));
       // setContentAreaFilled(false);
        setBackground( new Color(213,216,181));
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 10));
     //   setForeground(Color.white);
        setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(4, 4, 4, 4, Color.white),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        // Thêm hiệu ứng Hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(Color.yellow);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground( new Color(46,44,92));
            }
        });

        // Thêm hiệu ứng Press
        getModel().addChangeListener(evt -> {
            if (getModel().isPressed()) {
                setForeground(Color.gray);
            } else {
                setForeground(Color.white);
            }
        });
    }
}