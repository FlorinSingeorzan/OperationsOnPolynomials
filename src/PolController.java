import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PolController {
    private PolView view;
    private PolModel model;

    PolController(PolView view, PolModel model) {
        this.view = view;
        this.model = model;
        view.attachBack(new BackListener());        //ataseaza actiune butonului de Back
        view.attachCalc(new CalcListener());        //ataseaza actiune butonului de Calc
        view.attachChoose(new ChooseListener());    //determina actiunea, operatia aleasa
    }
    public class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.setState(7);
        }
    }

    public class CalcListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int currentState=view.getState();       //retine starea curenta
            switch (currentState){                  //verifica operatia pe care trebuie sa o faca
                case 1:                             //adunare
                    Polinom polAddOne=new Polinom();
                    Polinom polAddTwo=new Polinom();
                    Polinom polAddRes=new Polinom();
                    polAddOne = model.addString(view.getFirstPol(0));
                    polAddTwo = model.addString(view.getSecondPol(0));
                    if(model.isEmpty(polAddOne)|| model.isEmpty(polAddTwo))
                        view.setPolResult("Valori Ilegale",0);
                    else {
                        polAddRes = model.add(polAddOne, polAddTwo);
                        view.setPolResult(polAddRes.afisare(), 0);
                    }
                    break;
                case 2:                             //scadere
                    Polinom polSubOne=new Polinom();
                    Polinom polSubTwo=new Polinom();
                    Polinom polSubRes=new Polinom();
                    polSubOne=model.addString(view.getFirstPol(1));
                    polSubTwo=model.addString(view.getSecondPol(1));
                    if(model.isEmpty(polSubOne)|| model.isEmpty(polSubTwo))
                        view.setPolResult("Valori Ilegale",1);
                    else {
                        polSubRes = model.sub(polSubOne, polSubTwo);
                        view.setPolResult(polSubRes.afisare(), 1);
                    }
                    break;
                case 3:                                 //derivare

                    Polinom polDerivate=new Polinom();
                    Polinom resDerivate=new Polinom();
                    polDerivate=model.addString(view.getFirstPol(2));
                    if(model.isEmpty(polDerivate))
                        view.setPolResult("Valori Ilegale",2);
                    else {
                        resDerivate = model.derivate(polDerivate);
                        view.setPolResult(resDerivate.afisare(), 2);
                    }
                    break;
                case 4:                                 //integrere

                    Polinom polIntegrate=new Polinom();
                    Polinom resIntegrate=new Polinom();
                    polIntegrate=model.addString(view.getFirstPol(3));
                    if(model.isEmpty(polIntegrate))
                        view.setPolResult("Valori Ilegale",3);
                    else {
                        resIntegrate = model.integrate(polIntegrate);
                        view.setPolResult(resIntegrate.afisare(), 3);
                    }
                    break;
                case 5:                                 //imultire
                    Polinom polMulOne=new Polinom();
                    Polinom polMulTwo=new Polinom();
                    Polinom polMulRes=new Polinom();
                    polMulOne=model.addString(view.getFirstPol(4));
                    polMulTwo=model.addString(view.getSecondPol(4));
                    if(model.isEmpty(polMulOne)|| model.isEmpty(polMulTwo))
                        view.setPolResult("Valori Ilegale",4);
                    else {
                        polMulRes = model.mul(polMulOne, polMulTwo);
                        view.setPolResult(polMulRes.afisare(), 4);
                    }
                    break;
                case 6:                                 //impartire
                    Polinom polIMulOne=new Polinom();
                    Polinom polIMulTwo=new Polinom();
                    Polinom polIMulQ=new Polinom();
                    Polinom polIMULR=new Polinom();
                    polIMulOne=model.addString(view.getFirstPol(5));
                    polIMulTwo=model.addString(view.getSecondPol(5));
                    if(model.isEmpty(polIMulOne)|| model.isEmpty(polIMulTwo)) {
                        view.setPolResult("Valori Ilegale", 5);
                        view.setPolResult2("");
                    }
                    else {
                        polIMulQ = model.iMulQ(polIMulOne, polIMulTwo);
                        polIMULR = model.iMulR(polIMulOne, polIMulTwo);
                        view.setPolResult("Cat:  " + polIMulQ.afisare(), 5);
                        view.setPolResult2("                    Rest:   " + polIMULR.afisare());        //spatii lasate penru aliniere

                    }
                    break;
                default:
            }
        }
    }
    public class ChooseListener implements ActionListener {             //actiunile cu ramulele de facut de JComboBox
        public void actionPerformed(ActionEvent e) {
           switch (view.getSelected()) {                //la selectie se schimba starea
               case "Adunare Polinoame":
                   view.setState(1);
                   break;
               case "Scadere Polinoame":
                   view.setState(2);
                   break;
               case "Derivare Polinoam":
                   view.setState(3);
                   break;
               case "Integrare Polinoam":
                   view.setState(4);
                   break;
               case "Imultire Polinoame":
                   view.setState(5);
                   break;
               case "Impartire Polinoame":
                   view.setState(6);
                   break;
               default:
           }
        }
    }
}