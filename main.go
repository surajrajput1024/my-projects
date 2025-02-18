package main

import (
	"context"
	"fmt"
	"sync"
	"time"
)

type BinaryTreeNode struct {
	Val   int
	Left  *BinaryTreeNode
	Right *BinaryTreeNode
}

type ListNode struct {
	Data int
	Next *ListNode
}

func lowestCommonAncestor(root, p, q *BinaryTreeNode) *BinaryTreeNode {
	if root == nil || root == p || root == q {
		return root
	}

	left := lowestCommonAncestor(root.Left, p, q)
	right := lowestCommonAncestor(root.Right, p, q)

	if left == nil {
		return right
	} else if right == nil {
		return left
	} else {
		return root
	}
}

func reverseList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}

	reversed := reverseList(head.Next)
	head.Next.Next = head
	head.Next = nil

	return reversed
}

/**
* Goroutine Example
*
* What is a goroutine?
* - A goroutine is like a lightweight thread in Go
* - It allows functions to run concurrently (at the same time)
* - Created by using 'go' keyword before a function
* - Much cheaper than traditional threads
* - Managed by Go runtime, not OS
*
* Common Interview Questions:
* 1. How is goroutine different from thread?
* 2. What happens to goroutine if main function ends?
* 3. How to wait for goroutine to finish?
**/
func basicGoroutine() {
	fmt.Println("1. Basic Goroutine Example:")

	go func() {
		fmt.Println("Hello from goroutine")
	}()

	time.Sleep(1 * time.Second)
	fmt.Println("Main function")
}

/**
* Channel Example
*
* What is a channel?
* - Channels are pipes that connect goroutines
* - Used for communication between goroutines
* - Can send and receive values
* - Blocks until other side is ready
* - Created using make(chan type)
*
* Common Interview Questions:
* 1. What are buffered vs unbuffered channels?
* 2. How to prevent deadlocks in channels?
* 3. When to close a channel?
**/
func channelExample() {
	fmt.Println("\n2. Channel Example:")

	ch := make(chan string)
	go func() {
		ch <- "Message from goroutine"
	}()

	message := <-ch
	fmt.Println(message)
}

/**
* WaitGroup Example
*
* What is WaitGroup?
* - Used to wait for multiple goroutines to finish
* - Like a counter that can be incremented and decremented
* - Main goroutine waits until counter becomes zero
* - Add(): increment counter
* - Done(): decrement counter
* - Wait(): block until counter is zero
*
* Common Interview Questions:
* 1. Why use WaitGroup instead of sleep?
* 2. What happens if you forget to call Done()?
* 3. Can WaitGroup be reused?
**/
func waitGroupExample() {
	fmt.Println("\n3. WaitGroup Example:")

	var wg sync.WaitGroup

	for i := 1; i <= 3; i++ {
		wg.Add(1)
		go func(id int) {
			defer wg.Done()
			fmt.Printf("Goroutine %d executing\n", id)
		}(i)
	}

	wg.Wait()
}

/**
* Context Example
*
* What is Context?
* - Used for cancellation and deadline control
* - Can pass request-scoped values
* - Helps manage timeouts and cancellation
* - Propagates cancellation down the call chain
* - Common in HTTP servers and API calls
*
* Common Interview Questions:
* 1. When to use context.Background() vs context.TODO()?
* 2. How to handle context cancellation?
* 3. Best practices for context values?
**/
func contextExample() {
	fmt.Println("\n4. Context Example:")

	ctx, cancel := context.WithTimeout(context.Background(), 2*time.Second)
	defer cancel()

	go func() {
		select {
		case <-ctx.Done():
			fmt.Println("Context cancelled or timeout")
		case <-time.After(1 * time.Second):
			fmt.Println("Work completed before timeout")
		}
	}()

	time.Sleep(2 * time.Second)
}

/**
* Mutex Example
*
* What is Mutex?
* - Mutual exclusion lock
* - Prevents multiple goroutines from accessing shared resources simultaneously
* - Lock(): acquire the lock
* - Unlock(): release the lock
* - Used to prevent race conditions
*
* Common Interview Questions:
* 1. Difference between Mutex and RWMutex?
* 2. What is a deadlock and how to prevent it?
* 3. When to use atomic operations instead of mutex?
**/
func mutexExample() {
	fmt.Println("\n5. Mutex Example:")

	var mutex sync.Mutex
	counter := 0

	var wg sync.WaitGroup
	for i := 0; i < 3; i++ {
		wg.Add(1)
		go func() {
			defer wg.Done()
			mutex.Lock()
			counter++
			fmt.Printf("Counter: %d\n", counter)
			mutex.Unlock()
		}()
	}

	wg.Wait()
}

func main() {
	basicGoroutine()
	channelExample()
	waitGroupExample()
	contextExample()
	mutexExample()
}
