public class Role {
    // 角色名稱
    private String name;
    // 角色生命值
    private int health;
    // 角色攻擊力
    private int attackPower;

    // 建構子
    public Role(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    // 取得角色名稱
    public String getName() {
        return name;
    }

    // 取得角色生命值
    public int getHealth() {
        return health;
    }

    // 取得角色攻擊力
    public int getAttackPower() {
        return attackPower;
    }

    // 設定角色生命值
    public void setHealth(int health) {
        this.health = health;
    }
    public boolean isAlive() {
        return this.health > 0;
    }

    // 攻擊對手
    public void attack(Role opponent) {
        System.out.println(this.name + " 攻擊 " + opponent.getName() + "，造成 " + this.attackPower + " 點傷害！");
        int newHealth = opponent.getHealth() - this.attackPower;
        opponent.setHealth(newHealth);
        if (newHealth < 0) {
            opponent.setHealth(0);
        }
    }
}
