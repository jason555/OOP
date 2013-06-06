import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
힘들면 아프다고 말해 니가 얘기해 주길 바래~
/**
 * Created with IntelliJ IDEA.
 * User: WanH
 * Date: 13. 6. 3
 * Time: 오후 9:14
 * To change this template use File | Settings | File Templates.
 */
public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CLUE: Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);

        // 전체를 감싸는 패널 설정, 2단(상단, 하단) 구성
        JPanel background = new JPanel();
        background.setBackground(Color.gray);
        background.setLayout(new GridLayout(2, 1));

        // 상단 부분 시작
        // 상단 : 패널
        JPanel top = new JPanel();
        top.setBackground(new Color(0, 153, 68));
        top.setLayout(null);
        // 상단 : 타이틀
        JLabel title = new JLabel("CLUE No. 1");
        title.setBounds(100, 100, 600, 200);
        title.setForeground(Color.white);
        title.setFont(title.getFont().deriveFont(72.0f));
        // 타이틀 구성요소 추가
        top.add(title);
        background.add(top);

        // 하단 부분 시작
        // 하단 : 패널 레이아웃 설정
        JPanel bot = new JPanel();
        bot.setBackground(Color.white);
        bot.setLayout(null);
        // 하단 : 로그인부분
        // 닉네임 입력 부분 설정
        JTextField inputID = new JTextField();
        inputID.setFont(inputID.getFont().deriveFont(24.0f));

        // 서버 입력 부분 설정
        JTextField inputServer = new JTextField();
        inputServer.setFont(inputServer.getFont().deriveFont(24.0f));

        // NICKNAME 라벨 설정
        JLabel labelID = new JLabel("NICKNAME");
        labelID.setForeground(new Color(0, 153, 68));
        labelID.setFont(labelID.getFont().deriveFont(24.0f));
        // SERVER 라벨 설정
        JLabel labelServer = new JLabel("SERVER");
        labelServer.setForeground(new Color(0, 153, 68));
        labelServer.setFont(labelServer.getFont().deriveFont(24.0f));

        // 로그인 버튼 라벨 설정
        // Image 파일 읽어오기
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("C:\\Users\\WanH\\IdeaProjects\\OOP1\\src\\loginButton.png"));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        // Image 라벨에 적용 및 배치
        JLabel picLabel = new JLabel(new ImageIcon( myPicture ));
        picLabel.setBounds(800,0, 200,200);

        // 하단 : 크기, 위치 선정
        inputID.setBounds(350, 50, 400, 48);
        inputServer.setBounds(350, 110, 400, 48);
        labelID.setBounds(200, 50, 150, 48);
        labelServer.setBounds(200, 110, 150, 48);

        // 구성요소 추가
        bot.add(inputID);
        bot.add(inputServer);
        bot.add(labelID);
        bot.add(labelServer);
        bot.add(picLabel);
        background.add(bot);

        // 프레임 정리
        frame.setContentPane(background);
        frame.setVisible(true);
    }
}
