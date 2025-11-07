public class Magician  extends Role{
    private int healPower;

    public Magician(String name, int health, int mana, int spellPower) {
        super(name, health, mana);
        this.healPower = spellPower;
    }

    public int getHealPower() {
        return healPower;
    }



    public void attack(Magician opponent) {
        System.out.println(this.getName()+"使用魔法攻擊"+opponent.getName()+"造成"+this.getAttackPower()+"點傷害");
        int newHealth = opponent.getHealth() - this.getAttackPower();
        opponent.setHealth(newHealth);
        if(newHealth < 0) {
            opponent.setHealth(0);
        }
    }
    public void heal(SwordsMan ally) {
        System.out.println(this.getName()+"使用治療魔法治療"+ally.getName()+"恢復"+this.healPower+"點生命值");
        int newHealth = ally.getHealth() + this.healPower;
        ally.setHealth(newHealth);
    }

    public String toString() {
        return super.toString() + (", 治療力: "+healPower);
    }


    public boolean isAlive() {
        return getHealth() > 0;
    }
}


