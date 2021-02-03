package SELL;

import Market.*;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Seller_Agent  extends Agent {
    private static int index = 0;
    public int amount_money;
    private String name;
    private Seller_UI UI;
    private Market market;

    public Seller_Agent(){
        this.index++;
        this.amount_money = 0;
        this.name = "Seller_"+String.valueOf(index);
        this.market = new Market();
        this.UI = new Seller_UI(this,this.market.get_market_UI());
        this.UI.show_ui();
    }

    @Override
    protected void setup() {
        System.out.println(this.name+" is up & running !");
        this.addBehaviour(new Behaviour(this));
    }

    @Override
    protected void takeDown() {
        System.out.println("Shut down "+this.name);
    }

    public String get_name() {
        return this.name;
    }

    public Seller_UI get_UI(){
        return this.UI;
    }

//    public void sell_book(String type,String name,int price,String condition,String author){
//        Book new_book = new Book(this.getAID().getLocalName(),type,name,price,condition,author);
//        this.market.get_market_UI().add_book(new_book);
//    }
}

class Behaviour extends CyclicBehaviour {
    private Seller_Agent agent;
    Behaviour(Seller_Agent agent){
        this.agent = agent;
    }

    @Override
    public void action() {
        ACLMessage msg = this.getAgent().receive();
        if (msg!=null) {
            String content = msg.getContent();
            // result = {book_name , book_price}
            String[] result = content.split("\\s",0);
            int profilt = Integer.parseInt(result[1]);
            this.agent.amount_money += profilt;
            this.agent.get_UI().refresh_amount_of_money();
            this.agent.get_UI().show_sold_msg(result[0],profilt);
        }
    }
}



