package testqueue;

import static org.junit.Assert.*;

import java.util.Queue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;
import queue_singlelinkedlist.FifoQueue;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import queue_delegate.FifoQueue;
import queue_singlelinkedlist.FifoQueue;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Iterator;

public class TestAppendFifoQueue extends TestCase {
	private FifoQueue<Integer> m1;
	private FifoQueue<String> mS;
	private FifoQueue<Integer> m2;
	private FifoQueue<Integer> m22;
	private FifoQueue<String> sEmpty;

	@Before
	public void setUp() throws Exception {
		m1 = new FifoQueue<Integer>();
		mS = new FifoQueue<String>();
		m2 = new FifoQueue<Integer>();
		sEmpty = new FifoQueue<String>();
		m22 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		m1 = null;
		mS = null;
		m2 = null;
		sEmpty = null;
		m22 = null;
	}

	@Test
	public final void testOne() {
		//try {
		//	m1.append(m2);
		//	fail("Should raise IllegalArgumentException");
		//} catch (IllegalArgumentException e) {
			// successful test
		//}
		
		m1.append(m2);
		
		assertEquals("Listan är inte tom", 0, m1.size());
		assertEquals("Listan är inte tom", 0, m2.size());
		
	}

	@Test
	public final void testTwo() {
		m1.offer(2);
		m1.offer(1);
		
		m2.append(m1);
		//try {
		//	m2.append(m1);
		//	fail("Should raise NullPointerException");
		//}catch(NullPointerException e){
			// successful test
		//}
		
		assertEquals(0, m1.size());
		assertEquals(2, m2.size());
		assertTrue(m2.poll().equals(2));
		assertTrue(m2.poll().equals(1));

	}

	@Test
	public final void testThree() {
		m1.offer(1);
		m1.offer(2);
		m1.append(m2);
		
		assertEquals(2, m1.size());
		assertEquals(0, m2.size());
		assertTrue(m1.poll().equals(1));
		assertTrue(m1.poll().equals(2));
	}

	@Test
	public final void testFour() {
		m1.offer(1);
		m1.offer(1);
		
		m2.offer(2);
		m2.offer(2);
		

		m1.append(m2);
		
		assertEquals(4, m1.size());
		assertEquals(0, m2.size());
	}

	@Test
	public final void testFive() {
		try {
			m1.append(m1);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// successful test
		}
		
		assertEquals(0, m1.size());

	}

}
