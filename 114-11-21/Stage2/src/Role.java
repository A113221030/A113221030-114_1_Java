public abstract class Role {
    // 角色名稱
    private String name;
    // 生命值
    private int health;
    // 攻擊力
    private int attackPower;

    // 建構子：初始化角色的名稱、生命值和攻擊力
    public Role(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }
    // 取得角色名稱
    public String getName() {
        return name;
    }

    // 取得生命值
    public int getHealth() {
        return health;
    }

    // 取得攻擊力
    public int getAttackPower() {
        return attackPower;
    }
    // 設定生命值
    public void setHealth(int health) {
        this.health = health;
    }

    // 檢查角色是否存活
    public boolean isAlive() {
        return health > 0;
    }

    public abstract void attack(Role opponent);

    public abstract void showSpecialSkill();

    /**
     * 受到傷害（具體方法）
     * 所有角色受傷的基本流程都相同：
     * 1. 扣除生命值
     * 2. 顯示受傷訊息
     * 3. 檢查是否死亡
     * 4. 如果死亡，呼叫 onDeath()
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println("💥 " + name + " 受到 " + damage + " 點傷害！目前生命值：" + health);

        if (!isAlive()) {
            onDeath(); // 呼叫抽象方法
        }
    }
    /**
     * 死亡時的處理（抽象方法）
     * 每個角色死亡時的表現方式都不同
     */
    public abstract void onDeath();
    // 戰鬥前的準備動作
    public abstract void prepareBattle();

    // 戰鬥後的行為
    public abstract void afterBattle();





    @Override
    public String toString() {
        return "角色名稱: " + name + ", 生命值: " + health;
    }
}
