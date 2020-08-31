package sample;

import java.util.ArrayList;
import java.util.List;

public class GameStage {
    public static String list = new String();
    public static String[] Stage = new String[]{"4111111", "441121111" , "44121211211", "443111122300000211"};
    public static int stage = 0;
    public static void start() {
        Spawner.delay_time = -1;
        Spawner.Count = 0;
        list = Stage[stage];
        System.out.println(list.length());
        if(stage <= Stage.length-1){
            stage +=1;
        }
    }
}
