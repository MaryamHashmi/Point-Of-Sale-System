# Point-Of-Sale-System

### Software Construction

## ASSIGNMENT # 3

  

Introduction:

We’ve built an enterprise-level distributed Point-of-Sale System consisting of three separate applications:

  

- A command-line interface to carry out sales, manage inventory and employees in any single store. 
  

- A graphical interface for analyzing sales from all stores. 
  

- A graphical interface for tracking an order, so to improve usability.  
  

In any distributed system, a compromise between Partition Tolerance, Consistency and Availability has to be made (the CAP theorem). We’ve chosen to compromise Consistency in favour of the other two. Though, we do provide Eventual Consistency. 

  

The reasons for this decision are as follow:

  

- Availability is a big deal because no store can risk turning customers away. 
  

- Partition Tolerance is to be expected due to network failures in any distributed system connected via the Internet. 
  

We’ve built the application using CouchDB, Java and PHP. 

Link for project: [https://github.com/MaryamHashmi/Point-Of-Sale-System](https://github.com/MaryamHashmi/Point-Of-Sale-System)

  

Features:

Our implemented system provides four key features:

  

1. Inventory Management 
    - View inventory items 
    - Add new item to the inventory 
    - Remove item from the inventory 

2. Customer Management 
    - Adding a new order 
    - View pending orders 
    - Completing a pending order 

3. Employee Management 
    - View all employees 
    - Add new employee 
    - Remove an employee 

4. Sales Reporting 
    - Accessing synced records of all three cafes 
    - Graphical representation of accumulated data 

  
  

We also provide an interface to end-users for tracking their orders via the Order ID. Accordingly, the app notifies them automatically when their order is ready. 

  

Novelty in Approach:

- We’ve used the Continuous Replication feature of CouchDB in order to make the system work in a distributed environment with eventual consistency. Accordingly, we propose that each cafe would have its own copy of the application running, with its own local database. And then from all three cafes, the sales information would get synced with the global database. Because we’re using an existing feature of CouchDB, we did not had to implement any synchronization protocol ourself.  
  

- A sale record made through the system is never updated. This means that when sales record get synced with the global database, there are no chances of conflict. This is great because CouchDB does not provide conflict management out of the box. 
  

- We’ve given each individual cafe independence of managing their record of employees and inventory. This makes sense in the context of food chains, which have hundreds of local cafes and thus an admin cannot manage all the information globally. 

- When an order is ready, the sales person can see the list of all pending orders along with order items info. He / she can thus match a customer with the perspective order.    
  

Unit Tests:

Using the JUnit Framework, we’ve written tests to assert:

  

- All relevant databases exist in the machine running the system. 
  

- The databases are accessible by the system. 
  

- Employee adding / removing functionality works. 
  

- Item adding / removing functionality works. 
  

- Input sanitization takes place so to not corrupt the database records. 
  
