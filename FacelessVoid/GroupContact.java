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
                                    String s = A[i][j];
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


// 并查集
import java.util.*;
import java.lang.*;
import java.io.*;
 
/* Name of the class has to be "Main" only if the class is public. */
class Contact {
        String name;
        ArrayList<String> details;
        int group;
        Contact(String n, ArrayList<String> arr, int g) {
            name = n;
            details = arr;
            group = g;
        }
    }
 
class Ideone
{
 
    public static void main (String[] args) throws java.lang.Exception
    {
        // your code goes here
        String [][]contacts =  {{"John", "john@gmail.com", "john@fb.com"}, 
                                {"Dan", "dan@gmail.com", "+1234567"}, 
                                {"john123", "5412312", "john123@skype.com"}, 
                                {"john1985", "5412312", "john@fb.com"}, 
                                {"john19856", "john123@skype.com", "john@fb1.com"},
                                {"Dan2", "dan123@gmail.com", "+1234567"},{"Dan3", "dan@gmail.com", "+123456712312"},
                                {"Sandy", "sandy@gmail.com", "+123456712"},{"sandy4", "sandy@fb.com", "sandy@gmail.com"}};
 
        ArrayList<Contact> contactarray = new ArrayList<Contact>();
        for (int i=0;i<contacts.length;i++) {
            ArrayList<String> arr = new ArrayList<String>();
            for (int j=1;j<contacts[i].length;j++)
                arr.add(contacts[i][j]);
            Contact c = new Contact(contacts[i][0],arr,i);
            contactarray.add(c);
        }
 
        HashMap<String,Contact> hm = new HashMap<String,Contact>();
        //ArrayList<ArrayList<Integer>> groups = new ArrayList<ArrayList<Integer>>();
        for (Contact c: contactarray) {
            boolean found = false;
            for (int i = 0;i<c.details.size();i++) {
                String detail = c.details.get(i);
                if(hm.containsKey(detail)) {
                    Contact x = hm.get(detail);
                    c.group = x.group;
                    found = true;
                    break;
                }
            }
            if(!found) {
                for (int i = 0;i<c.details.size();i++) {
                    hm.put(c.details.get(i),c);
                }
            }
 
        }
 
        for (Contact c: contactarray) {
            System.out.println(c.group);
        }
 
 
 
    }
}



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



// UnionFind Java
/*SK has given an overview about the solution, here's the details and code

1.) Convert contact array to list of Contact Object 
2.) Create a map with key all the contact details(email, phone) and value be list of Contact(Person1, person2).
3.) Go through the map and for each contact in the list get the contact details(email, phone) and go over the map recursively and find out what other contact are mapped, while doing so mark contact as visited true.
4.) Add the person to a set.
5.) Add set to result list*/


import java.util.*;

class UnionPerson2 {

    static class Contact {
        List<String> contactList;
        String name;
        boolean visited;
        Contact(List<String> contactList, String name, boolean visited) {
            this.contactList = contactList;
            this.name = name;
            this.visited = visited;
        }
        
    }
    
    public static void main(String a[]) {
        String [][]contacts =  {{"John", "john@gmail.com", "john@fb.com"}, 
                                {"Dan", "dan@gmail.com", "+1234567"}, 
                                {"john123", "5412312", "john123@skype.com"}, 
                                {"john1985", "5412312", "john@fb.com"}, 
                                {"john19856", "john123@skype.com", "john@fb1.com"},
                                {"Dan2", "dan123@gmail.com", "+1234567"},{"Dan3", "dan@gmail.com", "+123456712312"},
                                {"Sandy", "sandy@gmail.com", "+123456712"},{"sandy4", "sandy@fb.com", "sandy@gmail.com"}};
        
        List<Contact> conList = new ArrayList<Contact>();
        for(int i = 0 ; i < contacts.length; i++) {
            List<String> contactList = new ArrayList<String>();
            for(int j = 1 ; j < contacts[i].length; j++) {
                contactList.add(contacts[i][j]);
            }
            Contact con = new Contact(contactList, contacts[i][0], false);
            conList.add(con);
        }
        
        Map<String, List<Contact>> map = new HashMap<String, List<Contact>>();
        for(Contact c: conList) {
            List<Contact> indexList = new ArrayList<Contact>();
            for(String detail: c.contactList) {
                if(map.containsKey(detail)) {
                    indexList = map.get(detail);
                    indexList.add(c);
                    map.put(detail, indexList);
                    
                } else {
                    indexList = new ArrayList<Contact>();
                    indexList.add(c);
                    map.put(detail, indexList);
                    
                }
            }
        }
        List<Set<Contact>> resultList = new ArrayList<Set<Contact>>();
        for(List<Contact> ls: map.values()) {
            Set<Contact> resultSubList = new HashSet<Contact>();
            for(Contact c: ls) {
                if(!c.visited) {
                    resultSubList = findUnion(map, c, resultSubList);
                }
            }
            
            resultList.add(resultSubList);
        }
        
        
        for(Set<Contact> subList: resultList) {
            if(subList.size() > 0) {
                for(Contact co: subList) {
                    System.out.print(co.name + ", ");
                }
                System.out.println();
            }
        }
    }
    
    
    static Set<Contact> findUnion(Map<String, List<Contact>> map, Contact cont, Set<Contact> resultSubList) {
        if(!cont.visited){
            cont.visited = true;
            resultSubList.add(cont);
            for(String contactListStr: cont.contactList) {
                for(Contact c: map.get(contactListStr)){
                    if(!c.visited) {
                        resultSubList.add(c);
                        findUnion(map, c, resultSubList);
                    }
                }
            }
        } 
        return resultSubList;
        
    }
}