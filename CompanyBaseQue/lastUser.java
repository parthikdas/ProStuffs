package CompanyBaseQue;
import java.util.*;

/*
    Given a sequence of user logins, find the users who logged only once.
    Lets say we are working on a shopping website and we want to analyze non-returning users to offer them some promotions to get their attention again.
    We know that every user has their own unique usernames, so we can track whether they are visiting the website again or not.
    For this task we need to implement below methods:
        newUserLogin(username) : This method will be called everytime a user logs in
        getOldestOneTimeVisitingUser() : This method will return the username of the oldest customer who has visited the website once

    Example:
        newUserLogin(john)
        newUserLogin(jeff)
        newUserLogin(jeff)
        getOldestOneTimeVisitingUser(); // should return john


        // Showing 1st approach dry run
        newUserLogin(john) // lc = {john : 1}, q = [john]
        newUserLogin(adam) // lc = {john : 1, adam : 1}, q = [john, adam]
        newUserLogin(adam) // lc = {john : 1, adam : 2}, q = [john]
        newUserLogin(john) // lc = {john : 2, adam : 2}, q = []
        newUserLogin(sandy) // lc = {john : 2, adam : 2, sandy : 1}, q = [sandy]
        getOldestOneTimeVisitingUser(); // should return sandy

        // Showing 1st approach dry run
        newUserLogin(john) // lc = {john : 1}, q = [john]
        newUserLogin(jeff) // lc = {john : 1, jeff : 1}, q = [john, jeff]
        newUserLogin(jeff) // lc = {john : 1, jeff : 2}, q = [john]
        getOldestOneTimeVisitingUser(); // should return john
        newUserLogin(john) // lc = {john : 2, jeff : 2}, q = []
        newUserLogin(parthik) // lc = {john : 2, jeff : 2, parthik : 1}, q = [parthik]
        getOldestOneTimeVisitingUser(); // should return parthik

        

    Approach : 
        1. using a hashmap and a queue // Time - O(n), Space - O(n)
        2. using a linkedHashMap // Time - O(n), Space - O(n)
        3. using 2 hashmap one stores the unique users and another stores the old users.
            first we store in unique users when its repeated we push to old and dlt from here

    Pseudo for approach 1:
        Initialize:
            loginCount = empty map
            oneTimeVisitors = empty queue
        Function newUserLogin(username):
            If username not in loginCount:
                set loginCount[username] = 1
                add username to oneTimeVisitors
            Else:
                increment loginCount[username] by 1
                if loginCount[username] == 2:
                    Remove username from oneTimeVisitors
        Function getOldestOneTimeVisitingUser():
            If oneTimeVisitors is not empty:
                return first element
            else:
                return null
 */
public class lastUser {
    // Approach 1
    class UserLoginTracker {
        private Map<String, Integer> loginCount = new HashMap<>(); // Space - O(n)
        private Queue<String> oneTimeVisitors = new LinkedList<>(); // Space - O(n)

        public void newUserLogin(String username) { // Time - O(n)
            loginCount.put(username, loginCount.getOrDefault(username, 0) + 1);
            if(loginCount.get(username) == 1) {
                oneTimeVisitors.offer(username);
            } else {
                oneTimeVisitors.remove(username); // just because of this as we may traverse till last
            }
        }

        public String getOldestOneTimeVisitingUser() {
            if(!oneTimeVisitors.isEmpty()) {
                return oneTimeVisitors.iterator().next(); // We should use ConcurrentHashMap as normal hashmap iterator is not thread safe <- bonus point in interview
            }
            return null;
        }
    }

    // Approach 2
    class UserLoginTracker1 { // O(n), O(n)
        private LinkedHashMap<String, Integer> visitor = new LinkedHashMap<>();

        public void newUserLogin(String userName) { // Time - O(1)
            if(userName == null || userName.isEmpty()) return;
            visitor.put(userName, visitor.getOrDefault(userName, 0) + 1);
        }

        public String getOldestOneTimeVisitingUser() { // Time - O(n)
            for(Map.Entry<String, Integer> entry : visitor.entrySet()) {
                if(entry.getValue() == 1) {
                    return entry.getKey();
                }
            }
            return null;
        }
    }

}
// Approach 3
class UserLoginTracker2 { // O(1), O(n)
    private Map<String, Boolean> uniqueUser; // Boolean as we are dealing only once
    private Set<String> oldUser;
    public UserLoginTracker2() {
        uniqueUser = new LinkedHashMap<>(); // we only care about who came first so no using accesmode true
        oldUser = new HashSet<>(); // Here we store users having more than 1 freq and here we dont need order and linked is than normal
    }
    public void updateUserVisit(String userName) {
        if(oldUser.contains(userName))
            uniqueUser.remove(userName); // If repeated, remove from unique
        else {
            oldUser.add(userName); // Mark as seen
            uniqueUser.put(userName, true); // Add to unique
        }
    }
    public String getFirstUniqueUser() {
    // Get the first user in the LinkedHashMap
        if (!uniqueUser.isEmpty()) {
            return uniqueUser.entrySet().iterator().next().getKey();
        }
        return null; // If no users have visited
    }
    public static void main(String[] args) {
        // Assuming LinkedHashMap maintains insertion order and .entrySet().iterator().next() is O(1)
        Scanner scanner = new Scanner(System.in);
        int usersCount = Integer.parseInt(scanner.nextLine());
        UserLoginTracker2 tracker = new UserLoginTracker2();
        for(int i = 0; i < usersCount; i++) {
            String userName = scanner.nextLine();
            tracker.updateUserVisit(userName);
        }
        String firstUser = tracker.getFirstUniqueUser();
        if (firstUser != null) {
            System.out.println("First unique user: " + firstUser);
        } else {
            System.out.println("No unique users.");
        }
        scanner.close();
    }
}
