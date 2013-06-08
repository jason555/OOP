// 이영호가 올린거임
// MapModule.java
package term.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

class MapCalc {
	private int[][] rooms, availablePaths;	// availablePaths는 path를 찾기 위함
	ArrayList<Point> searchQueue;			// path를 찾기 위함
	
	// characters
	public final static int SCARLET = 0;
	public final static int MUSTARD = 1;
	public final static int WHITE = 2;
	public final static int GREEN = 3;
	public final static int PEACOCK = 4;
	public final static int PLUM = 5;
	static int[][] playerPos;
	static JLabel[] playerImg;
	// characters end
	
	// 맵은 벽까지 26*31
	public final static int WIDTH = 26;
	public final static int HEIGHT = 31;
	// 지나갈 수 있는 길, 1로 표시
	private int[][] passZone = {{1, 9}, {1, 10}, {1, 20}, {1, 21},
			{2, 9}, {2, 10}, {2, 20}, {2, 21},
			{3, 9}, {3, 10}, {3, 20}, {3, 21},
			{4, 10}, {4, 20}, {4, 21},
			{5, 9}, {5, 10}, {5, 11}, {5, 20}, {5, 21},
			{6, 8}, {6, 9}, {6, 10}, {6, 20}, {6, 21},
			{7, 1}, {7, 2}, {7, 3}, {7, 4}, {7, 5}, {7, 6}, {7, 7}, {7, 8}, {7, 9}, {7, 10}, {7, 11}, {7, 19}, {7, 20}, {7, 21}, {7, 22},
			{8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 6}, {8, 7}, {8, 8}, {8, 9}, {8, 10}, {8, 11}, {8, 19}, {8, 20}, {8, 21}, {8, 22}, {8, 23}, {8, 24}, {8, 25}, {8, 26}, {8, 27}, {8, 28}, {8, 29},
			{9, 9}, {9, 10}, {9, 11}, {9, 12}, {9, 14}, {9, 16}, {9, 18}, {9, 19}, {9, 20}, {9, 21}, {9, 22}, {9, 23}, {9, 25}, {9, 26}, {9, 27}, {9, 28}, {9, 29},
			{10, 9}, {10, 10}, {10, 11}, {10, 12}, {10, 13}, {10, 14}, {10, 15}, {10, 16}, {10, 17}, {10, 18}, {10, 19}, {10, 20}, {10, 21}, {10, 22}, {10, 23},
			{11, 10}, {11, 11}, {11, 19},
			{12, 10}, {12, 11}, {12, 18}, {12, 19},
			{13, 9}, {13, 10}, {13, 11}, {13, 18},
			{14, 1}, {14, 4}, {14, 5}, {14, 6}, {14, 7}, {14, 8}, {14, 9}, {14, 10}, {14, 11}, {14, 18}, {14, 19},
			{15, 9}, {15, 10}, {15, 19},
			{16, 10}, {16, 11}, {16, 18}, {16, 19},
			{17, 11}, {17, 18}, {17, 19}, {17, 20}, {17, 21}, {17, 23},
			{18, 10}, {18, 11}, {18, 19}, {18, 20}, {18, 21}, {18, 22}, {18, 23}, {18, 24}, {18, 25}, {18, 26}, {18, 27}, {18, 28}, {18, 29},
			{19, 9}, {19, 10}, {19, 11}, {19, 12}, {19, 13}, {19, 14}, {19, 15}, {19, 16}, {19, 17}, {19, 18}, {19, 19}, {19, 20}, {19, 21}, {19, 22}, {19, 24}, {19, 25}, {19, 26}, {19, 27}, {19, 28}, {19, 29},
			{20, 9}, {20, 10}, {20, 11}, {20, 19}, {20, 20}, {20, 21}, {20, 22}, {20, 23}, {20, 24}, {20, 25}, {20, 26}, {20, 27}, {20, 28}, {20, 29},
			{21, 1}, {21, 2}, {21, 3}, {21, 4}, {21, 5}, {21, 6}, {21, 7}, {21, 8}, {21, 9}, {21, 10}, {21, 11}, {21, 20},
			{22, 1}, {22, 2}, {22, 3}, {22, 4}, {22, 5}, {22, 7}, {22, 8}, {22, 10}, {22, 11}, {22, 19}, {22, 20},
			{23, 10}, {23, 19}, {23, 20},
			{24, 10}, {24, 11}, {24, 19}, {24, 20}
	};
	// ? 카드 표시, 2로 표시
	private int[][] cardZone = {{4, 9}, {5, 19}, {8, 5}/*?*/, {9, 15}/*?*/, {9, 24}/*?*/, {12, 9}/*?*/, {14, 2}/*?*/, {15, 18}/*?*/, {19, 23}/*?*/, {21, 19}/*?*/, {22, 6}/*?*/};
	// 방 앞쪽 공간, 3으로 표시
	private int[][] frontZone = {{6, 7}/*f*/, {6, 11}/*f*/, {6, 19}/*f*/, {7, 22}/*f*/, {9, 13}/*f*/, {9, 17}/*f*/, {11, 9}/*f*/, {11, 18}/*f*/, {13, 19}/*f*/, {14, 3}/*f*/, {15, 11}/*f*/, {17, 22}/*f*/, {18, 18}/*f*/, {21, 21}/*f*/, {22, 9}, {23, 11}};
	// 방, 4로 표시
	private int[][] enterZone = {{6, 6}, {6, 12}, {6, 18}, {7, 23}, {8, 13}, {8, 17}, {11, 8}, {11, 17}, {13, 20}, {13, 3}, {15, 12}, {16, 22}, {17, 9}, {18, 17}, {21, 22}, {22, 21}, {23, 9}, {23, 12}};
	// ?와 f가 동시에, 5로 표시
	private int[][] cardFrontZone = {{17, 10}/*?f*/};
	
	// 캐릭터가 방에 있을 때 위치할 좌표
	private static int[][][] inRoomZone = {{{2, 1}, {2, 3}, {2, 5}, {4, 1}, {4, 3}, {4, 5}},
			{{2, 10}, {2, 12}, {4, 10}, {4, 12}, {6, 10}, {6, 12}},
			{{2, 16}, {2, 18}, {4, 17}, {4, 19}, {6, 16}, {6, 18}},
			{{1, 23}, {2, 24}, {4, 23}, {5, 24}, {7, 23}, {8, 24}},
			{{14, 1}, {14, 3}, {14, 5}, {16, 1}, {16, 3}, {16, 5}},
			{{14, 12}, {14, 14}, {14, 17}, {16, 12}, {16, 14}, {16, 17}},
			{{14, 20}, {14, 22}, {14, 24}, {16, 21}, {16, 23}, {16, 25}},
			{{24, 1}, {24, 3}, {24, 5}, {27, 2}, {27, 4}, {27, 6}},
			{{23, 12}, {23, 15}, {26, 11}, {26, 14}, {29, 12}, {29, 15}},
			{{24, 22}, {24, 24}, {26, 22}, {26, 24}, {28, 22}, {28, 24}}
	};
	
	MapEngine mEngine;
	
	
	public MapCalc() {
		mEngine = new MapEngine();
		
		playerPos = initPlayerPos();
		playerImg = new JLabel[6];
		for(int i=0 ; i<6 ; i++) {
			playerImg[i] = new JLabel(new ImageIcon("./resource/char.png"));
			playerImg[i].setPreferredSize(new Dimension(18, 30));
		}
		this.rooms = initRooms();
	}
	
	private int[][] initRooms() {
		int[][] r = new int[WIDTH][HEIGHT];
		for(int i=0 ; i<WIDTH ; i++) {
			for(int j=0 ; j<HEIGHT ; j++) {
				r[i][j] = 0;
			}
		}
		for(int i=0 ; i<passZone.length ; i++) {
			r[passZone[i][0]][passZone[i][1]] = 1;
		}
		for(int i=0 ; i<cardZone.length ; i++) {
			r[cardZone[i][0]][cardZone[i][1]] = 2;
		}
		for(int i=0 ; i<frontZone.length ; i++) {
			r[frontZone[i][0]][frontZone[i][1]] = 3;
		}
		for(int i=0 ; i<enterZone.length ; i++) {
			r[enterZone[i][0]][enterZone[i][1]] = 4;
		}
		for(int i=0 ; i<cardFrontZone.length ; i++) {
			r[cardFrontZone[i][0]][cardFrontZone[i][1]] = 5;
		}
		
		for(int i=0 ; i<6 ; i++)
			mEngine.gridChange(playerPos[i][0], playerPos[i][1], playerImg[i]);
		
		return r;
	}
	
	private int[][] initPlayerPos() {
		int[][] pos = new int[6][2];
		pos[SCARLET][0] = 29;
		pos[SCARLET][1] = 19;
		pos[MUSTARD][0] = 29;
		pos[MUSTARD][1] = 8;
		pos[WHITE][0] = 20;
		pos[WHITE][1] = 1;
		pos[GREEN][0] = 10;
		pos[GREEN][1] = 1;
		pos[PEACOCK][0] = 1;
		pos[PEACOCK][1] = 7;
		pos[PLUM][0] = 1;
		pos[PLUM][1] = 21;
		
		return pos;
	}
	
	public void printMap() {
		for(int i=0 ; i<HEIGHT ; i++) {
			for(int j=0 ; j<WIDTH ; j++) {
				System.out.printf("%3d", this.rooms[j][i]);
			}
			System.out.println();
		}
	}
	
	public void printPathMap() {
		for(int i=0 ; i<HEIGHT ; i++) {
			for(int j=0 ; j<WIDTH ; j++) {
				System.out.printf("%3d", this.availablePaths[j][i]);
			}
			System.out.println();
		}
	}
	
	
	public boolean isInBound(int x, int y) {
		if((x > 0 && x < WIDTH-1) && (y > 0 && y < HEIGHT-1))
			return true;
		else return false;
	}
	
	public void findPaths(int character, int length) {
		int x = playerPos[character][1];
		int y = playerPos[character][0];
		if(!isInBound(x, y)) return;
	//	if(rooms[x][y] < 1 || rooms[x][y] > 5)	return;
		
		// 배열 복사
		this.availablePaths = new int[WIDTH][HEIGHT];
		for(int i=0 ; i<rooms.length ; i++) {
			System.arraycopy(rooms[i], 0, availablePaths[i], 0, rooms[i].length);
		}
		
		// 캐릭터 표시
		for(int i=0 ; i<6 ; i++)
			availablePaths[playerPos[i][1]][playerPos[i][0]] += 10;
		
		
		// 방 안에서 출발할 경우 가능한 시작 좌표들을 불러옴
		int roomNum = isInRoom(x, y);
		System.out.println(roomNum);
		ArrayList<Point> roomExits = setRoomValue(roomNum);
		
		int exitCount = 0;
		// queue reset
		searchQueue = new ArrayList<Point>();
		
		// 해당 위치의 값이 11~15가 되게 하여 findNextPath 함수에서 정상 처리되도록 함
		// 그 직후 바로 queue에 넣음
		if(roomNum == 0) {
			availablePaths[x][y] += 10;
			searchQueue.add(new Point(x, y, length, 2));
		} else {
			exitCount = roomExits.size();
			for(int i=0 ; i<exitCount ; i++) {
				Point exit = roomExits.get(i);
				availablePaths[exit.getX()][exit.getY()] += 10;
				searchQueue.add(new Point(exit.getX(), exit.getY(), length, 2));
			}
		}
		
		Point temp;
		while(!searchQueue.isEmpty()) {
			temp = searchQueue.remove(0);
			findNextPath(temp.getX(), temp.getY(), temp.getTtl(), temp.getSignals());
//			System.out.println(searchQueue.size());
		}
		
		// 시작점은 값이 20 미만이 되도록 값 변조시킴
		if(roomNum == 0) availablePaths[x][y] = 19;
		else {
			exitCount = roomExits.size();
			for(int i=0 ; i<exitCount ; i++) {
				Point exit = roomExits.get(i);
				availablePaths[exit.getX()][exit.getY()] = 19;
			}
		}
		
		// button 활성화
		for(int i=0 ; i<WIDTH ; i++) {
			for(int j=0 ; j<HEIGHT ; j++) {
				Border activeBorder = new LineBorder(Color.BLUE, 1);
				if(availablePaths[i][j] >= 20) {
					mEngine.mapButtons[j][i].setBorder(activeBorder);
					mEngine.mapButtons[j][i].addActionListener(new MoveActionListener(j, i, character, mEngine));
				}
			}
		}
		
		
		this.printPathMap();
	}
	
	public void findNextPath(int x, int y, int ttl, int signals) {
		if(!isInBound(x, y)) return;
		if((this.availablePaths[x][y] < 1 || this.availablePaths[x][y] > 5) && signals != 2) return;
		if(this.availablePaths[x][y] >= 20 + ttl) return;
		if(ttl < 0) return;
		
		// 방 입장 / 방안에서 시작했을 때의 처리
		if(this.availablePaths[x][y] == 4) {
			if(signals == 1) {
				this.availablePaths[x][y] = 20 + ttl;
				return;
			} else if(signals != 2) return;
		}
		
		// 방문 앞에서의 처리
		if(this.availablePaths[x][y] == 3 || this.availablePaths[x][y] == 5) signals = 1;
		else signals = 0;
		if(signals != 2) this.availablePaths[x][y] = 20 + ttl;
		
		System.out.println(x + ", " + y + ", " + ttl + ", " + signals);
		ttl--;
		
		searchQueue.add(new Point(x-1, y, ttl, signals));
		searchQueue.add(new Point(x, y-1, ttl, signals));
		searchQueue.add(new Point(x+1, y, ttl, signals));
		searchQueue.add(new Point(x, y+1, ttl, signals));
	}
	
	public int isInRoom(int x, int y) {
		for(int i=0 ; i<10 ; i++) {				// 방 갯수
			for(int j=0 ; j<6 ; j++) {
				if(inRoomZone[i][j][0] == y && inRoomZone[i][j][1] == x) return i+1;
			}
		}
		return 0;
	}
	
	public ArrayList<Point> setRoomValue(int roomNum) {
		ArrayList<Point> exitList = new ArrayList<Point>();
		switch(roomNum) {
			case 1:
				exitList.add(new Point(6, 6, 0, 0));
				break;
			case 2:
				exitList.add(new Point(11, 8, 0, 0));
				exitList.add(new Point(13, 3, 0, 0));
				break;
			case 3:
				exitList.add(new Point(17, 9, 0, 0));
				break;
			case 4:
				exitList.add(new Point(23, 9, 0, 0));
				break;
			case 5:
				exitList.add(new Point(6, 12, 0, 0));
				exitList.add(new Point(6, 18, 0, 0));
				exitList.add(new Point(8, 13, 0, 0));
				exitList.add(new Point(8, 17, 0, 0));
				break;
			case 6:
				exitList.add(new Point(11, 17, 0, 0));
				exitList.add(new Point(15, 12, 0, 0));
				exitList.add(new Point(18, 17, 0, 0));
				break;
			case 7:
				exitList.add(new Point(23, 12, 0, 0));
				break;
			case 8:
				exitList.add(new Point(7, 23, 0, 0));
				break;
			case 9:
				exitList.add(new Point(13, 20, 0, 0));
				exitList.add(new Point(16, 22, 0, 0));
				break;
			case 10:
				exitList.add(new Point(21, 22, 0, 0));
				exitList.add(new Point(22, 21, 0, 0));
				break;		
		}
		
		return exitList;
	}
	
	public static int[] roomIn(int room) {
		int[] roomPos = new int[2];
		boolean alreadyExist = false;		// 방에 할당된 공간에 이미 다른 말이 있는지

		for(int i=0 ; i<6 ; i++) {
			roomPos[0] = inRoomZone[room-1][i][0];
			roomPos[1] = inRoomZone[room-1][i][1];
			
			for(int j=0 ; j<6 ; j++) {
				if(playerPos[j][0] == roomPos[0] && playerPos[j][1] == roomPos[1]) {
					alreadyExist = true;
					break;
				}
				if(j == 5) alreadyExist = false;
			}
			
			if(alreadyExist) continue;
			else break;
		}
		
		return roomPos;
	}
	
	// 캐릭터가 현재 어느 위치에 있는지 반환하는 정적 메소드
	public static int[] getCharacterPosition(int character) {
		int[] charPos = new int[2];
		charPos[0] = playerPos[character][0];
		charPos[1] = playerPos[character][1];
		
		return charPos;
	}
	
	public static void setCharacterPosition(int character, int x, int y) {
		playerPos[character][0] = x;
		playerPos[character][1] = y;
	}
	
	public static JLabel getCharacterImage(int character) {
		return playerImg[character];
	}
	
}

class Point {
	private int x, y, ttl, signals;
	
	public Point(int x, int y, int ttl, int signals) {
		this.x = x;
		this.y = y;
		this.ttl = ttl;
		this.signals = signals;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getTtl() {
		return ttl;
	}

	public int getSignals() {
		return signals;
	}
}

// ActionLinstener 상속받음
class MoveActionListener implements ActionListener {
	private int x, y, character;
	MapEngine mEngine;
	
	public MoveActionListener(int x, int y, int character, MapEngine mEngine) {
		this.x = x;
		this.y = y;
		this.character = character;
		this.mEngine = mEngine;
	}
	
	public void actionPerformed(ActionEvent e) {
		int[] charPos;
		charPos = MapCalc.getCharacterPosition(character);
		mEngine.gridChange(charPos[0], charPos[1], mEngine.mapButtons[charPos[0]][charPos[1]]);
		
		int roomNum = roomEnter(y, x);
		System.out.println(roomNum + ": " + x + ", " + y);
		if(roomNum > 0) {
			charPos = MapCalc.roomIn(roomNum);
			mEngine.gridChange(charPos[0], charPos[1], MapCalc.getCharacterImage(character));
			MapCalc.setCharacterPosition(character, charPos[0], charPos[1]);
		} else {
			mEngine.gridChange(x, y, MapCalc.getCharacterImage(character));
			MapCalc.setCharacterPosition(character, x, y);
		}
	}
	
	public int roomEnter(int x, int y) {
		if(x == 6 && y == 6) return 1;
		if((x == 11 && y == 8) || x == 13 && y == 3) return 2;
		if(x == 17 && y == 9) return 3;
		if(x == 23 && y == 9) return 4;
		if((x == 6 && (y == 12 || y == 18)) || (x == 8 && (y == 13 || y == 17)) ) return 5;
		if((x == 11 && y == 17) || (x == 15 && y == 12) || (x == 18 && y == 17)) return 6;
		if(x == 23 && y == 12) return 7;
		if(x == 7 && y == 23) return 8;
		if((x == 13 && y == 20) || (x == 16 && y == 22)) return 9;
		if((x == 21 && y == 22) || (x == 22 && y == 21)) return 10;
		return 0;
	}
}


public class MapModule {
	public static void main(String[] args) {
		MapCalc map = new MapCalc();
	//	map.printMap();
		map.findPaths(MapCalc.PLUM, 11);
	}
}













// MapEngine.java
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


