// BufferReader?

/*Implement a (Java) Iterable object that iterates lines one by one from a text file.*/

** A reference to a file. */
public class TextFile implements Iterable<String>
{
  public TextFile(String fileName) { // please implement this

  /** Begin reading the file, line by line. The returned Iterator.next() will return a line. */
  @Override
  public Iterator<String> iterator() { // please implement this 

}

public class TextFile implements Iterable<String>{
        
        private BufferedReader br;
        private Scanner scanner;
        
        public TextFile(String fileName) throws FileNotFoundException {
                br = new BufferedReader(new FileReader(fileName));
                scanner = new Scanner(new FileReader(fileName));
                
        }
        
        @Override
        public Iterator<String> iterator() {
            return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                try {
                    br.mark(1);
                    if (br.read() < 0) {
                        return false;
                    }
                    br.reset();
                    return true;
                } catch (IOException e) {
                    return false;
                }
            }

            @Override
            public String next() {
                try {
                    return br.readLine();
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
        }

}



public class TextFile implements Iterable<String> {
        ArrayList<String> lines = new ArrayList<String>();
        public TextFile(String fileName) { // please implement this
                Scanner sc = null;
                try {
                        sc = new Scanner(new File("file.txt"));
                } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                while (sc.hasNext()) {
                        lines.add(sc.nextLine());
                }
        }

        /**
         * Begin reading the file, line by line. The returned Iterator.next() will
         * return a line.
         */
        @Override
        public Iterator<String> iterator() { // please implement this. 
                return lines.iterator();
        }

        public static void main(String[] args) {
                TextFile text = new TextFile("file.txt");
                Iterator<String> itr = text.iterator();
                while (itr.hasNext()) {
                        System.err.println(itr.next());
                }
        }
}