/*
    Question asked in Amazon interview:
    Add 1 in linked list like:
    // add 1 in last if its 10 make it 0 and put 1 in prev
    1->2->3
    output: 1->2->3
    1->2->9
    output: 1->3->0
    1->9->9
    output: 2->0->0
    9->9->9
    output: 1->0->0->0 // added 1 more head as 1

    Approach:
    1. (Mine) Function name : addOne 
        Used recursion to achieve the result. First we check if the head is empty if yes make a new node as head and data as 1.
        Now, if the head is the only node then add 1 to it. If it became 10 then make it 0 and add a new node as 1 as new head.
        If head is not the only node then we will recall the function again, here we have used the concept of recursion (function 
        stack calling) so we will call it till its the last node then we will increment it according to the above condition now 
        when we reach second last node we check if next node is more than 9 so make that node as 0 and current node +1 and so on.
        If we have like 99 first it will be 9->10 then when it reaches 9 then it will make last node as 0 and increment 9. It will
        be 10->0 then in if we check if we reached head and no prev node is there so we make it 0 and make new node as 1 and make head.
    2. Reverse the linkedlist then add one and do the carry if neccessary basically iteration if last node then add new node. Then at 
        rev it before returning.

*/
#include <stdio.h>
#include <stdlib.h>
typedef struct linkedList{
    int data;
    struct linkedList *next;
} LL;
LL* insert(LL *head, int data) {
    LL *newNode = (LL*) malloc(sizeof(LL));
    newNode->data = data;
    newNode->next = NULL;
    if(!head) {
        head = newNode;
    } else {
        newNode->next = head;
        head = newNode;
    }
    return head;
}
LL* addOne(LL *head, LL *temp) { // This method is useful if the head is global then at last we dont need to worry to return head as new changes are already reflected
    if(!head) { // If head is empty
        return insert(head, 1); // create a new head and send it
    }
    if(temp->next) { // If its not the last node
        addOne(head, temp->next); // do recursion and get to last node 
        if(temp->next->data > 9) { // If the next node value is more than 9 then make it as 0 and increment current and leave rest to god
            temp->next->data = 0; 
            ++temp->data;
        }
    } else { // If its the last node then just increment it no calling
        ++temp->data;
    }
    if(head == temp && temp->data > 9) { // If its the head and we have 10 then make a new node and make it as head
        temp->data = 0;   
        return insert(head,1); 
    }
    return temp;
}
LL* rev(LL *head) { // Function to reverse a linkedlist
    if(!head) return head;
    LL *prev = NULL, *next = NULL;
    LL *curr = head;
    while(curr) {
        next = curr->next;
        curr->next = prev;
        prev = curr;
        curr = next;
    }
    head = prev;
    return head;
}
LL* revAddOne(LL *head) {
    if(!head) return head;
    head = rev(head); // Reverse it
    LL *temp = head;
    int adder = 1; // This will store what to add in the list
    while(temp) {
        temp->data += adder;
        if(temp->data > 9) { // If its 10 then next sum will be with 1 
            temp->data = 0;
            adder = 1;
        } else adder = 0; // If not 10 no carry
        if(!temp->next && adder) { // If 10 but last node then add new node at last that will be new head after rev
            LL *newNode = (LL*) malloc(sizeof(LL));
            newNode->data = 1;
            temp->next = newNode;
            break;
        }
        temp = temp->next;
    }
    return rev(head); // return after rev
}
void printLL(LL *head) {
    if(!head) return;
    LL *temp = head;
    while(temp) {
        printf(temp->next?"%d->":"%d\n", temp->data);
        temp = temp->next;
    }
}
int main() {
    LL *head;
    int no;
    scanf("%d", &no);
    while(no--) {
        int data;
        scanf("%d", &data);
        head = insert(head, data);
    }
    printLL(head);
    LL *temp = head;
    // head = addOne(head, temp); // Recursion method
    head = revAddOne(temp); // Reversal iteration method
    printLL(head);
    return 0;
}
