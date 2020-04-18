/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Almas Asghar
 */
public class MainCtrl {
    Main_Page mfr;
    public MainCtrl(Main_Page  fr1)
    {
       mfr=fr1;
    }
    public void LogOut()
    {
        LogInFrame lfr=new LogInFrame();
        lfr.setVisible(true);
        mfr.hide();
    }
    public void OpenStudentForm()
    {
       Student_Form fr=new Student_Form();
        fr.setVisible(true);
        mfr.hide();   
    }
    public void OpenEmployeForm()
    {
        Staff_form fr=new Staff_form();
        fr.setVisible(true);
        mfr.hide();
    }
    public void OpenVisitorsForm()
    {
        /*Visitors_form fr=new Visitors_form();
        fr.setVisible(true);
        mfr.hide();*/
    }
    public void OpenFeeForm()
    {
        /*Fee_Form fr=new Fee_Form();
        fr.setVisible(true);
        mfr.hide();*/
    }
    public void OpenMessForm()
    {
        Mess_Form fr=new Mess_Form();
        fr.setVisible(true);
        mfr.hide();
    }
    public void OpenRoomForm()
    {
       Room_Form fr=new Room_Form();
        fr.setVisible(true);
        mfr.hide();
    }
    public void OpenAttendanceForm()
    {
        Attendance_Form fr=new Attendance_Form();
        fr.setVisible(true);
        mfr.hide();
    }
    public void OpenInVoiceForm()
    {
        Invoice_Form fr=new Invoice_Form();
        fr.setVisible(true);
        mfr.hide();
    }
}
