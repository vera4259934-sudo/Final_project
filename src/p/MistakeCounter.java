package p;

public class MistakeCounter {

    private MistakeCounter() {
    }

    public static MistakeCounter getSharedInstance() {
        return sharedInstance;
    }

    private static final MistakeCounter sharedInstance = new MistakeCounter();

    public void setMilkAmount(int amount) {
        milkAmount = amount;
    }

    public void setFlourAmount(int amount) {
        flourAmount = amount;
    }

    public void setEggAmount(int amount) {
        eggAmount= amount;
    }

    public void setCheese1Amount(int amount) {
        cheese1Amount = amount;
    }

    public void setCheese2Amount(int amount) {
        cheese2Amount = amount;
    }

    public void setTomatoAmount(int amount) {
        tomatoAmount = amount;
    }

    public void setOwenTemperature(int temperature) {
        owenTemperature = temperature;
    }

    private int milkAmount;
    private int flourAmount;
    private int eggAmount;
    private int cheese1Amount;
    private int cheese2Amount;
    private int tomatoAmount;
    private int owenTemperature;

    public int getMistakeAmount() {
        int mistakeAmount = 0;
        if(milkAmount != Recipe_1.MILK_RECIPE_AMOUNT) {
            mistakeAmount += 1;
        }

        if(flourAmount != Recipe_1.FLOUR_RECIPE_AMOUNT) {
            mistakeAmount += 1;
        }

        if(eggAmount != Recipe_1.EGGS_RECIPE_AMOUNT) {
            mistakeAmount += 1;
        }

        if(cheese1Amount != Recipe_1.CHEESE1_RECIPE_AMOUNT) {
            mistakeAmount += 1;
        }

        if(cheese2Amount != Recipe_1.CHEESE2_RECIPE_AMOUNT) {
            mistakeAmount += 1;
        }

        if(tomatoAmount != Recipe_1.TOMATO_RECIPE_AMOUNT) {
            mistakeAmount += 1;
        }

        if(owenTemperature != Recipe_1.OWEN_TEMPERATURE) {
            mistakeAmount += 1;
        }

        return mistakeAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getMistakeAmount());
        sb.append(" mistake(s) entered vs recipe:");
        sb.append(formatAmount("milk", milkAmount, Recipe_1.MILK_RECIPE_AMOUNT));
        sb.append(formatAmount("flour", flourAmount, Recipe_1.FLOUR_RECIPE_AMOUNT));
        sb.append(formatAmount("eggs", flourAmount, Recipe_1.EGGS_RECIPE_AMOUNT));
        sb.append(formatAmount("cheese-1", cheese1Amount, Recipe_1.CHEESE1_RECIPE_AMOUNT));
        sb.append(formatAmount("cheese-2", cheese2Amount, Recipe_1.CHEESE2_RECIPE_AMOUNT));
        sb.append(formatAmount("tomato", tomatoAmount, Recipe_1.TOMATO_RECIPE_AMOUNT));
        sb.append(formatAmount("owenTemp", owenTemperature, Recipe_1.OWEN_TEMPERATURE));
        return sb.toString();
    }

    private String formatAmount(String product, int enteredAmount, int recipeAmount) {
        return String.format("\n\t%s: %d vs %d: correct? %b",
                product, enteredAmount, recipeAmount, enteredAmount == recipeAmount);
    }
}