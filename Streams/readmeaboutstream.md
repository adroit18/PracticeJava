List<String> list = List.of("a", "b", "c");

Stream<String> stream = list.stream();           // sequential stream
Stream<String> parallel = list.parallelStream(); // parallel stream


🔍 2. Intermediate Operations (lazy)
Operation	Description	Example
filter(Predicate)	Keeps elements that match	filter(s -> s.length() > 3)
map(Function)	Transforms each element	map(String::toUpperCase)
flatMap(Function)	Flattens nested streams	flatMap(List::stream)
distinct()	Removes duplicates	distinct()
sorted()	Sorts elements	sorted()
sorted(Comparator)	Custom sort	sorted(Comparator.reverseOrder())
limit(n)	Take first n elements	limit(5)
skip(n)	Skip first n elements	skip(2)
peek(Consumer)	For debugging/logging	peek(System.out::println)

🎯 3. Terminal Operations (triggers the stream)
Operation	Description	Example
collect()	Gather results	collect(Collectors.toList())
forEach()	Perform action on each	forEach(System.out::println)
toArray()	Convert to array	toArray(String[]::new)
reduce()	Aggregate into one value	reduce(0, Integer::sum)
count()	Count elements	count()
anyMatch()	At least one matches	anyMatch(s -> s.startsWith("A"))
allMatch()	All match condition	allMatch(s -> s.length() > 0)
noneMatch()	None match condition	noneMatch(String::isEmpty)
findFirst()	First element (Optional)	findFirst().orElse("None")
findAny()	Any element (Optional)	findAny().orElse("None")

📦 4. Collectors
Collector	Description	Example
toList()	Collect to List	collect(Collectors.toList())
toSet()	Collect to Set	collect(Collectors.toSet())
joining()	Join strings	collect(Collectors.joining(", "))
groupingBy()	Group by property	groupingBy(String::length)
partitioningBy()	Split into true/false groups	partitioningBy(s -> s.length() > 3)
counting()	Count per group	groupingBy(s -> s, counting())
summarizingInt()	Count, sum, min, avg, max	summarizingInt(String::length)

🔁 5. Primitive Streams
Type	How to get	Example
IntStream	From int[] or range	IntStream.range(1, 5)
LongStream	From long[] or range	LongStream.of(10L, 20L)
DoubleStream	From double[]	DoubleStream.of(1.1, 2.2)

✅ Convert boxed stream to primitive:

java
Copy
Edit
list.stream().mapToInt(Integer::intValue)
🧪 6. Debugging Streams
java
Copy
Edit
list.stream()
    .peek(s -> System.out.println("Filtered: " + s))
    .filter(s -> s.length() > 3)
    .peek(s -> System.out.println("Mapped: " + s))
    .map(String::toUpperCase)
    .forEach(System.out::println);
