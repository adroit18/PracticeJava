# Java Sorting & Comparison: Ultimate FAANG/MAANG Interview Prep

This guide is designed for candidates targeting FAANG/MAANG interviews, covering every aspect of Java sorting and comparison in collections using `Comparable`, `Comparator`, and relevant data structures like `PriorityQueue`, `TreeMap`, and more.

---

## 🧠 Core Interfaces

### `Comparable<T>` (Natural Ordering)

```java
public interface Comparable<T> {
    int compareTo(T o);
}
```

* Defined **within the class**.
* Used by default sorting mechanisms (`Collections.sort()`, `TreeSet`, etc).
* Affects natural order.

### `Comparator<T>` (Custom Ordering)

```java
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```

* Defined **outside the class**.
* Allows multiple sort logics.
* Passed into constructors or sort methods.

---

## ✅ Implementing `Comparable`

```java
class Person implements Comparable<Person> {
    String name;
    int age;

    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age); // Natural order: by age
    }
}
```

Usage:

```java
List<Person> people = new ArrayList<>();
Collections.sort(people); // Uses compareTo()
```

---

## 🔧 Using `Comparator`

```java
Comparator<Person> byName = (p1, p2) -> p1.name.compareTo(p2.name);
```

Or with Java 8+:

```java
Comparator<Person> byName = Comparator.comparing(Person::getName);
Comparator<Person> byAgeDesc = Comparator.comparingInt(Person::getAge).reversed();
Comparator<Person> byNameThenAge = byName.thenComparing(Person::getAge);
```

Usage:

```java
Collections.sort(people, byName);
people.sort(byNameThenAge);
```

---

## 🔢 Sorting in `List<T>`

```java
Collections.sort(list);                // Uses Comparable
Collections.sort(list, comparator);    // Uses Comparator
list.sort(comparator);                 // Java 8+
```

---

## 🌲 TreeSet

```java
TreeSet<Person> treeSet = new TreeSet<>(); // Uses compareTo()
TreeSet<Person> treeSet = new TreeSet<>(byName);
```

* Auto-sorted set.
* No duplicates as per comparator/compareTo logic.

---

## 🌳 TreeMap

```java
TreeMap<String, Integer> map = new TreeMap<>();
TreeMap<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());
```

* Sorted by **key**.
* To sort by **values** (not using streams):

```java
// Assume map is the TreeMap<String, Integer> and we want to sort by value from another map
final Map<String, Integer> valueReference = new HashMap<>();
valueReference.put("A", 30);
valueReference.put("B", 10);
valueReference.put("C", 20);

TreeMap<String, Integer> sorted = new TreeMap<>(new Comparator<String>() {
    public int compare(String k1, String k2) {
        return Integer.compare(valueReference.get(k1), valueReference.get(k2));
    }
});

sorted.putAll(valueReference);
```

---

## 📦 HashMap / LinkedHashMap

```java
Map<String, Integer> hashMap = new HashMap<>();         // Random order
Map<String, Integer> linkedMap = new LinkedHashMap<>(); // Insertion order
```

### 🔁 LinkedHashMap Access Order

```java
LinkedHashMap<String, String> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);

accessOrderMap.put("A", "Apple");
accessOrderMap.put("B", "Banana");
accessOrderMap.put("C", "Cherry");

accessOrderMap.get("A"); // A is now most recently accessed
```

* Pass `true` as the third parameter in the constructor to enable **access-order** instead of insertion-order.
* Useful for **LRU caches**.

---

## 📚 Sorting Map Without Streams (By Values)

Sort entries manually into a list:

```java
List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
        return e1.getValue().compareTo(e2.getValue());
    }
});

LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>();
for (Map.Entry<String, Integer> entry : entries) {
    sorted.put(entry.getKey(), entry.getValue());
}
```

---

## 🎯 PriorityQueue

```java
PriorityQueue<Integer> pq = new PriorityQueue<>(); // Min-heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

PriorityQueue<Person> pq = new PriorityQueue<>(Comparator.comparing(Person::getAge));
```

* Not fully sorted, but always retrieves **smallest/largest** first.
* Head element follows comparator logic.

---

## 🧵 Sorting Nested Structures

### Sort List\<Map.Entry\<K, V>>

```java
List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
Collections.sort(entries, Map.Entry.comparingByValue());
```

### Sort List\<List<Integer>> by sum:

```java
Collections.sort(listOfLists, new Comparator<List<Integer>>() {
    public int compare(List<Integer> l1, List<Integer> l2) {
        int sum1 = 0, sum2 = 0;
        for (int i : l1) sum1 += i;
        for (int i : l2) sum2 += i;
        return Integer.compare(sum1, sum2);
    }
});
```

---

## 🛠️ Utility: Top-K Elements Without Streams

```java
List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
Collections.sort(entries, (e1, e2) -> e2.getValue() - e1.getValue());

LinkedHashMap<String, Integer> topK = new LinkedHashMap<>();
for (int i = 0; i < Math.min(k, entries.size()); i++) {
    topK.put(entries.get(i).getKey(), entries.get(i).getValue());
}
```

---

## 📌 Summary Table

| Collection    | Ordering         | Sorted By             | Notes                            |
| ------------- | ---------------- | --------------------- | -------------------------------- |
| List          | Yes              | Comparator/Comparable | Manual sort required             |
| TreeSet       | Yes              | Comparator/Comparable | Auto-sorted, no duplicates       |
| TreeMap       | Yes              | Key                   | Value sort via custom comparator |
| HashMap       | No               | —                     | Unordered                        |
| LinkedHashMap | Insertion/Access | —                     | Use constructor for access-order |
| PriorityQueue | Heap             | Comparator/Comparable | Not globally sorted, only head   |

---

## 🚀 Interview Tips

* Be fluent with `Comparator.comparing`, `thenComparing`, `reversed()`.
* Know how to sort:

  * List of objects
  * Maps by value or custom criteria
  * Nested lists or maps
* Understand `LinkedHashMap` access-order vs insertion-order.
* Practice with `PriorityQueue` for Top-K, median, etc.
* Always remember: `TreeSet`, `TreeMap`, `PriorityQueue` depend on `Comparable` or `Comparator`.

---

Let me know if you'd like this exported as a downloadable `.md` file or extended with real coding problems from LeetCode/FAANG interviews!
