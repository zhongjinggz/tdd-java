package tdd.cashregister;

public class Purchase {
    private Item[] items;

    public Purchase(Item[] items) {

        this.items = items;
    }

    public String description() {
        StringBuilder result = new StringBuilder();

        for (Item item : items) {
            //result += item.getName() + "\t" + item.getPrice() + "\n";
            result.append(item.getName())
                    .append("\t")
                    .append(item.getPrice())
                    .append("\n");
        }

        return result.toString();
    }
}
