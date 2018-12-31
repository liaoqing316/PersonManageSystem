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

public class Menu extends JFrame implements ActionListener {
	private JButton click;
	private JButton quit;
	public Menu(){
		click = new JButton("人事管理系统");
		click.addActionListener(this);
		quit = new JButton("退出");
		quit.addActionListener(this);
		this.add(click);
		this.add(quit);
		this.setTitle("主窗口");
		this.setLayout(new GridLayout(2,1));
		GetScrHeiAndWid get = new GetScrHeiAndWid();
		this.setLocation(get.Center_Width(),get.Center_Height());
		this.setSize(200,100);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == click){
			new Personnel_management_system();
			dispose();
		}else if(e.getSource() == quit){
			new Enter();
			dispose();
		}
	}

}
