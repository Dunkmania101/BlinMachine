//package boris.blinmachine;
// This is my take on the program presented by LifeOfBoris in this video: https://youtu.be/FMIZEfjYmtM

import java.util.List;
import java.util.Scanner;

public class BlinMachine {
  public static void main(String[] args) {
    System.out.println("Hello!\nBlinmaker is starting up...\n");
    Scanner in = new Scanner(System.in);
    run(in);
    in.close();
    System.out.println("Goodbye!\nBlinmaker is shutting down...\n");
  }

  public static void run(Scanner in) {
    for (int i = 0; i < 25; i++) {
      try {
        Thread.sleep(25);
      } catch (InterruptedException e) {
        return;
      }
      System.out.print(".");
    }
    System.out.println("\n\n");


    Ingredient eggs = new Ingredient("eggs", 1);
    Ingredient milk = new Ingredient("milk (milliliters)", 200);
    Ingredient flour = new Ingredient("flour (grams)", 100);

    int i = 0;
    for (Ingredient ing : List.of(eggs, milk, flour)) {
      i++;
      if (!ing.prompt(in, i, 3)) {
        System.out.println("Not enough ".concat(ing.name).concat("!\nExiting..."));
        return;
      }
    }

    Integer maxBlins = Integer.valueOf(Double.valueOf(Math.min(Math.min(eggs.maxBlins(), milk.maxBlins()), flour.maxBlins())).intValue());
    System.out.println("\n\n\nYou can make up to ".concat(maxBlins.toString()).concat(" blin").concat(maxBlins == 1 ? "." : "s."));
  }

  public static class Ingredient {
    public final String name;
    public int amount;
    public final int min;

    public Ingredient(String name, int min) {
      this.name = name;
      this.min = min;
    }

    public boolean prompt(Scanner in, int progress, int endProgress) {
      System.out.print("\n\nSupply of ".concat(name).concat("?\n(".concat(Integer.toString(progress)).concat("/").concat(Integer.toString(endProgress)).concat(") > ")));
      this.amount = in.nextInt();
      return this.amount >= this.min;
    }

    public double maxBlins() {
      return Math.floor(this.amount / this.min);
    }
  }
}

