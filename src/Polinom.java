import java.util.ArrayList;
import java.util.Collections;

public class Polinom {
    ArrayList<Monom> polinom=new ArrayList<>();

    public int exist(int toSearch){                //verificam daca un anumit grad apare in polinom
        for(int i=0;i<polinom.size();i++){
            if(polinom.get(i).getDeg()==toSearch){
                return i;
            }
        }
        return -1;
    }
    public void adauga(int co,int gr){          //metoda de adaugare pentru coeficient real
        if(co!=0) {                         //adaugam doar coeficienti diferiti de zero
            if (exist(gr) != -1) {          //daca gradul mai exista in polinom adunam gradele
                polinom.get(exist(gr)).setCoef(polinom.get(exist(gr)).getCoef() + co);
            } else
                polinom.add(new Monom(co, gr));             //altfel creeam un nou obiect
                Collections.sort(polinom);
        }
    }
    public void adauga(double co,int gr){               //metode de adaugare pentru coeficient real
        if(co!=0.0) {
            if (exist(gr) != -1) {
                polinom.get(exist(gr)).setCoefDouble(polinom.get(exist(gr)).getCoefDouble() + co);
            } else
                polinom.add(new Monom(co, gr));
            Collections.sort(polinom);
        }
    }
    public String afisare(){                    //metoda pentru afisarea unui polinom
        String show="";                         //stringul de returnat
        String auxStr="";
        for(Monom k: this.polinom) {
            if (k.getCoefDouble() == 0.0 && k.getCoef()!=0) {               //ramura pentru decizia coeficientului comparat
                if (k.getCoef() > 0 && this.polinom.indexOf(k) != 0)        //ramura pentru verificare semnului coeficientului
                    show = show + "+" + k.getCoef() + "x^" + k.getDeg();
                else if(k.getCoef()<0 ||this.polinom.indexOf(k)==0)
                    show = show + k.getCoef() + "x^" + k.getDeg();
            }
            else if(k.getCoefDouble()!= 0.0){

                auxStr= String.format("%.2f",k.getCoefDouble());                //format pentru trunchiere
                    if (k.getCoefDouble() > 0 && this.polinom.indexOf(k) != 0)                  //ramura pentru verificare semnului coeficientului
                        show = show + "+" + auxStr + "x^" + k.getDeg();
                    else
                        show = show + auxStr + "x^" + k.getDeg();
            }
        }
        return show;
    }

    public boolean equals(Polinom a){                   //metoda pentru verificarea egalitatii dintre doua polinoame-> folosita la testare
        for(int i=0;i<a.polinom.size();i++) {
            if (a.polinom.get(i).getCoef()!=0) {        //ramura pentru decizia coeficientului comparat
                if (!(a.polinom.get(i).getCoef() == this.polinom.get(i).getCoef() && a.polinom.get(i).getDeg() == this.polinom.get(i).getDeg())) {
                    return false;
                }
            }
            else{
                if (!(a.polinom.get(i).getCoefDouble() == this.polinom.get(i).getCoefDouble() && a.polinom.get(i).getDeg() == this.polinom.get(i).getDeg())) {
                    return false;
                }
            }
        }
            return true;
    }
}