package Market;

public class Market{
    public static boolean is_market_on = false;
    private static Market_UI UI = new Market_UI();

    public Market() {
        if(! this.is_market_on){
            UI.show_ui();
            this.is_market_on = true;
        }
    }

    public Market_UI get_market_UI(){
        return this.UI;
    }
}




