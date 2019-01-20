package moe.berd.nukkit.FHiddenMine;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigProvider {

    private static Config config = null;
    private static ConfigSection defaults = new ConfigSection() {
        {
            set("FillMode", true);
            set("ScanHeight", 3);
            set("ProtectWorlds", new ArrayList<String>() {
                {
                    add("world");
                }
            });
            set("Ores", new ArrayList<Integer>() {
                {
                    add(14);
                    add(15);
                    add(16);
                    add(21);
                    add(56);
                    add(73);
                    add(74);
                    add(129);
                    add(153);
                }
            });
            set("Filters", new ArrayList<Integer>() {
                {
                    add(0);
                    add(6);
                    add(8);
                    add(9);
                    add(10);
                    add(11);
                    add(18);
                    add(20);
                    add(26);
                    add(27);
                    add(28);
                    add(29);
                    add(30);
                    add(31);
                    add(32);
                    add(33);
                    add(34);
                    add(37);
                    add(38);
                    add(39);
                    add(40);
                    add(44);
                    add(50);
                    add(51);
                    add(52);
                    add(53);
                    add(54);
                    add(55);
                    add(59);
                    add(60);
                    add(63);
                    add(64);
                    add(65);
                    add(66);
                    add(67);
                    add(68);
                    add(69);
                    add(70);
                    add(71);
                    add(72);
                    add(75);
                    add(76);
                    add(77);
                    add(78);
                    add(79);
                    add(81);
                    add(83);
                    add(85);
                    add(88);
                    add(90);
                    add(92);
                    add(93);
                    add(94);
                    add(95);
                    add(96);
                    add(101);
                    add(102);
                    add(104);
                    add(105);
                    add(106);
                    add(107);
                    add(108);
                    add(109);
                    add(111);
                    add(113);
                    add(114);
                    add(115);
                    add(116);
                    add(117);
                    add(118);
                    add(119);
                    add(120);
                    add(122);
                    add(126);
                    add(127);
                    add(128);
                    add(130);
                    add(131);
                    add(132);
                    add(134);
                    add(135);
                    add(136);
                    add(138);
                    add(139);
                    add(140);
                    add(141);
                    add(142);
                    add(143);
                    add(144);
                    add(145);
                    add(146);
                    add(147);
                    add(148);
                    add(149);
                    add(150);
                    add(151);
                    add(154);
                    add(156);
                    add(158);
                    add(160);
                    add(161);
                    add(163);
                    add(164);
                    add(165);
                    add(166);
                    add(167);
                    add(171);
                    add(175);
                    add(176);
                    add(177);
                    add(178);
                    add(180);
                    add(182);
                    add(183);
                    add(184);
                    add(185);
                    add(186);
                    add(187);
                    add(190);
                    add(191);
                    add(193);
                    add(194);
                    add(195);
                    add(196);
                    add(197);
                    add(198);
                    add(199);
                    add(200);
                    add(202);
                    add(203);
                    add(204);
                    add(205);
                    add(207);
                    add(208);
                    add(218);
                    add(230);
                    add(238);
                    add(239);
                    add(240);
                    add(241);
                    add(244);
                    add(250);
                    add(253);
                    add(254);
                }
            });
        }
    };

    public static Config getConfig() {
        return config;
    }

    public static ConfigSection getDefaults() {
        return new ConfigSection(defaults);
    }

    public static boolean validateIndex(String index) {
        return defaults.containsKey(index);
    }

    public static void init(Main main) {
        main.getDataFolder().mkdirs();
        config = new Config(new File(main.getDataFolder(), "config.yml").toString(), Config.YAML, defaults);
        ConfigSection data = getDefaults();
        for (String key : config.getAll().keySet()) {
            data.set(key, config.get(key, data.get(key)));
        }
        data.set("ProtectWorlds", data.getStringList("ProtectWorlds").stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList()));
        config.setAll(data);
        config.save();
    }

    public static void save() {
        config.save();
    }

    public static void set(String key, Object value) {
        if (!validateIndex(key)) {
            throw new RuntimeException("Invalid config index.");
        }
        config.set(key, value);
        config.save();
    }

    public static int getInt(String key) {
        return config.getInt(key, defaults.getInt(key));
    }

    public static double getDouble(String key) {
        return config.getDouble(key, defaults.getDouble(key));
    }

    public static String getString(String key) {
        return config.getString(key, defaults.getString(key));
    }

    public static boolean getBoolean(String key) {
        return config.getBoolean(key, defaults.getBoolean(key));
    }

    public static List<Integer> getIntegerList(String key) {
        return config.getIntegerList(key);
    }

    public static List<String> getStringList(String key) {
        return config.getStringList(key);
    }
}
