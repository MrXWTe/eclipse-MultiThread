package cn.xuweiteng.Thread05;

import java.util.LinkedList;

/**
 * 异步非阻塞的事件队列实现
 * @author MrXu
 *
 */
public class EventQueue {
	private final int max;
	
	static class Event{
		
	}
	
	private final LinkedList<Event> eventQueue = new LinkedList<>();
	
	private final static int DEFAULT_MAX_EVENT = 10;
	
	public EventQueue() {
		this(DEFAULT_MAX_EVENT);
	}
	
	public EventQueue(int max) {
		this.max = max;
	}
	
	public void offer(Event event) {
		synchronized (eventQueue) {
			if(eventQueue.size() >= max) {
				try {
					System.out.println(Thread.currentThread().getName() +
							" The queue is full");
					eventQueue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + 
					" the new event is committed");
			eventQueue.addLast(event);
			eventQueue.notify();
		}
	}
	public Event take() {
		synchronized (eventQueue) {
			if(eventQueue.isEmpty()) {
				try {
					System.out.println(Thread.currentThread().getName() +
							" the queue is empty");
					eventQueue.wait();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			Event event = eventQueue.removeFirst();
			this.eventQueue.notify();
			System.out.println(Thread.currentThread().getName() +
					" the event: " + event + "is handled.");
			return event;
		}
	}	
}
