import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.ListIterator;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

public class clue_main extends JFrame{
  JTextField inputID = new JTextField();
	JTextField inputServer = new JTextField();
	String id=null,server;
	GridBagLayout wait_gridbag=new GridBagLayout();
	GridBagConstraints wait_c=new GridBagConstraints();
	Label wait_l=new Label("CHAT");
	JPanel wait_chat=new JPanel(new BorderLayout());
	JPanel wait_side=new JPanel(new GridLayout(3,1));
	JButton wait_ready=new JButton();
	String[] temp={"PlayerList"};
	JList wait_l1=new JList(temp);
	
	Label wait_l3=new Label("READY");
	static JFrame frame=new clue_main();
	
	CheckboxGroup char_group=new CheckboxGroup();		
	Checkbox scarlett=new Checkbox("Scarlett",char_group,true);
	Checkbox peacock=new Checkbox("Peacock",char_group,false);
	Checkbox mustard=new Checkbox("Mustard",char_group,false);
	Checkbox plum=new Checkbox("Plum",char_group,false);
	Checkbox green=new Checkbox("Green",char_group,false);
	Checkbox white=new Checkbox("White",char_group,false);
	public clue_main(){
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
        
        inputID.setFont(inputID.getFont().deriveFont(24.0f));

        // 서버 입력 부분 설정
       
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
            myPicture = ImageIO.read(new File("./loginButton.jpg"));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        // Image 라벨에 적용 및 배치
        JButton picButton = new JButton(new ImageIcon( myPicture ));
        picButton.setBounds(800,0, 200,200);

        // 하단 : 크기, 위치 선정
        inputID.setBounds(350, 50, 400, 48);
        inputServer.setBounds(350, 110, 400, 48);
        labelID.setBounds(200, 50, 150, 48);
        labelServer.setBounds(200, 110, 150, 48);

        Next next=new Next();
        picButton.addActionListener(next);
        // 구성요소 추가
        bot.add(inputID);
        bot.add(inputServer);
        bot.add(labelID);
        bot.add(labelServer);
        bot.add(picButton);
        background.add(bot);
        add(background);
	}
	class waiting extends JFrame{
	JPanel wait_back=new JPanel(new BorderLayout());
	public waiting(){
			
			JPanel wait_l2=new JPanel(new GridLayout(6,1));
			wait_l2.add(scarlett);
			wait_l2.add(peacock);
			wait_l2.add(mustard);
			wait_l2.add(plum);
			wait_l2.add(green);
			wait_l2.add(white);
			
			Ready ready=new Ready();
			wait_ready.addActionListener(ready);
			
			wait_chat.add(wait_l);
			
			wait_ready.add(wait_l3);
			wait_side.add(wait_l1);
			wait_side.add(wait_l2);
			wait_side.add(wait_ready);
			wait_chat.setBackground(Color.green);
			
			wait_c.fill=GridBagConstraints.BOTH;
			wait_c.gridheight=3;
			wait_c.weightx=0.0;
			wait_c.weighty=1.0;
			wait_gridbag.setConstraints(wait_chat,wait_c);
			
			wait_back.add(wait_chat);
			
			wait_c.gridheight=1;
			wait_c.weighty=0.0;
			wait_gridbag.setConstraints(wait_side,wait_c);
			wait_back.add(wait_side,BorderLayout.EAST);
			add(wait_back);
			
			PlayerList pl=new PlayerList();
			pl.start();
			gogo go=new gogo();
			go.start();
			////////////////////////////////레이아웃
	}
	public class gogo extends Thread{
		public void run(){
			while(true){
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(getJSONArray(server_list())){
					frame.getContentPane().removeAll();
					frame.repaint();
					JFrame game_board=new game_board();
			
					frame.add(game_board.getRootPane());
					frame.repaint();
					frame.setVisible(true);
					break;
				}
			}
		}
	}
	class Ready implements ActionListener{
			public void actionPerformed(ActionEvent e){
				URL url=null;
				String server;
				String line="";
				BufferedReader input=null;
				String sel_char=char_group.getSelectedCheckbox().getLabel();
				
				
				server="http://sharpart.iptime.org/~clue/wr_ready.php?id="+id+"&char="+sel_char;
		 		
		 
				try {
					url=new URL(server);
					try {
						input=new BufferedReader(new InputStreamReader(url.openStream()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						while((line=input.readLine())!=null){
							System.out.println(getJSONArray(line));
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
						
			}
		}
	}
	public boolean getJSONArray(String json){
		JSONArray jsonArray=JSONArray.fromObject(JSONSerializer.toJSON(json));
		ListIterator itr=jsonArray.listIterator();
		int cnt=0;
		while(itr.hasNext()){
			JSONObject obj=(JSONObject)itr.next();
					
			JSONObject jsonObj=JSONObject.fromObject(JSONSerializer.toJSON(obj.toString()));
			Iterator itr1=jsonObj.keys();
			cnt++;
			while(itr1.hasNext()){
				Object key=itr1.next();
			//	System.out.println("key : "+key+" , value : "+jsonObj.get(key));
				
				if(key.toString().compareTo("ready")==0&&jsonObj.get(key).toString().compareTo("0")==0)
					return false;
			}
		}
		if(cnt==1)
			return false;
		return true;
	}
	public boolean getJSON(String json){
		JSONObject jsonObj=JSONObject.fromObject(JSONSerializer.toJSON(json));
		Iterator itr=jsonObj.keys();
		while(itr.hasNext()){
			Object key=itr.next();
		//	System.out.println("key : "+key+" , value : "+jsonObj.get(key));
			if(jsonObj.get(key).toString().compareTo("0")==0)
				return false;
		}
		return true;
	}
	
	public class game_board extends JFrame{
		public game_board(){
			JPanel game_back=new JPanel(new BorderLayout());
			GridBagLayout game_gridbag=new GridBagLayout();
			GridBagConstraints game_c=new GridBagConstraints();
			Label game_l=new Label("MAP");
			JPanel game_chat=new JPanel(new BorderLayout());
			JPanel game_side=new JPanel();
			
			Label game_l2=new Label("CHAT");
			
			game_chat.add(game_l);
			
			
			game_side.add(game_l2);
			
			game_chat.setBackground(Color.green);
			
			game_c.fill=GridBagConstraints.BOTH;
			game_c.gridheight=3;
			game_c.weightx=0.0;
			game_c.weighty=1.0;
			game_gridbag.setConstraints(game_chat,game_c);
			
			game_back.add(game_chat);
			
			game_c.gridheight=1;
			game_c.weighty=0.0;
			game_gridbag.setConstraints(game_side,game_c);
			game_back.add(game_side,BorderLayout.EAST);
			add(game_back);
			/******************추리카드*********/
			/*
			JPanel deduction=new JPanel();
			Label suspect=new Label("용의자");
			suspect.setBounds(1200, 700, 30,10);
			deduction.add(suspect);
			
			JPanel name=new JPanel();
			GridLayout suspect_name=new GridLayout(6,1);
			name.setLayout(suspect_name);
			name.add(new Label("머"));
			name.add(new Label("머2"));
			name.add(new Label("머3"));
			name.add(new Label("머4"));
			name.add(new Label("머5"));
			name.add(new Label("머6"));
			deduction.add(name);
			
			JPanel check=new JPanel();
			GridLayout note=new GridLayout(6,4);
			check.setLayout(note);
			for(int i=0;i<24;i++)
				check.add(new JTextField());
			check.setBounds(30,10,50,50);
			deduction.add(check);*/
			/***************************************/
			
			
			
		}
		
	}
	class Next implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			id=inputID.getText();
			server=inputServer.getText();
			if(id.compareTo("")==0)
				return;
			String line="";
			URL url=null;
			//id="jason5555";
			server="http://sharpart.iptime.org/~clue/login.php?id="+id;
			
			
			
			BufferedReader input=null;
			
			try {
				url=new URL(server);
				try {
					input=new BufferedReader(new InputStreamReader(url.openStream()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					while((line=input.readLine())!=null){
						if(getJSON(line)==false)
							return;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frame.getContentPane().removeAll();
			frame.repaint();
			JFrame waiting=new waiting();
			
			frame.add(waiting.getRootPane());
			frame.repaint();
			frame.setVisible(true);
		}
		
		
			
			
		
	}
	public void printPlayer(String json){
		JSONArray jsonArray=JSONArray.fromObject(JSONSerializer.toJSON(json));
		ListIterator itr=jsonArray.listIterator();
		String[] tmp=new String[10];
		int i=0;
		while(itr.hasNext()){
			JSONObject obj=(JSONObject)itr.next();
					
			JSONObject jsonObj=JSONObject.fromObject(JSONSerializer.toJSON(obj.toString()));
			Iterator itr1=jsonObj.keys();
			while(itr1.hasNext()){
				Object key=itr1.next();
		//		System.out.println("key : "+key+" , value : "+jsonObj.get(key));
				if(key.toString().compareTo("user_id")==0)
					tmp[i]=((String) jsonObj.get(key));
				if(key.toString().compareTo("ready")==0&&jsonObj.get(key).toString().compareTo("1")==0){
					tmp[i]=tmp[i]+" : ready";
				}
				if(key.toString().compareTo("char")==0){
					tmp[i]=tmp[i]+"("+jsonObj.get(key)+")";
				}
			}
			i++;
		}
	
		wait_l1.setListData(tmp);
		
	}
	public String server_list(){
		String server="http://sharpart.iptime.org/~clue/wr_player_list.php";
		String line="";
		URL url=null;
		BufferedReader input1=null;
		try {
			url=new URL(server);
			input1=new BufferedReader(new InputStreamReader(url.openStream()));
			while((line=input1.readLine())!=null){
				return line;
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	public class PlayerList extends Thread{
		public void run(){
			while(true){
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				printPlayer(server_list());
				
				
			}
		}
	}
    public static void main(String[] args) {
    	
        frame.setTitle("CLUE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
       
        frame.setLocationRelativeTo(null);
      
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
