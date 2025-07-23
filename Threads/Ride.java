// Multi-Threaded Ride-Sharing System (Uber-like Dispatcher)
// Design and implement a thread-safe ride dispatch system for a ride-sharing app. 
// Your system must match riders and drivers in a way that:
// Multiple Rider threads can request rides concurrently.
// Multiple Driver threads can become available concurrently.
// A Dispatcher should match one rider to one available driver atomically and safely (no double booking).
// The system must log each successful match.
// No race conditions should occur.
// Unmatched riders or drivers should wait (simulate using thread blocking).

// Classes required Rider, Driver, Dispatcher

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class RideDetails
{
    public Rider rider;
    public Driver driver;
    public int rideId;
    public boolean isActive;

    public RideDetails(Rider rider, Driver driver, int rideId, boolean active)
    {
        this.rider = rider;
        this.driver = driver;
        this.rideId = rideId;
        this.isActive = active;
    }

    public void stopRide()
    {
        this.isActive = false;
    }

    public void startRide()
    {
        this.isActive = true;
    }
}
class Rider
{
    public int id; // getter and setters to be decided later
    private String name;
    public RideDetails rideDetails;

    public Rider(int id, String name)
    {
        this.id = id; this.name = name;
    }

    public void findRide()
    {
        if(rideDetails == null)
        {
            Dispatcher.getInstance().RegisterFindBookingForRider(this);
        }
    }

    public void endRide()
    {
        Dispatcher.getInstance().EndBookingForRider(this);
    }
    
    public void startRide(RideDetails rideDetails)
    {
        this.rideDetails = rideDetails;
    }

    public void stopRide()
    {
        this.rideDetails = null;
    }

}

class Driver
{
    public int id;
    private String name;
    public RideDetails rideDetails;

    public Driver(int id, String name)
    {
        this.id = id; this.name = name;
    }

    public boolean isAvailableToBeBooked()
    {
        return rideDetails == null ? true : false;
    } 

    public void findRide()
    {
        if(rideDetails == null)
        {
            Dispatcher.getInstance().RegisterFindBookingForDriver(this);
        }
    }

    public void endRide()
    {
        Dispatcher.getInstance().EndBookingForDriver(this);
    }

    public void startRide(RideDetails rideDetails)
    {
        this.rideDetails = rideDetails;
    }

    public void stopRide()
    {
        this.rideDetails = null;
    }
}

class Dispatcher
{
    private final BlockingQueue <Driver> drivers = new LinkedBlockingQueue<>();
    private final BlockingQueue <Rider> riders = new LinkedBlockingQueue<>();
    private final ConcurrentHashMap<Integer, RideDetails> bookings = new ConcurrentHashMap<>();
    private final ExecutorService matcherExecutor = Executors.newSingleThreadExecutor();
    private int RideNumber = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition matchPossible = lock.newCondition(); 
    private static Dispatcher instance;

    private Dispatcher() {
        startMatchScheduler(); // Start the scheduler on instance creation
    }

    public static Dispatcher getInstance() {
        if (instance == null) {
            synchronized (Dispatcher.class) {
                if (instance == null) {
                    instance = new Dispatcher();
                }
            }
        }
        return instance;
    }

    public void RegisterFindBookingForRider(Rider rider)
    {
        try{
            // lock.lock();
            riders.add(rider);
            // matchPossible.signalAll();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            // lock.unlock();
        }
    }
    public void RegisterFindBookingForDriver(Driver driver)
    {
        try{
            // lock.lock();
            drivers.add(driver);
            // matchPossible.signalAll();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            // lock.unlock();
        }
    }
    public void EndBookingForRider(Rider rider)
    {
        RideDetails rideDetails = rider.rideDetails;
        int rideId = rideDetails.rideId;
        rider.stopRide();
        rider.rideDetails.driver.stopRide();
        rideDetails.stopRide();
        bookings.put(rideId, rideDetails);
    }
    public void EndBookingForDriver(Driver driver)
    {
        RideDetails rideDetails = driver.rideDetails;
        int rideId = rideDetails.rideId;
        driver.stopRide();
        driver.rideDetails.rider.stopRide();
        rideDetails.stopRide();
        bookings.put(rideId, rideDetails);
    }


    public void startMatchScheduler()
    {
        matcherExecutor.submit(
            () -> {
                while(true)
                {
                    try{
                        // lock.lock();
                        // // Wait until there's at least one rider and one driver
                        // while (riders.isEmpty() || drivers.isEmpty()) {
                        //     matchPossible.await(); // release lock and wait
                        // }

                        Rider bookingForRider = riders.take();
                        Driver bookingForDriver = drivers.take();
                        RideNumber += 1;
                        RideDetails ride = new RideDetails(bookingForRider, bookingForDriver,RideNumber, true);
                        bookingForRider.startRide(ride);
                        bookingForDriver.startRide(ride);
                        bookings.put(RideNumber, ride);
                        System.out.println("Matched Rider-" + bookingForRider.id + "with Driver-"+bookingForDriver.id );
                    }
                    catch(Exception ex)
                    {
                        throw ex;
                    }
                    finally
                    {
                        // lock.unlock();
                    }
                }
            }
        );

    }
}
