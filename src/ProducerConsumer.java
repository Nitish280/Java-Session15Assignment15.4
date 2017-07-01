/*
 * here we create a program of producer and consumer problem
 * here we create the class Item
 * 
 */
class Item {
  //here we take one integer value content     
    volatile int content;   
   /*
    * here we take the two method set and Get
    * getter gets the value of the content and setter set the value of that content 
    */
        public synchronized void setContent(int content) {   
            this.content=content;   
        }   
   
        public synchronized int getContent() {   
            return content;   
        }   
}   
 /*
  * here we take the class producer which is Runnable 
  * and create the constructor producer 
  * here we use get and set to get the value of the item
  * and set the value of the item
  */
class Producer implements Runnable {   
   
    Item item;   
   
    Producer(Item item) {   
         this.item=item;   
    } 
     
   
    public Item getItem() {
        return item;
    }
 
 
    public void setItem(Item item) {
        this.item = item;
    }
 /*
  * (non-Javadoc)
  * @see java.lang.Runnable#run()
  * Here we synchronize our method so that only one value can use at a time
  * here we initialize value from 0 to 10 
  */
 
    public void run() {  
        int i =0;
        synchronized(this)
        {
            while(true)
            {   
                ++i;
                if (i==10) return;
                System.out.println("Putting the value in item " + i  );   
                item.setContent(i);
                //here it will notify the next value that u have to wait for the some time
                notify();   
                try{
                    wait();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }   
        }   
    }   
}
  /*
  * 
  * here we take the class consumer which is Runnable 
  * and create the constructor consumer
  * 
  */ 
   
   
class Consumer implements Runnable {   
        
    Producer producer;   
     
   
    Consumer(Producer producer) {   
        this.producer = producer;   
    }   
   /*
    * (non-Javadoc)
    * @see java.lang.Runnable#run()
    * Here we synchronize our method so that only one value can use at a time
    */
   
    public void run() 
    { 
        synchronized(producer)
        {
            while(true){
                    System.out.println("Consuming " +producer.getItem().getContent());
                    producer.notify();
                        try {
                            if(producer.getItem().getContent() == 9)   
                                return;  
                            producer.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                     
            }
         
        }   
   
    }  
}

public class ProducerConsumer {
//here we create the main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//here we create the object for the item
		 Item item1 = new Item();   
		  //here we create object of the producer and consumer 
	        Producer producer = new Producer(item1);   
	        Consumer consumer = new Consumer(producer);   
	   //here we create the thread for the producer and consumer
	        Thread producerThread = new Thread(producer);   
	        Thread consumerThread = new Thread(consumer);   
	   //here we call the run method of producer and consumer 
	        producerThread.start();   
	        consumerThread.start();   
	    }   
	}
