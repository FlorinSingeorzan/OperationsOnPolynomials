import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolModel {
    public Polinom addString(String str) {
        Polinom toReturn = new Polinom();
        String regex = "([\\-]?\\d+)(x\\^)(\\d+)";
        Pattern patt = Pattern.compile(regex);
        Matcher mat = patt.matcher(str);
        if (str.matches("(^(([\\-\\+]?\\d+)(x\\^)(\\d+))*$)")) {
            while (mat.find()) {            //pentru fiecare monom se prelucreaza componentele lui
                toReturn.adauga(Integer.parseInt(mat.group(1)), Integer.parseInt(mat.group(3)));
            }
        }
        return toReturn;
    }

    public boolean isEmpty(Polinom is){
        if(is.polinom.isEmpty())
            return true;
        return false;
    }



    public Polinom add(Polinom polAddOne, Polinom polAddTwo) {
        Polinom res = new Polinom();
            for (Monom i : polAddOne.polinom) {
                res.adauga(i.getCoef(), i.getDeg());        //adauga primul polinom
            }
            for(Monom j :polAddTwo.polinom){
            res.adauga(j.getCoef(),j.getDeg());             //adauga polinoul al doilea
            }
        return res;     // rezultatul reprezinta adunarea datorita felului in care a fost scrisa metoda adauga
    }
    public Polinom sub(Polinom polSubOne, Polinom polSubTwo) {
        Polinom resSub = new Polinom();
        double polSOne,polSTwo;
            for (Monom i : polSubOne.polinom) {
                    if(i.getCoef()==0)      //folosit pentru a prelua valoarea coeficientului e diferita de 0
                        polSOne=i.getCoefDouble();
                    else
                        polSOne=i.getCoef();
                resSub.adauga(polSOne, i.getDeg());     //adauga primul polinom
            }
        for(Monom j :polSubTwo.polinom){
            if(j.getCoef()==0)          //folosit pentru a prelua valoarea coeficientului e diferita de 0
                polSTwo=j.getCoefDouble();
            else
                polSTwo=j.getCoef();
            resSub.adauga(-polSTwo,j.getDeg());         //adauga opusul celui de al doilea polinom
        }
        return resSub;      //rezultatul va fi scadera polinoamelor, coeficientul este detinut in coefDouble
    }
    public Polinom mul(Polinom polMulOne,Polinom polMulTwo){
        Polinom resMul = new Polinom();
        double toMulOne;
        double toMulTwo;
        for (Monom i:polMulOne.polinom ) {          //se parcurg element cu element cele doua polinoame si se imultesc membru cu membru
            for (Monom j: polMulTwo.polinom) {
                if(i.getCoef()==0)              //preia valoarea coeficientului deiferit de 0
                   toMulOne=i.getCoefDouble();
                else
                    toMulOne=i.getCoef();
                if(j.getCoef()==0)
                toMulTwo=j.getCoefDouble();     //preia valoarea coeficientului deiferit de 0
                else
                    toMulTwo=j.getCoef();
                            resMul.adauga(toMulOne*toMulTwo,i.getDeg()+j.getDeg());     //se adauga produsul coeficientilor
                        }
                    }
        return resMul;          //Polinomul returnat va avea coeficinti retinuti in coefDouble
    }
    public Polinom derivate(Polinom polDerivate){
        Polinom resDerivate=new Polinom();

        for (Monom i:polDerivate.polinom ) {        //se parcurg monoamele constituente
            resDerivate.adauga(i.getCoef()*i.getDeg(),i.getDeg()-1);        // se creaza derivata unui monom, forma matematica
        }
        return resDerivate;
    }
    public  Polinom integrate(Polinom polIntegrate){
        Polinom resIntegrate=new Polinom();

        for (Monom i:polIntegrate.polinom ) {
            resIntegrate.adauga((double) i.getCoef()/(i.getDeg()+1),i.getDeg()+1);      //operatia opusa derivari,impartirea coeficientului la grad si incrementarea gradului
        }
        return resIntegrate;    //coeficintii vor fi retinuti in coefDouble
    }
    private Monom  getMonomMax(Polinom toDeg){
        Monom to=new Monom();
        double aux=0.0;
        int degMax=0;
        for(Monom i: toDeg.polinom){
            if(i.getCoefDouble()!=0.0 || i.getCoef()!=0) {      //daca polinomul are un coeficient diferit de zero
                if (i.getCoef() != 0)               //se ia coeficientul diferit de zero
                    aux = i.getCoef();
                else if (i.getCoefDouble() != 0.0)
                    aux = i.getCoefDouble();
                if (i.getDeg() >= degMax) {     //se compara pe rand cu degMax pentru identificarea elementului maxim, egalitatea are rost in cazul in care gradul maxim este 0
                    to.setCoefDouble(aux);
                    degMax = i.getDeg();
                    to.setDeg(degMax);      //se modifica degMax si se seteaza componentele monomului
                }
            }
        }
        return to;      //monomul cu grad maxim si coeficient diferit de 0 este returnat
    }

    private Polinom getDouble(Polinom a){
        Polinom rezPol=new Polinom();
        double b;
        for(Monom i:a.polinom){
            b=i.getCoef();
            rezPol.adauga(b,i.getDeg());    //se inlocuieste coef cu coefDuble ca variabila ce tine coeficientul unui polinom
        }
        return rezPol;          //Se returneaza polinomul cu coeficienti reali
    }
    public Polinom iMulQ (Polinom polToImul,Polinom polImul){
        Polinom Q=new Polinom();            //catul
        Polinom aux=new Polinom();
        Polinom aux2=new Polinom();         //polinomul cu care se scade aux pas cu pas
        Polinom aux3=new Polinom();         //catul temporar
        aux=this.getDouble(polToImul);      //In “aux” se pastreaza polinomul curent, initial egal cu polinomul care trebuie impartit
        int gr,index=0;                     //gr-gradul si index e folosit pentru a testa sa nu se depaseaca numarul de impartiri temporare
        double co,vf=1;                     //co- coeficientul, vf folosit la impartire si verificarea ca nu se imparte cu 0
        if(aux.polinom.get(0).getDeg()<polImul.polinom.get(0).getDeg())     // cazul in care nu mai trebuie sa facem impartire, catul este nul
        return Q;
        else {
            while (aux.polinom.size()>index && this.getMonomMax(aux).getDeg() >= polImul.polinom.get(0).getDeg()){  //comaram gradele
                gr = this.getMonomMax(aux).getDeg() - polImul.polinom.get(0).getDeg();
                if (polImul.polinom.get(0).getCoefDouble()!=0.0)        //se pastreaza coeficientul diferit de 0
                    vf=polImul.polinom.get(0).getCoefDouble();
                else if (polImul.polinom.get(0).getCoef()!=0)
                    vf=polImul.polinom.get(0).getCoef();
                co = this.getMonomMax(aux).getCoefDouble() / vf;
                Q.adauga(co, gr);           //se adauga catului rezultatul impartirii coeficientilor si diferenta dintre grade
                aux3.adauga(co, gr);        //se pastreaza monomul rezultat si in auxiliarul temporarte
                aux2 = this.mul(polImul, aux3);     //se determina cu cat trebuie resdus aux pentru a i se mici gradul maxim
                aux = this.sub(aux, aux2);          //gradul maxima lui aux se reduce un unu
                aux3 = this.sub(aux3, aux3);        //eliminarea catului temporar
                index++;        //specifica faptul ca nu mai trebuie sa repetam operatia
            }
            return Q;
        }
        }
    public Polinom iMulR(Polinom polToImul,Polinom polImul) {
        Polinom R=new Polinom();
        Polinom qAux=new Polinom();
        Polinom mAux=new Polinom();
        if(polToImul.polinom.get(0).getDeg() < polImul.polinom.get(0).getDeg())
        return polToImul;           //se returneaza primul polinom daca are gradul mai mic decat cel de al doilea
        else {
            qAux = this.iMulQ(polToImul, polImul);      //se afla catul
            mAux = this.mul(polImul, qAux);             //se imulteste catul cu deimpartitul
            R = this.sub(polToImul, mAux);              // se scade din primul polinom rezultatul optinut mai sus
            return R;
        }
        }
}