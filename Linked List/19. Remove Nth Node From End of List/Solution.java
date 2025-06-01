/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// class Solution {
//     public ListNode removeNthFromEnd(ListNode head, int n) {
//         ListNode dummy = new ListNode(0);
//         dummy.next = head;
//         ListNode slow = dummy;
//         ListNode fast = dummy;
//         for (int i = 0; i < n; i++) {
//             fast = fast.next;
//         }

//         // 很關鍵，自己忘記寫了
//         while (fast.next != null) {
//             fast = fast.next;
//             slow = slow.next;
//         }
        
//         slow.next = slow.next.next;

//         return dummy.next;
//     }
// }

// class Solution {
//     public ListNode removeNthFromEnd(ListNode head, int n) {
//         ListNode dummy = new ListNode(0);
//         dummy.next = head;
//         ListNode slow = dummy;
//         ListNode fast = dummy;

//         for (int i = 0; i < n; i++) {
//             fast = fast.next;
//         }

//         while (fast.next != null) {
//             fast = fast.next;
//             slow = slow.next;
//         }

//         slow.next = slow.next.next;

//         return dummy.next;
//     }
// }



// 5/31
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
        
    }
}