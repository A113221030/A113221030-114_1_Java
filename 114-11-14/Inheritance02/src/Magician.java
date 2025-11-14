public class Magician extends Role{
    private int healPower; // 治癒力

    public Magician(String name, int health, int attackPower, int  healthPower) {
        super(name, health, healthPower);
        this.healPower = healthPower;
    }

    //取得治癒力
    public int getHealPower() {
        return healPower;
    }

    //攻擊對手(劍客/魔法師)
    public void attack(Role opponent) {
        System.out.println(this.getName() + " 使用魔法攻擊 " + opponent.getName() + "，造成 " + this.getAttackPower() + " 點傷害！");
        int newHealth = opponent.getHealth() - this.getAttackPower();
        opponent.setHealth(newHealth);
        if (newHealth < 0) {
            opponent.setHealth(0);
        }
    }

    //治癒劍客
    public void heal(Role ally) {
        System.out.println(this.getName() + " 使用治癒術治療 " + ally.getName() + "，恢復 " + healPower + " 點生命值！");
        int newHealth = ally.getHealth() + healPower;
        ally.setHealth(newHealth);
    }



    @Override
    public String toString() {
        return "角色名稱: " + getName() + ", 生命值: " + getHealth() + ", 攻擊力: " + getAttackPower() + ", 治癒力: " + healPower;
    }
}
