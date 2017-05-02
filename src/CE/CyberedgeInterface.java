/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CE;

import CE.Employee.EmployeeDetails;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
/**
 *
 * @author Vijay
 */
public class CyberedgeInterface extends javax.swing.JFrame {

    /**
     * Creates new form CyberedgeInterface
     */
    private FileInputStream fis; 
    Connection con=null;
    ResultSet rs=null;
    PreparedStatement ps= null;
    public CyberedgeInterface() {
        initComponents();
        TablePane.setVisible(false);
        ShowDateTime();
        findCandidates();
        Submit.setEnabled(false);
        OText.setText("\nCyberedge Infotech LLP,\n"+ "\n A Staffing Enterprise Solution Which deals with all recruitment needs.\n"+"And to help our Clients to hire the most qualified professionals,\n"+" in selection and highly skilled occupations,\n"+"Also developing long-tail relationships that may lead to hiring or further\n"+" networking down the road.\n"+"Our people keep your business on track.\n"+"We find the right professionals with the right skills so you can get on with business.\n"+"\n Cyberedge Infotech LLP help professionals to find the best job in their respective fields");

    }
public void UpdateTable()
{
    Connection con=DbConnection.getConnection();
    String sql="Select * from candidates_record";
   try
   {
    ps=con.prepareStatement(sql);
    rs=ps.executeQuery();
    Table.setModel(DbUtils.resultSetToTableModel(rs));
}catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
}

public void ShowDateTime()
{
    Thread t= new Thread(){
      public  void run()
            {
      for(;;)
            {
                Calendar c= new GregorianCalendar();
                int date= c.get(Calendar.DAY_OF_MONTH);
                int month= c.get(Calendar.MONTH)+1;
                int year= c.get(Calendar.YEAR);
                DLabel.setText("Date:"+date+"/"+month+"/"+year);
            
                int hour= c.get(Calendar.HOUR);  
                int min= c.get(Calendar.MINUTE);
                int sec= c.get(Calendar.SECOND);
                TLabel.setText("Time:"+hour+":"+min+":"+sec);
                
          try{
              sleep(1000);
          } catch (InterruptedException ex) {
              Logger.getLogger(CyberedgeInterface.class.getName()).log(Level.SEVERE, null, ex);
          }
            }
            }
    };
    t.start();
}
public ArrayList<Candidates> CandidateList(String search)
{
    ArrayList<Candidates> list= new ArrayList<Candidates>();
    
    Statement s=null;
    ResultSet rs=null;
   Candidates ca;
   
    try
    {
    con=DbConnection.getConnection();
    s=con.createStatement();
    String sq="SELECT * FROM `Candidates_record` WHERE CONCAT(`Name`, `Position`, `Client`, `Location`, `Remark`) LIKE '%"+search+"%'";
    rs=s.executeQuery(sq);
   
    while(rs.next())
    {
        ca=new Candidates(
                                rs.getInt("candidate_id"),
                                rs.getString("Name"),
                                rs.getString("Position"),
                                rs.getString("Client"),
                                rs.getString("Location"),
                                rs.getString("Contact"),
                                rs.getString("Email"),
                                rs.getInt("Experience"),
                                rs.getString("Remark")
        ,"","","");
    list.add(ca);
    }
    }catch(Exception e)
    {
    JOptionPane.showMessageDialog(null,e);
    }
    
    return list;
    
}
public void findCandidates()
{
    
    ArrayList<Candidates> c= CandidateList(Find.getText());
    DefaultTableModel model= new DefaultTableModel();
    model.setColumnIdentifiers(new Object[]{"ID","Name","Position","Client","Location","Contact","Email","Experience","Remark"});
    Object[] row=new Object[9];
    
    
    for(int i=0; i<c.size();i++)
    {
        row[0]= c.get(i).getId();
        row[1]= c.get(i).getName();
        row[2]= c.get(i).getPos();
        row[3]= c.get(i).getCl();
        row[4]= c.get(i).getLoc();
        row[5]= c.get(i).getCon();
        row[6]= c.get(i).getEmail();
        row[7]= c.get(i).getEx();
        row[8]= c.get(i).getRemark();
        model.addRow(row);
    }
    Table.setModel(model);
  
    int r=model.getRowCount();
            Rcount.setText("Total Records:"+" "+r);
}

public ArrayList<Candidates> SubCandidates(String search)
{
    ArrayList<Candidates> list= new ArrayList<Candidates>();
    
    Statement s=null;
    ResultSet rs=null;
   Candidates ca;
   
    try
    {
    con=DbConnection.getConnection();
    s=con.createStatement();
    String sq="SELECT * FROM `Submission` WHERE CONCAT(`Name`, `Position`, `Client`, `Location`) LIKE '%"+search+"%'";
    rs=s.executeQuery(sq);
   
    while(rs.next())
    {
        ca=new Candidates(0,
                                rs.getString("Name"),
                                rs.getString("Position"),
                                rs.getString("Client"),
                                rs.getString("Location"),
                                rs.getString("Contact"),
                                rs.getString("Email"),0,"",
                                rs.getString("Rate"),
                                rs.getString("SubmissionDate"),rs.getString("SubmissionDate")
        );
    list.add(ca);
    }
    }catch(Exception e)
    {
    JOptionPane.showMessageDialog(null,e);
    }
    
    return list;
    
}
public void findSubmission()
{
    
    ArrayList<Candidates> c= SubCandidates(Find.getText());
    DefaultTableModel model= new DefaultTableModel();
    model.setColumnIdentifiers(new Object[]{"Name","Position","Client","Location","Contact","Email","Rate","Submission Date","Interview Date"});
    Object[] row=new Object[9];
    
    
    for(int i=0; i<c.size();i++)
    {
        row[0]= c.get(i).getName();
        row[1]= c.get(i).getPos();
        row[2]= c.get(i).getCl();
        row[3]= c.get(i).getLoc();
        row[4]= c.get(i).getCon();
        row[5]= c.get(i).getEmail();
        row[6]= c.get(i).getRate();
        row[7]= c.get(i).getSDate();
        row[8]= c.get(i).getIDate();
        model.addRow(row);
    }
    SubmitTable.setModel(model);
  
    int r=model.getRowCount();
            Rcount.setText("Total Records:"+" "+r);
}
public void toExcel()
{
    
   try
   {
       con=DbConnection.getConnection();
       String query="Select * from Candidates_record";
       ps=con.prepareStatement(query);
       rs=ps.executeQuery();
   
    XSSFWorkbook w= new XSSFWorkbook();
    XSSFSheet ws= w.createSheet("Candidates Record");
    XSSFRow header=ws.createRow(0);
    header.createCell(0).setCellValue("ID");
    header.createCell(1).setCellValue("Name");
    header.createCell(2).setCellValue("Position");
    header.createCell(3).setCellValue("Client");
    header.createCell(4).setCellValue("Location");
    header.createCell(5).setCellValue("Contact");
    header.createCell(6).setCellValue("Email");
    header.createCell(7).setCellValue("Experience");
    header.createCell(8).setCellValue("Remark");
    
        ws.setColumnWidth(1,256*25);
        ws.setColumnWidth(2,256*25);
        ws.setColumnWidth(3,256*25);
        ws.setColumnWidth(4,256*25);
        ws.setColumnWidth(5,256*25);
        ws.setColumnWidth(6,256*25);
        ws.setColumnWidth(7,256*25);
        ws.setColumnWidth(8,256*25);
        ws.setColumnWidth(9,256*25);
    int index=1;
    while(rs.next())
    {
        XSSFRow row= ws.createRow(index);
        row.createCell(0).setCellValue(rs.getInt("Candidate_id"));
        row.createCell(1).setCellValue(rs.getString("Name"));
        row.createCell(2).setCellValue(rs.getString("Position"));
        row.createCell(3).setCellValue(rs.getString("Client"));
        row.createCell(4).setCellValue(rs.getString("Location"));
        row.createCell(5).setCellValue(rs.getString("Contact"));
        row.createCell(6).setCellValue(rs.getString("Email"));
        row.createCell(7).setCellValue(rs.getInt("Experience"));
        row.createCell(8).setCellValue(rs.getString("Remark"));
        index++;
        
    }
    String file="C:\\..\\..\\..\\..\\..\\Cyberedge\\CandidateDetails.xlsx";
    FileOutputStream fileout= new FileOutputStream(file);    
    w.write(fileout);
       
        
        fileout.close();

        
        JOptionPane.showMessageDialog(null,"File Saved");



        ps.close();
        rs.close();
   }catch(Exception e)
   {
       JOptionPane.showMessageDialog(null,e);
   }
    
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        UpperPane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        DLabel = new javax.swing.JLabel();
        TLabel = new javax.swing.JLabel();
        UserLabel = new javax.swing.JLabel();
        EmailLabel = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        DashPane = new javax.swing.JPanel();
        DashPane1 = new javax.swing.JPanel();
        Searches = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        SubButton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        OviewPane = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        OText = new javax.swing.JTextArea();
        TablePane = new javax.swing.JPanel();
        TableScroll = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        Find = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Submit = new javax.swing.JButton();
        Rcount = new javax.swing.JLabel();
        SubmitScroll = new javax.swing.JScrollPane();
        SubmitTable = new javax.swing.JTable();
        Delete = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        PrintMenu = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();

        setTitle("CYBEREDGE | INFOTECH");
        setBackground(new java.awt.Color(255, 255, 255));

        Panel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        UpperPane.setBackground(new java.awt.Color(156, 191, 216));
        UpperPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/logo2.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/staffing.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ce.png"))); // NOI18N

        DLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        TLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        UserLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        EmailLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jButton6.setText("Log out");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/user.png"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/email.png"))); // NOI18N

        javax.swing.GroupLayout UpperPaneLayout = new javax.swing.GroupLayout(UpperPane);
        UpperPane.setLayout(UpperPaneLayout);
        UpperPaneLayout.setHorizontalGroup(
            UpperPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpperPaneLayout.createSequentialGroup()
                .addGroup(UpperPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpperPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(UpperPaneLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel1)))
                .addGap(90, 90, 90)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(UpperPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpperPaneLayout.createSequentialGroup()
                        .addComponent(DLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UpperPaneLayout.createSequentialGroup()
                        .addGroup(UpperPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(UpperPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6))))
                .addGap(66, 66, 66))
        );
        UpperPaneLayout.setVerticalGroup(
            UpperPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpperPaneLayout.createSequentialGroup()
                .addGroup(UpperPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(UpperPaneLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(UpperPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UpperPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(UpperPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UserLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(UpperPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EmailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(43, 43, 43))
        );

        DashPane.setBackground(new java.awt.Color(156, 191, 216));
        DashPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dashboard", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(0, 0, 102))); // NOI18N

        DashPane1.setBackground(new java.awt.Color(212, 227, 238));
        DashPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Searches.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/search.icon.png"))); // NOI18N
        Searches.setText("Searches");
        Searches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchesActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/to-do.png"))); // NOI18N
        jButton2.setText("To-Do");

        SubButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/submission.png"))); // NOI18N
        SubButton.setText("Submission");
        SubButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubButtonActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/resume.png"))); // NOI18N
        jButton5.setText("Resume");

        javax.swing.GroupLayout DashPane1Layout = new javax.swing.GroupLayout(DashPane1);
        DashPane1.setLayout(DashPane1Layout);
        DashPane1Layout.setHorizontalGroup(
            DashPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashPane1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(DashPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Searches, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SubButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        DashPane1Layout.setVerticalGroup(
            DashPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashPane1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Searches)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SubButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jToggleButton1.setText("Overview");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DashPaneLayout = new javax.swing.GroupLayout(DashPane);
        DashPane.setLayout(DashPaneLayout);
        DashPaneLayout.setHorizontalGroup(
            DashPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DashPaneLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(DashPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DashPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        DashPaneLayout.setVerticalGroup(
            DashPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DashPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(304, Short.MAX_VALUE))
        );

        OviewPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Overview", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        OviewPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                OviewPaneComponentHidden(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/staffingprocess.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel5.setText("Contact: +1 925-399-4321");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel6.setText("Email: info@cyberedgeinfotech.com");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel7.setText("Website: www.cyberedgeinfotech.com");

        OText.setEditable(false);
        OText.setBackground(new java.awt.Color(240, 240, 240));
        OText.setColumns(20);
        OText.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        OText.setRows(5);
        OText.setBorder(null);
        OText.setCaretColor(new java.awt.Color(240, 240, 240));
        OText.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        OText.setEnabled(false);
        OText.setOpaque(false);
        jScrollPane1.setViewportView(OText);

        javax.swing.GroupLayout OviewPaneLayout = new javax.swing.GroupLayout(OviewPane);
        OviewPane.setLayout(OviewPaneLayout);
        OviewPaneLayout.setHorizontalGroup(
            OviewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OviewPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OviewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(OviewPaneLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addContainerGap())
            .addGroup(OviewPaneLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel5)
                .addGap(106, 106, 106)
                .addComponent(jLabel6)
                .addGap(106, 106, 106)
                .addComponent(jLabel7)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        OviewPaneLayout.setVerticalGroup(
            OviewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OviewPaneLayout.createSequentialGroup()
                .addGroup(OviewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OviewPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(OviewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(36, 36, 36))
        );

        TablePane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        TableScroll.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Table.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Candidate_ID", "Name", "Position", "Client", "Location", "Contact", "Email", "Experience", "Remark"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Table.getTableHeader().setReorderingAllowed(false);
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        TableScroll.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(1).setPreferredWidth(6);
            Table.getColumnModel().getColumn(2).setPreferredWidth(100);
            Table.getColumnModel().getColumn(3).setPreferredWidth(11);
            Table.getColumnModel().getColumn(4).setPreferredWidth(110);
            Table.getColumnModel().getColumn(5).setPreferredWidth(60);
            Table.getColumnModel().getColumn(6).setPreferredWidth(150);
        }

        jLabel8.setText("Find:");

        Find.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FindKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Candidates Details");

        Submit.setText("Submit");
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        Rcount.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        SubmitScroll.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        SubmitTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Position", "Client", "Location", "Contact", "Email", "Rate", "Submission Date", "Interview Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SubmitTable.getTableHeader().setReorderingAllowed(false);
        SubmitTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SubmitTableMouseClicked(evt);
            }
        });
        SubmitScroll.setViewportView(SubmitTable);

        Delete.setText("Delete");
        Delete.setEnabled(false);
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TablePaneLayout = new javax.swing.GroupLayout(TablePane);
        TablePane.setLayout(TablePaneLayout);
        TablePaneLayout.setHorizontalGroup(
            TablePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TablePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TablePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TablePaneLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Find, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(330, 330, 330)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Submit))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TablePaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Rcount, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TablePaneLayout.createSequentialGroup()
                        .addGroup(TablePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TableScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 1055, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SubmitScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 1055, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        TablePaneLayout.setVerticalGroup(
            TablePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TablePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TablePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Find, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(Submit)
                    .addComponent(Delete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TableScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SubmitScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Rcount, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(DashPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(OviewPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TablePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(UpperPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 4965, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(UpperPane, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DashPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OviewPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TablePane, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/add.png"))); // NOI18N
        jMenuItem1.setText("Add/Alter");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/open 22 x 22.png"))); // NOI18N
        jMenuItem3.setText("Open");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/save 22x22.png"))); // NOI18N
        jMenuItem4.setText("Save");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator2);

        PrintMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        PrintMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Print 22 x 22.png"))); // NOI18N
        PrintMenu.setText("Print");
        PrintMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintMenuActionPerformed(evt);
            }
        });
        jMenu1.add(PrintMenu);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/exit.png"))); // NOI18N
        jMenuItem6.setText("Exit");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Find");
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Tools");

        jMenuItem5.setText("Employee Details");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Send Email");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem8.setText("Dice");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Help");

        jMenuItem9.setText("Our Website");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
      TablePane.setVisible(false);
      TableScroll.setVisible(false);
      Table.setVisible(false);
        OviewPane.setVisible(true);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void SearchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchesActionPerformed
        
            OviewPane.setVisible(false);
            TablePane.setVisible(true);
            Delete.setVisible(false);
            Submit.setVisible(true);
            SubmitScroll.setVisible(false);
            TableScroll.setVisible(true);
            Table.setVisible(true);
            findCandidates();
        
    }//GEN-LAST:event_SearchesActionPerformed

    private void OviewPaneComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_OviewPaneComponentHidden
//        // TODO add your handling code here:

            
//        Fuck.setVisible(true);
//        
//        Table.setVisible(true);
    }//GEN-LAST:event_OviewPaneComponentHidden

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        
EmailClient ec= new EmailClient();

ec.setVisible(true);

    }//GEN-LAST:event_jMenuItem2ActionPerformed
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        
        
                JFrame frame = new JFrame();
                frame.setContentPane(new Candidates_Records());
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JFrame j= new JFrame();
j.add(new Filter());
        j.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void FindKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FindKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(TableScroll.isVisible())
            {
            findCandidates();
            }
            if(SubmitScroll.isVisible())
            {
                findSubmission();
            }
            }
    }//GEN-LAST:event_FindKeyPressed

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        // TODO add your handling code here:
                new SubmitFrame().setVisible(true);
        DefaultTableModel model= (DefaultTableModel) Table.getModel();
        int SelRow=Table.getSelectedRow();
        SubmitFrame.Sname.setText(model.getValueAt(SelRow,1).toString());
        SubmitFrame.Sposition.setText(model.getValueAt(SelRow,2).toString());
        SubmitFrame.Sclient.setText(model.getValueAt(SelRow,3).toString());                 
        SubmitFrame.Slocation.setText(model.getValueAt(SelRow,4).toString());        
        SubmitFrame.Scontact.setText(model.getValueAt(SelRow,5).toString());        
        SubmitFrame.Semail.setText(model.getValueAt(SelRow,6).toString());        

    }//GEN-LAST:event_SubmitActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        // TODO add your handling code here:
        Submit.setEnabled(true);
    }//GEN-LAST:event_TableMouseClicked

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        String Url="www.dice.com";
        try
        {
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(Url));
        }catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        String Url="www.cyberedgeinfotech.com";
        try
        {
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(Url));
        }catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void SubButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubButtonActionPerformed
        // TODO add your handling code here:
        OviewPane.setVisible(false);
        TablePane.setVisible(true);
        Submit.setVisible(false);
        Delete.setVisible(true);
        TableScroll.setVisible(false);
        SubmitScroll.setVisible(true);
        Rcount.setText("");
        findSubmission();
        
        
        
    }//GEN-LAST:event_SubButtonActionPerformed

    private void SubmitTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SubmitTableMouseClicked
        // TODO add your handling code here:
        Delete.setEnabled(true);
    }//GEN-LAST:event_SubmitTableMouseClicked

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel model= (DefaultTableModel) SubmitTable.getModel();
        int SR=SubmitTable.getSelectedRow();
        String s;
        s=model.getValueAt(SR,0).toString();
        
        String sql="delete from Submission Where Name=?";
        con=DbConnection.getConnection();
        try
        {
        ps=con.prepareStatement(sql);
        ps.setString(1, s);
        ps.execute();
        
        JOptionPane.showMessageDialog(null,s+"'s Record "+"Deleted");
        
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error");
        }
        findSubmission();
    }//GEN-LAST:event_DeleteActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"You Have Successfully Logged Out!!");
        setVisible(false);
        Login.pass.setText("");
        Login.username.setText("");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void PrintMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintMenuActionPerformed
        // TODO add your handling code here:
        
        MessageFormat header= new MessageFormat("Candidate Records");
        MessageFormat footer= new MessageFormat("--");
        try
        {
            Table.print(JTable.PrintMode.NORMAL,header,footer);
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Printing interrupted");
        }
    }//GEN-LAST:event_PrintMenuActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        toExcel();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        
        String s1="";
         s1=JOptionPane.showInputDialog("If you are an admin please enter the key:");
         System.out.println(s1);
        if(s1.equals("123".toString()))
        {
            
        
        
        JFrame frame = new JFrame();
                frame.setContentPane(new EmployeeDetails());
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    /**
     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CyberedgeInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CyberedgeInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CyberedgeInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CyberedgeInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CyberedgeInterface().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel DLabel;
    private javax.swing.JPanel DashPane;
    private javax.swing.JPanel DashPane1;
    private javax.swing.JButton Delete;
    public static javax.swing.JLabel EmailLabel;
    private javax.swing.JTextField Find;
    private javax.swing.JTextArea OText;
    private javax.swing.JPanel OviewPane;
    private javax.swing.JPanel Panel;
    private javax.swing.JMenuItem PrintMenu;
    private javax.swing.JLabel Rcount;
    private javax.swing.JButton Searches;
    private javax.swing.JButton SubButton;
    private javax.swing.JButton Submit;
    private javax.swing.JScrollPane SubmitScroll;
    private javax.swing.JTable SubmitTable;
    private javax.swing.JLabel TLabel;
    private javax.swing.JTable Table;
    private javax.swing.JPanel TablePane;
    private javax.swing.JScrollPane TableScroll;
    private javax.swing.JPanel UpperPane;
    public static javax.swing.JLabel UserLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
