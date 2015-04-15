package kae.util;

import java.util.HashMap;

import static kae.util.SimpleLinkedList.Node;

/**
 * FIFOCache
 *
 * @author Kapralov A.
 *         15.04.2015 12:40
 */
public class FIFOCache<K, V> {

  private final int capacity;
  private final HashMap<K, Node<Pair<K, V>>> map;
  private final SimpleLinkedList<Pair<K, V>> list = new SimpleLinkedList<Pair<K, V>>();

  public FIFOCache(int capacity) {
    this.capacity = capacity;
    map = new HashMap<K, Node<Pair<K, V>>>(capacity);
  }

  public int size() {
    return map.size();
  }

  public int getCapacity() {
    return capacity;
  }

  public V get(K key) {
    Node<Pair<K, V>> node = map.get(key);
    if (node != null) {
      return node.value.second;
    }

    return null;
  }

  public void set(K key, V value) {
    Node<Pair<K, V>> node = map.get(key);
    if (node != null) {
      node.value.second = value;
      return;
    }

    if (map.size() == capacity) {
      Node<Pair<K, V>> lastNode = list.removeFirst();
      if (lastNode != null) {
        map.remove(lastNode.value.first);
      }
    }

    add(key, value);
  }

  private void add(K key, V value) {
    Node<Pair<K, V>> node = new Node<Pair<K, V>>(new Pair<K, V>(key, value));
    list.addLast(node);
    map.put(key, node);
  }

}
