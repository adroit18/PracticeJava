import java.util.*;
class MeetingRooms2
{
    public static void main(String args[])
    {
        int[][] meetingsCalendar = {{0,30},{5,10},{15,20}};
        System.out.println(determine(meetingsCalendar));
        System.out.println(pq(meetingsCalendar));
    }

    private static int determine(int[][] meetingsCalendar)
    {
        int[] starts = new int[meetingsCalendar.length];
        int[] ends = new int[meetingsCalendar.length];

        for(int i = 0; i<meetingsCalendar.length; i+=1){
            starts[i] = meetingsCalendar[i][0];
            ends[i] = meetingsCalendar[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        int count = 0;
        int i = 0; int j = 0;

        while(i < starts.length && j < ends.length)
        {
            if(starts[i] < ends[j]){
                count++;
            }else{
                 j++;
            }
            i++;
        }

        return count;
    }

    private static int pq(int[][] meetingsCalendar){
        if(meetingsCalendar.length == 0){
            return 0;
        }
        Arrays.sort(meetingsCalendar, (a,b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(meetingsCalendar[0][1]);

        for(int i = 1; i<meetingsCalendar.length; i++){

            int endTimeLastMeeting = minHeap.peek();

            if(meetingsCalendar[i][0] > endTimeLastMeeting){
                minHeap.poll();
            }
            minHeap.add(meetingsCalendar[i][1]);
        }

        return minHeap.size();
    }
}