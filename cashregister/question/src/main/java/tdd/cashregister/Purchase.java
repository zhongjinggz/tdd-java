package tdd.cashregister;

public class Purchase {
    private Item[] items;

    public Purchase(Item[] items) {

        this.items = items;
    }

    public String description() {
        String out = "";

        for (Item item : items) {
            out += item.getName() + "\t" + item.getPrice() + "\n";
        }

        return out;
    }
}
