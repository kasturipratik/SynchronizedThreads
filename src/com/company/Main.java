package com.company;


/* Thread synchronization :-
 	---------------------
 	The process of locking the current thread until its job completed and
 	does not allow any other thread.
 */
class Reserve implements Runnable
{
    //available berths are 1
    private int available = 1;
    private int wanted;
    //accept wated berths at run time
    public Reserve(int i)
    {
        wanted=i;
    }

    public synchronized void run()
    {
        //display avilable berths
        System.out.println("Available Berths ="+available);
        //if available berths are more than wanted berths

        if(available >=wanted)
        {
            //get the name of passenger
            String name=Thread.currentThread().getName();
            //allot the berth to him
            System.out.println(wanted+" Berths reserved for"+name);
            try
            {
                Thread.sleep(1000); //wait for printing the ticket
                available=available-wanted;
                //update the no. of available berths
            }
            catch(InterruptedException e)
            {}
        }
        //if available berths are less, display sorry
        else
        {
            System.out.println("Sorry, no berths");
        }

    } //close of run()
}

 class Main
{
    public static void main(String ar[])
    {
        //tell that 1 berth is needed
        Reserve obj=new Reserve(1);
        //attach first thread to the object
        Thread t1=new Thread(obj);
        //attachsecondthread to the same object
        Thread t2=new Thread(obj);
        //take the thread names as persons names
        t1.setName("First person");
        t2.setName("Second Person");
        //send the requests for berth
        t1.start();
        t2.start();
    }
}

