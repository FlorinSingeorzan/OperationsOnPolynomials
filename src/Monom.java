
public class Monom implements Comparable {
    private int coef;                   // am presupus ca pe langa grad un polinom poate sa aiba un polinom intreg sau real(pentru integrare si impartire)
    private int deg;
    private double coefDouble;
    public Monom(int c, int d){             //constructori folositi pentru initializarea obiectelor
        coef=c;
        deg=d;
        coefDouble=0.0;
    }
    public Monom(double co,int dg){
        coefDouble=co;
        deg=dg;
        coef=0;
    }
    public Monom(){
        this(0,0);
            }
    public void setCoef(int coef) {
        this.coef = coef;
    }           //gettere si settere pentru a obtine sau modifica valorile private ale gradului sau coeficientilor
    public int getCoef() {
        return coef;
    }
    public int getDeg() {
        return deg;
    }
    public void setDeg(int deg) {
        this.deg = deg;
    }
    public double getCoefDouble() {
        return coefDouble;
    }
    public void setCoefDouble(double coefDouble) {
        this.coefDouble = coefDouble;
    }
    @Override
    public int compareTo(Object o) {
        return ((Monom)o).getDeg()-this.getDeg();
    }           //folosit la sortare
}
