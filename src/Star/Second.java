package Star;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
public class Second extends JFrame implements ActionListener{
	private JButton Message;
	private JButton Personnel_management_system;
	private JButton quit;
	public Second(){
		Message = new JButton("个人信息界面");
		Message.addActionListener(this);
		Personnel_management_system =new JButton("人事管理系统");
		Personnel_management_system.addActionListener(this);
		quit = new JButton("退出");
		quit.addActionListener(this);
		this.add(Message);
		this.add(Personnel_management_system);
		this.add(quit);
		this.setLayout(new GridLayout(3,1));
		GetScrHeiAndWid get = new GetScrHeiAndWid();
		this.setLocation(get.Center_Width(),get.Center_Height());
		this.setSize(400,150);//宽，高
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == Message){
			new Message();
			dispose();
		}
		else if(e.getSource() == Personnel_management_system){
			new Personnel_management_system();
			dispose();
		}else if(e.getSource() == quit){
			new Enter();
			dispose();
		}
	}

}

