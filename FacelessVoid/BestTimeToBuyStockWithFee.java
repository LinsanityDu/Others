就是卖有手续费，而且手续费恒定，所以我就是在II的基础上算profit时候减去手续费

public int maxProfit(int[] prices, int[] transFee) {
    if(prices == null || prices.length == 0) {
        return 0;
    }
    int maxProfit = 0;
    for(int i=1; i < prices.length; i++) {
        int buy = prices[i-1]+transFee[i-1];
        int sell = prices-transFee[i-1];
        if(sell > buy) {
            maxProfit += sell-buy;
        }
    }    
    return maxProfit;
}