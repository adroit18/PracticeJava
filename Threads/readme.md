# 🧵 Java Threads & Concurrency Deep Dive (Interview-Ready Guide)

A comprehensive and example-rich guide to understand, explain, and answer interview questions on Java concurrency.

---

## 📊 Table of Contents

1. [Thread Basics](#1-thread-basics)
2. [The `synchronized` Keyword](#2-synchronized-keyword)
3. [`ReentrantLock`](#3-reentrantlock)
4. [`Condition` Objects](#4-condition-object-advanced-synchronization)
5. [`setDaemon()`](#5-setdaemon)
6. [`volatile` Keyword](#6-volatile-visibility-control)
7. [`ThreadLocal`](#7-threadlocal-thread-isolated-variables)
8. [`BlockingQueue` and Producer-Consumer](#8-blockingqueue-and-producer-consumer)
9. [ExecutorService](#9-executorservice-thread-pools)
10. [Callable & Future](#10-callable--future)
11. [Best Practices](#11-best-practices)

---

## 1. Thread Basics

### What is a Thread?

A thread is the smallest unit of processing. Java supports multithreading using the `Thread` class or the `Runnable` interface.

### Ways to Create a Thread:

```java
// 1. Extending Thread
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running");
    }
}

// 2. Implementing Runnable
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable running");
    }
}

// 3. Using Lambda
new Thread(() -> System.out.println("Lambda thread")).start();
```

### Thread Lifecycle:

* **New** → **Runnable** → **Running** → **Terminated** (or **Blocked/Waiting/Sleeping**)

---

## 2. `synchronized` Keyword

### Purpose:

Ensures only one thread executes a critical section at a time. Useful for preventing race conditions.

### Usage:

```java
public synchronized void increment() {
    counter++;
}

public void increment() {
    synchronized(this) {
        counter++;
    }
}
```

### Limitations:

* Can lead to thread starvation.
* No flexibility (e.g., no tryLock, no multiple condition support).

---

## 3. `ReentrantLock`

### What is it?

A more powerful, flexible lock than `synchronized`. It allows:

* Manual lock/unlock
* Interruptible waits
* Multiple `Condition`s

### Example:

```java
ReentrantLock lock = new ReentrantLock();

lock.lock();
try {
    // critical section
} finally {
    lock.unlock();
}
```

### tryLock():

```java
if (lock.tryLock()) {
    try {
        // safe execution
    } finally {
        lock.unlock();
    }
}
```

---

## 4. `Condition` Object (Advanced Synchronization)

### What is it?

Used with `ReentrantLock` to allow threads to wait for specific conditions.
It replaces `wait()` / `notify()` methods in object-level monitors.

### Example:

```java
ReentrantLock lock = new ReentrantLock();
Condition condition = lock.newCondition();

Thread producer = new Thread(() -> {
    lock.lock();
    try {
        while (!dataReady) condition.await(); // releases lock and waits
        System.out.println("Data consumed");
    } catch (InterruptedException e) {}
    finally {
        lock.unlock();
    }
});

Thread consumer = new Thread(() -> {
    lock.lock();
    try {
        dataReady = true;
        condition.signal(); // wakes up one waiting thread
    } finally {
        lock.unlock();
    }
});
```

### Why Use It?

* Wait for a condition instead of a boolean busy loop.
* More fine-tuned than `wait()/notify()`.

---

## 5. `setDaemon()`

### What is a Daemon Thread?

A background thread that does not block JVM termination.

### Example:

```java
Thread t = new Thread(() -> {
    while (true) System.out.println("Daemon Running");
});
t.setDaemon(true); // Must be called before start()
t.start();
```

### Use Case:

* Background cleanup
* Monitoring

---

## 6. `volatile` (Visibility Control)

### What It Does:

Ensures visibility of changes across threads. When a thread modifies a volatile variable, the change is visible to all other threads.

### Example:

```java
private volatile boolean running = true;

public void run() {
    while (running) {
        // do work
    }
}

public void stop() {
    running = false;
}
```

### Important:

* `volatile` is **not** atomic.
* Use for flags, not for counters or compound actions.

---

## 7. `ThreadLocal`: Thread-Isolated Variables

### What is it?

Each thread gets its own independent copy of a variable.

### Example:

```java
ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> 0);

threadId.set(5);
System.out.println(threadId.get()); // prints 5
```

### Use Case:

* Per-request data (e.g., request ID)
* No need for synchronization

---

## 8. `BlockingQueue` and Producer-Consumer

### What is it?

A thread-safe queue that blocks when empty/full.

### Example: Producer-Consumer

```java
BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

Runnable producer = () -> {
    try {
        queue.put(1); // waits if full
    } catch (InterruptedException e) {}
};

Runnable consumer = () -> {
    try {
        int item = queue.take(); // waits if empty
    } catch (InterruptedException e) {}
};
```

### Benefits:

* No need for manual `wait()`/`notify()`
* Clean and efficient concurrency pattern

---

## 9. `ExecutorService` (Thread Pools)

### What is it?

A framework to manage and reuse thread pools.

### Example:

```java
ExecutorService service = Executors.newFixedThreadPool(3);

service.submit(() -> doWork());
service.shutdown();
```

### Types:

* `newFixedThreadPool(int n)`
* `newSingleThreadExecutor()`
* `newCachedThreadPool()`

### When to Use:

* Better control over threads
* Prevent resource exhaustion

---

## 10. Callable & Future

### What is it?

Allows threads to return values and throw exceptions.

### Example:

```java
ExecutorService executor = Executors.newSingleThreadExecutor();
Callable<Integer> task = () -> 42;

Future<Integer> future = executor.submit(task);
Integer result = future.get(); // blocks until done
executor.shutdown();
```

---

## 11. Best Practices

| DO                                           | DON'T                                     |
| -------------------------------------------- | ----------------------------------------- |
| Use `ExecutorService` instead of raw threads | Overuse `Thread.sleep()` for coordination |
| Use `BlockingQueue` for producer-consumer    | Share non-thread-safe collections         |
| Always unlock in `finally`                   | Forget to shutdown executors              |
| Use `volatile` only for flags                | Use `synchronized` with long tasks        |
| Profile for deadlocks                        | Ignore race conditions                    |

---

## ✨ Bonus Interview Q\&A

### Q: Difference between `synchronized` and `ReentrantLock`?

A:

* `synchronized` is simpler, but less flexible.
* `ReentrantLock` allows timeouts, `tryLock`, multiple `Condition`s.

### Q: When would you use `ThreadLocal`?

A:

* When you want to avoid passing context manually across layers (e.g., request tracking ID).

### Q: Why not always use `volatile` instead of `synchronized`?

ExecutorService executor = Executors.newFixedThreadPool(4); // 4 threads

// Other types
Executors.newSingleThreadExecutor(); // 1 thread
Executors.newCachedThreadPool();     // Unlimited threads, reused
Executors.newScheduledThreadPool(2); // For delayed tasks

Submitting Runnable (no return)

📘
executor.submit(() -> System.out.println("Running in thread: " + Thread.currentThread().getName()));


Submitting Callable (returns a value)

Future<Integer> future = executor.submit(() -> {
    Thread.sleep(1000);
    return 42;
});

System.out.println("Result: " + future.get()); // blocks until result is ready

⚠️ Always Shutdown the Executor
executor.shutdown(); // initiate shutdown
// OR
executor.shutdownNow(); // forcefully shutdown

💼 Practical Use Case: Batch Image Processing
List<String> imagePaths = List.of("img1.jpg", "img2.jpg", "img3.jpg");
ExecutorService executor = Executors.newFixedThreadPool(3);

for (String path : imagePaths) {
    executor.submit(() -> {
        processImage(path);
    });
}

executor.shutdown();

🧠 Advanced: invokeAll and invokeAny
List<Callable<String>> tasks = List.of(
    () -> "Task1",
    () -> "Task2",
    () -> "Task3"
);

// Waits for all to finish
List<Future<String>> results = executor.invokeAll(tasks);

// Returns first successful result
String first = executor.invokeAny(tasks);