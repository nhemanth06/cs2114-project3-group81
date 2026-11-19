Run Instructions: 

To run the program, simply press the run button on the MainLoop.java file.


Reflection

Several things changed from our first prototype.
Most importantly, the core features of the MVP have been completed, which includes the following:
 - Adding orders to the queue in order of importance (Non-trivial as this utilizes both DLinkedDeque/Linkedstack data states)
 - Viewing the highest priority order
 - Viewing completed/waiting orders
 - Completing orders in accordance to their priorty
 - A complete list of completed orders
 

Behind the scenes, this included the implementation of what was listed under what did not work in the previous prototype. Originally we had the MainLoop class essentially take care of every aspect of Model/View/Controller
which had to be revised in accordance to the rubric. This ended up increasing the overall amount of tests & exceptions that needed to be caught and accounted for.

What doesn't work yet?

Working and tested: All five MVP commands (add order, view next, complete next, view completed, view counts) run through CafeController without crashing. The bad-input cases from our scope are handled with an error message and a re-prompt: non-numeric or out-of-range menu choices, a blank name, a name over 50 characters, a drink number not on the menu, a size other than S/M/L, a priority answer other than y/n, and viewing or completing an order when the line is empty. All 40 JUnit tests (DLinkedDequeTest, OrderQueueTest, CafeControllerTest) pass.

Not working or missing:

- Waiting-time tracker (our D3 change feature) is not implemented yet. Drinks have no preparation times, OrderQueue does not calculate estimated waits, and there is no menu command to show them.

- No JUnit tests for DrinkOrder. Its validation (checkName, checkDrink, checkSize) works in the console but is not covered by tests.

- Rush-hour mode (stretch goal) has not been started - this feature may be dropped.


Next Steps

The next steps include polishing our current work and making sure that adding on to it is done in a correct and concise fashion. A major reflection would include the allocation of work; the allocation can be spread out in a better manner. Besides that, the group will begin to work on more interesting features, like seasonal menus, exporting completed order history to csv and cancelling an order by name instead of only being allowed to complete orders.

If given the chance, the group will work towards a GUI during the week of the final deliverable.

As for the projected attribution for each part, Richard & Hemanth will continue to write the classes/test classes which are to be reviewed by Talal and Aidan. The group as a collective will agree on which stretch goals can stil be implemented or dropped; based on this discussion, the work distribution will be decided there.

Flash Demonstration
Our demo will be relatively simple as there are not a plethora of features within this project.
We plan to spend the first quater demonstrating bad input and the exceptions/re-prompting that guides the user in the correct direction. Most of the demonstration will be about the main purpose of the model. That is, adding and keeping track of orders in the correct manner (highest priority orders, the oldest priority, take highest priority in what gets completed first, Completing/cancelling orders, showing updtated deque information matches and reflects the intended logic, and demonstrating a that a log of completed orders for a shift can be exported to csv.

Attribution for D4

- Hemanth: Deque Implementation/Deque Test Class/OrderQueue

- Richard: DrinkOrder class/MainLoop class/LinkedStack & LinkedStack interface/OrderQueue/DrinkOrder/CafeController

- Talal: Attribution Table/OrderQueue/LinkedStack implementation

- Aidan: README Review

Projected Attribution for D5

File / Class   ||  	Primary Owner(s)   ||  	Role & Contribution

CafeController.java //	Talal, Aidan	 // Menu options and order-command handling

CafeControllerTest.java //	Richard	//  Tests for controller inputs and menu flow

ConsoleView.java //	Richard //	Menu display and order information output

Deque.java  //	Hemanth //	Queue wrapper and snapshot methods

DequeInterface.java //  Hemanth  // Interface provided or reviewed

DLinkedDeque.java  //  Richard	 //  Doubly linked deque operations

DLinkedDequeTest.java // 	Aidan	// JUnit tests for deque operations and edge cases

DrinkOrder.java	// Richard // 	Order information and field validation

EmptyQueueException.java	 //  Talal 	//  Exception class provided or reviewed

MainLoop.java // 	Hemanth  //	 Main application entry point

OrderQueue.java //	Richard  // 	Queue management and order sequencing

OrderQueueTest.java 	//  Aidan	 // Tests for queue operations and order sequencing

README.md // Talal, everyone // 	Reflection, next steps, and attribution documentation


Group Gen AI Transcript:

The group made moderate use of AI and also opted to use previous class material as a refrence when implementing deque/LinkedStack logic & syntax. What the group plans to do is utilize AI essentially the same way later in the project; mainly to cover weak aspects, ensure our tests have good coverage and overall strengthen the integrity of our code.
