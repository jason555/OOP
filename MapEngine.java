package term.map;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;

class BgPanel extends JPanel {
    Image bg = new ImageIcon("./resource/board.png").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, 780, 930,this);
    }
}

public class MapEngine {
	private JFrame bgFrame;
	private JPanel bgPanel;
	private JPanel posPanel;
	JButton[][] mapButtons;
	
	public MapEngine() {
		bgFrame = new JFrame();
		bgFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bgPanel = new BgPanel();
		bgPanel.setLayout(null);
		
		mapButtons = new JButton[MapCalc.HEIGHT][MapCalc.WIDTH];
		
		posPanel = new JPanel();
		posPanel.setLayout(new GridLayout(MapCalc.HEIGHT, MapCalc.WIDTH, 0, 0));
		posPanel.setOpaque(false);

		posPanel.setBounds(0, 0, 780, 930);
		for(int i=0 ; i<MapCalc.HEIGHT ; i++) {
			for(int j=0 ; j<MapCalc.WIDTH ; j++) {
				mapButtons[i][j] = new JButton();
				mapButtons[i][j].setPreferredSize(new Dimension(30, 30));
			//	btn.setBackground(Color.white);
				mapButtons[i][j].setOpaque(false);
				mapButtons[i][j].setContentAreaFilled(false);
				Border defaultBorder = new LineBorder(Color.WHITE, 1);
				mapButtons[i][j].setBorder(defaultBorder);
		//		mapButtons[i][j].setBorderPainted(false);

				posPanel.add(mapButtons[i][j]);
			}
		}
		
		
		bgPanel.add(posPanel);
		
		bgFrame.setContentPane(bgPanel);
		bgFrame.setSize(1500, 980);
		bgFrame.setVisible(true);
	}
	
	// 캐릭터 이동 시 원래 자리를 다시 button으로 만들고 이동한 곳에 캐릭터를 위치시키는 메서드
	public void gridChange(int x, int y, Object replacement) {
		int pos = (26 * x) + y;
		posPanel.remove(pos);
		
		if(replacement instanceof JButton) posPanel.add((JButton)replacement, pos);
		else if(replacement instanceof JLabel) posPanel.add((JLabel)replacement, pos);
		posPanel.revalidate();
		posPanel.repaint();
	}
}


