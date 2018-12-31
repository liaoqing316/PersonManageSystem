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
public class New_person extends JFrame implements ActionListener{
	
	JLabel jLPersonInfoTable = null;
	JLabel jLSelectQueryField = null;
	JLabel jLEqual = null;
	JLabel jLPAuthority = null;
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
	
	JTextField jTfQueryField = null;
	JTextField jTFPID = null;
	JTextField jTFPAuthority = null;
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
	
	JButton jBQuery = null;//查询
	JButton jBQueryAll = null;//查询所有记录
	JButton jBInsert = null;//插入
	JButton jBUpdate = null;//更新
	JButton jBDeleteCurrentRecord = null;//删除当前记录
	JButton jBDeleteAllRecords = null;//删除所有记录
	JButton jBExit = null;//返回
	
	//JComboBox jCBSelectQueryField = null;
	JComboBox<String> jCBSelectQueryField = null;//查询字段
	JPanel jP1, jP2,jP3,jP4,jP5,jP6,jP7,jP8 = null;
	JPanel jPTop, jPBottom = null;
	DefaultTableModel personTableModel = null;
	static JTable personJTable = null;
	JScrollPane personJScrollPane = null;
	static Vector personVector = null;
	Vector titleVector = null;
	
	private static DbProcess dbProcess;
	String SelectQueryFieldStr = "员工号";
	
	public New_person(){
		super("人事管理系统");
		jLPersonInfoTable = new JLabel("员工个人信息表");
		jLSelectQueryField = new JLabel("选择查询字段");
		jLEqual = new JLabel("=");
		jLPAuthority = new JLabel("用户权限");
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
		
		jTfQueryField = new JTextField(20);//查询框
		jTFPID = new JTextField(20);
		jTFPAuthority = new JTextField(15);
		jTFPName = new JTextField(15);
		jTFPSex = new JTextField(15);
		jTFPBirthday = new JTextField(15);
		jTFPDepartment = new JTextField(20);
		jTFPJob = new JTextField(20);
		jTFPEdu_level = new JTextField(20);
		jTFPSpecialty = new JTextField(20);
		jTFPAddress = new JTextField(20);
		jTFPTel = new JTextField(20);
		jTFPEmail = new JTextField(20);
		jTFPState = new JTextField(20);
		jTFPRemark = new JTextField(20);
		
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
		
		jCBSelectQueryField = new JComboBox<String>();//查询字段
		jCBSelectQueryField.addItem("员工号");
		jCBSelectQueryField.addItem("用户权限");
		jCBSelectQueryField.addItem("姓名");
		jCBSelectQueryField.addItem("性别");
		jCBSelectQueryField.addItem("生日");
		jCBSelectQueryField.addItem("所在部门");  
		jCBSelectQueryField.addItem("职务");  
		jCBSelectQueryField.addItem("受教育程度");
		jCBSelectQueryField.addItem("专业技能");
		jCBSelectQueryField.addItem("家庭住址");
		jCBSelectQueryField.addItem("联系电话");
		jCBSelectQueryField.addItem("电子邮箱");
		jCBSelectQueryField.addItem("当前状态（T-员工，F-非员工）");
		jCBSelectQueryField.addItemListener(new ItemListener() {//下拉框事件监听  
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
		
		personVector = new Vector();
		titleVector = new Vector();
		// 定义表头
		titleVector.add("员工号");
		titleVector.add("用户权限");
	    titleVector.add("姓名");
		titleVector.add("性别");
		titleVector.add("生日");
		titleVector.add("所在部门");
		titleVector.add("职务");
		titleVector.add("受教育程度");
	    titleVector.add("专业技能");
		titleVector.add("家庭住址");
		titleVector.add("联系电话");
		titleVector.add("电子邮箱");
		titleVector.add("当前状态（T-员工，F-非员工）");
		titleVector.add("备注");
		//personTableModel = new DefaultTableModel(tableTitle, 15);
		personJTable = new JTable(personVector, titleVector);
		personJTable.setPreferredScrollableViewportSize(new Dimension(900,340));//员工信息表框的长宽
		personJScrollPane = new JScrollPane(personJTable);
		//分别设置水平和垂直滚动条自动出现
		personJScrollPane.setHorizontalScrollBarPolicy(                
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		personJScrollPane.setVerticalScrollBarPolicy(                
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		//为表格添加监听器 
		personJTable.addMouseListener(new MouseAdapter()
		{ 
			public void mouseClicked(MouseEvent e) 
			{ 
				int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置
				System.out.println("mouseClicked(). row = " + row);
				Vector v = new Vector();
				v = (Vector) personVector.get(row);

				jTFPID.setText((String) v.get(0));
				jTFPAuthority.setText((String) v.get(1));
				jTFPName.setText((String) v.get(2));
				jTFPSex.setText((String) v.get(3));
				jTFPBirthday.setText((String) v.get(4));
				jTFPDepartment.setText((String) v.get(5));
				jTFPJob.setText((String) v.get(6));
				jTFPEdu_level.setText((String) v.get(7));
				jTFPSpecialty.setText((String) v.get(8));
				jTFPAddress.setText((String) v.get(9));
				jTFPTel.setText((String) v.get(10));
				jTFPEmail.setText((String) v.get(11));
				jTFPState.setText((String) v.get(12));
				jTFPRemark.setText((String) v.get(13));
				
			}
		});
		
		jP1 = new JPanel();
		jP2 = new JPanel();
		jP3 = new JPanel();
		jP4 = new JPanel();
		jP5 = new JPanel();
		jP6 = new JPanel();
		jP7 = new JPanel();
		jP8 = new JPanel();
		jPTop = new JPanel();
		jPBottom = new JPanel();
		
		jP1.add(jLPersonInfoTable,BorderLayout.SOUTH);
		jP2.add(personJScrollPane);
		
		jP3.add(jLSelectQueryField);
		jP3.add(jCBSelectQueryField);
		jP3.add(jLEqual);
		jP3.add(jTfQueryField);
		jP3.add(jBQuery);
		jP3.add(jBQueryAll);
		jP3.setLayout(new FlowLayout(FlowLayout.LEFT));
		jP3.setPreferredSize(new Dimension(20,20));
		
		jP4.add(jLPAuthority);
		jP4.add(jTFPAuthority);
		jP4.add(jLPName);
		jP4.add(jTFPName);
		jP4.add(jLPSex);
		jP4.add(jTFPSex);
		jP4.add(jLPBirthday);
		jP4.add(jTFPBirthday);
		jP4.setLayout(new FlowLayout(FlowLayout.LEFT));
		jP4.setPreferredSize(new Dimension(20,20));
		
		jP5.add(jLPDepartment);
		jP5.add(jTFPDepartment);
		jP5.add(jLPJob);
		jP5.add(jTFPJob);
		jP5.add(jLPEdu_level);
		jP5.add(jTFPEdu_level);
		jP5.setLayout(new FlowLayout(FlowLayout.LEFT));
		jP5.setPreferredSize(new Dimension(20,20));
		
		jP6.add(jLPSpecialty);
		jP6.add(jTFPSpecialty);
		jP6.add(jLPAddress);
		jP6.add(jTFPAddress);
		jP6.add(jLPTel);
		jP6.add(jTFPTel);
		jP6.setLayout(new FlowLayout(FlowLayout.LEFT));
		jP7.setPreferredSize(new Dimension(20,20));
		
		jP7.add(jLPEmail);
		jP7.add(jTFPEmail);
		jP7.add(jLPState);
		jP7.add(jTFPState);
		jP7.add(jLPRemark);
		jP7.add(jTFPRemark);
		jP7.setLayout(new FlowLayout(FlowLayout.LEFT));
		jP7.setPreferredSize(new Dimension(20,20));
		
		jP8.add(jBInsert);
		jP8.add(jBUpdate);
		jP8.add(jBDeleteCurrentRecord);
		jP8.add(jBDeleteAllRecords);
		jP8.add(jBExit);
		jP8.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		jPTop.add(jP1);
		jPTop.add(jP2);
		
		jPBottom.setLayout(new GridLayout(6, 1));
		jPBottom.add(jP3);
		jPBottom.add(jP4);
		jPBottom.add(jP5);
		jPBottom.add(jP6);
		jPBottom.add(jP7);
		jPBottom.add(jP8);
		
		
		this.add("North", jPTop);
		this.add("South", jPBottom);
		
		this.setLayout(new GridLayout(2, 1));
		this.setSize(1000, 900);//跳出窗的尺寸，宽，高
		this.setLocation(150, 0);//跳出窗口的位置，左右，上下
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		dbProcess = new DbProcess();
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("查询")  
			&& !jTfQueryField.getText().isEmpty()){
			System.out.println("actionPerformed(). 查询");
			String sQueryField = jTfQueryField.getText().trim();
			queryProcess(sQueryField);
			jTfQueryField.setText("");
		}else if(e.getActionCommand().equals("查询所有记录")) {
			System.out.println("actionPerformed(). 查询所有记录");
			queryAllProcess();

		}else if(e.getActionCommand().equals("插入")
				&& !jTFPAuthority.getText().isEmpty()
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
			System.out.println("actionPerformed(). 插入");
			insertProcess();
		}else if(e.getActionCommand().equals("更新")
				&& !jTFPAuthority.getText().isEmpty()
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
			System.out.println("actionPerformed(). 更新");
			updateProcess();
		}else if(e.getActionCommand().equals("删除当前记录")){
			System.out.println("actionPerformed(). 删除当前记录");
			deleteCurrentRecordProcess();
		}else if(e.getActionCommand().equals("删除所有记录")){
			System.out.println("actionPerformed(). 删除所有记录");
			deleteAllRecordsProcess();
		}else if(e.getSource() == jBExit){
			new Personnel_management_system();
			dispose();
		}
	}
		
	
	public void queryProcess(String sQueryField)
	{
		try{
			// 建立查询条件
			String sql = "select PID,PPassword,PAuthority,PName,PSex, PBirthday,"+
				    "department.DName,job.JDescription,edu_level.EDescription,PSpecialty,PAddress,PTel,PEmail,PState,PRemark"+  
					" from person"+ 
					" inner join department on person.PDepartment=department.DID"+ 
					" inner join job on person.PJob=job.JCode"+
					" inner join edu_level on person.PEdu_level=Edu_level.Ecode where ";
			String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);
		
			if(queryFieldStr.equals("PID")){//int PID.
				sql = sql + queryFieldStr;
				sql = sql + " = " + sQueryField;
				
				
			}else{                           //char
				sql = sql + queryFieldStr;
				sql = sql + " = ";
				sql = sql + "'" + sQueryField + "';";
			}
			
			System.out.println("queryProcess(). sql = " + sql);
	
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);
	
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			personVector.clear();
			while(rs.next()){
				Vector v = new Vector();
				v.add(rs.getString("PID"));
				v.add(rs.getString("PAuthority"));
				v.add(rs.getString("PName"));
				v.add(rs.getString("PSex"));
				v.add(rs.getString("PBirthday"));
			    v.add(rs.getString("DName"));
				v.add(rs.getString("JDescription"));
				v.add(rs.getString("EDescription"));
				v.add(rs.getString("PSpecialty"));
				v.add(rs.getString("PAddress"));
				v.add(rs.getString("PTel"));
			    v.add(rs.getString("PEmail"));
				v.add(rs.getString("PState"));
				v.add(rs.getString("PRemark"));
				
				personVector.add(v);
			}
			
			personJTable.updateUI();

			dbProcess.disconnect();
		}catch(SQLException sqle){
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void queryAllProcess()
	{
		try{
			// 建立查询条件
			String sql = "select PID,PPassword,PAuthority,PName,PSex,PBirthday,department.DName,"+
				    "job.JDescription,edu_level.EDescription,PSpecialty,PAddress,PTel,PEmail,PState,PRemark"+  
				   	" from person"+ 
					" inner join department on person.PDepartment=department.DID"+ 
					" inner join job on person.PJob=job.JCode"+
					" inner join edu_level on person.PEdu_level=Edu_level.Ecode" +
					" order by PID;";
			System.out.println("queryAllProcess(). sql = " + sql);

			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);

			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			personVector.clear();
			while(rs.next()){
				Vector v = new Vector();
				v.add(rs.getString("PID"));
				v.add(rs.getString("PAuthority"));
				v.add(rs.getString("PName"));
				v.add(rs.getString("PSex"));
				v.add(rs.getString("PBirthday"));
				v.add(rs.getString("DName"));
				v.add(rs.getString("JDescription"));
				v.add(rs.getString("EDescription"));
				v.add(rs.getString("PSpecialty"));
				v.add(rs.getString("PAddress"));
				v.add(rs.getString("PTel"));
				v.add(rs.getString("PEmail"));
				v.add(rs.getString("PState"));
				v.add(rs.getString("PRemark"));
				personVector.add(v);
			}	 
			personJTable.updateUI();

			dbProcess.disconnect();
		}catch(SQLException sqle){
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void insertProcess()
	{

		String PID = "null";
		String PPassword = "123456";
		String PAuthority = jTFPAuthority.getText().trim();
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
		String sql = "insert into person values(";
		sql = sql + PID + ",'";
		sql = sql + PPassword + "','";
		sql = sql + PAuthority + "','";
		sql = sql + PName + "','";
		sql = sql + PSex + "','";
		sql = sql + PBirthday +"','" ;
		sql = sql + PDepartment + "','";
		sql = sql + PJob + "','";
		sql = sql + PEdu_level + "','";
		sql = sql + PSpecialty + "','";
		sql = sql + PAddress + "','";
		sql = sql + PTel +"','" ;
		sql = sql + PEmail + "','";
		sql = sql + PState + "','";
		sql = sql + PRemark + "');";

		System.out.println("insertProcess(). sql = " + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("insertProcess(). insert database failed.");
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}
		queryAllProcess();
	}
	
	public void updateProcess()
	{
		String PID = jTFPID.getText().trim();
		String PAuthority = jTFPAuthority.getText().trim();
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
		
		// 建立更新条件
		String sql = "update person set PAuthority = '";
		sql = sql + PAuthority + "', PName = '";
		sql = sql + PName + "', PSex = '";
		sql = sql + PSex + "', PBirthday = '";
		sql = sql + PBirthday + "', PDepartment = '";
		sql = sql + PDepartment + "', PJob = '";
		sql = sql + PJob + "', PEdu_level = '";
		sql = sql + PEdu_level + "', PSpecialty = '";
		sql = sql + PSpecialty + "', PAddress = '";
		sql = sql + PAddress + "', PTel = '";
		sql = sql + PTel + "', PEmail = '";
		sql = sql + PEmail + "', PState = '";
		sql = sql + PState + "', PRemark = '";
		sql = sql + PRemark + "'";
		sql = sql + " WHERE PID = " + PID + ";";
		personJTable.updateUI();
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
		queryAllProcess();
	}
	
	public void deleteCurrentRecordProcess()
	{
		String PID = jTFPID.getText().trim();
		
		// 建立删除条件
		String sql = "delete from person where PID = " + PID + ";";
		System.out.println("deleteCurrentRecordProcess(). sql = " + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("deleteCurrentRecordProcess(). delete database failed.");
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}
		queryAllProcess();
	}
	
	public void deleteAllRecordsProcess()
	{
		// 建立删除条件
		String sql = "delete from person;";
		System.out.println("deleteAllRecordsProcess(). sql = " + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("deleteAllRecordsProcess(). delete database failed.");
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}
		queryAllProcess();
	}
	
	public String jCBSelectQueryFieldTransfer(String InputStr)
	{
		String outputStr = "";
		System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);
		
		if(InputStr.equals("员工号")){
			outputStr = "PID";
		}else if(InputStr.equals("用户权限")){
			outputStr = "PAuthority";
		}else if(InputStr.equals("姓名")){
			outputStr = "PName";
		}else if(InputStr.equals("性别")){
			outputStr = "PSex";
		}else if(InputStr.equals("生日")){
			outputStr = "PBirthday";
		}else if(InputStr.equals("所在部门")){
			outputStr = "PDepartment";
		}else if(InputStr.equals("职务")){
			outputStr = "PJob";
		}else if(InputStr.equals("受教育程度")){
			outputStr = "PEdu_level";
		}else if(InputStr.equals("专业技能")){
			outputStr = "PSpecialty";
		}else if(InputStr.equals("家庭住址")){
			outputStr = "PAddress";
		}else if(InputStr.equals("联系电话")){
			outputStr = "PTel";
		}else if(InputStr.equals("电子邮箱")){
			outputStr = "PEmail";
		}else if(InputStr.equals("当前状态（T-员工，F-非员工）")){
			outputStr = "PState";
		}else if(InputStr.equals("备注")){
			outputStr = "PRemark";
		}
		System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);
		return outputStr;
	}
	
	/*public static void main(String[] args){
		New_person p = new New_person();
	}*/

}

