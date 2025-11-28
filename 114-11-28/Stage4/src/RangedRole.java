// 第三階段和第四階段完全相同
public abstract class RangedRole extends Role {
    private int range;
    private int energy;
    private int maxEnergy;

    public RangedRole(String name, int health, int attackPower, int range, int maxEnergy) {
        super(name, health, attackPower);
        this.range = range;
        this.maxEnergy = maxEnergy;
        this.energy = maxEnergy;
    }

    public int getRange() { return range; }
    public int getEnergy() { return energy; }
    public int getMaxEnergy() { return maxEnergy; }
    public void setEnergy(int energy) {
        this.energy = Math.min(energy, maxEnergy);
    }

    public boolean isInRange(int distance) {
        boolean inRange = distance <= range;
        if (!inRange) {
            System.out.println("❌ 目標距離 " + distance + " 超出射程 " + range + "！");
        }
        return inRange;
    }

    public boolean consumeEnergy(int amount) {
        if (energy >= amount) {
            energy -= amount;
            System.out.println("💫 消耗 " + amount + " 點能量，剩餘：" + energy + "/" + maxEnergy);
            return true;
        } else {
            System.out.println("❌ 能量不足！需要 " + amount + "，目前只有 " + energy);
            return false;
        }
    }

    public void restoreEnergy(int amount) {
        int oldEnergy = energy;
        energy = Math.min(energy + amount, maxEnergy);
        System.out.println("✨ 恢復 " + (energy - oldEnergy) + " 點能量 (" +
                oldEnergy + " → " + energy + ")");
    }

    public abstract String getRangedAttackType();

    @Override
    public void prepareBattle() {
        System.out.println("🎯 " + getName() + " 準備 " + getRangedAttackType() + " 攻擊...");
        System.out.println("📊 能量值：" + energy + "/" + maxEnergy + "，射程：" + range);
        onRangedPrepare();
    }

    protected abstract void onRangedPrepare();

    @Override
    public void afterBattle() {
        restoreEnergy(10);
        onRangedRecover();
    }

    protected abstract void onRangedRecover();

    @Override
    public String toString() {
        return super.toString() + ", 能量: " + energy + "/" + maxEnergy + ", 射程: " + range;
    }
}
