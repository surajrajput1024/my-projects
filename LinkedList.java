import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class LinkedList {

    @SuppressWarnings("unused")
    private static ListNode deleteMiddleNode(ListNode head) {
        ListNode slow = head, fast = head;

        if(head.next == null) {
            return null;
        }

        while (fast.next != null && fast.next.next != null && fast.next.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode temp = slow.next;
        slow.next = slow.next.next;
        temp.next = null;
        temp = null;

        return head;
    }

    @SuppressWarnings("unused")
    private static ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }

    public static int twinSum(ListNode head) {
        int sum = 0;

        // Find middle of the linked list
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHalf = reverseList(slow);

        ListNode firstHalf = head;
        while (secondHalf != null) {
            sum = Math.max(sum, firstHalf.val + secondHalf.val);
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return sum;
    }

    @SuppressWarnings("unused")
    private static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList(head.next); 
        head.next.next = head; 
        head.next = null;

        return newHead; 
    }
    

    public static ListNode reverseRec(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode current = head, prev = null, next = head.next;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) return true;
    
            ListNode slow = head, fast = head;
    
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
    
            ListNode secondHalf = reverseList(slow);
            ListNode firstHalf = head;
            ListNode reversedSecondHalf = secondHalf; 
    
            while (secondHalf != null) {  
                if (firstHalf.val != secondHalf.val) {
                    return false;
                }
                firstHalf = firstHalf.next;
                secondHalf = secondHalf.next;
            }
    
            reverseList(reversedSecondHalf);
    
            return true;
    }

    public ListNode addOne(ListNode head) {
        ListNode reversed = reverseList(head);
        ListNode secondList = new ListNode(1);

        
        ListNode ans = addTwoList(reversed, secondList, 0);
        

        return reverseList(ans);
    }

    private static ListNode addTwoList(ListNode l1, ListNode l2, int carry) {
       if(l1 == null && l2 == null && carry == 0) {
        return null;
       }

       int sum = carry;

       if(l1 != null) {
        sum += l1.val;
        l1 = l1.next;
       }

       if(l2 != null) {
        sum += l2.val;
        l2 = l2.next;
       }

       ListNode newNode = new ListNode(sum % 10);
       newNode.next = addTwoList(l1, l2, sum / 10);

       return newNode;
    }

//    private static ListNode constructLL(int[] nums) {
//        int n = nums.length;
//        if(n == 0) {
//            return null;
//        }
//
//
//    }

    public static void main(String[] args) {
        // Test Case 1: Regular case [5,4,2,1]
        ListNode head1 = new ListNode(5);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(2);
        head1.next.next.next = new ListNode(1);
        System.out.println("Test Case 1 - Expected: 6, Actual: " + twinSum(head1)); // 5+1=6, 4+2=6

        // Test Case 2: Minimum length [1,2]
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        System.out.println("Test Case 2 - Expected: 3, Actual: " + twinSum(head2)); // 1+2=3

        // Test Case 3: All same values [1,1,1,1]
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(1);
        head3.next.next = new ListNode(1);
        head3.next.next.next = new ListNode(1);
        System.out.println("Test Case 3 - Expected: 2, Actual: " + twinSum(head3)); // 1+1=2

        // Test Case 4: Large values [100,1,100,1]
        ListNode head4 = new ListNode(100);
        head4.next = new ListNode(1);
        head4.next.next = new ListNode(100);
        head4.next.next.next = new ListNode(1);
        System.out.println("Test Case 4 - Expected: 101, Actual: " + twinSum(head4)); // 100+1=101
    }
}
