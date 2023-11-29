public class MovieChannel extends TVChannel{
    int additionalPrice = 15;
    public MovieChannel(String channelName, String language, String category, int price) {
        super(channelName, language, category, price);
    }

    @Override
    public int getPrice() {
        return super.getPrice() +additionalPrice;
    }
}
