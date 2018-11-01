# Multiplayer Connect Four
## Description
### What?
Multiplayer Connect Four is our attempt at making an online multiplayer Connect Four game.
### Why?
We are making Connect Four a multiplayer game so that the two players don't have to play on the same computer.
### How?
The first computer (the server) will be running the Connect Four game. The second computer (the client) will be able to connect to the server and the two can interact.

## Deliverables
1. The server's version of Connect Four.
2. The client's version of Connect Four.
3. Documentation

## Plan
- Week 1:
1. Prepare the Connect Four code we already have to add sockets
2. Find ways to optimize code and fix any remaining bugs
- Week 2:
1. Begin working on the server
- Week 3:
1. Finish working on the server
2. Begin working on the client
- Week 4:
1. Finish the client version
2. Make sure the server & client can communicate
- Week 5:
1. Optimize code
2. Begin working on documentation
- Week 6:
1. Play test
2. Complete documentation
3. Fix any remaining bugs

## Team Members
- Colin Hennessey: Networking
- Jonathon Macone: Design & Backend

# Comments
1. Provide more info about the Connect Four game. At least a brief description of what it is and how the game is played. Also a link to the wiki page if exists.
2. It is a bit confusing how you design the game. Is it client-server or client-client? Looks like it is better to have a server handle the game management: create a game, let user join the game, etc. From what you described, it is more like 2 clients connect to each other and exchange messages rather than client-server model
3. Need to be more specific about the deliverables. What are the features of the client? What scenarios do the game support?
4. Adjust the plan to fit in 5 weeks schedule
5. ProgressUpdates.md is missing!
