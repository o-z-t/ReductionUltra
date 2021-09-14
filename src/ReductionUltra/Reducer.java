package ReductionUltra;

public class Reducer {

    public static void reduce(Factor a, Factor b, int redx)
    {
        if (redx != 0 && a.isSelected()
                && b.isSelected() && a.isInDenominator() != b.isInDenominator())
        {
            if (a.getCurrentValue() % redx == 0 && b.getCurrentValue() % redx == 0)
            {
                a.setCurrentValue(a.getCurrentValue()/redx);
                b.setCurrentValue(b.getCurrentValue()/redx);
            }
        }

        else
        {
            System.out.println("Invalid selection or invalid reduction factor.");
        }

    }
}
