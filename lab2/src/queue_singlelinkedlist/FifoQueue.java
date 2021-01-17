package queue_singlelinkedlist;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;

	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param e the element to insert
	 * @return true if it was possible to add the element to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> n = new QueueNode<E>(e);

		if (last == null) {
			last = n;
			last.next = n;
			size++;

		} else {
			n.next = last.next;
			last.next = n;
			last = n;
			size++;

		}
		return true; // alltid möjligt då vi inte har några begränsningar (ex högst x antal element får sättas in...)
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (last == null) {
			return null;
		}
		return last.next.element;
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is empty.
	 * post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() { //vi vill alltså ta bort det första elementet i listan
		if (last == null) {
			return null;
		}
		if (last.next == last) {
			QueueNode<E> temp = last;
			last = null;
			size--;
			return temp.element;

		}
		E temp = last.next.element;
		last.next = last.next.next;
		size--;
		return temp;
	}

	/**
	 * Appends the specified queue to this queue post: all elements from the
	 * specified queue are appended to this queue. The specified queue (q) is empty
	 * after the call.
	 * 
	 * @param q the queue to append
	 * @throws IllegalArgumentException if this queue and q are identical
	 */
	public void append(FifoQueue<E> q) {
		if (this ==q) {
			throw new IllegalArgumentException();
		}
		if(size == 0 && q.size > 0) {
			FifoQueue<E> temp = new FifoQueue<E>();
			last = q.last;
			q.last = temp.last;
			size = q.size;
			q.size = 0;
		}
		
		if(size>0 && q.size() >0) {
			FifoQueue<E> temp = new FifoQueue<E>();
			last.next = q.last.next;
			last = q.last;
			q.last = temp.last;
			size = size + q.size;
			q.size = 0;
		}
		
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<E> {
		private int counter;
		private QueueNode<E> pos;

		/* Konstruktor */
		private QueueIterator() {
			if (size > 0) {
				pos = last.next;
				counter = 0;
			}
		}

		public boolean hasNext() {
			if (size == 0) {
				return false;
			}
			if (counter < size) {
				return true;
			}
			return false;
		}

		public E next() {
			if (counter == 0 && last == null) {
				throw new NoSuchElementException();
			}
			if (counter < size()) {
				counter++;
				QueueNode<E> temp = pos;
				pos = pos.next;
				return temp.element;
			} else
				throw new NoSuchElementException();
			// E e = pos.next.element;
			// pos = pos.next;
			// return e;
		}
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
