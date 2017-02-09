package reservation;        

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;       

public class CancelDialog extends Dialog implements ActionListener, WindowListener,ItemListener{
    
    
    //�t�B�[���h�̒ǉ�

    boolean canceled;        //�L�����Z�����ꂽ�� true �\����s�{�^���̂Ƃ��� false
    // �p�l��
    Panel panelNorth;
    Panel panelMid;
    Panel panelSouth;
    // ���͗p�R���|�[�l���g
    ChoiceFacility choiceFacility;    // �{�ݑI��p�{�b�N�X
    TextField tfYear,tfMonth,tfDay;    // �N�����̃e�L�X�g�t�B�[���h
    ChoiceHour startHour;            // �\��J�n�����@���̑I��p�{�b�N�X
    ChoiceMinute startMinute;        // �\��J�n�����@���̑I��p�{�b�N�X
    ChoiceHour endHour;                // �\��I������ �@���̑I��p�{�b�N�X
    ChoiceMinute endMinute;            // �\��I�������@���̑I��p�{�b�N�X
    // �{�^��
    Button buttonOK;
    Button buttonCancel;

    
    public CancelDialog(Frame owner) {
        //���N���X(Dialog)�̃R���X�g���N�^���Ăяo��
        super(owner, "�\��L�����Z��(�{�݁E�L�����Z�����E���Ԃ𐳂������͂��Ă�������)", true);
        //�L�����Z���͏����l�ł�true�Ƃ��Ă���
        canceled = true;
        //�{�ݑI���{�b�N�X�̐���
        choiceFacility = new ChoiceFacility();
        //�e�L�X�g�t�B�[���h�̐����@�N����
        tfYear = new TextField("",4);
        tfMonth = new TextField("",2);
        tfDay = new TextField("",2);
        //�J�n�����@�����I���{�b�N�X�̐���
        startHour = new ChoiceHour();
        startMinute = new ChoiceMinute();
        //�I�������@�����I���{�b�N�X�̐���
        endHour = new ChoiceHour();
        endMinute = new ChoiceMinute();

        //�{�^���̐���
        buttonOK = new Button("�\�������");
        buttonCancel = new Button("�߂�");

        //�p�l���̐���
        panelNorth = new Panel();
        panelMid = new Panel();
        panelSouth = new Panel();

        //�㕔�p�l���Ɏ{�ݑI���{�b�N�X�C�N�������͗���ǉ�
        panelNorth.add( new Label("�{�݁@"));
        panelNorth.add(choiceFacility);
        panelNorth.add( new Label("�\��� "));
        panelNorth.add(tfYear);
        panelNorth.add(new Label("�N"));
        panelNorth.add(tfMonth);
        panelNorth.add(new Label("��"));
        panelNorth.add(tfDay);
        panelNorth.add(new Label("���@"));

        //�����p�l���ɗ\��@�J�n�����C�I���������͗p�I���{�b�N�X��ǉ�
        panelMid.add( new Label("�\�񎞊ԁ@"));
        panelMid.add( startHour);
        panelMid.add( new Label("��"));
        panelMid.add( startMinute);
        panelMid.add( new Label("���@�`�@"));
        panelMid.add( endHour);
        panelMid.add( new Label("��"));
        panelMid.add( endMinute);
        panelMid.add( new Label("��"));

        //�����p�l����2�̃{�^����ǉ�
        panelSouth.add(buttonCancel);
        panelSouth.add( new Label("�@�@�@�@"));
        panelSouth.add(buttonOK);

        // ReservationDialog��BorderLayout�ɐݒ肵�C3�̃p�l����ǉ�
        setLayout(new BorderLayout());
        add(panelNorth, BorderLayout.NORTH);
        add(panelMid,BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);

        // �E�B���h�E���X�i��ǉ�
        addWindowListener(this);
        // �{�^���ɃA�N�V�������X�i��ǉ�
        buttonOK.addActionListener(this);
        buttonCancel.addActionListener(this);
        //�{�ݑI���{�b�N�X�C���E���I���{�b�N�X���ꂼ��ɍ��ڃ��X�i��ǉ�
        choiceFacility.addItemListener(this);
        startHour.addItemListener(this);
        startMinute.addItemListener(this);
        endHour.addItemListener(this);
        endMinute.addItemListener(this);

        // �I������Ă���{�݂ɂ���āC�����͈̔͂�ݒ肷��D
        resetTimeRange();

        // �傫���̐ݒ�C�E�B���h�E�̃T�C�Y�ύX�s�̐ݒ�
        this.setBounds( 100, 100, 500, 120);
        setResizable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
       
        if ( e.getSource() == buttonCancel){
            setVisible(false);
            dispose();
        } else if ( e.getSource() == buttonOK){
            canceled = false;
            setVisible(false);
            dispose();
        }

    }
    
    private void resetTimeRange() {

        // �I������Ă���{�݂ɂ���āC�����͈̔͂�ݒ肷��D
        if ( choiceFacility.getSelectedIndex()==0){
            // �ŏ��̎{��(���z�[���̂Ƃ�)�̐ݒ�
            startHour.resetRange(10, 20);
            endHour.resetRange(10, 21);
        } else {
            // ���z�[���ȊO�̐ݒ�
            startHour.resetRange(9, 19);
            endHour.resetRange(9, 20);
        }
    }


    @Override
    public void windowOpened(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        setVisible(false);
        dispose();


    }

    @Override
    public void windowClosed(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        if ( e.getSource()==choiceFacility){
            
            resetTimeRange();
        } else if ( e.getSource()==startHour){
            
            int start = Integer.parseInt( startHour.getSelectedItem());
            endHour.resetRange(start, Integer.parseInt( endHour.getLast()));
        } else if ( e.getSource()==endHour){
            
            if ( endHour.getSelectedIndex()==endHour.getItemCount()-1){
                endMinute.select(0);
            }
        } if( e.getSource()==endMinute){
            
            if ( endHour.getSelectedIndex()==endHour.getItemCount()-1){
                endMinute.select(0);
            }
        }
    } 

}
