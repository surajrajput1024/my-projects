package dsaproblems.greedy;

import java.util.ArrayList;
import java.util.List;

public class NMeetingsInOneRoom {
    static class Item {
        private int start, end, position;

        Item(int start, int end, int pos) {
            this.start = start;
            this.end = end;
            this.position = pos;
        }

        public int getEndTime() {
            return this.end;
        }

        public int getPosition() {
            return this.position;
        }

        public int getStartTime() {
            return this.start;
        }
    }

    public int maxMeetings(int start[], int end[]) {
        int n = start.length;

        List<Item> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            items.add(new Item(start[i], end[i], i + 1));
        }

        items.sort((a, b) -> {
            if (a.getEndTime() == b.getEndTime()) {
                return a.getPosition() - b.getPosition();
            }
            return a.getEndTime() - b.getEndTime();
        });

        int endTime = -1, count = 0;
        for (Item item : items) {
            if (item.getStartTime() > endTime) {
                count++;
                endTime = item.getEndTime();
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int start[] = {1, 3, 0, 5, 8, 5};
        int end[] = {2, 4, 6, 7, 9, 9};
        NMeetingsInOneRoom obj = new NMeetingsInOneRoom();
        System.out.println(obj.maxMeetings(start, end));
    }
}
