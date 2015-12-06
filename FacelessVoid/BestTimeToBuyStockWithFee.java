就是卖有手续费，而且手续费恒定，所以我就是在II的基础上算profit时候减去手续费

public int maxProfit(int[] prices, int[] transFee) {
    if(prices == null || prices.length == 0) {
        return 0;
    }
    int maxProfit = 0;
    for(int i=1; i < prices.length; i++) {
        int buy = prices[i-1]+transFee[i-1];. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
        int sell = prices-transFee[i-1];. 1point3acres.com/bbs
        if(sell > buy) {
            maxProfit += sell-buy;
        }. 1point3acres.com/bbs
    }    . 鍥磋鎴戜滑@1point 3 acres
    return maxProfit;.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
}