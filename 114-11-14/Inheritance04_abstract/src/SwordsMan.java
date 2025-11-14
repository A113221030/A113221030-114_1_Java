public class SwordsMan extends Role {
    //建構子
    public SwordsMan(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    //攻擊對手
    public void attack(Role opponent) {
        System.out.println(this.getName() + " 使用劍術攻擊 " + opponent.getName() + "，造成 " + this.getAttackPower() + " 點傷害！");
        int newHealth = opponent.getHealth() - this.getAttackPower();
        opponent.setHealth(newHealth);
        if (newHealth < 0) {
            opponent.setHealth(0);
        }
    }

    //治癒劍客
    public void heal(Role ally, int healPower) {
        System.out.println(this.getName() + " 使用治癒術治療 " + ally.getName() + "，恢復 " + healPower + " 點生命值！");
        int newHealth = ally.getHealth() + healPower;
        ally.setHealth(newHealth);
    }

    //檢查角色是否存活
    public boolean isAlive() {
        return this.getHealth() > 0;
    }
}