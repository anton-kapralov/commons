package kae.util;

import java.util.HashMap;

/**
 * LRUCache
 *
 * @author Kapralov A.
 *         14.04.2015 16:45
 */
public class LRUCache<K, V> {

  private static class Element<K, V> {
    public final K key;
    public V value;

    private Element(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  private static class Node<K, V> {
    public Node<K, V> previous;
    public Node<K, V> next;
    public Element<K, V> element;

    private Node(Element<K, V> element) {
      this.element = element;
    }

  }

  private final int capacity;
  private final HashMap<K, Node<K, V>> map;

  private Node<K, V> head;
  private Node<K, V> tail;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    map = new HashMap<K, Node<K, V>>(capacity);
  }

  public int size() {
    return map.size();
  }

  public int getCapacity() {
    return capacity;
  }

  public V get(K key) {
    Node<K, V> node = map.get(key);
    if (node != null) {
      final Element<K, V> element = node.element;
      setFirst(node);
      return element.value;
    }

    return null;
  }

  public void set(K key, V value) {
    Node<K, V> node = map.get(key);
    if (node != null) {
      final Element element = node.element;
      setFirst(node);
      element.value = value;
      return;
    }

    if (map.size() == capacity) {
      Node<K, V> lastNode = removeLast();
      if (lastNode != null) {
        map.remove(lastNode.element.key);
      }
    }

    Node<K, V> newNode = new Node<K, V>(new Element<K, V>(key, value));
    add(newNode);
  }

  private void add(Node<K, V> node) {
    addFirst(node);
    map.put(node.element.key, node);
  }

  private void setFirst(Node<K, V> node) {
    if (head != node) {
      remove(node);
      add(node);
    }
  }

  private void addFirst(Node<K, V> newNode) {
    if (head == null) {
      head = newNode;
      tail = head;

      newNode.next = null;
      newNode.previous = null;
    } else {
      Node<K, V> oldHead = head;
      head = newNode;

      newNode.next = oldHead;
      newNode.previous = null;
      oldHead.previous = newNode;
    }
  }

  private void remove(Node<K, V> node) {
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

  private Node<K, V> removeLast() {
    Node<K, V> last = tail;
    remove(last);
    return last;
  }

}
