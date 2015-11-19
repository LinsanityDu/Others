class Edge{
  int start;
  int end;
  public Edge(int s,int e){ start = s; end = e;}
}
int count(List<Edge> edges){
     HashMap<Edge,Integer> map = new HashMap<Edge,Integer>();
     int count =0;
     for(Edge edge: edges){
          Edge reciEdge = new Edge(edge.end, edge.start);
         if(!map.containsKey(reciEdge)){
                 map.put(edge,1);
          }else{
                 count += 2;
           }
           return count;
}