package BUY;

import Market.Market;
import jade.core.Agent;

public class Buyer_Agent  extends Agent {
    private static int index = 0;
    public int amount_money;
    private String name;
    private Buyer_UI UI;
    private Market market;

    public Buyer_Agent(){
        this.index++;
        this.amount_money = 5000;
        this.name = "Buyer_"+String.valueOf(index);
        this.UI = new Buyer_UI(this);
        this.UI.show_ui();
        this.market = new Market();
    }

    @Override
    protected void setup() {
        System.out.println(this.name+" is up & running !");
    }

    @Override
    protected void takeDown() {
        System.out.println("Shut down "+this.name);
    }

    public String get_name() {
        return this.name;
    }

    public Market getMarket(){
        return this.market;
    }

}







