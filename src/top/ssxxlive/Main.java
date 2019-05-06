package top.ssxxlive;

public class Main {

    public static void main(String[] args) {

        TankFrame tf = new TankFrame();

        while(true){
            try{
                Thread.sleep(10);
                tf.repaint();
            } catch (InterruptedException i) {
                i.printStackTrace();
            }
        }
    }
}
