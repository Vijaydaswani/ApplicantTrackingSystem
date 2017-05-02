/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CE;

/**
 *
 * @author HP
 */
public class Candidates {
    
    private int ID;
    private String Name;
    private String Pos;
    private String Cl;
    private String Loc;
    private String Con;
    private String Email;
    private int Ex;
    private String Remark;
    private String Rate;
    private String SDate;
    private String IDate;
    
    
    
    public Candidates(int id, String name,String position,String client,String location,String contact,String email_id,int experience,String remark,String rate,String Sdate,String Idate)
    {
        this.ID=id;
        this.Name=name;
        this.Pos=position;
        this.Cl=client;
        this.Loc=location;
        this.Con=contact;
        this.Email=email_id;
        this.Ex=experience;
        this.Remark=remark;
        this.Rate=rate;
        this.SDate=Sdate;
        this.IDate=Idate;
    }
    public int getId()
    {
        return ID;
    }
    public String getName()
    {
        return Name;
    }
        
    public String getPos()
    {
        return Pos;
    }    
        public String getCl()
    {
        return Cl;
    }   
        public String getLoc()
    {
        return Loc;
    }   
        public String getCon()
    {
        return Con;
    }    
        public String getEmail()
    {
        return Email;
    }   
        public int getEx()
    {
        return Ex;
    }
        public String getRemark()
    {
        return Remark;
    }
        public String getRate()
    {
        return Rate;
    }
        public String getSDate()
    {
        return SDate;
    }
        public String getIDate()
    {
        return IDate;
    }
        
}
