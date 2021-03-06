package com.antimeta.game.util;

import android.support.annotation.NonNull;

import com.antimeta.game.ormlite.entity.Enemy;
import com.antimeta.game.ormlite.entity.Player;
import com.antimeta.game.ormlite.entity.Stats;

public class StatsUtil {

    public static Integer getPlayerDamage(Player player, Enemy enemy){
        return calculateStats(player.getStats(), enemy.getStats());
    }

    public static Integer getEnemyDamage(Player player, Enemy enemy){
        return calculateStats(enemy.getStats(), player.getStats());
    }

    private static Integer calculateStats(Stats stats1, Stats stats2){
        Integer result = stats1.getAttack() - stats2.getDefense();
        if(result <= 0){
            return 0;
        }
        return result;
    }

    public static boolean checkIfAlive(Integer health){
        return health > 0;
    }

    public static Stats calculateEnemyStats(Stats stats, Integer level){
        Integer attack = stats.getAttack();
        Double attackSpeed = stats.getAttackSpeed();
        Integer agility = stats.getAgility();
        Integer armor = stats.getArmor();
        Double block = stats.getBlock();
        Double crit = stats.getCrit();
        Double critDamage = stats.getCritDamage();
        Integer defense = stats.getDefense();
        Double dodge = stats.getDodge();
        Integer health = stats.getHealth();
        Integer intellect = stats.getIntellect();
        Double lifeSteal = stats.getLifeSteal();
        Integer mana = stats.getMana();
        Integer manaRegen = stats.getManaRegen();
        Integer strength = stats.getStrength();
        Double speed = stats.getSpeed();
        Integer spellPower = stats.getSpellPower();
        Integer vitality = stats.getVitality();

        if(vitality != 0){
            stats = calculateVitalityStats(stats, health, defense, level);
        }

        if(strength != 0){
            stats = calculateStrengthStats(stats, health, defense, attack, level);
        }
        if(agility != 0) {
            stats = calculateAgilityStats(stats, health, defense, attack, level);
        }
        if(intellect != 0){
            stats = calculateIntellectStats(stats, attack, mana, level);
        }

        return stats;
    }

    private static Stats calculateVitalityStats(Stats stats, Integer startHealth, Integer startDefense, Integer level){
        Integer vitality = stats.getVitality();

        stats.setHealth(calculateStatValue(0.03, vitality, startHealth, stats.getHealth(), level));
        stats.setDefense(calculateStatValue(0.01, vitality, startDefense, stats.getDefense(), level));

        return stats;
    }

    private static Stats calculateStrengthStats(Stats stats, Integer startHealth, Integer startDefense, Integer startAttack, Integer level){
        Integer strength = stats.getStrength();


        stats.setHealth(calculateStatValue(0.01, strength, startHealth, stats.getHealth(), level));
        stats.setDefense(calculateStatValue(0.03, strength, startDefense, stats.getDefense(), level));
        stats.setAttack(calculateStatValue(0.01, strength, startAttack, stats.getAttack(), level));

        return stats;
    }

    private static Stats calculateAgilityStats(Stats stats, Integer startHealth, Integer startDefense, Integer startAttack, Integer level){
        Integer agility = stats.getAgility();

        stats.setHealth(calculateStatValue(0.01, agility, startHealth, stats.getHealth(), level));
        stats.setDefense(calculateStatValue(0.01, agility, startDefense, stats.getDefense(), level));
        stats.setAttack(calculateStatValue(0.03, agility, startAttack, stats.getAttack(), level));

        return stats;
    }

    private static Stats calculateIntellectStats(Stats stats, Integer startAttack, Integer startMana, Integer level){
        Integer intellect = stats.getIntellect();

        stats.setAttack(calculateStatValue(0.01, intellect, startAttack, stats.getAttack(), level));
        stats.setMana(calculateStatValue(0.03, intellect, startMana, stats.getMana(), level));

        return stats;
    }

    @NonNull
    private static Integer calculateStatValue(Double increasement, Integer statLevel, Integer baseValue, Integer currentValue, Integer level){
        Double totalMultiplier = (1.00 + ((increasement * statLevel) * level));
        Double totalNeededToAdd = (baseValue * totalMultiplier) - baseValue;
        return totalNeededToAdd.intValue() + currentValue;
    }

    @NonNull
    private static Double calculateStatValue(Double increasement, Integer statLevel, Double baseValue, Double currentValue, Integer level){
        Double totalMultiplier = (1.00 + ((increasement * statLevel) * level));
        Double totalNeededToAdd = (baseValue * totalMultiplier) - baseValue;
        return totalNeededToAdd + currentValue;
    }

    public static Stats getCalculatedEquipmentStats(Stats stats, String rarity){
        Double increasement;
        switch (rarity){
            case "Legendary":
                increasement = 0.10;
                break;
            case "Epic":
                increasement = 0.05;
                break;
            case "Rare":
                increasement = 0.02;
                break;
            case "Uncommon":
                increasement = 0.01;
                break;
            case "Common":
                increasement = 0.00;
                break;
            default:
                increasement = 0.00;
                break;
        }
        stats = calculateEquipmentStats(stats, increasement);
        return stats;
    }

    private static Stats calculateEquipmentStats(Stats stats, Double increasement){
        Integer vitality = stats.getVitality();
        Integer strength = stats.getStrength();
        Integer agility = stats.getAgility();
        Integer intellect = stats.getIntellect();

        stats.setVitality(calculateStatValue(increasement, 1, vitality, vitality, 1));
        stats.setStrength(calculateStatValue(increasement, 1, strength, strength, 1));
        stats.setAgility(calculateStatValue(increasement, 1, agility, agility, 1));
        stats.setIntellect(calculateStatValue(increasement, 1, intellect, intellect, 1));
        return stats;
    }
}
