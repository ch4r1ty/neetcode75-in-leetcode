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

class Solution {
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        // 找到鏈表的中點
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 斷開鏈表
        ListNode second = slow.next; // 保存斷開之前，slow的.next
        // ListNode prev = slow.next = null;
        slow.next = null; // 斷開鏈表
        ListNode prev = null; // 初始化 prev , 用於反轉鏈表

        // 反轉第二部分
        while (second != null) {
            ListNode tmp = second.next;
            second.next = prev;
            prev = second;
            second = tmp;
        }

        // 合併兩部分鏈表
        ListNode first = head;
        // 在前面反轉鏈表的步驟中，prev 最終指向了反轉後的第二部分鏈表的頭節點（反轉過了）
        second = prev;
        while (second != null) {    // 如果長度是奇數，那麽第二部分更短
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;
            first.next = second;
            second.next = tmp1;
            first = tmp1;
            second = tmp2;
        }
    }
}