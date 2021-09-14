package ReductionUltra;

import java.util.ArrayList;
import java.util.Random;

public class Factor {

    private boolean selected;
    private final boolean inDenominator;
    private int currentValue;
    private final ArrayList<Integer> reductionHistory;

    public Factor(int boundary)
    {
        Random random = new Random();
        currentValue = random.nextInt(boundary);
        inDenominator = random.nextBoolean();
        reductionHistory = new ArrayList<>(currentValue);
        selected = false;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public ArrayList<Integer> getReductionHistory() {
        return reductionHistory;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isInDenominator() {
        return inDenominator;
    }
}
