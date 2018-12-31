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
public class Personnel_management_system extends JFrame implements ActionListener{
	private JButton new_person;
	private JButton personnel_change;
	private JButton staff_record;
	private JButton quit;
	public Personnel_management_system(){
		new_person = new JButton("新员工档案输入,查询和修改");
		new_person.addActionListener(this);
		personnel_change =new JButton("人事变更");
		personnel_change.addActionListener(this);
		staff_record = new JButton("部门信息");
		staff_record.addActionListener(this);
		quit = new JButton("退出");
		quit.addActionListener(this);
		this.add(new_person);
		this.add(personnel_change);
		this.add(staff_record);
		this.add(quit);
		this.setLayout(new GridLayout(4,1));
		GetScrHeiAndWid get = new GetScrHeiAndWid();
		this.setLocation(get.Center_Width(),get.Center_Height());
		this.setSize(400,150);//宽，高
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == new_person){
			new New_person();
			dispose();
		}
		else if(e.getSource() == personnel_change){
			new Personnel();
			dispose();
		}
		else if(e.getSource() == staff_record){
			new Department();
			dispose();
		}else if(e.getSource() == quit){
			new Menu();
			dispose();
		}
	}

}
