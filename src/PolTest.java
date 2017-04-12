import junit.framework.TestCase;


public class PolTest extends TestCase  {
        PolModel modelTest=new PolModel();

    public void testAdd(){
    Polinom firstPolAdd=new Polinom();          //3x^2-5x^1+4x^0
    Polinom secondToAdd=new Polinom();          //-x^2+2x^0
    Polinom resAdd=new Polinom();
    Polinom verifAdd=new Polinom();             //2x^2-5x^1+6x^0

    firstPolAdd.adauga(3,2);
    firstPolAdd.adauga(-5,1);
    firstPolAdd.adauga(4,0);
    secondToAdd.adauga(-1,2);
    secondToAdd.adauga(2,0);
    verifAdd.adauga(2,2);
    verifAdd.adauga(-5,1);
    verifAdd.adauga(6,0);

    resAdd=modelTest.add(firstPolAdd,secondToAdd);
    assertTrue(resAdd.equals(verifAdd));

    }

    public void testSub(){                          //test adunare
        //instantiere obiecte necesare
        Polinom firstPolSub=new Polinom();          //3x^2-5x^1+4x^0
        Polinom secondToSub=new Polinom();          //-1x^2+2x^0
        Polinom resSub=new Polinom();
        Polinom verifSub=new Polinom();             //4x^2-5x^1+2x^0

        firstPolSub.adauga(3,2);            //adaugarea polinoamelor
        firstPolSub.adauga(-5,1);
        firstPolSub.adauga(4,0);

        secondToSub.adauga(-1,2);
        secondToSub.adauga(2,0);

        verifSub.adauga(4.0,2);
        verifSub.adauga(-5.0,1);
        verifSub.adauga(2.0,0);

        resSub=modelTest.sub(firstPolSub,secondToSub);      //operatia de adunare
        assertTrue(resSub.equals(verifSub));                //verificarea asertiunii
    }

    public void testDerivate(){             //verificare operatia de derivare
        Polinom firstPolDev=new Polinom();      //1x^4-3x^2+7x^1+1x^0
        Polinom resDev=new Polinom();
        Polinom verifDev=new Polinom();         //4x^3-6x^1+7x^0

        firstPolDev.adauga(1,4);
        firstPolDev.adauga(-3,2);
        firstPolDev.adauga(7,1);
        firstPolDev.adauga(1,0);
        verifDev.adauga(4,3);
        verifDev.adauga(-6,1);
        verifDev.adauga(7,0);
        //verifDev.adauga(2.0,0);


        resDev=modelTest.derivate(firstPolDev);
        assertTrue(resDev.equals(verifDev));
    }

    public void testIntegrate(){                //verificare integrare
        Polinom firstPolInt=new Polinom();      //10x^4+6x^2-5x^1+3x^0
        Polinom resInt=new Polinom();
        Polinom verifInt=new Polinom();         //2x^5+2x^3-2.5x^2+3x^1

        firstPolInt.adauga(10,4);
        firstPolInt.adauga(6,2);
        firstPolInt.adauga(-5,1);
        firstPolInt.adauga(3,0);

        verifInt.adauga(2.0,5);
        verifInt.adauga(2.0,3);
        verifInt.adauga(-2.5,2);
        verifInt.adauga(3.0,1);

        resInt=modelTest.integrate(firstPolInt);
        assertTrue(resInt.equals(verifInt));
    }
    public void testMul(){                          //erificare operatie de imultire
        Polinom firstPolMul=new Polinom();          //1x^3-5x^1+2x^0
        Polinom secondToMul=new Polinom();          //6x^2+10x^0
        Polinom resMul=new Polinom();
        Polinom verifMul=new Polinom();             //5x^5-20x^3+12x^2-50x^1+20x^0

        firstPolMul.adauga(1,3);
        firstPolMul.adauga(-5,1);
        firstPolMul.adauga(2,0);
        secondToMul.adauga(6,2);
        secondToMul.adauga(10,0);
        verifMul.adauga(6.0,5);
        verifMul.adauga(-20.0,3);
        verifMul.adauga(12.0,2);
        verifMul.adauga(-50.0,1);
        verifMul.adauga(20.0,0);

        resMul=modelTest.mul(firstPolMul,secondToMul);
        assertTrue(resMul.equals(verifMul));
    }


    public void testIMul(){                 //verifacare operatie de imultire
        Polinom firstPolIMul=new Polinom();         //-2x^5-5x^3+6x^2-2x^1-2x^0
        Polinom secondToIMul=new Polinom();         //-x^3-1x^1+3x^0
        Polinom resIMulQ=new Polinom();
        Polinom resIMulR=new Polinom();
        Polinom verifIMulQ=new Polinom();           //2x^2+3x^0
        Polinom verifIMulR=new Polinom();           //1x^1-11x^0
        firstPolIMul.adauga(-2,5);
        firstPolIMul.adauga(-5,3);
        firstPolIMul.adauga(6,2);
        firstPolIMul.adauga(-2,1);
        firstPolIMul.adauga(-2,0);
        secondToIMul.adauga(-1,3);
        secondToIMul.adauga(-1,1);
        secondToIMul.adauga(3,0);
        verifIMulQ.adauga(2.0,2);
        verifIMulQ.adauga(3.0,0);
        verifIMulR.adauga(1,5);
        verifIMulR.adauga(1,3);
        verifIMulR.adauga(1,2);
        verifIMulR.adauga(-1,5);
        verifIMulR.adauga(-1,3);
        verifIMulR.adauga(-1,2);                //adunate si scazute pentru a pastra valori actuale de 0 deoarece la aflarea restului un monom cu gras
        verifIMulR.adauga(1.0,1);
        verifIMulR.adauga(-11.0,0);
        resIMulQ=modelTest.iMulQ(firstPolIMul,secondToIMul);
        resIMulR=modelTest.iMulR(firstPolIMul,secondToIMul);
        assertEquals(resIMulQ.equals(verifIMulQ),resIMulR.equals(verifIMulR));
    }
}