package Star;

import javax.swing.*;

import java.awt.event.*;
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
public class Message extends JFrame implements ActionListener{

	JLabel jLPersonInfoTable = null;
	JLabel jLPPassword = null;
	JLabel jLPName = null;
	JLabel jLPSex = null;
	JLabel jLPBirthday = null;
	JLabel jLPDepartment = null;
	JLabel jLPJob = null;
	JLabel jLPEdu_level = null;
	JLabel jLPSpecialty = null;
	JLabel jLPAddress = null;
	JLabel jLPTel = null;
	JLabel jLPEmail = null;
	JLabel jLPState = null;
	JLabel jLPRemark = null;
	
	JTextField jTFPPassword = null;
	JTextField jTFPName = null;
	JTextField jTFPSex = null;
	JTextField jTFPBirthday = null;
	JTextField jTFPDepartment = null;
	JTextField jTFPJob = null;
	JTextField jTFPEdu_level = null;
	JTextField jTFPSpecialty = null;
	JTextField jTFPAddress = null;
	JTextField jTFPTel = null;
	JTextField jTFPEmail = null;
	JTextField jTFPState = null;
	JTextField jTFPRemark = null;
	
	JButton jBUpdate = null;//修改
	JButton jBExit = null;//返回
	
	JComboBox<String> jCBSelectQueryField1 = null;
	JComboBox<String> jCBSelectQueryField2 = null;
	JPanel jP1, jP2,jP3,jP4,jP5,jP6,jP7,jP8,jP9,jP10,jP11,jP12,jP13,jP14,jP15,jP16= null;
	JPanel jPTop, jPBottom = null;
	DefaultTableModel personTableModel = null;
	JTable pesonJTable;

	 public static DbProcess dbProcess;
	
	public Message(){
		super("员工个人信息");
		jLPPassword = new JLabel("密码");
		jLPName = new JLabel("姓名");
		jLPSex = new JLabel("性别");
		jLPBirthday =  new JLabel("生日");
		jLPDepartment = new JLabel("所在部门");
		jLPJob = new JLabel("职务");
		jLPEdu_level = new JLabel("受教育程度");
		jLPSpecialty = new JLabel("专业技能");
		jLPAddress = new JLabel("家庭住址");
		jLPTel = new JLabel("联系电话");
		jLPEmail = new JLabel("电子邮箱");
		jLPState = new JLabel("当前状态");
		jLPRemark = new JLabel("备注");
		
		jTFPPassword = new JTextField(20);
		jTFPName = new JTextField(20);
		jTFPSex = new JTextField(20);
		jTFPBirthday = new JTextField(20);
		jTFPDepartment = new JTextField(20);
		jTFPJob = new JTextField(20);
		jTFPEdu_level = new JTextField(20);
		jTFPSpecialty = new JTextField(20);
		jTFPAddress = new JTextField(20);
		jTFPTel = new JTextField(20);
		jTFPEmail = new JTextField(20);
		jTFPState = new JTextField(20);
		jTFPRemark = new JTextField(20);
		
		jBUpdate = new JButton("修改");
		jBExit = new JButton("返回");
		
		jBUpdate.addActionListener(this);
		jBExit.addActionListener(this);
		
		
		
		
		
		jP3 = new JPanel();
		jP4 = new JPanel();
		jP5 = new JPanel();
		jP6 = new JPanel();
		jP7 = new JPanel();
		jP8 = new JPanel();
		jP9 = new JPanel();
		jP10 = new JPanel();
		jP11 = new JPanel();
		jP12 = new JPanel();
		jP13 = new JPanel();
		jP14 = new JPanel();
		jP15 = new JPanel();
		jP16 = new JPanel();
	
		jPTop = new JPanel();
		jPBottom = new JPanel();
		
	
		jP3.add(jLPPassword);
		jP3.add(jTFPPassword);
		jP3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jP3.setPreferredSize(new Dimension(20,20));
		
		jP4.add(jLPName);
		jP4.add(jTFPName);
		jP4.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jP4.setPreferredSize(new Dimension(20,20));
		
		jP5.add(jLPSex);
		jP5.add(jTFPSex);
		jP5.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jP5.setPreferredSize(new Dimension(20,20));
		
		jP6.add(jLPBirthday);
		jP6.add(jTFPBirthday);
		jP6.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jP6.setPreferredSize(new Dimension(20,20));
		
		jP7.add(jLPDepartment);
		jP7.add(jTFPDepartment);
		jP7.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jP7.setPreferredSize(new Dimension(20,20));
		
		jP8.add(jLPJob);
		jP8.add(jTFPJob);
		jP8.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jP8.setPreferredSize(new Dimension(20,20));
		
		jP9.add(jLPEdu_level);
		jP9.add(jTFPEdu_level);
		jP9.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jP9.setPreferredSize(new Dimension(20,20));
		
		jP10.add(jLPSpecialty);
		jP10.add(jTFPSpecialty);
		jP10.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jP10.setPreferredSize(new Dimension(20,20));
		
		jP11.add(jLPAddress);
		jP11.add(jTFPAddress);
		jP11.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jP11.setPreferredSize(new Dimension(20,20));
		
		jP12.add(jLPTel);
		jP12.add(jTFPTel);
		jP12.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jP12.setPreferredSize(new Dimension(20,20));
		
		jP13.add(jLPEmail);
		jP13.add(jTFPEmail);
		jP13.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jP13.setPreferredSize(new Dimension(20,20));
		
		jP14.add(jLPState);
		jP14.add(jTFPState);
		jP14.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jP14.setPreferredSize(new Dimension(20,20));
		
		jP15.add(jLPRemark);
		jP15.add(jTFPRemark);
		jP15.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jP15.setPreferredSize(new Dimension(20,20));
		
		jP16.add(jBUpdate);
		jP16.add(jBExit);
		jP16.setLayout(new FlowLayout(FlowLayout.CENTER));
		jP16.setPreferredSize(new Dimension(20,20));
		
		
		jPBottom.setLayout(new GridLayout(14, 1));
		jPBottom.add(jP3);
		jPBottom.add(jP4);
		jPBottom.add(jP5);
		jPBottom.add(jP6);
		jPBottom.add(jP7);
		jPBottom.add(jP8);
		jPBottom.add(jP9);
		jPBottom.add(jP10);
		jPBottom.add(jP11);
		jPBottom.add(jP12);
		jPBottom.add(jP13);
		jPBottom.add(jP14);
		jPBottom.add(jP15);
		jPBottom.add(jP16);
		
		this.add("South", jPBottom);
		
		this.setLayout(new GridLayout(1, 1));
		this.setSize(350, 600);//跳出窗的尺寸，宽，高
		this.setLocation(300, 150);//跳出窗口的位置，左右，上下
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		dbProcess = new DbProcess();
		
	}
	
	public void actionPerformed(ActionEvent e) {	
		 if(e.getActionCommand().equals("修改")
				&& !jTFPPassword.getText().isEmpty()
				&& !jTFPName.getText().isEmpty()
				&& !jTFPSex.getText().isEmpty()
				&& !jTFPBirthday.getText().isEmpty()
				&& !jTFPDepartment.getText().isEmpty()
				&& !jTFPJob.getText().isEmpty()
				&& !jTFPEdu_level.getText().isEmpty()
				&& !jTFPSpecialty.getText().isEmpty()
				&& !jTFPAddress.getText().isEmpty()
				&& !jTFPTel.getText().isEmpty()
				&& !jTFPEmail.getText().isEmpty()
				&& !jTFPState.getText().isEmpty()
				&& !jTFPRemark.getText().isEmpty()){
			System.out.println("actionPerformed(). 修改");
			updateProcess();
			this.dispose();
		     new Enter();
			
		}else if(e.getActionCommand().equals("返回")){
			this.dispose();
			new Enter();
			
}
		
	}

	public void updateProcess()
	{
		
		String PPassword = jTFPPassword.getText().trim();
		String PName = jTFPName.getText().trim();
		String PSex = jTFPSex.getText().trim();
		String PBirthday = jTFPBirthday.getText().trim();
		String PDepartment = jTFPDepartment.getText().trim();
		String PJob = jTFPJob.getText().trim();
		String PEdu_level = jTFPEdu_level.getText().trim();
		String PSpecialty = jTFPSpecialty.getText().trim();
		String PAddress = jTFPAddress.getText().trim();
		String PTel = jTFPTel.getText().trim();
		String PEmail = jTFPEmail.getText().trim();
		String PState = jTFPState.getText().trim();
		String PRemark = jTFPRemark.getText().trim();
		
		
		// 建立插入条件
		String sql = "update person set PPassword = '";
		sql = sql + PPassword + "', PName = '";
		sql = sql + PName + "', PSex = '";
		sql = sql + PSex + "', PBirthday = '";
		sql = sql + PBirthday + "', PDepartment = '";
		sql = sql + PDepartment + "', PJob = '";
		sql = sql + PJob + "',  PEdu_level = '";
		sql = sql + PEdu_level + "', PSpecialty = '";
		sql = sql + PSpecialty + "', PAddress = '";
		sql = sql + PAddress + "', PTel = '";
		sql = sql + PTel + "', PEmail = '";
		sql = sql + PEmail + "', PState = '";
		sql = sql + PState + "', PRemark = '";
		sql = sql + PRemark + "'";
		sql = sql + " WHERE PID = "+ (Enter.jtxtName.getText().trim())+";";
		System.out.println("updateProcess(). sql = " + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("updateProcess(). update database failed.");
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/*public static void main(String[] args){
		Message m = new Message();
	}*/
}



