import java.util.*;
import java.util.stream.*;

class Main
{
    public static void main(String args[])
    {
        List<User> users = List.of(
                            new User("Alice", 28, "alice@example.com", List.of("user", "admin")),
                            new User("Bob", 17, "bob@example.com", List.of("user")),
                            new User("Charlie", 35, "charlie@example.com", List.of("user")),
                            new User("Diana", 52, "diana@example.com", List.of("admin")),
                            new User("Eve", 22, "eve@example.com", List.of("user", "moderator"))
                        );
        ManageUsers mngUsers = new ManageUsers(users);
        mngUsers.printUserList(mngUsers.filterAdults(users));
        System.out.println(mngUsers.getUserNameAndEmails(users));
        mngUsers.printUserList(mngUsers.sortByNames(users));
        // System.out.println(mngUsers.groupByAges(users));
        // System.out.println(mngUsers.partitionByRoles(users));
        // System.out.println(mngUsers.groupByAgesCount(users));
        // System.out.println(mngUsers.getStats(users));
        // System.out.println(mngUsers.getRandomUserWithNameA(users));
        // System.out.println(mngUsers.getUserNames(users));
    }
}


class User
{
    String name;
    int age;
    String email;
    List<String> roles;

    public User(String name, int age, String email, List<String> roles)
    {
        this.name = name;
        this.age = age;
        this.email = email;
        this.roles = roles;
    }
    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email + "', roles=" + roles + "}";
    }
}


class ManageUsers
{
    List<User> usersPresent;
    
    public ManageUsers(List<User> usersPresent)
    {
        this.usersPresent = usersPresent;
    }
    // Filter users who are 18 or older.
    public List<User> filterAdults(List<User> usersPresent)
    {
        return usersPresent.stream().filter(user -> user.age > 18).collect(Collectors.toList());
    }
    // Map to a new string format: NAME (EMAIL) in uppercase.
    public List<String> getUserNameAndEmails(List<User> usersPresent)
    {
        return usersPresent.stream().map(user -> user.name + "(" + user.email + ")").collect(Collectors.toList());
    }
    // sort the names alphabetically.
    public List<User> sortByNames(List<User> usersPresent)
    {
        return usersPresent.stream().sorted(Comparator.comparing(user -> user.name)).collect(Collectors.toList());
    }
    // Group users by age bracket
    // < 25: "Young" 25–50: "Adult" > 50: "Senior"
    public Map<String, List<User>> groupByAges(List<User> usersPresent)
    {
        return usersPresent.stream()
            .collect(Collectors.groupingBy(user -> {
                if(user.age < 25) return "Young";
                else if(user.age > 25 && user.age < 50) return "Adult";
                else  return "Senior";
            }));
    }
    // Partition users into: Admins (roles contains "admin") Non-admins
    public Map<Boolean, List<User>> partitionByRoles(List<User> usersPresent) {
        return usersPresent.stream()
            .collect(Collectors.partitioningBy(user -> user.roles.contains("admin")));
    }
    // Count how many users are in each age bracket.
    public Map<String, Long> groupByAgesCount(List<User> usersPresent)
    {
        return usersPresent.stream()
            .collect(Collectors.groupingBy(user -> {
                if(user.age < 25) return "Young";
                else if(user.age > 25 && user.age < 50) return "Adult";
                else  return "Senior";
            }, Collectors.counting()));
    }
    // Get stats on ages: average, min, max, count.
    public IntSummaryStatistics getStats(List<User> usersPresent)
    {
        return usersPresent.stream().collect(Collectors.summarizingInt(user -> user.age));
    }
    // Find any user whose name starts with "A" and print it (if any).
    public Optional<User> getRandomUserWithNameA(List<User> usersPresent)
    {
        return usersPresent.stream().filter(user -> user.name.startsWith("a")).findAny();
    }
    // collect final usernames into a comma-separated string.
    public String getUserNames(List<User> usersPresent)
    {
        return usersPresent.stream().map(user -> user.name).collect(Collectors.joining(","));
    }

    public void printUserList(List<User> usersPresent) {
        for (User user : usersPresent) {
            System.out.println(user);
        }
    }
}

// You need to process this list using Streams to:
// ✅ Objectives:
// Filter users who are 18 or older.

// Map to a new string format: NAME (EMAIL) in uppercase.

// Sort the names alphabetically.

// Group users by age bracket:

// < 25: "Young"

// 25–50: "Adult"

// > 50: "Senior"

// Partition users into:

// Admins (roles contains "admin")

// Non-admins

// Count how many users are in each age bracket.

// Flatten all roles across users into a single Set (i.e., deduplicate).

// Get stats on ages: average, min, max, count.

// Find any user whose name starts with "A" and print it (if any).

// Collect final usernames into a comma-separated string.

