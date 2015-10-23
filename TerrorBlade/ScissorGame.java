Map<String, int> map = new HashMap<String, int>();
map.put("rock", 1);
map.put("paper", 2);
map.put("scissors", 3);

int user = map.get(userLower);
int computer = map.get(comp) % 3;

if(user %3 == computer) {
    System.out.println("It's a tie!");
} else if(user == computer-1) {
    System.out.println("You win!");
} else {
    System.out.println("You lose!");
}