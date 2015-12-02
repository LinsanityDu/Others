/*Given an array of contacts with phone numbers/emails you should detect and union identical contacts. 

For example, given the following contacts array: 

[ [ "John", "john@gmail.com", "john@fb.com"], 
[ "Dan", "dan@gmail.com", "+1234567"], 
[ "john123", "+5412312", "john123@skype.com"], 
[ "john1985", "+5412312", "john@fb.com"] ] 

We can see that john1985, John and john123 are the same person by their contact information. 

We should output 

[[ 0, 2, 3], [1]] (0,2,3 are the same person and 1 is another one)*/



public static void main(String[] args) {
    HashMap<String, Integer> h = new HashMap();
        String[][] A = { { "John", "john@gmail.com", "john@fb.com"}, 
                                { "Dan", "dan@gmail.com", "+1234567"}, 
                                {"john123", "+5412312", "john123@skype.com"}, 
                                { "john1985", "+5412312", "john@fb.com"}  };
                
        int code = 0;
        for(int i = 0; i < A.length; i++) {
                int c = -1;
        Set<Integer> groupings = new HashSet<Integer>();
                for(int j = 0; j < A[i].length; j++) {
                        String s = A[i][j];
                        if(h.containsKey(s)){
                int value=h.get(s);
                                grouping.add(value);
                                c = Math.min(c,value);
            }
                }
                if(c == -1){
                        c = code++;
                }
        else{
            groupings.remove(c);
            for(Integer k : groupings ){
                for(int m=0;m<A[0].length;m++){
                    h.put(A[k][m],c);
                }
            }
        }
        for(int j = 0; j < A[i].length; j++){
                        String s = A[i][j];. more info on 1point3acres.com
                        h.put(s,  c);
                }
        }
        for(int i = 0; i < A.length; i++) {
                System.out.println(h.get(A[i][0]));
        }
        }


public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashMap<String, Integer> h = new HashMap();
		String[][] A = { { "John", "john@gmail.com", "john@fb.com"}, 
				{ "Dan", "dan@gmail.com", "+1234567"}, 
				{"john123", "+5412312", "john123@skype.com"}, 
				{ "john1985", "+5412312", "john@fb.com"}  };
		
		int code = 0;
		for(int i = 0; i < A.length; i++) {
			int c = -1;
      Set<Integer> groupings = new HashSet<Integer>();
			for(int j = 0; j < A[i].length; j++) {
				String s = A[i][j];
				if(h.containsKey(s)) {
          int value=h.get(s);
					grouping.add(value);
					c = Math.min(c,value);
          }
			}
			if(c == -1) {
				c = code++;
			}
      else
      {
        groupings.remove(c);
        for(Integer k : groupings )
        {
          for(int m=0;m<A[0].length;m++)
          {
            h.put(A[k][m],c);
          }
        }
      
      }
			for(int j = 0; j < A[i].length; j++) {
				String s = A[i][j];
				h.put(s,  c);
			}
		}
		
		for(int i = 0; i < A.length; i++) {
			System.out.println(h.get(A[i][0]));
		}
		

	}