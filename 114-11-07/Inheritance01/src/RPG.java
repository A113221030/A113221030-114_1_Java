public class RPG {
    public static void main(String[] args) {
        SwordsMan swordsMan_light = new SwordsMan("聖光星要騎士", 100, 20);
        SwordsMan swordsMan_dark = new SwordsMan("地域黑騎士", 100, 25);

        Magician magician_light = new Magician("光魔導士", 90, 120, 30);
        Magician magician_dark = new Magician("黑魔導士", 85, 110, 28);

        System.out.println("戰鬥開始！");
        swordsMan_light.attack(swordsMan_dark);
        swordsMan_dark.attack(swordsMan_light);
        magician_light.attack(magician_dark);
        magician_dark.attack(magician_light);
    }
}