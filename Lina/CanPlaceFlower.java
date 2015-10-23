/*Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die. Given a flowerbed (represented as an array containing booleans), return if a given number of new flowers can be planted in it without violating the no-adjacent-flowers rule 
Sample inputs 

Input: 1,0,0,0,0,0,1,0,0 

3 => true 
4 => false 

Input: 1,0,0,1,0,0,1,0,0 

1 => true 
2 => false 
input: 0 

1 => true 
2 => false */ 

public boolean canPlaceFlowers(List<Boolean> flowerbed, int numberToPlace) { 

	// Implementation here 
	// list size 0
	int size = flowerbed.size();
	    
	if (size == 0 || size < numberToPlace)
	{
	    return false;
	}
	    
	if (numberToPlace == 0)
	{
	    return true;
	}

	for (int i = 0; i < size; i++) {
		if (i == 0) {
			if (!flowerbed.get(i) && i + 1 < size && !flowerbed.get(i + 1)) {
				numberToPlace--;
				flowerbed.set(i, true);
			} 
		} else if (i == size - 1) {
			if (!flowerbed.get(i) && i > 0 && !flowerbed.get(i - 1)) {
				numberToPlace--;
				flowerbed.set(i, true);
			}
		} else {
			if (!flowerbed.get(i - 1) && !flowerbed.get(i + 1)) {
				numberToPlace--;
				flowerbed.set(i, true);
			}
		}
		if (numberToPlace == 0) return true;
	}
	return numberToPlace == 0;




}


for (int i = 0; i < n; i++) {
    if (i == 0 || (i > 0 && flowerbed[i-1] == false)) {
        flower = true;
        num--;
        if (num == 0) return true;
    }
}