class MyLinkedList {

    private int size;
    private ListNode dummy;

    public MyLinkedList() {
        size = 0;
        dummy = new ListNode(0);
    }
    
    public int get(int index) {
        if (index < 0 || index >= size) {  // 這裏是不能只 > 的，get要 >=
            return -1;
        }
        ListNode node = dummy;
        for (int i = index; i >= 0; i--) {
            node = node.next;
        }

        return node.val;
    }
    
    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = dummy.next;
        dummy.next = newNode;
        size++;
    }
    
    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        ListNode temp = dummy;
        
        while (temp.next != null) {  // 簡化條件判斷，因爲 dummy 永遠不會為 null
            temp = temp.next;
        }
        temp.next = newNode;
        size++;
    }
    
    public void addAtIndex(int index, int val) {
        if (index > size || index < 0) {
            return;
        }

        ListNode newNode = new ListNode(val);
        ListNode node = dummy;
        for (int i = 0; i < index; i++) {    // 這裏是< 而不是<=
            node = node.next;
        }
        newNode.next = node.next;
        node.next = newNode;

        size++;
    }
    
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {  // 修正邊界條件
            return;
        }

        ListNode node = dummy;
        for (int i = 0; i < index; i++) {
            node = node.next;  // 先找到要刪除節點的前驅節點
        }
        node.next = node.next.next;  // 然後再進行刪除
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */