import java.util.ArrayList;

public class ControlQueue {
	
	private ArrayList<Command> queue;

	public ControlQueue() {
		queue = new ArrayList<>();
	}

	public void push(Command c) {
		queue.add(c);
	}

	public void insert(int i, Command c) {
		queue.add(i, c);
	}

	public void replace(int i, Command c) {
		queue.set(i, c);
	}

	public Command get(int i) {
		return queue.get(i);
	}

	public Command pop() {
		Command c = queue.get(0);
		queue.remove(0);
		return c;
	}
}