package sample;

public class Spawner {
    public static int delay_time = -1;
    public static int Count = 0;

    public static void doSpawn() {
        if(delay_time<GameStage.list.length()*30) delay_time++;
        if (delay_time < GameStage.list.length() * 30 && delay_time % 30 == 0&&Count<GameStage.list.length()) {
            if (GameStage.list.charAt(Count) == '1') {
                Enemy enemy1 = new SmallerEnemy();
                GameField.enemy.add(enemy1);
                Count += 1;
            }
        }
        if (delay_time < GameStage.list.length() * 100 && delay_time % 100 == 0 && Count< GameStage.list.length()) {
            if (GameStage.list.charAt(Count) == '2') {
                Enemy enemy1 = new Tank();
                GameField.enemy.add(enemy1);
                Count += 1;
            }
        }
        if (delay_time < GameStage.list.length() * 100 && delay_time % 100 == 0&&Count<GameStage.list.length()) {
            if (GameStage.list.charAt(Count) == '3') {
                Enemy enemy1 = new BossEnemy();
                GameField.enemy.add(enemy1);
                Count += 1;
            }
        }
        if (delay_time < GameStage.list.length() * 40 && delay_time % 40 == 0&&Count<GameStage.list.length()) {
            if (GameStage.list.charAt(Count) == '4') {
                Enemy enemy1 = new NormalEnemy();
                GameField.enemy.add(enemy1);
                Count += 1;
            }
        }
    }
}
