package kae.util;

/**
 * SimpleLinkedList
 *
 * @author Kapralov A.
 *         15.04.2015 16:37
 */
public class SimpleLinkedList<T> {

  public static class Node<T> {

    private Node<T> previous;
    private Node<T> next;

    public T value;

    public Node(T value) {
      this.value = value;
    }

  }

  private Node<T> head;
  private Node<T> tail;

  public void addFirst(Node<T> newNode) {
    if (head == null) {
      head = newNode;
      tail = head;

      newNode.next = null;
      newNode.previous = null;
    } else {
      Node<T> oldHead = head;
      head = newNode;

      newNode.next = oldHead;
      newNode.previous = null;
      oldHead.previous = newNode;
    }
  }

  public void setFirst(Node<T> node) {
    if (head != node) {
      if (node == tail) {
        tail = node.previous;
      }

      if (node.next != null) {
        node.next.previous = node.previous;
      }

      if (node.previous != null) {
        node.previous.next = node.next;
      }

      Node<T> oldHead = head;
      head = node;

      node.next = oldHead;
      node.previous = null;
      oldHead.previous = node;
    }
  }

  public void addLast(Node<T> newNode) {
    if (head == null) {
      head = newNode;
      tail = head;

      newNode.next = null;
      newNode.previous = null;
    } else {
      Node<T> oldTail = tail;
      tail = newNode;

      newNode.previous = oldTail;
      newNode.next = null;
      oldTail.next = newNode;
    }
  }

  public void remove(Node<T> node) {
    if (node != null) {
      if (node == tail) {
        tail = node.previous;
      }

      if (node == head) {
        head = node.next;
      }

      if (node.next != null) {
        node.next.previous = node.previous;
      }

      if (node.previous != null) {
        node.previous.next = node.next;
      }
    }
  }

  public Node<T> removeFirst() {
    Node<T> first = head;
    remove(first);
    return first;
  }

  public Node<T> removeLast() {
    Node<T> last = tail;
    remove(last);
    return last;
  }

}
