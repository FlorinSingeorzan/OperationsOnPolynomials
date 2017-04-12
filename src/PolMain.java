
public class PolMain {
    public static void main(String[] args){
        PolModel mod =new PolModel();           //instantieri View si Model
        PolView view =new PolView();
        PolController afisare=new PolController(view,mod);
    }

}
