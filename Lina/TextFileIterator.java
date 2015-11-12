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
                //scanner = new Scanner(new FileReader(fileName));
                
        }

The java.io.BufferedReader.mark(int) method marks the current position in the stream. Invoking reset() will reposition the stream to this point.
public void mark(int readAheadLimit)
readAheadLimit -- number of characters to be read while preserving the mark.

The java.io.BufferedReader.read() method reads a single character from this buffered reader.
The method returns a character as an integer. If the end of the stream has been reached the method returns -1.

The java.io.BufferedReader.reset() method resets the stream to the most recent mark.

        
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


//
Implement a (Java) Iterable object that iterates lines one by one from a text file..

/** A reference to a file. */
public class TextFile implements Iterable<String>. From 1point 3acres bbs
{
  public TextFile(String fileName) { // please implement this

  /** Begin reading the file, line by line. The returned Iterator.next() will return a line. */
  @Override
  public Iterator<String> iterator() { // please implement this
ANSWER: An implementation of this using bufferedReader:

public class TextFile implements Iterable<String>{

        private BufferedReader br;

        public TextFile(String fileName) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(fileName));
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
From Here

//Another implementation using scanner
public class TextFile implements Iterable<String> {
 public TextFile(String fileName) { // please implement this }
 // Begin reading the file, line by line. The returned Iterator.next() will
 // return a line.
 @Override
 public Iterator<String> iterator() { // please implement this }
}

public class TextFile implements Iterable<String> {

 Scanner scanner;

 public TextFile(String fileName) {
   try {
     scanner = new Scanner(new File(fileName));
   } catch(Exception e) { }
 }

 @Override
 public Iterator<String> iterator() {
   return new Iterator<String>() {
     @Override
     public boolean hasNext() {
       return scanner.hasNext();
     }
     @Override
     public String next() {
       if (hasNext()) {
         return scanner.nextLine();
       } else {
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