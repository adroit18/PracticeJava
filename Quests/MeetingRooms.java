import java.util.*;
class MeetingRooms
{
    public static void main(String args[])
    {
        int[][] meetingsCalendar = {{0,30},{5,10},{15,20}};
        System.out.println(determine(meetingsCalendar));
    }

    private static boolean determine(int[][] meetingsCalendar)
    {
        Arrays.sort(meetingsCalendar, (a, b) -> Integer.compare(a[0], b[0]));
        Stack<int[]> stack = new Stack<>();

        for(int i = 0; i<meetingsCalendar.length; i+=1){
            
            if(stack.size() == 0){
                stack.push(meetingsCalendar[i]);
                continue;
            }

            int[] lastMeet = stack.peek();
            if(lastMeet[1] > meetingsCalendar[i][0]){
                return false;
            }

        }
        return true;
    }
}