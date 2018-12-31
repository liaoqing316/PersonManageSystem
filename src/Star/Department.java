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

public class Department extends JFrame implements ActionListener {
	
	JLabel jLDepartmentInfoTable = null;
	JLabel jLSelectQueryField = null;
	JLabel jLEqual = null;
	JLabel jLDID = null;
	JLabel jLDName = null;
	JLabel jLDManager = null;
	JLabel jLDIntro = null;

	JTextField jTfQueryField = null;
	JTextField jTFDID = null;
	JTextField jTFDName = null;
	JTextField jTFDManager = null;
	JTextField jTFDIntro = null;

	JButton jBQuery = null;// 查询
	JButton jBQueryAll = null;// 查询所有记录
	JButton jBInsert = null;// 插入
	JButton jBUpdate = null;// 更新
	JButton jBDeleteCurrentRecord = null;// 删除当前记录
	JButton jBDeleteAllRecords = null;// 删除所有记录
	JButton jBExit = null;

	// JComboBox jCBSelectQueryField = null;
	JComboBox<String> jCBSelectQueryField = null;// 查询字段
	JPanel jP1, jP2, jP3, jP4, jP5 = null;
	JPanel jPTop, jPBottom = null;
	DefaultTableModel departmentTableModel = null;
	JTable departmentJTable;
	JScrollPane departmentJScrollPane = null;
	Vector departmentVector = null;
	Vector titleVector = null;

	private static DbProcess dbProcess;
	String SelectQueryFieldStr = "部门编号";

	public Department() {
		jLDepartmentInfoTable = new JLabel("部门信息表");
		jLSelectQueryField = new JLabel("选择查询字段");
		jLEqual = new JLabel("=");
		jLDID = new JLabel("部门编号");
		jLDName = new JLabel("部门名称");
		jLDManager = new JLabel("部门经理");
		jLDIntro = new JLabel("简介");

		jTfQueryField = new JTextField(20);// 查询框
		jTFDID = new JTextField(10);
		jTFDName = new JTextField(10);
		jTFDManager = new JTextField(10);
		jTFDIntro = new JTextField(10);

		jBQuery = new JButton("查询");
		jBQueryAll = new JButton("查询所有记录");
		jBInsert = new JButton("插入");
		jBUpdate = new JButton("更新");
		jBDeleteCurrentRecord = new JButton("删除当前记录");
		jBDeleteAllRecords = new JButton("删除所有记录");
		jBExit = new JButton("返回");
		// 设置监听
		jBQuery.addActionListener(this);
		jBQueryAll.addActionListener(this);
		jBInsert.addActionListener(this);
		jBUpdate.addActionListener(this);
		jBDeleteCurrentRecord.addActionListener(this);
		jBDeleteAllRecords.addActionListener(this);
		jBExit.addActionListener(this);

		jCBSelectQueryField = new JComboBox<String>();// 查询字段
		jCBSelectQueryField.addItem("部门编号");
		jCBSelectQueryField.addItem("部门名称");
		jCBSelectQueryField.addItem("部门经理");
		jCBSelectQueryField.addItem("简介");

		jCBSelectQueryField.addItemListener(new ItemListener() {// 下拉框事件监听
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

		departmentVector = new Vector();
		titleVector = new Vector();
		// 定义表头
		titleVector.add("部门编号");
		titleVector.add("部门名称");
		titleVector.add("部门经理");
		titleVector.add("简介");

		// personTableModel = new DefaultTableModel(tableTitle, 15);
		departmentJTable = new JTable(departmentVector, titleVector);
		departmentJTable.setPreferredScrollableViewportSize(new Dimension(700,
				300));// 信息表框的长宽
		departmentJScrollPane = new JScrollPane(departmentJTable);
		// 分别设置水平和垂直滚动条自动出现
		departmentJScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		departmentJScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 为表格添加监听器
		departmentJTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置
				System.out.println("mouseClicked(). row = " + row);
				Vector v = new Vector();
				v = (Vector) departmentVector.get(row);

				jTFDID.setText((String) v.get(0));
				jTFDName.setText((String) v.get(1));
				jTFDManager.setText((String) v.get(2));
				jTFDIntro.setText((String) v.get(3));

			}
		});
		jP1 = new JPanel();
		jP2 = new JPanel();
		jP3 = new JPanel();
		jP4 = new JPanel();
		jP5 = new JPanel();
		jPTop = new JPanel();
		jPBottom = new JPanel();

		jP1.add(jLDepartmentInfoTable, BorderLayout.SOUTH);
		jP2.add(departmentJScrollPane);

		jP3.add(jLSelectQueryField);
		jP3.add(jCBSelectQueryField);
		jP3.add(jLEqual);
		jP3.add(jTfQueryField);
		jP3.add(jBQuery);
		jP3.add(jBQueryAll);
		jP3.setLayout(new FlowLayout(FlowLayout.CENTER));
		jP3.setPreferredSize(new Dimension(20, 20));

		jP4.add(jLDID);
		jP4.add(jTFDID);
		jP4.add(jLDName);
		jP4.add(jTFDName);
		jP4.add(jLDManager);
		jP4.add(jTFDManager);
		jP4.add(jLDIntro);
		jP4.add(jTFDIntro);
		jP4.setLayout(new FlowLayout(FlowLayout.CENTER));
		jP4.setPreferredSize(new Dimension(20, 20));

		jP5.add(jBInsert);
		jP5.add(jBUpdate);
		jP5.add(jBDeleteCurrentRecord);
		jP5.add(jBDeleteAllRecords);
		jP5.add(jBExit);
		jP5.setLayout(new FlowLayout(FlowLayout.CENTER));
		jP5.setPreferredSize(new Dimension(20, 20));

		jPTop.add(jP1);
		jPTop.add(jP2);

		jPBottom.setLayout(new GridLayout(3, 1));
		jPBottom.add(jP3);
		jPBottom.add(jP4);
		jPBottom.add(jP5);

		this.add("North", jPTop);
		this.add("South", jPBottom);

		this.setLayout(new GridLayout(2, 1));
		this.setTitle("人事管理系统");
		this.setSize(800, 850);// 跳出窗口的尺寸，宽，高
		this.setLocation(150, 0);// 跳出窗口的位置，左右，上下
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		dbProcess = new DbProcess();

	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("查询")
				&& !jTfQueryField.getText().isEmpty()) {
			System.out.println("actionPerformed(). 查询");
			String sQueryField = jTfQueryField.getText().trim();
			queryProcess(sQueryField);
			jTfQueryField.setText("");
		} else if (e.getActionCommand().equals("查询所有记录")) {
			System.out.println("actionPerformed(). 查询所有记录");
			queryAllProcess();

		} else if (e.getActionCommand().equals("插入")
				&& !jTFDID.getText().isEmpty() 
				&& !jTFDName.getText().isEmpty()
				&& !jTFDManager.getText().isEmpty()
				&& !jTFDIntro.getText().isEmpty()) {
			System.out.println("actionPerformed(). 插入");
			insertProcess();
		} else if (e.getActionCommand().equals("更新")
				&& !jTFDID.getText().isEmpty() 
				&& !jTFDName.getText().isEmpty()
				&& !jTFDManager.getText().isEmpty()
				&& !jTFDIntro.getText().isEmpty()) {
			System.out.println("actionPerformed(). 更新");
			updateProcess();
		} else if (e.getActionCommand().equals("删除当前记录")) {
			System.out.println("actionPerformed(). 删除当前记录");
			deleteCurrentRecordProcess();
		} else if (e.getActionCommand().equals("删除所有记录")) {
			System.out.println("actionPerformed(). 删除所有记录");
			deleteAllRecordsProcess();
		} else if (e.getActionCommand().equals("返回")) {
			new Personnel_management_system();
			dispose();
		}
	}

	public void queryProcess(String sQueryField) {
		try {
			// 建立查询条件
			String sql = "select * from department where ";
			String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);

			sql = sql + queryFieldStr;
			sql = sql + " = ";
			sql = sql + "'" + sQueryField + "';";

			System.out.println("queryProcess(). sql = " + sql);

			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);

			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			departmentVector.clear();
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("DID"));
				v.add(rs.getString("DName"));
				v.add(rs.getString("DManager"));
				v.add(rs.getString("DIntro"));

				departmentVector.add(v);
			}

			departmentJTable.updateUI();

			dbProcess.disconnect();
		} catch (SQLException sqle) {
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void queryAllProcess() {
		try {
			// 建立查询条件
			String sql = "select * from department;";
			System.out.println("queryAllProcess(). sql = " + sql);

			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);

			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			departmentVector.clear();

			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("DID"));
				v.add(rs.getString("DName"));
				v.add(rs.getString("DManager"));
				v.add(rs.getString("DIntro"));

				departmentVector.add(v);
			}
			departmentJTable.updateUI();

			dbProcess.disconnect();
		} catch (SQLException sqle) {
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void insertProcess() {
		String DID = jTFDID.getText().trim();
		String DName = jTFDName.getText().trim();
		String DManager = jTFDManager.getText().trim();
		String DIntro = jTFDIntro.getText().trim();

		// 建立插入条件
		String sql = "insert into department values('";
		sql = sql + DID + "','";
		sql = sql + DName + "','";
		sql = sql + DManager + "','";
		sql = sql + DIntro + "');";

		System.out.println("insertProcess(). sql = " + sql);
		try {
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("insertProcess(). insert database failed.");
			}
		} catch (Exception e) {
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
		queryAllProcess();
	}

	public void updateProcess() {
		String DID = jTFDID.getText().trim();
		String DName = jTFDName.getText().trim();
		String DMananger = jTFDManager.getText().trim();
		String DIntro = jTFDIntro.getText().trim();

		// 建立更新条件
		String sql = "update department set DName= '";
		sql = sql + DName + "', DManager = '";
		sql = sql + DMananger + "', DIntro = '";
		sql = sql + DIntro + "'";
		sql = sql + " WHERE DID = '" + DID + "';";
		System.out.println("updateProcess(). sql = " + sql);
		try {
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("updateProcess(). update database failed.");
			}
		} catch (Exception e) {
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
		queryAllProcess();
	}

	public void deleteCurrentRecordProcess() {
		String DID = jTFDID.getText().trim();

		// 建立删除条件
		String sql = "delete from department where DID = '" + DID + "';";
		System.out.println("deleteCurrentRecordProcess(). sql = " + sql);
		try {
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out
						.println("deleteCurrentRecordProcess(). delete database failed.");
			}
		} catch (Exception e) {
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
		queryAllProcess();
	}

	public void deleteAllRecordsProcess() {
		// 建立删除条件
		String sql = "delete from Department;";
		System.out.println("deleteAllRecordsProcess(). sql = " + sql);
		try {
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out
						.println("deleteAllRecordsProcess(). delete database failed.");
			}
		} catch (Exception e) {
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
		queryAllProcess();
	}

	public String jCBSelectQueryFieldTransfer(String InputStr) {
		String outputStr = "";
		System.out.println("jCBSelectQueryFieldTransfer(). InputStr = "
				+ InputStr);

		if (InputStr.equals("部门编号")) {
			outputStr = "DID";
		} else if (InputStr.equals("部门名称")) {
			outputStr = "DName";
		} else if (InputStr.equals("部门经理")) {
			outputStr = "DManager";
		} else if (InputStr.equals("简介")) {
			outputStr = "DIntro";

		}
		System.out.println("jCBSelectQueryFieldTransfer(). outputStr = "
				+ outputStr);
		return outputStr;
	}
	/*public static void main(String[] args){
		Department d = new Department();
	}*/

}
