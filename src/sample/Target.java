package sample;

public class Target {
    public static void doTarget(){
        for(int i =0; i<GameField.enemy.size(); i++){
            if(GameField.enemy.get(i).calculateDirection()==-1){
                GameField.enemy.get(i).setHealth(0);
                GameField.Health-=GameField.enemy.get(i).getDamage();
            }
        }
    }
}
