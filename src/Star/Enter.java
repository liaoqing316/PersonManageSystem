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
import java.lang.invoke.StringConcatFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Vector;

public class Enter extends JFrame implements ActionListener {
	String SelectQueryFieldStr = "普通员工";
	private JPanel jp1, jp2, jp3, jp4, jp5;
	private JLabel jla = new JLabel("人事管理系统");
	JComboBox<String> jCBSelectQueryField;

	String password = null;
	String authority = null;
	String name = null;
	String sex = null;
	String birthday = null;
	String department = null;
	String job = null;
	String edu_level = null;
	String specialty = null;
	String address = null;
	String tel = null;
	String email = null;
	String state = null;
	String remark = null;
	

	private JLabel[] jlArray = { new JLabel("用户名"), new JLabel("密　码"), new JLabel("用户权限"),

			new JLabel("") };

	private JButton[] jbArray = { new JButton("登陆"),

			new JButton("清空") };

	public static JTextField jtxtName = new JTextField(15);
	public JPasswordField jtxtPassword = new JPasswordField(15);
	private Object sql;
	public static DbProcess dbProcess = new DbProcess();

	void init() {
		jCBSelectQueryField = new JComboBox<String>();
		jCBSelectQueryField.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
				case ItemEvent.SELECTED:
					SelectQueryFieldStr = (String) event.getItem();
					System.out.println("选中：" + SelectQueryFieldStr);
					break;
				case ItemEvent.DESELECTED:
					System.out.println("取消选中：" + event.getItem());
					break;
				}

			}
		});

		jCBSelectQueryField.addItem("普通员工");
		jCBSelectQueryField.addItem("人事部员工");
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp1.add(jla);
		jp2.add(jlArray[0]);
		jp2.add(jtxtName);
		jp3.add(jlArray[1]);
		jp3.add(jtxtPassword);
		jp4.add(jlArray[2]);
		jp4.add(jCBSelectQueryField);
		jp5.add(jbArray[0]);
		jp5.add(jbArray[1]);
	}

	public Enter() {
		init();
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.setTitle("登录");
		this.setLocation(300, 300);
		this.setLayout(new GridLayout(5, 1));
		this.setSize(new Dimension(300, 300));

		jbArray[0].addActionListener(this);

		jtxtPassword.setEchoChar('*');

		jbArray[1].addActionListener(this);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void queryProcess() {
		try {
			String sql = "select * from person where PID=" + (jtxtName.getText().trim()) + ";";
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);
			if (rs.next()) {
				password = (rs.getString("Ppassword"));
				authority = (rs.getString("PAuthority"));
				name = (rs.getString("PName"));
				sex = (rs.getString("PSex"));
				birthday = (rs.getString("PBirthday"));
				department = (rs.getString("PDepartment"));
				job = (rs.getString("PJob"));
				edu_level = (rs.getString("PEdu_level"));
				specialty = (rs.getString("PSpecialty"));
				address = (rs.getString("PAddress"));
				tel = (rs.getString("PTel"));
				email = (rs.getString("PEmail"));
				state = (rs.getString("PState"));
				remark = (rs.getString("PRemark"));

				System.out.println(password);
				System.out.println(authority);
				System.out.println(name);
				System.out.println(sex);
				System.out.println(birthday);
				System.out.println(department);
				System.out.println(job);
				System.out.println(edu_level);
				System.out.println(specialty);
				System.out.println(address);
				System.out.println(tel);
				System.out.println(email);
				System.out.println(state);
				System.out.println(remark);
				
			}
			dbProcess.disconnect();
		} catch (SQLException sqle) {
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null, "登录失败", "错误", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent e) {

		queryProcess();
		if (e.getActionCommand().equals("清空")) {     //清空按钮

			jlArray[2].setText("");

			jtxtName.setText("");

			jtxtPassword.setText("");

			jtxtName.requestFocus();

		} else if (e.getActionCommand().equals("登陆") && (jtxtPassword.getText().trim()).equals(password)) {

			if (SelectQueryFieldStr.equals("人事部员工")) {
				if ((SelectQueryFieldStr.toString()).equals(authority)) {
					new Second();
					this.dispose();
					jlArray[2].setText("登陆成功");
				}else {
						JOptionPane.showMessageDialog(null, "登录失败", "错误", JOptionPane.ERROR_MESSAGE);
					}

			} else if (SelectQueryFieldStr.equals("普通员工")) {
				if ((SelectQueryFieldStr.toString()).equals(authority)) {
					new Message();
					this.dispose();
					jlArray[2].setText("登陆成功");
				} else {
					JOptionPane.showMessageDialog(null, "登录失败", "错误", JOptionPane.ERROR_MESSAGE);
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "登录失败", "错误", JOptionPane.ERROR_MESSAGE);

		}
	}
}
