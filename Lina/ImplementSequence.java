/*implement an interface (Given a sequence class  implement a sequence )*/

interface Sequence{
	int size();
	Object get(int idx);
	void add(Object o);
	boolean remove(int idx);
}



public class SequenceImp implements Sequence {
	private Object[] data;
	private int count = 0;
	private int FIXED_SIZE = 10;

	public SequenceImp() {
		data = new Object[this.FIXED_SIZE];
	}

	public Object get(int index) {
		if (index < count) {
			return data[index];
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	public void add(Object obj) {
		if (data.length - count <= data.length / 2) {
			this.resizeArray();
		}
		data[count++] = obj;
	}

	public void resizeArray() {
		data =  Arrays.copyOf(data, data.length * 2);
	}

	public int size() {
		return count;
	}

	public boolean remove(int index) {
		if (index < count) {
			Object obj = data[index];
			int temp = index;
			data[index] = null;

			while (temp < count) {
				data[temp] = data[temp + 1];
				data[temp + 1] = null;
				temp++;
			}
			count--;
			return true;

		} else {
			return false;
		}
	}


} 