Explanation
Concept:
Frontier list will be based on the priority queue. Every new node will be added at the end of the list and the list will give priority to the least cost path.

The node at the top of the frontier list will be added to the expand list, which shows that this node is going to be explored in the next step. It will not repeat any node. If the node has already been explored, you can discard it.

Explored list will be having the nodes list, which will be completely explored.

Algo:
Add the Starting node S in the frontier list with the path cost g(n) = 0 (starting point is at 0 path cost).

Add this node to the Explored list, as we only have a single node in the frontier list. If we have multiple nodes, then we will add that one node at the top of the frontier.

Now, explore this node by visiting all of its child nodes. After that, add this node to the Explored list, as it is now fully explored.

Check if the added node is the goal node or not. Stop if the goal node is found, or else move on to the next step.

Since new nodes are added to the frontier list, we need to compare and set the priority queue again, depending upon the priority, that is, the minimum path cost g(n).

Now, move to back to step 2 and repeat the steps until the goal node is not added to the explored list.

Solutions:
Actual path: This is obtained by the frontier list.

Traversed path: This is obtained by the explored list.
