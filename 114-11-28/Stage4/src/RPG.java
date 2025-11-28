public class RPG {
    public static void main(String[] args) {
        System.out.println("════════════════════════════════════════");
        System.out.println("        🎮 RPG 遊戲 - 第四階段");
        System.out.println("          展示：介面的應用");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        // ==========================================
        // 1. 顯示類別與介面結構（修改）
        // ==========================================
        System.out.println("【📋 類別與介面結構】");
        System.out.println("─────────────────────────────────────");
        System.out.println("Role (抽象類別)");
        System.out.println("├─ MeleeRole");
        System.out.println("│  ├─ SwordsMan");
        System.out.println("│  ├─ ShieldSwordsMan ────► implements Defendable");  // ← 修改
        System.out.println("│  └─ Paladin ─────────────► implements Defendable, Healable ⭐");  // ← 新增
        System.out.println("└─ RangedRole");
        System.out.println("   ├─ Magician ──────────► implements Healable");  // ← 修改
        System.out.println("   └─ Archer");
        System.out.println();
        System.out.println("介面 (Interface)：");  // ← 新增
        System.out.println("├─ Defendable：防禦能力");
        System.out.println("└─ Healable：治療能力");
        System.out.println();

        // ==========================================
        // 2. 建立角色（新增 Paladin）
        // ==========================================
        System.out.println("【創建角色】");
        System.out.println("─────────────────────────────────────");

        SwordsMan swordsMan_light = new SwordsMan("光明劍士", 100, 20, 5);
        System.out.println("✅ " + swordsMan_light);

        SwordsMan swordsMan_dark = new SwordsMan("黑暗劍士", 100, 25, 3);
        System.out.println("✅ " + swordsMan_dark);

        ShieldSwordsMan shieldSwordsMan = new ShieldSwordsMan("持盾劍士", 120, 18, 8, 10);
        System.out.println("✅ " + shieldSwordsMan);

        Magician magician_light = new Magician("光明法師", 80, 15, 10, 8, 100);
        System.out.println("✅ " + magician_light);

        Magician magician_dark = new Magician("黑暗法師", 80, 20, 5, 8, 100);
        System.out.println("✅ " + magician_dark);

        Archer archer = new Archer("精靈射手", 90, 18, 10, 80, 30);
        System.out.println("✅ " + archer);

        // ⭐ 新增：聖騎士
        Paladin paladin = new Paladin("聖騎士", 110, 17, 6, 12, 12, 100);
        System.out.println("✅ " + paladin);

        System.out.println();

        // ==========================================
        // 3. 將所有角色放入陣列（新增 paladin）
        // ==========================================
        Role[] gameRoles = {swordsMan_light, swordsMan_dark, shieldSwordsMan,
                magician_light, magician_dark, archer, paladin};  // ← 新增 paladin

        // ==========================================
        // 4. 展示介面能力（新增）
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("          🔍 介面能力展示");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        // ⭐ 新增：展示可防禦角色
        System.out.println("【可防禦角色 (Defendable)】");
        System.out.println("─────────────────────────────────────");
        for (Role role : gameRoles) {
            if (role instanceof Defendable) {
                Defendable defender = (Defendable) role;
                System.out.println("🛡️  " + role.getName());
                System.out.println("    防禦力：" + defender.getDefenseCapacity());
                System.out.println("    可防禦：" + (defender.canDefend() ? "是" : "否"));
                System.out.println();
            }
        }

        // ⭐ 新增：展示可治療角色
        System.out.println("【可治療角色 (Healable)】");
        System.out.println("─────────────────────────────────────");
        for (Role role : gameRoles) {
            if (role instanceof Healable) {
                Healable healer = (Healable) role;
                System.out.println("💚 " + role.getName());
                System.out.println("    治療力：" + healer.getHealPower());
                System.out.println("    可治療：" + (healer.canHeal() ? "是" : "否"));
                healer.showHealInfo();  // 使用介面的預設方法
                System.out.println();
            }
        }

        // ⭐ 新增：展示多重能力角色
        System.out.println("【多重能力角色】");
        System.out.println("─────────────────────────────────────");
        for (Role role : gameRoles) {
            if (role instanceof Defendable && role instanceof Healable) {
                System.out.println("⭐ " + role.getName() + " - 同時擁有防禦和治療能力！");
                System.out.println("   防禦力：" + ((Defendable)role).getDefenseCapacity());
                System.out.println("   治療力：" + ((Healable)role).getHealPower());
                System.out.println();
            }
        }

        // ==========================================
        // 5. 展示類別特性
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("          🔍 角色類別特性展示");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        System.out.println("【近戰角色特性】");
        System.out.println("─────────────────────────────────────");
        for (Role role : gameRoles) {
            if (role instanceof MeleeRole) {
                MeleeRole melee = (MeleeRole) role;
                System.out.println("⚔️  " + role.getName() + "：");
                System.out.println("    武器類型：" + melee.getWeaponType());
                System.out.println("    護甲值：" + melee.getArmor());

                // ⭐ 新增：顯示額外能力
                String abilities = "";
                if (role instanceof Defendable) abilities += " [可防禦]";
                if (role instanceof Healable) abilities += " [可治療]";
                if (!abilities.isEmpty()) {
                    System.out.println("    額外能力：" + abilities);
                }
                System.out.println();
            }
        }

        System.out.println("【遠程角色特性】");
        System.out.println("─────────────────────────────────────");
        for (Role role : gameRoles) {
            if (role instanceof RangedRole) {
                RangedRole ranged = (RangedRole) role;
                System.out.println("🎯 " + role.getName() + "：");
                System.out.println("    攻擊類型：" + ranged.getRangedAttackType());
                System.out.println("    射程：" + ranged.getRange() + " 米");
                System.out.println("    能量：" + ranged.getEnergy() + "/" + ranged.getMaxEnergy());

                if (role instanceof Archer) {
                    Archer a = (Archer) role;
                    System.out.println("    箭矢：" + a.getArrowCount() + " 支");
                }

                // ⭐ 新增：顯示額外能力
                String abilities = "";
                if (role instanceof Healable) abilities += " [可治療]";
                if (!abilities.isEmpty()) {
                    System.out.println("    額外能力：" + abilities);
                }
                System.out.println();
            }
        }

        // ==========================================
        // 6. 顯示所有角色的特殊技能
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("          ⚔️  特殊技能展示");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        for (Role role : gameRoles) {
            role.showSpecialSkill();
            System.out.println();
        }

        // ==========================================
        // 7. 戰鬥前準備
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("          🛡️  戰鬥前準備");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        for (Role role : gameRoles) {
            role.prepareBattle();
            System.out.println();
        }

        // ==========================================
        // 8. 戰鬥測試：護甲減免展示
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("      ⚔️  戰鬥測試 1：護甲減免效果");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        System.out.println("【測試：光明劍士受到 30 點傷害】");
        System.out.println("護甲值：" + ((MeleeRole)swordsMan_light).getArmor());
        System.out.println("預期實際傷害：30 - 5 = 25");
        System.out.println("─────────────────────────────────────");
        swordsMan_light.takeDamage(30);
        System.out.println("實際狀態：" + swordsMan_light);
        System.out.println();

        // ==========================================
        // 9. 戰鬥測試：介面防禦能力（修改）
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("      🛡️  戰鬥測試 2：介面防禦能力");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        System.out.println("【測試所有可防禦角色】");
        System.out.println("─────────────────────────────────────");

        // ⭐ 使用介面進行統一處理
        for (Role role : gameRoles) {
            if (role instanceof Defendable) {
                Defendable defender = (Defendable) role;

                System.out.println("\n" + role.getName() + " 的防禦測試：");
                System.out.println("當前生命值：" + role.getHealth());

                // 先受傷
                role.takeDamage(25);

                // 檢查是否可以防禦
                if (defender.canDefend()) {
                    System.out.println("執行防禦：");
                    defender.defend();  // ⭐ 呼叫介面方法
                } else {
                    System.out.println("❌ 無法防禦！");
                }

                System.out.println("防禦後生命值：" + role.getHealth());
            }
        }
        System.out.println();

        // ==========================================
        // 10. 戰鬥測試：介面治療能力（修改）
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("          💚 戰鬥測試 3：介面治療能力");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        System.out.println("【測試所有可治療角色】");
        System.out.println("─────────────────────────────────────");

        // ⭐ 使用介面進行統一處理
        for (Role role : gameRoles) {
            if (role instanceof Healable) {
                Healable healer = (Healable) role;

                System.out.println("\n" + role.getName() + " 的治療測試：");

                // 檢查是否可以治療
                if (healer.canHeal()) {
                    System.out.println("目標：黑暗劍士（生命值：" + swordsMan_dark.getHealth() + "）");
                    System.out.println("執行治療：");
                    healer.heal(swordsMan_dark);  // ⭐ 呼叫介面方法
                    System.out.println("治療後生命值：" + swordsMan_dark.getHealth());
                } else {
                    System.out.println("❌ 無法治療！");
                }
            }
        }
        System.out.println();

        // ==========================================
        // 11. 戰鬥測試：Paladin 多重能力（新增）
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("    ⭐ 戰鬥測試 4：聖騎士多重能力展示");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        System.out.println("【聖騎士可以執行三種行動】");
        System.out.println("─────────────────────────────────────");

        // ⭐ 展示 Paladin 的攻擊能力
        System.out.println("\n1️⃣  攻擊能力：");
        System.out.println("目標：精靈射手（生命值：" + archer.getHealth() + "）");
        paladin.attack(archer);
        System.out.println("攻擊後：精靈射手（生命值：" + archer.getHealth() + "）");
        System.out.println("聖騎士聖能：" + paladin.getHolyPower() + "/100");

        // ⭐ 展示 Paladin 的防禦能力
        System.out.println("\n2️⃣  防禦能力（Defendable 介面）：");
        paladin.takeDamage(20);
        System.out.println("受傷後生命值：" + paladin.getHealth());

        if (paladin.canDefend()) {
            paladin.defend();
            System.out.println("防禦後生命值：" + paladin.getHealth());
            System.out.println("聖騎士聖能：" + paladin.getHolyPower() + "/100");
        }

        // ⭐ 展示 Paladin 的治療能力
        System.out.println("\n3️⃣  治療能力（Healable 介面）：");
        System.out.println("目標：光明劍士（生命值：" + swordsMan_light.getHealth() + "）");

        if (paladin.canHeal()) {
            paladin.heal(swordsMan_light);
            System.out.println("治療後：光明劍士（生命值：" + swordsMan_light.getHealth() + "）");
            System.out.println("聖騎士聖能：" + paladin.getHolyPower() + "/100");
        }

        System.out.println();

        // ==========================================
        // 12. 模擬戰鬥回合（新增）
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("        ⚔️  模擬戰鬥回合");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        System.out.println("【進行 3 回合戰鬥】");

        for (int round = 1; round <= 3; round++) {
            System.out.println("\n━━━━━━━━ 回合 " + round + " ━━━━━━━━");

            for (Role currentRole : gameRoles) {
                if (!currentRole.isAlive()) continue;

                System.out.println("\n" + currentRole.getName() + " 的回合：");

                // ⭐ Paladin 的戰鬥邏輯：可以選擇攻擊/防禦/治療
                if (currentRole instanceof Paladin) {
                    Paladin p = (Paladin) currentRole;
                    double action = Math.random();

                    if (action < 0.3 && p.canDefend()) {
                        // 30% 機率防禦
                        System.out.println("選擇：防禦");
                        p.defend();
                    } else if (action < 0.6 && p.canHeal()) {
                        // 30% 機率治療
                        System.out.println("選擇：治療");
                        Role wounded = findMostWoundedRole(gameRoles);
                        if (wounded != null) {
                            p.heal(wounded);
                        }
                    } else {
                        // 40% 機率攻擊
                        System.out.println("選擇：攻擊");
                        Role target = getRandomAliveEnemy(gameRoles, currentRole);
                        if (target != null) {
                            currentRole.attack(target);
                        }
                    }
                }
                // ShieldSwordsMan 的戰鬥邏輯：可能防禦
                else if (currentRole instanceof ShieldSwordsMan) {
                    ShieldSwordsMan shield = (ShieldSwordsMan) currentRole;

                    if (Math.random() < 0.3 && shield.canDefend()) {
                        System.out.println("選擇：防禦");
                        shield.defend();
                    } else {
                        System.out.println("選擇：攻擊");
                        Role target = getRandomAliveEnemy(gameRoles, currentRole);
                        if (target != null) {
                            currentRole.attack(target);
                        }
                    }
                }
                // Magician 的戰鬥邏輯：攻擊或治療
                else if (currentRole instanceof Magician) {
                    Magician mage = (Magician) currentRole;

                    if (Math.random() < 0.4 && mage.canHeal()) {
                        System.out.println("選擇：治療");
                        Role wounded = findMostWoundedRole(gameRoles);
                        if (wounded != null) {
                            mage.heal(wounded);
                        }
                    } else {
                        System.out.println("選擇：攻擊");
                        Role target = getRandomAliveEnemy(gameRoles, currentRole);
                        if (target != null) {
                            currentRole.attack(target);
                        }
                    }
                }
                // 其他角色：直接攻擊
                else {
                    System.out.println("選擇：攻擊");
                    Role target = getRandomAliveEnemy(gameRoles, currentRole);
                    if (target != null) {
                        currentRole.attack(target);
                    }
                }
            }
        }
        System.out.println();

        // ==========================================
        // 13. 戰鬥後行為
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("          🌙 戰鬥結束");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        System.out.println("【所有存活角色的戰後行為】");
        System.out.println("─────────────────────────────────────");

        for (Role role : gameRoles) {
            if (role.isAlive()) {
                System.out.println("\n" + role.getName() + " 的戰後行為：");

                if (role instanceof RangedRole) {
                    RangedRole ranged = (RangedRole) role;
                    System.out.println("戰前能量：" + ranged.getEnergy() + "/" + ranged.getMaxEnergy());
                } else if (role instanceof Paladin) {  // ⭐ 新增
                    Paladin p = (Paladin) role;
                    System.out.println("戰前聖能：" + p.getHolyPower() + "/100");
                }

                role.afterBattle();

                if (role instanceof RangedRole) {
                    RangedRole ranged = (RangedRole) role;
                    System.out.println("戰後能量：" + ranged.getEnergy() + "/" + ranged.getMaxEnergy());
                } else if (role instanceof Paladin) {  // ⭐ 新增
                    Paladin p = (Paladin) role;
                    System.out.println("戰後聖能：" + p.getHolyPower() + "/100");
                }
            }
        }
        System.out.println();

        // ==========================================
        // 14. 最終狀態報告（增強）
        // ==========================================
        System.out.println("════════════════════════════════════════");
        System.out.println("          📊 最終狀態報告");
        System.out.println("════════════════════════════════════════");
        System.out.println();

        int aliveCount = 0;
        int deadCount = 0;
        int meleeCount = 0;
        int rangedCount = 0;
        int defendableCount = 0;  // ⭐ 新增
        int healableCount = 0;    // ⭐ 新增

        System.out.println("【角色狀態詳情】");
        System.out.println("─────────────────────────────────────");

        for (Role role : gameRoles) {
            String status = role.isAlive() ? "✅ 存活" : "💀 陣亡";
            String type = "";
            String abilities = "";  // ⭐ 新增

            if (role instanceof MeleeRole) {
                type = "⚔️  近戰";
                meleeCount++;
            } else if (role instanceof RangedRole) {
                type = "🎯 遠程";
                rangedCount++;
            }

            // ⭐ 新增：統計介面能力
            if (role instanceof Defendable) {
                abilities += "[防禦]";
                defendableCount++;
            }
            if (role instanceof Healable) {
                abilities += "[治療]";
                healableCount++;
            }

            System.out.println(status + " | " + type + " " + abilities + " | " + role);

            if (role.isAlive()) {
                aliveCount++;
            } else {
                deadCount++;
            }
        }

        System.out.println();
        System.out.println("【統計資訊】");
        System.out.println("─────────────────────────────────────");
        System.out.println("總角色數：" + gameRoles.length + " 名");
        System.out.println("近戰角色：" + meleeCount + " 名");
        System.out.println("遠程角色：" + rangedCount + " 名");
        System.out.println("可防禦角色：" + defendableCount + " 名");  // ⭐ 新增
        System.out.println("可治療角色：" + healableCount + " 名");    // ⭐ 新增
        System.out.println("存活角色：" + aliveCount + " 名");
        System.out.println("陣亡角色：" + deadCount + " 名");
        System.out.println("─────────────────────────────────────");
        System.out.println();

        // ⭐ 新增：介面能力總結
        System.out.println("【介面能力總結】");
        System.out.println("─────────────────────────────────────");
        System.out.println("✨ 介面讓不同類型的角色共享能力");
        System.out.println("✨ Paladin 展示了多重介面實作的強大");
        System.out.println("✨ 介面提供了靈活的能力組合方式");
        System.out.println();

        System.out.println("🎮 遊戲結束！");
    }

    // ========== 輔助方法 ==========

    /**
     * 找出生命值最低的角色
     */
    private static Role findMostWoundedRole(Role[] roles) {
        Role mostWounded = null;
        int lowestHealthPercent = 100;

        for (Role role : roles) {
            if (role.isAlive()) {
                int healthPercent = (role.getHealth() * 100) / role.getMaxHealth();
                if (healthPercent < lowestHealthPercent) {
                    lowestHealthPercent = healthPercent;
                    mostWounded = role;
                }
            }
        }

        return mostWounded;
    }

    /**
     * 隨機選擇一個存活的敵人
     */
    private static Role getRandomAliveEnemy(Role[] roles, Role self) {
        java.util.List<Role> aliveEnemies = new java.util.ArrayList<>();

        for (Role role : roles) {
            if (role.isAlive() && role != self) {
                aliveEnemies.add(role);
            }
        }

        if (aliveEnemies.isEmpty()) {
            return null;
        }

        int randomIndex = (int)(Math.random() * aliveEnemies.size());
        return aliveEnemies.get(randomIndex);
    }
}
