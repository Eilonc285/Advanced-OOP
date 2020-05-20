import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class QueueAsList implements Queue<Object> {
	private LinkedList<Object> lst;

	public QueueAsList() {
		lst = new LinkedList<Object>();
	}

	public boolean isEmpty() {
		return lst.size() == 0;
	}

	public Object dequeue() throws NoSuchElementException {
		if (lst.size() == 0) {
			throw new NoSuchElementException("Queue is empty");
		}
		return lst.remove(0);
	}

	public void enqueue(Object o) {
		lst.addLast(o);
	}

	@Override
	public boolean addAll(Collection<?> c) {
		lst.addAll(c);
		return true;
	}

	@Override
	public void clear() {
		lst.clear();
	}

	@Override
	public boolean contains(Object o) {
		return lst.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return lst.containsAll(c);
	}

	@Override
	public Iterator<Object> iterator() {
		return lst.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return lst.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return lst.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return lst.retainAll(c);
	}

	@Override
	public int size() {
		return lst.size();
	}

	@Override
	public Object[] toArray() {
		return lst.toArray();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray(Object[] a) {
		return lst.toArray(a);
	}

	@Override
	public boolean add(Object arg0) {
		lst.add(arg0);
		return true;
	}

	@Override
	public Object element() {
		if (lst.size() == 0) {
			throw new NoSuchElementException("Queue is empty");
		}
		return lst.get(0);
	}

	@Override
	public boolean offer(Object arg0) {
		return false;
	}

	@Override
	public Object peek() {
		if (lst.size() == 0) {
			return null;
		}
		return lst.get(0);
	}

	@Override
	public Object poll() {
		if (lst.size() == 0) {
			return null;
		}
		return lst.remove(0);
	}

	@Override
	public Object remove() {
		if (lst.size() == 0) {
			throw new NoSuchElementException("Queue is empty");
		}
		return lst.remove(0);
	}
}
