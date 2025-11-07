public class  Role {
    //角色名稱
    private String name;
    //角色生命值
    private int health;
    //角色攻擊力
    private int attackPower;

    public Role(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }
    //取得角色名稱
    public String getName() {
        return name;
    }
    //取得角色生命值
    public int getHealth() {
        return health;
    }
    //取得角色攻擊力
    public int getAttackPower() {
        return attackPower;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    //攻擊對手
    public void attack(Role opponent) {
        opponent.health -= this.attackPower;
        System.out.println(this.name + " 攻擊 " + opponent.name + "，造成 " + this.attackPower + " 點傷害！");
    }
    //檢查角色是否存活
    public boolean isAlive() {
        return health > 0;
    }
    // 角色狀態顯示
    @Override
    public String toString() {
        return String.format("角色名稱: %s, 生命值: %d, 攻擊力: %d", name, health, attackPower);
    }
}

