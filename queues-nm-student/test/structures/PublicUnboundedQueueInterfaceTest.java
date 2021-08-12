package structures;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class PublicUnboundedQueueInterfaceTest {
  UnboundedQueueInterface<Integer> queue;

  @Test
  public void testCopyConstructorEmpty() throws Exception  {
    Queue<Integer> q = new Queue<Integer>();
    UnboundedQueueInterface<Integer> r;
    r = new Queue<Integer>(q);
    assertTrue(r.isEmpty());
    assertTrue(q.isEmpty());
  }

  @Test
  public void testCopyConstructorEmptyNotAliased() throws Exception  {
    Queue<Integer> q = new Queue<Integer>();
    UnboundedQueueInterface<Integer> r;
    r = new Queue<Integer>(q);
    assertTrue(r.isEmpty());
    assertTrue(q.isEmpty());

    q.enqueue(1);
    q.enqueue(2);
    assertEquals(2, q.size());
    assertTrue(r.isEmpty());

    r.enqueue(3);
    r.enqueue(4);
    r.enqueue(5);
    assertEquals(2, q.size());
    assertEquals(3, r.size());

    r.dequeue();
    r.dequeue();
    r.dequeue();
    assertTrue(r.isEmpty());
    assertEquals(2, q.size());

    q.dequeue();
    q.dequeue();
    assertTrue(q.isEmpty());
  }

  @Test
  public void testCopyConstructorOneElement() throws Exception  {
    Queue<Integer> q = new Queue<Integer>();
    UnboundedQueueInterface<Integer> r;
    q.enqueue(1);
    r = new Queue<Integer>(q);

    assertEquals(1, q.size());
    assertEquals(1, r.size());
  }

  @Test
  public void testCopyConstructorOneElementNotAliased() throws Exception  {
    Queue<Integer> q = new Queue<Integer>();
    UnboundedQueueInterface<Integer> r;
    q.enqueue(1);
    r = new Queue<Integer>(q);

    q.enqueue(2);
    assertEquals(1, (int)r.dequeue());
    assertTrue(r.isEmpty());
    assertEquals(2, q.size());
  }

  @Test
  public void testCopyConstructorTwoElements() throws Exception  {
    Queue<Integer> q = new Queue<Integer>();
    UnboundedQueueInterface<Integer> r;
    q.enqueue(1);
    q.enqueue(2);
    r = new Queue<Integer>(q);

    assertEquals(2, q.size());
    assertEquals(2, r.size());
  }

  @Test
  public void testCopyConstructorTwoElementsNotAliased() throws Exception  {
    Queue<Integer> q = new Queue<Integer>();
    UnboundedQueueInterface<Integer> r;
    q.enqueue(1);
    q.enqueue(2);
    r = new Queue<Integer>(q);

    q.enqueue(3);
    assertEquals(1, (int)r.dequeue());
    assertEquals(3, q.size());
    assertEquals(1, r.size());
    r.enqueue(3);
    r.enqueue(4);
    r.enqueue(5);
    r = r.reversed();
    assertEquals(5,(int)r.dequeue());
    assertEquals(4,(int)r.dequeue());
    assertEquals(3,(int)r.dequeue());
    assertEquals(2,(int)r.dequeue());
  }

}
