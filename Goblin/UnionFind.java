class UnionFind{
		HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();
		UnionFind(HashSet<Integer> hashSet){
			for(Integer now : hashSet) {
				father.put(now, now);
			}
		}
                int find(int x){
                	int parent =  father.get(x);
                	while(parent!=father.get(parent)) {
                		parent = father.get(parent);
                	}
                	return parent;
                }
                int compressed_find(int x){
                	int parent =  father.get(x);
                	while(parent!=father.get(parent)) {
                		parent = father.get(parent);
                	}
                	int temp = -1;
                	int fa = father.get(x);
                	while(fa!=father.get(fa)) {
                		temp = father.get(fa);
                		father.put(fa, parent) ;
                		fa = temp;
                	}
                	return parent;
                		
                }
                
                void union(int x, int y){
                	int fa_x = find(x);
                	int fa_y = find(y);
                	if(fa_x != fa_y)
                		father.put(fa_x, fa_y);
                }
	}