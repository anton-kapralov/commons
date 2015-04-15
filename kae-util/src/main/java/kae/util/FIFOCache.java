package kae.util;

import java.util.HashMap;

/**
 * FIFOCache
 *
 * @author Kapralov A.
 *         15.04.2015 12:40
 */
public class FIFOCache<K, V> {

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


  public FIFOCache(int capacity) {
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
      return node.element.value;
    }

    return null;
  }

  public void set(K key, V value) {
    Node<K, V> node = map.get(key);
    if (node != null) {
      node.element.value = value;
      return;
    }

    if (map.size() == capacity) {
      Node<K, V> lastNode = removeFirst();
      if (lastNode != null) {
        map.remove(lastNode.element.key);
      }
    }

    Node<K, V> newNode = new Node<K, V>(new Element<K, V>(key, value));
    add(newNode);
  }

  private void add(Node<K, V> node) {
    addLast(node);
    map.put(node.element.key, node);
  }

  private void addLast(Node<K, V> newNode) {
    if (head == null) {
      head = newNode;
      tail = head;

      newNode.next = null;
      newNode.previous = null;
    } else {
      Node<K, V> oldTail = tail;
      tail = newNode;

      newNode.previous = oldTail;
      newNode.next = null;
      oldTail.next = newNode;
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

  private Node<K, V> removeFirst() {
    Node<K, V> first = head;
    remove(first);
    return first;
  }

}
