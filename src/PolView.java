import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PolView extends JFrame {
    private static CardLayout container=new CardLayout();               //obiectele ce urmeaza a fi folosite in fereastra grafica
    private static JPanel winPanel = new JPanel();
    private static JPanel menuPanel = new JPanel();
    private static JPanel op1 = new JPanel();
    private static JPanel op2 = new JPanel();
    private static JPanel op3 = new JPanel();
    private static JPanel op4 = new JPanel();
    private static JPanel op5 = new JPanel();
    private static JPanel op6 = new JPanel();
    private JComboBox choose = new JComboBox();
    private JTextField polinom1[]=new JTextField[6];
    private JTextField polinom2[]=new JTextField[6];
    private JLabel polResult[]= new JLabel[6];
    private JLabel polResult2=new JLabel("");
    private JLabel text[] =new JLabel[6];
    private JLabel textMenu=new JLabel("Alege Operatiunea");
    private JButton backButton[]= new JButton[6];
    private JButton calc[]=new JButton[6];
    private int state=0;        //pastreaza starea curenta a ferestrei grafice

    private void setFrame() {           //seteaza caracteristicele ferestrei
        setSize(800, 600);
        setResizable(false);
        setVisible(true);
        setTitle("Operatii Polinoame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void init(){             //initializeaza containerele
        for (int i=0;i<6;i++){
            text[i]=new JLabel("Introduceti polinoamele");
            polinom1[i]=new JTextField();
            polinom2[i]=new JTextField();
            polResult[i]=new JLabel("");
            calc[i]=new JButton("Calculeaza");
            backButton[i]=new JButton("Inapoi");
        }
    }
    private  void addBounds(){         //adauga dimeniunile si pozitionare pe fereastra pentru obiectele folosite
        polResult2.setBounds(200,430,400,30);
        for(int i=0; i<=5;i++ ) {
            text[i].setText("Introduceti Polinoamele:");
            calc[i].setBounds(630,200,100,40);
            backButton[i].setBounds(630,500,100,40);
            text[i].setBounds(320,40,300,30);
            polinom1[i].setBounds(200,100,400,20);
            polinom2[i].setBounds(200,170,400,20);
            polResult[i].setBounds(200,400,400,30);
        }
    }
    private void menu(){                            //7
        //seteaza containerele ce vor fi folosite in menie
        textMenu.setBounds(330,60,300,30);
        textMenu.setText("Alege Operatia");
        choose.setBounds(250,120,300,25);
        menuPanel.add(textMenu);
        menuPanel.add(choose);
        choose.addItem("Adunare Polinoame");   //1
        choose.addItem("Scadere Polinoame");   //2
        choose.addItem("Derivare Polinoam");   //3
        choose.addItem("Integrare Polinoam");  //4
        choose.addItem("Imultire Polinoame");  //5
        choose.addItem("Impartire Polinoame"); //6
        choose.setEditable(true);
        choose.setBackground(Color.LIGHT_GRAY);
    }
    private void addToPanel(JPanel currentPanel,int a){     //adauga panoului curent elementele sale
        currentPanel.add(polinom1[a]);
        currentPanel.add(polResult[a]);
        currentPanel.add(text[a]);
        currentPanel.add(backButton[a]);
        currentPanel.add(calc[a]);
    }
    private void addComponents() {              //adauga componentele folosite
        for(int i=0; i<=5;i++) {
            text[i].setBounds(330, 60, 300, 30);
            text[i].setText("Introduceti Polinomul");
        }
        addToPanel(op1,0);          //adauga fiecarui panou containarele corespunzatoare
        addToPanel(op2,1);
        addToPanel(op3,2);
        addToPanel(op4,3);
        addToPanel(op5,4);
        addToPanel(op6,5);

        op1.add(polinom2[0]);
        op2.add(polinom2[1]);
        op5.add(polinom2[4]);
        op6.add(polinom2[5]);
        op6.add(polResult2);
        menu();
        addBounds();

    }
    private void incPanels(){           //leaga toate celelate panouri de winPanel, panoul principal
        winPanel.add(menuPanel,"7");
        winPanel.add(op1,"1");
        winPanel.add(op2,"2");
        winPanel.add(op3,"3");
        winPanel.add(op4,"4");
        winPanel.add(op5,"5");
        winPanel.add(op6,"6");
        container.show(winPanel,"0");
    }
    public PolView(){   //constructor unde metodele de pana acum vor fi apelate
        init();
        setFrame();
        add(winPanel);          //se aduga ferestrei panoul principal,care le contine pe toate celelalte
        winPanel.setLayout(container);      //se seteaza layout-ul fiecarui panou la null
        menuPanel.setLayout(null);
        op1.setLayout(null);
        op2.setLayout(null);
        op3.setLayout(null);
        op4.setLayout(null);
        op5.setLayout(null);
        op6.setLayout(null);
        menuPanel.setBackground(Color.WHITE);           //setarea culorii panourilor la alb
        op1.setBackground(Color.WHITE);
        op2.setBackground(Color.WHITE);
        op3.setBackground(Color.WHITE);
        op4.setBackground(Color.WHITE);
        op5.setBackground(Color.WHITE);
        op6.setBackground(Color.WHITE);
        addComponents();
        incPanels();
    }
    public String getFirstPol(int i){
        return polinom1[i].getText();
    }       //preia ca string primul polinom
    public String getSecondPol(int i){
        return polinom2[i].getText();
    }       //preia ca string al doilea polinom
    public void setPolResult(String tx,int i){
            polResult[i].setText("Rezultat:    "+tx);
    }       //seteaza rezultatul pe un panou dat ca parametru
    public void setState(int a){                //seteaza starea
        state=a;
        container.show(winPanel,""+a);      //comuta panoul
    }
    public void setPolResult2(String r){
            polResult2.setText(r);
    }       //seteaza Jlabelul restului
    public int getState(){          //preia starea
        return state;
    }
    public String getSelected(){
        return (String) choose.getSelectedItem();
    }       //selecteaza actiunea aleasa din choose
    public void attachCalc(ActionListener a){       //ataseaza actionListener butoanelor "Calculeaz"
        for (int i=0;i<6;i++)
        calc[i].addActionListener(a);
    }
    public void attachBack(ActionListener a){       //ataseaza actionListener butoanelor "Inapoi"
        for (int i=0;i<6;i++)
        backButton[i].addActionListener(a);
    }
    public void attachChoose(ActionListener a){
        choose.addActionListener(a);
    }   //ataseaza actionListener JComboBox-ului Choose pentru fiecare operatie in parte

}