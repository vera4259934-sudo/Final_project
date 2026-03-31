package p;

import java.util.*;

public class MistakeCounter2 {

    private final Recipe_2.IceKind iceKind;
    private final Recipe_2.TeaKind teaKind;
    private final Recipe_2.TapiokaKind tapiokaKind;
    private final Recipe_2.TrubKind trubKind;

    public MistakeCounter2(Recipe_2.IceKind iceKind, Recipe_2.TeaKind teaKind, Recipe_2.TapiokaKind tapiokaKind, Recipe_2.TrubKind trubKind) {
        this.iceKind = iceKind;
        this.teaKind = teaKind;
        this.tapiokaKind = tapiokaKind;
        this.trubKind = trubKind;
    }

//    public static MistakeCounter2 getSharedInstance() {
//        return sharedInstance;
//    }
//    private static final MistakeCounter2 sharedInstance = new MistakeCounter2();

    public void setIceAmount(int amount, Recipe_2.IceKind iceKind) {
        iceAmount.put(iceKind, amount);
    }

    public void setTeaAmount(int amount, Recipe_2.TeaKind teaKind) {
        teaAmount.put(teaKind, amount);
    }

    public void setTapiokaAmount(int amount, Recipe_2.TapiokaKind tapiokaKind) {
        tapiokaAmount.put(tapiokaKind, amount);
    }

    public void setTrubAmount(int amount, Recipe_2.TrubKind trubKind) {
        trubAmount.put(trubKind, amount);
    }

    public void setVanillaAmount(int amount) {
        vanillaAmount = amount;
    }

    public void setSugarAmount(int amount) {
        sugarAmount = amount;
    }

    public void setMilkAmount(int amount) {
        milkAmount = amount;
    }

    private Map<Recipe_2.IceKind, Integer> iceAmount = new HashMap<>();
    private Map<Recipe_2.TeaKind, Integer> teaAmount = new HashMap<>();
    private Map<Recipe_2.TapiokaKind, Integer> tapiokaAmount = new HashMap<>();
    private int vanillaAmount;
    private int sugarAmount;
    private int milkAmount;
    private Map<Recipe_2.TrubKind, Integer> trubAmount = new HashMap<>();

    public int getMistakeAmount() {
        int mistakeAmount = 0;

        for(Recipe_2.IceKind kind : Recipe_2.IceKind.values()) {
            if(kind == iceKind) {
                int iceAmount = this.iceAmount.getOrDefault(kind, 0);
                if(iceAmount != Recipe_2.ICE_RECIPE_AMOUNT) {
                    mistakeAmount += 1;
                }
            }
            else if(iceAmount.containsKey(kind)) {
                mistakeAmount += 1;
            }
        }

        for(Recipe_2.TeaKind kind : Recipe_2.TeaKind.values()) {
            if(kind == teaKind) {
                int teaAmount = this.teaAmount.getOrDefault(kind, 0);
                if(teaAmount != Recipe_2.TEA_RECIPE_AMOUNT) {
                    mistakeAmount += 1;
                }
            }
            else if(teaAmount.containsKey(kind)) {
                mistakeAmount += 1;
            }
        }

        for(Recipe_2.TapiokaKind kind : Recipe_2.TapiokaKind.values()) {
            if(kind == tapiokaKind) {
                int tapiokaAmount = this.tapiokaAmount.getOrDefault(kind, 0);
                if(tapiokaAmount != Recipe_2.TAPIOKA_RECIPE_AMOUNT) {
                    mistakeAmount += 1;
                }
            }
            else if(tapiokaAmount.containsKey(kind)) {
                mistakeAmount += 1;
            }
        }

        if(vanillaAmount != Recipe_2.VANILLA_RECIPE_AMOUNT) {
            mistakeAmount += 1;
        }

        if(sugarAmount != Recipe_2.SUGAR_RECIPE_AMOUNT) {
            mistakeAmount += 1;
        }

        if(milkAmount != Recipe_2.MILK_RECIPE_AMOUNT) {
            mistakeAmount += 1;
        }

        for(Recipe_2.TrubKind kind : Recipe_2.TrubKind.values()) {
            if(kind == trubKind) {
                int trubAmount = this.trubAmount.getOrDefault(kind, 0);
                if(trubAmount != Recipe_2.TRUB_RECIPE_AMOUNT) {
                    mistakeAmount += 1;
                }
            }
            else if(trubAmount.containsKey(kind)) {
                mistakeAmount += 1;
            }
        }

        return mistakeAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getMistakeAmount());
        sb.append(" mistake(s) entered vs recipe:");
        for(Recipe_2.IceKind kind : Recipe_2.IceKind.values()) {
            int recipeAmount = iceKind == kind ? Recipe_2.ICE_RECIPE_AMOUNT : 0;
            sb.append(formatAmount(String.valueOf(kind), iceAmount.getOrDefault(kind, 0), recipeAmount));
        }
        for(Recipe_2.TeaKind kind : Recipe_2.TeaKind.values()) {
            int recipeAmount = teaKind == kind ? Recipe_2.TEA_RECIPE_AMOUNT : 0;
            sb.append(formatAmount(String.valueOf(kind), teaAmount.getOrDefault(kind, 0), recipeAmount));
        }
        for(Recipe_2.TapiokaKind kind : Recipe_2.TapiokaKind.values()) {
            int recipeAmount = tapiokaKind == kind ? Recipe_2.TAPIOKA_RECIPE_AMOUNT : 0;
            sb.append(formatAmount(String.valueOf(kind), tapiokaAmount.getOrDefault(kind, 0), recipeAmount));
        }
        sb.append(formatAmount("vanilla", vanillaAmount, Recipe_2.VANILLA_RECIPE_AMOUNT));
        sb.append(formatAmount("sugar", sugarAmount, Recipe_2.SUGAR_RECIPE_AMOUNT));
        sb.append(formatAmount("milk", milkAmount, Recipe_2.MILK_RECIPE_AMOUNT));
        for(Recipe_2.TrubKind kind : Recipe_2.TrubKind.values()) {
            int recipeAmount = trubKind == kind ? Recipe_2.TRUB_RECIPE_AMOUNT : 0;
            sb.append(formatAmount(String.valueOf(kind), trubAmount.getOrDefault(kind, 0), recipeAmount));
        }
        return sb.toString();
    }

    private String formatAmount(String product, int enteredAmount, int recipeAmount) {
        return String.format("\n\t%s: %s vs %d: correct? %b",
                product, String.valueOf(enteredAmount), recipeAmount, enteredAmount == recipeAmount);
    }
}