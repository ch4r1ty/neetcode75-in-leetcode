/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        while (nodeA != null) {
            lenA++;
            nodeA = nodeA.next;
        }
        while (nodeB != null) {
            lenB++;
            nodeB = nodeB.next;
        }
        int dif = 0;
        if (lenA < lenB) {
            nodeA = headB;
            nodeB = headA;
            dif = lenB - lenA;
        } else {
            nodeA = headA;
            nodeB = headB;
            dif = lenA - lenB;
        }
        // 這個判斷條件很關鍵，這個注釋掉的是不能這麽寫的
        while (nodeA != null) {
            if (dif == 0) break; // 要先判斷，再操作（這麽想：dif為0的話，還要不要讓nodeA往後面移一位？顯然不用！）
            nodeA = nodeA.next;
            dif--;
        }
        // 下面這個循環，和上面的其實是一樣的
        // while (dif > 0) {
        //     dif--;
        //     nodeA = nodeA.next;
        // }
        while (nodeA != null) {
            if (nodeA == nodeB) {
                return nodeA;
            }
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }
        return null;
    }
} 