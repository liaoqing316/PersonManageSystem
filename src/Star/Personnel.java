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

public class Personnel extends JFrame implements ActionListener {
	
	JLabel jLPersonnelInfoTable = null;
	JLabel jLSelectQueryField = null;
	JLabel jLEqual = null;
	JLabel jLPNPerson = null;
	JLabel jLPNChange = null;
	JLabel jLPNDescription = null;


	JTextField jTfQueryField = null;
	JTextField jTFPNID = null;
	JTextField jTFPNPerson = null;
	JTextField jTFPNChange = null;
	JTextField jTFPNDescription = null;
	

	JButton jBQuery = null;// 查询
	JButton jBQueryAll = null;// 查询所有记录
	JButton jBInsert = null;// 插入
	JButton jBUpdate = null;// 更新
	JButton jBDeleteCurrentRecord = null;// 删除当前记录
	JButton jBDeleteAllRecords = null;// 删除所有记录
	JButton jBExit = null;

	// JComboBox jCBSelectQueryField = null;
	JComboBox<String> jCBSelectQueryField = null;// 查询字段
	JPanel jP1, jP2, jP3, jP4 ,jP5= null;
	JPanel jPTop, jPBottom = null;
	DefaultTableModel personnel_changeTableModel = null;
	JTable personnelJTable;
	JScrollPane personnelJScrollPane = null;
	Vector personnelVector = null;
	Vector titleVector = null;

	private static DbProcess dbProcess;
	String SelectQueryFieldStr = "记录编号";

	public Personnel() {
		super("人事管理系统");
		jLPersonnelInfoTable = new JLabel("人事变更记录表");
		jLSelectQueryField = new JLabel("选择查询字段");
		jLEqual = new JLabel("=");
		jLPNPerson = new JLabel("员工号");
		jLPNChange = new JLabel("变更代码");
		jLPNDescription = new JLabel("详细记录");

		jTfQueryField = new JTextField(10);// 查询框
		jTFPNID = new JTextField(20);
		jTFPNPerson = new JTextField(15);
		jTFPNChange = new JTextField(15);
		jTFPNDescription = new JTextField(15);
		
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
		jCBSelectQueryField.addItem("记录编号");
		jCBSelectQueryField.addItem("员工号");
		jCBSelectQueryField.addItem("变更代码");
		jCBSelectQueryField.addItem("详细记录");
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

		personnelVector = new Vector();
		titleVector = new Vector();
		// 定义表头
		titleVector.add("记录编号");
		titleVector.add("员工号");
		titleVector.add("变更代码");
		titleVector.add("详细记录");
		// personTableModel = new DefaultTableModel(tableTitle, 15);
		personnelJTable = new JTable(personnelVector, titleVector);
		personnelJTable.setPreferredScrollableViewportSize(new Dimension(700, 300));// 员工信息表框的长宽
		personnelJScrollPane = new JScrollPane(personnelJTable);
		// 分别设置水平和垂直滚动条自动出现
		personnelJScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		personnelJScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		// 为表格添加监听器
		personnelJTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置
				System.out.println("mouseClicked(). row = " + row);
				Vector v = new Vector();
				v = (Vector) personnelVector.get(row);

				jTFPNID.setText((String) v.get(0));
				jTFPNPerson.setText((String) v.get(1));
				jTFPNChange.setText((String) v.get(2));
				jTFPNDescription.setText((String) v.get(3));
			}
		});

		jP1 = new JPanel();
		jP2 = new JPanel();
		jP3 = new JPanel();
		jP4 = new JPanel();
		jP5 = new JPanel();
		jPTop = new JPanel();
		jPBottom = new JPanel();

		jP1.add(jLPersonnelInfoTable, BorderLayout.SOUTH);
		jP2.add(personnelJScrollPane);

		jP3.add(jLSelectQueryField);
		jP3.add(jCBSelectQueryField);
		jP3.add(jLEqual);
		jP3.add(jTfQueryField);
		jP3.add(jBQuery);
		jP3.add(jBQueryAll);
		jP3.setLayout(new FlowLayout(FlowLayout.CENTER));
		jP3.setPreferredSize(new Dimension(20, 20));

		
		jP4.add(jLPNPerson);
		jP4.add(jTFPNPerson);
		jP4.add(jLPNChange);
		jP4.add(jTFPNChange);
		jP4.add(jLPNDescription);
		jP4.add(jTFPNDescription);
		jP4.setLayout(new FlowLayout(FlowLayout.LEFT));
		jP4.setPreferredSize(new Dimension(20, 20));

		

		jP5.add(jBInsert);
		jP5.add(jBUpdate);
		jP5.add(jBDeleteCurrentRecord);
		jP5.add(jBDeleteAllRecords);
		jP5.add(jBExit);
		jP5.setLayout(new FlowLayout(FlowLayout.CENTER));
		jP5.setPreferredSize(new Dimension(20,20));

		jPTop.add(jP1);
		jPTop.add(jP2);

		jPBottom.setLayout(new GridLayout(3, 1));
		jPBottom.add(jP3);
		jPBottom.add(jP4);
		jPBottom.add(jP5);
		

		this.add("North", jPTop);
		this.add("South", jPBottom);

		this.setLayout(new GridLayout(2, 1));
		this.setSize(800, 800);// 跳出窗的尺寸，宽，高
		this.setLocation(150, 0);// 跳出窗口的位置，左右，上下
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

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
				
				&& !jTFPNPerson.getText().isEmpty()
				&& !jTFPNChange.getText().isEmpty()
				&& !jTFPNDescription.getText().isEmpty()) {
			System.out.println("actionPerformed(). 插入");
			insertProcess();
		} else if (e.getActionCommand().equals("更新")
				&& !jTFPNID.getText().isEmpty()
				&& !jTFPNPerson.getText().isEmpty()
				&& !jTFPNChange.getText().isEmpty()
				&& !jTFPNDescription.getText().isEmpty()) {
			System.out.println("actionPerformed(). 更新");
			updateProcess();
		} else if (e.getActionCommand().equals("删除当前记录")) {
			System.out.println("actionPerformed(). 删除当前记录");
			deleteCurrentRecordProcess();
		} else if (e.getActionCommand().equals("删除所有记录")) {
			System.out.println("actionPerformed(). 删除所有记录");
			deleteAllRecordsProcess();
		} else if(e.getActionCommand().equals("返回")){
			new Personnel_management_system();
			dispose();
		}
	}

	public void queryProcess(String sQueryField) {
		try {
			// 建立查询条件
			String sql = "select PNID,PNPerson,personnel_change.PCDescription,PNDescription"+
                    " from personnel"+
                    " inner join personnel_change"+
                    " on personnel.PNChange=personnel_change.PCCode where ";
			String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);

			if (queryFieldStr.equals("PNID")) {// int PNID.
				sql = sql + queryFieldStr;
				sql = sql + " = " + sQueryField;
			} else {
				sql = sql + queryFieldStr;
				sql = sql + " = ";
				sql = sql + "'" + sQueryField + "';";
			}

			System.out.println("queryProcess(). sql = " + sql);

			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);

			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			personnelVector.clear();
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("PNID"));
				v.add(rs.getString("PNPerson"));
				v.add(rs.getString("PCDescription"));
				v.add(rs.getString("PNDescription"));

				personnelVector.add(v);
			}

			personnelJTable.updateUI();

			dbProcess.disconnect();
		} catch (SQLException sqle) {
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void queryAllProcess() {
		try {
			// 建立查询条件
			String sql = "select PNID,PNPerson,personnel_change.PCDescription,PNDescription"+
                         " from personnel"+
                         " inner join personnel_change"+
                         " on personnel.PNChange=personnel_change.PCCode" +
                         " order by PNID; ";
			System.out.println("queryAllProcess(). sql = " + sql);

			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);

			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			personnelVector.clear();
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("PNID"));
				v.add(rs.getString("PNPerson"));
				v.add(rs.getString("PCDescription"));
				v.add(rs.getString("PNDescription"));
				
				personnelVector.add(v);
			}
			personnelJTable.updateUI();

			dbProcess.disconnect();
		} catch (SQLException sqle) {
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	

	public void insertProcess() {
		String PNID = "null";
		String PNPerson = jTFPNPerson.getText().trim();
		String PNChange = jTFPNChange.getText().trim();
		String PNDescription = jTFPNDescription.getText().trim();

		// 建立插入条件
		String sql = "insert into personnel values(";
		sql = sql + PNID + ",'";
		sql = sql + PNPerson + "','";
		sql = sql + PNChange + "','";
		sql = sql + PNDescription + "');";

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
		String PNID = jTFPNID.getText().trim();
		String PNPerson = jTFPNPerson.getText().trim();
		String PNChange = jTFPNChange.getText().trim();
		String PNDescription = jTFPNDescription.getText().trim();
		// 建立更新条件
		String sql = "update personnel set PNPerson = '";
		sql = sql + PNPerson + "', PNChange = '";
		sql = sql + PNChange + "', PNDescription = '";
		sql = sql + PNDescription + "'";
		sql = sql + " where PNID = " + PNID + ";";
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
		String PNID = jTFPNID.getText().trim();

		// 建立删除条件
		String sql = "delete from personnel where PNPID = " + PNID + ";";
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
		String sql = "delete from personnel;";
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

		if (InputStr.equals("记录编号")) {
			outputStr = "PNID";
		} else if (InputStr.equals("员工号")) {
			outputStr = "PNPerson";
		} else if (InputStr.equals("变更代码")) {
			outputStr = "PNChange";
		} else if (InputStr.equals("详细记录")) {
			outputStr = "PNDescription";
		} 
		System.out.println("jCBSelectQueryFieldTransfer(). outputStr = "
				+ outputStr);
		return outputStr;
	}
	/*public static void main(String[] args){
		Personnel ps = new Personnel();
	}*/

}
