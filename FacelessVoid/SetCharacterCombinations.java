/*印度小哥，只一道题目，有一个set里有不同的字母{'a', 'b', 'c'}, 有一个整数值K，如果K＝2，输出aa,ab,ac,ba,bb,bc,ca,cb,cc
follow up:组合中不包含相同字符怎么实现
follow up++:不用额外空间怎么做。
*/

public static void main(String[] args) {
                    String[] set = {"a", "b", "c"};
                    System.out.println(getSet(set, 2));
                  }
          
         public static List<String> getSet(String[] set, int k){
                 List<String> result = new ArrayList<String>();
                 result.add(""); 
                 
                 for(int i = 0; i < k; i++){
                         int size = result.size();
                         for(int j = 0; j < size; j++){
                                 String temp = result.get(0);
                                 for(int z = 0; z < set.length; z ++){
                                         result.add(temp + set[z]);
                                 }
                                 result.remove(0);
                         }
                 }
                 
                 return result;
         }