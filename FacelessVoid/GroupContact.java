/*Given an array of contacts with phone numbers/emails you should detect and union identical contacts. 

For example, given the following contacts array: 

[ [ "John", "john@gmail.com", "john@fb.com"], 
[ "Dan", "dan@gmail.com", "+1234567"], 
[ "john123", "+5412312", "john123@skype.com"], 
[ "john1985", "+5412312", "john@fb.com"] ] 

We can see that john1985, John and john123 are the same person by their contact information. 

We should output 

[[ 0, 2, 3], [1]] (0,2,3 are the same person and 1 is another one)*/

有这么一个class Contact，里面有一个string的name，和一个vector 装着email address，是这个Contact有的address，用一个list装着是因为一个人有可 能有多个email，现在给你vector，比如
{
    { "John", {"john@gmail.com"} },
    { "Mary", {"mary@gmail.com"} },
    { "John", {"john@yahoo.com"} },
    { "John", {"john@gmail.com", "john@yahoo.com", "john@hotmail.com"} },
    { "Bob",  {"bob@gmail.com"} }
}
现在我们知道如果email address相同的话，那么就说明是同一个人，现在要做的是根据这些email address，把同一个contact给group起来，生成一个vector<vector<Contact>>


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


有这么一个class Contact，里面有一个string的name，和一个vector 装着email address，是这个Contact有的address，用一个list装着是因为一个人有可 能有多个email，现在给你vector，比如

{
    { "John", {"john@gmail.com"} },
    { "Mary", {"mary@gmail.com"} },
    { "John", {"john@yahoo.com"} },
    { "John", {"john@gmail.com", "john@yahoo.com", "john@hotmail.com"} },
    { "Bob",  {"bob@gmail.com"} }
}
现在我们知道如果email address相同的话，那么就说明是同一个人，现在要做的是根据这些email address，把同一个contact给group起来，生成一个vector<vector<Contact>>

class Contact {
public:
    string name;
    vector<string> emails;
};
class UnionFind {
    vector<int> father, ranks;
public:
    UnionFind(int num_nodes) {
        for (int i = 0; i < num_nodes; i++) {
            father.push_back(i);
            ranks.push_back(0);
        }
    }
    int find(int x) {
        if (x == father[x]) return x;
        return father[x] = find(father[x]);
    }
    void do_union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;
        if (ranks[x] < ranks[y]) {
            father[x] = y;
        } else {
            father[y] = x;
            if (ranks[x] == ranks[y]) {
                ranks[x]++;
            }
        }
    }
};
vector<vector<Contact>> group_contacts(vector<Contact>& input) {
    unordered_map<string,vector<int>> email_record;
    
    int n = (int)input.size();
    for (int k = 0; k < input.size(); k++) {
        for (auto email : input[k].emails) {
            email_record[email].push_back(k);
        }
    }
    UnionFind uf(n);
    for (auto p : email_record) {
        for (int i = 0; i < p.second.size() - 1; i++) {
            uf.do_union(p.second[i], p.second[i + 1]);
        }
    }
    unordered_map<int,vector<int>> groups;
    for (int i = 0; i < n; i++) groups[uf.find(i)].push_back(i);
    
    vector<vector<Contact>> ret;
    for (auto& p : groups) {
        vector<Contact> vs;
        for (auto& c : p.second) vs.push_back(input[c]);
        ret.push_back(vs);
    }
    return ret;
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