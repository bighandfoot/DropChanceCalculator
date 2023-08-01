package Calculator;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static class DropChanceCalc {

        private static double inputBaseChance() throws Exception {
            Scanner baseDrop = new Scanner(System.in);
            System.out.println("Base Chance: ");
            System.out.println("(Please Do Not Add % At The End When Writing This Number)");
            double base = baseDrop.nextDouble();
            if (base <= 0 || base > 100) {
                throw new Exception("Base Chance Cannot Be Lower Than Or Equal to 0 or Larger Than 100!");
            }
            return base;
        }


        public static void main(String[] args) {
            try {
                double bottomFraction = 10000;

                Scanner userChoice = new Scanner(System.in);
                System.out.println("Would You Like To Check the Spawn/Drop Chance of a Mob, Item Or a Dungeon Item?");
                System.out.println("Please Make Sure You Type Your Choice In Exactly As Written Above,");
                System.out.println("Otherwise, The Calculator Won't Work As Intended.");
                String choice = userChoice.nextLine();

                if (choice.equalsIgnoreCase("mob") || choice.equalsIgnoreCase("dungeon chest")) {
                    double base = inputBaseChance();
                    double topFraction = base * 100;
                    double ratio = bottomFraction / topFraction;
                    System.out.println(1 + "/" + ratio);

                } else if (choice.equalsIgnoreCase("item")) {
                    double base = inputBaseChance();
                    Scanner MF = new Scanner(System.in);
                    System.out.println("Magic Find: ");
                    double magic = MF.nextDouble();

                    if (magic == 0 || magic < 0) {
                        throw new Exception("Magic Find Cannot Be Lower Than Or Equal to 0!");
                    }
                    if (magic > 350) {
                        throw new Exception("Magic Find Cannot Be That High!");
                    }
                    Scanner looting = new Scanner(System.in);
                    System.out.println("Looting Enchantment Level: ");
                    double loot = looting.nextDouble();
                    if (loot < 0 || loot > 5) {
                        throw new Exception("Your Looting Enchantment Level Cannot Be Lower Than 0 Or Higher Than 5!");
                    }
                    double personalOdds = base * (1 + magic / 100 + (loot * 0.15));
                    double topFraction = personalOdds * 100;
                    double ratio = bottomFraction / topFraction;

                    if (personalOdds > 100 && ratio < 1) {
                        System.out.println("~100%");
                        System.out.println("~1/1");
                    } else {

                        System.out.println("Drop Chance:");
                        System.out.format("%.7f or 1/%.0f\n", personalOdds, ratio);
                    }
                } else {
                    throw new Exception("You Must Choose Between an Item and a Mob!");
                }
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
                System.out.println("Please Check Your Input and Try Again.");
            }
        }
    }
}



